package va.CC.tasks;

public class Starter {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
//        taskManager.readInput(); //
        taskManager.printAllTasks();
        TaskDAO taskDAO = new TaskDAO();
        taskDAO.getAllTasks();

    }
}
