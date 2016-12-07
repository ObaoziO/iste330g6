/**
 * Group 6: Yuqing Guo, David Player, Ashraf Wan
 * Final Project
 * ISTE.330.01
 * Instructor: Michael Floeser
**/

import java.sql.*;
import java.util.*;
/**
   *LibraryDatabase is the data layer for this program
   *and it connects the program to the database
*/
class LibraryDatabase 
{
   private String uri = "jdbc:mysql://localhost/FacResearchDB?autoReconnect=true&useSSL=false";
   private String driver = "com.mysql.jdbc.Driver";
   private String user = "root";
   private String password = "misty";
   private String sql;
   private int numField;
   
   Connection conn = null;
   Statement stmnt = null;
   PreparedStatement pstmt = null;
   
   /**
      *Method to connect to the Database
      *@param none
      *@return boolean true or false
   */
   public boolean connect() 
   { 
      String driver = "com.mysql.jdbc.Driver"; // MySQL database driver
      try 
      {
         Class.forName(driver); //Load Driver
         // System.out.println("MySQL database driver loaded");
      }
      catch(ClassNotFoundException cnfe) 
      {
         System.out.println("Cannot find or load driver: " + driver);
      }
      
      try 
      {
         conn = DriverManager.getConnection(uri, user, password); //Connect to the MySQL DB
         return true; //return true on success
      }
      catch(SQLException sqle) 
      {
         System.out.println("Failed to connect to database: " + sqle);        
         return false; //return false on failure
      }
   } // End connect
    
   /**
      *Method to close to the Database
      *@param none
      *@return boolean true or false
   */
   public boolean close() 
   {
      try
      {
         if (conn != null ) 
         {
            conn.close(); // Close connection
         }
         return true; //return true on success
      }
      catch( SQLException sqle ) 
      {
         System.out.println( "Failed to close connection or no database connected: " + sqle );
         return false; //return false on failure
      }
   } //end close
   
   /**
      *Method to connect to the Database
      *@param String sql - The SQL query that is pass through to get data from DB
      *@return String[][] - 2D array of strings of data from DB
   */
   public String[][] getData(String sql) 
   {
      String[][] ary = null;  //create 2D array
      try 
      {
         //Connect to DB
         try 
         {
            connect();
         }
         catch (Exception e) 
         {
            e.printStackTrace();
         }
         //Create Statements
         stmnt = conn.createStatement();
         ResultSet rs = stmnt.executeQuery(sql); //execute statement
         ResultSetMetaData rsmd = rs.getMetaData(); //result metadata
         
         rs.last(); // Go to the last row 
         int numRow = rs.getRow();  //Get the number of the last row
         int numCol = rsmd.getColumnCount(); //Get the number of columns
         ary = new String[numRow][numCol]; //Set the Dimension for 2D array
         rs.beforeFirst(); //Go to before the first row
         int row = 0;
         //Go through the table
         while (rs.next()) 
         {
            for (int i=1; i<=numCol; i++ ) 
            {
               ary[ row ][ i-1 ] = rs.getString(i);   //write data to the 2d array
            }
            row++; //go to the next row
         }
      }
      catch(SQLException sqle) 
      {
         System.out.println("SQLException error: " + sqle);
      }
      return ary; //return 2D array
   } // End getData
   /**
      *Method to getData using Prepared Statement style
      *@param String sql - The SQL query that is pass through to get the data in DB
      *@param ArrayList<String> questions - arraylist of the prepared statment informations
      *@return String[][] - 2D array of data from DB
   */
   public String[][] getData(String sql, ArrayList<String> questions) 
   {
      String[][] ary = null;
      try 
      {
         //Connect to DB
         try 
         {
            connect();
         }
         catch (Exception e) 
         {
            e.printStackTrace();
         }
         //Create the prepareStament
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, questions.get(0));  //setString for the first ?
         ResultSet rs = pstmt.executeQuery();   //execute the prepared statement
         ResultSetMetaData rsmd = rs.getMetaData(); //get metadata of the results
         
         rs.last(); // Go to the last row
         int numRow = rs.getRow();  //get the row number
         int numCol = rsmd.getColumnCount(); // Returns the number of columns
         ary = new String[numRow][numCol]; //set the dimensions for the 2D array
         rs.beforeFirst(); //go to before first row
         int row = 0;
         //Go through all the data
         while (rs.next()) 
         {
            for (int i=1; i<=numCol; i++ ) 
            {
               ary[ row ][ i-1 ] = rs.getString(i);   //set data into the 2D array
            }
            row++;
         }
      }
      catch(SQLException sqle) 
      {
         System.out.println("SQLException error: " + sqle);
      }
      return ary; //return 2D array
   } // End getData
   /**
      *Method to make changes to the DB
      *@param String sql - the SQL query to make changes to the DB
      *@param ArrayList<String> questions - List of the prepared statement information
      *@return boolean true or false
   */
   public boolean setData(String sql, ArrayList<String> questions) 
   {
      try 
      {
         //Connect to DB
         try 
         {
            connect();
         }
         catch (Exception e) 
         {
            System.out.println("Failed to set data: " + e);
         }
         //create the prepared statement using the input string
         pstmt = conn.prepareStatement(sql);
         //go through all the list
         for(int i = 0; i < questions.size(); i++){
            pstmt.setString(i+1, questions.get(i));   //set String for every row in the list
         }
         //if statement execute make more than 0 change return true, else false
         if(pstmt.executeUpdate() > 0)
         {
            return true; 
         }
         else
         {
            return false; 
         }
      }
      catch(SQLException sqle) 
      {
         System.out.println("SQLException error: " + sqle);
         return false;
      }
   } // End setData  
} // End LibraryDatabase