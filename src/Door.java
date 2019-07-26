/* 
 * Door
 * Creates a door within a room map
 * @author Alyssa Gao, Joyce Huang, Julia Collins
 * June 14, 2018
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Door implements Serializable {
  
  // ==== VARIABLES ====   
  private boolean unlocked = false; //default is that door is locked
  
  private Task task; // task associated with door in order to unlock it
  private boolean exitDoor = false; // determine whether door is exit door and function accordingly
  
  private Room room1; // first room that door is connected to
  private Room room2; // other room that door is connected to
  
  private Position playerPositionInRoom1; // player position in first room
  private Position playerPositionInRoom2; // player position in other room
  
  private Position doorPositionInRoom1; // door position in first room
  private Position doorPositionInRoom2; // door position in first room
  
  private List<InteractionStep> interactionStepsForIncompletTask; // dialoge outputted if task is incomplete to open door
  
  /**
   * Used for entry door
   * @param doorPositionInRoom1 door position in first room
   * @param room1 the first room 
   * @param playerPositionInRoom1 player's position in first room when they enter room
   */
  Door(Position doorPositionInRoom1, Room room1, Position playerPositionInRoom1) {
    this(doorPositionInRoom1, null, room1, null, null, playerPositionInRoom1, null);
  }
  
  /**
   * Used to link two rooms together
   * Also takes in player's position for each room
   * Used for doors with no associated task
   * @param doorPositionInRoom1 door position in first room
   * @param doorPositionInRoom2 door position in second room
   * @param room1 the first room 
   * @param room2 the second room 
   * @param playerPositionInRoom1 player's position in first room when they enter room
   * @param playerPositionInRoom2 player's position in second room when they enter room
   */
  Door(Position doorPositionInRoom1, Position doorPositionInRoom2, Room room1, Room room2,
       Position playerPositionInRoom1, Position playerPositionInRoom2) {
    this(doorPositionInRoom1, doorPositionInRoom2, room1, room2, null, playerPositionInRoom1,
         playerPositionInRoom2);
  }
  
  /**
   * Used for exit door
   * @param doorPositionInRoom1 door position in first room
   * @param room1 the first room 
   * @param task task that needs to be complete in order to unlock exit door
   * @param playerPositionInRoom1 player's position in first room when they enter room
   */
  Door(Position doorPositionInRoom1, Room room1, Task task, Position playerPositionInRoom1) {
    this(doorPositionInRoom1, null, room1, null, task, playerPositionInRoom1, null);
  }
  
  /**
   * Used to link two rooms together
   * Also takes in player's position for each room
   * @param doorPositionInRoom1 door position in first room
   * @param doorPositionInRoom2 door position in second room
   * @param room1 the first room 
   * @param room2 the second room 
   * @param task the task that is needed to be completed in order to open the door
   * @param playerPositionInRoom1 player's position in first room when they enter room
   * @param playerPositionInRoom2 player's position in second room when they enter room
   */
  Door(Position doorPositionInRoom1, Position doorPositionInRoom2, Room room1, Room room2, Task task,
       Position playerPositionInRoom1, Position playerPositionInRoom2) {
    this.doorPositionInRoom1 = doorPositionInRoom1;
    this.doorPositionInRoom2 = doorPositionInRoom2;
    this.room1 = room1;
    if (room1 != null) {
      room1.getDoors().add(this);
    }
    this.room2 = room2;
    if (room2 != null) {
      room2.getDoors().add(this);
    }
    this.task = task;
    this.playerPositionInRoom1 = playerPositionInRoom1;
    this.playerPositionInRoom2 = playerPositionInRoom2;
    this.interactionStepsForIncompletTask = new ArrayList<InteractionStep>();
    this.interactionStepsForIncompletTask.add(new InteractionStep("door locked.png", null));
  }
  
  // ==== METHODS ==== 
  
  /**
   * unlockDoor
   * Unlocks a locked door
   */
  public void unlockDoor() {
    unlocked = true;
  }
  
  /**
   * getOtherRoom
   * Gets the other room that the door leads to
   * @param currentRoom The current room
   * @return the other room
   */
  public Room getOtherRoom(Room currentRoom) {
    if (this.room1 != currentRoom) {
      return this.room1;
    } else {
      return this.room2;
    }
  }
  
  /**
   * getOtherPlayerPosition
   * Gets the player position in the other room
   * @param playerPosition The current player position
   * @return the player position in the other room
   */
  public Position getOtherPlayerPosition(Position playerPosition) {
    if (!((this.playerPositionInRoom1.positionX == playerPosition.positionX) && (this.playerPositionInRoom1.positionY == playerPosition.positionY))) {
      return this.playerPositionInRoom1;
    } else {
      return this.playerPositionInRoom2;
    }
  }
  
  /**
   * getDoorPosition
   * Gets the door position in the current room
   * @param room The current player position
   * @return the door position in the current room
   */
  public Position getDoorPosition(Room room) {
    if (this.room1 == room) {
      return this.doorPositionInRoom1;
    } else {
      return this.doorPositionInRoom2;
    }
  }
  
  
  /**
   * setExitDoor
   * Sets door as exit door
   * @param exitDoor if it is exit door, then exitDoor = true
   */
  public void setExitDoor(boolean exitDoor) {
    this.exitDoor = exitDoor;
  }
  
  /**
   * openDoor
   * Opens the door if task is completed in order to open door
   * @param panel
   * @param completeTaskBag The bag of completed tasks
   * @return unlocked door
   */
  public boolean openDoor(Set<Task> completeTaskBag) {
    if (completeTaskBag.contains(this.task)) {
      this.unlocked = true;
    } else if ((this.task != null) && (this.task.isLocalTask())) {
      if (this.task.complete()) {
        this.unlocked = true;
        completeTaskBag.add(this.task);
      } else {
        new InteractionDialog(this.interactionStepsForIncompletTask);
      }
    } else if (this.task != null) {
      new InteractionDialog(this.interactionStepsForIncompletTask);
    }
    return this.unlocked;
  }
  
  /**
   * isUnlocked
   * Checks if door is unlocked
   * @return whether or not door is unlcoked
   */
  public boolean isUnlocked() {
    return unlocked;
  }
  
  /**
   * getRoom1
   * returns first room
   * @return room1, the first room that door is linked to
   */
  public Room getRoom1() {
    return room1;
  }
  
  /**
   * getRoom1
   * returns second room
   * @return room2, the second room that door is linked to
   */
  public Room getRoom2() {
    return room2;
  }
  
  /**
   * isExitDoor
   * checks if door is exit door
   * @return true if door is exit door
   */
  public boolean isExitDoor() {
    return exitDoor;
  }
  
  /**
   * getPlayerPositionInRoom1
   * gets the player's position in first room
   * @return player position in first room
   */
  public Position getPlayerPositionInRoom1() {
    return playerPositionInRoom1;
  }
}