import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

  static String name = "Tweety";
  static String indent = "    ";

  private static void printLine() {
    System.out.println(
      indent + "____________________________________________________________"
    );
  }

  private static void print(String msg) {
    printLine();
    String[] lines = msg.split("\n");
    for (String line : lines) {
      System.out.println(indent + line);
    }
    printLine();
  }

  private static void printList(ArrayList<Task> list) {
    String msg = "Here are the tasks in your list:\n";
    for (int i = 0; i < list.size(); i++) {
      Task item = list.get(i);
      msg += (i + 1) + ". " + item.toString() + "\n";
    }
    print(msg);
  }

  private static void markTask(Task task) {
    task.setDone();
    String msg = "Nice! I've marked this task as done:\n";
    msg += indent + task.toString() + "\n";
    print(msg);
  }

  private static void unMarkTask(Task task) {
    task.setUndone();
    String msg = "Ok, I've marked this task as not done yet:\n";
    msg += indent + task.toString() + "\n";
    print(msg);
  }

  public static void main(String[] args) {
    String greeting = "Hello I'm " + name + "\n" + "What can I do for you?";
    String goodbye = "Bye. Hope to see you again soon!";

    ArrayList<Task> tasks = new ArrayList<>();
    print(greeting);

    Scanner in = new Scanner(System.in);
    String msg = "";

    while (!msg.equals("bye")) {
      try {
        msg = in.nextLine();
        if (msg.equals("list")) {
          printList(tasks);
        } else if (msg.startsWith("mark")) {
          try {
            int itemNo = Integer.parseInt(msg.split(" ")[1]) - 1;
            if (itemNo >= 0 && itemNo < tasks.size()) {
              markTask(tasks.get(itemNo));
            } else {
              print("Invalid task number");
            }
          } catch (NumberFormatException e) {
            print("OOPS!!! Please provide a valid task number to mark.");
          }
        } else if (msg.startsWith("unmark")) {
          int itemNo = Integer.parseInt(msg.split(" ")[1]) - 1;
          if (itemNo >= 0 && itemNo < tasks.size()) {
            unMarkTask(tasks.get(itemNo));
          } else {
            print("Invalid task number");
          }
        } else {
          String taskType = msg.split(" ")[0];
          String description;
          switch (taskType) {
            case "todo":
              description = msg.substring(4).trim();
              if (description.isEmpty()) {
                throw new DukeException(
                  "OOPS!!! The description of a todo cannot be empty."
                );
              }
              tasks.add(new Todo(description));
              print(
                "Got it. I've added this task:\n" +
                indent +
                tasks.get(tasks.size() - 1).toString() +
                "\nNow you have " +
                tasks.size() +
                " tasks in the list."
              );
              break;
            case "deadline":
              String[] deadlineDetails = msg.split("deadline|/by");
              description = deadlineDetails[1].trim();
              String by = deadlineDetails[2].trim();
              tasks.add(new Deadline(description, by));
              print(
                "Got it. I've added this task:\n" +
                indent +
                tasks.get(tasks.size() - 1).toString() +
                "\nNow you have " +
                tasks.size() +
                " tasks in the list."
              );
              break;
            case "event":
              String[] eventDetails = msg.split("/from|/to");
              description = eventDetails[0].substring(6).trim();
              String from = eventDetails[1].trim();
              String to = eventDetails[2].trim();
              tasks.add(new Event(description, from, to));
              print(
                "Got it. I've added this task:\n" +
                indent +
                tasks.get(tasks.size() - 1).toString() +
                "\nNow you have " +
                tasks.size() +
                " tasks in the list."
              );
              break;
            default:
              throw new DukeException(
                "OOPS!!! I'm sorry, but I don't know what that means :-("
              );
          }
        }
      } catch (DukeException e) {
        print(e.getMessage());
      }
    }
    in.close();
    print(goodbye);
  }
}
