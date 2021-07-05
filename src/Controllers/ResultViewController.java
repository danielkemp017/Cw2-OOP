package Controllers;

import Classes.Result;
import Classes.Test;
import Classes.User;
import Repository.ResultsRepository;
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

public class ResultViewController implements Initializable {
    private ObservableList<Result> ResultRows = FXCollections.observableArrayList();
    private User user;


    @FXML
    TableView ResultTable;

    @Override
    /*
    creates results table
     */
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ResultsRepository data = new ResultsRepository();
        ResultRows = data.getResults();
        TableColumn rIdCol = new TableColumn("Result ID");
        rIdCol.setCellValueFactory(new PropertyValueFactory<Result, Integer>("ResultID"));

        TableColumn tIdCol = new TableColumn("Test ID");
        tIdCol.setCellValueFactory(new PropertyValueFactory<Result, Integer>("TestID"));

        TableColumn uIdCol = new TableColumn("User ID");
        uIdCol.setCellValueFactory(new PropertyValueFactory<Result, Integer>("UserID"));

        TableColumn totalCol = new TableColumn("Total Score");
        totalCol.setCellValueFactory(new PropertyValueFactory<Result, Integer>("TotalScore"));

        ResultTable.getColumns().addAll(rIdCol,tIdCol,uIdCol,totalCol);
        ResultTable.setItems(ResultRows);

    }

    /*
    receives user from any controller that calls this method
     */
    public void receiveUser(User receivedUser)
    {
        user = receivedUser;
    }

    /*
    loads in the test fxml and sends the user ID to the take test controller.
    Also sends the test to be taken and states that it is in marking mode
    */
    public void markManualAns(ActionEvent event)
    {
        Parent root;
        if (ResultTable.getSelectionModel().getSelectedItem() != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(TestSelectionController.class.getResource("/TakeTest.fxml"));
                root = loader.load();
                TakeTestController controller = loader.getController();
                controller.receiveStudentID(((Result) ResultTable.getSelectionModel().getSelectedItem()).UserID);
                controller.receiveUser(user);
                controller.receiveTestID(((Result) ResultTable.getSelectionModel().getSelectedItem()).TestID,true);
                controller.receiveResultID(((Result) ResultTable.getSelectionModel().getSelectedItem()).ResultID);
                Stage stage = new Stage();
                stage.setTitle("Test");
                stage.setScene(new Scene(root, 600, 450));
                stage.setResizable(false);
                stage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{showMessageDialog(null, "Please select a Answer to mark");}

    }
}



