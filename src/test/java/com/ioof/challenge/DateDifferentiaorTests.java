package com.ioof.challenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class DateDifferentiaorTests {

	@Test
	public void dateShouldNotBeCreated() {
		createDate(-1, 12, 1994);
		createDate(10, 13, 1994);
		createDate(31, 11, 1994);
		createDate(31, 10, -10);
		createDate(0, 0, 0);
	}

	@Test
	public void testDateComparison() {
		Date before = new Date(15, 11, 1999);
		Date after = new Date(4, 6, 2004);

		assertTrue(before.isBefore(after));
		assertTrue(after.isAfter(before));
		assertTrue(before.compareTo(before) == 0);
	}

	@Test
	public void testIsLeapYear() {
		assertTrue(Calendar.isLeapYear(2004));
		assertTrue(Calendar.isLeapYear(1900));
		assertFalse(Calendar.isLeapYear(2014));
	}

	@Test
	public void testNumberOfDaysInYearRange() {
		assertEquals(1827, Calendar.numberOfDaysInYearRange(2012, 2016));
		assertEquals(1827, Calendar.numberOfDaysInYearRange(2016, 2012));
		assertEquals(1095, Calendar.numberOfDaysInYearRange(2009, 2011));
		assertEquals(366, Calendar.numberOfDaysInYearRange(2012, 2012));
	}

	@Test
	public void testDaysBetween() {

		Date before = new Date(15, 6, 2012);
		Date after = new Date(4, 7, 2014);

		assertEquals(750, Calendar.daysBetween(before, after));
		assertEquals(750, Calendar.daysBetween(after, before));
		assertEquals(0, Calendar.daysBetween(before, before));

		assertEquals(2, Calendar.daysBetween(new Date(31, 12, 2012), new Date(1, 1, 2013)));
		assertEquals(4003, Calendar.daysBetween(new Date(8, 01, 1995), new Date(24, 12, 2005)));
		assertEquals(8616, Calendar.daysBetween(new Date(15, 4, 1969), new Date(12, 9, 1945)));

	}

	void createDate(int day, int month, int year) {
		try {
			new Date(day, month, year);
			fail(String.format("Date creation is expected to fail for : %d %d %d", day, month, year));
		} catch (IllegalArgumentException iae) {
		}
	}
}
