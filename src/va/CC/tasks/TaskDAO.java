package va.CC.tasks;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    public List<TaskVO> getAllTasks() {
        List<TaskVO> task = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/notizbuch?user=root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from tasks");
            while (resultSet.next()) {
                String name = resultSet.getString(2);
                int idtasks = resultSet.getInt(1);
                //System.out.println("ID: "+idtasks + " - " + name);

                TaskVO taskVO = new TaskVO(idtasks, name);
                task.add(taskVO);
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return task;
    }

    public void insertTask(TaskVO task) {
        String sql = "insert into tasks (name) values ('" + task.getName() + "')";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/notizbuch?user=root");
            Statement statement = connection.createStatement();
            statement.execute(sql);
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }
}
