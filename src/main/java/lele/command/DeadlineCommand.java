package lele.command;

import lele.storage.Storage;
import lele.task.Deadline;
import lele.task.TaskList;
import lele.ui.Ui;

import java.io.IOException;

/**
 * Handles the actions taken for a
 * request to create a deadline.
 */
public class DeadlineCommand extends Command {
    private final Deadline deadline;

    /**
     * Instantiates the deadline instance passed from
     * another class.
     *
     * @param deadline Deadline instance created from another
     *                 class.
     */
    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    /**
     * Adds task to task list, updates the storage and
     * finally prints the task added to command line.
     *
     * @param taskList Current task list instance.
     * @param ui Current ui instance.
     * @param storage Current storage instance.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.addTasks(deadline);
            storage.updateStorage(taskList);
            ui.printAddTask(taskList, deadline);
        } catch  (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Does not exit immediately after command.
     *
     * @return False, so that loop does not terminate.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}