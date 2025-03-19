/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.library;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertMaker {

    public static void showErrorMessage(String title, String content) {
        showAlert(AlertType.ERROR, title, content);
    }

    public static void showInformationMessage(String title, String content) {
        showAlert(AlertType.INFORMATION, title, content);
    }

    private static void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}


