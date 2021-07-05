package Repository;

import Classes.Answer;
import Classes.ConnectionCreator;
import Classes.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class AnswerRepository {

    public ObservableList<Answer> getAllAnswers()
    {
        String sql = "SELECT * FROM SubmittedAnswers";
        ObservableList<Answer> row = FXCollections.observableArrayList();
        try {
            Connection conn = ConnectionCreator.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                Answer a = new Answer(rs.getInt("AnsID"),
                        rs.getInt("TestID"),
                        rs.getInt("QuestionID"),
                        rs.getInt("UserID"),
                        rs.getString("AnsText"));
                row.add(a);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return row;

    }

    public Answer getStudentAnswer(Integer testID, Integer questionID, Integer userID)
    {
        Answer a = null;
        try{

            Connection conn = ConnectionCreator.connect();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM SubmittedAnswers WHERE TestID = ? AND QuestionID = ? AND UserID = ? ");
            pstmt.setInt(1, testID);
            pstmt.setInt(2, questionID);
            pstmt.setInt(3, userID);
            ResultSet rs = pstmt.executeQuery();
            // loop through the result set
            while (rs.next()) {
                a = new Answer(rs.getInt("TestID"),
                        rs.getInt("QuestionID"),
                        rs.getInt("UserID"),
                        rs.getString("AnsText"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return a;


    }
}
