package duke.command;

import duke.ui.Ui;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;


public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= tasks.getTaskCount()) {
            throw new DukeException("Invalid task number.");
        }
        Task task = tasks.getTask(index); // Adjusting the index here
        tasks.removeTask(index);
        storage.saveTasks(tasks);
        ui.print("Noted. I've removed this task:\n    " + task + "\nNow you have " + tasks.getTaskCount() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}