package com.halfegg.jtext.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Logger {

    public final int EXIT_SUCCESS = 0;
    public final int EXIT_FAILURE = 1;

    private final Path LOGS_DIRECTORY_PATH = Paths.get("logs");

    public void createFiles() {
        try {
            if (Files.notExists(LOGS_DIRECTORY_PATH)) Files.createDirectory(LOGS_DIRECTORY_PATH);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
