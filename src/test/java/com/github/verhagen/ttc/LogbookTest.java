package com.github.verhagen.ttc;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbookTest {
	private Logger logger = LoggerFactory.getLogger(LogbookTest.class);
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

	@Test
	void createByStringJanuary() {
		Logbook logbook = new Logbook();
		String result = logbook.create("2020.01");
		logger.info(result);
		assertTrue(result.contains("## 2020.01.31 Friday"));
		assertTrue(result.contains("## 2020.01.01 Wednesday (New Year's Day)"));
	}

	@Test
	void createByStringDecember() {
		Logbook logbook = new Logbook();
		String result = logbook.create("2020.12");
		logger.info(result);
		assertTrue(result.contains("## 2020.12.31 Thursday (New Year's Eve)"));
		assertTrue(result.contains("## 2020.12.01 Tuesday"));
		assertTrue(result.contains("## 2020.12.24 Thursday (Christmas Eve)"));
		assertTrue(result.contains("## 2020.12.25 Friday (Christmas Day)"));
		assertTrue(result.contains("## 2020.12.26 Saturday (Second Day of Christmas)"));
	}

	@Test
	void createByDateFirst() {
		Logbook logbook = new Logbook();
		String result = logbook.create(LocalDate.parse("2020.12.01", formatter));
		logger.info(result);
		assertTrue(result.contains("## 2020.12.31 Thursday"));
		assertTrue(result.contains("## 2020.12.01 Tuesday"));
		assertTrue(result.contains("## 2020.12.24 Thursday (Christmas Eve)"));
		assertTrue(result.contains("## 2020.12.25 Friday (Christmas Day)"));
		assertTrue(result.contains("## 2020.12.26 Saturday (Second Day of Christmas)"));
	}


	@Test
	void createByDateLast() {
		Logbook logbook = new Logbook();
		String result = logbook.create(LocalDate.parse("2020.12.31", formatter));
		logger.info(result);
		assertFalse(result.contains("## 2021.01.01"));
		assertFalse(result.contains("## 2020.12.32"));
		assertTrue(result.contains("## 2020.12.31 Thursday"));
		assertTrue(result.contains("## 2020.12.01 Tuesday"));
		assertTrue(result.contains("## 2020.12.24 Thursday (Christmas Eve)"));
		assertTrue(result.contains("## 2020.12.25 Friday (Christmas Day)"));
		assertTrue(result.contains("## 2020.12.26 Saturday (Second Day of Christmas)"));
	}

	@Test
	void create2020February() {
		Logbook logbook = new Logbook();
		String result = logbook.create(LocalDate.parse("2020.02.01", formatter));
		logger.info(result);
		assertTrue(result.contains("## 2020.02.29 Saturday"));
		assertTrue(result.contains("## 2020.02.01 Saturday"));
		assertFalse(result.contains("## 2020.02.30"));
		assertFalse(result.contains("## 2020.03.01"));
	}

	@Test
	void create2019February() {
		Logbook logbook = new Logbook();
		String result = logbook.create(LocalDate.parse("2019.02.01", formatter));
		logger.info(result);
		assertTrue(result.contains("## 2019.02.28 Thursday"));
		assertTrue(result.contains("## 2019.02.01 Friday"));
		assertFalse(result.contains("## 2019.02.29"));
		assertFalse(result.contains("## 2019.02.30"));
		assertFalse(result.contains("## 2019.03.01"));
	}

}

