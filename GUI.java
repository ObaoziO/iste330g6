/**
* Group 6: Yuqing Guo, David Player, Ashraf Wan
* Final Project
* ISTE.330.01
* Instructor: Michael Floeser
**/

import java.awt.*;
import javax.swing.*;

// **description of class here**
public class GUI extends JFrame 
{
   private JTextArea jtaTextArea; 
   private JMenu jmAbout, jmSignIn, jmHelp, jmSearch; 
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
      jmSearch = new JMenu("Search");
      
      jtfSearchBox = new JTextField(20); 
      
      // Add object to JMenuBar
      topBar.add(Box.createHorizontalGlue());
      topBar.add(jmAbout); 
      topBar.add(jmSignIn); 
      topBar.add(jmHelp); 
      topBar.add(jtfSearchBox); 
      topBar.add(jmSearch); 
         
      jtaTextArea = new JTextArea(25, 30); 
      scrollPane = new JScrollPane(jtaTextArea); 
     
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

}