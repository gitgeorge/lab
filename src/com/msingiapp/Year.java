package com.msingiapp;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Year extends ListActivity {
	public static String question_year;
	DatabaseHelper db = new DatabaseHelper(this);
	ExamSession ex;
	ListView list;
	TextView subject;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.year);
		// find view
		subject = (TextView) findViewById(R.id.tvsub);
		subject.setText(Subjects.subject);
		list = getListView();
		// set up the list adapter to fetch lists array from subjects.xml in
		// values
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, getResources()
						.getStringArray(R.array.kcpeyear)));
		// adding action to the list
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// call various activity when item is selected in each instance
				if (position == 0) {
					question_year = "2005";
					checkDatabase();
				} else if (position == 1) {
					question_year = "2006";
					checkDatabase();
				} else if (position == 2) {
					question_year = "2007";
					checkDatabase();
				} else if (position == 3) {
					question_year = "2008";
					checkDatabase();
				} else if (position == 4) {
					question_year = "2009";
					checkDatabase();
				} else if (position == 5) {
					question_year = "2010";
					checkDatabase();
				} else if (position == 6) {
					question_year = "2011";
					checkDatabase();
				} else if (position == 7) {
					question_year = "2012";
					checkDatabase();
				} else if (position == 7) {
					question_year = "2013";
					checkDatabase();
				}

			}

		});

	}

	// method to check state of database
	public void checkDatabase() {
		List<ExamSession> examQuestion = db.getAllQusetions();
		if (examQuestion.isEmpty()) {
			Toast.makeText(getApplicationContext(),
					Subjects.subject+"	exam content not ready !", Toast.LENGTH_LONG)
					.show();
		} else {
			Intent exam = new Intent(Year.this, Exam.class);
			startActivity(exam);
		}

	}
}