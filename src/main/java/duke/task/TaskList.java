package duke.task;

import java.util.ArrayList;

public class TaskList {
  
  private ArrayList<Task> tasks;

  public TaskList() {
    tasks = new ArrayList<>();
  }

  public TaskList(ArrayList<Task> tasks) {
    this.tasks = tasks;
  }

  public void addTask(Task task) {
    tasks.add(task);
  }

  public void removeTask(int index) {
    tasks.remove(index);
  }

  public Task getTask(int index) {
    return tasks.get(index);
  }

  public int getTaskCount() {
    return tasks.size();
  }

}
