/* 
 * Item
 * Any object within the room
 * @author Alyssa Gao, Joyce Huang, Julia Collins
 * June 14, 2018
 */

import java.io.Serializable;
import java.util.List;

public class Item implements Serializable {

  // ==== VARIABLES ====
 private int positionX; // x-cooridinate of item's position
 private int positionY; // y-cooridinate of item's position
 private Task task; // task associated with item (default is null)
 private List<InteractionStep> interactionSteps; // all the dialog messages for item

 // ==== CONSTRUCTORS ====
 
 /**
  * Position of item
  * @param positionX x-cooridinate of item's position
  * @param positionY y-cooridinate of item's position
  */
 Item (int positionX, int positionY){
   this.positionX = positionX;
   this.positionY = positionY;
 } 
 
 /**
  * Position of item with a task
  * @param positionX x-cooridinate of item's position
  * @param positionY y-cooridinate of item's position
  * @param task task associated wtih item
  */
 Item(int positionX, int positionY, Task task) {
  this.positionX = positionX;
  this.positionY = positionY;
  this.task = task;
 }
 
 /**
  * Position of item with a task
  * @param positionX x-cooridinate of item's position
  * @param positionY y-cooridinate of item's position
  * @param interactionSteps all the dialog messages for item
  */
 Item(int positionX, int positionY, List<InteractionStep> interactionSteps) {
   this.positionX = positionX;
   this.positionY = positionY;
   this.interactionSteps = interactionSteps;
 }
 
  // ==== METHODS ====

 /**
  * getPositionX
  * Gets x-coordinate position of item
  * @return positionX x-cooridinate of item's position
  */
 public int getPositionX() {
  return positionX;
 }

 /**
  * getPositionY
  * Gets y-coordinate position of item
  * @return positionY y-cooridinate of item's position
  */
 public int getPositionY() {
  return positionY;
 }
 
 /**
  * displayMessagesWhenInteract
  * displays message if player interacts with objects
  */
 public void displayMessagesWhenInteract( ) {
   new InteractionDialog(this.interactionSteps);
 }

 
 /**
  * interact
  * displays message if player interacts with objects and also checks whether task is completed or if tas exists
  * @return either the task needed to be complete or null (if task is compelte/no task)
  */
 public Task interact( ) {
  displayMessagesWhenInteract();
  if (getTask() != null) {
   if ( getTask().complete() ) {
    return getTask();
   }
  }
  return null;
 }

 /**
  * getTask
  * Gets task for item
  * @return task for item
  */
 public Task getTask() {
  return task;
 }
}