package com.msingiapp;

/*

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
			if (Subjects.subject.equals("Kiswahili")) {
				remarks = "Vyema";
			} else if (!Subjects.subject.equals("Kiswahili")) {
				remarks = " Exacellent";
			}
		} else if (percent <= 79 && percent >= 75) {

			grade = ("A-");
			if (Subjects.subject.equals("Kiswahili")) {
				remarks = "Vizuri sana";
			} else if (!Subjects.subject.equals("Kiswahili")) {
				remarks = "Very Good";
			}
		}
		if (percent <= 74 && percent >= 70) {

			grade = ("B+");
			if (Subjects.subject.equals("Kiswahili")) {
				remarks = "Vizuri";
			} else if (!Subjects.subject.equals("Kiswahili")) {
				remarks = " Good";
			}

		} else if (percent <= 69 && percent >= 65) {

			grade = ("B");
			if (Subjects.subject.equals("Kiswahili")) {
				remarks = "Vizuri";
			} else if (!Subjects.subject.equals("Kiswahili")) {
				remarks = " Good";
			}
		}
		if (percent <= 64 && percent >= 60) {

			grade = ("B-");
			if (Subjects.subject.equals("Kiswahili")) {
				remarks = "Vizuri";
			} else if (!Subjects.subject.equals("Kiswahili")) {
				remarks = "Good";
			}

		} else if (percent <= 59 && percent >= 55) {

			grade = ("C+");
			if (Subjects.subject.equals("Kiswahili")) {
				remarks = "Kuridhisha";
			} else if (!Subjects.subject.equals("Kiswahili")) {
				remarks = "Satisfactory";
			}
		}
		if (percent <= 54 && percent >= 50) {

			grade = ("C");
			if (Subjects.subject.equals("Kiswahili")) {
				remarks = "Wastani";
			} else if (!Subjects.subject.equals("Kiswahili")) {
				remarks = "Average";
			}
		} else if (percent <= 49 && percent >= 45) {

			grade = ("C-");
			if (Subjects.subject.equals("Kiswahili")) {
				remarks = "Unahitaji kuboresha";
			} else if (!Subjects.subject.equals("Kiswahili")) {
				remarks = "Needs improvement";
			}
		}
		if (percent <= 44 && percent >= 40) {

			grade = ("D+");
			if (Subjects.subject.equals("Kiswahili")) {
				remarks = "Unahitaji kuboresha";
			} else if (!Subjects.subject.equals("Kiswahili")) {
				remarks = "Needs improvement";
			}

		} else if (percent <= 39 && percent >= 35) {

			grade = ("D");
			if (Subjects.subject.equals("Kiswahili")) {
				remarks = "Kutoridhisha";
			} else if (!Subjects.subject.equals("Kiswahili")) {
				remarks = "Unsatisfactory";
			}
		}
		if (percent <= 34 && percent >= 30) {

			grade = ("D-");
			if (Subjects.subject.equals("Kiswahili")) {
				remarks = "Kutoridhisha";
			}
			if (!Subjects.subject.equals("Kiswahili")) {
				remarks = "Unsatisfactory";
			}
		} else if (percent <= 29 && percent >= 0) {

			grade = ("E");
			if (Subjects.subject.equals("Kiswahili")) {
				remarks = "Kutoridhisha";
			}
			if (!Subjects.subject.equals("Kiswahili")) {
				remarks = "Unsatisfactory";
			}
		}

	}
}
