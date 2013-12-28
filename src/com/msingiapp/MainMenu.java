package com.msingiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenu extends Activity implements OnClickListener {
	Button exam, help, share, quit, about;

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
		quit = (Button) findViewById(R.id.btnquit);
		quit.setOnClickListener(this);
		about = (Button) findViewById(R.id.btnabout);
		about.setOnClickListener(this);
		
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
		case R.id.btnquit:
			finish();
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			break;

		}
	}

}
