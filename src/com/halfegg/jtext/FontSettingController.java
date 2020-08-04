package com.halfegg.jtext;

import com.halfegg.jtext.io.Config;
import com.halfegg.jtext.util.FontInit;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Author: halfegg
 */
public class FontSettingController {

    @FXML
    public VBox root;
    @FXML
    public ListView<String> fontFamilyListView;
    @FXML
    public ListView<String> fontStyleListView;
    @FXML
    public ListView<String> fontSizeListView;
    @FXML
    public Text previewText;

    private final FontInit fontInit = new FontInit();
    private final Config config = new Config();

    public void initialize() {
        initListViews();
        initFonts();
    }

    @FXML
    public void previewAction() {
        applySettingsToPreviewText();
    }

    @FXML
    public void applyAction() {
        applySettingsToTextArea();
        config.setFontConfig(fontFamilyListView.getSelectionModel().getSelectedItem(),
                fontStyleListView.getSelectionModel().getSelectedItem(),
                fontSizeListView.getSelectionModel().getSelectedItem());
        getOwner().requestFocus();
        ((Stage) root.getScene().getWindow()).close();
    }

    private void applySettingsToPreviewText() {
        fontInit.textFlow(fontFamilyListView.getSelectionModel().getSelectedItem(),
                fontStyleListView.getSelectionModel().getSelectedItem(),
                fontSizeListView.getSelectionModel().getSelectedItem(), previewText);
    }

    private void applySettingsToTextArea() {
        var textArea = getTextArea();
        var indexRange = textArea.getSelection();
        textArea.deselect();
        fontInit.textArea(fontFamilyListView.getSelectionModel().getSelectedItem(),
                fontStyleListView.getSelectionModel().getSelectedItem(),
                fontSizeListView.getSelectionModel().getSelectedItem(), textArea);
        textArea.selectRange(indexRange.getStart(), indexRange.getEnd());
    }

    private void initFonts() {
        var configs = config.getFontConfig();
        fontFamilyListView.getSelectionModel().select(configs[0]);
        fontStyleListView.getSelectionModel().select(configs[1]);
        fontSizeListView.getSelectionModel().select(configs[2]);
        fontFamilyListView.scrollTo(configs[0]);
        fontStyleListView.scrollTo(configs[1]);
        fontSizeListView.scrollTo(configs[2]);
        applySettingsToPreviewText();
    }

    private void initListViews() {
        fontInit.familyList(fontFamilyListView);
        fontInit.styleList(fontStyleListView);
        fontInit.sizeList(fontSizeListView);
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

    private Stage getOwner() {
        return (Stage) ((Stage) root.getScene().getWindow()).getOwner();
    }
}
