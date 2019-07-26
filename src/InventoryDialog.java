/* 
 * InventoryDialog
 * Dialog used to display inventory
 * @author Alyssa Gao, Joyce Huang, Julia Collins
 * June 14, 2018
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class InventoryDialog extends JDialog implements KeyListener {
  
  // ==== VARIABKES ====
  private Set<Task> tasks; // set of all completed tasks
  
  // ==== CONSTRUCTORS ====
  
  /**
   * creates array list of all dialogue associated to the item/door
   * @tasks interactionSteps set of all completed tasks
   */
  public InventoryDialog(Set<Task> tasks) {
    this.tasks = tasks;
    initGUI();
  }
  
  /**
   * initGUI
   * initializes the inventory dialog using 3 panels
   */
  public final void initGUI() {
    
    JPanel topPanel = new JPanel ( );
    getContentPane().add(topPanel, BorderLayout.NORTH);
    JLabel titleLabel = new JLabel ( "INVENTORY" ); // inventory title 
    titleLabel.setFont( new Font ( "Algerian", Font.BOLD, 40 ) ); // inventory font 
    topPanel.setBackground( new Color ( 51, 62, 80 )); 
    titleLabel.setForeground( new Color ( 200, 200, 50 ) );
    topPanel.add ( titleLabel ); // 'INVENTORY' will appear at the top of the entire inventory dialog
    
    JPanel bottomPanel = new JPanel ( );
    getContentPane().add(bottomPanel, BorderLayout.SOUTH);
    JLabel bottomLabel = new JLabel ( "Press ESC key to close" );
    bottomLabel.setFont( new Font ( "Cambria", Font.BOLD, 15 ) );
    bottomPanel.setBackground( new Color ( 51, 62, 80 ));
    bottomLabel.setForeground( new Color ( 200, 200, 50 ) );
    bottomPanel.add ( bottomLabel ); // 'Press ESC key to close' will appear at the bottom of the entire inventory dialog
    
    JPanel mainPanel = new JPanel ( );
    JScrollPane mainPane = new JScrollPane();
    mainPane.getViewport().add(mainPanel);
    getContentPane().add( mainPane );
    mainPanel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
    mainPanel.setLayout(new GridLayout(5, 2, 5, 5));
    mainPanel.setBackground( new Color ( 71, 82, 100 ));
    
    // Inventory Dialoge Appearance
    if ( this.tasks.size() == 0 ) {
      // If there are no completed tasks in bag
      JLabel noTaskNameLabel = new JLabel ( "Your inventory is currently empty. Go look for objects." );
      noTaskNameLabel.setForeground( new Color ( 200, 200, 50 ) );
      noTaskNameLabel.setFont( new Font ( "Cambria", Font.PLAIN, 15 ) );
      mainPanel.add ( noTaskNameLabel );
    } else {
      for ( Task task : tasks ) {
        // only will display completed retireval tasks in inventory
        if (task instanceof RetrievalTask){
          JPanel taskPanel = new JPanel ( ); // makes a panel for each task
          taskPanel.setLayout( new FlowLayout() );
          ImageIcon taskImageIcon = new ImageIcon( task.getIconImageFilename() ); 
          JLabel taskLabel = new JLabel ( taskImageIcon ); // display image of item(s) in inventory
          taskPanel.add ( taskLabel );
          JLabel taskNameLabel = new JLabel ( task.getName() );
          taskPanel.setBackground ( new Color ( 51, 62, 80 ) );
          taskPanel.setAlignmentX(LEFT_ALIGNMENT);
          taskNameLabel.setForeground( new Color ( 200, 200, 50 ) );
          taskNameLabel.setFont( new Font ( "Cambria", Font.PLAIN, 15 ) ); // task descriptions
          taskPanel.add ( taskNameLabel );
          mainPanel.add ( taskPanel );
        }
      }
    }
    
    // Inventory Dialog Features
    setModalityType(ModalityType.APPLICATION_MODAL);
    setTitle("Inventory");
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setResizable(false);
    setUndecorated(true);
    setLocation(EscapeRoom.location.x + (EscapeRoom.SCREEN_WIDTH - 900) / 2, EscapeRoom.location.y + 100);
    setSize(900, 500);
    addKeyListener(this);
    setVisible(true);
  }
  
  long timestamp = 0;
  
  /**
   * keyTyped
   * Checks if key is typed
   * @param e key event
   */
  public void keyTyped(KeyEvent e) {
  }
  
  /**
   * keyPressed
   * Checks if key is pressed
   * @param e key event
   */
  public void keyPressed(KeyEvent e) {
  }
  
  /**
   * keyReleased
   * Overriden method used to see if 'esc' key is released
   * Disposes the inventory dialog if key is released.
   * @param e key event
   */
  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
      dispose();
    }
  }
}
