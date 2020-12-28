package com.github.verhagen.ttc;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AppTest {
	private static final Path TARGET_PATH = Paths.get("target", "generated-text");
	private App app = new App(TARGET_PATH);


	@BeforeAll
	static void setUp() {
		if (! TARGET_PATH.toFile().exists()) {
			TARGET_PATH.toFile().mkdirs();
		}
		for (File file : TARGET_PATH.toFile().listFiles()) {
			file.delete();
		}
	}

	@Test
	void mainTimeSheet2020() {
		app.execute(new String [] { "time-sheet", "2020" });
	}

	@Test
	void mainTimeSheetMonth2020() {
		app.execute(new String [] { "time-sheet-month", "2020.01" });
	}

	@Test
	void mainLogbook202001() {
		app.execute(new String [] { "logbook", "2020.01" });
	}

}
