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
	WebView qusetRev, choiceA, choiceB, choiceC, choiceD, selected, correct,
			explan;
	public String ansSelected;
	final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		View title = getWindow().findViewById(android.R.id.title);
		View titleBar = (View) title.getParent();
		titleBar.setBackgroundColor(getResources().getColor(
				R.color.dark_green_color));
		setContentView(R.layout.revision);
		initialize();
	}

	@SuppressLint("NewApi")
	public void initialize() {
		sv = (ScrollView) findViewById(R.id.ScrollView01);
		questionNo = (TextView) findViewById(R.id.tvrevisonNo);
		qusetRev = (WebView) findViewById(R.id.revison_quest_webview);
		// setting the layer prevents the webview from flickering when
		// dispalying the contents
		qusetRev.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		choiceA = (WebView) findViewById(R.id.answer_web_viewA);
		choiceA.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		choiceB = (WebView) findViewById(R.id.answer_web_viewB);
		choiceB.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		choiceC = (WebView) findViewById(R.id.answer_web_viewC);
		choiceC.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		choiceD = (WebView) findViewById(R.id.answer_web_viewD);
		choiceD.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		selected = (WebView) findViewById(R.id.webselectedAns);
		selected.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		correct = (WebView) findViewById(R.id.webcorrectAns);
		correct.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		explan = (WebView) findViewById(R.id.explan_web_view);
		explan.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
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
		String quiz = Exam.ex.getQuestion();
		String selection = Exam.ex.getSelectedAnswer();
		String corr = Exam.ex.getAnswer();
		String choiA = Exam.ex.getChoice1();
		String choiB = Exam.ex.getChoice2();
		String choiC = Exam.ex.getChoice3();
		String choiD = Exam.ex.getChoice4();
		String questExplan = Exam.ex.getExplanation();

		questionNo.setText("	Question	" + questNo + "	out of "
				+ Exam.quest.size());
		qusetRev.loadDataWithBaseURL("file:///android_asset/", quiz,
				"text/html", "utf-8", null);
		choiceA.loadDataWithBaseURL("file:///android_asset/", choiA,
				"text/html", "utf-8", null);
		choiceB.loadDataWithBaseURL("file:///android_asset/", choiB,
				"text/html", "utf-8", null);
		choiceC.loadDataWithBaseURL("file:///android_asset/", choiC,
				"text/html", "utf-8", null);
		choiceD.loadDataWithBaseURL("file:///android_asset/", choiD,
				"text/html", "utf-8", null);
		explan.loadDataWithBaseURL("file:///android_asset/", questExplan,
				"text/html", "utf-8", null);
		// handling for questions not answered
		String j = "You did not answer the question";
		if (selection.equals("")) {
			selected.loadData(j, "text/html", "utf-8");
			correct.loadData("The correct answer is	" + "&nbsp;&nbsp;&nbsp"
					+ corr, "text/html", "utf-8");
		} else {
			selected.loadData(
					"You selected	" + "&nbsp;&nbsp;&nbsp" + selection,
					"text/html", "utf-8");
			correct.loadData("The correct answer is	" + "&nbsp;&nbsp;&nbsp"
					+ corr, "text/html", "utf-8");
		}

	}

	public void nextRecord() {
		number++;
		if (number >= Exam.quest.size()) {
			/*
			 * if user has reached the end of results, disable forward button
			 * next.setEnabled(false); prev.setEnabled(true); // dec by one to
			 * counter last inc
			 */number--;
		} else {
			prev.setEnabled(true);
			Exam.ex = (ExamSession) Exam.quest.get(number);
			int questNo = number + 1;
			// displaying questions to the user
			questionNo.setText("	Question	" + questNo + "	out of "
					+ Exam.quest.size());
			qusetRev.loadDataWithBaseURL("file:///android_asset/",
					Exam.ex.getQuestion(), "text/html", "utf-8", null);
			choiceA.loadDataWithBaseURL("file:///android_asset/",
					Exam.ex.getChoice1(), "text/html", "utf-8", null);
			choiceB.loadDataWithBaseURL("file:///android_asset/",
					Exam.ex.getChoice2(), "text/html", "utf-8", null);
			choiceC.loadDataWithBaseURL("file:///android_asset/",
					Exam.ex.getChoice3(), "text/html", "utf-8", null);
			choiceD.loadDataWithBaseURL("file:///android_asset/",
					Exam.ex.getChoice4(), "text/html", "utf-8", null);
			explan.loadDataWithBaseURL("file:///android_asset/",
					Exam.ex.getExplanation(), "text/html", "utf-8", null);
			ansSelected = Exam.ex.getSelectedAnswer();
			if (number == Exam.quest.size() - 1) {
				/*
				 * if user has reached the end of results, disable forward
				 * button
				 */
				next.setEnabled(false);
				prev.setEnabled(true);
				// dec by one to counter last inc
			}
			// handling for questions not answered
			if (ansSelected.equals("")) {
				selected.loadData("You did not answer the question",
						"text/html", "utf-8");
				correct.loadData("The correct answer is	" + "&nbsp;&nbsp;&nbsp"
						+ Exam.ex.getAnswer(), "text/html", "utf-8");
			} else {
				selected.loadData("You selected	" + "&nbsp;&nbsp;&nbsp"
						+ Exam.ex.getSelectedAnswer(), "text/html", "utf-8");
				correct.loadData("The correct answer is	" + "&nbsp;&nbsp;&nbsp"
						+ Exam.ex.getAnswer(), "text/html", "utf-8");
			}
		}
		// request focus at the top of the scrolview
		sv.fullScroll(ScrollView.FOCUS_UP);
	}

	public void previousRecord() {
		number--;
		if (number < 0) {
			/* if user has reached the begining of results, disable back button */
			next.setEnabled(true);
			prev.setEnabled(false);
			// inc by one to counter last dec
			number++;
		} else {
			next.setEnabled(true);
			Exam.ex = (ExamSession) Exam.quest.get(number);
			int questNo = number + 1;
			// displaying search record in text fields
			questionNo.setText("	Question	" + questNo + "	out of "
					+ Exam.quest.size());
			qusetRev.loadDataWithBaseURL("file:///android_asset/",
					Exam.ex.getQuestion(), "text/html", "utf-8", null);
			choiceA.loadDataWithBaseURL("file:///android_asset/",
					Exam.ex.getChoice1(), "text/html", "utf-8", null);
			choiceB.loadDataWithBaseURL("file:///android_asset/",
					Exam.ex.getChoice2(), "text/html", "utf-8", null);
			choiceC.loadDataWithBaseURL("file:///android_asset/",
					Exam.ex.getChoice3(), "text/html", "utf-8", null);
			choiceD.loadDataWithBaseURL("file:///android_asset/",
					Exam.ex.getChoice4(), "text/html", "utf-8", null);
			explan.loadDataWithBaseURL("file:///android_asset/",
					Exam.ex.getExplanation(), "text/html", "utf-8", null);
			ansSelected = Exam.ex.getSelectedAnswer();
			// handling for questions not answered
			if (ansSelected.equals("")) {
				selected.loadData("You did not answer the question",
						"text/html", "utf-8");
				correct.loadData("The correct answer is	" + "&nbsp;&nbsp;&nbsp"
						+ Exam.ex.getAnswer(), "text/html", "utf-8");
			} else {
				selected.loadData("You selected	" + "&nbsp;&nbsp;&nbsp"
						+ Exam.ex.getSelectedAnswer(), "text/html", "utf-8");
				correct.loadData("The correct answer is	" + "&nbsp;&nbsp;&nbsp"
						+ Exam.ex.getAnswer(), "text/html", "utf-8");
			}
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
