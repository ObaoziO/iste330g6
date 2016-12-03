/**
* Group 6: Yuqing Guo, David Player, Ashraf Wan
* Final Project
* ISTE.330.01
* Instructor: Michael Floeser
**/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import java.sql.*;
import java.util.*;
//import java.awt.Font;

// **description of class here**
public class GUI extends JFrame
{
   private JTextArea jtaDLFRC, jtaMainContent; 
   private JMenuItem jmiAbout, jmiSignIn, jmiHelp, jmiSearch; 
   private JTextField jtfSearchBox; 
   private JScrollPane scrollPane; 
   private JButton jbUpdate, jbDelete, jbInsert; 
   String users = null;
   String pass = null;
   
   public GUI() 
   {
      displayGUI(); 
   }
   
   /*Display GUI*/ 
   public void displayGUI()
   {  
      /************************** 
       * Create JMenuBar object *
       **************************/
      JMenuBar topBar = new JMenuBar(); 
      setJMenuBar(topBar);
      
      // Create JMenu and other objects to add to the JMenuBar
      jmiSignIn = new JMenuItem("Sign In");
      jmiSignIn.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent ae){
               JPanel overal = new JPanel(new BorderLayout(5, 5));
            
               JPanel panel = new JPanel(new GridLayout(0,1,2,2));
               panel.add(new JLabel("Username", SwingConstants.RIGHT));
               panel.add(new JLabel("Password", SwingConstants.RIGHT));
               overal.add(panel, BorderLayout.WEST);
            
               JPanel panel2 = new JPanel(new GridLayout(0,1,2,2));
               JTextField username = new JTextField();
               panel2.add(username);
               JPasswordField password = new JPasswordField();
               panel2.add(password);
               overal.add(panel2, BorderLayout.CENTER);
               overal.setPreferredSize(new Dimension(200,50));
               
               int n = JOptionPane.showConfirmDialog(null,overal, "Sign-In", JOptionPane.OK_CANCEL_OPTION);
               String userSQL = "teste";
               String passSQL = "test2";
               
               users = username.getText();
               pass = password.getText();
               UserPermission userPerm = new UserPermission();
               boolean check = false;
               check = userPerm.login(users, pass);
               
               if(n == JOptionPane.OK_OPTION){
                  if(check == true){
                     String fname = userPerm.signedIn();
                     jmiSignIn.setText("Hello, " + fname);                   
                     jmiSignIn.setEnabled(false);
                  }
                  else{
                     JOptionPane.showMessageDialog(null, "Incorrect Username and/or Password", "Error", JOptionPane.ERROR_MESSAGE);
                  }
               }
            }
         });
      jmiAbout = new JMenuItem("About");
      jmiAbout.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent ae){
               jtaMainContent.setText("Welcome to Our program");
            }
         });       
      jmiHelp = new JMenuItem("Help");
      jmiHelp.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent ae){
               JPanel helpPane = new JPanel(new BorderLayout(5, 5));
               String stepsSignIn = "<html><b>Sign In</b><br>1) Click on 'Sign In' menu item on the menu bar. <br>" 
                  + "2) Enter email in the username field and password in the password field. <br>"
                  + "3) If everything was entered properly, you should be signed in.<br>If not, an "
                  + "error message will appear and you have to repeat step 1 again.</html>";
               String stepsSearch = "<html><b>Search</b><br>1) Click on the search field on the top right corner of the frame. <br>"
                  + "2) Enter keywords or author's name into the field. <br>"
                  + "3) Click 'Search' button right next to the field. <br>"
                  + "4) Your results should appear.</html>";
                 
               JLabel jlSignInSteps = new JLabel(stepsSignIn);
               JLabel jlSearchSteps = new JLabel(stepsSearch);
               
               helpPane.add(jlSignInSteps, BorderLayout.NORTH);
               helpPane.add(jlSearchSteps, BorderLayout.SOUTH);
               helpPane.setPreferredSize(new Dimension(600, 200));
               JOptionPane.showMessageDialog(null, helpPane, "Help", JOptionPane.PLAIN_MESSAGE);
            }
         });       
      jmiSearch = new JMenuItem("Search");
      jmiSearch.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent ae){
               String results = jtfSearchBox.getText();
               if(results.equals("")){
                  JOptionPane.showMessageDialog(null, "Please enter keyword(s) in the search field", "Search Error", JOptionPane.PLAIN_MESSAGE);   
               }
               else if(results != null){
                  SearchResults sr = new SearchResults();
                  ArrayList<String> array = new ArrayList<String>();
                  array = sr.getResults(results);
                  String test = array.get(0);
                  if(test.equals("none")){
                     jtaMainContent.setText("No Results Found!");
                  }
                  else{
                     jtaMainContent.setText(array.get(1) + "\n");
                     jtaMainContent.append(array.get(2) + "\n");
                     jtaMainContent.append(array.get(3));
                  }                  
               }
               
               //jtaMainContent.setText("Here are your search results");
            }
         });
      jtfSearchBox = new JTextField(20); 
      
      // Add object to JMenuBar
      topBar.add(Box.createHorizontalGlue());       
      topBar.add(jmiSignIn);
      topBar.add(jmiAbout); 
      topBar.add(jmiHelp); 
      topBar.add(jtfSearchBox); 
      topBar.add(jmiSearch); 
      
      
      /**********
       * CENTER *
       **********/
      String dlfrc = "Digital Library for Research Collaborations\n\n";
      //dlfrc.setFont(dlfrc.getFont().deriveFont(18.0f));
      // jtaMainContent = new JTextArea(25, 30); 
      jtaMainContent = new JTextArea(dlfrc + "Test: search working or not?");
      scrollPane = new JScrollPane(jtaMainContent); 
      
      //Set TextField Editable False
      jtaMainContent.setEditable(false);
           
      // Add object to JFrame
      add(scrollPane, BorderLayout.CENTER);
   
      /*********
       * SOUTH *
       *********/
      // Adding JButtons to JPanel called jpMain
      JPanel jpMain = new JPanel(); 
      
      jbUpdate = new JButton("Update"); 
      jbUpdate.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent ae){
            JPanel updates = new JPanel(new BorderLayout(5, 5));
            
            JPanel panelUp = new JPanel(new GridLayout(0,1,2,2));
            panelUp.add(new JLabel("Old Text", SwingConstants.RIGHT));
            panelUp.add(new JLabel("New Text", SwingConstants.RIGHT));
            updates.add(panelUp, BorderLayout.WEST);
            
            JPanel panelUp2 = new JPanel(new GridLayout(0,1,2,2));
            JTextField oldText = new JTextField();
            oldText.setText("This is the old research texts");
            panelUp2.add(oldText);
            JTextField newText = new JTextField();
            panelUp2.add(newText);
            updates.add(panelUp2, BorderLayout.CENTER);
            
            JOptionPane.showConfirmDialog(null,updates, "Update", JOptionPane.OK_CANCEL_OPTION);
            String updatedText = newText.getText();
            System.out.println(updatedText);
         }
      });
      jpMain.add(jbUpdate); 
      jbDelete = new JButton("Delete");
      jbDelete.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent ae){
            JPanel deletes = new JPanel(new BorderLayout(5, 5));
            
            JPanel panelDl = new JPanel(new GridLayout(0,1,2,2));
            panelDl.add(new JLabel("Delete", SwingConstants.RIGHT));
            deletes.add(panelDl, BorderLayout.WEST);
            
            JPanel panelDl2 = new JPanel(new GridLayout(0,1,2,2));
            JTextField delText = new JTextField();
            panelDl2.add(delText);
            deletes.add(panelDl2, BorderLayout.CENTER);
            
            JOptionPane.showConfirmDialog(null,deletes, "Delete", JOptionPane.OK_CANCEL_OPTION);
            String deletedText = delText.getText();
            System.out.println(deletedText);
         }
      });
      jpMain.add(jbDelete); 
      jbInsert = new JButton("Insert"); 
      jbInsert.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent ae){
            JPanel inserts = new JPanel(new BorderLayout(5, 5));
            
            JPanel panelIn = new JPanel(new GridLayout(0,1,2,2));
            panelIn.add(new JLabel("Add new Research", SwingConstants.RIGHT));
            inserts.add(panelIn, BorderLayout.WEST);
            
            JPanel panelIn2 = new JPanel(new GridLayout(0,1,2,2));
            JTextField insText = new JTextField();
            panelIn2.add(insText);
            inserts.add(panelIn2, BorderLayout.CENTER);
            
            JOptionPane.showConfirmDialog(null,inserts, "Delete", JOptionPane.OK_CANCEL_OPTION);
            String insertedText = insText.getText();
            System.out.println(insertedText);
         }
      });
      jpMain.add(jbInsert); 
      
      //Adding jpMain to JFrame
      add(jpMain, BorderLayout.SOUTH); 
      
      
      /********************************************************************
       * Set GUI property: title, window size, location, visibility, etc. *
       ********************************************************************/
      setTitle("Digital Library for Research Collobrations"); 
      setSize(600, 400); 
      //pack();
      setLocationRelativeTo( null );
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);  
   }
   
   // Test method for displaying server text onto the GUI
   public void displayTextTest()
   {
      
   }
}
