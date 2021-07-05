package Repository;

import Classes.ConnectionCreator;
import Classes.Question;
import Classes.Result;
import Classes.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ResultsRepository {

    public ObservableList<Result> getResults()
    {
        String sql = "SELECT * FROM Results";
        ObservableList<Result> row = FXCollections.observableArrayList();
        try {
            Connection conn = ConnectionCreator.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                Result r = new Result(rs.getInt("TestID"),
                        rs.getInt("UserID"),
                        rs.getInt("ResultID"),
                        rs.getInt("TotalScore"));
                row.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return row;
    }

    public void createResult(Result result)
    {
        String sql = "INSERT INTO Results(TestID, UserID, TotalScore) VALUES(?,?,?)";

        try{
            Connection conn = ConnectionCreator.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, result.TestID);
            pstmt.setInt(2, result.UserID);
            pstmt.setInt(3, result.TotalScore);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void submitManualMarks(Integer totalMark, Integer resultID)
    {
        String sql = "UPDATE Results SET TotalScore = TotalScore + ? WHERE ResultID = ?";

        try{
            Connection conn = ConnectionCreator.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, totalMark);
            pstmt.setInt(2, resultID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
