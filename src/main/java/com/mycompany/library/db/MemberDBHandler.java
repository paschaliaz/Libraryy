
package com.mycompany.library.db;

import com.mycompany.library.App;
import com.mycompany.library.model.Book;
import com.mycompany.library.model.Member;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;


public class MemberDBHandler {
    
   public static int addMember(String name, String address, double phoneNumber, String email) {
    try {
        String sql = "{CALL insert_member(?,?,?,?)}"; 
        CallableStatement c = App.dbConnection.prepareCall(sql);
        c.setString(1, name);
        c.setString(2, address);
        c.setDouble(3, phoneNumber);
        c.setString(4, email);

        int rows = c.executeUpdate();
        return rows;
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return -1;
}

    
    
    public static ArrayList<Member> getAllMembers() {
        ArrayList<Member> allMembers = new ArrayList<>();
        
         try {
            CallableStatement st = App.dbConnection.prepareCall("{CALL get_all_members(?)}");
            st.registerOutParameter(1, Types.REF_CURSOR);
            st.execute();
            
            ResultSet resultSet = (ResultSet) st.getObject(1);
            
            while(resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                
                Member m = new Member();
                m.setId(id);
                m.setName(name);
                
                allMembers.add(m);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return allMembers;
        
    }
    
    public static Member getMemberById(int memberId) {
        
        Member m = new Member();
         try {
            CallableStatement st = App.dbConnection.prepareCall("{CALL get_member_by_id(?,?)}");
            st.setInt(1, memberId);
            st.registerOutParameter(2, Types.REF_CURSOR);
            st.execute();
            
            ResultSet resultSet = (ResultSet) st.getObject(1);
            
            while(resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
               
                m.setId(id);
                m.setName(name);
               
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
         
         return m;
    }
    
    public static ArrayList<Book> getBooksIssuedToMember(int memberId) {
        ArrayList<Book> books = new ArrayList<>();
        
        try {
            CallableStatement st = App.dbConnection.prepareCall("{CALL get_books_issued_to_member(?,?)}");
            st.setInt(1, memberId);
            st.registerOutParameter(2, Types.REF_CURSOR);
            st.execute();
            
            ResultSet resultSet = (ResultSet) st.getObject(2);
            
            while(resultSet.next()) {
                int id = resultSet.getInt("BOOK_ID");
                String title = resultSet.getString("BOOK_TITLE");
                
                Book book = new Book();
                book.setId(id);
                book.setTitle(title);
                
                
                books.add(book);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        
        return books;
    }
    
}
