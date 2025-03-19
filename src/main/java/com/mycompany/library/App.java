package com.mycompany.library;

import com.mycompany.library.db.BookDBHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

/**
 * JavaFX App
 */
public class App extends Application {
    
    public static Connection dbConnection;

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        String url="jdbc:postgresql://dblabs.it.teithe.gr:5432/it154446";
        String url2="jdbc:oracle:thin:@192.168.6.21:1521:dblabs";
        
        String username="";
        String passwd="";
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            dbConnection=DriverManager.getConnection(url2,username,passwd);


            BookDBHandler.getAllBooks();
            
           
            //dbConnection.close();
        } catch (SQLException | ClassNotFoundException ex) {
           ex.printStackTrace();
        }
        
        
        launch();
    }

}