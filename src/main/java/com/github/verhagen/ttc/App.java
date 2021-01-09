package com.github.verhagen.ttc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * App time-sheet <year> 
 * App logbook <year.month> 
 *
 */
public class App {
	private static Logger logger = LoggerFactory.getLogger(App.class);
	private Path targetPath;


	public App(Path targetPath) {
		this.targetPath = targetPath;
	}

	public static void main(String[] args) {
		for (int index = 0; index < args.length; index++) {
			logger.info("[" + index + "]: " + args[index]);
		}

		App app = new App(Paths.get("."));
		app.execute(args);
	}

	public void execute(String[] args) {
		String textType = args[0];
		if ("time-sheet".equalsIgnoreCase(textType)) {
			logger.info(createTimeSheet(args[1]));
			writeAsFile(new TimeSheetYear().creatFileName(args[1]), createTimeSheet(args[1]));
		}
		else if ("time-sheet-month".equalsIgnoreCase(textType)) {
			logger.info(createTimeSheetYearMonth(args[1]));
			writeAsFile(new TimeSheetYearMonth().creatFileName(args[1]), createTimeSheetYearMonth(args[1]));
		}
		else  if ("logbook".equalsIgnoreCase(textType)) {
			logger.info(createLogbook(args[1]));
			writeAsFile(new Logbook().creatFileName(args[1]), createLogbook(args[1]));
		}
		else {
			logger.warn("Unknown text type '" + textType + "'");
		}
	}

	public String createTimeSheet(String yearStr) {
		TimeSheetYear timeSheet = new TimeSheetYear();
		return timeSheet.create(yearStr);
	}

	public String createTimeSheetYearMonth(String yearStr) {
		TimeSheetYearMonth timeSheet = new TimeSheetYearMonth();
		return timeSheet.create(yearStr);
	}

	public String createLogbook(String yearMonthStr) {
		Logbook logbook = new Logbook();
		return logbook.create(yearMonthStr);
	}


	private void writeAsFile(String fileName, String text) {
		File file = targetPath.resolve(fileName).toFile();
		if (file.exists()) {
			logger.error("File '" + file + "' already exists! Keeping existing file.");
			System.exit(1);
		}
		try (FileWriter writer = new FileWriter(file)) {
			writer.append(text);
		}
		catch (IOException ioe) {
			logger.warn("Unable to create file '" + file + "'", ioe);
		}
	}

}
