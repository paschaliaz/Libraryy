/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.library;

import com.mycompany.library.db.AuthorDBHandler;
import com.mycompany.library.db.BookDBHandler;
import com.mycompany.library.model.Author;
import com.mycompany.library.model.Book;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class AllBooksController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView<Book> tableView;
    @FXML
    private TableColumn<Book, Integer> idCol;
    @FXML
    private TableColumn<Book, String> titleCol;
    @FXML
    private TableColumn<Book, Integer> authorCol;
    @FXML
    private TableColumn<Book, Integer> publisherCol;
    @FXML
    private TableColumn<Book, String> isbnCol;
    @FXML
    private TableColumn<Book, Integer> copiesCol;

    @FXML
    private void handleBookDeleteOption(ActionEvent event) {
        Book selectedForDelete = tableView.getSelectionModel().getSelectedItem();

        if (selectedForDelete == null) {
            AlertMaker.showErrorMessage("No book selected", "Please select a book to delete");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting Book");
        alert.setContentText("Are you sure you want to delete the book " + selectedForDelete.getTitle() + "?");

        Optional<ButtonType> answer = alert.showAndWait();

        if (answer.isPresent() && answer.get() == ButtonType.OK) {
            BookDBHandler.deleteBook(selectedForDelete);
        } else {
            AlertMaker.showInformationMessage("Delete Cancelled", "Delete process cancelled");
        }
    }

    private BookDBHandler bookDBHandler = new BookDBHandler();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }

    private void loadData() {
        ArrayList<Book> books = bookDBHandler.getAllBooks();
        tableView.getItems().addAll(books);

    }

    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        copiesCol.setCellValueFactory(new PropertyValueFactory<>("copies"));
    }
}
