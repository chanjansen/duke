package duke.ui;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;


public class Ui {

  static String indent = "    ";
  private Scanner scanner;
  private String chatbotName;

  public Ui(String chatbotName) {
    this.scanner = new Scanner(System.in);
    this.chatbotName = chatbotName;
  }

  public void printLine() {
    System.out.println(
      indent + "____________________________________________________________"
    );
  }

  public void print(String msg) {
    printLine();
    String[] lines = msg.split("\n");
    for (String line : lines) {
      System.out.println(indent + line);
    }
    printLine();
  }

  public String getName() {
    print("Hello! I'm " +  chatbotName + "\nBefore we start, can I have your name?");
    return scanner.nextLine().trim();
  }

  public void welcomeMsg(String username) {
    String msg = "Hello " + username + "! " + "I'm " + chatbotName + ", your personal assistant.\n" +
                 "You can ask me to add, list, mark, unmark, or delete tasks.\n" + 
                 "Just type 'todo', 'deadline', 'event', 'list', 'mark', 'unmark' or 'delete' followed by your task details.\n" +
                 "Type 'bye' when you're done. How can I assist you today?";
    print(msg);
  }


  public void sayGoodbye(String username) {
    print("Bye " + username + ". Just enter the same name again to retrieve your tasks!\nHope to see you again soon!");
  }

  public void printList(TaskList taskList) {
    String msg = "Here are the tasks in your list:\n";
    for (int i = 0; i < taskList.getTaskCount(); i++) {
      Task item = taskList.getTask(i);
      msg += (i + 1) + ". " + item.toString() + "\n";
    }
    print(msg);
  }

  public void showLoadingError() {
    print("Unable to load file");
  }

  public String readCommand() {
    return scanner.nextLine();
  }

  public void closeScanner() {
    if (scanner != null) {
      scanner.close();
    }
  }

}
