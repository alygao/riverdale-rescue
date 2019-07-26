/* 
 * InteractionStep
 * Template for each interaction step within interaction dialog
 * @author Alyssa Gao, Joyce Huang, Julia Collins
 * June 14, 2018
 */

import java.io.Serializable;

public class InteractionStep implements Serializable {
  
 // ==== VARIABLES ====
 String messageImageFilename; // filename for dialogue image
 boolean requiresAnswer = false; //default is that dialog does not require answer
 String inputAnswer; // answer inputted from user
 String correctAnswer; // the correct answer that 'inputAnswer' will be compared to
 boolean questionIsAnswered = false; //default to question being unanswered 
 int x = -1; // -1: default to calculated value
 int y = -1; // -1: default to calcualted value

 // ==== CONSTRUCTORS ====
 
 /**
  * takes in standard message with no answer needed and using defalt position
  * @param messageImageFilename filename for dialogue image
  */
 public InteractionStep(String messageImageFilename) {
  this(messageImageFilename, null, -1, -1); }

 /**
  * takes in standard message with answer needed and using defalt position
  * @param messageImageFilename filename for dialogue image
  * @param correctAnswer the correct answer that will be compared to
  */
 public InteractionStep(String messageImageFilename, String correctAnswer) {
  this(messageImageFilename, correctAnswer, -1, -1); }

 /**
  * takes in standard message with or without answer and using custom position
  * @param messageImageFilename filename for dialogue image
  * @param correctAnswer the correct answer that will be compared to
  * @param x how much the image neesds to be shifted horizontally
  * @param y how much the image neesds to be shifted vertically
  */
 public InteractionStep(String messageImageFilename, String correctAnswer, int x, int y) {
  this.messageImageFilename = messageImageFilename;
  if (correctAnswer != null) {
   this.requiresAnswer = true;
   this.correctAnswer = correctAnswer;
  }
  this.x = x;
  this.y = y;
 }
}