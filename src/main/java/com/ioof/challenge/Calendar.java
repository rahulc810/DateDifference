package com.ioof.challenge;

public interface Calendar {
	public static boolean isLeapYear(int year) {
		return year % 4 == 0 || (year % 100 == 0 && year % 400 != 0);
	}

	public static int numberOfDays(int year) {
		return isLeapYear(year) ? 366 : 365;
	}

	public static int numberOfDaysInYearRange(int startYear, int endYear) {
		int totalYears = endYear - startYear + 1;

		int leapYears = 0;

		switch (totalYears % 4) {
		case 1:
			leapYears = isLeapYear(startYear) ? 1 : 0;
		case 2:
			leapYears = isLeapYear(startYear) || isLeapYear(startYear + 1) ? 1 : 0;
		case 3:
			leapYears = isLeapYear(startYear) || isLeapYear(startYear + 1) || isLeapYear(startYear + 2) ? 1 : 0;
			break;
		default:
			break;
		}
		leapYears += totalYears / 4;
		return totalYears * 365 + leapYears;
	}

	public static int daysPassedInYear(Date date) {
		return date.getMonth().getDaysUntilMonth() + date.getDay();
	}

	public static int daysLeftInYear(Date date) {
		return numberOfDays(date.getYear()) - daysPassedInYear(date);
	}

	public static int daysBetween(Date dateA, Date dateB) {
		int datesInOrder = dateA.compareTo(dateB);
		if (datesInOrder == 0) {
			return 0;
		}

		Date before = dateA;
		Date after = dateB;

		if (datesInOrder > 0) {
			before = dateB;
			after = dateA;
		}

		return Calendar.numberOfDaysInYearRange(before.year + 1, after.year - 1)
				+ Calendar.daysLeftInYear(before)
				+ Calendar.daysPassedInYear(after);
	}

	public enum Months implements Comparable<Months> {
		JAN(31, 0), FEB(28, 31), MAR(31, 59), APR(30, 90), MAY(31, 120), JUN(30, 151), JUL(31, 181), AUG(31, 212),
		SEP(30, 243), OCT(31, 273), NOV(30, 304), DEC(31, 334);

		private int daysInMonth;
		private int daysUntilMonth;

		private Months(int daysInMonth, int daysPassed) {
			this.daysInMonth = daysInMonth;
			this.daysUntilMonth = daysPassed;
		}

		public int getDaysUntilMonth() {
			return this.daysUntilMonth;
		}

		public int getDaysInMonth() {
			return daysInMonth;
		}

		public boolean isValidDay(int day) {
			return day > 0 && day <= this.getDaysInMonth();
		}

		static Months getMonth(int month) {
			if (month < 1 || month > 12) {
				throw new IllegalArgumentException("Invalid month");
			}

			return Months.values()[month - 1];
		}
	}
}
