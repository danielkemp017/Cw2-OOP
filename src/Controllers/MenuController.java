package Controllers;

import Classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;


public class MenuController
{
    @FXML
    Button createQuestion;
    @FXML
    Button createUser;
    @FXML
    Button viewResults;


    private User user;

    /*
    takes user from login
    */
    public void takeUser(User receivedUser)
    {
        user = receivedUser;
        if (user.IsStaff == 0)
        {
            createQuestion.setVisible(false);
            createUser.setVisible(false);
            viewResults.setVisible(false);
        }
    }

    /*
    opens the create question screen
     */
    public void openCreateQuestion(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader test = new FXMLLoader();
            test.setLocation(MenuController.class.getResource("/CreateQuestion.fxml"));
            root = test.load();
            Stage stage = new Stage();
            stage.setTitle("Question Creation");
            stage.setScene(new Scene(root, 850, 450));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    opens the test selection screen
     */
    public void openTestSelection(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader test = new FXMLLoader();
            test.setLocation(TestSelectionController.class.getResource("/TestSelection.fxml"));
            root = test.load();
            TestSelectionController controller = test.getController();
            controller.takeUser(user);
            Stage stage = new Stage();
            stage.setTitle("Select Test");
            stage.setScene(new Scene(root, 700, 450));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    opens the view results screen
     */
    public void openViewResults(ActionEvent event)
    {
        Parent root;
        try {
            FXMLLoader test = new FXMLLoader();
            test.setLocation(ResultViewController.class.getResource("/ResultView.fxml"));
            root = test.load();
            ResultViewController controller = test.getController();
            controller.receiveUser(user);
            Stage stage = new Stage();
            stage.setTitle("Results");
            stage.setScene(new Scene(root, 700, 450));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
    opens the create user screen
     */
    public void openCreateUser(ActionEvent event)
    {
        Parent root;
        try {
            FXMLLoader test = new FXMLLoader();
            test.setLocation(CreateUserController.class.getResource("/CreateUser.fxml"));
            root = test.load();
            Stage stage = new Stage();
            stage.setTitle("Create Users");
            stage.setScene(new Scene(root, 850, 450));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
