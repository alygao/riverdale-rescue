/* 
 * MusicPlayer
 * Plays music file
 * @author Alyssa Gao, Joyce Huang, Julia Collins
 * June 14, 2018
 */

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import java.io.File;

class MusicPlayer {
  
  // ==== VARIABLES ====
  // declare all variables required for playing audio
  private static AudioInputStream audioStream;
  private static DataLine.Info info;
  private static Clip clip;
  private static File currentSong;
  
  // ==== METHODS ====
  /**
   * getCurrentSong
   * Gets the current song
   * @return the current song
   */
  public File getCurrentSong() {
    return currentSong;
  }
  
  /**
   * setCurrentSong
   * Sets the current song
   * @param musicFile the file that is to be set as the current song
   */
  public void setCurrentSong(File musicFile) {
    currentSong = musicFile;
  }
  
  /**
   * playMusic
   * This method takes the current song stored in the object and begins to play it
   */
  public void playMusic() {
    try {
      // sets up the song to be played
      audioStream = AudioSystem.getAudioInputStream(currentSong);
      info = new DataLine.Info(Clip.class, audioStream.getFormat());
      clip = (Clip) AudioSystem.getLine(info);
      
      // plays the music
      clip.open(audioStream);
      clip.loop( Clip.LOOP_CONTINUOUSLY );
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * stopMusic
   * This method stops the current music that is playing
   */
  public void stopMusic() {
    // stops the music
    clip.close();
  }
}