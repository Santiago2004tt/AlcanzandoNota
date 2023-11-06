package com.example.alcanzandonota.util;

import javafx.scene.control.TextFormatter;

public class TextFormatterUtil {

    public static TextFormatter.Change upperCaseFormat(TextFormatter.Change change){
        change.setText(change.getText().toUpperCase());
        return change;
    }

    public static TextFormatter.Change integerFormat(TextFormatter.Change change){
        if (change.getText().matches("[0-9]*")) {
            return change;
        }
        return null;
    }

    public static TextFormatter.Change decimalFormat(TextFormatter.Change change){
        if (change.getControlNewText().matches("^\\d*\\.\\d+|\\d+\\.\\d*|\\d*$")) {
            return change;
        }
        return null;
    }
}
