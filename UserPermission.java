/**
 * Group 6: Yuqing Guo, David Player, Ashraf Wan
 * Final Project
 * ISTE.330.01
 * Instructor: Michael Floeser
**/

// User specific permissions are provided accordingly
public class UserPermission{
   String fname = null;
   String lname = null;
   String email = null;
   String pass = null;
   LibraryDatabase ldb = new LibraryDatabase();
   
   public boolean login(String inEmail, String inPass){
      String[][] data = ldb.getData("SELECT * FROM faculty");
      String test = null;
      String test2 = null;
      for(int i = 0; i < data.length; i++){
         test = data[i][2];
         test2 = data[i][3];
         if(inEmail.equals(test2) && inPass.equals(test)){
            email = inEmail;
            pass = inPass;
            fname = data[i][0];
            lname = data[i][1];
         }
         else{
            return false;
         }
         break;               
      }
      return true;

   }
}