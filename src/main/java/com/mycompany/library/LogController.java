/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.library;

import com.mycompany.library.db.LogDBHandler;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.ListView;
/**
 * FXML Controller class
 *
 * @author user
 */
public class LogController implements Initializable {


    @FXML
    private ListView<String> logList;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> bookList = LogDBHandler.getBookLog();
        ObservableList<String> bookListOb = FXCollections.observableArrayList(bookList);
        logList.setItems(bookListOb);
    }    
    
}
