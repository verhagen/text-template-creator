package com.github.verhagen.ttc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeSheetYearMonthTest {
	private Logger logger = LoggerFactory.getLogger(TimeSheetYearMonthTest.class);


	@Test
	void create202001() throws Exception {
		TimeSheetYearMonth timesheet = new TimeSheetYearMonth();
		String text = timesheet.create("2020.01");

		logger.info(text);
		assertTrue(text.contains("# Time Sheet 2020 January"));
		assertTrue(text.contains("2020; 05; 2020.01.31; Fri;"));
		assertTrue(text.contains("2020; 01; 2020.01.01; Wed; ; (New Year's Day)"));
	}

	@Test
	void create202002() throws Exception {
		TimeSheetYearMonth timesheet = new TimeSheetYearMonth();
		String text = timesheet.create("2020.02");

		logger.info(text);
		assertTrue(text.contains("# Time Sheet 2020 February"));
		assertTrue(text.contains("2020; 09; 2020.02.28; Fri;"));
		assertTrue(text.contains("2020; 06; 2020.02.03; Mon;"));
		assertTrue(text.contains("year; week; date; day; hours; activity"));
//		assertTrue(text.contains("year; 09; Sat; 2020.02.29;"));
//		assertTrue(text.contains("year; 05; Sat; 2020.02.01;"));
	}

}
