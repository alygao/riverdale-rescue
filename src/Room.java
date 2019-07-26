/* 
 * Room
 * Template for a room
 * @author Alyssa Gao, Joyce Huang, Julia Collins
 * June 14, 2018
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Room implements Serializable {

 // ==== VARIABLES ====
 private String name;
 private List<Item> items = new ArrayList<Item>(); // have an array of objects in each room
 private List<Door> doors = new ArrayList<Door>();
 private int height;
 private int width;

 
 private String imageFilename;
 private transient BufferedImage roomImage;
 
 // the four corners of the room
 private Position topLeftCorner;
 private Position topRightCorner;
 private Position bottomLeftCorner;
 private Position bottomRightCorner;

 // ==== CONSTRUCTORS ====
 
 /**
  * @param name name of the room
  * @param height height of the room (length)
  * @param width width of the room
  * @param imageFilename file name for the room image
  */
 public Room(String name, int height, int width, String imageFilename) {
  this.name = name;
  this.height = height;
  this.width = width;
  this.imageFilename = imageFilename;
  loadRoomImage();
 }
 
 /**
  * @param name name of the room
  * @param height height of the room (length)
  * @param width width of the room
  * @param imageFilename file name for the room image
  * @param topLeftCorner top left corner of room
  * @param topRightCorner top right corner of room
  * @param bottomLeftCorner bottom left corner of room
  * @param bottomRightCorner bottom right corner of room
  */
  public Room(String name,
              int height, 
              int width, 
              String imageFilename, 
              Position topLeftCorner, 
              Position topRightCorner, 
              Position bottomLeftCorner, 
              Position bottomRightCorner ) {
    
  this.name = name;
  this.height = height;
  this.width = width;
  this.imageFilename = imageFilename;
  loadRoomImage();
  this.topLeftCorner = topLeftCorner;
  this.topRightCorner = topRightCorner;
  this.bottomLeftCorner = bottomLeftCorner;
  this.bottomRightCorner = bottomRightCorner;
 }
 
  // ==== METHODS ====
  
  /**
   * getTopLeftCorner
   * Gets the top left corner
   * @return the position of the top left corner
   */
  public Position getTopLeftCorner (){
    return topLeftCorner;
  }

  /**
   * getTopRightCorner
   * Gets the top right corner
   * @return the position of the top right corner
   */
  public Position getTopRightCorner (){
    return topRightCorner;
  }

  /**
   * getBottomLeftCorner
   * Gets the bottom left corner
   * @return the position of the bottom left corner
   */
  public Position getBottomLeftCorner (){
    return bottomLeftCorner;
  }
  
  /**
   * getBottomRightCorner
   * Gets the bottom right corner
   * @return the position of the bottom right corner
   */
   public Position getBottomRightCorner (){
    return bottomRightCorner;
  }
 
 /**
   * loadRoomImage
   * Reads and loads the image of the room
   */
 public void loadRoomImage ( ) {
  try {
   this.roomImage = ImageIO.read(new File(imageFilename));
  } catch (IOException e) {
   e.printStackTrace(System.err);
  }
 }
 
 /**
   * getRoomImage
   * Gets the room image
   * @return the room image
   */
 public BufferedImage getRoomImage() {
  return roomImage;
 }
 
 /**
   * getItems
   * Gets all the items within the room
   * @return the arraylist of tiems
   */
 public List<Item> getItems() {
  return items;
 }
 
 /**
   * getDoors
   * Gets all the doors within the room
   * @return the arraylist of doors
   */
 public List<Door> getDoors() {
  return doors;
 }
 
 /**
   * getHeight
   * Gets the height of the room
   * @return the height of the room
   */
 public int getHeight() {
  return height;
 }

 /**
   * getWidth
   * Gets the width of the room
   * @return the width of the room
   */
 public int getWidth() {
  return width;
 }

 /**
   * getName
   * Gets the name of the room (used to tell player which room they have just entered
   * @return the name of the room
   */
 public String getName ( ) {
   return this.name;
 }
}