/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.library;

import com.mycompany.library.db.AuthorDBHandler;
import com.mycompany.library.db.PublisherDBHandler;
import com.mycompany.library.model.Author;
import com.mycompany.library.model.Publisher;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddPublisherController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField pubName;
    @FXML
    private TextField pubAddress;
    @FXML
    private TextField pubPhone;
    @FXML
    private TextField pubEmail;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Publisher> publishers = PublisherDBHandler.getAllPublishers();
    }

    @FXML
    private void savePublisherButton(ActionEvent event) {
        String Name = pubName.getText();
        String Address = pubAddress.getText();
        String Phone = pubPhone.getText();
        String Email = pubEmail.getText();

       

        int result = PublisherDBHandler.addPublisher(Name,Address,Phone, Email);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Publisher Info");

        if (result > 0) {
            alert.setContentText("Publisher information saved successfully");
        } else {
            alert.setContentText("Failed to save publisher information");
        }

        alert.showAndWait();
    }

    @FXML
    private void cancelPublisherButton(ActionEvent event) {
         Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

}
