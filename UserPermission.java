/**
 * Group 6: Yuqing Guo, David Player, Ashraf Wan
 * Final Project
 * ISTE.330.01
 * Instructor: Michael Floeser
**/

// User specific permissions are provided accordingly
public class UserPermission 
{
   String fname = null;
   String lname = null;
   String email = null;
   String pass = null;
   LibraryDatabase ldb = new LibraryDatabase();
   
   // To check validity of user information
   public boolean login(String inEmail, String inPass) 
   {
      String[][] data = ldb.getData("SELECT * FROM faculty");
      String test = null;
      String test2 = null;
      String name = null;
      for(int i = 0; i < data.length; i++) 
      {
         test = data[i][3];
         test2 = data[i][4];
         name = data[i][1];
         if(inEmail.equals(test2) && inPass.equals(test)) 
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
} // End UserPermission