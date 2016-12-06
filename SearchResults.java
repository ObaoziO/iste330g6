/**
 * Group 6: Yuqing Guo, David Player, Ashraf Wan
 * Final Project
 * ISTE.330.01
 * Instructor: Michael Floeser
**/
import java.util.*;

/**
   *SearchResults class is one of the business layer that connects data and presentation layers
   *Runs the search functionality
*/
public class SearchResults 
{
   //LibraryDatabase class used to connect to the DB
   private LibraryDatabase ldb = new LibraryDatabase();
   String paperId = "";
   String lastId = "";
   /**
      *Method to check if keyword exist in the paper_keywords table
      *if exist, get the paperId number
      *@param String result - Keyword that is pass through from getResults
      *@return none
   */
   public void checkResults(String result){
      String[][] data = ldb.getData("SELECT * FROM paper_keywords");
      for(int i = 0; i < data.length; i++){
         String test = data[i][1];
         String id = data[i][0];
         if(test.equals(result)){
            paperId = id;
         }
      }
   }//end checkresults
   /**
      *Method to get the result of the searched keyword
      *@param String result - Keyword that is pass through from presentation layer
      *@return ArrayList<String> - List of the research that was found
   */
   public ArrayList<String> getResults(String result){
      //run to check if exist
      checkResults(result);
      //create array to return to the presentation layer
      ArrayList<String> array = new ArrayList<String>();
      //if ID is empty
      if(paperId.equals("")){
         //add none to list so presentation know there was no result
         array.add("none");
      }
      //if Id is not empty
      else{
         //create array to be pass through the preparedstatement
         ArrayList<String> questions = new ArrayList<String>();
         questions.add(paperId);
         //create 2d array to receive data
         String[][] data = ldb.getData("SELECT * FROM papers WHERE id=?", questions); 
         //to go through the 2d array and attach them to the list      
         for(int i=0; i < data.length; i++){
            for(int j=0; j < data[i].length; j++){
               array.add(data[i][j]);
            }
         }
      }
      return array;//return list
   }//end getResults
   /**
      *Method to get the last id number
      *@param none
      *@return String - the id number
   */
   public String getLastId(){
      String[][] data = ldb.getData("SELECT * FROM papers");
      for(int i=0; i < data.length; i++){         
         lastId = data[i][0]; //go through all the ids
      }
      return lastId;//return the last id number
   }
} // End SearchResults