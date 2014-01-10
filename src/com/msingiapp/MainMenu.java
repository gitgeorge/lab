package com.msingiapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenu extends Activity implements OnClickListener {
	Button exam, help, share, quit, about;
	final Context context = this;

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
		quit = (Button) findViewById(R.id.btnquit);
		quit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				try {

					final Dialog dialog = new Dialog(context);
					dialog.setContentView(R.layout.appexit_dailog);
					dialog.setTitle("MsingiPACK");

					Button dialogButtonOk = (Button) dialog
							.findViewById(R.id.dialogButtonOK);
					// if button is clicked, close the custom dialog
					dialogButtonOk.setOnClickListener(new OnClickListener() {
						@SuppressWarnings("static-access")
						@Override
						public void onClick(View v) {
							dialog.dismiss();
							System.exit(0);
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
							// unaswredQuestions();
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
