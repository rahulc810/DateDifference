package com.ioof.challenge;

import com.ioof.challenge.Calendar.Months;

public final class Date implements Comparable<Date>, Cloneable {

	final int day;
	final Months month;
	final int year;

	public Date(int day, Months month, int year) {
		super();
		if (!month.isValidDay(day)) {
			throw new IllegalArgumentException("Invalid date specified");
		}

		if (year < 0) {
			throw new IllegalArgumentException("Invalid date specified");
		}

		this.day = day;
		this.month = month;
		this.year = year;
	}

	public Date(int day, int month, int year) {
		this(day, Months.getMonth(month), year);
	}

	public Date(Date master) {
		this(master.day, master.month, master.year);
	}

	@Override
	public int compareTo(Date then) {
		if (this.year != then.year) {
			return this.year > then.year ? 1 : -1;
		}

		int monthComparison = this.month.compareTo(then.month);
		if (monthComparison != 0) {
			return monthComparison;
		}

		if (this.day != then.day) {
			return this.day > then.day ? 1 : -1;
		}

		return 0;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Date(this);
	}

	public boolean isBefore(Date then) {
		return this.compareTo(then) > 0 ? false : true;
	}

	public boolean isAfter(Date then) {
		return this.compareTo(then) > 0 ? true : false;
	}

	public int getDay() {
		return day;
	}

	public Months getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}
}
