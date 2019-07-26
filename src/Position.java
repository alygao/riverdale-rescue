/* 
 * Position
 * Made up of x-coordinate and y-coordinate
 * @author Alyssa Gao, Joyce Huang, Julia Collins
 * June 14, 2018
 */

import java.io.Serializable;

public class Position implements Serializable {
  
 // ==== VARIABLES ====
 public int positionX; // x-coordinate
 public int positionY; // y-coordinate

 // ==== CONSTRUCTORS ====
 
 /**
  * @param positionX x-coordinate
  * @param positionY y-coordinate
  */
 public Position(int positionX, int positionY) {
  this.positionX = positionX;
  this.positionY = positionY;
 }
}
