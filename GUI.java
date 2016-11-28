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
      display(); 
   }
   
   /*Display GUI*/ 
   public void display()
   {  
      JMenuBar topBar = new JMenuBar(); 
      setJMenuBar(topBar);
      
      // Create JMenu object
      jmAbout = new JMenu("About"); 
      topBar.add(jmAbout); 
      
      jmSignIn = new JMenu("Sign In"); 
      topBar.add(jmSignIn); 
      
      jmHelp = new JMenu("Help"); 
      topBar.add(jmHelp); 
      
      jtfSearchBox = new JTextField(20); 
      
      // Add object to JMenuBar
      topBar.add(jtfSearchBox); 
      topBar.add(Box.createHorizontalGlue());
      jmSearch = new JMenu("Search");
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

}