package com.halfegg.jtext;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FindReplaceController {

    @FXML
    private GridPane root;
    @FXML
    private TextField findTextField;
    @FXML
    private TextField replaceTextField;

    private static int fromIndex = 0;

    public void initialize() {}

    @FXML
    private void findAction() {
        var textArea = getTextArea();
        fromIndex = textSelector(findTextField.getText(), textArea, fromIndex);
    }

    @FXML
    private void replaceAction() {
        var textArea = getTextArea();
        if (textArea.getSelection().getLength() != 0 && !replaceTextField.getText().isEmpty()) {
            textArea.replaceText(textArea.getSelection(), replaceTextField.getText());
            findAction();
        }
    }

    private int textSelector(String keyWord, TextArea textArea, int index) {
        var content = textArea.getText();
        var start = content.indexOf(keyWord, index);
        var end = start + keyWord.length();

        if (start != -1) {
            textArea.selectRange(start, end);
            return end;
        } else return 0;
    }

    private TextArea getTextArea() {
        var stage = (Stage) root.getScene().getWindow();
        var owner = (Stage) stage.getOwner();
        var parent = (VBox) owner.getScene().getRoot();
        var anchorPane = (AnchorPane) parent.getChildren().get(1);
        return (TextArea) anchorPane.getChildren().get(0);
    }
}
