
package com.mycompany.library.db;

import com.mycompany.library.App;
import com.mycompany.library.model.Book;
import com.mycompany.library.model.Member;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookDBHandler {

    public static ArrayList<Book> getAllBooks() {
        ArrayList<Book> allBooks = new ArrayList<>();

        try {
            CallableStatement st = App.dbConnection.prepareCall("{CALL get_all_books(?)}");
            st.registerOutParameter(1, Types.REF_CURSOR);
            st.execute();

            ResultSet resultSet = (ResultSet) st.getObject(1);

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String title = resultSet.getString("TITLE");
                int author = resultSet.getInt("AUTHOR_ID");
                int publisher = resultSet.getInt("PUBLISHER_ID");
                String ISBN = resultSet.getString("ISBN");
                int copies = resultSet.getInt("COPIES");

                Book book = new Book();
                book.setId(id);
                book.setTitle(title);
                book.setAuthor(author);
                book.setPublisher(publisher);
                book.setISBN(ISBN);
                book.setCopies(copies);

                allBooks.add(book);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return allBooks;
    }

    public static int addNewBook(String title, int authorId, int publisherId, String ISBN, int copies) {
        try {
            String sql = "{CALL insert_book(?,?,?,?,?)}";
            PreparedStatement st = App.dbConnection.prepareStatement(sql);
            st.setString(1, title);
            st.setInt(2, authorId);
            st.setInt(3, publisherId);
            st.setString(4, ISBN);
            st.setInt(5, copies);

            int rows = st.executeUpdate();
            return rows;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public static boolean issueBook(int bookId, int memberId, Date issuedDate, Date duedate) {

        try {
            String sql = "{CALL issue_book_to_member(?,?,?,?,?)}";
            CallableStatement c = App.dbConnection.prepareCall(sql);
            c.setInt(1, bookId);
            c.setInt(2, memberId);
            c.setDate(3, issuedDate);
            c.setDate(4, duedate);
            c.registerOutParameter(5, Types.INTEGER);

            c.execute();

            int result = c.getInt(5);
            if (result == 1) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public static ArrayList<Book> getAvailableBooks() {
        ArrayList<Book> books = new ArrayList<>();

        try {
            CallableStatement st = App.dbConnection.prepareCall("{CALL get_available_books(?)}");
            st.registerOutParameter(1, Types.REF_CURSOR);
            st.execute();

            ResultSet resultSet = (ResultSet) st.getObject(1);

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String title = resultSet.getString("TITLE");

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

    public static int issueBookToMember(Book book, Member member) {

        java.util.Date today = new java.util.Date();
        LocalDate returnDate = LocalDate.now().plusMonths(1);
        int result = -1;

        try {
            CallableStatement st = App.dbConnection.prepareCall("{CALL issue_book_to_member(?,?,?,?,?)}");
            st.setInt(1, book.getId());
            st.setInt(2, member.getId());
            st.setDate(3, new Date(today.getTime()));
            st.setDate(4, Date.valueOf(returnDate));

            st.registerOutParameter(5, Types.INTEGER);
            st.execute();

            result = st.getInt(5);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public static int returnBook(Book book, int memberId) {
        java.util.Date today = new java.util.Date();
        int result = -1;

        try {
            CallableStatement st = App.dbConnection.prepareCall("{CALL return_book(?,?,?,?)}");
            st.setInt(1, book.getId());
            st.setInt(2, memberId);
            st.setDate(3, new Date(today.getTime()));
            st.registerOutParameter(4, Types.INTEGER);

            st.execute();

            result = st.getInt(4);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;

    }

  public static void deleteBook(Book book) {
    try {
        CallableStatement st = App.dbConnection.prepareCall("{CALL delete_book(?)}");
        st.setInt(1, book.getId()); 

        boolean hasResults = st.execute();

        System.out.println("Stored procedure executed: " + hasResults);

        if (!hasResults) {
          
            System.out.println("Book deleted successfully.");
        } else {
            
            System.out.println("Unexpected result set returned.");
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        System.err.println("Error executing stored procedure: " + ex.getMessage());
    }
}



}
