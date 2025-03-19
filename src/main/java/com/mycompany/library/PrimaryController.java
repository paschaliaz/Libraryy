/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.library;

import com.mycompany.library.db.BookDBHandler;
import com.mycompany.library.db.MemberDBHandler;
import com.mycompany.library.model.Book;
import com.mycompany.library.model.Member;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class PrimaryController implements Initializable {

    @FXML
    private ComboBox<Book> availableBooksCombo;

    @FXML
    private ComboBox<Member> allMembersCombo;

    @FXML
    private ListView<Book> membersBooksList;

    @FXML
    private TextField memberIdTxt;

    @FXML

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ArrayList<Book> availableBooks = BookDBHandler.getAvailableBooks();
        availableBooksCombo.getItems().addAll(availableBooks);

        ArrayList<Member> allMembers = MemberDBHandler.getAllMembers();
        allMembersCombo.getItems().addAll(allMembers);

    }

    @FXML
    public void issueBook() {
        Book selectedBook = availableBooksCombo.getValue();
        Member selectedMember = allMembersCombo.getValue();

        int result = BookDBHandler.issueBookToMember(selectedBook, selectedMember);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Issue Book Info");
        if (result > 0) {
            alert.setContentText("Issued successfully");
        } else {
            alert.setContentText("Not issued");
        }

        alert.showAndWait();

    }

    @FXML
    public void findMembersBooks() {
        String memberId = memberIdTxt.getText();
        ArrayList<Book> booksToMember = new ArrayList<>();
        try {
            int memberIdInt = Integer.valueOf(memberId);
            booksToMember = MemberDBHandler.getBooksIssuedToMember(memberIdInt);
            ObservableList<Book> observableBookList = FXCollections.observableArrayList(booksToMember);
            membersBooksList.setItems(observableBookList);

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong member id input.");
            alert.setContentText("Member id must be an integer.");
            alert.showAndWait();
        }
    }

    @FXML
    public void returnBook() {
        String memberId = memberIdTxt.getText();

        try {
            int memberIdInt = Integer.valueOf(memberId);
            Book selectedBook = membersBooksList.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                int result = BookDBHandler.returnBook(selectedBook, memberIdInt);
                if (result > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success.");
                    alert.setContentText("Book returned successfully.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Fail.");
                    alert.setContentText("Book retured not.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Select a Book to return.");
                alert.setContentText("Select a Book to return.");
                alert.showAndWait();
            }

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong member id input.");
            alert.setContentText("Member id must be an integer.");
            alert.showAndWait();
        }
    }

    @FXML
    private void loadMember(ActionEvent event) {
        loadWindow("/com/mycompany/library/addMember.fxml", "Add New Member ");
    }

    @FXML
    private void loadBook(ActionEvent event) {
        loadWindow("/com/mycompany/library/addBook.fxml", "Add New Book ");
    }

    @FXML
    private void loadAuthor(ActionEvent event) {
        loadWindow("/com/mycompany/library/addAuthor.fxml", "Add New Author ");
    }

    @FXML
    private void loadPublisher(ActionEvent event) {
        loadWindow("/com/mycompany/library/addPublisher.fxml", "Add New Publisher ");
    }
    
     @FXML
    private void loadAllBooks(ActionEvent event) {
        loadWindow("/com/mycompany/library/allBooks.fxml", "Books ");
    }
    
    @FXML
    private void loadLog(ActionEvent event) {
        loadWindow("/com/mycompany/library/log.fxml", "Log ");
    }

    void loadWindow(String loc, String title) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
