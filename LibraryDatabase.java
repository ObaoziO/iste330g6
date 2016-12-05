/**
 * Group 6: Yuqing Guo, David Player, Ashraf Wan
 * Final Project
 * ISTE.330.01
 * Instructor: Michael Floeser
**/

import java.sql.*;
import java.util.*;

class LibraryDatabase 
{
   private String uri = "jdbc:mysql://localhost/FacResearchDB?autoReconnect=true&useSSL=false";
   private String driver = "com.mysql.jdbc.Driver";
   private String user = "root";
   private String password = "maple";
   private String sql;
   private int numField;
   
   Connection conn = null;
   Statement stmnt = null;
   PreparedStatement pstmt = null;
   
   // Connect to the database and returns true or false 
   // depending on the success of the connection
   public boolean connect() 
   { 
      // Load the driver
      String driver = "com.mysql.jdbc.Driver"; // MySQL database driver
      try 
      {
         Class.forName(driver);
         // System.out.println("MySQL database driver loaded");
      }
      catch(ClassNotFoundException cnfe) 
      {
         System.out.println("Cannot find or load driver: " + driver);
      }
      
      try 
      {
         conn = DriverManager.getConnection(uri, user, password);
         return true;
      }
      catch(SQLException sqle) 
      {
         System.out.println("Failed to connect to database: " + sqle);
         sqle.printStackTrace();
         
         return false;
      }
   } // End connect
    
   // Close the connection and returns true or false 
   // depending on the success of the close
   public boolean close() 
   {
      try
      {
         if (conn != null ) 
         {
            conn.close(); // Close connection
         }
         return true;
      }
      catch( SQLException sqle ) 
      {
         System.out.println( "Failed to close connection or no database connected: " + sqle );
         sqle.printStackTrace();
         return false;
      }
   } // close
   
   // To obtain data from the database
   public String[][] getData(String sql) 
   {
      String[][] ary = null;
      try 
      { // 1
         try 
         {
            connect();
         }
         catch (Exception e) 
         {
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
         
         while (rs.next()) 
         {
            for (int i=1; i<=numCol; i++ ) 
            { // Go through each field of rows of rs
               ary[ row ][ i-1 ] = rs.getString(i);
            }
            row++;
         }
      } // End try 1
      catch(SQLException sqle) 
      {
         System.out.println("SQLException error: " + sqle);
         sqle.printStackTrace();
      }
      return ary; 
   } // End getData
   // To obtain data from the database - PREPARED STATEMENT
   public String[][] getData(String sql, ArrayList<String> questions) 
   {
      String[][] ary = null;
      try 
      { // 1
         try 
         {
            connect();
         }
         catch (Exception e) 
         {
            e.printStackTrace();
         }
         
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, questions.get(0));
         ResultSet rs = pstmt.executeQuery(); 
         ResultSetMetaData rsmd = rs.getMetaData();
         
         rs.last(); // Get the number of the last row and put it into the array
         int numRow = rs.getRow();
         int numCol = rsmd.getColumnCount(); // Returns the number of columns
         ary = new String[numRow][numCol]; 
         rs.beforeFirst();
         int row = 0;
         
         while (rs.next()) 
         {
            for (int i=1; i<=numCol; i++ ) 
            { // Go through each field of rows of rs
               ary[ row ][ i-1 ] = rs.getString(i);
            }
            row++;
         }
      } // End try 1
      catch(SQLException sqle) 
      {
         System.out.println("SQLException error: " + sqle);
         sqle.printStackTrace();
      }
      return ary; 
   } // End getData
   // For making changes to the database - FACULTY only
   public boolean setData(String sql, ArrayList<String> questions) 
   {
      try 
      { // 1
         try 
         {
            connect();
         }
         catch (Exception e) 
         {
            System.out.println("Failed to set data: " + e);
            e.printStackTrace();
         }
          
         pstmt = conn.prepareStatement(sql);
         for(int i = 0; i < questions.size(); i++){
            pstmt.setString(i+1, questions.get(i));
         }
         
         if(pstmt.executeUpdate() > 0)
         {
            return true; 
         }
         else
         {
            return false; 
         }
      } // End try 1
      catch(SQLException sqle) 
      {
         System.out.println("SQLException error: " + sqle);
         sqle.printStackTrace();
         return false;
      }
   } // End setData
   
   // Display the information to user
   public boolean descTable(String sql) 
   {
      try 
      { // 1
         try 
         { 
            connect();
         }
         catch (Exception e) 
         {
            System.out.println("Failed to get data: " + e);
            e.printStackTrace();
         }
         
         stmnt = conn.createStatement();
         ResultSet rs = stmnt.executeQuery(sql);
         ResultSetMetaData rsmd = rs.getMetaData();
         
         int numCols = rsmd.getColumnCount(); // Returns the number of columns
         while (rs.next()) 
         {
            for (int i=1; i<=numField; i++) 
            {
               String name = rsmd.getColumnName(i); // Name of column i
               String tblNam = rsmd.getTableName(i); // Gets the column's table name
               DatabaseMetaData dbmd = conn.getMetaData();
            } // End for 
            numCols++;  
         } // End while
         return true;
      } // End try 1
      catch(SQLException sqle) 
      {
         System.out.println("SQLException error: " + sqle);
         sqle.printStackTrace();
         return false;
      }
   } // End descTable
   
} // End LibraryDatabase