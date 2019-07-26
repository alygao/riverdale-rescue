/* 
 * EscapeRoom
 * Initializes room map, contains saving, loading, checking inventory function, and directs how player interacts with objects within room
 * @author Alyssa Gao, Joyce Huang, Julia Collins
 * June 14, 2018
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Toolkit;
import java.awt.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class EscapeRoom extends JFrame implements KeyListener, ComponentListener, WindowListener {
  
  // ===== VARIABLES ====
  // JFrame size
  public static final int SCREEN_WIDTH = 1366;
  public static final int SCREEN_HEIGHT = 738;
  
  public static Point location;
  
  private String currentGameDataFilename = null;
  
  private Player player = null;
  private Room currentRoom = null;
  private Object[][] roomMap; // map with objects
  private EscapeRoomPanel escapeRoomPanel = null;
  
  private boolean gameDataHasChangedSinceStartOrLoadOrSave = false; // helps with displaying message in the case the that player has not saved game after movement
  
  private List<InteractionStep> interactionStepsForSuccessfulExit = new ArrayList<InteractionStep>(); // dialogue if player has successfully exited
  
  // used in creating a 'notification'
  private Queue<Notification> notificationMessages = new ConcurrentLinkedQueue<Notification>();
  private JLabel notificationLabel = null; 
  
  private GameMenu menuFrame;
  Image riverdaleIcon = Toolkit.getDefaultToolkit().getImage("Riverdale Icon.jpg");
  private boolean hasExited = false;
  
  // ===== CONSTRUCTORS ====
  
  /**
   * Adds attributes to frame
   * @param menuFrame the frame that the rooms will be displaeyed within
   */
  public EscapeRoom(GameMenu menuFrame) {
    // Frame Attributes
    menuFrame.getMainWindow().setVisible(false);
    this.menuFrame = menuFrame;
    loadGame();
    setUndecorated(true);
    setIconImage(riverdaleIcon);
  }
  
  // ===== METHODS ====
  
  /**
   * init
   * Main initializing method that calls other initializing methods
   * @param room the current room
   * @param player the player (position, inventory, etc.)
   */
  public void init ( Room room, Player player ) {
    this.player = player;
    this.currentRoom = room;
    
    interactionStepsForSuccessfulExit.add(new InteractionStep("exit room message.png", null));
    
    initRoomMap();
    
    initGUI();
    
    addListeners();
    
    new ProcessTimedEvent().start();
  }
  
  /**
   * initGUI
   * Initializes the panel
   */
  public void initGUI() {
    this.currentRoom.loadRoomImage();
    
    this.escapeRoomPanel = new EscapeRoomPanel(this.roomMap, this.currentRoom, this.player);
    
    getContentPane().removeAll();
    setContentPane(this.escapeRoomPanel);
    this.escapeRoomPanel.setLayout(null);
    
    this.notificationLabel = new JLabel();
    this.notificationLabel.setBounds(450, 50, 400, 50);
    this.notificationLabel.setForeground(new Color(200, 200, 50));
    this.notificationLabel.setFont( new Font ( "Calibri", Font.BOLD, 12 ) );
    this.notificationLabel.setBackground(new Color(51, 62, 80));
    this.notificationLabel.setOpaque(true);
    this.notificationLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.YELLOW, Color.DARK_GRAY));
    this.notificationLabel.setVisible(false);
    add(this.notificationLabel);
    
    setTitle("Escape Room");
    setResizable(false);
    setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    addWindowListener(this);
    
    location = getLocation();
    
    revalidate();
    
  }
  
  /**
   * initRoomMap
   * Initializes room map
   */
  private void initRoomMap() {
    roomMap = new Object[this.currentRoom.getHeight()][this.currentRoom.getWidth()];
    // put items on map
    for (int i = 0; i < this.currentRoom.getItems().size(); i++) {
      Item item = this.currentRoom.getItems().get(i);
      roomMap[item.getPositionY()][item.getPositionX()] = item;
    }
    // put doors on map
    for (int i = 0; i < this.currentRoom.getDoors().size(); i++) {
      Door door = this.currentRoom.getDoors().get(i);
      Position doorPositionInRoom = door.getDoorPosition(this.currentRoom);
      roomMap[doorPositionInRoom.positionY][doorPositionInRoom.positionX] = door;
    }
    roomMap[player.getPositionY()][player.getPositionX()] = player;
    
  }
  
  /**
   * addListeners
   * Adds listeners such as keyboard, and component listener
   */
  private void addListeners() {
    addKeyListener(this);
    addComponentListener(this);
  }
  
  /**
   * loadGame
   * Loads game from saved file
   */
  public void loadGame() {
    // Read theme file
    ObjectInputStream ois = null;
    Door entryDoor = null;
    List<InteractionStep> themeStory = null;
    
    try {
      ois = new ObjectInputStream(new FileInputStream(SetupRoom.THEME_FILENAME));
      entryDoor = (Door) ois.readObject();
      themeStory = (List<InteractionStep>) ois.readObject();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return;
    } catch (IOException e) {
      e.printStackTrace();
      return;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      return;
    } finally {
      if (ois != null) {
        try {
          ois.close();
        } catch (IOException e) {
        }
      }
    }
    
    //beginning position for player in first room
    int playerX = entryDoor.getPlayerPositionInRoom1().positionX;
    int playerY = entryDoor.getPlayerPositionInRoom1().positionY;
    
    // player position and images for all four directions
    final Player player = new Player(playerX, playerY, "BettyLeft.png", "BettyRight.png", "BettyUp.png", "BettyDown.png");
    
    final Room room = entryDoor.getRoom1(); // creates beginning room
    final List<InteractionStep> themeStory0 = themeStory; // display story before actual game begins
    
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        init (room, player);
        new InteractionDialog ( themeStory0 );
        setVisible(true);
      }
    }
    );
  }
  
  /**
   * keyTyped
   * Method that is included with the keyListener interface
   * Checks if a specific key is typed
   */
  public void keyTyped(KeyEvent e) {
  }
  
  /**
   * keyPressed
   * Method that is included and has been overidden with the keyListener interface
   * Checks if key(s) is pressed
   * Used for moving player, saving game, quick saving, and check inventory
   */
  public void keyPressed(KeyEvent e) {
    if (this.player != null) {
      boolean moved = false;
      // change player direction accordingly
      if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        changePlayerDirection(Player.DIRECTION_LEFT);
        moved = processMovement( Player.DIRECTION_LEFT);
      } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        changePlayerDirection(Player.DIRECTION_RIGHT);
        moved = processMovement( Player.DIRECTION_RIGHT);
      } else if (e.getKeyCode() == KeyEvent.VK_UP) {
        changePlayerDirection(Player.DIRECTION_UP);
        moved = processMovement( Player.DIRECTION_UP);
      } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
        changePlayerDirection(Player.DIRECTION_DOWN);
        moved = processMovement( Player.DIRECTION_DOWN);
      } else if ((e.getKeyCode() == KeyEvent.VK_B) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
        // Ctrl+B: display inventory
        new InventoryDialog ( this.player.getBag() );
      } else if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
        // Ctrl+S: saving game
        File file = selectFileAndSaveGame();
        if (file != null) {
          saveGame(file);
        }
      } else if ((e.getKeyCode() == KeyEvent.VK_W) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
        // Ctrl+W: quick-saving game (using the last game file name if loaded before).
        if (this.currentGameDataFilename != null) {
          saveGame(new File(this.currentGameDataFilename));
        } else {
          JFileChooser fileopen = new JFileChooser();
          FileFilter filter = new FileNameExtensionFilter("game files", "riv");
          fileopen.addChoosableFileFilter(filter);
          
          int ret = fileopen.showDialog(this, "Save game file");
          if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            this.currentGameDataFilename = file.getAbsolutePath();
            saveGame(file);
          }
        }
      } else if ((e.getKeyCode() == KeyEvent.VK_L) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
        // Ctrl+L: loading game
        if (this.gameDataHasChangedSinceStartOrLoadOrSave) {
          boolean actionTaken = false;
          while (!actionTaken) {
            String message = "";
            if (this.currentGameDataFilename == null) {
              message = "Do you want save your game progress before loading another game progress?";
            } else {
              // if player has already saved game
              message = "Do you want save your game progress to your last loaded file before loading another game progress?";
            }
            int ret = JOptionPane.showConfirmDialog(this, message, "Load game file",
                                                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (ret == JOptionPane.YES_OPTION) {
              if (this.currentGameDataFilename == null) {
                File file = selectFileAndSaveGame();
                if (file == null) {
                  continue;
                } else {
                  this.currentGameDataFilename = file.getAbsolutePath();
                }
              }
              saveGame(new File(this.currentGameDataFilename));
            } else if (ret == JOptionPane.NO_OPTION) {
              // do nothing, go ahead for saving.
            } else if (ret == JOptionPane.CANCEL_OPTION) {
              return;
            }
            
            actionTaken = true;
          }
        }
        JFileChooser fileopen = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("game files", "riv");
        fileopen.addChoosableFileFilter(filter);
        
        int ret = fileopen.showDialog(this, "Load game file");
        if (ret == JFileChooser.APPROVE_OPTION) {
          File file = fileopen.getSelectedFile();
          this.currentGameDataFilename = file.getAbsolutePath();          
          loadGame(file);
        }
      } else if ((e.getKeyCode() == KeyEvent.VK_X) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
        exitGame();
      }
      
      if (moved) {
        repaint();
        this.gameDataHasChangedSinceStartOrLoadOrSave = true;
      }
    }
  }
  
  /**
   * selectFileAndSaveGame
   * Allows user to create file to save game
   */
  private File selectFileAndSaveGame() {
    
    JFileChooser fileopen = new JFileChooser();
    FileFilter filter = new FileNameExtensionFilter("game files", "riv"); // extension for file can either be 'gdat' or custom 'riv'
    fileopen.addChoosableFileFilter(filter);
    
    int ret = fileopen.showDialog(this, "Save game file");
    if (ret == JFileChooser.APPROVE_OPTION) {
      File file = fileopen.getSelectedFile();
      this.currentGameDataFilename = file.getAbsolutePath();
      
      return file;
    } else {
      return null;
    }
  }
  
  /**
   * saveGame
   * Allows user to save game in specified file
   * @param file The file that user wants to save game in
   */
  private void saveGame(File file) {
    ObjectOutputStream oos = null;
    try {
      oos = new ObjectOutputStream(new FileOutputStream(file));
      oos.writeObject(this.currentRoom);
      oos.writeObject(this.player);
      gameDataHasChangedSinceStartOrLoadOrSave = false;
      this.notificationMessages.add(new Notification("Game progress has been saved to " + file.getAbsolutePath(), 2000));
      this.notificationMessages.add(new Notification("To load the game, hit 'CTRL + L'", 1500));
    } catch (FileNotFoundException e1) {
      e1.printStackTrace();
      JOptionPane.showMessageDialog(this, "File does not exist. Please try again.", "Error in saving game", JOptionPane.ERROR_MESSAGE);
    } catch (IOException e1) {
      e1.printStackTrace();
      JOptionPane.showMessageDialog(this, "There is an error in saving the game data. Please try again.", "Error in saving game", JOptionPane.ERROR_MESSAGE);
    } finally {
      if (oos != null) {
        try {
          oos.close();
        } catch (IOException e) {
        }
      }
    }
  }
  
  /**
   * loadGame
   * Allows user to load game in specified file
   * @param file The file that user wants to load game from
   */
  private void loadGame(File file) {
    ObjectInputStream ois = null;
    try {
      ois = new ObjectInputStream(new FileInputStream(file));
      this.currentRoom = (Room) ois.readObject();
      this.player = (Player) ois.readObject();
      this.currentRoom.loadRoomImage();
      initRoomMap();
      initGUI();
      repaint();
      this.gameDataHasChangedSinceStartOrLoadOrSave = false;
      this.notificationMessages
        .add(new Notification("Game progress has been loaded from " + file.getAbsolutePath(), 2000));
    } catch (FileNotFoundException e1) {
      e1.printStackTrace();
      JOptionPane.showMessageDialog(this, "The file does not exist. Please try again.", 
                                    "Error in loading game",
                                    JOptionPane.ERROR_MESSAGE);
    } catch (IOException e1) {
      e1.printStackTrace();
      JOptionPane.showMessageDialog(this, "There is an error in loading the game data. Please try again.",
                                    "Error in loading game", 
                                    JOptionPane.ERROR_MESSAGE);
    } catch (ClassNotFoundException e1) {
      e1.printStackTrace();
      JOptionPane.showMessageDialog(this, "There is an error in loading the game data. Please try again.",
                                    "Error in loading game", 
                                    JOptionPane.ERROR_MESSAGE);
    } finally {
      if (ois != null) {
        try {
          ois.close();
        } catch (IOException e) {
        }
      }
    }
  }
  
  /**
   * changePlayerDirection
   * Player changes direction
   * @param direction the direction that player changes to
   */
  private void changePlayerDirection ( int direction ) {
    this.player.changeDirection(direction);
    repaint(); // after each direction change, player image is repainted
  }
  
  /**
   * keyReleased
   * Method that is included with the keyListener interface
   * Checks if a specific key is released
   */
  public void keyReleased(KeyEvent e) {
  }
  
  /**
   * componentResized
   * Method that is included with the ComponentListener
   */
  public void componentResized(ComponentEvent e) {
  }
  
  /**
   * componentMoved
   * Method that is included with the ComponentListener
   */
  public void componentMoved(ComponentEvent e) {
    location.x = e.getComponent().getX();
    location.y = e.getComponent().getY();
  }
  
  /**
   * componentShown
   * Method that is included with the ComponentListener
   */
  public void componentShown(ComponentEvent e) {
  }
  
  /**
   * componentHidden
   * Method that is included with the ComponentListener
   */
  public void componentHidden(ComponentEvent e) {
  }
  
  /* 
   * ProcessTimedEvent
   * Allows for multithreading so this can run in the background 
   * @author Alyssa Gao, Joyce Huang, Julia Collins
   * June 14, 2018
   */
  class ProcessTimedEvent extends Thread {
    @Override
    public void run() {
      while (true) {
        Notification notification = notificationMessages.poll();
        if (notification != null) {
          notificationLabel.setText(notification.getMessage());
          notificationLabel.setVisible(true);
          try {
            Thread.sleep(notification.getDisplayTime());
          } catch (InterruptedException e) {
          }
        } else {
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
          }
        }
        notificationLabel.setVisible(false);
      }
    }
  }
  
  /**
   * windowOpened
   * Method that is included with the WindowListener
   */
  public void windowOpened(WindowEvent e) {
  }
  
  /**
   * windowClosing
   * Method that is included with the WindowListener
   */
  public void windowClosing(WindowEvent e) {
    exitGame();
  }
  
  /**
   * windowClosed
   * Method that is included with the WindowListener
   */
  public void windowClosed(WindowEvent e) {
  }
  
  /**
   * windowIconified
   * Method that is included with the WindowListener
   */
  public void windowIconified(WindowEvent e) {
  }
  
  /**
   * windowDeiconified
   * Method that is included with the WindowListener
   */
  public void windowDeiconified(WindowEvent e) {
  }
  
  /**
   * windowActivated
   * Method that is included with the WindowListener
   */
  public void windowActivated(WindowEvent e) {
  }
  
  /**
   * windowDeactivated
   * Method that is included with the WindowListener
   */
  public void windowDeactivated(WindowEvent e) { 
  }
  
  /**
   * exitGame
   * Allows user to exit game
   */
  private void exitGame ( ) {
    if ( this.hasExited ) {
      // avoid double event.
      return;
    }
    int ret = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit the current game?", "Quit",
                                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (ret == JOptionPane.YES_OPTION) {
      if (this.gameDataHasChangedSinceStartOrLoadOrSave) {
        boolean actionTaken = false;
        while (!actionTaken) {
          String message = "";
          if (this.currentGameDataFilename == null) {
            message = "Do you want save your game progress before exiting?";
          } else {
            message = "Do you want save your game progress to your last loaded file before exiting?";
          }
          ret = JOptionPane.showConfirmDialog(this, message, "Quit",
                                              JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
          if (ret == JOptionPane.YES_OPTION) {
            if (this.currentGameDataFilename == null) {
              File file = selectFileAndSaveGame();
              if (file == null) {
                continue;
              } else {
                this.currentGameDataFilename = file.getAbsolutePath();
              }
            }
            saveGame(new File(this.currentGameDataFilename));
          } else if (ret == JOptionPane.NO_OPTION) {
            // do nothing, go ahead for saving.
          } else if (ret == JOptionPane.CANCEL_OPTION) {
            return;
          }
          
          actionTaken = true;
        }
      }
      endGame ( );
    }
  }
  
  /**
   * endGame
   * returns to menu screen after game has been ended
   */
  private void endGame ( ) {
    this.hasExited = true;
    this.menuFrame.getMainWindow().setVisible(true);
    this.menuFrame.returnToMainMenu();
    dispose();
  }
  
  /**
   * processMovement
   * processes the player's movement
   * @return true if player has moved, false if player hasn't moved
   */
  private boolean processMovement ( int direction ) {
    boolean moved = false;
    boolean canMove = false;
    int nextPositionX = 0;
    int nextPositionY = 0;
    switch ( direction ) {
      case Player.DIRECTION_LEFT:
        canMove = ( this.player.getPositionX() > this.currentRoom.getTopLeftCorner().positionX + 1 );
        nextPositionX = this.player.getPositionX() - 1;
        nextPositionY = this.player.getPositionY();
        break;
      case Player.DIRECTION_RIGHT:
        canMove = (this.player.getPositionX() < this.currentRoom.getTopRightCorner().positionX - 1);
        nextPositionX = this.player.getPositionX() + 1;
        nextPositionY = this.player.getPositionY();
        break;
      case Player.DIRECTION_UP:
        canMove = (this.player.getPositionY() > this.currentRoom.getTopLeftCorner().positionY + 1);
        nextPositionX = this.player.getPositionX();
        nextPositionY = this.player.getPositionY() - 1;
        break;
      case Player.DIRECTION_DOWN:
        canMove = (this.player.getPositionY() < this.currentRoom.getBottomRightCorner().positionY - 1);
        nextPositionX = this.player.getPositionX();
        nextPositionY = this.player.getPositionY() + 1;
        break;
    }
    
    if ( canMove ) {
      if (this.roomMap[nextPositionY][nextPositionX] == null) {
        this.roomMap[nextPositionY][nextPositionX] = this.player;
        this.roomMap[this.player.getPositionY()][this.player.getPositionX()] = null;
        this.player.setPositionX( nextPositionX );
        this.player.setPositionY( nextPositionY );
        moved = true;
      } else {
        // Hit an item, start the interaction
        if (this.roomMap[nextPositionY][nextPositionX] instanceof Item) {
          Item item = (Item) this.roomMap[nextPositionY][nextPositionX];
          Task completeTask = item.interact();
          if (completeTask != null) {
            this.player.getBag().add(completeTask);
          }
          moved = true;
        } else if (this.roomMap[nextPositionY][nextPositionX] instanceof Door) {
          Door door = (Door) this.roomMap[nextPositionY][nextPositionX];
          // Check if the door is locked. If so, we need to challenge task's question.
          if (!door.isUnlocked()) {
            // Door is locked, so need to challenge task.
            door.openDoor(this.player.getBag());
          }
          if (door.isUnlocked()) {
            if (door.isExitDoor()) {
              new InteractionDialog(this.interactionStepsForSuccessfulExit);
              endGame();
            } else {
              Room newRoom = door.getOtherRoom(this.currentRoom);
              if (newRoom != null) {
                this.currentRoom = newRoom;
                this.escapeRoomPanel.setRoom(newRoom);
                newRoom.loadRoomImage();
                this.player.setPosition(door.getOtherPlayerPosition(this.player.getPosition()));
                initRoomMap();
                this.escapeRoomPanel.setRoomMap(this.roomMap);
                this.notificationMessages.add(new Notification("You have entered the " + newRoom.getName(), 2500));
              }
            }
          }
          moved = true;
        }
      }
    }
    return moved;
  }
}