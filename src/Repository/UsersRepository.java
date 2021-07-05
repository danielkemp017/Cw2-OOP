package Repository;

import Classes.ConnectionCreator;
import Classes.Test;
import Classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class UsersRepository {
    public ObservableList<User> getUsers() {
        String sql = "SELECT * FROM Users";
        ObservableList<User> users = FXCollections.observableArrayList();

        try {
            Connection conn = ConnectionCreator.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            // loop through the result set
            while (rs.next()) {
                User u = new User(rs.getInt("UserID"),
                        rs.getString("FirstName"),
                        rs.getString("Surname"),
                        rs.getInt("isStaff"));
                users.add(u);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public User getUser(Integer userID)
    {
        User u = null;
        try {
            Connection conn = ConnectionCreator.connect();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Users WHERE UserID = ?");
            pstmt.setInt(1,userID);
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                u = new User(rs.getInt("UserID"),
                        rs.getString("FirstName"),
                        rs.getString("Surname"),
                        rs.getInt("isStaff"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return u;
    }

    public void createUser(User user)
    {
        String sql = "INSERT INTO Users(FirstName, Surname, isStaff) VALUES(?,?,?)";

        try{
            Connection conn = ConnectionCreator.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.FirstName);
            pstmt.setString(2, user.Surname);
            pstmt.setInt(3, user.IsStaff);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }






}
