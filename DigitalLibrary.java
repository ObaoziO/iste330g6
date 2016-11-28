import java.awt.*; 
import java.awt.event.*;
import javax.swing.*; 
import java.util.*;
import java.io.*; 

public class DigitalLibrary extends JFrame
{
   private JTextArea jtaTextArea; 
   private JMenu jmAbout, jmSignIn, jmHelp, jmSearch; 
   private JTextField jtfSearchBox; 
   private JScrollPane scrollPane; 
  
   public static void main(String [] args)
   {
      new DigitalLibrary(); 
   }
   public DigitalLibrary()
   {
      display();  
   }
   
   /*Display GuI*/ 
   public void display()
   {  
      JMenuBar topBar = new JMenuBar(); 
      setJMenuBar(topBar);
      
      jmAbout = new JMenu("About"); 
      topBar.add(jmAbout); 
      
      jmSignIn = new JMenu("Sign In"); 
      topBar.add(jmSignIn); 
      
      jmHelp = new JMenu("Help"); 
      topBar.add(jmHelp); 
      
      jtfSearchBox = new JTextField(20); 
      
      topBar.add(jtfSearchBox); 
      topBar.add(Box.createHorizontalGlue());
      jmSearch = new JMenu("Search");
      topBar.add(jmSearch); 
         
      jtaTextArea = new JTextArea(25, 30); 
      scrollPane = new JScrollPane(jtaTextArea); 
     
      add(scrollPane, BorderLayout.CENTER);
      
      setTitle("Digital Library for Research Collobrations"); 
      setSize(600, 600); 
      //pack();
      setLocationRelativeTo( null );
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);  
   
   }
}