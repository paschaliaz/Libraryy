/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.library.db;

import com.mycompany.library.App;
import com.mycompany.library.model.Author;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class LogDBHandler {
    
    public static ArrayList<String> getBookLog() {
        ArrayList<String> log = new ArrayList<>();
        
         try{
       String sql="{CALL get_book_log(?)}";
       CallableStatement c=App.dbConnection.prepareCall(sql);
       c.registerOutParameter(1, Types.REF_CURSOR);
       c.execute();
       
       ResultSet resultSet = (ResultSet) c.getObject(1);
       
        while(resultSet.next()) {
            StringBuilder sb = new StringBuilder();
            sb.append("TIMESTAMP: ");
            sb.append(resultSet.getTimestamp("TIMESTAMP"));
            sb.append(", OPERATION: ");
            sb.append(resultSet.getString("OPERATION"));
            sb.append(", USERNAME: ");
            sb.append(resultSet.getString("USERNAME"));
            sb.append(", BOOK ID: ");
            sb.append(resultSet.getInt("BOOK_ID"));
                
            log.add(sb.toString());
        }
       }
       catch(SQLException ex){
          ex.printStackTrace();
       }
        return log;
    }
    
}
