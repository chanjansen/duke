package duke.task;

public class Todo extends Task {
  protected String by;

  public Todo(String description) {
    super(description);
  }

  @Override
  public String toString() {
    return "[T]" + super.toString();
  }

  public static Todo fromFileString(String str) {
    String[] parts = str.split(" \\| ");
    Todo todo = new Todo(parts[2]);
    if (parts[1].equals("1")) {
      todo.setDone();
    }
    return todo;
  }

  public String toFileString() {
    return "T | " + (isDone ? "1" : "0") + " | " + description;
  }
}