package com.msingiapp.report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.msingiapp.Grade;
import com.msingiapp.Report;
import com.msingiapp.Reports;
import com.msingiapp.Year;

public class DBAdapter {

	private DatabaseHelper databaseHelper;
	private SQLiteDatabase database;

	public DBAdapter open() throws Exception {
		databaseHelper = new DatabaseHelper();
		database = databaseHelper.getWritableDatabase();
		return this;
	}

	public boolean insertIntoTable() {
		try {
			/*String date = java.text.DateFormat.getDateTimeInstance().format(
					Calendar.getInstance().getTime());*/
			
			Date now = new Date();
			String date = new SimpleDateFormat("yyyy-MM-dd").format(now);

			String query = "insert into exam_scores(subject,percentage_score,grade,remarks,date)values('"
					+ Year.subTitle
					+ "','"
					+ Grade.percent
					+ "','"
					+ Grade.grade
					+ "','"
					+ Grade.remarks
					+ "','"
					+ date
					+ "');";
			database.execSQL(query);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Cursor getData() {

		return database.rawQuery(Reports.query, null);
	}

	public List<String> getSubjects() {
		List<String> subj = new ArrayList<String>();
		try {
			// Select All Query
			String selectQuery = "SELECT DISTINCT subject FROM exam_scores  ";
			System.out.println(selectQuery);

			Cursor cursor = database.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					subj.add(cursor.getString(cursor.getColumnIndex("subject")));
				} while (cursor.moveToNext());
			}

			// closing connection
			cursor.close();
			database.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		// returning lables
		return subj;

	}

	public List<String> getDates() {
		List<String> subj = new ArrayList<String>();
		try {
			// Select All Query
			String selectQuery = "SELECT DISTINCT date FROM exam_scores  ";
			System.out.println(selectQuery);

			Cursor cursor = database.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					subj.add(cursor.getString(cursor.getColumnIndex("date")));
				} while (cursor.moveToNext());
			}

			// closing connection
			cursor.close();
			database.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		// returning lables
		return subj;

	}

	public List<Report> getAllReports() {
		List<Report> reports = new ArrayList<Report>();

		// select query
		String selectQuery = "SELECT * FROM exam_scores";

		Cursor cursor = database.rawQuery(selectQuery, null);
		// cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()
		if (cursor.moveToFirst()) {
			do {
				Report rpt = new Report();
				rpt.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
				rpt.setSubject(cursor.getString(cursor
						.getColumnIndex("subject")));
				rpt.setPercentage_score(cursor.getString(cursor
						.getColumnIndex("percentage_score")));
				rpt.setGrade(cursor.getString(cursor.getColumnIndex("grade")));
				rpt.setRemarks(cursor.getString(cursor
						.getColumnIndex("remarks")));
				rpt.setDate(cursor.getString(cursor.getColumnIndex("date")));

				// Adding report to list
				reports.add(rpt);
			} while (cursor.moveToNext());
		}
		return reports;

	}

	public void deleteRecords() {
		database.execSQL("delete from exam_scores");
	}

	public void close() throws Exception {
		database.close();
	}

}
