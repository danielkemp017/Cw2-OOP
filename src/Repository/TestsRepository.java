package Repository;

import Classes.ConnectionCreator;
import Classes.Question;
import Classes.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class TestsRepository {
    public ObservableList<Test> getTests() {
        String sql = "SELECT * FROM Tests";
        ObservableList<Test> row = FXCollections.observableArrayList();

        try {
            Connection conn = ConnectionCreator.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                Test t = new Test(rs.getInt("TestID"),
                        rs.getString("QuestionList"),
                        rs.getString("TestName"));
                row.add(t);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return row;
    }

    public Test getTest(Integer testID) {

        Test t = null;
        try {
            Connection conn = ConnectionCreator.connect();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Tests WHERE TestID = ?");
            pstmt.setInt(1,testID);
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                 t = new Test(rs.getInt("TestID"),
                        rs.getString("QuestionList"),
                        rs.getString("TestName"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return t;
    }

    public void createTest(Test test)
    {
        String sql = "INSERT INTO Tests(QuestionList, TestName) VALUES(?,?)";

        try{
            Connection conn = ConnectionCreator.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, test.QuestionList);
            pstmt.setString(2, test.TestName);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void editTest(Test test)
    {
        String sql = "UPDATE Tests SET QuestionList = ? , TestName = ? WHERE TestID = ?";

        try{
            Connection conn = ConnectionCreator.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, test.QuestionList);
            pstmt.setString(2, test.TestName);
            pstmt.setInt(3, test.TestID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
