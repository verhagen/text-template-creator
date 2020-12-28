package com.github.verhagen.ttc;

import java.util.HashMap;
import java.util.Map;

public class CalendarEntries {
	private Map<String, String> entries = new HashMap<>();

	public CalendarEntries() {
		entries.put("01.01", "New Year's Day");

		entries.put("00.00", "Good Friday");
		entries.put("00.00", "Easter Sunday");
		entries.put("00.00", "Easter Monday");
		entries.put("00.00", "Ascension Day");
		entries.put("00.00", "With Sunday");
		entries.put("00.00", "With Monday");

		entries.put("04.27", "King's Birthday");
		entries.put("05.05", "Libeeration Day");

		entries.put("12.24", "Christmas Eve");
		entries.put("12.25", "Christmas Day");
		entries.put("12.26", "Second Day of Christmas");
		entries.put("12.31", "New Year's Eve");
	}

	public String extraInfo(int month, int dayOfMonth) {
		String key = (month < 10 ? "0" + month : month) + "." + (dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth);
		if (entries.containsKey(key)) {
			return " (" + entries.get(key)  + ")";
		}
		return "";
	}

}
