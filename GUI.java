/**
* Group 6: Yuqing Guo, David Player, Ashraf Wan
* Final Project
* ISTE.330.01
* Instructor: Michael Floeser
**/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// **description of class here**
public class GUI extends JFrame{
   private JTextArea jtaTextArea; 
   private JMenuItem jmAbout, jmSignIn, jmHelp, jmSearch; 
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
      JMenuBar topBar = new JMenuBar(); 
      setJMenuBar(topBar);
      
      // Create JMenu and other objects to add to the JMenuBar
      jmAbout = new JMenuItem("About");
      jmAbout.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent ae){
               jtaTextArea.setText("Welcome to Our program");
            }
         });
      jmSignIn = new JMenuItem("Sign In");
      jmSignIn.addActionListener(
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
               users = username.getText();
               pass = password.getText(); 
            }
         });       
      jmHelp = new JMenuItem("Help");
      jmHelp.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent ae){
               JOptionPane.showMessageDialog(null, "Do you need help", "Help", JOptionPane.PLAIN_MESSAGE);
            }
         });       
      jmSearch = new JMenuItem("Search");
      jmSearch.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent ae){
               jtaTextArea.setText("Here are your search results");
            }
         });
      jtfSearchBox = new JTextField(20); 
      
      //adding Buttons to JPanel called jpMain
      JPanel jpMain = new JPanel(); 
      
      jbUpdate = new JButton("Update"); 
      jpMain.add(jbUpdate); 
      jbDelete = new JButton("Delete"); 
      jpMain.add(jbDelete); 
      jbInsert = new JButton("Insert"); 
      jpMain.add(jbInsert); 
      
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
      
      //Adding jpMain to JFrame
      add(jpMain, BorderLayout.SOUTH); 
      
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
