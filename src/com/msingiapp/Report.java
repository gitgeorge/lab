package com.msingiapp;

public class Report {
	public int _id;
	public String subject;
	public String percentage_score;
	public String grade;
	public String remarks;
	public String date;

	public Report() {

	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPercentage_score() {
		return percentage_score;
	}

	public void setPercentage_score(String percentage_score) {
		this.percentage_score = percentage_score;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
