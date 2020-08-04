package com.halfegg.jtext;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Author: halfegg
 */
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

    /**
     *
     * @return TextArea from the main window.
     * embedding objects with castings.
     * better than four lines of code when separated.
     * better still than lookup() method.
     * delete comment and change block when come up with solution
     */
    private TextArea getTextArea() {
        return (TextArea) ((AnchorPane) ((VBox) ((Stage) root.getScene().getWindow())
                .getOwner().getScene().getRoot()).getChildren().get(1)).getChildren().get(0);

//        var stage = (Stage) root.getScene().getWindow();
//        var vBox = (VBox) stage.getOwner().getScene().getRoot();
//        var anchorPane = (AnchorPane) vBox.getChildren().get(1);
//        return (TextArea) anchorPane.getChildren().get(0);
    }
}
