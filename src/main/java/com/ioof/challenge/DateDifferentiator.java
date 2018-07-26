package com.ioof.challenge;

import java.util.Scanner;

public class DateDifferentiator {

	public static void main(String[] args) {

		for (String s : args) {
			System.out.println(s);
		}

		try (Scanner scanner = new Scanner(System.in)) {
			String input = null;
			while (!(input = scanner.nextLine()).equals("")) {
				// DD MM YYYY, DD MM YYYY
				String[] dates = input.split(",");
				if (dates.length < 2 || dates.length > 2) {
					System.out.println("Please provide the dates in <DD MM YYYY, DD MM YYYY> format.");
					continue;
				}
				String[] firstDateString = dates[0].trim().split(" ");
				String[] secondDateString = dates[1].trim().split(" ");
				Date d1 = new Date(Integer.valueOf(firstDateString[0]), Integer.valueOf(firstDateString[1]),
						Integer.valueOf(firstDateString[2]));
				Date d2 = new Date(Integer.valueOf(secondDateString[0]), Integer.valueOf(secondDateString[1]),
						Integer.valueOf(secondDateString[2]));

				System.out.println(input.trim() + " " + Calendar.daysBetween(d1, d2));
			}
			System.exit(0);
		}
	}

}
