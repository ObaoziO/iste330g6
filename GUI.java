/**
* Group 6: Yuqing Guo, David Player, Ashraf Wan
* Final Project
* ISTE.330.01
* Instructor: Michael Floeser
**/

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

// **description of class here**
public class GUI extends JFrame implements ActionListener
{
   private JTextArea jtaTextArea; 
   private JMenu jmAbout, jmSignIn, jmHelp; // , jmSearch; 
   private JButton jbSearch;
   private JTextField jtfSearchBox; 
   private JScrollPane scrollPane; 

   public GUI() 
   {
      displayGUI(); 
   }
   
   /*Display GUI*/ 
   public void displayGUI()
   {  
      JMenuBar topBar = new JMenuBar(); 
      setJMenuBar(topBar);
      
      // Create JMenu and other objects to add to the JMenuBar
      jmAbout = new JMenu("About"); 
      jmSignIn = new JMenu("Sign In"); 
      jmHelp = new JMenu("Help"); 
      // jmSearch = new JMenu("Search");
      jbSearch = new JButton("Search");
      
      
      jtfSearchBox = new JTextField(15);
      //jbSearch.addActionListener(this);
      
      // Add object to JMenuBar
      topBar.add(Box.createHorizontalGlue());
      topBar.add(jmAbout); 
      topBar.add(jmSignIn); 
      topBar.add(jmHelp); 
      topBar.add(jtfSearchBox); 
      topBar.add(jbSearch);
      // topBar.add(jmSearch); 
         
      // jtaTextArea = new JTextArea(25, 30); 
      jtaTextArea = new JTextArea("Test: search working or not?"); 
      scrollPane = new JScrollPane(jtaTextArea); 
      
      //Set TextField Editable False
      jtaTextArea.setEditable(false);
     
      // Add object to JFrame
      add(scrollPane, BorderLayout.CENTER);
      
      // Set GUI property: title, window size, location, visibility, etc.
      setTitle("Digital Library for Research Collobrations"); 
      setSize(600, 600); 
      //pack();
      setLocationRelativeTo( null );
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);  
   
   }
   
   // Test method for displaying server text onto the GUI
   public void displayTextTest()
   {
      
   }
   
   // Search the database to see if there are opportunities for collaboration using abstracts, keywords, etc.
   public void search(String input) { //throws FileNotFoundException 
      
   }
   

} // GUI class