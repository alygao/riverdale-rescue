/* 
 * Player
 * Template to create the player
 * @author Alyssa Gao, Joyce Huang, Julia Collins
 * June 14, 2018
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;
import java.util.HashSet;
import java.util.Set;

public class Player implements Serializable{
  
  // ==== VARIABLES ====
  
  //position of player
  private int positionX;
  private int positionY;
  
  //pictures for all different directions
  private static BufferedImage playerPic = null; // current player direction that is being displayed
  private static BufferedImage playerPicLeft = null;
  private static BufferedImage playerPicRight = null;
  private static BufferedImage playerPicUp = null;
  private static BufferedImage playerPicDown = null;
  
  private Set<Task> bag = new HashSet<Task>(); // bag of completed tasks
  
  // integer values to represent each direction
  public static final int DIRECTION_UP = 1;
  public static final int DIRECTION_DOWN = 2;
  public static final int DIRECTION_LEFT = 3;
  public static final int DIRECTION_RIGHT = 4;
  
  // ==== CONSTRUCTORS ====
 
  /**
   * @param positionX player's position's x-coordinate
   * @param positionY player's position's y-coordinate
   * @param picNameLeft picture when player faces left
   * @param picNameRight picture when player faces right
   * @param picNameUp picture when player faces up
   * @param picNameDown picture when player faces down
   */
  public Player(int positionX, int positionY, String picNameLeft, String picNameRight, String picNameUp, String picNameDown) {
    this.positionX = positionX;
    this.positionY = positionY;
    try {
      playerPicLeft = ImageIO.read(new File(picNameLeft));
      playerPicRight = ImageIO.read(new File(picNameRight));
      playerPicUp = ImageIO.read(new File(picNameUp));
      playerPicDown = ImageIO.read(new File(picNameDown));
      playerPic = playerPicUp; // player begins facing upwards
    } catch (IOException e) {
      e.printStackTrace(System.err);
    }
  }
  
  // ==== METHODS ====
  /**
   * getPositionX
   * gets x-coordinate of player
   * @return x-coordinate player's position's x-coordinate
   */
  public int getPositionX() {
    return positionX;
  }
   
  /**
   * setPositionX
   * sets x-coordinate of player
   * @param positionX the new player x-coordinate
   */
  public void setPositionX(int positionX) {
    this.positionX = positionX;
  }
  
  /**
   * getPositionY
   * gets y-coordinate of player
   * @return y-coordinate player's position's y-coordinate
   */
  public int getPositionY() {
    return positionY;
  }
  
  /**
   * setPositionY
   * sets y-coordinate of player
   * @param positionY the new player y-coordinate
   */
  public void setPositionY(int positionY) {
    this.positionY = positionY;
  }
  
  /**
   * setPosition
   * sets position of player
   * @param position the new player position
   */
  public void setPosition ( Position position ) {
    this.positionX = position.positionX;
    this.positionY = position.positionY;
  }
  
  /**
   * getPosition
   * gets position of player
   * @return position (x-coordinate and y-coordinate)
   */
  public Position getPosition ( ) {
    return new Position(this.positionX, this.positionY);
  }
  
  /**
   * getBag
   * gets bag with completed tasks
   * @return bag with completed tasks
   */
  public Set<Task> getBag() {
    return bag;
  }
  
  /**
   * draw
   * draws the player image in the cell
   * @param g used for graphics
   * @param cellWidth width of the cell that the player image is to be drawn in
   * @param cellHeight height of the cell that the player image is to be drawn in
   */
  public void draw(Graphics g, int cellWidth, int cellHeight) {
    g.drawImage(playerPic, this.getPositionX() * cellWidth, this.getPositionY() * cellHeight, cellWidth, cellHeight, null);
  }
  
  /**
   * changeDirection
   * changes the direction image of the player
   * @param direction the direction number that represents which direction the player is to face
   */
  public void changeDirection ( int direction ) {
    if ( direction == DIRECTION_LEFT ) {
      playerPic = playerPicLeft;
    } else if ( direction == DIRECTION_RIGHT ) {
      playerPic = playerPicRight;
    } else if ( direction == DIRECTION_UP ) {
      playerPic = playerPicUp;
    } else if ( direction == DIRECTION_DOWN ) {
      playerPic = playerPicDown;
    }
  }
}