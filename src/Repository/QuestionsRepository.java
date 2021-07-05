package Repository;
import Classes.ConnectionCreator;
import Classes.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class QuestionsRepository {
    public ObservableList<Question> getAllQuestions() {
        String sql = "SELECT * FROM questions";
        ObservableList<Question> row = FXCollections.observableArrayList();
        try {
            Connection conn = ConnectionCreator.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                Question q = new Question(rs.getInt("QuestionID"),
                        rs.getString("Description"),
                        rs.getString("QuestionType"),
                        rs.getString("CorrectAnswer"),
                        rs.getString("Tags"),
                        rs.getInt("PointValue"),
                        rs.getInt("ManuallyMarked"));
                row.add(q);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return row;

    }

    public Question getQuestion(Integer questionID) {

        Question q = null;
        try {
            Connection conn = ConnectionCreator.connect();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM questions Where QuestionID = ?");
            pstmt.setInt(1, questionID);
            ResultSet rs = pstmt.executeQuery();
            // loop through the result set
            while (rs.next()) {
                 q = new Question(rs.getInt("QuestionID"),
                        rs.getString("Description"),
                        rs.getString("QuestionType"),
                        rs.getString("CorrectAnswer"),
                        rs.getString("Tags"),
                        rs.getInt("PointValue"),
                        rs.getInt("ManuallyMarked"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return q;

    }

    public void createQuestion(Question question)
    {
        String sql = "INSERT INTO Questions(Description, QuestionType, CorrectAnswer, Tags, PointValue, ManuallyMarked) VALUES(?,?,?,?,?,?)";

        try{
            Connection conn = ConnectionCreator.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, question.Description);
            pstmt.setString(2, question.QuestionType);
            pstmt.setString(3, question.CorrectAnswer);
            pstmt.setString(4, question.Tags);
            pstmt.setInt(5, question.PointValue);
            pstmt.setInt(6,question.MarkedManually);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void editQuestion(Question question)
    {
        String sql = "UPDATE Questions  SET Description = ? , QuestionType = ?, CorrectAnswer = ?, Tags = ?, PointValue = ? WHERE QuestionID = ?";

        try{
            Connection conn = ConnectionCreator.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, question.Description);
            pstmt.setString(2, question.QuestionType);
            pstmt.setString(3, question.CorrectAnswer);
            pstmt.setString(4, question.Tags);
            pstmt.setInt(5, question.PointValue);
            pstmt.setInt(6, question.QuestionID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}

