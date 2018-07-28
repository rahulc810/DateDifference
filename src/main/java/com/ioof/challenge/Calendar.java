package com.ioof.challenge;

public interface Calendar {

	/**
	 * @param year
	 * @return whether the given year is a leap year
	 */
	public static boolean isLeapYear(int year) {
		if (year < 0) {
			throw new IllegalArgumentException("Invalid year :" + year);
		}

		if (year % 4 != 0) {
			return false;
		} else if (year % 400 == 0) {
			return true;
		} else if (year % 100 == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 
	 * @param year
	 * @return Number of days in an year, based on Leap-ness
	 */
	public static int numberOfDays(int year) {
		return isLeapYear(year) ? 366 : 365;
	}

	/**
	 * Number of days between the two years, INCLUCIVE of both years. For example
	 * for 2005-2006 output will be 730 days
	 *
	 * @param startYear
	 * @param endYear
	 * @return Number of days between the two years, INCLUCIVE of both years
	 */
	public static int numberOfDaysInYearRange(int startYear, int endYear) {
		if (endYear < startYear) {
			int swap = endYear;
			endYear = startYear;
			startYear = swap;
		}

		int diffInYears = endYear - startYear + 1;
		int leapYears = 0;

		switch (diffInYears % 4) {
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
		leapYears += diffInYears / 4;

		return diffInYears * 365 + leapYears;
	}

	/**
	 * 
	 * @param date
	 * @return Days passed since year started, including current day
	 */
	public static int daysPassedInYear(Date date) {
		return date.getMonth().getDaysUntilMonth() + date.getDay();
	}

	/**
	 * 
	 * @param date
	 * @return Days remaining for year end, excluding current day
	 */
	public static int daysLeftInYear(Date date) {
		return numberOfDays(date.getYear()) - daysPassedInYear(date);
	}

	/**
	 * 
	 * Takes in two dates and returns the difference between them in days. The dates
	 * can be in any order.
	 * 
	 * @param dateA
	 * @param dateB
	 * @return Returns the difference between the dates in days, excluding the date
	 *         days.
	 */
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

		int totalDays = 0;

		switch (after.year - before.year) {
		case 0:
			totalDays = Calendar.numberOfDays(after.year) - Calendar.daysPassedInYear(before)
					- Calendar.daysLeftInYear(after) - 1;
			break;
		case 1:
			totalDays = Calendar.daysLeftInYear(before) + Calendar.daysPassedInYear(after);
			break;
		default:
			//happy case
			totalDays = Calendar.numberOfDaysInYearRange(before.year + 1, after.year - 1) + Calendar.daysLeftInYear(before)
					+ Calendar.daysPassedInYear(after);
			break;
		}

		return totalDays;
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

		/**
		 * 
		 * @return Days passed in the current year since when this month started. 0 for
		 *         Jan, 31 for Feb and so on.
		 */
		public int getDaysUntilMonth() {
			return this.daysUntilMonth;
		}

		/**
		 * 
		 * @return Number of days in the month.
		 */
		public int getDaysInMonth() {
			return daysInMonth;
		}

		public boolean isValidDay(int day) {
			return day > 0 && day <= this.getDaysInMonth();
		}

		/**
		 * 
		 * @param month
		 * @return Enum representation of numerical (1-12) month.
		 */
		static Months getMonth(int month) {
			if (month < 1 || month > 12) {
				throw new IllegalArgumentException("Invalid month");
			}

			return Months.values()[month - 1];
		}
	}
}
