package com.msingiapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Revision extends Activity implements OnClickListener {
	ScrollView sv;
	TextView questionNo;
	int number = 0;
	int questNo = number + 1;
	Button next, prev, quit;
	WebView qusetRev;
	public String ansSelected;
	final Context context = this;
	public String selected, correct;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		View title = getWindow().findViewById(android.R.id.title);
		View titleBar = (View) title.getParent();
		titleBar.setBackgroundColor(getResources().getColor(
				R.color.dark_green_color));
		setContentView(R.layout.revision2);
		initialize();
	}

	@SuppressLint("NewApi")
	public void initialize() {
		sv = (ScrollView) findViewById(R.id.ScrollView01);
		questionNo = (TextView) findViewById(R.id.tvrevisonNo);
		qusetRev = (WebView) findViewById(R.id.revison_quest_webview);
		qusetRev.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

		next = (Button) findViewById(R.id.Button_Next);
		next.setOnClickListener(this);
		prev = (Button) findViewById(R.id.Button_Prev);
		prev.setOnClickListener(this);
		quit = (Button) findViewById(R.id.Button_Quit);
		quit.setOnClickListener(this);
		displayFirstQuestion();

	}

	public void displayFirstQuestion() {
		// getting the elements at index 0 from database table
		Exam.ex = (ExamSession) Exam.quest.get(0);

		String questExplan = "<link rel=\"stylesheet\" type=\"text/css\" href=\"msingipack.css\" />"
				+ Exam.ex.getExplanation();

		String quiz = "<link rel=\"stylesheet\" type=\"text/css\" href=\"msingipack.css\" />"
				+ Exam.ex.getQuestion();

		String choiA = "<link rel=\"stylesheet\" type=\"text/css\" href=\"msingipack.css\" />"
				+ Exam.ex.getChoice1();

		String choiB = "<link rel=\"stylesheet\" type=\"text/css\" href=\"msingipack.css\" />"
				+ Exam.ex.getChoice2();

		String choiC = "<link rel=\"stylesheet\" type=\"text/css\" href=\"msingipack.css\" />"
				+ Exam.ex.getChoice3();

		String choiD = "<link rel=\"stylesheet\" type=\"text/css\" href=\"msingipack.css\" />"
				+ Exam.ex.getChoice4();
		String selection = "<link rel=\"stylesheet\" type=\"text/css\" href=\"msingipack.css\" />"
				+ Exam.ex.getSelectedAnswer();
		String corr = "<link rel=\"stylesheet\" type=\"text/css\" href=\"msingipack.css\" />"
				+ Exam.ex.getAnswer();

		questionNo.setText("	Question	" + questNo + "	out of "
				+ Exam.quest.size());
		qusetRev.loadDataWithBaseURL("file:///android_asset/",
				" </p>  <p id=\"question\">" + quiz + "</p> " + "<p></p>"
						+ "<strong>&nbsp;A&nbsp&nbsp</strong>" + choiA
						+ "<p></p>" + "<strong>&nbsp;B&nbsp&nbsp</strong>"
						+ choiB + "<p></p>"
						+ "<strong>&nbsp;C&nbsp&nbsp</strong>" + choiC
						+ "<p></p>" + "<strong>&nbsp;D&nbsp&nbsp</strong>"
						+ choiD + "<p></p>"
						+ "<strong>&nbsp;&nbsp&nbsp</strong>" + selected
						+ "<p></p>" + "<strong>&nbsp;&nbsp&nbsp</strong>"
						+ correct + "<p></p>"
						+ "<strong>&nbsp;&nbsp&nbsp</strong>" + questExplan,
				"text/html", "utf-8", null);
		// handling for questions not answered

		if (selection.equals("")) {
			selected = " <p id='paragraph'> You did not answer the question</p>";
			correct = "<p id='paragraph'> The correct answer is	"
					+ "&nbsp;&nbsp;&nbsp" + corr + "</p>";
		} else {
			selected = "<p id='paragraph'> You selected	" + "&nbsp;&nbsp;&nbsp"
					+ selection + "</p>";
			correct = "<p id='paragraph'> The correct answer is	"
					+ "&nbsp;&nbsp;&nbsp" + corr + "</p>";
		}

	}

	public void nextRecord() {

		number++;
		try {

			if (number >= Exam.quest.size()) {
				/*
				 * if user has reached the end of results, disable forward
				 * button next.setEnabled(false); prev.setEnabled(true); // dec
				 * by one to counter last inc
				 */number--;
			} else {
				prev.setEnabled(true);
				Exam.ex = (ExamSession) Exam.quest.get(number);
				int questNo = number + 1;
				String corr = "<link rel=\"stylesheet\" type=\"text/css\" href=\"msingipack.css\" />"
						+ Exam.ex.getAnswer();

				String questExplan = "<link rel=\"stylesheet\" type=\"text/css\" href=\"msingipack.css\" />"
						+ Exam.ex.getExplanation();

				String quiz = "<link rel=\"stylesheet\" type=\"text/css\" href=\"msingipack.css\" />"
						+ Exam.ex.getQuestion();

				String choiA = "<link rel=\"stylesheet\" type=\"text/css\" href=\"msingipack.css\" />"
						+ Exam.ex.getChoice1();

				String choiB = "<link rel=\"stylesheet\" type=\"text/css\" href=\"msingipack.css\" />"
						+ Exam.ex.getChoice2();

				String choiC = "<link rel=\"stylesheet\" type=\"text/css\" href=\"msingipack.css\" />"
						+ Exam.ex.getChoice3();

				String choiD = "<link rel=\"stylesheet\" type=\"text/css\" href=\"msingipack.css\" />"
						+ Exam.ex.getChoice4();
				ansSelected = Exam.ex.getSelectedAnswer();

				// displaying questions to the user
				questionNo.setText("	Question	" + questNo + "	out of "
						+ Exam.quest.size());

				qusetRev.loadDataWithBaseURL("file:///android_asset/",
						" </p>  <p id=\"question\">" + quiz + "</p> "
								+ "<p></p>"
								+ "<strong>&nbsp;A&nbsp&nbsp</strong>" + choiA
								+ "<p></p>"
								+ "<strong>&nbsp;B&nbsp&nbsp</strong>" + choiB
								+ "<p></p>"
								+ "<strong>&nbsp;C&nbsp&nbsp</strong>" + choiC
								+ "<p></p>"
								+ "<strong>&nbsp;D&nbsp&nbsp</strong>" + choiD
								+ "<p></p>"
								+ "<strong>&nbsp;&nbsp&nbsp</strong>"
								+ selected + "<p></p>"
								+ "<strong>&nbsp;&nbsp&nbsp</strong>" + correct
								+ "<p></p>"
								+ "<strong>&nbsp;&nbsp&nbsp</strong>"
								+ questExplan, "text/html", "utf-8", null);

				// handling for questions not answered

				if (ansSelected.equals("")) {
					selected = "<pid='paragraph' >You did not answer the question</p>";
					correct = "<p id='paragraph'> The correct answer is	"
							+ "&nbsp;&nbsp;&nbsp" + corr;
				} else {
					selected = "<p id='paragraph'> You selected	"
							+ "&nbsp;&nbsp;&nbsp" + Exam.ex.getSelectedAnswer();
					correct = " <p id='paragraph'> The correct answer is	"
							+ "&nbsp;&nbsp;&nbsp" + corr;
				}

				if (number == Exam.quest.size() - 1) {
					/*
					 * if user has reached the end of results, disable forward
					 * button
					 */
					next.setEnabled(false);
					prev.setEnabled(true);
					// dec by one to counter last inc
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// request focus at the top of the scrolview
		sv.fullScroll(ScrollView.FOCUS_UP);
	}

	public void previousRecord() {
		number--;
		try {
			if (number < 0) {
				/*
				 * if user has reached the begining of results, disable back
				 * button
				 */
				next.setEnabled(true);
				prev.setEnabled(false);
				// inc by one to counter last dec
				number++;
			} else {
				next.setEnabled(true);
				Exam.ex = (ExamSession) Exam.quest.get(number);
				int questNo = number + 1;
				String corr = "<link rel=\"stylesheet\" type=\"text/css\" href=\"msingipack.css\" />"
						+ Exam.ex.getAnswer();

				String questExplan = "<link rel=\"stylesheet\" type=\"text/css\" href=\"msingipack.css\" />"
						+ Exam.ex.getExplanation();

				String quiz = "<link rel=\"stylesheet\" type=\"text/css\" href=\"msingipack.css\" />"
						+ Exam.ex.getQuestion();

				String choiA = "<link rel=\"stylesheet\" type=\"text/css\" href=\"msingipack.css\" />"
						+ Exam.ex.getChoice1();

				String choiB = "<link rel=\"stylesheet\" type=\"text/css\" href=\"msingipack.css\" />"
						+ Exam.ex.getChoice2();

				String choiC = "<link rel=\"stylesheet\" type=\"text/css\" href=\"msingipack.css\" />"
						+ Exam.ex.getChoice3();

				String choiD = "<link rel=\"stylesheet\" type=\"text/css\" href=\"msingipack.css\" />"
						+ Exam.ex.getChoice4();

				ansSelected = Exam.ex.getSelectedAnswer();
				// handling for questions not answered

				if (ansSelected.equals("")) {
					selected = " <p id='paragraph'> You did not answer the question";
					correct = " <p id='paragraph'> The correct answer is	"
							+ "&nbsp;&nbsp;&nbsp" + corr;
				} else {
					selected = " <p id='paragraph'> You selected	"
							+ "&nbsp;&nbsp;&nbsp" + Exam.ex.getSelectedAnswer();
					correct = " <p id='paragraph'> The correct answer is	"
							+ "&nbsp;&nbsp;&nbsp" + corr;
				}

				// displaying questions to the user
				questionNo.setText("	Question	" + questNo + "	out of "
						+ Exam.quest.size());

				qusetRev.loadDataWithBaseURL("file:///android_asset/",
						" </p>  <p id=\"question\">" + quiz + "</p> "
								+ "<p></p>"
								+ "<strong>&nbsp;A&nbsp&nbsp</strong>" + choiA
								+ "<p></p>"
								+ "<strong>&nbsp;B&nbsp&nbsp</strong>" + choiB
								+ "<p></p>"
								+ "<strong>&nbsp;C&nbsp&nbsp</strong>" + choiC
								+ "<p></p>"
								+ "<strong>&nbsp;D&nbsp&nbsp</strong>" + choiD
								+ "<p></p>"
								+ "<strong>&nbsp;&nbsp&nbsp</strong>"
								+ selected + "<p></p>"
								+ "<strong>&nbsp;&nbsp&nbsp</strong>" + correct
								+ "<p></p>"
								+ "<strong>&nbsp;&nbsp&nbsp</strong>"
								+ questExplan, "text/html", "utf-8", null);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// request focus at the top of the scrolview
		sv.fullScroll(ScrollView.FOCUS_UP);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.Button_Quit:
			try {

				// check if the user has gone through everyexam revision
				if (number == Exam.quest.size() - 1) {
					Revision.this.finish();
					Intent mainmenu = new Intent(Revision.this, MainMenu.class);
					startActivity(mainmenu);
				} else if (number < Exam.quest.size() - 1) {

					final Dialog dialog = new Dialog(context);
					dialog.setContentView(R.layout.revisionfinish_dialog);
					dialog.setTitle("Revision Session");

					Button dialogButtonOk = (Button) dialog
							.findViewById(R.id.dialogButtonOK);
					// if button is clicked, close the custom dialog
					dialogButtonOk.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							dialog.dismiss();
							Revision.this.finish();
							// clear the collection
							Exam.quest.clear();
							Intent finish = new Intent(Revision.this,
									MainMenu.class);
							startActivity(finish);

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

				}
			} catch (Exception e) {
				Toast.makeText(context, "error", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.Button_Next:
			nextRecord();
			break;
		case R.id.Button_Prev:
			previousRecord();
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
