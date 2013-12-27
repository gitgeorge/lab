package com.msingiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Score extends Activity implements OnClickListener {
	TextView text, score, grade, remarks, questions, questionsAns,
			questionsUnans, correct, incorrect, scorevalue, gradevalue,
			remarksvalue, questionsvalue, questionsAnsvalue,
			questionsUnansvalue, correctvalue, incorrectvalue;
	Button review;
	

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

	private void initialize() {
		score = (TextView) findViewById(R.id.TextView_PercentageScore);
		scorevalue = (TextView) findViewById(R.id.TextViewPercentageScore);
		scorevalue.setText("	" + Grade.percent + " % ");

		grade = (TextView) findViewById(R.id.TextView_Grade);
		gradevalue = (TextView) findViewById(R.id.TextViewGrade);
		gradevalue.setText(Grade.grade);

		remarks = (TextView) findViewById(R.id.TextView_Remarks);
		remarksvalue = (TextView) findViewById(R.id.TextViewRemarks);
		remarksvalue.setText(Grade.remarks);

		questions = (TextView) findViewById(R.id.TextView_Questions);
		questionsvalue = (TextView) findViewById(R.id.TextViewQuestions);
		questionsvalue.setText(String.valueOf(Exam.quest.size()));

		questionsAns = (TextView) findViewById(R.id.TextView_QuestionsAnswered);
		questionsAnsvalue = (TextView) findViewById(R.id.TextViewQuestionsAnswered);
		questionsAnsvalue.setText(String.valueOf(Exam.totalAnswered));

		questionsUnans = (TextView) findViewById(R.id.TextView_Unanswered_Questions);
		questionsUnansvalue = (TextView) findViewById(R.id.TextViewUnansweredQuestions);
		questionsUnansvalue.setText(String.valueOf(Exam.totalUnaswered));

		correct = (TextView) findViewById(R.id.TextView_CorrectAnswers);
		correctvalue = (TextView) findViewById(R.id.TextViewCorrectAnswers);
		int c = (int) (Exam.totalCorrectAns);//casted to remove decimal places
		correctvalue.setText(String.valueOf(c));

		incorrect = (TextView) findViewById(R.id.TextView_IncorrectAnswers);
		incorrectvalue = (TextView) findViewById(R.id.TextViewIncorrectAnswers);
		incorrectvalue.setText(String.valueOf(Exam.incorectAnswers));

		review = (Button) findViewById(R.id.Button_Review);
		review.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.Button_Review:
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
}
