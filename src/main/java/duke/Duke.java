package duke;

import duke.ui.Parser;
import duke.ui.Ui;
import duke.command.Command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;


public class Duke {

  private Storage storage;
  private TaskList tasks;
  private Ui ui;
  private String username;

  public Duke(String chatbotName) {
    ui = new Ui(chatbotName);
    this.username = ui.getName();
    String filePath = "./data/" + username + "_tasks.txt";
    storage = new Storage(filePath);
    tasks = new TaskList(storage.loadTasks()); 
  }

  public void run() {

    boolean isExit = false;
    ui.welcomeMsg(username);
    while (!isExit) {
      try {
        String fullCommand = ui.readCommand();
        Command c = Parser.parse(fullCommand, username);
        c.execute(tasks, ui, storage);
        isExit = c.isExit();
      } catch (DukeException e) {
        ui.print(e.getMessage());
      }
    }

  }

  public static void main(String[] args) {
    Duke duke = new Duke("Tweety");
    duke.run();
  }
}

    
