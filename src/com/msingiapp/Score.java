package com.msingiapp;

import com.msingiapp.report.DBAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Score extends Activity implements OnClickListener {
	TextView text, examSubject, score, grade, remarks, questions, questionsAns,
			questionsUnans, correct, incorrect, scorevalue, gradevalue,
			remarksvalue, questionsvalue, questionsAnsvalue,
			questionsUnansvalue, correctvalue, incorrectvalue, ansExplanation,
			reviewexaplantion, title;
	Button review;
	DBAdapter dbAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		View title = getWindow().findViewById(android.R.id.title);
		View titleBar = (View) title.getParent();
		titleBar.setBackgroundColor(getResources().getColor(
				R.color.dark_green_color));
		setContentView(R.layout.score);

		initialize();

	}

	@SuppressLint("DefaultLocale")
	private void initialize() {
		examSubject = (TextView) findViewById(R.id.TextViewExam);
		examSubject.setText(Year.subTitle.toUpperCase() + "		"
				+ Year.question_year);
		title = (TextView) findViewById(R.id.TextViewResults);
		score = (TextView) findViewById(R.id.TextView_PercentageScore);
		grade = (TextView) findViewById(R.id.TextView_Grade);
		remarks = (TextView) findViewById(R.id.TextView_Remarks);
		questions = (TextView) findViewById(R.id.TextView_Questions);
		questionsAns = (TextView) findViewById(R.id.TextView_QuestionsAnswered);
		questionsUnans = (TextView) findViewById(R.id.TextView_Unanswered_Questions);
		correct = (TextView) findViewById(R.id.TextView_CorrectAnswers);
		incorrect = (TextView) findViewById(R.id.TextView_IncorrectAnswers);
		ansExplanation = (TextView) findViewById(R.id.TextViewReviewTitle);
		reviewexaplantion = (TextView) findViewById(R.id.TextViewReview);
		review = (Button) findViewById(R.id.Button_Review);

		if (Year.subTitle.equals("Kiswahili")) {
			title.setText("Alama zako...");
			score.setText("Alama");
			grade.setText("Gredi");
			remarks.setText("Hotuba");
			questions.setText("Idadi ya maswali");
			questionsAns.setText("Maswali yaliyo jibiwa");
			questionsUnans.setText("Maswali ambayo hayakujibiwa");
			correct.setText("Majibu sahihi");
			incorrect.setText("Majibu yasiyo sahihi");
			ansExplanation.setText("Majibu na maelezo");
			reviewexaplantion
					.setText("Kagua uchaguzi wako na maelezo ili upate majibu sahihi");
			review.setText("ndiyo");
		}

		scorevalue = (TextView) findViewById(R.id.TextViewPercentageScore);
		scorevalue.setText("	" + Grade.percent + " % ");

		gradevalue = (TextView) findViewById(R.id.TextViewGrade);
		gradevalue.setText(Grade.grade);

		remarksvalue = (TextView) findViewById(R.id.TextViewRemarks);
		remarksvalue.setText(Grade.remarks);

		questionsvalue = (TextView) findViewById(R.id.TextViewQuestions);
		questionsvalue.setText(String.valueOf(Exam.quest.size()));

		questionsAnsvalue = (TextView) findViewById(R.id.TextViewQuestionsAnswered);
		questionsAnsvalue.setText(String.valueOf(Exam.totalAnswered));

		questionsUnansvalue = (TextView) findViewById(R.id.TextViewUnansweredQuestions);
		questionsUnansvalue.setText(String.valueOf(Exam.totalUnaswered));

		correctvalue = (TextView) findViewById(R.id.TextViewCorrectAnswers);
		int c = (int) (Exam.totalCorrectAns);// casted to remove decimal places
		correctvalue.setText(String.valueOf(c));

		incorrectvalue = (TextView) findViewById(R.id.TextViewIncorrectAnswers);
		incorrectvalue.setText(String.valueOf(Exam.incorectAnswers));

		review.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.Button_Review:
			Boolean isSDPresent = android.os.Environment
					.getExternalStorageState().equals(
							android.os.Environment.MEDIA_MOUNTED);
			if (isSDPresent) {
				try {
					writeToSdCard();
					Intent revision = new Intent(Score.this, Revision.class);
					startActivity(revision);
					finish();
					Exam.totalAnswered = 0;
					Exam.totalCorrectAns = 0;
					Exam.incorectAnswers = 0;
					Exam.totalUnaswered = 0;
					Grade.percent = 0;
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			finish();
			Exam.totalAnswered = 0;
			Exam.totalCorrectAns = 0;
			Exam.incorectAnswers = 0;
			Exam.totalUnaswered = 0;
			Grade.percent = 0;
			Intent revision = new Intent(Score.this, Revision.class);
			startActivity(revision);
			break;
		}

	}

	public void writeToSdCard() {
		try {
			dbAdapter = new DBAdapter();
			dbAdapter.open();
			boolean flag = dbAdapter.insertIntoTable();
			if (flag == true) {
				Toast.makeText(getApplicationContext(), "results updated",
						Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(this, "False  ", Toast.LENGTH_LONG).show();
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
			e.printStackTrace();
		}

	}
}
