package com.msingiapp;


import android.app.Activity;

public class MainActivity extends Activity {
	
	
    // Question XML Tag Names
    public static final String XML_TAG_QUESTION_BLOCK = "questions";
    public static final String XML_TAG_QUESTION = "question";
    public static final String XML_TAG_QUESTION_ATTRIBUTE_NUMBER = "number";
    public static final String XML_TAG_QUESTION_ATTRIBUTE_ANSWER = "answer";
    public static final String XML_TAG_QUESTION_ATTRIBUTE_TOPIC = "topic" ;
    public static final String XML_TAG_QUESTION_ATTRIBUTE_IMAGEURL = "questionUrl";
    public static final String XML_TAG_QSATP_ATTRIBUTE_IMAGEURL = "qsatpUrl";
    public static final int QUESTION_BATCH_SIZE = 50;
       
    
    public static final String DEBUG_TAG = "MsingiPACK Activity Log";
    
    public static final String SUBJECT = "KCPE Science 2009";
    public static int NUMBEROFQUESTIONS=QUESTION_BATCH_SIZE;
    public static int QUESTIONS_ANSWERED = 0;
    public static int UNANSWERED_QUESTIONS = 0;
    public static int CORRECT_ANSWERS = 0;
    public static int INCORRECT_ANSWERS = 0;
    public static int SCORE = 0;
    public static String GRADE = null;
    public static String REMARKS = null;
    
    
    static final int FINISH_DIALOG_ID = 1;
	static final int RESULT_DIALOG_ID = 2;	
	static final int QSATP_FINISH_DIALOG_ID = 3;
	
	
	
}