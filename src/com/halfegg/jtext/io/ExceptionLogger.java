package com.halfegg.jtext.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ExceptionLogger {

    private static final Path LOG_DIRECTORY_PATH = Paths.get("log");
    private static final Path DBG_DIRECTORY_PATH = Paths.get("log", "dbg");
    private static final Path DBG_LOG_PATH = Paths.get("log", "dbg", "dev-dbg.log");

    private ExceptionLogger() {}

    public static void createFiles() {
        try {
            if (Files.notExists(LOG_DIRECTORY_PATH)) Files.createDirectory(LOG_DIRECTORY_PATH);
            if (Files.notExists(DBG_DIRECTORY_PATH)) Files.createDirectory(DBG_DIRECTORY_PATH);
            if (Files.notExists(DBG_LOG_PATH)) Files.createFile(DBG_LOG_PATH);
        } catch (IOException ex) {
            ExceptionLogger.log(ExceptionLogger.class.getName(), "createFiles()", ex);
            ex.printStackTrace();
        }
    }

    public static void log(String className, String methodName, Exception exception) {
        checkFiles();
        var content = "\n\n\n#jtext Exception Log\n#" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")) +
                "\nSEVERE: " + className + " " + methodName + "\n";
        FileIO.write(DBG_LOG_PATH.toFile(), content, true);
        FileIO.write(DBG_LOG_PATH.toFile(), exception);
    }

    private static void checkFiles() {
        if (Files.notExists(DBG_LOG_PATH)) createFiles();
        if (DBG_LOG_PATH.toFile().length() > 150_000L) {
            try {
                Files.delete(DBG_LOG_PATH);
                Files.createFile(DBG_LOG_PATH);
            } catch (IOException ex) {
                ExceptionLogger.log(ExceptionLogger.class.getName(), "checkFiles()", ex);
                ex.printStackTrace();
            }
        }
    }
}
