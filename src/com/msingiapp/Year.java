package com.msingiapp;

import java.io.File;
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
	public static String question_year, subTitle;
	DatabaseHelper db = new DatabaseHelper(this);
	ExamSession ex;
	ListView list;
	TextView subject;
	static File myFile;
	public static String cssData, Data;

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
		String subt = Subjects.subject;
		if (subt.contains("_")) {
			String[] parts = subt.split("_");
			String part1 = parts[0]; // social
			String part2 = parts[1]; // studies

			subTitle = part1 + "	" + part2;

		} else {
			subTitle = Subjects.subject;
		}

		tvYear.setText(subTitle.toUpperCase());
		Spinner spinner = (Spinner) findViewById(R.id.spinner);

		List<String> year = db.getYears();

		if (Subjects.subject.equals("Kiswahili")) {
			year.add(0, "Tafathali chagua mwaka wa mtihani");
		} else if (!Subjects.subject.equals("Kiswahili")) {
			year.add(0, "Please Select KCPE  Year");
		}
		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, year);

		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);

		spinner.setOnItemSelectedListener(new OnItemSelectedListener()

		{
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// On selecting a spinner item
				String label = parent.getItemAtPosition(position).toString();
				question_year = label;
				if (!label.equals("Please Select KCPE  Year")
						&& !label.equals("Tafathali chagua mwaka wa mtihani")) {
					Intent exam = new Intent(Year.this, Exam.class);
					startActivity(exam);
					Year.this.finish();
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
					Subjects.subject + "" + Year.question_year,
					Toast.LENGTH_LONG).show();
		} else {
			Intent exam = new Intent(Year.this, Exam.class);
			startActivity(exam);
			Year.this.finish();

		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		finish();
	}
}