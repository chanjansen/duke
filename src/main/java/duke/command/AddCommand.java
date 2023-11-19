package duke.command;

import duke.ui.Ui;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;


public class AddCommand extends Command {
    private String taskType;
    private String taskDetails;

    public AddCommand(String taskType, String taskDetails) {
        this.taskType = taskType;
        this.taskDetails = taskDetails;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask = null;
        switch (taskType) {
            case "todo":
                if (taskDetails.isEmpty()) {
                    throw new DukeException("Incorrect format for todo. Use todo [description].");
                }
                newTask = new Todo(taskDetails);
                break;
            case "deadline":
                String[] deadlineParts = taskDetails.split("/by");
                if (deadlineParts.length < 2) {
                    throw new DukeException("Incorrect format for deadline. Use: deadline [description] /by [time]");
                }
                newTask = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
                break;
            case "event":
                String[] eventParts = taskDetails.split("/from|/to");
                if (eventParts.length < 3) {
                    throw new DukeException("Incorrect format for event. Use: event [description] /from [start time] /to [end time]");
                }
                newTask = new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());
                break;
            default:
                throw new DukeException("Unknown task type: " + taskType);
        }
        tasks.addTask(newTask);
        storage.saveTasks(tasks);
        ui.print("Got it. I've added this task:\n    " + newTask + "\nNow you have " + tasks.getTaskCount() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

