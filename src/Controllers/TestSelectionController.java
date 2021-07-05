package Controllers;

import Classes.Test;
import Classes.User;
import Repository.TestsRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javax.swing.JOptionPane.showMessageDialog;

public class TestSelectionController implements Initializable {

    private ObservableList<Test> TestRows = FXCollections.observableArrayList();
    private User user;


    @FXML
    TableView TestTable;

    /*
    Creates test table on page load
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        TestsRepository data = new TestsRepository();
        TestRows = data.getTests();
        TableColumn idCol = new TableColumn("Test ID");
        idCol.setCellValueFactory(new PropertyValueFactory<Test, Integer>("TestID"));

        TableColumn qListCol = new TableColumn("Question List");
        qListCol.setCellValueFactory(new PropertyValueFactory<Test, String>("QuestionList"));

        TableColumn testNameCol = new TableColumn("Test Name");
        testNameCol.setCellValueFactory(new PropertyValueFactory<Test, String>("TestName"));

        TestTable.getColumns().addAll(idCol,qListCol,testNameCol);

        TestTable.setItems(TestRows);

    }

    public void takeUser(User receivedUser)
    {
        user = receivedUser;
    }

    /*
    loads in the test fxml and sends the user ID to the take test controller.
    Also sends the test to be taken
     */
    public void startTest(ActionEvent event)
    {
        boolean markingMode = false;
        if (user.IsStaff == 1)
        {
            markingMode = true;
        }
        Parent root;
        if (TestTable.getSelectionModel().getSelectedItem() != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(TestSelectionController.class.getResource("/TakeTest.fxml"));
                root = loader.load();
                TakeTestController controller = loader.getController();
                controller.receiveTestID(((Test) TestTable.getSelectionModel().getSelectedItem()).TestID, false);
                controller.receiveUser(user);
                Stage stage = new Stage();
                stage.setTitle("Test");
                stage.setScene(new Scene(root, 600, 400));
                stage.setResizable(false);
                stage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{showMessageDialog(null, "Please select a test to take");}
    }

    /*
    opens the create test screen
     */
    public void openCreateTest(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader test = new FXMLLoader();
            test.setLocation(CreateTestController.class.getResource("/CreateTest.fxml"));
            root = test.load();
            Stage stage = new Stage();
            stage.setTitle("Test Creation");
            stage.setScene(new Scene(root, 700, 450));
            stage.setResizable(false);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    opens the edit test screen
     */
    public void editTest(ActionEvent event)
    {
        Parent root;
        if (TestTable.getSelectionModel().getSelectedItem() != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(EditTestController.class.getResource("/EditTest.fxml"));
                root = loader.load();
                EditTestController controller = loader.getController();
                controller.receiveTest(((Test) TestTable.getSelectionModel().getSelectedItem()));
                Stage stage = new Stage();
                stage.setTitle("Edit Test");
                stage.setScene(new Scene(root, 700, 450));
                stage.setResizable(false);
                stage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {showMessageDialog(null, "Please select a test to edit");}
    }

    /*
    opens the view results screen
     */
    public void openViewResults(ActionEvent event)
    {
        Parent root;
        try {
            FXMLLoader test = new FXMLLoader();
            test.setLocation(TakeTestController.class.getResource("/ResultView.fxml"));
            root = test.load();
            Stage stage = new Stage();
            stage.setTitle("Results");
            stage.setScene(new Scene(root, 700, 450));
            stage.setResizable(false);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
