import java.io.IOException;

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
            case "all" -> manager.printAllTasks();
            case "assigned" -> manager.printTODOTasks();
            case "completed" -> manager.printCompletedTasks();
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

    public boolean isRunning() {
        return isRunning;
    }
}
