package Classes;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionCreator {
    public static Connection connect() {
        String basePath = new File("").getAbsolutePath();
        basePath=basePath+"/SchoolQuiz.db";
        String url = "jdbc:sqlite:"+basePath;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
