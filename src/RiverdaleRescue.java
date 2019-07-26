/* 
 * RiverdaleRescue
 * To start game, this class must be run so it will call GameMenu
 * @author Alyssa Gao, Joyce Huang, Julia Collins
 * June 14, 2018
 */ 

class RiverdaleRescue {
  public static void main(String[] args) {
    GameMenu mainGUI = new GameMenu();
    mainGUI.createTitleGUI();
  }
}