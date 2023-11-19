package duke.storage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class Storage {

  private String filepath;

  public Storage(String filepath) {
    this.filepath = filepath;
  }

  public ArrayList<Task> loadTasks() {
    ArrayList<Task> tasks = new ArrayList<>();
    try {
      File file = new File(filepath);
      if (file.exists()) {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
          String line = scanner.nextLine();
          String type = line.split(" \\| ")[0];
          Task task;
          switch (type) {
            case "T":
              task = Todo.fromFileString(line);
              break;
            case "D":
              task = Deadline.fromFileString(line);
              break;
            case "E":
              task = Event.fromFileString(line);
              break;
            default:
              throw new IllegalArgumentException("Unknown task type: " + type);
          }
          tasks.add(task);
        }
        scanner.close();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return tasks;
  }

  public void saveTasks(TaskList taskList) {
    try {
      File file = new File(filepath);
      file.getParentFile().mkdirs();
      FileWriter fw = new FileWriter(file);
      for (int i = 0; i < taskList.getTaskCount(); i++) {
        Task task = taskList.getTask(i);
        fw.write(task.toFileString() + "\n");
      }
      fw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
