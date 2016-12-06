/**
 * Group 6: Yuqing Guo, David Player, Ashraf Wan
 * Final Project
 * ISTE.330.01
 * Instructor: Michael Floeser
**/
import java.security.*;

// User specific permissions are provided accordingly
public class UserPermission 
{
   String fname = null;
   String lname = null;
   String email = null;
   String pass = null;
   private LibraryDatabase ldb = new LibraryDatabase();
   
   // To check validity of user information
   public boolean login(String inEmail, String inPass) 
   {
      String tempPass = passwordSafe(inPass);
      String[][] data = ldb.getData("SELECT * FROM faculty");
      String test = null;
      String test2 = null;
      String name = null;
      for(int i = 0; i < data.length; i++) 
      {
         test = data[i][3];
         test2 = data[i][4];
         name = data[i][1];
         if(inEmail.equals(test2) && tempPass.equals(test)) 
         {
            fname = name;
            return true;
         }               
      } // End for loop
      return false;
   } // End login
   public String signedIn()
   {
      return fname;
   }
   public String passwordSafe(String password){
      String newPass = null;
      try{
         MessageDigest md = MessageDigest.getInstance("MD5");
         md.update(password.getBytes());
         
         byte byteData[] = md.digest();
         StringBuffer sb = new StringBuffer();
         for(int i = 0; i < byteData.length; i++){
            sb.append(Integer.toString((byteData[i] &0xff) + 0x100, 16).substring(1));
         }
         newPass = sb.toString();
      }
      catch(NoSuchAlgorithmException nsae){
         System.out.println("Fail");
      }
      return newPass;
      
   }
} // End UserPermission