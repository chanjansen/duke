package duke.command;

import duke.ui.Ui;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= tasks.getTaskCount()) {
            throw new DukeException("Invalid task number.");
        }
        Task task = tasks.getTask(index); 
        task.setDone();
        storage.saveTasks(tasks);
        ui.print("Nice! I've marked this task as done:\n    " + task);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}

