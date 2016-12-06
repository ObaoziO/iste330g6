/**
 * Group 6: Yuqing Guo, David Player, Ashraf Wan
 * Final Project
 * ISTE.330.01
 * Instructor: Michael Floeser
**/
import java.util.*;

// The codes for Search Results are found here
public class SearchResults 
{
   private LibraryDatabase ldb = new LibraryDatabase();
   String paperId = "";
   String lastId = "";
   
   public void checkResults(String result){
      String[][] data = ldb.getData("SELECT * FROM paper_keywords");
      for(int i = 0; i < data.length; i++){
         String test = data[i][1];
         String id = data[i][0];
         if(test.equals(result)){
            paperId = id;
         }
      }
   }
   public ArrayList<String> getResults(String result){
      checkResults(result);
      
      ArrayList<String> array = new ArrayList<String>();
      if(paperId.equals("")){
         array.add("none");
      }
      else{
         ArrayList<String> questions = new ArrayList<String>();
         questions.add(paperId);
         String[][] data = ldb.getData("SELECT * FROM papers WHERE id=?", questions);       
         for(int i=0; i < data.length; i++){
            for(int j=0; j < data[i].length; j++){
               array.add(data[i][j]);
            }
         }
      }
      return array;
   }
   public String getLastId(){
      String[][] data = ldb.getData("SELECT * FROM papers");
      for(int i=0; i < data.length; i++){
         lastId = data[i][0];
      }
      return lastId;
   }
} // End SearchResults