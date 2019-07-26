/* 
 * InteractionDialog
 * Used for all dialogue that are associated with either an item, door, or the beginning storyline message
 * @author Alyssa Gao, Joyce Huang, Julia Collins
 * June 14, 2018
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InteractionDialog extends JDialog implements KeyListener {

 // ==== VARIABLES ====
 private List<InteractionStep> interactionSteps;
 private int currentStep = -1;

 private JLabel messageLabel = null;
 private JTextField answerBox = null;

 // ==== CONSTRUCTORS ====
 
  /**
   * creates array list of all dialogue associated to the item/door
   * @param interactionSteps array list of all dialogue associated to the item/door
   */
 public InteractionDialog(List<InteractionStep> interactionSteps) {
  this.interactionSteps = interactionSteps;
  if (this.interactionSteps != null) {
   for (int i = 0; i < interactionSteps.size(); i++) {
    interactionSteps.get(i).questionIsAnswered = false;
   }
   initGUI();
  }
 }

 // ==== METHODS ====
 
 /**
  * initGUI
  * Initializes the dialog box and answer box
  */
 public final void initGUI() {
  this.timestamp = new Date().getTime();
  setLayout(null);

  this.messageLabel = new JLabel();
  this.messageLabel.setBounds(0, 0, 610, 456);
  add(this.messageLabel);

  this.answerBox = new JTextField();
  answerBox.setBounds(20, 7, 200, 50);
  answerBox.setVisible(false);
  answerBox.setFont(new Font("Cambria", Font.BOLD, 22));
  answerBox.setBackground(new Color(51, 62, 80));
  answerBox.setForeground(new Color(255, 204, 1));
  answerBox.setBorder(BorderFactory.createEmptyBorder());
  add(this.answerBox);

  setModalityType(ModalityType.APPLICATION_MODAL);

  setTitle("Challenge");
  setDefaultCloseOperation(DISPOSE_ON_CLOSE);

  setResizable(false);
  setUndecorated(true);
  setLocation(EscapeRoom.location.x + (EscapeRoom.SCREEN_WIDTH - 600) / 2, EscapeRoom.location.y + 200);
  setSize(600, 456);
  this.messageLabel.addKeyListener(this);
  this.answerBox.addKeyListener(this);
  addKeyListener(this);
  advanceToNextInteractionSteps();
  setVisible(true);
 }

 long timestamp = 0;

 /**
  * keyTyped
  * Method that is included with the keyListener interface
  */
 public void keyTyped(KeyEvent e) {
 }

 /**
  * keyPressed
  * Method that is included with the keyListener interface
  */
 public void keyPressed(KeyEvent e) {
 }

 /**
  * keyReleased
  * Method that will follow through if a key is pressed
  */
 public void keyReleased(KeyEvent e) {
  if (new Date().getTime() - this.timestamp < 500) {
   return;
  }
  // 'esc' key is pressed to close dialog
  if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
   dispose();
  }

  // collecting answer from the previous step.
  if ((this.currentStep >= 0) && (this.interactionSteps.get(this.currentStep).requiresAnswer)) {
   // 'enter' key is pressed to submit answer
   if (e.getKeyCode() == KeyEvent.VK_ENTER) {
    this.interactionSteps.get(this.currentStep).inputAnswer = this.answerBox.getText();
    this.answerBox.setVisible(false);
    this.interactionSteps.get(this.currentStep).questionIsAnswered = true;
    if (this.currentStep == this.interactionSteps.size() - 1) {
     // if the requiresAnswer step is the last step, then close the dialog.
     dispose();
    }
   }

  }

  advanceToNextInteractionSteps();

 }

 /**
  * advanceToNextInteractionSteps
  * advances to following dialogue message
  */
 private void advanceToNextInteractionSteps() {
  if ((this.currentStep < 0) || (!this.interactionSteps.get(this.currentStep).requiresAnswer) || (this.interactionSteps.get(this.currentStep).questionIsAnswered)) {

   this.answerBox.setVisible(false);
   this.currentStep++;
   if (this.currentStep < this.interactionSteps.size()) {
    ImageIcon icon = new ImageIcon(this.interactionSteps.get(this.currentStep).messageImageFilename);
    this.messageLabel.setIcon(icon);
    this.messageLabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
    setSize(icon.getIconWidth(), icon.getIconHeight());
    int messageLabelX = this.interactionSteps.get(this.currentStep).x;
    int messageLabelY = this.interactionSteps.get(this.currentStep).y;
    if (messageLabelX == -1) { // default location for width
     messageLabelX = EscapeRoom.location.x + (EscapeRoom.SCREEN_WIDTH - icon.getIconWidth()) / 2 + 10;
    }
    if (messageLabelY == -1) { // default location for height
     messageLabelY = EscapeRoom.location.y + 20;
    }
    if (messageLabelY == -2) { // centres the dialog message
     messageLabelY = EscapeRoom.location.y + (EscapeRoom.SCREEN_HEIGHT - icon.getIconHeight()) / 2;
    }
    setLocation(messageLabelX, messageLabelY);

    if (this.interactionSteps.get(this.currentStep).requiresAnswer) {
     answerBox.setText("");
     answerBox.setVisible(true);
     answerBox.grabFocus();
    } else {
     this.messageLabel.grabFocus();
    }
    repaint();
   } else {
    dispose();
   }
  }
 }
}