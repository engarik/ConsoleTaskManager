import java.io.IOException;
import java.util.ArrayList;

public class CommandHandler {
    private TaskManager manager;
    private boolean isRunning;

    public CommandHandler() {
        manager = new TaskManager();
        isRunning = true;
    }

    public void handleCommand(String command) {
        String[] commands = command.split(" ");

        switch (commands[0].toLowerCase()) {
            case "q" -> isRunning = false;
            case "add" -> {
                try {
                    manager.addTask(command.split(" ")[1]);
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                    System.out.println("Please add info about your task.");
                }
            }
            case "delete" -> {
                try {
                    manager.deleteTask(Integer.parseInt(command.split(" ")[1]));
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                    System.out.println("Please specify task 'id' to delete.");
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    System.out.println("Please use decimal number to set task 'id'.");
                }
            }
            case "complete" -> {
                try {
                    manager.completeTask(Integer.parseInt(command.split(" ")[1]));
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                    System.out.println("Please specify task 'id' to complete.");
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    System.out.println("Please use decimal number to set task 'id'.");
                }
            }
            case "all" -> printAllTasks(manager.getToDoTasks(), manager.getCompletedTasks());
            case "assigned" -> printTODOTasks(manager.getToDoTasks());
            case "completed" -> printCompletedTasks(manager.getCompletedTasks());
            case "help" -> getCommandsList();
            case "save" -> {
                try {
                    manager.save(command.split(" ")[1]);
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                    System.out.println("Please specify filepath to save.");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Wrong filepath.");
                }

            }
            case "load" -> {
                try {
                    manager.load(command.split(" ")[1]);
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                    System.out.println("Please specify filepath to load.");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Wrong filepath.");
                }

            }
            default -> System.out.println("There's no such command. Use 'help' for command list");
        }
    }

    private void getCommandsList() {
        System.out.print(
                "Available commands:\n" +
                "'q' - exit\n" +
                "'add task-info' - add task\n" +
                "'delete task-id' - delete task by id\n" +
                "'complete task-id' - complete task by id\n" +
                "'all' - show all your tasks\n" +
                "'assigned' - show your assigned tasks\n" +
                "'completed' - show your completed tasks\n" +
                "'help' - show command list\n" +
                "'save filename.txt' - save tasks to file\n" +
                "'load filename.txt' - load tasks from file\n"
        );
    }

    private void printAllTasks(ArrayList<Task> toDoTasks, ArrayList<Task> completedTasks) {
        if (toDoTasks.size() + completedTasks.size() == 0)
            System.out.println("You don't have any tasks!");
        else {
            printTODOTasks(toDoTasks);
            printCompletedTasks(completedTasks);
        }
    }

    private void printTODOTasks(ArrayList<Task> toDoTasks) {
        if (toDoTasks.size() == 0)
            System.out.println("There's no assigned tasks.");
        else {
            System.out.println("Assigned tasks:");
            for (Task toDoTask : toDoTasks)
                System.out.println(toDoTask.toString());
        }
    }

    private void printCompletedTasks(ArrayList<Task> completedTasks) {
        if (completedTasks.size() == 0)
            System.out.println("There's no completed tasks.");
        else {
            System.out.println("Completed tasks:");
            for (Task completedTask : completedTasks)
                System.out.println(completedTask.toString());
        }
    }

    public boolean isRunning() {
        return isRunning;
    }
}
