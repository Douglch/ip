package duke.task;

/**
 * The parent for all the types of task available
 * for the user to create.
 */
public class Task {
    protected String description;
    protected boolean isDone;
//    protected static int counter = 0;

    /**
     * Creates a task instance, usually stemming
     * from its child instances.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gives the status of the task.
     *
     * @return Returns the string of the task status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done Duke.task with X
    }

    /**
     * For its child classes to instantiate and override,
     * not to be run by Task.
     *
     * @return A string to indicate that this shouldn't be ran
     * by Task.
     */
    public String getName() {
        return "You should not be getting this output here";
    }

    /**
     * Provides the description of the task.
     * Mainly for passing to other methods.
     *
     * @return Description of the task in string.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the status of the task.
     *
     * @param status The status to be marked.
     */
    public void markStatus(boolean status) {
        this.isDone = status;
    }

    /**
     * Marks the status of the task.
     * This is mainly for the purpose of loading
     * the data of the user.
     *
     * @param s String indicating its status.
     */
    public void markStatus(String s) {
        this.isDone = !s.equals("0");
    }

    /**
     * To be overriden and appended by its child classes.
     *
     * @return A string representation of task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
