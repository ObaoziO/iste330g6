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
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.border.LineBorder;

/**
   *GUI class extends the JFrame is the presentation layer for this program
*/
public class GUI extends JFrame
{
   private JTextArea jtaDLFRC, jtaMainContent; 
   private JMenuItem jmiHome, jmiSignIn, jmiHelp, jmiSearch; 
   private JTextField jtfSearchBox; 
   private JScrollPane scrollPane; 
   private JButton jbUpdate, jbDelete, jbInsert, jmiCollab; 
   private String users = null;
   private String pass = null;
   private String ids, titles, abstrs, citation, keywrd;
   private boolean signedIn = false;
   private JPanel jpMain =  new JPanel();
   private JTextPane jtpMainContent;
   private SimpleAttributeSet setStyle;
      
   // String for Home page
   private String title = "<html><h1>Digital Library for Research Collaborations (DLFRC)</h1></html>";
   private String body = "The Digital Library for Research Collaborations (DLFRC) is a database system created for easier access to research collaborations. Department faculty regularly engage in research and publish their results. They are often looking to work with other faculty and students, but it is difficult to keep track of what each person is working on. The DLFRC database application will address this problem. DLFRC allow users to search through the database and quickly find possible opportunities for collaboration on the researches.";
   
   /**
      *Constructor
      *@param none
      *@return none
   */   
   public GUI() 
   {
      displayGUI(); 
   }
   
   /**
      *Method to create the GUI
      *@param none
      *@return none
   */
   public void displayGUI()
   {  
      // Create JMenuBar to hold JMenuItems
      JMenuBar topBar = new JMenuBar(); 
      setJMenuBar(topBar);
      
      // Create JMenuItems and their actionListeners to add to the JMenuBar
      jmiCollab = new JButton("Collaborate");  //Create Collaborate button
      jmiSignIn = new JMenuItem("Sign In");  //Create Sign in Button
      //Add the ActionListener
      jmiSignIn.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent ae){
               //Create an overall JPanel that will go into the JOptionPane
               JPanel overal = new JPanel(new BorderLayout(5, 5));
               //Create a JPanel that will hold the Username & Password label
               JPanel panel = new JPanel(new GridLayout(0,1,2,2));
               panel.add(new JLabel("Username", SwingConstants.RIGHT)); //Create label for Username
               panel.add(new JLabel("Password", SwingConstants.RIGHT)); //Create label for Password
               overal.add(panel, BorderLayout.WEST);  //add panel into the WEST side of the overall panel
               //Create a JPanel that will hold the Username & Password labl
               JPanel panel2 = new JPanel(new GridLayout(0,1,2,2));
               JTextField username = new JTextField();   //Create JTextField for Username
               panel2.add(username);
               JPasswordField password = new JPasswordField(); //Create JTextField for Password
               panel2.add(password);
               overal.add(panel2, BorderLayout.CENTER); //Add panel into the CENTER side of the overall panel
               overal.setPreferredSize(new Dimension(200,50)); //Set Preferred Size so JOptionPane come out that size
               
               //Create JOptionPane to ask for Login
               int n = JOptionPane.showConfirmDialog(null,overal, "Sign-In", JOptionPane.OK_CANCEL_OPTION);
               //Get text from the textfields and attach it to their respective string
               users = username.getText();
               pass = password.getText();
               //Call the UserPermission Class to run login
               UserPermission userPerm = new UserPermission();
               boolean check = false;  //to check whether a user is successfully logged in
               check = userPerm.login(users, pass); //Login returns a true on success log in
               //If users click on the OK option on JOptionPane
               if(n == JOptionPane.OK_OPTION){
                  //if login method was successful
                  if(check == true){
                     String fname = userPerm.signedIn(); //get first name of logged in user
                     jmiSignIn.setText("Hello, " + fname);  //Set the text for Sign In JMenuItem
                     jmiSignIn.setEnabled(false);  //Disable the JMenuItem so users cannot click on it
                     jpMain.setVisible(true); //Set panel with the Add/Delete/Update button to visible
                     signedIn = true;  //keep track if a user is logged in
                     jbUpdate.setVisible(true); //set the Update visible in case it was not
                     jbDelete.setVisible(true); //set the Delete visible in case it was not
                     jbInsert.setVisible(true); //set the Add visible in case it was not
                  }
                  //if login was not successful
                  else{
                     //JOptionPane to print out the error
                     JOptionPane.showMessageDialog(null, "Incorrect Username and/or Password", "Error", JOptionPane.ERROR_MESSAGE);
                     signedIn = false; //keep track if a user is logged in
                  }

               }
            }
         });
         
 
      //Create JMenuItem for Home
      jmiHome  = new JMenuItem("Home");
      //Add an actionlistener to Home
      jmiHome.addActionListener(

         new ActionListener(){
            public void actionPerformed(ActionEvent ae){
               jtaMainContent.setText(body); //Set the JTextArea to the body string
               jmiCollab.setVisible(false);  //Set the Collaborate visibility to false
               jpMain.setVisible(false);  //Set the panel that holds the button visibility
            }//End of actionPerformed
         });//End of Home actionListener
      //Create JMenuItem for Help       
      jmiHelp = new JMenuItem("Help");
      //Add an actionListener for Help
      jmiHelp.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent ae){
               // Create string for Help menu content
               String helpSteps = "<html><h4>Sign In</h4>"
                  + "<p>1) Click on 'Sign In' menu item on the menu bar.</p>" 
                  + "<p>2) Enter email in the username field and password in the password field.</p>"
                  + "<p>3) If everything was entered properly, you should be signed in. If not, an "
                  + "error message will appear and you have to repeat step 1 again.</p>"
                  + "<h4>Search</h4>"
                  + "<p>1) Click on the search field on the top right corner of the frame.</p>"
                  + "<p>2) Enter keywords or author's name into the field.</p>"
                  + "<p>3) Click 'Search' button right next to the field.</p>"
                  + "<p>4) Your results should appear."
                  + "<h4>Add a research</h4>"
                  + "<p>1) You must to be signed in to have access to this.</p>"
                  + "<p>2) Once you are signed in, click the 'Add a research' button that appears on the bottom of the window.</p>"
                  + "<p>3) Fill in the text field with information. You must fill in the Title and Keyword field.</p>"
                  + "<p>4) Click 'OK' once you have finish adding all the information.</p>"
                  + "<h4>Delete a research</h4>"
                  + "<p>1) You must be signed in to have access to this.</p>"
                  + "<p>2) Once you are signed in, click the 'Delete Research' button that appears on the bottom of the window.</p>"
                  + "<p>3) A confirmation window will pop up.</p>"
                  + "<p>4) Click 'OK' if you are sure you want to delete that research.</p>"
                  + "<h4>Update a research</h4>"
                  + "<p>1) You must be signed in to have access to this.</p>"
                  + "<p>2) Once you are signed in, click the 'Update research' button that appears on the bottom of the window.</p>"
                  + "<p>3) A pop up window with the old research appears, and you can edit it straight in the text field.</p>"
                  + "<p>4) Once you are done with everything, click 'OK'.</p></html>";
               
               // Create new JPanel
               JPanel jpHelp = new JPanel();
               
               // Set Help menu content string as JLabel then add to the JPanel
               JLabel jlContent = new JLabel(helpSteps);
               jpHelp.add(jlContent, BorderLayout.CENTER);
               
               // Construct JPanel properties
               jpHelp.setLayout(new GridBagLayout());
               jpHelp.setBorder(LineBorder.createBlackLineBorder());
               
               // Consctruct JScrollPane
               scrollPane = new JScrollPane(jpHelp, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
               scrollPane.setPreferredSize(new Dimension(600, 200));
               
               // Set JPanel dimension then add JPanel to JOptionPane
               JOptionPane.showMessageDialog(null, scrollPane, "Help", JOptionPane.PLAIN_MESSAGE);
            }//End of actionPerformed
         });//End of actionListener HOME
      //Create JMenuItem for Search       
      jmiSearch = new JMenuItem("Search");
      //Create an ActionListener that can be used for Search JMenuItem and Search textField
      ActionListener action = 
         new ActionListener(){
            public void actionPerformed(ActionEvent ae){
               //Get text for Search textbox and attach it to a string
               String results = jtfSearchBox.getText();
               //If results is empty
               if(results.equals("")){
                  //JOptionPane error pops up
                  JOptionPane.showMessageDialog(null, "Please enter keyword(s) in the search field", "Search Error", JOptionPane.PLAIN_MESSAGE);   
               }
               //If results not null
               else if(results != null){
                  //Call the searchresults class
                  SearchResults sr = new SearchResults();
                  //Set results to another string
                  keywrd = results;
                  //Create an array to hold the search results
                  ArrayList<String> array = new ArrayList<String>();
                  array = sr.getResults(results);
                  //Create test string to test first array
                  String test = array.get(0);
                  //if test returns none, print no results
                  if(test.equals("none")){
                     jtaMainContent.setText("No Results Found!");
                     //if user is not signedIn, set the JPanel holding buttons to invisible
                     if(signedIn == false){
                        jpMain.setVisible(false);
                     }
                     //if user is signedIn, set the JPanel holding buttons to visible and collab button to invisible
                     else{
                        jpMain.setVisible(true);
                        jmiCollab.setVisible(false);
                     }
                  }
                  //if test returns something other than none
                  else{
                     ids = array.get(0);  //attach research ID to string ids
                     titles = array.get(1);  //attach research title to string titles
                     abstrs = array.get(2);  //attach research abstract to string abstrs
                     citation = array.get(3);   //attach research citation to string citation
                     jtaMainContent.setText("Title: \n" + titles + "\n\n");   //set titles in the jtextarea
                     jtaMainContent.append("Basic Summary: \n " + abstrs + "\n\n"); //append abstrs in the jtextarea
                     jtaMainContent.append("Citations: \n" + citation); //append citation in the jtextarea
                     jtaMainContent.setCaretPosition(0); //set the jtextarea to the top on load
                     jbUpdate.setEnabled(true); //enable the update button
                     jbDelete.setEnabled(true); //enable the delete button
                     
                     //ActionListener for Collaborate button
                     jmiCollab.addActionListener(
                        new ActionListener(){
                           public void actionPerformed(ActionEvent ae){
                              //Create overall panel to hold everything
                              JPanel collab = new JPanel(new BorderLayout(5, 5));
                              //Create panel to hold the message
                              JPanel panelCl0 = new JPanel(new GridLayout(1,1));
                              panelCl0.add(new JLabel("Enter your email below to notify researcher", SwingConstants.CENTER));
                              collab.add(panelCl0, BorderLayout.NORTH);
                              //Create panel to hold the label
                              JPanel panelCl = new JPanel(new GridLayout(0,1,2,2));
                              panelCl.add(new JLabel("Email:", SwingConstants.RIGHT));
                              collab.add(panelCl, BorderLayout.WEST);
                              //Create panel to hold the jtextfield
                              JPanel panelCl2 = new JPanel(new GridLayout(0,1,2,2));
                              JTextField colText = new JTextField();
                              panelCl2.add(colText);
                              collab.add(panelCl2, BorderLayout.CENTER);
                              collab.setPreferredSize(new Dimension(300, 50));   //set preferred size for the JOptionPane
                              //Create the JOptionPane to ask for potential collaborator
                              int n = JOptionPane.showConfirmDialog(null,collab, "Join Collaboration", JOptionPane.OK_CANCEL_OPTION);
                              String collabText = colText.getText(); //attach the text in the JTextField to string
                              //If users click on OK
                              if(n == JOptionPane.OK_OPTION){
                                 //if JTextField is empty
                                 if(collabText.equals("")){
                                    //JOptionPane Error ask enter email
                                    JOptionPane.showMessageDialog(null, "Please enter a valid email", "Error", JOptionPane.PLAIN_MESSAGE);
                                 }
                                 //if JTextField is not empty
                                 else{
                                    //Using Regex to check if valid email
                                    String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
                                    java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
                                    java.util.regex.Matcher m = p.matcher(collabText);
                                    boolean test = m.matches();
                                    
                                    if(test == true){
                                       //JOptionPane for success
                                       JOptionPane.showMessageDialog(null, "Researcher notified", "Success!", JOptionPane.PLAIN_MESSAGE);
                                    }
                                    else{
                                       //JOptionPane Error ask enter email
                                       JOptionPane.showMessageDialog(null, "Please enter a valid email", "Error", JOptionPane.PLAIN_MESSAGE);
                                    }                                                                        
                                 }
                              }
                           }//End of actionPerformed
                        });//End of actionListener
                     //Add Collab button to the JPanel holding the other buttons
                     jpMain.add(jmiCollab);
                     //if user is not logged in
                     if(signedIn == false){  
                        jpMain.setVisible(true);   //set JPanel holding button to visible
                        jbUpdate.setVisible(false);   //set Update button invisible
                        jbDelete.setVisible(false);   //set Delete button invisible
                        jbInsert.setVisible(false);   //set Add button invisible
                     }
                     //if user is logged in
                     else if(signedIn == true){
                        jpMain.setVisible(true);   //set JPanel holding button to visible
                        jbUpdate.setVisible(true); //set Update button visible
                        jbDelete.setVisible(true); //set Delete button visible
                        jmiCollab.setVisible(true);   //set Collab button visible
                        jbInsert.setVisible(true); //set Add button visible
                        jpMain.revalidate(); //refresh the JPanel holding button
                     }                                         
                  }                  
               }//end of actionPerformed
            }
         };//end of actionListener SEARCH
      //add actionListenr to JMenuItem Search
      jmiSearch.addActionListener(action);
      //create jtextfield for search
      jtfSearchBox = new JTextField(20);
      //add actionlistener to jtextfield search 
      jtfSearchBox.addActionListener(action);
      
      // Add Sign In, Home, Help, SearchBar, Search to JMenuBar
      topBar.add(Box.createHorizontalGlue()); 
      topBar.add(jmiHome);
      topBar.add(jmiHelp); 
      topBar.add(jtfSearchBox); 
      topBar.add(jmiSearch); 
      topBar.add(jmiSignIn);   
      
      //Calls method for the content
      homeContent();

      //Create JButton for Add research   
      jbInsert = new JButton("Add research");
      //add actionListener to Add Button 
      jbInsert.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent ae){
               //Create overall panel for JOptionPane for Add research
               JPanel inserts = new JPanel(new BorderLayout(5, 5));
               //Create panel for labels
               JPanel panelIn = new JPanel(new GridLayout(0,1,2,2));
               panelIn.add(new JLabel("Title", SwingConstants.RIGHT));
               panelIn.add(new JLabel("Abstract of Research", SwingConstants.RIGHT));
               panelIn.add(new JLabel("Citation", SwingConstants.RIGHT));
               panelIn.add(new JLabel("Keyword(s)", SwingConstants.RIGHT));
               inserts.add(panelIn, BorderLayout.WEST);
               //Create panel for textfields
               JPanel panelIn2 = new JPanel(new GridLayout(0,1,2,2));
               JTextField inTitle = new JTextField(); //Create JTextField for Title
               JTextField inAbstract = new JTextField(); //Create JTextField for Abstract
               JTextField inCitation = new JTextField(); //Create JTextField for Citation
               JTextField inKeyword = new JTextField();  //Create JTextField for Keyword
               panelIn2.add(inTitle);
               panelIn2.add(inAbstract);
               panelIn2.add(inCitation);
               panelIn2.add(inKeyword);
               inserts.add(panelIn2, BorderLayout.CENTER);
               inserts.setPreferredSize(new Dimension(500, 150)); //set preferred size for JOptionPane
               //Create JOptionPane for Add research
               int n = JOptionPane.showConfirmDialog(null,inserts, "Add a Research", JOptionPane.OK_CANCEL_OPTION);
               //Set the text in the JtextField to strings
               String title = inTitle.getText();
               String abstr = inAbstract.getText();
               String citat = inCitation.getText();
               String keyword = inKeyword.getText();
               //Sanitize those strings for security reasons
               String titleC = title.replaceAll("[^a-zA-Z0-9 ]","");
               String abstrC = abstr.replaceAll("[^a-zA-Z0-9 ]","");
               String citatC = citat.replaceAll("[^a-zA-Z0-9 ]","");
               String keywordC = keyword.replaceAll("[^a-zA-Z0-9]","");
               
               //If users click OK
               if(n == JOptionPane.OK_OPTION){
                  //If title is empty
                  if(title.equals("")){
                     //JOptionPane asking for valid title
                     JOptionPane.showMessageDialog(null, "Please enter a valid title","Error", JOptionPane.PLAIN_MESSAGE);
                  }
                  //If keyword is empty
                  else if(keyword.equals("")){
                     //JOptionPane asking for valid keywords
                     JOptionPane.showMessageDialog(null, "Please enter a keyword", "Error", JOptionPane.PLAIN_MESSAGE);
                  }
                  else{
                     ResearchAUD raud = new ResearchAUD();  //call for ResearchAUD class
                     //Call addResearch method
                     boolean s = raud.addResearch(titleC, abstrC, citatC, keywordC);
                     //check if addResearch was successful or not
                     if(s == true){
                        //JOptionPane pop up for success
                        JOptionPane.showMessageDialog(null, "Research was added successfully", "Success!", JOptionPane.PLAIN_MESSAGE);
                     }
                     else{
                        //JOptionPane pop up for failure
                        JOptionPane.showMessageDialog(null, "Research was not successfully added", "Fail!", JOptionPane.PLAIN_MESSAGE);
                     }
                  }
               }
            }//end of actionPerformed
         });//end of actionListener ADD RESEARCH
      jpMain.add(jbInsert);   //add Add Button to Button Panel
      //Create Update research Button
      jbUpdate = new JButton("Update research");
      //Add ActionListent to UPDATE 
      jbUpdate.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent ae){ 
               //create overall panel for Update JOptionPane       
               JPanel updates = new JPanel(new BorderLayout(5, 5));
               //create panel for labels
               JPanel panelUp = new JPanel(new GridLayout(0,1,2,2));
               panelUp.add(new JLabel("Title", SwingConstants.RIGHT));
               panelUp.add(new JLabel("Abstract of Research", SwingConstants.RIGHT));
               panelUp.add(new JLabel("Citation", SwingConstants.RIGHT));
               panelUp.add(new JLabel("Keyword(s)", SwingConstants.RIGHT));
               updates.add(panelUp, BorderLayout.WEST);
               //create panel for JTextField
               JPanel panelUp2 = new JPanel(new GridLayout(0,1,2,2));
               JTextField upTitle = new JTextField(); //Create Title text field
               upTitle.setText(titles);   //set Title to the current title string
               JTextField upAbstract = new JTextField(); //Create Abstract text field
               upAbstract.setText(abstrs);   //set Abstract to the current abstract string
               JTextField upCitation = new JTextField(); //Create Citation text field
               upCitation.setText(citation); //set Citation to the current citation string
               JTextField upKeyword = new JTextField(); //Create Keyword text field
               upKeyword.setText(keywrd); //set Keyword to the current keyword string
               //add text field to panel
               panelUp2.add(upTitle);
               panelUp2.add(upAbstract);
               panelUp2.add(upCitation);
               panelUp2.add(upKeyword);           
               updates.add(panelUp2, BorderLayout.CENTER);
               updates.setPreferredSize(new Dimension(500, 150)); //set preferred size for JOptionPane
               //Create JOptionPane for Update
               int n = JOptionPane.showConfirmDialog(null,updates, "Update a research", JOptionPane.OK_CANCEL_OPTION);
               //if users click on OK
               if(n == JOptionPane.OK_OPTION){
                  //grab the text from the text field an attach to strings
                  String title = upTitle.getText();
                  String abstr = upAbstract.getText();
                  String citations = upCitation.getText();
                  String keyword = upKeyword.getText();
                  //if title is empty
                  if(title.equals("")){
                     //Joptionpane error for missing title
                     JOptionPane.showMessageDialog(null, "Please enter a valid title", "Error", JOptionPane.PLAIN_MESSAGE);
                  }
                  else{
                     ResearchAUD raud = new ResearchAUD();  //Call ResearchAUD class
                     //call updateResearch method with the strings
                     boolean s = raud.updateResearch(ids, title, abstr, citations, keyword);
                     //if successful
                     if(s == true){
                        //JOptionPane for success
                        JOptionPane.showMessageDialog(null, "Research was edited successfully", "Success!", JOptionPane.PLAIN_MESSAGE);
                        jtaMainContent.setText(title);   //set JTextArea with new title
                        jtaMainContent.append(abstr);    //set Jtextarea with new abstract
                        jtaMainContent.append(citations);   //set JtextArea with new citations
                     }
                     //if failure
                     else{
                        //JOptionPane for failure
                        JOptionPane.showMessageDialog(null, "Research was not successfully edited", "Fail!", JOptionPane.PLAIN_MESSAGE);
                     }
                  
                  }
               }
            } //end of actionPerformed
         });//end of actionListener UPDATE
      jbUpdate.setEnabled(false);
      jpMain.add(jbUpdate); 
      //Create JButton for Delete Research
      jbDelete = new JButton("Delete research");
      //Create actionlistener for delete research
      jbDelete.addActionListener(
         new ActionListener(){
            public void actionPerformed(ActionEvent ae){
               //Create overall panel for JOptionPane
               JPanel deletes = new JPanel(new BorderLayout(5, 5));
               //create panel for message
               JPanel panelDl = new JPanel(new GridLayout(0,1,2,2));
               panelDl.add(new JLabel("Are you sure you want to delete this research?", SwingConstants.RIGHT));
               deletes.add(panelDl, BorderLayout.CENTER);
               deletes.setPreferredSize(new Dimension(300, 50)); //set preferred size for JOptionPane
               //Create JOptionPane asking for delete confirmation
               int n = JOptionPane.showConfirmDialog(null,deletes, "Delete research", JOptionPane.OK_CANCEL_OPTION);
               String deletedText = titles; //set title to string
               //If user click on OK
               if(n == JOptionPane.OK_OPTION){
                  //if title is empty
                  if(deletedText.equals("")){
                     //JOptionPane error research doesn't exist
                     JOptionPane.showMessageDialog(null, "Research doesn't exist","Error", JOptionPane.PLAIN_MESSAGE);
                  }
                  //if title not empty
                  else{
                     ResearchAUD raud = new ResearchAUD();  //Call ResearchAUD class
                     boolean s = raud.deleteResearch(deletedText);   //call deleteResearch method
                     //if successful
                     if(s == true){
                        //JOptionPane success message
                        JOptionPane.showMessageDialog(null, "Research was deleted successfully", "Success!", JOptionPane.PLAIN_MESSAGE);
                        jtaMainContent.setText("No Results Found!"); //set JTextArea to empty
                     }
                     else{
                        //JOptionPane failure message
                        JOptionPane.showMessageDialog(null, "Research was not successfully deleted", "Fail!", JOptionPane.PLAIN_MESSAGE);
                     }
                  }
               }
            }//end actionPerformed
         });//end actionListener DELETE
      jbDelete.setEnabled(false);
      jpMain.add(jbDelete); 
            
      //set Button Panel to invisble
      jpMain.setVisible(false);
      //Add Button Panel to JFrame
      add(jpMain, BorderLayout.SOUTH); 
      //Set JFrame properties
      setTitle("Digital Library for Research Collobrations (DLFRC)"); 
      setSize(660, 400); 
      setLocationRelativeTo( null );
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);  
   }//end of displayGUI
   

   // Display contents for the Home page

   /**
      *Method to create home contents
      *@param none
      *@return none
   */

   public void homeContent()
   {
      // Add text to JLabel and JTextArea
      JLabel jlTitle = new JLabel(title, SwingConstants.CENTER);      
      jtaMainContent = new JTextArea(body);
      
      // Set font properties for body string
      Font timesBody = new Font("Arial", Font.BOLD, 15);
      jtaMainContent.setFont(timesBody); 
      
      jtaMainContent.setLineWrap(true);
      jtaMainContent.setWrapStyleWord(true);
      jtaMainContent.setEditable(false); // Set TextField Editable False
   
      scrollPane = new JScrollPane(jtaMainContent); // Set jtaMainConent to be scrollable
      
      // Set background color of JFrame
      getContentPane().setBackground(Color.WHITE);
      
      // Add object to JFrame
      add(jlTitle, BorderLayout.NORTH);
      add(scrollPane, BorderLayout.CENTER);
      

   } //homeContent

  // } //end homeAndAboutText

   
} //end GUI
