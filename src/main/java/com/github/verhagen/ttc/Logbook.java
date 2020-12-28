package com.github.verhagen.ttc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Logbook {
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
	private CalendarEntries calendar = new CalendarEntries();


	/**
	 * Create a Markdown template for a month in a specific year.
	 * @param yearMonthStr As text like year dot month {@code 2020.12}
	 * @return A logbook text containing for every day an entry.
	 */
	public String create(String yearMonthStr) {
		if (! Pattern.matches("\\d{4}\\.\\d{2}", yearMonthStr)) {
			throw new IllegalArgumentException("Argument 'date' with value '" + yearMonthStr + "' should have pattern 'yyyy.MM'");
		}
		return create(LocalDate.parse(yearMonthStr + ".01", formatter));
	}

	/**
	 * Create a Markdown template for a month in a specific year.
	 * @param date Give a date value, and its month and year will be used. 
	 * @return A logbook text containing for every day an entry.
	 */
	public String create(LocalDate date) {
		DateTimeFormatter formatDaySection = DateTimeFormatter.ofPattern("yyyy.MM.dd EEEE");
		DateTimeFormatter formatLogbookTitle = DateTimeFormatter.ofPattern("yyyy MMMM");
		List<String> logbook = new ArrayList<>();
		int month = date.getMonthValue();
		int year = date.getYear();
		LocalDate day = null;
		for (int dayOfMonth = 1; dayOfMonth <= 31; dayOfMonth++) {
			day = LocalDate.of(year, month, dayOfMonth);
			logbook.add("## " + day.format(formatDaySection) + calendar.extraInfo(month, dayOfMonth));
		}
		logbook.add("# Logbook " + day.format(formatLogbookTitle));
		StringBuilder bldr = new StringBuilder();
		for (int index = logbook.size() - 1; index >= 0; index--) {
			if (bldr.length() > 0) {
				bldr.append("\n");
			}
			bldr.append(logbook.get(index)).append("\n");
			
		}
		return bldr.toString();
	}


	public String creatFileName(String date) {
		if (! Pattern.matches("\\d{4}\\.\\d{2}", date)) {
			throw new IllegalArgumentException("Argument 'date' with value '" + date + "' should have pattern 'yyyy.MM'");
		}
		return date + "-logbook.md";
	}

}
