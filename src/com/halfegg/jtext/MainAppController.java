package com.halfegg.jtext;

import com.halfegg.jtext.io.Config;
import com.halfegg.jtext.io.FileIO;
import com.halfegg.jtext.util.FontInit;
import com.halfegg.jtext.util.Shortcut;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Author: halfegg
 */
public class MainAppController {

    @FXML
    private VBox root;
    @FXML
    private TextArea textArea;
    @FXML
    private Label fileLabel;
    @FXML
    public CheckBox wrapTextCheckBox;
    @FXML
    public CheckBox showFileOnStartCheckBox;

    private final CurrentFile currentFile = new CurrentFile();
    private final WindowLayer windowLayer = new WindowLayer();
    private final Config config = new Config();
    private final FontInit fontInit = new FontInit();

    public void initialize() {
        initWrapText();
        initTextAreaFont();
        initShowFile();
    }

    @FXML
    public void keyCombinations(KeyEvent keyEvent) {
        if (Shortcut.OPEN_FILE_KEY_COMBINATION.match(keyEvent))
            openFile();
        else if (Shortcut.NEW_FILE_KEY_COMBINATION.match(keyEvent))
            newFile();
        else if (Shortcut.SAVE_FILE_KEY_COMBINATION.match(keyEvent))
            saveFile();
        else if (Shortcut.SAVEAS_FILE_KEY_COMBINATION.match(keyEvent))
            saveAsFile();
        else if (Shortcut.FIND_REPLACE_KEY_COMBINATION.match(keyEvent))
            findReplaceText();
        else if (Shortcut.EDIT_FONT_KEY_COMBINATION.match(keyEvent))
            editFont();
        else if (Shortcut.CHANGE_THEME_KEY_COMBINATION.match(keyEvent))
            changeTheme();
    }

    @FXML
    private void newFile() {
        textArea.clear();
        currentFile.set(null);
        updateNamePlacements();
    }

    @FXML
    private void openFile() {
        currentFile.set(windowLayer.getOpenFileChooser(root.getScene().getWindow(), textArea));
        updateNamePlacements();
    }

    @FXML
    private void saveFile() {
        if (currentFile.get() != null) windowLayer.getSaveFileChooser(currentFile.get(), textArea.getText());
        else saveAsFile();
        updateNamePlacements();
    }

    @FXML
    private void saveAsFile() {
        currentFile.set(windowLayer.getSaveAsFileChooser(root.getScene().getWindow(), textArea.getText()));
        updateNamePlacements();
    }

    @FXML
    private void closeApp() {
        Platform.exit();
    }

    @FXML
    private void copyText() {
        textArea.copy();
    }

    @FXML
    private void pasteText() {
        textArea.paste();
    }

    @FXML
    private void cutText() {
        textArea.cut();
    }

    @FXML
    private void deleteText() {
        textArea.deleteText(textArea.getSelection());
    }

    @FXML
    private void selectAll() {
        textArea.selectAll();
    }

    @FXML
    private void findReplaceText() {
        windowLayer.getFindReplaceWindow(root.getScene().getWindow());
    }

    @FXML
    private void insertDateTime() {
        textArea.insertText(textArea.getCaretPosition(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
    }

    @FXML
    private void editFont() {
        windowLayer.getFontSettingWindow(root.getScene().getWindow());
    }

    @FXML
    private void changeTheme() {
    }

    @FXML
    private void wrapText() {
        wrapTextCheckBox.setSelected(!wrapTextCheckBox.isSelected());
        textArea.setWrapText(wrapTextCheckBox.isSelected());
        config.setWrapTextConfig(wrapTextCheckBox.isSelected());
    }

    @FXML
    public void showFileOnStart() {
       if (showFileOnStartCheckBox.isSelected()) {
           showFileOnStartCheckBox.setSelected(false);
           config.setShowFileOnStartConfig(showFileOnStartCheckBox.isSelected(), "");
       }
       else {
           if (currentFile.get() != null) {
               showFileOnStartCheckBox.setSelected(true);
               config.setShowFileOnStartConfig(showFileOnStartCheckBox.isSelected(), currentFile.name());
           }
       }
    }

    @FXML
    private void about() {
    }

    @FXML
    private void help() {
    }

    private void initWrapText() {
        var wrapTextConfig = config.getWrapTextConfig();
        if (wrapTextConfig.equals("true")) wrapTextCheckBox.setSelected(true);
        else if (wrapTextConfig.equals("false")) wrapTextCheckBox.setSelected(false);
        textArea.setWrapText(wrapTextCheckBox.isSelected());
    }

    private void initTextAreaFont() {
        var configs = config.getFontConfig();
        fontInit.textArea(configs[0], configs[1], configs[2], textArea);
    }

    private void initShowFile() {
        var configs = config.getShowFileOnStartConfig();
        if (configs[0].equals("true")) {
            var file = new File(configs[1]);
            var contentFromRemoteFile = FileIO.read(file);
            if (contentFromRemoteFile != null) {
                textArea.setText(contentFromRemoteFile);
                textArea.positionCaret(textArea.getLength());
                currentFile.set(file);
                showFileOnStartCheckBox.setSelected(true);
                fileLabel.setText(currentFile.name());
            }
        }
    }

    private void updateNamePlacements() {
        if (currentFile.get() != null) ((Stage) root.getScene().getWindow()).setTitle("jtext - " + currentFile.get().getName());
        else ((Stage) root.getScene().getWindow()).setTitle("jtext");
        fileLabel.setText(currentFile.name());
    }

}
