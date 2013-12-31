package com.msingiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Subjects extends Activity {
	public static String subject;
	ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		View title = getWindow().findViewById(android.R.id.title);
		View titleBar = (View) title.getParent();
		titleBar.setBackgroundColor(getResources().getColor(
				R.color.dark_green_color));
		setContentView(R.layout.subjects);
		// find view
		String[] web = { "Mathematics", "English", "Kiswahili", "Science",
				"Social Studies", "Cristian Religious Education (C.R.E)",
				"Islamic Religious Education  (I.R.E)",
				"Hindu Religious Education (H.R.E)" };
		Integer[] imageId = { R.drawable.math, R.drawable.english,
				R.drawable.swahili, R.drawable.science,
				R.drawable.socialstudies, R.drawable.cre, R.drawable.ire,
				R.drawable.hre };
		// set up the list adapter to fetch lists array from subjects.xml in
		// values
		CustomList adapter = new CustomList(Subjects.this, web, imageId);
		// set up the list adapter to fetch lists array from subjects.xml in
		// values
		list = (ListView) findViewById(R.id.list);
		list.setAdapter(adapter);
		// adding action to the list
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// call various activity when item is selected in each instance
				if (position == 0) {
					subject = "Mathematics";
					loadNextIntent();
				} else if (position == 1) {
					subject = "English";
					loadNextIntent();
				} else if (position == 2) {
					subject = "Kiswahili";
					loadNextIntent();
				} else if (position == 3) {
					subject = "Science";
					loadNextIntent();
				} else if (position == 4) {
					subject = "Socialstudies";
					loadNextIntent();
				} else if (position == 5) {
					subject = "Cre";
					loadNextIntent();
				} else if (position == 6) {
					subject = "Ire";
					loadNextIntent();
				} else if (position == 7) {
					subject = "Hre";
					loadNextIntent();
				}
			}
		});
	}

	public void loadNextIntent() {

		Intent sub = new Intent(Subjects.this, Year.class);
		startActivity(sub);
	}

}
