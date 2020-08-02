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

    private TextArea getTextArea() {
        var stage = (Stage) root.getScene().getWindow();
        var owner = (Stage) stage.getOwner();
        var parent = (VBox) owner.getScene().getRoot();
        var anchorPane = (AnchorPane) parent.getChildren().get(1);
        return (TextArea) anchorPane.getChildren().get(0);
    }

    private Stage getOwner() {
        var stage = (Stage) root.getScene().getWindow();
        return (Stage) stage.getOwner();
    }
}
