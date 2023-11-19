package duke.ui;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;

public class Parser {
  public static Command parse(String fullCommand, String username) throws DukeException {
    String[] commandStrings = fullCommand.split(" ", 2);
    String command = commandStrings[0];
    String arguments = commandStrings.length > 1? commandStrings[1] : "";

    switch (command) {
            case "list":
                return new ListCommand();
            case "mark":
            case "unmark":
            case "delete":
                if (arguments.isEmpty()) {
                    throw new DukeException("The command '" + command + "' requires a task number.");
                }
                try {
                    int index = Integer.parseInt(arguments) - 1;
                    switch (command) {
                        case "mark":
                            return new MarkCommand(index);
                        case "unmark":
                            return new UnmarkCommand(index);
                        case "delete":
                            return new DeleteCommand(index);
                    }
                } catch (NumberFormatException e) {
                    throw new DukeException("The command '" + command + "' requires a numerical value.");
                }
                break;
            case "todo":               
            case "deadline":                
            case "event":                
                return new AddCommand(command, arguments);
            case "bye":
                return new ExitCommand(username);
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
         throw new DukeException("Unexpected error in parsing command.");
    }
}
