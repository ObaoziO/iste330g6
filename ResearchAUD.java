/**
 * Group 6: Yuqing Guo, David Player, Ashraf Wan
 * Final Project
 * ISTE.330.01
 * Instructor: Michael Floeser
**/
import java.util.*;
import java.sql.*;

/**
   *ResearchAUD class is one of the business layer that connects
   *the data and presentation layer. This class is used to
   *run the add, delete, update button
*/
public class ResearchAUD 
{
   //Call the LibraryDatabase class to pass through info
   private LibraryDatabase ldb = new LibraryDatabase();
   //Call the SearchResults to get last id number
   private SearchResults sr = new SearchResults();
   /**
      *Method to Insert new row into a table
      *@param String title - The new research title
      *@param String abstr - The new research basic summary
      *@param String citation - the new research citation
      *@param String keywords - the new research keyword search
      *@return boolean rc - hold true or false indicating success or failure
   */
   public boolean addResearch(String title, String abstr, String citation, String keywords){
      boolean rc = false;
      String sql = "INSERT INTO papers VALUES(?,?,?,?)"; //SQL Query to INSERT new row into papers table
      
      int id = Integer.parseInt(sr.getLastId()) + 1;  //set new ID number for the new row
      String realId = Integer.toString(id);  //Parse new ID to string
      //SQL Query to INSERT new row into paper_keywords table
      String sql2 = "INSERT INTO paper_keywords VALUES((SELECT id FROM papers WHERE id='"+realId+"'),?)";
      ArrayList<String> questions = new ArrayList<String>();   //create list to pass through the preparedstatement
      //add info in order id, title, abstract, citation
      questions.add(realId);
      questions.add(title);
      questions.add(abstr);
      questions.add(citation);
      
      ArrayList<String> questions2 = new ArrayList<String>();  //create second list to pass through the preparedstatment for keyword
      questions2.add(keywords);  //add keyword to list
      rc = ldb.setData(sql, questions);   //execute first sql, return true or false
      rc = ldb.setData(sql2, questions2); //execute second sql, return true or false
      return rc;  //return true or false
   }//end add
   /**
      *Method to delete a row in a table
      *@param String title - The research title that needs to be deleted
      *@return boolean rc - return true or false indicating success or failure
   */
   public boolean deleteResearch(String title){
      //create SQL query to DELETE from papers
      String sql = "DELETE FROM papers WHERE title=?";
      //create second SQL query to DELETE from paper_keywords
      String sql2 = "DELETE FROM paper_keywords WHERE id=(SELECT id FROM papers WHERE title=?)";
      boolean rc = false;
      //Create list to pass title into the prepared statement
      ArrayList<String> array = new ArrayList<String>();
      array.add(title);
      
      rc = ldb.setData(sql2, array);   //run the second query first since paper_keywords is the child table
      rc = ldb.setData(sql, array);    //run the first query second since papers is the parent table
      return rc;  //return true or false
   }//end delete
   /**
      *Method to update a row in a table
      *@param String id - The research ID number
      *@param String title - The research Title that needs changing
      *@param String abstr -  The research Abstract that needs changing
      *@param String citation - The research Citation that needs changing
      *@param String keywords - The research Keyword that needs changing
      *@return boolean true or false
   */
   public boolean updateResearch(String id, String title, String abstr, String citation, String keywords){
      //Create SQL query to UPDATE the papers table
      String sql = "UPDATE papers SET title=?, abstract=?, citation=? WHERE id=?";
      //Create SQL query to UPDATE the paper_keywords table
      String sql2 = "UPDATE paper_keywords SET keyword=? WHERE id=?";
      boolean rc = false;
      //Create list to pass info through preparedstatement
      ArrayList<String> array = new ArrayList<String>();
      //Add info in order to list title, abstract, citation, id
      array.add(title);
      array.add(abstr);
      array.add(citation);
      array.add(id);
      //create list to pass keyword and id through preparedstatement
      ArrayList<String> array2 = new ArrayList<String>();
      //Add info in order to list keywords, id
      array2.add(keywords);
      array2.add(id);
      rc = ldb.setData(sql, array);    //run query to papers
      rc = ldb.setData(sql2, array2);  //run query to paper_keywords
      return rc; //return true or false
   }//End update
} // End ResearchAUD