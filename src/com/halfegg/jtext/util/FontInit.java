package com.halfegg.jtext.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Arrays;

public class FontInit {

    public void familyList(ListView<String> familyListView) {
        ObservableList<String> fontFamilyList = FXCollections.observableArrayList(Font.getFamilies());
        familyListView.setItems(fontFamilyList);
        familyListView.getSelectionModel().select("System");
        familyListView.scrollTo("System");
    }

    public void styleList(ListView<String> styleListView) {
        ObservableList<String> fontStyleList = FXCollections.observableArrayList(
                Arrays.asList("Regular", "Bold", "Italic", "Bold Italic"));
        styleListView.setItems(fontStyleList);
        styleListView.getSelectionModel().select("Regular");
    }

    public void sizeList(ListView<String> sizeListView) {
        ObservableList<String> fontSizeList = FXCollections.observableArrayList(
                Arrays.asList("10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "21"));
        sizeListView.setItems(fontSizeList);
        sizeListView.getSelectionModel().select("14");
    }

    public void textFlow(String family, String style, String size, Text textFlowText) {
        switch (style) {
            case "Regular":
                textFlowText.setFont(Font.font(family, FontPosture.REGULAR, Double.parseDouble(size)));
                break;
            case "Italic":
                textFlowText.setFont(Font.font(family, FontPosture.ITALIC, Double.parseDouble(size)));
                break;
            case "Bold":
                textFlowText.setFont(Font.font(family, FontWeight.BOLD, Double.parseDouble(size)));
                break;
            case "Bold Italic":
                textFlowText.setFont(Font.font(family, FontWeight.BOLD, FontPosture.ITALIC, Double.parseDouble(size)));
                break;
            default:
                break;
        }
    }

    public void textArea(String family, String style, String size, TextArea textArea) {
        switch (style) {
            case "Regular":
                textArea.setFont(Font.font(family, FontPosture.REGULAR, Double.parseDouble(size)));
                break;
            case "Italic":
                textArea.setFont(Font.font(family, FontPosture.ITALIC, Double.parseDouble(size)));
                break;
            case "Bold":
                textArea.setFont(Font.font(family, FontWeight.BOLD, Double.parseDouble(size)));
                break;
            case "Bold Italic":
                textArea.setFont(Font.font(family, FontWeight.BOLD, FontPosture.ITALIC, Double.parseDouble(size)));
                break;
            default:
                break;
        }
    }
}
