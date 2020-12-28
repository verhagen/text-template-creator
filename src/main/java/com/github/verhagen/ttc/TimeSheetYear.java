package com.github.verhagen.ttc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;


public class TimeSheetYear {
	private WeekFields weekFields = WeekFields.ISO;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");


	public String create(String yearStr) {
		return create(Integer.parseInt(yearStr));
	}

	public String create(int year) {
		if (year < 1900 || year > 9999) {
			throw new IllegalArgumentException("Argument 'year' should be between 1900 and 10.000");
		}

		LocalDate date = LocalDate.of(year, 1, 1);
		date = getFirstMondayOfFirstWeekOfTheYear(date);
		List<String> timeSheet = new ArrayList<>();
		for (int dayToAdd = 0; dayToAdd < 366; dayToAdd = dayToAdd + 7)  {
			timeSheet.add(addWeek(date.plusDays(dayToAdd)));
		}
		StringBuilder bldr = new StringBuilder();
		for (int index = timeSheet.size() - 1; index >= 0; index--) {
			if (bldr.length() > 0) {
				bldr.append("\n");
			}
			bldr.append(timeSheet.get(index));
		}
		return "week   ; start - end            ; m; t; w; t; f;\n" + bldr.toString();
	}

	private LocalDate getFirstMondayOfFirstWeekOfTheYear(LocalDate date) {
		switch (date.getDayOfWeek()) {
			case MONDAY: return date;
			case TUESDAY: return date.minusDays(1);
			case WEDNESDAY: return date.minusDays(2);
			case THURSDAY: return date.minusDays(3);
			case FRIDAY: return date.minusDays(4);
			case SATURDAY: return date.minusDays(5);
			case SUNDAY: return date.minusDays(6);
		}
		return null;
	}

	private String addWeek(LocalDate date) {
		StringBuilder bldr = new StringBuilder();
		int weekOfYear = date.get(weekFields.weekOfWeekBasedYear());
		int year = date.getMonthValue() == 12 && weekOfYear  == 1 ? date.getYear() + 1 : date.getYear();
		bldr.append(year).append(".").append(weekOfYear < 10 ? "0" + weekOfYear : weekOfYear).append("; ");
		LocalDate lastDate = date.plusDays(6);
		bldr.append(date.format(formatter)).append(" - ").append(lastDate.format(formatter)).append("; ; ; ; ; ;");
		return bldr.toString();
	}


	public String creatFileName(String yearStr) {
		return creatFileName(Integer.parseInt(yearStr));
	}

	public String creatFileName(int year) {
		if (year < 1900 || year > 9999) {
			throw new IllegalArgumentException("Argument 'year' should be between 1900 and 10.000");
		}
		return year + "-time-sheet.csv";
	}

}
