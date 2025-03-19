/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.library;

import com.mycompany.library.db.AuthorDBHandler;
import com.mycompany.library.db.MemberDBHandler;
import com.mycompany.library.model.Author;
import com.mycompany.library.model.Member;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AddAuthorController implements Initializable {

    @FXML
    private AnchorPane rootpane;
    @FXML
    private TextField authorName;
    @FXML
    private TextField authorPhone;
    @FXML
    private TextField authorEmail;
    @FXML
    private AnchorPane rootPane;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Author> authors = AuthorDBHandler.getAllAuthors();
    }

   @FXML
private void saveAuthorButton(ActionEvent event) {
   
    String Name = authorName.getText();
    String Phone = authorPhone.getText();
    String Email = authorEmail.getText();

    
    double authorPhoneNumber = 0.0;  
    try {
        authorPhoneNumber = Double.parseDouble(Phone);
    } catch (NumberFormatException e) {
        
        e.printStackTrace();  
    }

    
    int result = AuthorDBHandler.addAuthor(Name,authorPhoneNumber,Email);

    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Author Info");

    if (result > 0) {
        alert.setContentText("Author information saved successfully");
    } else {
        alert.setContentText("Failed to save author information");
    }

    alert.showAndWait();
}


    @FXML
    private void cancelAuthorButton(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

}
