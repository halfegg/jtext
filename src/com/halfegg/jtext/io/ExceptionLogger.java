package com.halfegg.jtext.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ExceptionLogger {

    private final Path LOG_DIRECTORY_PATH = Paths.get("log");
    private final Path DBG_DIRECTORY_PATH = Paths.get("log", "dbg");
    private final Path DBG_LOG_PATH = Paths.get("log", "dbg", "dev-dbg.log");

    private Logger logger;
    private FileHandler fileHandler;

    public ExceptionLogger() {
        createFiles();
        initFileHandler();
        initLogger();
    }

    private void createFiles() {
        try {
            if (Files.notExists(LOG_DIRECTORY_PATH)) Files.createDirectory(LOG_DIRECTORY_PATH);
            if (Files.notExists(DBG_DIRECTORY_PATH)) Files.createDirectory(DBG_DIRECTORY_PATH);
            if (Files.notExists(DBG_LOG_PATH)) Files.createFile(DBG_LOG_PATH);
            else checkFileLength();
        } catch (IOException ex) {
            this.log(this.getClass().getName(), "createFiles()", ex);
        }
    }

    private void initFileHandler() {
        try {
            fileHandler = new FileHandler(DBG_LOG_PATH.toFile().getAbsolutePath(), true);
            fileHandler.setFormatter(new SimpleFormatter());
        } catch (IOException ex) {
            this.log(this.getClass().getName(), "initFileHandler()", ex);
        }
    }

    private void initLogger() {
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.setLevel(Level.SEVERE);
        logger.addHandler(fileHandler);
    }

    private void checkFileLength() {
        try {
            if (DBG_LOG_PATH.toFile().length() > 150_000) {
                Files.delete(DBG_LOG_PATH);
                Files.createFile(DBG_LOG_PATH);
            }
        } catch (IOException ex) {
            this.log(this.getClass().getName(), "checkFileLength()", ex);
        }
    }

    public void log(String className, String methodName, Throwable ex) {
        logger.log(Level.SEVERE, className + " " + methodName, ex);
    }
}
