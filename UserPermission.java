/**
 * Group 6: Yuqing Guo, David Player, Ashraf Wan
 * Final Project
 * ISTE.330.01
 * Instructor: Michael Floeser
**/
import java.security.*;

/**
   *UserPermission class is one of the business layer that connects the data and presentation layer
   *It is used as the functionality for signing in
*/
public class UserPermission 
{
   String fname = null;
   String lname = null;
   String email = null;
   String pass = null;
   //Call LibraryData class to connect to DB
   private LibraryDatabase ldb = new LibraryDatabase();
   
   /**
      *Method to login a user if their username and pass are in the DB
      *@param String inEmail - Input email 
      *@param String inPass - Input password
      *@return boolean true or false
   */
   public boolean login(String inEmail, String inPass) 
   {
      String tempPass = passwordSafe(inPass); //hash the inPass
      System.out.println(tempPass);
      String[][] data = ldb.getData("SELECT * FROM faculty");  //look for the data in the table
      String test = null;
      String test2 = null;
      String name = null;
      //Go through all the found results
      for(int i = 0; i < data.length; i++) 
      {
         test = data[i][3];//Password Field in DB
         test2 = data[i][4];//Email Field in DB
         name = data[i][1];//Name field in DB
         //if pass field and email field match
         if(inEmail.equals(test2) && tempPass.equals(test)) 
         {
            //save first name
            fname = name;
            return true;   //return success
         }               
      } // End for loop
      return false;  //return failure
   } // End login
   /**
      *Method to get name to presentation
      *@param none
      *@return String - the name
   */
   public String signedIn()
   {
      return fname;
   }//end signedIn
   /**
      *Method to hash the password using MD5
      *@param String password - Input password
      *@return String - the newly hashed password
   */
   public String passwordSafe(String password){
      String newPass = null;
      try{
         //set up the instance for MD5
         MessageDigest md = MessageDigest.getInstance("MD5");
         //get byte for the password string
         md.update(password.getBytes());
         
         byte byteData[] = md.digest();
         StringBuffer sb = new StringBuffer();
         for(int i = 0; i < byteData.length; i++){
            sb.append(Integer.toString((byteData[i] &0xff) + 0x100, 16).substring(1));
         }
         newPass = sb.toString();   //the newly buffered password set to string
      }
      catch(NoSuchAlgorithmException nsae){
         System.out.println("Fail");
      }
      return newPass;   //return newly hashed password
      
   }//end passwordSafe
} // End UserPermission