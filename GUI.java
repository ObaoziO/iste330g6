/**
* Group 6: Yuqing Guo, David Player, Ashraf Wan
* Final Project
* ISTE.330.01
* Instructor: Michael Floeser
**/

import java.awt.*;
import javax.swing.*;

// **description of class here**
public class GUI extends JFrame {
   private JMenu jmTest;
   private JMenuItem jmiTest;

   public GUI() {
      // Title of the window
      setTitle("Digital Library for Research Collaborations");
      
      // Title area
      JMenuBar jmbTest = new JMenuBar();
      jmTest = new JMenu("Test menu");
      jmiTest = new JMenuItem("Test item");
      
      jmTest.add(jmiTest);
      
      jmbTest.add(jmTest);
      add(jmbTest);
      
      setJMenuBar(jmbTest);
      
      // Set GUI window size, location, visibility, etc.
      setSize(800,550);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);
   }
}