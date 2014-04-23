package com.msingiapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.msingiapp.report.DBAdapter;

public class Report_Main extends Activity {
	WebView webReports;
	DBAdapter dbAdapter = new DBAdapter();

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		View title = getWindow().findViewById(android.R.id.title);
		View titleBar = (View) title.getParent();
		titleBar.setBackgroundColor(getResources().getColor(
				R.color.dark_green_color));
		setContentView(R.layout.rpt_main);
		webReports = (WebView) findViewById(R.id.report_webview);
		webReports.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		try {
			viewReports();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void viewReports() {

		try {
			dbAdapter.open();
			Cursor c = dbAdapter.getData();
			String data = "<!DOCTYPE html>\n" + "<html>\n" + "\n" + "<head>\n"
					+ "<style>\n" + "table,th,td\n" + "{\n"
					+ "border:1px solid black;\n"
					+ "border-collapse:collapse;\n" + "}\n" + "th,td\n" + "{\n"
					+ "padding:5px;\n" + "}\n" + "</style>\n" + "</head>\n"
					+ "\n" + "<body>\n" + "<table style=\"width:100%\">\n"
					+ "<tr>\n" + "  <th>No of exams</th>\n"
					+ "  <th>Subject</th>\n" + "  <th>Score</th>\n"
					+ "  <th>Grade</th>\n" + "  <th>Remarks</th>\n"
					+ "  <th>Date</th>\n";

			if (c != null) {
				if (c.moveToFirst()) {
					do {
						int no = c.getInt(c.getColumnIndex("_id"));
						String sub = c.getString(c.getColumnIndex("subject"));
						String perc = c.getString(c
								.getColumnIndex("percentage_score"));
						String grade = c.getString(c.getColumnIndex("grade"));
						String remarks = c.getString(c
								.getColumnIndex("remarks"));
						String date = c.getString(c.getColumnIndex("date"));

						data += "<tr> <td>" + no + "</td><td>" + sub
								+ "</td><td>" + perc + "</td><td>" + grade
								+ "</td><td>" + remarks + "</td><td>" + date
								+ "</td></tr>";

					} while (c.moveToNext());
					data += "</table></body></html>";
					webReports.loadData(data, "text/html", "UTF-8");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		try {
			dbAdapter.close();
			finish();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
