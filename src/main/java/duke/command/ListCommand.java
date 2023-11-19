package duke.command;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
      if (tasks.getTaskCount() == 0) {
        ui.print("There are no tasks in your list");
      } else {
        ui.printList(tasks);
      }   
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

