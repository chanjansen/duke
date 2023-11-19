package duke.task;

public class Deadline extends Task {
  
  protected String by;

  public Deadline(String description, String by) {
    super(description);
    this.by = by;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + by + ")";
  }

  public static Deadline fromFileString(String str) {
    String[] parts = str.split(" \\| ");
    Deadline deadline = new Deadline(parts[2], parts[3]);
    if (parts[1].equals("1")) {
      deadline.setDone();
    }
    return deadline;
  }

  public String toFileString() {
    return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
  }

}