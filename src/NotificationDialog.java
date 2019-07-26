/* 
 * NotificationDialog
 * Creates an notification dialog that appears when player enters a new room, saves/loads game
 * @author Alyssa Gao, Joyce Huang, Julia Collins
 * June 14, 2018
 */

import java.util.List;
import javax.swing.JLabel;

public class NotificationDialog {
  
  // ==== VARIABLES ====
  
  private List<Notification> notifications;
  private int currentStep = -1; // used to keep track which specific notification is being displayed. 
  private int width; // width of notification dialog
  private int height; // height of notification dialog
  
  private JLabel notificationLabel = null;
  
  // ==== CONSTRUCTORS ====
  
  /**
   * Notification with default dimensions
   * @param notifications array list of all notifications
   * @param notificationLabel the notification label
   */
  public NotificationDialog(List<Notification> notifications, JLabel notificationLabel) {
    this(notifications, notificationLabel, 500, 50);
  }
  
  /**
   * Notification with custom dimensions
   * @param notifications array list of all notifications
   * @param notificationLabel the notification label
   * @param width width of notification
   * @param height height of notification
   */
  public NotificationDialog(List<Notification> notifications, JLabel notificationLabel, int width, int height) {
    this.notifications = notifications;
    this.width = width;
    this.height = height;
    this.notificationLabel = notificationLabel;
    this.notificationLabel.setBounds(EscapeRoom.location.x + (EscapeRoom.SCREEN_WIDTH - this.width) / 2, EscapeRoom.location.y + 50, this.width, this.height);
    new ProcessTimedEvent().start();
  }
  
 /* 
  * ProcessTimedEvent
  * Runs in the background
  * @author Alyssa Gao, Joyce Huang, Julia Collins
  * June 14, 2018
  */
  class ProcessTimedEvent extends Thread {
    @Override
    public void run() {
      notificationLabel.setVisible(true);
      while (currentStep < notifications.size()-1) {
        advanceToNextInteractionSteps();
        try {
          Thread.sleep(notifications.get(currentStep).getDisplayTime());
        } catch (InterruptedException e) {
        }
      }
      notificationLabel.setVisible(false);
    }
  }
  
  // ==== METHODS ====
  /**
   * advanceToNextInteractionSteps
   * If the notification has a proceeding message after the current one, this will allow it to advance to the next one
   */
  private void advanceToNextInteractionSteps() {
    if (this.currentStep < this.notifications.size() - 1) {
      this.currentStep++;
      this.notificationLabel.setText("  " + this.notifications.get(this.currentStep).getMessage());
      this.notificationLabel.repaint();
    }
  }
}