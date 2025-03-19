
package com.mycompany.library.db;

import com.mycompany.library.App;
import com.mycompany.library.model.Author;
import com.mycompany.library.model.Book;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;


public class AuthorDBHandler {
    public static ArrayList<Author> getAllAuthors(){
       ArrayList<Author> authors=new ArrayList<>();
       try{
       String sql="{CALL get_all_authors(?)}";
       CallableStatement c=App.dbConnection.prepareCall(sql);
       c.registerOutParameter(1, Types.REF_CURSOR);
       c.execute();
       
       ResultSet resultSet = (ResultSet) c.getObject(1);
       
        while(resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                
                Author author = new Author();
                author.setId(id);
                author.setName(name);
                
                authors.add(author);
        }
       }
       catch(SQLException ex){
          ex.printStackTrace();
       }
       return authors;
       
    
    }
    
     public static int addAuthor(String name,double phonenumber, String email) {
    try {
        String sql = "{CALL insert_author(?,?,?)}"; 
        CallableStatement c = App.dbConnection.prepareCall(sql);
        c.setString(1, name);
        c.setDouble(2, phonenumber);
        c.setString(3, email);

        int rows = c.executeUpdate();
        return rows;
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return -1;
}

}


