/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.library.db;

import com.mycompany.library.App;
import com.mycompany.library.model.Author;
import com.mycompany.library.model.Publisher;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class PublisherDBHandler {
     public static ArrayList<Publisher> getAllPublishers(){
       ArrayList<Publisher> publishers=new ArrayList<>();
       try{
       String sql="{CALL get_all_publishers(?)}";
       CallableStatement c=App.dbConnection.prepareCall(sql);
       c.registerOutParameter(1, Types.REF_CURSOR);
       c.execute();
       
       ResultSet resultSet = (ResultSet) c.getObject(1);
       
        while(resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                
                Publisher publisher = new Publisher();
                publisher.setId(id);
                publisher.setName(name);
                
                publishers.add(publisher);
        }
       }
       catch(SQLException ex){
          ex.printStackTrace();
       }
       return publishers;
       
    
    }
         public static int addPublisher(String name,String address,String phonenumber, String email) {
    try {
        String sql = "{CALL insert_publisher(?,?,?,?)}"; 
        CallableStatement c = App.dbConnection.prepareCall(sql);
        c.setString(1, name);
        c.setString(2, address);
        c.setString(3, phonenumber);
         c.setString(4, email);

        int rows = c.executeUpdate();
        return rows;
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return -1;
}
}
