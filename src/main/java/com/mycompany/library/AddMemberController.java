/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.library;

import com.mycompany.library.db.AuthorDBHandler;
import com.mycompany.library.db.BookDBHandler;
import com.mycompany.library.db.MemberDBHandler;
import com.mycompany.library.model.Author;
import com.mycompany.library.model.Member;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AddMemberController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField email;
    @FXML
    private AnchorPane rootPane;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Member> members = MemberDBHandler.getAllMembers();

    }

    @FXML
    private void saveButton(ActionEvent event) {
        String nameValue = name.getText();
        String addressValue = address.getText();
        String phoneNumberText = phoneNumber.getText();
        String emailValue = email.getText();

       
        double phoneNumberValue = 0.0;  
        try {
            phoneNumberValue = Double.parseDouble(phoneNumberText);
        } catch (NumberFormatException e) {
           
            
            e.printStackTrace();  
        }

        
        int result = MemberDBHandler.addMember(nameValue, addressValue, phoneNumberValue, emailValue);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Member Info");

        if (result > 0) {
            alert.setContentText("Saved successfully");
        } else {
            alert.setContentText("Not saved");
        }

        alert.showAndWait();
    }
    
      @FXML
    private void cancelButton(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
}


