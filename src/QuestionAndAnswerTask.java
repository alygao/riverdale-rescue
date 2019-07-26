/* 
 * QuestionAndAnswerTask
 * Tasks that ask a question and expect and answer in return
 * @author Alyssa Gao, Joyce Huang, Julia Collins
 * June 14, 2018
 */

import java.util.List;

public class QuestionAndAnswerTask extends Task {
  
  // ==== VARIABLES ====
  private List<InteractionStep> interactionStepsForMessageAndQuestions; // messages that will be displayed while is the task is still incomplete 
  private List<InteractionStep> interactionStepsForTaskCompleted; // messages that will be displayed once task is complete
  
  // ==== CONSTRUCTORS ====
   /**
   * @param name name of the task (used in inventory)
   * @param iconImageFilename image of object retrieved (used in inventory)
   * @param interactionStepsForMessageAndQuestions messages shown while task is still incomplete
   * @param interactionStepsForTaskCompleted messages shown once task is complete
   */
  QuestionAndAnswerTask(String name, String iconImageFilename, List<InteractionStep> interactionStepsForMessageAndQuestions,List<InteractionStep> interactionStepsForTaskCompleted) {
    super(name, iconImageFilename, null);
    this.interactionStepsForMessageAndQuestions = interactionStepsForMessageAndQuestions;
    this.interactionStepsForTaskCompleted = interactionStepsForTaskCompleted;
    setLocalTask(true);
  }
  
  // ==== METHODS ====
  
  /**
   * complete
   * Checks if task/depedent task is complete
   * @param panel 
   * @return whether or not task is complete
   */
  public boolean complete() {
    
    if (isTaskComplete()) {
      new InteractionDialog(interactionStepsForTaskCompleted);
    } else {
      new InteractionDialog(interactionStepsForMessageAndQuestions);
      
      // verify the answer and mark taskComplete accordingly
      if ((getDependentTask() == null) || (getDependentTask().isTaskComplete())) { // if either there is no dependent task or dependent task is complete
        boolean taskHasComplete = true;
        for (int i = 0; i < interactionStepsForMessageAndQuestions.size(); i++) {
          if (interactionStepsForMessageAndQuestions.get(i).requiresAnswer) {
            if (interactionStepsForMessageAndQuestions.get(i).questionIsAnswered) {
              if (!interactionStepsForMessageAndQuestions.get(i).correctAnswer.equals(interactionStepsForMessageAndQuestions.get(i).inputAnswer)) {
                // inputted answer is correct
                taskHasComplete = false;
                break;
              }
            } else {
              taskHasComplete = false;
              break;
            }
          }
        }
        this.setTaskComplete(taskHasComplete);
      }
    }
    return isTaskComplete();
  }
}