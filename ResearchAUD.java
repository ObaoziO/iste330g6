/**
 * Group 6: Yuqing Guo, David Player, Ashraf Wan
 * Final Project
 * ISTE.330.01
 * Instructor: Michael Floeser
**/
import java.util.*;
import java.sql.*;

// The codes for Research Adds, Updates, Deletes are found here
public class ResearchAUD 
{
   LibraryDatabase ldb = new LibraryDatabase();
   SearchResults sr = new SearchResults();
   public boolean addResearch(String title, String abstr, String citation, String keywords){
      boolean rc = false;
      String sql = "INSERT INTO papers VALUES(?,?,?,?)";     
      
      int id = Integer.parseInt(sr.getLastId()) + 1;
      String realId = Integer.toString(id);
      String sql2 = "INSERT INTO paper_keywords VALUES((SELECT id FROM papers WHERE id='"+realId+"'),?)";
      ArrayList<String> questions = new ArrayList<String>();
      questions.add(realId);
      questions.add(title);
      questions.add(abstr);
      questions.add(citation);
      
      ArrayList<String> questions2 = new ArrayList<String>();
      questions2.add(keywords);
      rc = ldb.setData(sql, questions);
      rc = ldb.setData(sql2, questions2);
      return rc;
   }
   public boolean deleteResearch(String title){
      String sql = "DELETE FROM papers WHERE title=?";
      String sql2 = "DELETE FROM paper_keywords WHERE id=(SELECT id FROM papers WHERE title=?)";
      boolean rc = false;
      ArrayList<String> array = new ArrayList<String>();
      array.add(title);
      
      rc = ldb.setData(sql2, array);
      rc = ldb.setData(sql, array);
      return rc;     
   }
   public boolean updateResearch(String id, String title, String abstr, String citation, String keywords){
      String sql = "UPDATE papers SET title=?, abstract=?, citation=? WHERE id=?";
      String sql2 = "UPDATE paper_keywords SET keyword=? WHERE id=?";
      boolean rc = false;
      ArrayList<String> array = new ArrayList<String>();
      array.add(title);
      array.add(abstr);
      array.add(citation);
      array.add(id);
      
      ArrayList<String> array2 = new ArrayList<String>();
      array2.add(keywords);
      array2.add(id);
      rc = ldb.setData(sql, array);
      rc = ldb.setData(sql2, array2);
      return rc;
   }
} // End ResearchAUD