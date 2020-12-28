package com.github.verhagen.ttc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeSheetYearTest {
	private Logger logger = LoggerFactory.getLogger(TimeSheetYearTest.class);

	@Test
	void createByString2020() {
		TimeSheetYear timeSheet = new TimeSheetYear();
		String text = timeSheet.create("2020");

		assertTrue(text.contains("2020.53; 2020.12.28 - 2021.01.03;"));
		assertTrue(text.contains("2020.01; 2019.12.30 - 2020.01.05;"));
	}

	@Test
	void createByInteger2020() {
		TimeSheetYear timeSheet = new TimeSheetYear();
		String text = timeSheet.create(2020);
		
		assertTrue(text.contains("2020.53; 2020.12.28 - 2021.01.03;"));
		assertTrue(text.contains("2020.01; 2019.12.30 - 2020.01.05;"));
	}

	@Test
	void createByInteger2019() {
		TimeSheetYear timeSheet = new TimeSheetYear();
		String text = timeSheet.create(2019);
		logger.info(text);

		assertTrue(text.contains("2019.52; 2019.12.23 - 2019.12.29;"));
		assertTrue(text.contains("2019.01; 2018.12.31 - 2019.01.06;"));
	}

	@Test
	void createByInteger2021() {
		TimeSheetYear timeSheet = new TimeSheetYear();
		String text = timeSheet.create(2021);
		logger.info(text);

		assertTrue(text.contains("2021.52; 2021.12.27 - 2022.01.02;"));
		assertTrue(text.contains("2020.53; 2020.12.28 - 2021.01.03;"));
	}

}
