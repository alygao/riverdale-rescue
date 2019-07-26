/* 
 * GameMenu
 * The game menu with buttons that can lead to playing the game, reading instructions, or exiting game
 * @author Alyssa Gao, Joyce Huang, Julia Collins
 * June 14, 2018
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.OverlayLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

public class GameMenu {
  
  // ==== VARIABLES ==== 
  
  // initialize all the GUI elements for the title screen
  private JFrame mainWindow = new JFrame("Riverdale Rescue");
  private JLayeredPane overallPanel = new JLayeredPane();
  private JPanel backgroundPanel = new JPanel();
  private JPanel buttonPanel = new JPanel();
  private JPanel levelsPanel = new JPanel();
  private JPanel instructionsButtonPanel = new JPanel();
  private JLabel titleScreen;
  private JLabel instructions1, instructions2, instructions3, instructions4;
  private JButton playButton, instructionsButton, exitButton;
  private JButton playFromInstructionsButton, nextButton, backButton, menuButton;
  private JButton easyButton, mediumButton, hardButton, levelsMenuExitButton;
  
  // create a MusicPlayer object to control the audio throughout the game
  private MusicPlayer musicPlayer = new MusicPlayer();
  
  // create files containing all the BGM for the game
  private static File titleBGMFile = new File("Riverdale Main Theme.wav");
  private static File easyLevelBGMFile = new File("Mad World.wav");
  private static File mediumLevelBGMFile = new File("Rumor.wav");
  private static File hardLevelBGMFile = new File("Undermine.wav");
  
  // create the image to be used as the icon for the game
  private static Image riverdaleIcon = Toolkit.getDefaultToolkit().getImage("Riverdale Icon.jpg");
  
  // create all image icons for the backgrounds when on the title screen and instructions
  private static ImageIcon titleScreenPicture = new ImageIcon("Title Screen.png");
  private static ImageIcon instructionsScreen1Picture = new ImageIcon("Instructions 1.png");
  private static ImageIcon instructionsScreen2Picture = new ImageIcon("Instructions 2.png");
  private static ImageIcon instructionsScreen3Picture = new ImageIcon("Instructions 3.jpg");
  private static ImageIcon instructionsScreen4Picture = new ImageIcon("Instructions 4.png");
  
  // create all the button pictures
  private static ImageIcon playButtonPicture = new ImageIcon("Play Button.jpg");
  private static ImageIcon instructionsButtonPicture = new ImageIcon("How To Play Button.jpg");
  private static ImageIcon exitButtonPicture = new ImageIcon("Exit Button.jpg");
  private static ImageIcon menuButtonPicture = new ImageIcon("Menu Button.jpg");
  private static ImageIcon easyButtonPicture = new ImageIcon("Easy Button.jpg");
  private static ImageIcon mediumButtonPicture = new ImageIcon("Medium Button.jpg");
  private static ImageIcon hardButtonPicture = new ImageIcon("Hard Button.jpg");
  private static ImageIcon nextButtonPicture = new ImageIcon("Next Button.png");
  private static ImageIcon backButtonPicture = new ImageIcon("Back Button.png");
  
  // create a variable that states which screen the instructions screen is showing
  private static int currentInstructionsScreen = 1;
  
  // creates a variable to store the escape room in when created;
  EscapeRoom starting;
  
  // set a GameMenu object so the code can refer to itself
  private GameMenu gameMenu = this;
  
  // getter that returns the main window of the game
  public JFrame getMainWindow() {
    return mainWindow;
  }
  
  // ==== METHODS ==== 
  
  /**
   * createTitleGUI
   * This method creates the title screen of the game, including level selection and instructions, and creates all the buttons
   */
  public void createTitleGUI() {
    
    // set parameters of the main window
    mainWindow.setIconImage(riverdaleIcon);
    mainWindow.setSize(1366, 702);
    mainWindow.setLocationRelativeTo(null);
    mainWindow.setResizable(false);
    mainWindow.setUndecorated(true);
    mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // create a label to display the background of the title image
    titleScreen = new JLabel(titleScreenPicture);
    
    // create labels to display the different instruction screens
    instructions1 = new JLabel(instructionsScreen1Picture);
    instructions2 = new JLabel(instructionsScreen2Picture);
    instructions3 = new JLabel(instructionsScreen3Picture);
    instructions4 = new JLabel(instructionsScreen4Picture);
    
    // set all the layouts of the panels
    overallPanel.setLayout(new OverlayLayout(overallPanel));
    buttonPanel.setLayout(null);
    instructionsButtonPanel.setLayout(null);
    levelsPanel.setLayout(null);
    
    // set all the panels overlaying other panels to not be opaque
    buttonPanel.setOpaque(false);
    instructionsButtonPanel.setOpaque(false);
    levelsPanel.setOpaque(false);
    
    // create all buttons on the title screen
    playButton = new JButton(playButtonPicture);
    instructionsButton = new JButton(instructionsButtonPicture);
    exitButton = new JButton(exitButtonPicture);
    easyButton = new JButton(easyButtonPicture);
    mediumButton = new JButton(mediumButtonPicture);
    hardButton = new JButton(hardButtonPicture);
    levelsMenuExitButton = new JButton(menuButtonPicture);
    playFromInstructionsButton = new JButton(playButtonPicture);
    menuButton = new JButton(menuButtonPicture);
    nextButton = new JButton(nextButtonPicture);
    backButton = new JButton(backButtonPicture);
    
    // create ActionListeners for all five buttons
    ActionListener buttonL = new ButtonListener();
    playButton.addActionListener(buttonL);
    instructionsButton.addActionListener(buttonL);
    exitButton.addActionListener(buttonL);
    easyButton.addActionListener(buttonL);
    mediumButton.addActionListener(buttonL);
    hardButton.addActionListener(buttonL);
    levelsMenuExitButton.addActionListener(buttonL);
    playFromInstructionsButton.addActionListener(buttonL);
    menuButton.addActionListener(buttonL);
    nextButton.addActionListener(buttonL);
    backButton.addActionListener(buttonL);
    
    // set locations of the buttons on the screen
    playButton.setBounds(500, 500, 300, 50);
    instructionsButton.setBounds(500, 550, 300, 50);
    exitButton.setBounds(500, 600, 300, 50);
    playFromInstructionsButton.setBounds(200, 600, 300, 50);
    menuButton.setBounds(800, 600, 300, 50);
    easyButton.setBounds(500, 450, 300, 50);
    mediumButton.setBounds(500, 500, 300, 50);
    hardButton.setBounds(500, 550, 300, 50);
    levelsMenuExitButton.setBounds(500, 600, 300, 50);
    backButton.setBounds(50, 300, 100, 56);
    nextButton.setBounds(1250, 300, 100, 56);
    
    // add the background and buttons to the panels
    backgroundPanel.add(titleScreen);
    buttonPanel.add(playButton);
    buttonPanel.add(instructionsButton);
    buttonPanel.add(exitButton);
    levelsPanel.add(easyButton);
    levelsPanel.add(mediumButton);
    levelsPanel.add(hardButton);
    levelsPanel.add(levelsMenuExitButton);
    
    // add the seperate panels to the JLayeredPane
    overallPanel.add(backgroundPanel, new Integer(0));
    overallPanel.add(buttonPanel, new Integer(100));
    
    // add the panel to the window
    mainWindow.add(overallPanel);
    mainWindow.pack();
    
    // make window visible
    mainWindow.setVisible(true);
    
    // set the music to the default for the title and play it
    musicPlayer.setCurrentSong(titleBGMFile);
    musicPlayer.playMusic();
    
  }
  
  /**
   * setupInstructions
   * This method decides which aspects of the four potential instructions screens to display
   * @param  currentScreen  An integer that represents which of the four instructions screens to display
   */
  private void setupInstructions(int currentScreen) {
    
    // clear the button panel and re-add the constant play and menu buttons
    instructionsButtonPanel.removeAll();
    instructionsButtonPanel.add(playFromInstructionsButton);
    instructionsButtonPanel.add(menuButton);
    
    // checks which directional buttons should be added
    if (currentScreen < 4) {
      instructionsButtonPanel.add(nextButton); // adds next button
    }
    
    if (currentScreen > 1) {
      instructionsButtonPanel.add(backButton); // adds back button
    }
    
    // clear the current background and add whichever instructions screen should be displayed
    backgroundPanel.removeAll();
    if (currentScreen == 1) {
      backgroundPanel.add(instructions1);
    } else if (currentScreen == 2) {
      backgroundPanel.add(instructions2);
    } else if (currentScreen == 3) {
      backgroundPanel.add(instructions3);
    } else {
      backgroundPanel.add(instructions4);
    }
    
    // clear the layered panel and add back the background with the buttons overlaid on top
    overallPanel.removeAll();
    overallPanel.add(backgroundPanel, new Integer(0));
    overallPanel.add(instructionsButtonPanel, new Integer(100));
    mainWindow.pack();
    mainWindow.repaint();
    
  }
  
  /**
   * returnToMainMenu
   * This method sets up the JFrame to the original state of the main title screen menu
   */
  public void returnToMainMenu() {
    
    if (musicPlayer.getCurrentSong() != titleBGMFile) { // checks if the title BGM is still playing
      musicPlayer.stopMusic();
      musicPlayer.setCurrentSong(titleBGMFile);
      musicPlayer.playMusic();
    }
    
    // clear the entire layered panel and add back everything the title screen contains
    overallPanel.removeAll();
    overallPanel.add(backgroundPanel, new Integer(0));
    overallPanel.add(buttonPanel, new Integer(100));
    backgroundPanel.removeAll();
    backgroundPanel.add(titleScreen);
    mainWindow.pack();
    mainWindow.repaint();
    
  }
  
  /* 
   * ButtonListener
   * Used to create and allow all buttons to function (calling a specific level, returning to menu, etc.)
   * @author Alyssa Gao, Joyce Huang, Julia Collins
   * June 14, 2018
   */
  class ButtonListener implements ActionListener {
    
    // ==== METHOD ==== 
    
    /**
     * actionPerformed
     * This method looks at whether the button has been pressed and proceeds with steps depending on which button was pressed
     * @press sees if button is pressed
     */
    public void actionPerformed(ActionEvent press) {
      if ((press.getSource() == playButton) || (press.getSource() == playFromInstructionsButton)) { // if any play button is pressed
        // clears frame and places all the frames needed for level selection
        backgroundPanel.removeAll();
        backgroundPanel.add(titleScreen);
        overallPanel.removeAll();
        overallPanel.add(backgroundPanel, new Integer(0));
        overallPanel.add(levelsPanel, new Integer(100));
        mainWindow.pack();
        mainWindow.repaint();
      } else if (press.getSource() == instructionsButton) { // if the instructions button is pressed
        setupInstructions(1); // draws the first screen of the instructions
      } else if (press.getSource() == nextButton) { // if the next button on the instructions page is pressed
        currentInstructionsScreen++;
        setupInstructions(currentInstructionsScreen); // draws the screen next to the current screen of instructions
      } else if (press.getSource() == backButton) { // if the back button on the instructions page is pressed
        currentInstructionsScreen--;
        setupInstructions(currentInstructionsScreen); // draws the screen before the current screen of instructions
      } else if ( (press.getSource() == levelsMenuExitButton) || (press.getSource() == menuButton) ) { // if any of the menu buttons are pressed
        returnToMainMenu();
      } else if (press.getSource() == easyButton) { // if the easy level is selected
        // plays the easy level music
        musicPlayer.stopMusic();
        musicPlayer.setCurrentSong(easyLevelBGMFile);
        musicPlayer.playMusic();
        SetupRoom.setupEasyLevelRoom(); // sets up the easy level
        starting = new EscapeRoom(gameMenu); // starts the escape room
      } else if (press.getSource() == mediumButton) { // if the medium level is selected
        // plays the medium level music
        musicPlayer.stopMusic();
        musicPlayer.setCurrentSong(mediumLevelBGMFile);
        musicPlayer.playMusic();
        SetupRoom.setupMediumLevelRoom(); // sets up the medium level
        starting = new EscapeRoom(gameMenu); // starts the escape room
      } else if (press.getSource() == hardButton) { // if the hard level is selected
        // plays the hard level music
        musicPlayer.stopMusic();
        musicPlayer.setCurrentSong(hardLevelBGMFile);
        musicPlayer.playMusic();
        SetupRoom.setupHardLevelRoom(); // sets up the hard level
        starting = new EscapeRoom(gameMenu); // starts the escape room
      } else if (press.getSource() == exitButton) { // if the exit button is pressed
        musicPlayer.stopMusic();
        System.exit(0); // quits game
      }
    }
  }
}