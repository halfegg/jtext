package com.halfegg.jtext;

import com.halfegg.jtext.io.Config;
import com.halfegg.jtext.io.ExceptionLogger;
import com.halfegg.jtext.io.FileIO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.*;

import java.io.File;
import java.io.IOException;

public class WindowLayer {

    private final ExceptionLogger logger = new ExceptionLogger();
    private final Config config = new Config();

    public Parent getRoot() {
        try {
            config.createFiles();
            return FXMLLoader.load(getClass().getResource("resources/main-app.fxml"));
        } catch (IOException ex) {
            logger.log(this.getClass().getName(),"getRoot()", ex);
            return null;
        }
    }

    public File getOpenFileChooser(Window sceneWindow, TextArea textArea) {
        var stage = new Stage();
        var fileChooser = new FileChooser();
        stage.initOwner(sceneWindow);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setWidth(500.0);
        stage.setHeight(500.0);
        fileChooser.setTitle("Open File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files", "*", "*.*"));
        var file = fileChooser.showOpenDialog(stage);
        textArea.setText(FileIO.read(file));
        textArea.positionCaret(textArea.getLength());
        return file;
    }

    public void getSaveFileChooser(File file, String content) {
        FileIO.write(file, content);
    }

    public File getSaveAsFileChooser(Window sceneWindow, String content) {
        var stage = new Stage();
        var fileChooser = new FileChooser();
        stage.initOwner(sceneWindow);
        stage.initModality(Modality.APPLICATION_MODAL);
        fileChooser.setTitle("Save File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        var file = fileChooser.showSaveDialog(stage);
        FileIO.write(file, content);
        return file;
    }

    public void getFindReplaceWindow(Window sceneWindow) {
        try {
            var stage = new Stage();
            var scene = new Scene(FXMLLoader.load(getClass().getResource("resources/find-replace-text.fxml")));
            stage.setScene(scene);
            stage.initOwner(sceneWindow);
            stage.initModality(Modality.NONE);
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Find / Replace Text");
            stage.setResizable(false);
            stage.show();
            stage.requestFocus();
            stage.setX(sceneWindow.getX() + sceneWindow.getWidth() / 2 - stage.getWidth() / 2);
            stage.setY(sceneWindow.getY() + sceneWindow.getHeight() / 2 - stage.getHeight() / 2);
        } catch (IOException ex) {
            logger.log(this.getClass().getName(),"getFindReplaceWindow(Window)", ex);
        }
    }

    public void getFontSettingWindow(Window sceneWindow) {
        try {
            var stage = new Stage();
            var scene = new Scene(FXMLLoader.load(getClass().getResource("resources/font-setting.fxml")));
            stage.setScene(scene);
            stage.initOwner(sceneWindow);
            stage.initModality(Modality.NONE);
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Font Setting");
            stage.setResizable(false);
            stage.show();
            stage.requestFocus();
            stage.setX(sceneWindow.getX() + sceneWindow.getWidth() / 2 - stage.getWidth() / 2);
            stage.setY(sceneWindow.getY() + sceneWindow.getHeight() / 2 - stage.getHeight() / 2);
        } catch (IOException ex) {
            logger.log(this.getClass().getName(),"getFontSettingWindow(Window)", ex);
        }
    }

}
