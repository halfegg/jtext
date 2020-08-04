package com.halfegg.jtext.io;

import java.io.*;

public class FileIO {

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
            ExceptionLogger.log(FileIO.class.getName(), "read(File)", ex);
            ex.printStackTrace();
            return null;
        }
    }

    public static void write(File file, String content) {
        try (var writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
            writer.write(content);
        } catch (NullPointerException | IOException ex) {
            ExceptionLogger.log(FileIO.class.getName(), "write(File, String)", ex);
            ex.printStackTrace();
        }
    }

    public static void write(File file, String content, boolean append) {
        try (var writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append)))) {
            writer.write(content);
        } catch (NullPointerException | IOException ex) {
            ExceptionLogger.log(FileIO.class.getName(), "write(File, String, boolean)", ex);
            ex.printStackTrace();
        }
    }

    public static void write(File file, Throwable throwable) {
        try {
            throwable.printStackTrace(new PrintStream(new FileOutputStream(file, true)));
        } catch (FileNotFoundException ex) {
            ExceptionLogger.log(FileIO.class.getName(), "write(File, Throwable)", ex);
            ex.printStackTrace();
        }
    }
}
