/* 
 * SetupRoom
 * Setup room for easy, medium, and hard level
 * @author Alyssa Gao, Joyce Huang, Julia Collins
 * June 14, 2018
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class SetupRoom {
  
  public static final String THEME_FILENAME = "EscapeTheme.dat";
  
  // =================== EASY LEVEL ===================
  public static void setupEasyLevelRoom() {
    
    // Art Gallery Four Corners
    Position artGalleryTopLeftCorner = new Position(7, 4);
    Position artGalleryTopRightCorner = new Position(22, 4);
    Position artGalleryBottomLeftCorner = new Position(7, 10);
    Position artGalleryBottomRightCorner = new Position(22, 10);
    
    // Small Room Four Corners
    Position smallRoomTopLeftCorner = new Position(9, 5);
    Position smallRoomTopRightCorner = new Position(18, 5);
    Position smallRoomBottomLeftCorner = new Position(9, 13);
    Position smallRoomBottomRightCorner = new Position(18, 13);

    Room artGallery = new Room("Art Gallery", 17, 28, "art gallery.jpg", artGalleryTopLeftCorner,
                               artGalleryTopRightCorner, artGalleryBottomLeftCorner, artGalleryBottomRightCorner);
    
    Room smallRoom = new Room("Small Room", 17, 28, "small room.jpg", smallRoomTopLeftCorner,
                              smallRoomTopRightCorner, smallRoomBottomLeftCorner, smallRoomBottomRightCorner);
    
    // Art Gallery
    Door entryDoor = new Door(new Position(14, 10), artGallery, new Position(14, 9));
    
    //grape painting dialogue
    List<InteractionStep> interactionStepsForGrape = new ArrayList<InteractionStep>();
    interactionStepsForGrape.add(new InteractionStep("grape message 1.jpg"));
    interactionStepsForGrape.add(new InteractionStep("bottom right corner message.jpg"));
    interactionStepsForGrape.add(new InteractionStep("grape message 2.jpg"));
    Item grape = new Item(10, 6, interactionStepsForGrape);
    //blueberry painting dialogue
    List<InteractionStep> interactionStepsForBlueberry = new ArrayList<InteractionStep>();
    interactionStepsForBlueberry.add(new InteractionStep("blueberry message 1.jpg"));
    interactionStepsForBlueberry.add(new InteractionStep("bottom right corner message.jpg"));
    interactionStepsForBlueberry.add(new InteractionStep("blueberry message 2.jpg"));
    Item blueberry = new Item(11, 6, interactionStepsForBlueberry);
    
    //pear painting dialogue
    List<InteractionStep> interactionStepsForPear = new ArrayList<InteractionStep>();
    interactionStepsForPear.add(new InteractionStep("pear message 1.jpg"));
    interactionStepsForPear.add(new InteractionStep("bottom right corner message.jpg"));
    interactionStepsForPear.add(new InteractionStep("pear message 2.jpg"));
    Item pear = new Item(12, 6, interactionStepsForPear);
    
    //lemon painting dialogue
    List<InteractionStep> interactionStepsForLemon = new ArrayList<InteractionStep>();
    interactionStepsForLemon.add(new InteractionStep("lemon message 1.jpg"));
    interactionStepsForLemon.add(new InteractionStep("bottom right corner message.jpg"));
    interactionStepsForLemon.add(new InteractionStep("lemon message 2.jpg"));
    Item lemon = new Item(16, 6, interactionStepsForLemon);
    
    //apple painting dialogue
    List<InteractionStep> interactionStepsForApple = new ArrayList<InteractionStep>();
    interactionStepsForApple.add(new InteractionStep("apple message 1.jpg"));
    interactionStepsForApple.add(new InteractionStep("bottom right corner message.jpg"));
    interactionStepsForApple.add(new InteractionStep("apple message 2.jpg"));
    Item apple = new Item(17, 6, interactionStepsForApple);
    
    //pineapple painting dialogue
    List<InteractionStep> interactionStepsForPineapple = new ArrayList<InteractionStep>();
    interactionStepsForPineapple.add(new InteractionStep("pineapple message 1.jpg"));
    interactionStepsForPineapple.add(new InteractionStep("bottom right corner message.jpg"));
    interactionStepsForPineapple.add(new InteractionStep("pineapple message 2.jpg"));
    Item pineapple = new Item(18, 6, interactionStepsForPineapple);
    //first dialogue for statue
    List<InteractionStep> interactionStepsForDependentOfStatueTask = new ArrayList<InteractionStep>();
    interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue message 1.jpg"));
    interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue message 2.jpg"));
    // dependent task of statue task
    Random random = new Random();
    int pictureNumber = random.nextInt(6) + 1;
    
    // Grape question
    if (pictureNumber == 1){  
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue message grape 1.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue message grape 2.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue message grape 3.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue message grape 4.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue enter password.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("answer box.jpg", "16"));
    }
    // Blueberry question
    else if (pictureNumber == 2){  
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue message blueberry 1.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue message blueberry 2.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue message blueberry 3.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue message blueberry 4.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue enter password.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("answer box.jpg", "68"));
    }
    // Pear question
    else if (pictureNumber == 3){  
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue message pear 1.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue message pear 2.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue message pear 3.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue message pear 4.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue enter password.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("answer box.jpg", "22"));
    }
    // Lemon question
    else if (pictureNumber == 4){  
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue message lemon 1.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue message lemon 2.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue message lemon 3.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue message lemon 4.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue enter password.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("answer box.jpg", "3"));
    }
    
    // Apple question
    else if (pictureNumber == 5){  
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue message apple 1.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue message apple 2.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue message apple 3.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue message apple 4.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue enter password.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("answer box.jpg", "6"));
    }
    // Pineapple question
    else{
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue message pineapple 1.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue message pineapple 2.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue message pineapple 3.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue message pineapple 4.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("statue enter password.jpg"));
      interactionStepsForDependentOfStatueTask.add(new InteractionStep("answer box.jpg", "21"));
    }   
    
    // door to small room dialogue
    List<InteractionStep> interactionStepsForDependentOfStatueTaskCompleted = new ArrayList<InteractionStep>();
    interactionStepsForDependentOfStatueTaskCompleted.add(new InteractionStep("door unlocked.png"));
    // statue Q and A 
    QuestionAndAnswerTask dependentOfStatueTask = new QuestionAndAnswerTask(
                                                                            null, 
                                                                            null,
                                                                            interactionStepsForDependentOfStatueTask, 
                                                                            interactionStepsForDependentOfStatueTaskCompleted);
    // statue task complete dialogue
    List<InteractionStep> interactionStepsForTaskCompleteForStatueTask = new ArrayList<InteractionStep>();
    interactionStepsForTaskCompleteForStatueTask.add(new InteractionStep("already received key.jpg"));
    // statue task incomplete dialogue
    List<InteractionStep> interactionStepsForIncompleteDependentTaskForStatueTask = new ArrayList<InteractionStep>();
    interactionStepsForIncompleteDependentTaskForStatueTask.add(new InteractionStep("nothing happens.jpg"));
    // first completion message for statue Q and A
    List<InteractionStep> interactionStepsForObtainingKitchenKeyForStatueTask = new ArrayList<InteractionStep>();
    interactionStepsForObtainingKitchenKeyForStatueTask.add(new InteractionStep("key obtained.jpg"));
    // get key from statue if statue task complete ( make new task) 
    RetrievalTask statueTask = new RetrievalTask(
                                                 "A key.", 
                                                 "key image.png", 
                                                 dependentOfStatueTask,
                                                 interactionStepsForObtainingKitchenKeyForStatueTask, 
                                                 interactionStepsForTaskCompleteForStatueTask,
                                                 interactionStepsForIncompleteDependentTaskForStatueTask);
    // initialize statue
    Item statue = new Item(14, 7, statueTask);
    
    // add paintings into room array
    artGallery.getItems().add(grape);
    artGallery.getItems().add(blueberry);
    artGallery.getItems().add(pear);
    artGallery.getItems().add(lemon);
    artGallery.getItems().add(apple);
    artGallery.getItems().add(pineapple);
    artGallery.getItems().add(statue);
    
    // initialize and add wall
    artGallery.getItems().add(new Item(9, 6));
    artGallery.getItems().add(new Item(13, 6));
    artGallery.getItems().add(new Item(15, 6));
    artGallery.getItems().add(new Item(19, 6));
    artGallery.getItems().add(new Item(21, 6));
    // initialize door from main room to small room
    Door smallRoomDoor = new Door(new Position(8, 6),
                                  new Position(11, 12), artGallery, 
                                  smallRoom, statueTask, 
                                  new Position(11, 11), 
                                  new Position(8, 7));
    // exit door Q and A dialogue
    List<InteractionStep> interactionStepsForExitDoorTask = new ArrayList<InteractionStep>();
    interactionStepsForExitDoorTask.add(new InteractionStep("exit door message.png"));
    interactionStepsForExitDoorTask.add(new InteractionStep("answer box.jpg", "2001"));
    // exit door task complete dialogue
    List<InteractionStep> interactionStepsForExitDoorTaskCompleted = new ArrayList<InteractionStep>();
    interactionStepsForExitDoorTaskCompleted.add(new InteractionStep("door task complete.png"));
    //initialize exit door task
    QuestionAndAnswerTask exitDoorTask = new QuestionAndAnswerTask(
                                                                   "Passcode", 
                                                                   null, 
                                                                   interactionStepsForExitDoorTask,
                                                                   interactionStepsForExitDoorTaskCompleted);
    //initialize door to exit room 
    Door exitDoor = new Door(new Position(20, 6), artGallery, exitDoorTask, new Position(0, 0));
    exitDoor.setExitDoor(true);
    
    // Small Room within easy level
    //initialize and add walls to room
    smallRoom.getItems().add(new Item(10, 11));
    for (int i = 12; i <= 17; i++) {
      smallRoom.getItems().add(new Item(i, 11));
    }
    for (int i = 10; i <= 12; i++) {
      smallRoom.getItems().add(new Item(i, 7));
    }
    smallRoom.getItems().add(new Item(15, 7));
    smallRoom.getItems().add(new Item(16, 7));
    
    // painted numbers dialogue
    List<InteractionStep> interactionStepsForPaintedNumbers = new ArrayList<InteractionStep>();
    interactionStepsForPaintedNumbers.add(new InteractionStep("painted numbers message.jpg"));
    
    // spray paint cans dialogue
    List<InteractionStep> interactionStepsForSprayPaintCans = new ArrayList<InteractionStep>();
    interactionStepsForSprayPaintCans.add(new InteractionStep("spray paint cans message.jpg"));
    
    // dresser dialogue
    List<InteractionStep> interactionStepsForDresser = new ArrayList<InteractionStep>();
    interactionStepsForDresser.add(new InteractionStep("dresser message 1.jpg"));
    interactionStepsForDresser.add(new InteractionStep("dresser message 2.jpg"));
    
    //initialize and add items to room
    smallRoom.getItems().add(new Item(13, 7, interactionStepsForPaintedNumbers));
    smallRoom.getItems().add(new Item(14, 8, interactionStepsForSprayPaintCans));
    smallRoom.getItems().add( new Item(17, 8, interactionStepsForDresser));
    
    // dialog to introduce easy level (mini story)
    List<InteractionStep> themeStory = new ArrayList<InteractionStep> ( );
    themeStory.add( new InteractionStep ( "easy level message 1.jpg", null, -1, -2 ) );
    themeStory.add( new InteractionStep ( "easy level message 2.jpg", null, -1, -2 ) );
    themeStory.add( new InteractionStep ( "easy level message 3.jpg", null, -1, -2 ) );
    
    // output theme for easy level using serializable
    ObjectOutputStream oos = null;
    try {
      File file = new File(THEME_FILENAME);
      oos = new ObjectOutputStream(new FileOutputStream(file));
      oos.writeObject(entryDoor);
      oos.writeObject( themeStory );
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (oos != null) {
        try {
          oos.close();
        } catch (IOException e) {
        }
      }
    }
  }
  
  // =================== MEDIUM LEVEL ===================
  public static void setupMediumLevelRoom() {
    
    // Dining Room Four Corners
    Position diningRoomTopLeftCorner = new Position(6, 2);
    Position diningRoomTopRightCorner = new Position(22, 2);
    Position diningRoomBottomLeftCorner = new Position(6, 13);
    Position diningRoomBottomRightCorner = new Position(10, 13);
    
    // Kitchen Four Corners
    Position kitchenTopLeftCorner = new Position(7, 4);
    Position kitchenTopRightCorner = new Position(21, 4);
    Position kitchenBottomLeftCorner = new Position(7, 13);
    Position kitchenBottomRightCorner = new Position(21, 13);
    
    // Storage Room Four Corners
    Position storageRoomTopLeftCorner = new Position(8, 4);
    Position storageRoomTopRightCorner = new Position(20, 4);
    Position storageRoomBottomLeftCorner = new Position(8, 13);
    Position storageRoomBottomRightCorner = new Position(20, 13);
    
    // initialize rooms within level
    Room diningRoom = new Room(
                               "Dining Room", 
                               17, 
                               28, 
                               "Dining Room.jpg", 
                               diningRoomTopLeftCorner,
                               diningRoomTopRightCorner, 
                               diningRoomBottomLeftCorner, 
                               diningRoomBottomRightCorner);
    
    Room kitchen = new Room(
                            "Kitchen", 
                            17, 
                            28, 
                            "Kitchen.jpg", 
                            kitchenTopLeftCorner,
                            kitchenTopRightCorner, 
                            kitchenBottomLeftCorner, 
                            kitchenBottomRightCorner);
    
    Room storageRoom = new Room(
                                "Storage Room", 
                                17, 
                                28, 
                                "Storage Room.jpg", 
                                storageRoomTopLeftCorner,
                                storageRoomTopRightCorner, 
                                storageRoomBottomLeftCorner, 
                                storageRoomBottomRightCorner);
    
    // Dining Room entry door initialization
    Door entryDoor = new Door(new Position(14, 13), diningRoom, new Position(14, 12)); 
    
    // dependent task of cash register task (Q and A) dialogue
    List<InteractionStep> interactionStepsForDependentOfCashRegisterTask = new ArrayList<InteractionStep>();
    interactionStepsForDependentOfCashRegisterTask.add(new InteractionStep("cash register question message.jpg"));
    interactionStepsForDependentOfCashRegisterTask.add(new InteractionStep("answer box.jpg", "15"));
    // door to kitchen unlocked if cash register Q and A complete 
    List<InteractionStep> interactionStepsForDependentOfCashRegisterTaskCompleted = new ArrayList<InteractionStep>();
    interactionStepsForDependentOfCashRegisterTaskCompleted.add(new InteractionStep("door unlocked.png"));
    // initialize task
    QuestionAndAnswerTask dependentOfCashRegisterTask = new QuestionAndAnswerTask(
                                                                                  null, 
                                                                                  null,
                                                                                  interactionStepsForDependentOfCashRegisterTask,
                                                                                  interactionStepsForDependentOfCashRegisterTaskCompleted);
    
    // dialogue for possible outcomes of cash register Q and A task
    //complete
    List<InteractionStep> interactionStepsForTaskCompleteForCashRegisterTask = new ArrayList<InteractionStep>();
    interactionStepsForTaskCompleteForCashRegisterTask.add(new InteractionStep("kitchen key obtained.jpg"));
    //incomplete
    List<InteractionStep> interactionStepsForIncompleteDependentTaskForCashRegisterTask = new ArrayList<InteractionStep>();
    interactionStepsForIncompleteDependentTaskForCashRegisterTask.add(new InteractionStep("nothing happens.jpg"));
    
    // instructions from kitchen door 
    List<InteractionStep> interactionStepsForObtainingKitchenKeyForCashRegisterTask = new ArrayList<InteractionStep>();
    interactionStepsForObtainingKitchenKeyForCashRegisterTask.add(new InteractionStep("get kitchen key.jpg"));
    //initialize task for cash register retrieval of key
    RetrievalTask cashRegisterTask = new RetrievalTask(
                                                       "Key with a kitchen label attached to it.", 
                                                       "kitchen key image.jpg",
                                                       dependentOfCashRegisterTask, 
                                                       interactionStepsForObtainingKitchenKeyForCashRegisterTask, 
                                                       interactionStepsForTaskCompleteForCashRegisterTask, 
                                                       interactionStepsForIncompleteDependentTaskForCashRegisterTask);
    
    // dialogue for interaction with coffee picture
    List<InteractionStep> interactionStepsForPictureFrame = new ArrayList<InteractionStep>();
    interactionStepsForPictureFrame.add(new InteractionStep("picture frame coffee message.jpg"));
    
    // dialogue for interaction with dining tables
    List<InteractionStep> interactionStepsForDiningTable = new ArrayList<InteractionStep>();
    interactionStepsForDiningTable.add(new InteractionStep("dining room table message.jpg"));
    
    //diaogue for interaction with tables
    List<InteractionStep> interactionStepsForTable = new ArrayList<InteractionStep>();
    interactionStepsForTable.add(new InteractionStep("empty table message.jpg"));
    List<InteractionStep> interactionStepsForCashRegisterTable = new ArrayList<InteractionStep>();
    interactionStepsForCashRegisterTable.add(new InteractionStep("cash register counter message.jpg"));
    
    //dialogue for interaction with receipt
    List<InteractionStep> interactionStepsForReceipt = new ArrayList<InteractionStep>();
    interactionStepsForReceipt.add(new InteractionStep("receipt image.jpg", null, -1, -2));
    interactionStepsForReceipt.add(new InteractionStep("receipt message.jpg"));
    
    //dialogue for interaction with menu
    List<InteractionStep> interactionStepsForMenu = new ArrayList<InteractionStep>();
    interactionStepsForMenu.add(new InteractionStep("menu image.jpg", null, -1, -2));
    interactionStepsForMenu.add(new InteractionStep("menu message.png"));
    
    //dialogue for interaction with note
    List<InteractionStep> interactionStepsForPopsNote = new ArrayList<InteractionStep>();
    interactionStepsForPopsNote.add(new InteractionStep("pops favourite colour message.jpg"));
    
    //dialogue for interaction with bulletin board
    List<InteractionStep> interactionStepsForBulletinBoard = new ArrayList<InteractionStep>();
    interactionStepsForBulletinBoard.add(new InteractionStep("bulletin board image.jpg", null, -1, -2));
    interactionStepsForBulletinBoard.add(new InteractionStep("bulletin board message.jpg"));
   
    // dialogue for exit door Q and A
    List<InteractionStep> interactionStepsForExitDoorTask = new ArrayList<InteractionStep>();
    interactionStepsForExitDoorTask.add(new InteractionStep("exit door message.png"));
    interactionStepsForExitDoorTask.add(new InteractionStep("answer box.jpg", "1120"));
    // exit door Q and A complete
    List<InteractionStep> interactionStepsForExitDoorTaskCompleted = new ArrayList<InteractionStep>();
    interactionStepsForExitDoorTaskCompleted.add(new InteractionStep("door task complete.png"));
    // initalize Q and A tesk for exit door
    QuestionAndAnswerTask exitDoorTask = new QuestionAndAnswerTask(
                                                                   null,
                                                                   null,
                                                                   interactionStepsForExitDoorTask, 
                                                                   interactionStepsForExitDoorTaskCompleted);
    
    // initialize items and add them to corresponding rooms
    
    //tables
    for (int i = 7; i <= 12; i++) {
      diningRoom.getItems().add(new Item(i, 11, interactionStepsForDiningTable));
    }
    diningRoom.getItems().add(new Item(13, 11, interactionStepsForDiningTable));
    for (int i = 15; i <= 21; i++) {
      diningRoom.getItems().add(new Item(i, 11, interactionStepsForDiningTable));
    }
    diningRoom.getItems().add(new Item(15, 12, interactionStepsForDiningTable));
    diningRoom.getItems().add(new Item(13, 12, interactionStepsForDiningTable));
    for (int i = 7; i <=14; i++) {
      diningRoom.getItems().add(new Item(i, 8, interactionStepsForTable));
    }
    diningRoom.getItems().add(new Item(16, 8, interactionStepsForTable));
    for (int i = 7; i <=14; i++) {
      diningRoom.getItems().add(new Item(i, 7, interactionStepsForTable));
    }    
    diningRoom.getItems().add(new Item(16, 7, interactionStepsForTable));
    
    //specialty objects
    diningRoom.getItems().add(new Item(15, 8, interactionStepsForCashRegisterTable)); //cash register table
    diningRoom.getItems().add(new Item(15, 7, cashRegisterTask)); //cash register
    diningRoom.getItems().add(new Item(11, 4, interactionStepsForPictureFrame)); //picture frame
    diningRoom.getItems().add(new Item(13, 4, interactionStepsForMenu));//menu
    diningRoom.getItems().add(new Item(16, 4, interactionStepsForPopsNote)); //pops note
    diningRoom.getItems().add(new Item(19, 4, interactionStepsForBulletinBoard)); //bulletin board item 1
    diningRoom.getItems().add(new Item(20, 4, interactionStepsForBulletinBoard)); //bulletin board item 2
    diningRoom.getItems().add(new Item(8, 11, interactionStepsForReceipt)); //receipt
    
    //walls
    diningRoom.getItems().add(new Item(7, 4));
    diningRoom.getItems().add(new Item(9, 4));
    diningRoom.getItems().add(new Item(10, 4));
    diningRoom.getItems().add(new Item(12, 4));
    diningRoom.getItems().add(new Item(14, 4));
    diningRoom.getItems().add(new Item(15, 4));
    diningRoom.getItems().add(new Item(18, 4));
    diningRoom.getItems().add(new Item(21, 4));
    
    //initialize door to kitchen from dining room
    Door kitchenDoor = new Door(new Position(17, 4), new Position(18, 12), diningRoom, kitchen, cashRegisterTask, new Position(18, 11), new Position(17, 5));
    //initialize exit door
    Door exitDoor = new Door(new Position(8, 4), diningRoom, exitDoorTask, new Position(0, 0));
    exitDoor.setExitDoor(true);
    
    // Kitchen Room
    //dialogue for Q and A tas to open storage room door
    List<InteractionStep> interactionStepsForStorageRoomDoorTask = new ArrayList<InteractionStep>();
    interactionStepsForStorageRoomDoorTask.add(new InteractionStep("storage room door passcode.jpg"));
    interactionStepsForStorageRoomDoorTask.add(new InteractionStep("answer box.jpg", "4"));
    // dialogue for completed storage room task
    List<InteractionStep> interactionStepsForStorageRoomDoorTaskCompleted = new ArrayList<InteractionStep>();
    interactionStepsForStorageRoomDoorTaskCompleted.add(new InteractionStep("door unlocked.png"));
    //initialize storage room task
    QuestionAndAnswerTask storageRoomDoorTask = new QuestionAndAnswerTask(
                                                                          null, 
                                                                          null,
                                                                          interactionStepsForStorageRoomDoorTask, 
                                                                          interactionStepsForStorageRoomDoorTaskCompleted);
    //initialise door to storage room from kitchen
    Door storageRoomDoor = new Door(new Position(9, 6), new Position(11, 12), kitchen, storageRoom, storageRoomDoorTask, new Position(11, 11), new Position(9, 7));
    
    //dialogue for flowers
    List<InteractionStep> interactionStepsForFlowers = new ArrayList<InteractionStep>();
    interactionStepsForFlowers.add(new InteractionStep("flower card message 1.jpg"));
    interactionStepsForFlowers.add(new InteractionStep("flower card message.jpg", null, -1, -2));
    interactionStepsForFlowers.add(new InteractionStep("flower message 1.jpg"));
    interactionStepsForFlowers.add(new InteractionStep("flower message 2.jpg"));
    
    //dialogue for stool
    List<InteractionStep> interactionStepsForStool = new ArrayList<InteractionStep>();
    interactionStepsForStool.add(new InteractionStep("stool message 1.jpg"));
    interactionStepsForStool.add(new InteractionStep("stool message 2.jpg"));
    
    //dialogue for kitchen counter and appliances
    List<InteractionStep> interactionStepsForKitchenCounter = new ArrayList<InteractionStep>();
    interactionStepsForKitchenCounter.add(new InteractionStep("kitchen counter message.jpg"));
    List<InteractionStep> interactionStepsForKitchenSink = new ArrayList<InteractionStep>();
    interactionStepsForKitchenSink.add(new InteractionStep("kitchen sink message.jpg"));
    List<InteractionStep> interactionStepsForKitchenOven = new ArrayList<InteractionStep>();
    interactionStepsForKitchenOven.add(new InteractionStep("kitchen oven message.jpg"));
    
    //dialogue for cupboard
    List<InteractionStep> interactionStepsForCupboard = new ArrayList<InteractionStep>();
    interactionStepsForCupboard.add(new InteractionStep("cupboard image.jpg", null, -1, -2));
    interactionStepsForCupboard.add(new InteractionStep("cupboard message.jpg"));
    
    
    // initialize and add items to kitchen room
    // specialty objects
    kitchen.getItems().add(new Item(11, 7, interactionStepsForFlowers));//table with flowers
    kitchen.getItems().add(new Item(12, 7, interactionStepsForStool)); //stool
    kitchen.getItems().add( new Item(15, 7, interactionStepsForKitchenOven)); //oven
    kitchen.getItems().add(new Item(16, 7, interactionStepsForKitchenCounter)); //counter 1
    kitchen.getItems().add(new Item(18, 7, interactionStepsForKitchenSink)); // sink
    kitchen.getItems().add(new Item(19, 7, interactionStepsForKitchenCounter)); //counter 2
    kitchen.getItems().add(new Item(13, 7, interactionStepsForCupboard)); //cupboard 1
    kitchen.getItems().add(new Item(14, 7, interactionStepsForCupboard)); //cupboard 2
    
    //walls
    kitchen.getItems().add(new Item(8, 6));
    kitchen.getItems().add( new Item(10, 6));
    kitchen.getItems().add(new Item(17, 6));
    kitchen.getItems().add(new Item(20, 6));
    
    for (int i = 8; i <= 17; i++) {
      kitchen.getItems().add(new Item(i, 11));
    }
    for (int i = 19; i <= 20; i++) {
      kitchen.getItems().add(new Item(i, 11));
    }
    
    // Storage Room
    //storage room table dialogue
    List<InteractionStep> interactionStepsForStorageRoomTable = new ArrayList<InteractionStep>();
    interactionStepsForStorageRoomTable.add(new InteractionStep("table message.jpg"));
    // red jar dialogue
    List<InteractionStep> interactionStepsForRedJar = new ArrayList<InteractionStep>();
    interactionStepsForRedJar.add(new InteractionStep("red jar message 1.jpg"));
    interactionStepsForRedJar.add(new InteractionStep("red jar message 2.jpg"));
    interactionStepsForRedJar.add(new InteractionStep("red jar message 3.jpg"));
    //blue jar dialogue
    List<InteractionStep> interactionStepsForBlueJar = new ArrayList<InteractionStep>();
    interactionStepsForBlueJar.add(new InteractionStep("blue jar message 1.jpg"));
    interactionStepsForBlueJar.add(new InteractionStep("blue jar message 2.jpg"));
    interactionStepsForBlueJar.add(new InteractionStep("blue jar message 3.jpg"));
    //purple jar dialogue
    List<InteractionStep> interactionStepsForPurpleJar = new ArrayList<InteractionStep>();
    interactionStepsForPurpleJar.add(new InteractionStep("purple jar message 1.jpg"));
    interactionStepsForPurpleJar.add(new InteractionStep("purple jar message 2.jpg"));
    interactionStepsForPurpleJar.add(new InteractionStep("purple jar message 3.jpg"));
    
    //initialize and add items    
    storageRoom.getItems().add(new Item(13, 8, interactionStepsForStorageRoomTable)); //table
    storageRoom.getItems().add(new Item(14, 8, interactionStepsForStorageRoomTable));
    
    storageRoom.getItems().add( new Item(16, 8, interactionStepsForRedJar)); //red jar
    storageRoom.getItems().add( new Item(17, 8, interactionStepsForBlueJar)); //blue jar
    storageRoom.getItems().add( new Item(18, 8, interactionStepsForPurpleJar)); //purple jar
    
    //walls
    storageRoom.getItems().add(new Item(9, 7));
    storageRoom.getItems().add(new Item(10, 7));
    storageRoom.getItems().add(new Item(11, 7));
    storageRoom.getItems().add(new Item(12, 7));
    storageRoom.getItems().add(new Item(15, 7));
    storageRoom.getItems().add(new Item(19, 7));
    for (int i = 9; i <= 19; i++) {
      if (i != 11) {
        storageRoom.getItems().add(new Item(i, 11));
      }
    }
    
    // dialog to introduce medium level (mini story)
    List<InteractionStep> themeStory = new ArrayList<InteractionStep> ( );
    themeStory.add( new InteractionStep ( "medium level message 1.jpg", null, -1, -2 ) );
    themeStory.add( new InteractionStep ( "medium level message 2.jpg", null, -1, -2 ) );
    themeStory.add( new InteractionStep ( "medium level message 3.jpg", null, -1, -2) );
    themeStory.add( new InteractionStep ( "medium level message 4.jpg", null, -1, -2) );
    
    // output theme for medium level using serializable
    ObjectOutputStream oos = null;
    try {
      File file = new File(THEME_FILENAME);
      oos = new ObjectOutputStream(new FileOutputStream(file));
      oos.writeObject(entryDoor);
      oos.writeObject(themeStory);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (oos != null) {
        try {
          oos.close();
        } catch (IOException e) {
        }
      }
    }
  }
  
  
  // =================== HARD LEVEL ===================
  public static void setupHardLevelRoom() {
    // Cafeteria Four Corners
    Position cafeteriaTopLeftCorner = new Position (7, 3);
    Position cafeteriaTopRightCorner = new Position (22, 3);
    Position cafeteriaBottomLeftCorner = new Position (7, 14);
    Position cafeteriaBottomRightCorner = new Position (22, 14);
    
    Position foodRoomTopLeftCorner = new Position (10, 2);
    Position foodRoomTopRightCorner = new Position (17, 2);
    Position foodRoomBottomLeftCorner = new Position (10, 15);
    Position foodRoomBottomRightCorner = new Position (17, 15);
    
    Position classroomTopLeftCorner = new Position (9, 4);
    Position classroomTopRightCorner = new Position (20, 4);
    Position classroomBottomLeftCorner = new Position (9, 15);
    Position classroomBottomRightCorner = new Position (20, 15);
    
    Position liarsRoomTopLeftCorner = new Position(7, 4);
    Position liarsRoomTopRightCorner = new Position (19, 4);
    Position liarsRoomBottomLeftCorner = new Position (7, 10);
    Position liarsRoomBottomRightCorner = new Position (19, 10);
    
    Position trophyRoomTopLeftCorner = new Position(11, 3);
    Position trophyRoomTopRightCorner = new Position (17, 3);
    Position trophyRoomBottomLeftCorner = new Position (11, 13);
    Position trophyRoomBottomRightCorner = new Position (17, 13);
    
    Room foodRoom = new Room("Food Room", 17, 28, "food room.png", foodRoomTopLeftCorner, foodRoomTopRightCorner, foodRoomBottomLeftCorner, foodRoomBottomRightCorner);
    Room cafeteria = new Room("Cafeteria", 17, 28, "cafeteria.png", cafeteriaTopLeftCorner, cafeteriaTopRightCorner, cafeteriaBottomLeftCorner, cafeteriaBottomRightCorner);
    Room classroom = new Room("Classroom", 17, 28, "classroom.png", classroomTopLeftCorner, classroomTopRightCorner, classroomBottomLeftCorner, classroomBottomRightCorner);
    Room liarsRoom = new Room("Liars' Room", 17, 28, "liars room.png", liarsRoomTopLeftCorner, liarsRoomTopRightCorner, liarsRoomBottomLeftCorner, liarsRoomBottomRightCorner);
    Room trophyRoom = new Room("Trophy Room", 17, 28, "trophy room.png", trophyRoomTopLeftCorner, trophyRoomTopRightCorner, trophyRoomBottomLeftCorner, trophyRoomBottomRightCorner);
    
    
    //DIALOGUE
    //Cafeteria / attached food room
    //menu dialogue
    List<InteractionStep> interactionStepsForCafMenu= new ArrayList<InteractionStep>();
    interactionStepsForCafMenu.add(new InteractionStep("menu1.png"));
    interactionStepsForCafMenu.add(new InteractionStep("menu2.png"));    
    
    //dependent task dialogue to get garbage (asks for change)
    List<InteractionStep> interactionStepsForDependentOfCafCashTask = new ArrayList<InteractionStep>();
    interactionStepsForDependentOfCafCashTask.add(new InteractionStep("cafCash1.png"));
    interactionStepsForDependentOfCafCashTask.add(new InteractionStep("answer box.jpg", "1"));
    // dialogue for if task is completed
    List<InteractionStep> interactionStepsForDependentOfCafCashTaskCompleted= new ArrayList<InteractionStep>();
    interactionStepsForDependentOfCafCashTaskCompleted.add(new InteractionStep("garbage.png"));
    List<InteractionStep> interactionStepsForIncompleteDependentTaskForCafCash= new ArrayList<InteractionStep>();
    interactionStepsForIncompleteDependentTaskForCafCash.add(new InteractionStep("nothing happens.jpg"));
    //Cash register tasks initialize
    QuestionAndAnswerTask dependentOfCafCashTask = new QuestionAndAnswerTask (
                                                                              "Change", 
                                                                              "key.png", 
                                                                              interactionStepsForDependentOfCafCashTask, 
                                                                              interactionStepsForDependentOfCafCashTaskCompleted);
    RetrievalTask cafCashTask = new RetrievalTask(
                                                  "A piece of garbage.",
                                                  "garbage image.png", 
                                                  dependentOfCafCashTask, 
                                                  interactionStepsForDependentOfCafCashTaskCompleted,  
                                                  interactionStepsForDependentOfCafCashTaskCompleted, 
                                                  interactionStepsForIncompleteDependentTaskForCafCash);
    
    //dialogue for closed trash can
    List<InteractionStep> interactionStepsForTrashCanClosed= new ArrayList<InteractionStep>();
    interactionStepsForTrashCanClosed.add(new InteractionStep("trash.png"));
    //dialogue for open trash can
    List<InteractionStep> interactionStepsForTrashCanOpen= new ArrayList<InteractionStep>();
    interactionStepsForTrashCanOpen.add(new InteractionStep("trash1.png"));
    interactionStepsForTrashCanOpen.add(new InteractionStep("trash2.png"));
    //previous task complete (have garbage), get map
    List<InteractionStep> interactionStepsForMapPiece= new ArrayList<InteractionStep>();
    interactionStepsForMapPiece.add(new InteractionStep("trash3.png"));
    //task get map
    RetrievalTask trashTask = new RetrievalTask(
                                                "A map piece", 
                                                "map piece image.png", 
                                                cafCashTask, interactionStepsForTrashCanOpen, 
                                                interactionStepsForMapPiece, 
                                                interactionStepsForTrashCanClosed);
    
    //dialogue for missing map piece
    List<InteractionStep> interactionStepsForMissingMap= new ArrayList<InteractionStep>();
    interactionStepsForMissingMap.add(new InteractionStep("mapMissing.png"));
    List<InteractionStep> interactionStepsForMapFull= new ArrayList<InteractionStep>();
    interactionStepsForMapFull.add(new InteractionStep("mapPic.png", null, -1, -2));
    interactionStepsForMapFull.add(new InteractionStep("map.png"));
    //task making sure player has map piece
    RetrievalTask mapTask = new RetrievalTask("The other piece of the map","mapPic copy.png", trashTask, interactionStepsForMapFull, interactionStepsForMapFull, interactionStepsForMissingMap);
    
    //message "nobody here" for all tables
    List<InteractionStep> interactionStepTable= new ArrayList<InteractionStep>();
    interactionStepTable.add(new InteractionStep("dining room table message.jpg"));
    
    //door to classroom
    List<InteractionStep> interactionStepClassroomDoor= new ArrayList<InteractionStep>();
    interactionStepClassroomDoor.add(new InteractionStep("benchNote2.png"));
    interactionStepClassroomDoor.add(new InteractionStep("answer box.jpg", "7"));  //correct #trees given
    //door task 
    QuestionAndAnswerTask classroomDoorTask = new QuestionAndAnswerTask (null, null,interactionStepClassroomDoor,null);
     //initialize entry door
    Door entryDoor = new Door(new Position (14, 12), cafeteria, new Position(14, 13)); 
    
    //initialize and add all items in cafeteri

    //tables
    cafeteria.getItems().add(new Item (10, 12, interactionStepTable));
    cafeteria.getItems().add(new Item (10, 11, interactionStepTable));
    cafeteria.getItems().add(new Item (10, 7, interactionStepTable));
    cafeteria.getItems().add(new Item (10, 8, interactionStepTable));
    cafeteria.getItems().add(new Item (14, 12, interactionStepTable));
    cafeteria.getItems().add(new Item (14, 11, interactionStepTable));
    cafeteria.getItems().add(new Item (19, 12, interactionStepTable));
    cafeteria.getItems().add(new Item (19, 11, interactionStepTable));
    cafeteria.getItems().add(new Item (19, 7, interactionStepTable));
    cafeteria.getItems().add(new Item (19, 8, interactionStepTable));
    //chairs
    cafeteria.getItems().add(new Item (9, 11, interactionStepTable));
    cafeteria.getItems().add(new Item (11, 11, interactionStepTable));
    cafeteria.getItems().add(new Item (9, 12, interactionStepTable));
    cafeteria.getItems().add(new Item (11, 12, interactionStepTable));
    cafeteria.getItems().add(new Item (9, 7, interactionStepTable));
    cafeteria.getItems().add(new Item (11, 7, interactionStepTable));
    cafeteria.getItems().add(new Item (13, 11, interactionStepTable));
    cafeteria.getItems().add(new Item (15, 11, interactionStepTable));
    cafeteria.getItems().add(new Item (13, 12, interactionStepTable));
    cafeteria.getItems().add(new Item (15, 12, interactionStepTable));
    cafeteria.getItems().add(new Item (18, 12, interactionStepTable));
    cafeteria.getItems().add(new Item (20, 12, interactionStepTable));
    cafeteria.getItems().add(new Item (20, 11, interactionStepTable));
    cafeteria.getItems().add(new Item (18, 11, interactionStepTable));
    cafeteria.getItems().add(new Item (9, 8, interactionStepTable));
    cafeteria.getItems().add( new Item (11, 8, interactionStepTable));
    cafeteria.getItems().add(new Item (18, 7, interactionStepTable));
    cafeteria.getItems().add(new Item (18, 8, interactionStepTable));
    cafeteria.getItems().add(new Item (20, 7, interactionStepTable));
    cafeteria.getItems().add( new Item (20, 8, interactionStepTable));
            
    cafeteria.getItems().add(new Item (20, 5, trashTask)); //garbage can
    
    //walls
    cafeteria.getItems().add(new Item (8, 4));    
    for (int i=10; i<=13; i++) {
      cafeteria.getItems().add(new Item (i, 4));
    }
    for (int i=5; i<=8; i++) {
      cafeteria.getItems().add(new Item (13, i));
    }
    cafeteria.getItems().add(new Item (15, 8));
    for (int i=5; i<=8; i++) {
      cafeteria.getItems().add(new Item (16, i));
    }
    for (int i=16; i<=19; i++) {
      cafeteria.getItems().add(new Item (i, 4));
    }
    cafeteria.getItems().add(new Item (21, 4));
    
    //food room initialize and add items
    //tables
    foodRoom.getItems().add(new Item (14, 9, interactionStepTable));
    foodRoom.getItems().add(new Item (15, 9, interactionStepTable));
    foodRoom.getItems().add(new Item (16, 9, interactionStepTable));
    //walls 
    foodRoom.getItems().add(new Item (11, 5));
    foodRoom.getItems().add(new Item (16, 5));
    foodRoom.getItems().add(new Item (15, 14));
    foodRoom.getItems().add(new Item (16, 14));
    foodRoom.getItems().add(new Item (13, 14));
    //chairs
    foodRoom.getItems().add(new Item (11, 12, interactionStepTable));
    foodRoom.getItems().add(new Item (12, 12, interactionStepTable));
    foodRoom.getItems().add(new Item (12, 13, interactionStepTable));
    
    foodRoom.getItems().add(new Item (12, 5, interactionStepsForCafMenu)); //bulletin board 1
    foodRoom.getItems().add( new Item (13, 5, interactionStepsForCafMenu));//bulletin board 2
    foodRoom.getItems().add( new Item (14, 5, mapTask));//map
    foodRoom.getItems().add(new Item (15, 5, mapTask));//map
    
    foodRoom.getItems().add(new Item (13, 9, cafCashTask)); //food room cash register
    
    
    //Classroom
    //cupboard Q and A dialogue
    List<InteractionStep> interactionStepClassroomCupboard= new ArrayList<InteractionStep>();
    interactionStepClassroomCupboard.add(new InteractionStep("classroomCupboard.png"));
    interactionStepClassroomCupboard.add(new InteractionStep("answer box.jpg", "1981"));
    //complete cupboard task dialogue
    List<InteractionStep> interactionStepClassroomCupboardOpen= new ArrayList<InteractionStep>();
    interactionStepClassroomCupboardOpen.add(new InteractionStep("key(exit).png"));
    //incomplete cupboard task
    List<InteractionStep> interactionStepClassroomCupboardClosed= new ArrayList<InteractionStep>();
    interactionStepClassroomCupboardClosed.add(new InteractionStep("nothing happens.jpg"));
    //initialize cupboard tasks
    QuestionAndAnswerTask classroomCupboardTask= new QuestionAndAnswerTask (null, null, interactionStepClassroomCupboard, interactionStepClassroomCupboardOpen);
    RetrievalTask exitKeyTask = new RetrievalTask("Exit Door key", "key image.png", classroomCupboardTask,  interactionStepClassroomCupboardOpen,  interactionStepClassroomCupboardOpen, interactionStepClassroomCupboardClosed);
        
    //initialize and add all items to classroom
    classroom.getItems().add(new Item (10, 6, exitKeyTask)); //cabinet
     //walls
    classroom.getItems().add(new Item (11, 5));
    classroom.getItems().add(new Item (15, 5));
    classroom.getItems().add(new Item (17, 5));
    //blackboard
    classroom.getItems().add(new Item(12, 5)); 
    classroom.getItems().add(new Item(13, 5));
    classroom.getItems().add(new Item(14, 5));
    //tables
    classroom.getItems().add(new Item (12, 7, interactionStepTable));
    classroom.getItems().add(new Item (13, 7, interactionStepTable));
    classroom.getItems().add(new Item (14, 7, interactionStepTable));
    classroom.getItems().add(new Item(11, 9, interactionStepTable));
    classroom.getItems().add(new Item (12, 9, interactionStepTable));
    classroom.getItems().add(new Item(15, 9, interactionStepTable));
    classroom.getItems().add( new Item (16, 9, interactionStepTable));
    classroom.getItems().add(new Item (11, 11, interactionStepTable));
    classroom.getItems().add(new Item(12, 11, interactionStepTable));
    classroom.getItems().add(new Item (15, 11, interactionStepTable));
    classroom.getItems().add(new Item(16, 11, interactionStepTable));
    //Liars Room 
    //painting 1 dialogue
    List<InteractionStep> interactionStepPainting1= new ArrayList<InteractionStep>();
    interactionStepPainting1.add(new InteractionStep("painting1.png"));
    //painting 2 dialogue
    List<InteractionStep> interactionStepPainting2= new ArrayList<InteractionStep>();
    interactionStepPainting2.add(new InteractionStep("painting2.png"));
    //painting 3 dialogue
    List<InteractionStep> interactionStepPainting3= new ArrayList<InteractionStep>();
    interactionStepPainting3.add(new InteractionStep("painting3.png"));
    //painting 4 dialogue
    List<InteractionStep> interactionStepPainting4= new ArrayList<InteractionStep>();
    interactionStepPainting4.add(new InteractionStep("painting4.png"));
    //painting 5 dialogue
    List<InteractionStep> interactionStepPainting5= new ArrayList<InteractionStep>();
    interactionStepPainting5.add(new InteractionStep("painting5.png"));
    
    //liar room door dialogue for Q and A
    List<InteractionStep> interactionStepLiarDoor= new ArrayList<InteractionStep>();
    interactionStepLiarDoor.add(new InteractionStep("liarDoor.png"));
    interactionStepLiarDoor.add(new InteractionStep("answer box.jpg", "3"));
    //initialize door Q and A task
    QuestionAndAnswerTask liarRiddleTask= new QuestionAndAnswerTask ("truth", "key.png", interactionStepLiarDoor, null);
    
     //initialize and add all items to liars room
    //all paintings
    liarsRoom.getItems().add(new Item(10, 7, interactionStepPainting1)); //painting 1
    liarsRoom.getItems().add(new Item(11, 7, interactionStepPainting1));
    liarsRoom.getItems().add(new Item(12, 7, interactionStepPainting2)); //painting 2
    liarsRoom.getItems().add(new Item(13, 7, interactionStepPainting3));//painting 3
    liarsRoom.getItems().add(new Item(14, 7, interactionStepPainting3));
    liarsRoom.getItems().add(new Item(16, 7, interactionStepPainting4));//painting 4
    liarsRoom.getItems().add(new Item(17, 7, interactionStepPainting4));
    liarsRoom.getItems().add(new Item(18, 7, interactionStepPainting5));//painting 5
    //walls
    liarsRoom.getItems().add(new Item(9, 8));
    for (int j = 10; j < 19; j++) {
      if (j != 13) {
        classroom.getItems().add(new Item(j, 13));
      }
    }
    for (int k = 6; k < 13; k++) {
      if (k != 8) {
        classroom.getItems().add(new Item(18, k));
      }
    }
    
    //Trophy Room
    List<InteractionStep> interactionStepTrophyGold= new ArrayList<InteractionStep>();
    interactionStepTrophyGold.add(new InteractionStep("trophy1.png"));
    List<InteractionStep> interactionStepTrophySilver= new ArrayList<InteractionStep>();
    interactionStepTrophySilver.add(new InteractionStep("trophyText2.png"));
    List<InteractionStep> interactionStepPlaque= new ArrayList<InteractionStep>();
    interactionStepPlaque.add(new InteractionStep("plaqueText.png"));   
    
    //initialize and add all items to trophy room   
    //walls
    trophyRoom.getItems().add(new Item (12, 6));
    trophyRoom.getItems().add(new Item (16, 6));
    trophyRoom.getItems().add( new Item (12, 12));
    trophyRoom.getItems().add( new Item (13, 12));
    trophyRoom.getItems().add( new Item (15, 12));
    trophyRoom.getItems().add( new Item (16, 12));
    //specialty objects
    trophyRoom.getItems().add(new Item(13, 7, interactionStepTrophyGold)); //trophy 1
    trophyRoom.getItems().add(new Item(15, 7, interactionStepTrophySilver)); //trophy 2
    trophyRoom.getItems().add(new Item(14, 6, interactionStepPlaque)); //plaque
   
    
    // initialize door from cafeteria to food room
    Door cafeteriaDoor = new Door(new Position (14, 8), new Position (14, 14), cafeteria, foodRoom, new Position(14, 9), new Position(14,  13) );
    cafeteriaDoor.unlockDoor();
    
    // initialize door from cafeteria to classroom
    Door classroomDoor = new Door(new Position (9, 4), new Position (13, 14), cafeteria, classroom, classroomDoorTask, new Position(9, 5), new Position(13, 13));
    
    // initialize door from classroom to liars room
    Door liarsRoomDoor = new Door(new Position (19, 8), new Position(8, 9), classroom, liarsRoom, new Position (18, 8), new Position (9, 9));
    liarsRoomDoor.unlockDoor();
    
    // initialize door from liars room to trophy room
    Door trophyRoomDoor = new Door(new Position (15, 7), new Position(14, 12), liarsRoom, trophyRoom, liarRiddleTask, new Position(15, 8), new Position(14, 11));
    
    // initialize exit door
    Door exitDoor = new Door(new Position (16, 5), classroom, exitKeyTask, new Position(16, 6) );
    exitDoor.setExitDoor(true);
    
    // dialog to introduce hard level (mini story)
    List<InteractionStep> themeStory = new ArrayList<InteractionStep> ( );
    themeStory.add( new InteractionStep ( "hard level message 1.jpg", null, -1, -2 ) );
    themeStory.add( new InteractionStep ( "hard level message 2.jpg", null, -1, -2 ) );
    themeStory.add( new InteractionStep ( "hard level message 3.jpg", null, -1, -2 ) );
   
    // output theme for hard level using serializable
    ObjectOutputStream oos = null;
    try {
      File file = new File(THEME_FILENAME);
      oos = new ObjectOutputStream(new FileOutputStream(file));
      oos.writeObject(entryDoor);
      oos.writeObject( themeStory );
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (oos != null) {
        try {
          oos.close();
        } catch (IOException e) {
        }
      }
    }
  }
}