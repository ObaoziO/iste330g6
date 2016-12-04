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
   private JMenuItem jmiAbout, jmiSignIn, jmiHelp, jmiSearch, jmiCollab; 
   private JTextField jtfSearchBox; 
   private JScrollPane scrollPane; 
   private JButton jbUpdate, jbDelete, jbInsert; 
   String users = null;
   String pass = null;
   String ids, titles, abstrs, citation, keywrd;
   boolean signedIn = false;
   JPanel jpMain =  new JPanel();
   
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
                      //I set these buttons' setVisible to be true
                     jpMain.setVisible(true); 
                     signedIn = true;
                  }
                  else{
                     JOptionPane.showMessageDialog(null, "Incorrect Username and/or Password", "Error", JOptionPane.ERROR_MESSAGE);
                     signedIn = false;
                  }
               }
            }
         });
      jmiAbout = new JMenuItem("About");
      jmiAbout.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent ae){
               jtaMainContent.setText("Welcome to Our Digital Library");
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
      ActionListener action = new ActionListener(){
            public void actionPerformed(ActionEvent ae){
               String results = jtfSearchBox.getText();
               if(results.equals("")){
                  JOptionPane.showMessageDialog(null, "Please enter keyword(s) in the search field", "Search Error", JOptionPane.PLAIN_MESSAGE);   
               }
               else if(results != null){
                  SearchResults sr = new SearchResults();
                  keywrd = results;
                  ArrayList<String> array = new ArrayList<String>();
                  array = sr.getResults(results);
                  String test = array.get(0);
                  if(test.equals("none")){
                     jtaMainContent.setText("No Results Found!");
                  }
                  else{
                     ids = array.get(0);
                     titles = array.get(1);
                     abstrs = array.get(2);
                     citation = array.get(3);
                     jtaMainContent.setText("Title: \n" + titles + "\n\n");
                     jtaMainContent.append("Basic Summary: \n " + abstrs + "\n\n");
                     jtaMainContent.append("Citations: \n" + citation);
                     jbUpdate.setEnabled(true);
                     jbDelete.setEnabled(true);
                     jmiCollab = new JMenuItem("Join Collaboration");
                     topBar.add(jmiCollab);
                     getContentPane().revalidate();
                     jmiCollab.addActionListener(
                        new ActionListener(){
                           public void actionPerformed(ActionEvent ae){
                              JPanel collab = new JPanel(new BorderLayout(5, 5));
                              
                              JPanel panelCl0 = new JPanel(new GridLayout(1,1));
                              panelCl0.add(new JLabel("Enter your email below to notify researcher", SwingConstants.CENTER));
                              collab.add(panelCl0, BorderLayout.NORTH);
                              
                              JPanel panelCl = new JPanel(new GridLayout(0,1,2,2));
                              panelCl.add(new JLabel("Email:", SwingConstants.RIGHT));
                              collab.add(panelCl, BorderLayout.WEST);
                              
                              JPanel panelCl2 = new JPanel(new GridLayout(0,1,2,2));
                              JTextField colText = new JTextField();
                              panelCl2.add(colText);
                              collab.add(panelCl2, BorderLayout.CENTER);
                              collab.setPreferredSize(new Dimension(300, 25));
                              int n = JOptionPane.showConfirmDialog(null,collab, "Join Collaboration", JOptionPane.OK_CANCEL_OPTION);
                              String collabText = colText.getText();
                              if(n == JOptionPane.OK_OPTION){
                                 if(collabText.equals("")){
                                    JOptionPane.showMessageDialog(null, "Please enter a valid email", "Error", JOptionPane.PLAIN_MESSAGE);
                                 }
                                 else{
                                    JOptionPane.showMessageDialog(null, "Researcher notified", "Success!", JOptionPane.PLAIN_MESSAGE);
                                 }
                              }
                           }
                        });
                  }                  
               }
            }
         };

      jmiSearch.addActionListener(action);
      jtfSearchBox = new JTextField(20); 
      jtfSearchBox.addActionListener(action);
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
      Font tr = new Font("TimesRoman", Font.BOLD, 20);
      //dlfrc.setFont(dlfrc.getFont().deriveFont(18.0f));
      // jtaMainContent = new JTextArea(25, 30); 
      jtaMainContent = new JTextArea(dlfrc + "Test: search working or not?");
      jtaMainContent.setFont(tr); 
      jtaMainContent.setLineWrap(true);
      jtaMainContent.setWrapStyleWord(true);
      scrollPane = new JScrollPane(jtaMainContent); 
      
      //Set TextField Editable False
      jtaMainContent.setEditable(false);
           
      // Add object to JFrame
      add(scrollPane, BorderLayout.CENTER);
   
      /*********
       * SOUTH *
       *********/
      // Adding JButtons to JPanel called jpMain
     // JPanel jpMain = new JPanel(); 
      
      jbInsert = new JButton("Add a research"); 
      jbInsert.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent ae){
            JPanel inserts = new JPanel(new BorderLayout(5, 5));
            
            JPanel panelIn = new JPanel(new GridLayout(0,1,2,2));
            panelIn.add(new JLabel("Title", SwingConstants.RIGHT));
            panelIn.add(new JLabel("Abstract of Research", SwingConstants.RIGHT));
            panelIn.add(new JLabel("Citation", SwingConstants.RIGHT));
            panelIn.add(new JLabel("Keyword(s)", SwingConstants.RIGHT));
            inserts.add(panelIn, BorderLayout.WEST);
            
            JPanel panelIn2 = new JPanel(new GridLayout(0,1,2,2));
            JTextField inTitle = new JTextField();
            JTextField inAbstract = new JTextField();
            JTextField inCitation = new JTextField();
            JTextField inKeyword = new JTextField();
            panelIn2.add(inTitle);
            panelIn2.add(inAbstract);
            panelIn2.add(inCitation);
            panelIn2.add(inKeyword);
            inserts.add(panelIn2, BorderLayout.CENTER);
            inserts.setPreferredSize(new Dimension(500, 150));
            
            int n = JOptionPane.showConfirmDialog(null,inserts, "Add a Research", JOptionPane.OK_CANCEL_OPTION);
            String title = inTitle.getText();
            String abstr = inAbstract.getText();
            String citat = inCitation.getText();
            String keyword = inKeyword.getText();
            
            String titleC = title.replaceAll("[^a-zA-Z0-9 ]","");
            String abstrC = abstr.replaceAll("[^a-zA-Z0-9 ]","");
            String citatC = citat.replaceAll("[^a-zA-Z0-9 ]","");
            String keywordC = keyword.replaceAll("[^a-zA-Z0-9]","");
            System.out.println(titleC + abstrC);
            if(n == JOptionPane.OK_OPTION){
               if(title.equals("")){
                  System.out.println(title);
                  JOptionPane.showMessageDialog(null, "Please enter a valid title","Error", JOptionPane.PLAIN_MESSAGE);
               }
               else if(keyword.equals("")){
                  JOptionPane.showMessageDialog(null, "Please enter a keyword", "Error", JOptionPane.PLAIN_MESSAGE);
               }
               else{
                  ResearchAUD raud = new ResearchAUD();
                  boolean s = raud.addResearch(titleC, abstrC, citatC, keywordC);
                  if(s == true){
                     JOptionPane.showMessageDialog(null, "Research was added successfully", "Success!", JOptionPane.PLAIN_MESSAGE);
                  }
                  else{
                     JOptionPane.showMessageDialog(null, "Research was not successfully added", "Fail!", JOptionPane.PLAIN_MESSAGE);
                  }
               }
            }
         }
      });
      jpMain.add(jbInsert);
      jbUpdate = new JButton("Update research"); 
      jbUpdate.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent ae){        
            JPanel updates = new JPanel(new BorderLayout(5, 5));
            
            JPanel panelUp = new JPanel(new GridLayout(0,1,2,2));
            panelUp.add(new JLabel("Title", SwingConstants.RIGHT));
            panelUp.add(new JLabel("Abstract of Research", SwingConstants.RIGHT));
            panelUp.add(new JLabel("Citation", SwingConstants.RIGHT));
            panelUp.add(new JLabel("Keyword(s)", SwingConstants.RIGHT));
            updates.add(panelUp, BorderLayout.WEST);

            JPanel panelUp2 = new JPanel(new GridLayout(0,1,2,2));
            JTextField upTitle = new JTextField();
            upTitle.setText(titles);
            JTextField upAbstract = new JTextField();
            upAbstract.setText(abstrs);
            JTextField upCitation = new JTextField();
            upCitation.setText(citation);
            JTextField upKeyword = new JTextField();
            upKeyword.setText(keywrd);
            
            panelUp2.add(upTitle);
            panelUp2.add(upAbstract);
            panelUp2.add(upCitation);
            panelUp2.add(upKeyword);
            
            updates.add(panelUp2, BorderLayout.CENTER);
            updates.setPreferredSize(new Dimension(500, 150));
            
            int n = JOptionPane.showConfirmDialog(null,updates, "Update a research", JOptionPane.OK_CANCEL_OPTION);
            if(n == JOptionPane.OK_OPTION){
               String title = upTitle.getText();
               String abstr = upAbstract.getText();
               String citations = upCitation.getText();
               String keyword = upKeyword.getText();
               if(title.equals("")){
                  JOptionPane.showMessageDialog(null, "Please enter a valid title", "Error", JOptionPane.PLAIN_MESSAGE);
               }
               else{
                  ResearchAUD raud = new ResearchAUD();
                  boolean s = raud.updateResearch(ids, title, abstr, citations, keyword);
                  if(s == true){
                     JOptionPane.showMessageDialog(null, "Research was edited successfully", "Success!", JOptionPane.PLAIN_MESSAGE);
                     jtaMainContent.setText(title);
                     jtaMainContent.append(abstr);
                     jtaMainContent.append(citations);
                  }
                  else{
                     JOptionPane.showMessageDialog(null, "Research was not successfully edited", "Fail!", JOptionPane.PLAIN_MESSAGE);
                  }

               }
            }
         }
      });
      jbUpdate.setEnabled(false);
      jpMain.add(jbUpdate); 
      jbDelete = new JButton("Delete a research");
      jbDelete.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent ae){
            JPanel deletes = new JPanel(new BorderLayout(5, 5));
            
            JPanel panelDl = new JPanel(new GridLayout(0,1,2,2));
            panelDl.add(new JLabel("Title of Research", SwingConstants.RIGHT));
            deletes.add(panelDl, BorderLayout.WEST);
            
            JPanel panelDl2 = new JPanel(new GridLayout(0,1,2,2));
            JTextField delText = new JTextField();
            panelDl2.add(delText);
            deletes.add(panelDl2, BorderLayout.CENTER);
            deletes.setPreferredSize(new Dimension(300, 50));
            int n = JOptionPane.showConfirmDialog(null,deletes, "Delete a research", JOptionPane.OK_CANCEL_OPTION);
            String deletedText = delText.getText();
            if(n == JOptionPane.OK_OPTION){
               if(deletedText.equals("")){
                  JOptionPane.showMessageDialog(null, "Please enter a valid title","Error", JOptionPane.PLAIN_MESSAGE);
               }
               else{
                  ResearchAUD raud = new ResearchAUD();
                  boolean s = raud.deleteResearch(deletedText);
                  if(s == true){
                     JOptionPane.showMessageDialog(null, "Research was deleted successfully", "Success!", JOptionPane.PLAIN_MESSAGE);
                     jtaMainContent.setText("");
                  }
                  else{
                     JOptionPane.showMessageDialog(null, "Research was not successfully deleted", "Fail!", JOptionPane.PLAIN_MESSAGE);
                  }
               }
            }
         }
      });
      jbDelete.setEnabled(false);
      jpMain.add(jbDelete); 
            
      //I set jpMain panel's setVisible to be false
      jpMain.setVisible(false);
      //Adding jpMain to JFrame
      add(jpMain, BorderLayout.SOUTH); 
      
            
      /********************************************************************
       * Set GUI property: title, window size, location, visibility, etc. *
       ********************************************************************/
      setTitle("Digital Library for Research Collobrations (DLFRC)"); 
      setSize(660, 400); 
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
