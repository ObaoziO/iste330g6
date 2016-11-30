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
      jmiAbout = new JMenuItem("About");
      jmiAbout.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent ae){
               jtaMainContent.setText("Welcome to Our program");
            }
         });
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
               JTextField password = new JTextField();
               panel2.add(password);
               overal.add(panel2, BorderLayout.CENTER);
            
               JOptionPane.showConfirmDialog(null,overal, "Sign-In", JOptionPane.OK_CANCEL_OPTION);
               String userSQL = "teste";
               String passSQL = "test2";
               
               users = username.getText();
               pass = password.getText();
               if(users.equals(userSQL)){
                  if(pass.equals(passSQL)){
                     jtaMainContent.setText("You are signed in");
                  }
                  else{
                     JOptionPane.showMessageDialog(null, "Incorrect Username and/or Password", "Error", JOptionPane.ERROR_MESSAGE);
                  }
               }
               else{
                  JOptionPane.showMessageDialog(null, "Incorrect Username and/or Password", "Error", JOptionPane.ERROR_MESSAGE);
               } 
            }
         });       
      jmiHelp = new JMenuItem("Help");
      jmiHelp.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent ae){
               JOptionPane.showMessageDialog(null, "Do you need help", "Help", JOptionPane.PLAIN_MESSAGE);
            }
         });       
      jmiSearch = new JMenuItem("Search");
      jmiSearch.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent ae){
               jtaMainContent.setText("Here are your search results");
            }
         });
      jtfSearchBox = new JTextField(20); 
      
      // Add object to JMenuBar
      topBar.add(Box.createHorizontalGlue());
      topBar.add(jmiAbout); 
      topBar.add(jmiSignIn); 
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
      jpMain.add(jbUpdate); 
      jbDelete = new JButton("Delete"); 
      jpMain.add(jbDelete); 
      jbInsert = new JButton("Insert"); 
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
