package com.ioof.challenge;

import java.util.Scanner;

public class DateDifferentiator {

	public static void main(String[] args) {

		for (String s : args) {
			System.out.println(s);
		}

		System.out.println("Welcome to the date diiference calculator. Type \\h for help, \\q to quit.");
		
		try (Scanner scanner = new Scanner(System.in)) {
			String input = null;
			while (!(input = scanner.nextLine()).equals("\\q")) {
				switch (input) {
				case "\\h":
					System.out.println("The input dates are expected in DD MM YYYY format and the output difference will be in days. For example:");
					System.out.println("Sample input:  22 10 2010, 31 1 2011");
					System.out.println("Sample output: 22 10 2010, 31 1 2011 832");
					System.out.println();
					break;

				default:
					evaluate(input);
					break;
				}				
			}
			System.exit(0);
		}
	}

	static void evaluate(String input){
		try {
			// DD MM YYYY, DD MM YYYY
			String[] dates = input.split(",");
			if (dates.length < 2 || dates.length > 2) {
				System.out.println("Please provide the dates in <DD MM YYYY, DD MM YYYY> format or type /h for help");
				return;
			}
			String[] firstDateString = dates[0].trim().split(" ");
			String[] secondDateString = dates[1].trim().split(" ");
			Date d1 = new Date(Integer.valueOf(firstDateString[0]), Integer.valueOf(firstDateString[1]),
					Integer.valueOf(firstDateString[2]));
			Date d2 = new Date(Integer.valueOf(secondDateString[0]), Integer.valueOf(secondDateString[1]),
					Integer.valueOf(secondDateString[2]));

			System.out.println(input.trim() + " " + Calendar.daysBetween(d1, d2));
		} catch (NumberFormatException e) {
			System.out.println("Please provide the dates in <DD MM YYYY, DD MM YYYY> format or type /h for help");
		}
	}

}

