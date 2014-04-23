package com.msingiapp;

import java.util.List;

import com.msingiapp.report.DBAdapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainMenu extends Activity implements OnClickListener {
	Button exam, help, share, quit, about, rpt;
	final Context context = this;
	DBAdapter dbAdapter = new DBAdapter();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		View title = getWindow().findViewById(android.R.id.title);
		View titleBar = (View) title.getParent();
		titleBar.setBackgroundColor(getResources().getColor(
				R.color.dark_green_color));
		setContentView(R.layout.mainmenu);
		initialize();
	}

	public void initialize() {
		exam = (Button) findViewById(R.id.btnexam);
		exam.setOnClickListener(this);
		help = (Button) findViewById(R.id.btnhelp);
		help.setOnClickListener(this);
		about = (Button) findViewById(R.id.btnabout);
		about.setOnClickListener(this);
		rpt = (Button) findViewById(R.id.btnreports);
		rpt.setOnClickListener(this);
		quit = (Button) findViewById(R.id.btnquit);
		quit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				try {

					final Dialog dialog = new Dialog(context);
					dialog.setContentView(R.layout.appexit_dailog);
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
							MainMenu.this.finish();
							Intent intent = new Intent(Intent.ACTION_MAIN);
							intent.addCategory(Intent.CATEGORY_HOME);
							intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(intent);

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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnexam:
			Intent exam = new Intent(this, Subjects.class);
			startActivity(exam);
			break;
		case R.id.btnhelp:
			Intent help = new Intent(this, Help.class);
			startActivity(help);
			break;
		case R.id.btnabout:
			Intent about = new Intent(this, About.class);
			startActivity(about);
			break;
		case R.id.btnreports:
			Boolean isSDPresent = android.os.Environment
					.getExternalStorageState().equals(
							android.os.Environment.MEDIA_MOUNTED);
			if (isSDPresent) {
				checkDatabase();
				break;
			} else if (!isSDPresent) {
				Toast.makeText(getApplicationContext(),
						"External storage missing \n please insert an sdcard",
						Toast.LENGTH_LONG).show();
				break;
			}

		}

	}

	// method to check state of database
	public void checkDatabase() {
		try {
			dbAdapter.open();
			List<Report> examQuestion = dbAdapter.getAllReports();
			if (examQuestion.isEmpty()) {
				Toast.makeText(getApplicationContext(),
						"There are no examination reports to view!\n",
						Toast.LENGTH_LONG).show();
			} else {
				Intent report = new Intent(MainMenu.this, Reports.class);
				startActivity(report);
				MainMenu.this.finish();
			}
		} catch (Exception e) {
			// TODO: handle exception
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
