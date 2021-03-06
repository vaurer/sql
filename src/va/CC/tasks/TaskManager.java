package va.CC.tasks;

import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private Scanner scanner;
    private TaskDAO taskDAO;

    public TaskManager() {
        this.scanner = new Scanner(System.in);
        this.taskDAO = new TaskDAO();
    }

    public void readInput() {
        System.out.println("Welcome!");
        printAllTasks();
        System.out.println("a for add new task, p for print all tasks");
        while (true) {
            System.out.println("enter the new command");
            String input = this.scanner.nextLine();
            if (input.equals("a")) {
                addTask();
            }
            else if (input.equals("p")) {
                printAllTasks();
            }

        }
    }

    public void printAllTasks() {
        List<TaskVO> tasks = this.taskDAO.getAllTasks();
        for (TaskVO task : tasks) {
            System.out.println("ID: " + task.getId() + " Name: " + task.getName());
        }
    }

    public void addTask() {
        String input = this.scanner.nextLine();
        System.out.println("name of the new task:");
        TaskVO taskVO = new TaskVO(0, input);
        this.taskDAO.insertTask(taskVO);
        printAllTasks();
    }
}
