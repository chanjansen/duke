import java.util.Scanner;
import java.util.ArrayList;


public class Duke {

    static String name = "Tweety";
    static String indent = "    ";


    private static void printLine() {
        System.out.println(indent + "____________________________________________________________");
    }

    private static void print(String msg) {
        printLine();
        String[] lines = msg.split("\n");
        for (String line: lines) {
            System.out.println(indent + line);
        }
        printLine();
    }

    

    private static void printList(ArrayList<Task> list) {
        String msg ="Here are the tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            Task item = list.get(i);
            msg += (i+1) + ". [" + item.getStatusIcon() +  "] " + item.getDescription() + "\n";
        }
        print(msg);
    }

    private static void markTask(Task task) {
        task.setDone();
        String msg = "Nice! I've marked this task as done:\n";
        msg += indent + "[" + task.getStatusIcon() +  "] " + task.getDescription() + "\n";
        print(msg);
    }

    private static void unMarkTask(Task task) {
        task.setUndone();
        String msg = "Ok, I've marked this task as not done yet:\n";
        msg += indent + "[" + task.getStatusIcon() +  "] " + task.getDescription() + "\n";
        print(msg);
    }


    public static void main(String[] args) {

        String greeting = "Hello I'm " + name + "\n" + "What can I do for you?";
        String goodbye = "Bye. Hope to see you again soon!";

        ArrayList<Task> tasks = new ArrayList<>();
        print(greeting);

        Scanner in = new Scanner(System.in);
        String msg ="";

        while (!msg.equals("bye")) {

            msg = in.nextLine();
            if (msg.equals("list")) {
                printList(tasks);
            }
            else if (msg.startsWith("mark")) {
                int itemNo = Integer.parseInt(msg.split(" ")[1]) -1;
                if (itemNo >= 0 && itemNo < tasks.size()) {
                    markTask(tasks.get(itemNo));
                } else {
                    print("Invalid task number");
                }
            } else if (msg.startsWith("unmark")) {
                int itemNo = Integer.parseInt(msg.split(" ")[1]) -1;
                if (itemNo >= 0 && itemNo < tasks.size()) {
                    unMarkTask(tasks.get(itemNo));
                } else {
                    print("Invalid task number");
                }
            } 
            else {
                tasks.add(new Task(msg));
                print("added: " + msg);
            }
        }

        print(goodbye);
    }
}
