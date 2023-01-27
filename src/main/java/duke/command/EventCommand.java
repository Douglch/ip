package duke.command;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class EventCommand extends Command {
    private final Event event;
    public EventCommand(Event event) {
        this.event = event;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.addTasks(event);
            storage.updateStorage(taskList);
            ui.printAddTask(taskList, event);
        } catch  (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
