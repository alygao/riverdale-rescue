/* 
 * Notifiation
 * Template for a notification
 * @author Alyssa Gao, Joyce Huang, Julia Collins
 * June 14, 2018
 */

import java.io.Serializable;

public class Notification implements Serializable {
  
  // ==== VARIABLES ====
  private String message;
  private long displayTime;
  
  // ==== CONSTRUCTORS ====
  /**
   * Takes in the message and the length of time for the notification to be shown
   * @param message the actual message to be displayed in the notificaiton dialog
   * @param displayTime the length of time for the message to be displayed for
   */
  public Notification(String message, long displayTime) {
    this.message = message;
    this.displayTime = displayTime;
  }
  
  // ==== METHODS ====
  
  /**
   * getMessage
   * Gets the message that is to be shown in the notification
   * @return the message
   */
  public String getMessage() {
    return message;
  }
  
  /**
   * getDisplayTime
   * Gets the length of time the notifcation will display for
   * @return the display time
   */
  public long getDisplayTime() {
    return displayTime;
  }
}