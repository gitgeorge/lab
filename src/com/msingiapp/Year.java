package com.msingiapp;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Year extends Activity {
	public static String question_year;
	DatabaseHelper db = new DatabaseHelper(this);
	ExamSession ex;
	ListView list;
	TextView subject;

	@SuppressLint("DefaultLocale")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		View title = getWindow().findViewById(android.R.id.title);
		View titleBar = (View) title.getParent();
		titleBar.setBackgroundColor(getResources().getColor(
				R.color.dark_green_color));
		setContentView(R.layout.year);
		// find view
		TextView tvYear = (TextView) findViewById(R.id.tvsub);
		tvYear.setText(Subjects.subject.toUpperCase());
		Spinner spinner = (Spinner) findViewById(R.id.spinner);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.kcpeyear, android.R.layout.simple_spinner_item);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		spinner.setOnItemSelectedListener(new OnItemSelectedListener()

		{
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// call various activity when item is selected in each instance
				if (position == 0) {
					question_year = "2013";
					checkDatabase();
				} else if (position == 1) {
					question_year = "2012";
					checkDatabase();
				} else if (position == 2) {
					question_year = "2011";
					checkDatabase();
				} else if (position == 3) {
					question_year = "2010";
					checkDatabase();
				} else if (position == 4) {
					question_year = "2009";
					checkDatabase();
				} else if (position == 5) {
					question_year = "2008";
					checkDatabase();
				} else if (position == 6) {
					question_year = "2007";
					checkDatabase();
				} else if (position == 7) {
					question_year = "2006";
					checkDatabase();
				} else if (position == 8) {
					question_year = "2005";
					checkDatabase();
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});

	}

	// method to check state of database
	public void checkDatabase() {
		List<ExamSession> examQuestion = db.getAllQusetions();
		if (examQuestion.isEmpty()) {
			Toast.makeText(getApplicationContext(),
					Subjects.subject + "	exam content not ready !",
					Toast.LENGTH_LONG).show();
		} else {
			Intent exam = new Intent(Year.this, Exam.class);
			startActivity(exam);
		}

	}
}