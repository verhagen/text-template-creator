package com.github.verhagen.ttc;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TimeSheetYearMonth {
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
	private CalendarEntries calendar = new CalendarEntries();


	public String create(String yearMonthStr) {
		if (! Pattern.matches("\\d{4}\\.\\d{2}", yearMonthStr)) {
			throw new IllegalArgumentException("Argument 'date' with value '" + yearMonthStr + "' should have pattern 'yyyy.MM'");
		}
		return create(LocalDate.parse(yearMonthStr + ".01", formatter));
	}


	private String create(LocalDate date) {
		DateTimeFormatter formatDaySection = DateTimeFormatter.ofPattern("ww; yyyy.MM.dd; EEE");
		DateTimeFormatter formatTitle = DateTimeFormatter.ofPattern("yyyy MMMM");
		List<String> timeSheet = new ArrayList<>();
		int month = date.getMonthValue();
		int year = date.getYear();
		LocalDate day = null;
		for (int daysToAdd = 0; daysToAdd <= 30; daysToAdd++) {
			day = date.plusDays(daysToAdd);
			if (day.getMonthValue() != month) {
				continue;
			}
			if (day.getDayOfWeek() == DayOfWeek.SATURDAY || day.getDayOfWeek() == DayOfWeek.SUNDAY) {
				continue;
			}
			if (day.getDayOfWeek() == DayOfWeek.MONDAY) {
				timeSheet.add("");
			}
			timeSheet.add("                         ; ;");
			timeSheet.add(year + "; " + day.format(formatDaySection) + "; ;" + calendar.extraInfo(month, day.getDayOfMonth()));
			if (day.getDayOfWeek() == DayOfWeek.FRIDAY) {
				timeSheet.add("year; week; date; day; hours; activity");
			}
		}
		timeSheet.add("");
		timeSheet.add("# Time Sheet " + date.format(formatTitle));
		
		StringBuilder bldr = new StringBuilder();
		for (int index = timeSheet.size() - 1; index >= 0; index--) {
			if (bldr.length() > 0) {
				bldr.append("\n");
			}
			bldr.append(timeSheet.get(index));
		}
		return bldr.toString();
	}


	public String creatFileName(String yearMonthStr) {
		if (! Pattern.matches("\\d{4}\\.\\d{2}", yearMonthStr)) {
			throw new IllegalArgumentException("Argument 'date' with value '" + yearMonthStr + "' should have pattern 'yyyy.MM'");
		}
		return yearMonthStr + "-time-sheet.csv";
	}

}
