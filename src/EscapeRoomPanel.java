/* 
 * EscapeRoomPanel
 * Creates the panel for escape room
 * @author Alyssa Gao, Joyce Huang, Julia Collins
 * June 14, 2018
 */

import java.awt.Graphics;
import javax.swing.JPanel;

public class EscapeRoomPanel extends JPanel {
  
  // ==== VARIABLES ====
  private Room room; // the current room
  private Player player;
  private Object[][] roomMap; // map with all objects within room
  
  // ==== CONSTRUCTORS ====
  
  /**
   *creates room map, room, and player
   * @param roomMap the map with all objects within room
   * @param room the current room
   * @param player the player (position, bag, etc.)
   */
  public EscapeRoomPanel(Object[][] roomMap, Room room, Player player) {
    this.room = room;
    this.player = player;
    this.roomMap = roomMap;
  }
  
  // ==== METHODS ====
  /**
   * paintComponent
   * Used to draw all objects in room
   * @param g A painting tool
   */
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    // Draw room map
    g.drawImage(room.getRoomImage(), 0, 0, getWidth(), getHeight(), null);
    
    // Cell measurements
    int cellWidth = (EscapeRoom.SCREEN_WIDTH) / this.room.getWidth();
    int cellHeight = (EscapeRoom.SCREEN_HEIGHT) / this.room.getHeight();
    
    for (int row = 0; row < roomMap.length; row++) {
      for (int col = 0; col < roomMap[row].length; col++) {
        if (roomMap[row][col] instanceof Player) {
          player.draw(g, cellWidth, cellHeight);
        }
      }
    }
  }
  
  /**
   * setRoom
   * Sets room as the specified room
   * @param room The specific room
   */
  public void setRoom(Room room) {
    this.room = room;
  }
  
  /**
   * setRoomMap
   * Sets room map as the specified room map
   * @param roomMap The specific room map
   */
  public void setRoomMap(Object[][] roomMap) {
    this.roomMap = roomMap;
  }
}