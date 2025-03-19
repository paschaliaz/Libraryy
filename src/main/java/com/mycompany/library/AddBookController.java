/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.library;

import com.mycompany.library.db.AuthorDBHandler;
import com.mycompany.library.db.BookDBHandler;
import com.mycompany.library.db.PublisherDBHandler;
import com.mycompany.library.model.Author;
import com.mycompany.library.model.Publisher;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddBookController implements Initializable {

    @FXML
    private TextField isbnTxt;
    @FXML
    private TextField titleTxt;
    @FXML
    private ComboBox<Author> authorCombo;
    @FXML
    private ComboBox<Publisher> publisherCombo;
    @FXML
    private AnchorPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ArrayList<Publisher> publishers = PublisherDBHandler.getAllPublishers();

        publisherCombo.getItems().addAll(publishers);
        ArrayList<Author> authors = AuthorDBHandler.getAllAuthors();

        authorCombo.getItems().addAll(authors);

    }

    @FXML
    public void saveBook() {
        Author selectedAuthor = authorCombo.getValue();
        Publisher selectedPublisher=publisherCombo.getValue();
        int result = BookDBHandler.addNewBook(titleTxt.getText(), selectedAuthor.getId(),selectedPublisher.getId() , isbnTxt.getText(), 3);
        Alert alert = new Alert(AlertType.INFORMATION);

        alert.setTitle("Book Info");

        if (result > 0) {
            alert.setContentText("Saved successfully");
            titleTxt.setText("");
            authorCombo.setValue(null);
            publisherCombo.setValue(null);
            isbnTxt.setText("");
        } else {
            alert.setContentText("Not saved");
        }

        alert.showAndWait();
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

}
