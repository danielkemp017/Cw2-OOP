package Repository;

import Classes.Answer;
import Classes.ConnectionCreator;
import Classes.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SubmittedAnswerRepository {

    public void saveAnswer(Answer answer)
    {
        String sql = "INSERT INTO SubmittedAnswers(TestID, QuestionID, UserID,AnsText) VALUES(?,?,?,?)";

        try{
            Connection conn = ConnectionCreator.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, answer.TestID);
            pstmt.setInt(2, answer.QuestionID);
            pstmt.setInt(3, answer.UserID);
            pstmt.setString(4, answer.AnsText);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
