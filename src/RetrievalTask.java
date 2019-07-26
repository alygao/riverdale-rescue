import java.util.List;

public class RetrievalTask extends Task {
  
  // ==== VARIABLES ====
  private List<InteractionStep> interactionStepsForObjectObtained; // messages for object being obtained
  private List<InteractionStep> interactionStepsForTaskCompleted; // messages for task being completed 
  private List<InteractionStep> interactionStepsForIncompletDependentTask; // messages for incomplete dependent task
  
   // ==== CONSTRUCTORS ====
  
  /**
   * @param name name of the task (used in inventory)
   * @param iconImageFilename image of object retrieved (used in inventory)
   * @param dependentTask dependent task needed to be completed befrore object can be obtained
   * @param interactionStepsForObjectObtained messages shown when object is obtained
   * @param interactionStepsForTaskCompleted messages shown once task is complete
   * @param interactionStepsForIncompletDependentTask messages for incomplete dependent task
   */
  RetrievalTask(String name, String iconImageFilename, Task dependentTask, List<InteractionStep> interactionStepsForObjectObtained,
                List<InteractionStep> interactionStepsForTaskCompleted,
                List<InteractionStep> interactionStepsForIncompletDependentTask) {
    super(name, iconImageFilename, dependentTask);
    this.interactionStepsForObjectObtained = interactionStepsForObjectObtained;
    this.interactionStepsForTaskCompleted = interactionStepsForTaskCompleted;
    this.interactionStepsForIncompletDependentTask = interactionStepsForIncompletDependentTask;
  }
  
  // ==== METHODS ====
  
  /**
   * complete
   * Checks if task/depedent task is complete
   * @param panel 
   * @return whether or not task is complete, to see whether or not the object can be retrieved
   */
  public boolean complete() {
    if (isTaskComplete()) {
      new InteractionDialog(this.interactionStepsForTaskCompleted);
    } else {
      
      // complete the dependent task first, if it exists.
      if (getDependentTask() != null) {
        if (getDependentTask().isLocalTask()) {
          if ( !getDependentTask().complete() ) {
            // if dependent task is incomplete, then display messages for incomplete dependent task
            new InteractionDialog(this.interactionStepsForIncompletDependentTask);
          }
        } else {
          if (!getDependentTask().isTaskComplete()) {
            new InteractionDialog(this.interactionStepsForIncompletDependentTask);
          }
        }
      }
      
      // complete this task
      if (getDependentTask() == null || getDependentTask().isTaskComplete()) {
        new InteractionDialog(this.interactionStepsForObjectObtained);
        this.setTaskComplete(true);
      }
    }
    return isTaskComplete();
  }
}