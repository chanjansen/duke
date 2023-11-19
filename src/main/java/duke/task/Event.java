package duke.task;

public class Event extends Task {

  protected String from;
  protected String to;

  public Event(String description, String from, String to) {
    super(description);
    this.from = from;
    this.to = to;
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
  }

  public static Event fromFileString(String str) {
    String[] parts = str.split(" \\| ");
    Event event = new Event(parts[2], parts[3], parts[4]);
    if (parts[1].equals("1")) {
      event.setDone();
    }
    return event;
  }

  public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
  }
}
