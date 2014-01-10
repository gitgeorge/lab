package com.msingiapp;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author George
 */
public class Grade {

	public static float percentage = 0, noOfQuizes = 0, score = 0;
	public static String grade = null, remarks = null;
	public static int percent;

	public static void Grading(float noOfQuizes, float score) {

		percentage = ((score / noOfQuizes) * 100);
		Math.round(percentage);
		// casting percentage to an int for the purpose of user display
		percent = (int) percentage;

		if (percent <= 100 && percent >= 80) {

			grade = ("A");
			remarks = "Excellent";

		} else if (percent <= 79 && percent >= 75) {

			grade = ("A-");
			remarks = "Very Good";
		}
		if (percent <= 74 && percent >= 70) {

			grade = ("B+");
			remarks = " Good";

		} else if (percent <= 69 && percent >= 65) {

			grade = ("B");
			remarks = " Good";
		}
		if (percent <= 64 && percent >= 60) {

			grade = ("B-");
			remarks = "Good";

		} else if (percent <= 59 && percent >= 55) {

			grade = ("C+");
			remarks = "Satisfactory";
		}
		if (percent <= 54 && percent >= 50) {

			grade = ("C");
			remarks = "Average";
		} else if (percent <= 49 && percent >= 45) {

			grade = ("C-");
			remarks = "Needs improvement";
		}
		if (percent <= 44 && percent >= 40) {

			grade = ("D+");
			remarks = "Needs improvement";

		} else if (percent <= 39 && percent >= 35) {

			grade = ("D");
			remarks = "Unsatisfactory";
		}
		if (percent <= 34 && percent >= 30) {

			grade = ("D-");
			remarks = "Unsatisfactory";
		} else if (percent <= 29 && percent >= 0) {

			grade = ("E");
			remarks = "Unsatisfactory";
		}

	}
}
