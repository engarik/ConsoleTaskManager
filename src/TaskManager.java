import java.io.*;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> toDoTasks;
    private ArrayList<Task> completedTasks;

    private int maxTaskId;

    public TaskManager() {
        toDoTasks = new ArrayList<>();
        completedTasks = new ArrayList<>();
        maxTaskId = 0;
    }

    public void addTask(String taskText) {
        Task task = new Task(maxTaskId, taskText);

        toDoTasks.add(task);
        maxTaskId++;
    }

    public void deleteTask(int taskId) {
        boolean flagDeleted = false;

        for (int i = 0; i < toDoTasks.size(); i++)
            if (toDoTasks.get(i).getId() == taskId) {
                toDoTasks.remove(i);
                flagDeleted = true;
                break;
            }
        if (!flagDeleted)
            for (int i = 0; i < completedTasks.size(); i++)
                if (completedTasks.get(i).getId() == taskId) {
                    completedTasks.remove(i);
                    flagDeleted = true;
                    break;
                }
        if (flagDeleted)
            System.out.printf("Successfully deleted task (id:%d)\n", taskId);
        else
            System.out.printf("No such task (id:%d)\n", taskId);
    }

    public void completeTask(int taskId) {
        boolean flagCompleted = false;

        for (int i = 0; i < toDoTasks.size(); i++)
            if (toDoTasks.get(i).getId() == taskId) {
                toDoTasks.get(i).completeTask();
                completedTasks.add(toDoTasks.get(i));
                toDoTasks.remove(i);
                flagCompleted = true;
                break;
            }
        if (flagCompleted)
            System.out.printf("Successfully completed task (id:%d)\n", taskId);
        else
            System.out.printf("No such task or it's already completed (id:%d)\n", taskId);
    }

    public void printAllTasks() {
        if (toDoTasks.size() + completedTasks.size() == 0)
            System.out.println("You don't have any tasks!");
        else {
            printTODOTasks();
            printCompletedTasks();
        }
    }

    public void printTODOTasks() {
        if (toDoTasks.size() == 0)
            System.out.println("There's no assigned tasks.");
        else {
            System.out.println("Assigned tasks:");
            for (Task toDoTask : toDoTasks)
                System.out.println(toDoTask.toString());
        }
    }

    public void printCompletedTasks() {
        if (completedTasks.size() == 0)
            System.out.println("There's no completed tasks.");
        else {
            System.out.println("Completed tasks:");
            for (Task completedTask : completedTasks)
                System.out.println(completedTask.toString());
        }
    }

    public void save(String filepath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filepath)));

        for (Task toDoTask : toDoTasks)
            writer.write(toDoTask.toWrite() + "\n");
        for (Task completedTask : completedTasks)
            writer.write(completedTask.toWrite() + "\n");
        
        writer.close();
    }

    public void load(String filepath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(filepath)));
        String line = reader.readLine();
        int id;

        toDoTasks.clear();
        completedTasks.clear();

        while (line != null) {
            String[] split = line.split(" ");
            try {
                id = Integer.parseInt(split[1]);
                if (Integer.parseInt(split[2]) == 0)
                    toDoTasks.add(new Task(id, split[0]));
                else
                    completedTasks.add(new Task(id, split[0]));
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                e.printStackTrace();
                System.out.println("Can't properly read, file is corrupted.");
            }
            line = reader.readLine();
        }

        reader.close();
    }
}
