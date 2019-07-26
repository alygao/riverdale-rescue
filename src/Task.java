/* 
 * Task
 * Abstract class for a task template
 * @author Alyssa Gao, Joyce Huang, Julia Collins
 * June 14, 2018
 */

import java.io.Serializable;

public abstract class Task implements Serializable {
  
  // ==== VARIABLES ====
  private boolean taskComplete = false; // default is incomplete task
  private Task dependentTask = null; //the dependent task that must be complete first
  private boolean localTask = false; // default is that task is not a local task
  private String name; // name of task
  private String iconImageFilename; // image for task
  
  // ==== CONSTRUCTORS ====
  
  /**
   * @param name name of task (for inventory)
   * @param iconImageFilename image for task (for inventory)
   * @param dependentTask the dependent task that must be complete first
   */
  Task(String name, String iconImageFilename, Task dependentTask) {
    this.iconImageFilename = iconImageFilename;
    this.dependentTask = dependentTask;
    this.name = name;
  }
  
  // ==== METHODS ====
  
  /**
   * complete
   * abstract method to see whether or not task is complete
   * @return boolen whether or not task is complete
   */
  public abstract boolean complete();
  
  /**
   * isTaskComplete
   * checks if task is complete
   * @return whether or not task is complete
   */
  public boolean isTaskComplete() {
    return this.taskComplete;
  }
  
  /**
   * setTaskComplete
   * sets task as either complete or not complete
   * @param true, if task is complete ... false if task is not complete
   */
  public void setTaskComplete(boolean taskComplete) {
    this.taskComplete = taskComplete;
  }
  
  /**
   * getDependentTask
   * gets dependent task
   * @return dependent task
   */
  public Task getDependentTask() {
    return dependentTask;
  }
  
  /**
   * setDependentTask
   * sets dependent task
   * @param dependent task
   */
  public void setDependentTask(Task dependentTask) {
    this.dependentTask = dependentTask;
  }
  
  /**
   * isLocalTask
   * checks if task is local task
   * @return whether or not task is local task
   */
  public boolean isLocalTask() {
    return localTask;
  }
  
  /**
   * setLocalTask
   * sets task as either local task or not 
   * @param true, if task is local task ... false if task is not local task
   */
  public void setLocalTask(boolean localTask) {
    this.localTask = localTask;
  }
  
  /**
   * getName
   * gets task name
   * @return task name
   */
  public String getName() {
    return name;
  }
  
  /**
   * getIconImageFilename
   * gets icon image filename to display in inventory
   * @return con image filename
   */
  public String getIconImageFilename() {
    return iconImageFilename;
  }
}