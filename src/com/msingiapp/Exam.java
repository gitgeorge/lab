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
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class Exam extends Activity implements OnClickListener {
	public DatabaseHelper db = new DatabaseHelper(this);
	public static List<ExamSession> quest;
	public static ExamSession ex;
	WebView webQuest, webChoiceA, webChoiceB, webChoiceC, webChoiceD;
	TextView questionNo;
	public RadioGroup rgroup;
	RadioButton radioA, radioB, radioC, radioD;
	Button next, prev, quit;
	int number = 0;
	int questNo = number + 1;
	String pickedAnswer = "";
	public static int totalAnswered = 0, incorectAnswers, totalUnaswered = 0;
	public static int totalCorrectAns = 0;
	public static String ans;
	final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exam2);
		View title = getWindow().findViewById(android.R.id.title);
		View titleBar = (View) title.getParent();
		titleBar.setBackgroundColor(getResources().getColor(
				R.color.dark_green_color));
		initialize();
		displayFirstQuestion();

	}

	@SuppressLint("NewApi")
	public void initialize() {
		questionNo = (TextView) findViewById(R.id.tvquestionNumber1);
		webQuest = (WebView) findViewById(R.id.quest_web_view1);
		webQuest.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		webChoiceA = (WebView) findViewById(R.id.webView1);
		webChoiceA.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		webChoiceB = (WebView) findViewById(R.id.webView2);
		webChoiceB.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		webChoiceC = (WebView) findViewById(R.id.webView3);
		webChoiceC.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		webChoiceD = (WebView) findViewById(R.id.webView4);
		webChoiceD.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		// declare radiogroup
		rgroup = (RadioGroup) findViewById(R.id.radioGroup);
		// declare radiobuttons
		radioA = (RadioButton) findViewById(R.id.radio_AA);
		radioB = (RadioButton) findViewById(R.id.radio_BB);
		radioC = (RadioButton) findViewById(R.id.radio_CC);
		radioD = (RadioButton) findViewById(R.id.radio_DD);
		// add action to the radiogroup on chhagelistner
		rgroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// checkedId is the RadioButton selected
				switch (rgroup.getCheckedRadioButtonId()) {
				case R.id.radio_AA:
					pickedAnswer = "A";

					break;

				case R.id.radio_BB:
					pickedAnswer = "B";

					break;
				case R.id.radio_CC:
					pickedAnswer = "C";

					break;
				case R.id.radio_DD:
					pickedAnswer = "D";

					break;
				}
			}
		});

		next = (Button) findViewById(R.id.Button_Next);
		next.setOnClickListener(this);
		prev = (Button) findViewById(R.id.Button_Prev);
		prev.setOnClickListener(this);
		quit = (Button) findViewById(R.id.Button_Quit);
		quit.setOnClickListener(this);

	}

	public void displayFirstQuestion() {
		// getting the elements at index 0 from database table
		quest = db.getAllQusetions();
		if (quest.isEmpty()) {
			Toast.makeText(getApplicationContext(),
					"Ther are no contents to display", Toast.LENGTH_SHORT)
					.show();
		} else {
			ex = (ExamSession) quest.get(0);
			String quiz = ex.getQuestion();
			String choiA = ex.getChoice1();
			String choiB = ex.getChoice2();
			String choiC = ex.getChoice3();
			String choiD = ex.getChoice4();

			questionNo.setText("	Question	" + questNo + "	out of 50");
			webQuest.loadData(quiz, "text/html", "utf-8");
			webChoiceA.loadData(choiA, "text/html", "utf-8");
			webChoiceB.loadData(choiB, "text/html", "utf-8");
			webChoiceC.loadData(choiC, "text/html", "utf-8");
			webChoiceD.loadData(choiD, "text/html", "utf-8");
		}
	}

	@SuppressLint("ShowToast")
	public void nextRecord() {
		number++;
		if (number == quest.size()) {
			/* if user has reached the end of results, disable forward button */
			// Toast.makeText(getApplicationContext(),"number"+
			// number+"size is "+quest.size(), Toast.LENGTH_SHORT).show();
			// next.setEnabled(false);
			// prev.setEnabled(true);
			// dec by one to counter last inc
			number--;
		} else {
			prev.setEnabled(true);
			ex = (ExamSession) quest.get(number);
			int questNo = number + 1;
			// displaying questions to the user
			ans = ex.getSelectedAnswer();
			questionNo.setText("	Question	" + questNo + "	out of 50");
			webQuest.loadData(ex.getQuestion(), "text/html", "utf-8");
			webChoiceA.loadData(ex.getChoice1(), "text/html", "utf-8");
			webChoiceB.loadData(ex.getChoice2(), "text/html", "utf-8");
			webChoiceC.loadData(ex.getChoice3(), "text/html", "utf-8");
			webChoiceD.loadData(ex.getChoice4(), "text/html", "utf-8");
			if (number == quest.size() - 1) {
				/*
				 * if user has reached the end of results, disable forward
				 * button
				 */
				next.setEnabled(false);
				prev.setEnabled(true);
				// dec by one to counter last inc
			}
			// set the selected answer -1 because we are setting when nxt btn
			// is clicked
			ex = (ExamSession) quest.get(number - 1);
			ex.setSelectedAnswer(pickedAnswer);
			// get the picked answer at that index
			clearRadioButtons();
			selectedAnswer();
		}

	}

	@SuppressLint("ShowToast")
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
			ex = (ExamSession) quest.get(number);
			int questNo = number + 1;
			// displaying search record in text fields
			ans = ex.getSelectedAnswer();

			questionNo.setText("	Question	" + questNo + "	out of 50");
			webQuest.loadData(ex.getQuestion(), "text/html", "utf-8");
			webChoiceA.loadData(ex.getChoice1(), "text/html", "utf-8");
			webChoiceB.loadData(ex.getChoice2(), "text/html", "utf-8");
			webChoiceC.loadData(ex.getChoice3(), "text/html", "utf-8");
			webChoiceD.loadData(ex.getChoice4(), "text/html", "utf-8");
			// set the selected answer -1 because we are setting when nxt btn
			// is clicked
			ex = (ExamSession) quest.get(number + 1);
			ex.setSelectedAnswer(pickedAnswer);
			// get the picked answer at that index
			clearRadioButtons();
			selectedAnswer();
		}

	}

	public void markExam() {

		if (!ex.selectedAnswer.equals("")) {

			for (int i = 0; i < quest.size(); i++) {
				ex = (ExamSession) quest.get(i);
				// comparing the supplied answer to the correct ans hence
				// incrementing
				if (ex.selectedAnswer.equals(ex.answer)) {
					totalCorrectAns++;
					// increment the value of totalUnAnswered if the question
					// remains unanswered
				}
				if (!ex.getSelectedAnswer().equals("")) {
					totalAnswered++;

					// else increment the value of incorrect answers
				}
				if (ex.getSelectedAnswer().equals("")) {

					totalUnaswered++;
					// else increment the value of incorrect answers
				}
				if (!ex.selectedAnswer.equals(ex.answer)
						|| ex.getSelectedAnswer().equals("")) {
					incorectAnswers++;
				}
			}
		} else if (ex.selectedAnswer.equals("")) {

		}
		if (totalAnswered == 0) {
			// reinitialize the total unanswered &incorect answers to number of
			// questions
			Grade.grade = "E";
			Grade.remarks = "Unsatisfactory";
			totalUnaswered = quest.size();
			incorectAnswers = quest.size();

			final Dialog dialog = new Dialog(context);
			dialog.setContentView(R.layout.examfinish_dialog);
			dialog.setTitle("Exam Session");

			Button dialogButtonOk = (Button) dialog
					.findViewById(R.id.dialogButtonOK);
			// if button is clicked, close the custom dialog
			dialogButtonOk.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					Intent finish = new Intent(Exam.this, Score.class);
					startActivity(finish);
					finish();
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

		} else {
			Grade.Grading(quest.size(), totalCorrectAns);
			Intent finish = new Intent(Exam.this, Score.class);
			startActivity(finish);
			finish();
		}
	}

	// get the last selection from exam choices
	public void lastSelectedAns() {
		ex = (ExamSession) quest.get(number);
		ex.setSelectedAnswer(pickedAnswer);
		// get the picked answer at that index
		ans = ex.getSelectedAnswer();
		clearRadioButtons();

	}

	public void clearRadioButtons() {
		webQuest.reload();
		webChoiceA.reload();
		webChoiceB.reload();
		webChoiceC.reload();
		webChoiceD.reload();
		rgroup.clearCheck();
		pickedAnswer = "";

	}

	// To check the previously selected answer, show it by selecting the
	// radio btn in question and the re-assing it if answer is changed

	public void selectedAnswer() {
		try {

			if (ans.equals("")) {
				pickedAnswer = "";
			}
			if (ans.equals("A")) {
				rgroup.check(R.id.radio_AA);
				pickedAnswer = "A";
			}
			if (ans.equals("B")) {
				rgroup.check(R.id.radio_BB);
				pickedAnswer = "B";
			}
			if (ans.equals("C")) {
				rgroup.check(R.id.radio_CC);
				pickedAnswer = "C";
			}
			if (ans.equals("D")) {
				rgroup.check(R.id.radio_DD);
				pickedAnswer = "D";
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onClick(View v) {
		// ex = (ExamSession) quest.get(number);
		switch (v.getId()) {
		case R.id.Button_Quit:
			// set picked answer at that index incase the user exits the app
			// without clicking on next
			ex = (ExamSession) quest.get(number);
			ex.setSelectedAnswer(pickedAnswer);
			lastSelectedAns();
			markExam();

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
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}
