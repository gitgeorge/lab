package com.msingiapp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	// The Android's default system path of your application database.
	private static String DB_PATH = "/data/data/com.msingiapp/databases/";

	private static String DB_NAME = "MsingiPackApp.sqlite";

	private SQLiteDatabase myDataBase;

	private final Context myContext;

	/**
	 * Constructor Takes and keeps a reference of the passed context in order to
	 * access to the application assets and resources.
	 * 
	 * @param context
	 */
	public DatabaseHelper(Context context) {

		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}

	/**
	 * Creates a empty database on the system and rewrites it with your own
	 * database.
	 * */
	public void createDataBase() throws IOException {

		boolean dbExist = checkDataBase();

		if (dbExist) {
			// do nothing - database already exist
		} else {

			// By calling this method and empty database will be created into
			// the default system path
			// of your application so we are gonna be able to overwrite that
			// database with our database.
			this.getReadableDatabase();

			try {

				copyDataBase();

			} catch (IOException e) {

				throw new Error("Error copying database");

			}
		}

	}

	/**
	 * Check if the database already exist to avoid re-copying the file each
	 * time you open the application.
	 * 
	 * @return true if it exists, false if it doesn't
	 */
	private boolean checkDataBase() {

		SQLiteDatabase checkDB = null;

		try {
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READWRITE);

		} catch (SQLiteException e) {

			// database does't exist yet.

		}

		if (checkDB != null) {

			checkDB.close();

		}

		return checkDB != null ? true : false;
	}

	/**
	 * Copies your database from your local assets-folder to the just created
	 * empty database in the system folder, from where it can be accessed and
	 * handled. This is done by transfering bytestream.
	 * */
	private void copyDataBase() throws IOException {

		// Open your local db as the input stream
		InputStream myInput = myContext.getAssets().open(DB_NAME);

		// Path to the just created empty db
		String outFileName = DB_PATH + DB_NAME;

		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public void openDataBase() throws SQLException {

		// Open the database
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY);
		// Toast.makeText(myContext, "connected", Toast.LENGTH_SHORT).show();

	}

	@Override
	public synchronized void close() {

		if (myDataBase != null)
			myDataBase.close();

		super.close();

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public List<ExamSession> getAllQusetions() throws SQLiteException {
		List<ExamSession> exam = new ArrayList<ExamSession>();
		try {
			createDataBase();
			openDataBase();

			SQLiteDatabase db = this.getReadableDatabase();
			String selectQuery = "   SELECT *  FROM " + Subjects.subject
					+ " where Question_year=" + Year.question_year + "    ";
			Cursor c = db.rawQuery(selectQuery, null);
			// looping through all rows and adding to list
			if (c.moveToFirst()) {
				do {
					ExamSession ex = new ExamSession();
					ex.setQuestion_id(c.getInt((c.getColumnIndex("_id"))));
					ex.setQuestion_no(c.getInt(c.getColumnIndex("Question_no")));
					ex.setQuestion_year(c.getString(c
							.getColumnIndex("Question_year")));
					ex.setQuestion(c.getString(c.getColumnIndex("Question")));
					ex.setChoice1(c.getString(c.getColumnIndex("Choice1")));
					ex.setChoice2(c.getString(c.getColumnIndex("Choice2")));
					ex.setChoice3(c.getString(c.getColumnIndex("Choice3")));
					ex.setChoice4(c.getString(c.getColumnIndex("Choice4")));
					ex.setCategory(c.getString(c.getColumnIndex("Category")));
					ex.setAnswer(c.getString(c.getColumnIndex("Answer")));
					ex.setExplanation(c.getString(c
							.getColumnIndex("Explanation")));
					// adding to questions list
					exam.add(ex);
				} while (c.moveToNext());
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exam;

	}
}
