package com.halfegg.jtext.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {

    private final ExceptionLogger logger = new ExceptionLogger();

    private final Path CONFIG_DIRECTORY_PATH = Paths.get("config");
    private final Path PROPERTIES_DIRECTORY_PATH = Paths.get("config", "properties");
    private final Path FONT_CONFIG_PATH = Paths.get("config", "properties", "font.properties");
    private final Path THEME_CONFIG_PATH = Paths.get("config", "properties", "theme.properties");
    private final Path POSITION_CONFIG_PATH = Paths.get("config", "properties", "position.properties");
    private final Path WRAP_TEXT_CONFIG_PATH = Paths.get("config", "properties", "wrap-text.properties");
    private final Path SHOW_FILE_CONFIG_PATH = Paths.get("config", "properties", "show-file.properties");
    private final String CONFIG_MESSAGE = "\nIMPORTANT: DO NOT EDIT HERE.\n";

    public void createFiles() {
        try {
            if (Files.notExists(CONFIG_DIRECTORY_PATH)) Files.createDirectory(CONFIG_DIRECTORY_PATH);
            if (Files.notExists(PROPERTIES_DIRECTORY_PATH)) Files.createDirectory(PROPERTIES_DIRECTORY_PATH);
            if (Files.notExists(FONT_CONFIG_PATH)) {
                Files.createFile(FONT_CONFIG_PATH);
                setFontConfig("System", "Regular", "14");
            }
            if (Files.notExists(THEME_CONFIG_PATH)) {
                Files.createFile(THEME_CONFIG_PATH);
                //
            }
            if (Files.notExists(POSITION_CONFIG_PATH)) {
                Files.createFile(POSITION_CONFIG_PATH);
                setPositionSizeConfig(589.0,  182.0, 680.0, 520.0);
            }
            if (Files.notExists(WRAP_TEXT_CONFIG_PATH)) {
                Files.createFile(WRAP_TEXT_CONFIG_PATH);
                setWrapTextConfig(true);
            }
            if (Files.notExists(SHOW_FILE_CONFIG_PATH)) {
                Files.createFile(SHOW_FILE_CONFIG_PATH);
                setShowFileOnStartConfig(false, "");
            }
        } catch (IOException ex) {
            logger.log(this.getClass().getName(), "createFiles()", ex);
        }
    }

    public void setFontConfig(String fontFamily, String fontStyle, String fontSize){
        var properties = new Properties();
        properties.setProperty("fontFamily", fontFamily);
        properties.setProperty("fontStyle", fontStyle);
        properties.setProperty("fontSize", String.valueOf(fontSize));
        try {
            properties.store(Files.newOutputStream(FONT_CONFIG_PATH), CONFIG_MESSAGE);
        } catch (IOException ex) {
            logger.log(this.getClass().getName(), "setFontConfig(String, String, String)", ex);
        }
    }

    public String[] getFontConfig(){
        var configs = new String[4];
        var properties = new Properties();
        try {
            properties.load(Files.newInputStream(FONT_CONFIG_PATH));
        } catch (IOException ex) {
            logger.log(this.getClass().getName(), "getFontConfig()", ex);
        }
        configs[0] = properties.getProperty("fontFamily");
        configs[1] = properties.getProperty("fontStyle");
        configs[2] = properties.getProperty("fontSize");
        return configs;
    }

    public void setPositionSizeConfig(double stageX, double stageY, double stageWidth, double stageHeight) {
        var properties = new Properties();
        properties.setProperty("x", String.valueOf(stageX));
        properties.setProperty("y",String.valueOf(stageY) );
        properties.setProperty("stageWidth", String.valueOf(stageWidth));
        properties.setProperty("stageHeight", String.valueOf(stageHeight));
        try {
            properties.store(Files.newOutputStream(POSITION_CONFIG_PATH), CONFIG_MESSAGE);
        } catch (IOException ex) {
            logger.log(this.getClass().getName(), "setPositionSizeConfig(double, double, double, double)", ex);
        }
    }

    public double[] getPositionSizeConfig() {
        var configs = new double[4];
        var properties = new Properties();
        try {
            properties.load(Files.newInputStream(POSITION_CONFIG_PATH));
        } catch (IOException ex) {
            logger.log(this.getClass().getName(), "getPositionSizeConfig()", ex);
        }
        configs[0] = Double.parseDouble(properties.getProperty("x"));
        configs[1] = Double.parseDouble(properties.getProperty("y"));
        configs[2] = Double.parseDouble(properties.getProperty("stageWidth"));
        configs[3] = Double.parseDouble(properties.getProperty("stageHeight"));
        return configs;
    }

    public void setWrapTextConfig(boolean isWrapText) {
        var properties = new Properties();
        properties.setProperty("isWrapText", String.valueOf(isWrapText));
        try {
            properties.store(Files.newOutputStream(WRAP_TEXT_CONFIG_PATH), CONFIG_MESSAGE);
        } catch (IOException ex) {
            logger.log(this.getClass().getName(), "setWrapTextConfig(boolean)", ex);
        }
    }

    public String getWrapTextConfig() {
        var properties = new Properties();
        try {
            properties.load(Files.newInputStream(WRAP_TEXT_CONFIG_PATH));
        } catch (IOException ex) {
            logger.log(this.getClass().getName(), "getWrapTextConfig()", ex);
        }
        return properties.getProperty("isWrapText");
    }

    public void setShowFileOnStartConfig(boolean showFile, String filePath) {
        var properties = new Properties();
        properties.setProperty("showFileOnStart", String.valueOf(showFile));
        properties.setProperty("file", filePath);
        try {
            properties.store(Files.newOutputStream(SHOW_FILE_CONFIG_PATH), CONFIG_MESSAGE);
        } catch (IOException ex) {
            logger.log(this.getClass().getName(), "setShowFileOnStartConfig(boolean, String)", ex);
        }
    }

    public String[] getShowFileOnStartConfig() {
        var configs = new String[2];
        var properties = new Properties();
        try {
            properties.load(Files.newInputStream(SHOW_FILE_CONFIG_PATH));
        } catch (IOException ex) {
            logger.log(this.getClass().getName(), "getShowFileOnStartConfig()", ex);
        }
        configs[0] = properties.getProperty("showFileOnStart");
        configs[1] = properties.getProperty("file");
        return configs;
    }

}
