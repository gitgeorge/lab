package com.msingiapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Subjects extends ListActivity {
	public static String  subject;

	ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subjects);
		// find view
		list = getListView();
		// set up the list adapter to fetch lists array from subjects.xml in
		// values
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, getResources()
						.getStringArray(R.array.subjects)));
		// adding action to the list
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// call various activity when item is selected in each instance
				if (position == 0) {
					subject = "Mathematics";
					Intent maths = new Intent(Subjects.this, Year.class);
					startActivity(maths);
				} else if (position == 1) {
					subject = "English";
					Intent maths = new Intent(Subjects.this, Year.class);
					startActivity(maths);

				} else if (position == 2) {
					subject = "Kiswahili";
					Intent maths = new Intent(Subjects.this, Year.class);
					startActivity(maths);

				} else if (position == 3) {
					subject = "Science";
					Intent maths = new Intent(Subjects.this, Year.class);
					startActivity(maths);

				} else if (position == 4) {
					subject = "Social Studies";
					Intent maths = new Intent(Subjects.this, Year.class);
					startActivity(maths);

				} else if (position == 5) {
					subject = "Cre";
					Intent maths = new Intent(Subjects.this, Year.class);
					startActivity(maths);

				} else if (position == 6) {
					subject = "Ire";
					Intent maths = new Intent(Subjects.this, Year.class);
					startActivity(maths);
				} else if (position == 7) {
					subject = "Hre";
					Intent maths = new Intent(Subjects.this, Year.class);
					startActivity(maths);
				}

			}

		});

	}

}
