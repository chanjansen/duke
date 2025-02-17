package duke.command;

import duke.ui.Ui;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;


public abstract class Command {
  public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
  public abstract boolean isExit();
}
