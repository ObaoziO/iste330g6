/**
 * Group 6: Yuqing Guo, David Player, Ashraf Wan
 * Final Project
 * ISTE.330.01
 * Instructor: Michael Floeser
**/

import java.sql.*;
// import java.util.*;

class LibraryDatabase {
   private String uri = "jdbc:mysql://localhost/travel?autoReconnect=true&useSSL=false";
   private String driver = "com.mysql.jdbc.Driver";
   private String user = "root";
   private String password = "pw";
   private String sql;
   //private int numField;
   
   Connection conn = null;
   Statement stmnt = null;
   
   // Connect to the database and returns true or false 
   // depending on the success of the connection
   public boolean connect() { 
      // load the driver
      String driver = "com.mysql.jdbc.Driver"; // MySQL database driver
      try {
         Class.forName( driver );
         // System.out.println("MySQL database driver loaded");
      }
      catch(ClassNotFoundException cnfe) {
         System.out.println( "Cannot find or load driver: " + driver );
      }
      
      try{
         conn = DriverManager.getConnection( uri, user, password );
         return true;
      }
      catch( SQLException sqle ) {
         System.out.println( "Failed to connect to database: " + sqle );
         sqle.printStackTrace();
         
         return false;
      }
   } // connect
    
   // Close the connection and returns true or false 
   // depending on the success of the close
   public boolean close() {
      try{
         if (conn != null ) {
            conn.close(); // Close connection
         }
         return true;
      }
      catch( SQLException sqle ) {
         System.out.println( "Failed to close connection or no database connected: " + sqle );
         sqle.printStackTrace();
         return false;
      }
   } // close
   
   // To obtain data from the database
   public String[][] getData() {
      String[][] ary = null;
      try{ //1
         try {//2
            connect();
         }
         catch (Exception e) {
            e.printStackTrace();
         }
         
         stmnt = conn.createStatement();
         ResultSet rs = stmnt.executeQuery(sql); 
         ResultSetMetaData rsmd = rs.getMetaData();
         
         rs.last(); // Get the number of the last row and put it into the array
         int numRow = rs.getRow();
         int numCol = rsmd.getColumnCount(); // Returns the number of columns
         ary = new String[numRow][numCol]; 
         rs.beforeFirst();
         int row = 0;
         
         while (rs.next()){
            for (int i=1; i<=numCol; i++ ) { // Go through each field of rows of rs
               ary[ row ][ i-1 ] = rs.getString(i);
            } // End for loop
            row++;
         } // End while loop
         
      } // End try statement
      catch(SQLException sqle) {
         sqle.printStackTrace();
      }
      return ary; 
   } // getData
   
   // For making changes to the database - FACULTY only
   public boolean setData() {
      
      return true; // placeholder
   } //getData
   
   // Print out result for user
   public boolean descTable() {
      
      return true; // placeholder
   } //descTable
   
   // some comment about the method functinality
   // public PreparedStatement prepare() {
//       PreparedStatement prepStmt = null;
//       return prepStmt;
//    } //prepare
   
} // end LibraryDatabase