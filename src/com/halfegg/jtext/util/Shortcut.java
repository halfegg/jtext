package com.halfegg.jtext.util;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class Shortcut {

    private Shortcut() {}

    public static final KeyCombination OPEN_FILE_KEY_COMBINATION = new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);
    public static final KeyCombination NEW_FILE_KEY_COMBINATION = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);
    public static final KeyCombination SAVE_FILE_KEY_COMBINATION = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
    public static final KeyCombination SAVEAS_FILE_KEY_COMBINATION = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);
    public static final KeyCombination FIND_REPLACE_KEY_COMBINATION = new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN);
    public static final KeyCombination EDIT_FONT_KEY_COMBINATION = new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN);
    public static final KeyCombination CHANGE_THEME_KEY_COMBINATION = new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN);
}
