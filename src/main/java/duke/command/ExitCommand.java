package duke.command;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

public class ExitCommand extends Command {

  private String username;

  public ExitCommand(String username) {
    this.username = username;
  }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sayGoodbye(username);
    }

  public boolean isExit() {
        return true;
    }
}

