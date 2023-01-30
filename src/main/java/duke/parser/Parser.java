package duke.parser;

import duke.command.*;
import duke.exception.EmptyDescException;
import duke.exception.UnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Parses the user's commands into a readable
 * format for the other classes.
 */
public class Parser {

    /**
     * Represents all the valid commands that the user can input.
     */
    enum Commands {
        FIND,
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        LIST,
        BYE

    }



    /**
     * Matches the appropriate enum to the input converted
     * into a string, then creates the relevant command to be
     * executed. When an input is gibberish, an exception is
     * also thrown.
     *
     * @param fullCommand String representation of user input.
     * @return A command to be executed.
     * @throws UnknownCommandException Error when input does not match
     * any of the commands allowed.
     * @throws EmptyDescException Input does not contain a description.
     */
    public static Command parse(String fullCommand) throws UnknownCommandException, EmptyDescException {
        try {
            String[] inputArr = fullCommand.split(" ");
            Commands command = Commands.valueOf(inputArr[0].toUpperCase());
            String desc = "variable not initialised";
            StringBuilder sb = new StringBuilder();

            switch (command) {
                case TODO:
                    if (inputArr.length == 1) {
                        throw new EmptyDescException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    for (int i = 1; i < inputArr.length; i++) {
                        sb.append(inputArr[i]);
                        if (i != inputArr.length - 1) {
                            sb.append(" ");
                        }
                    }
                    Todo todo = new Todo(sb.toString().trim());
                    return new TodoCommand(todo);
                case DEADLINE:
                    if (inputArr.length == 1) {
                        throw new EmptyDescException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    for (int i = 1; i < inputArr.length; i++) {
                        if (inputArr[i].equals("/by")) {
                            desc = sb.toString();
                            sb.setLength(0);
                            continue;
                        }
                        sb.append(inputArr[i]);
                        if (i != inputArr.length - 1) {
                            sb.append(" ");
                        }
                    }
                    String[] dateTime = sb.toString().split(" ");
                    Deadline deadline = new Deadline(desc, dateTime[0], dateTime[1]);
                    return new DeadlineCommand(deadline);
                case EVENT:
                    if (inputArr.length == 1) {
                        throw new EmptyDescException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    String from = "local variable not initialised";
                    for (int i = 1; i < inputArr.length; i++) {
                        if (inputArr[i].equals("/from")) {
                            desc = sb.toString();
                            sb.setLength(0);
                            continue;
                        } else if (inputArr[i].equals("/to")) {
                            from = sb.toString();
                            sb.setLength(0);
                            continue;
                        }
                        sb.append(inputArr[i]);
                        if (i != inputArr.length - 1) {
                            sb.append(" ");
                        }
                    }
                    Event event = new Event(desc, from, sb.toString().trim());
                    return new EventCommand(event);
                case MARK:
                    return new MarkCommand(Integer.parseInt(inputArr[1]));
                case UNMARK:
                    return new UnmarkCommand(Integer.parseInt(inputArr[1]));
                case DELETE:
                    int inputIndex = Integer.parseInt(inputArr[1]);
                    return new DeleteCommand(inputIndex);
                case LIST:
                    return new ListCommand();
                case BYE:
                    return new ByeCommand();
                case FIND:
                    if (inputArr.length == 1) {
                        throw new EmptyDescException("☹ OOPS!!! You need to specify what you want to find!");
                    }
                    return new FindCommand(inputArr[1]);
                default:
                    throw new UnknownCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }
}