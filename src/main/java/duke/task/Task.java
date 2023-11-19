package duke.task;

public abstract class Task {
  protected String description;
  protected boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

public String getDescription() {
    return this.description;
  }


  public String getStatusIcon() {
    return (isDone? "X": " ");
  }

  public String toString() {
    return "[" + getStatusIcon() + "] " + description;
  }

  public void setDone() {
    this.isDone = true;
  }

  public void setUndone() {
    this.isDone = false;
  }

  public abstract String toFileString();

}
