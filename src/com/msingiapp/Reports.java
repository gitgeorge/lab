package com.msingiapp;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;

import com.msingiapp.report.DBAdapter;

public class Reports extends Activity implements OnClickListener {
	public static String subj;
	Button report, delete;
	public static String query;
	final Context context = this;
	CalendarView cal;
	DBAdapter dbAdapter = new DBAdapter();

	@SuppressLint("DefaultLocale")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		View title = getWindow().findViewById(android.R.id.title);
		View titleBar = (View) title.getParent();
		titleBar.setBackgroundColor(getResources().getColor(
				R.color.dark_green_color));
		setContentView(R.layout.rpt);

		report = (Button) findViewById(R.id.allreports);
		report.setOnClickListener(this);
		delete = (Button) findViewById(R.id.delete);
		delete.setOnClickListener(this);

		Spinner spinnersubject = (Spinner) findViewById(R.id.spinnerSub);

		try {
			dbAdapter.open();
			List<String> subjects = dbAdapter.getSubjects();
			subjects.add(0, "Please select subject to view reports");
			// Creating adapter for spinner
			ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, subjects);
			dataAdapter2
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinnersubject.setAdapter(dataAdapter2);

			spinnersubject
					.setOnItemSelectedListener(new OnItemSelectedListener() {
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							// On selecting a spinner item
							String label = parent.getItemAtPosition(position)
									.toString();
							query = "select _id,subject,percentage_score,grade,remarks,date from exam_scores where subject='"
									+ label + "';";
							if (!label
									.equals("Please select subject to view reports")) {
								Intent rpt = new Intent(Reports.this,
										Report_Main.class);
								startActivity(rpt);
							}
						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub
						}
					});

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Spinner spinnerdate = (Spinner) findViewById(R.id.spinnerDate);

		try {
			dbAdapter.open();

			List<String> dates = dbAdapter.getDates();
			dates.add(0, "Please select date to view reports");
			// Creating adapter for spinner
			ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, dates);
			dataAdapter2
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinnerdate.setAdapter(dataAdapter2);

			spinnerdate.setOnItemSelectedListener(new OnItemSelectedListener()

			{
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					// On selecting a spinner item
					String label = parent.getItemAtPosition(position)
							.toString();
					query = "select _id,subject,percentage_score,grade,remarks,date from exam_scores where date='"
							+ label + "';";
					if (!label.equals("Please select date to view reports")) {

						Intent rpt = new Intent(Reports.this, Report_Main.class);
						startActivity(rpt);
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
				}

			});

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.allreports:
			query = "select _id,subject,percentage_score,grade,remarks,date from exam_scores;";
			Intent rpt = new Intent(this, Report_Main.class);
			startActivity(rpt);
			break;
		case R.id.delete:
			try {
				dbAdapter.open();
				dbAdapter.deleteRecords();
				final Dialog dialog = new Dialog(context);
				dialog.setContentView(R.layout.report_del);
				dialog.setTitle("MsingiPACK");
				dialog.setCanceledOnTouchOutside(false);

				Button dialogButtonOk = (Button) dialog
						.findViewById(R.id.dialogButtonOK);
				// if button is clicked, close the custom dialog
				dialogButtonOk.setOnClickListener(new OnClickListener() {
					@SuppressWarnings("static-access")
					@Override
					public void onClick(View v) {
						dialog.dismiss();
						Toast.makeText(getApplicationContext(),
								"All records have been cleared",
								Toast.LENGTH_LONG).show();
						Intent mn = new Intent(Reports.this, MainMenu.class);
						startActivity(mn);
						finish();
					}

				});
				Button dialogCancel = (Button) dialog
						.findViewById(R.id.dialogButtonCancel);
				dialogCancel.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
				dialog.show();
				break;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Reports.this.finish();
		Intent mn = new Intent(Reports.this, MainMenu.class);
		startActivity(mn);
		
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
		try {
			dbAdapter.close();
			super.onDestroy();
			finish();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
