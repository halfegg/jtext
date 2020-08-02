package com.halfegg.jtext.io;

import java.io.*;

public class FileIO {

    private static final com.halfegg.jtext.io.ExceptionLogger logger = new com.halfegg.jtext.io.ExceptionLogger();

    private FileIO() {}

    public static String read(File file) {
        try (var reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            var stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            return stringBuilder.toString();
        } catch (NullPointerException | IOException ex) {
            logger.log(FileIO.class.getName(), "read(File)", ex);
            return null;
        }
    }

    public static void write(File file, String content) {
        try (var writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
            writer.write(content);
        } catch (NullPointerException | IOException ex) {
            logger.log(FileIO.class.getName(), "write(File, String)", ex);
        }
    }
}
