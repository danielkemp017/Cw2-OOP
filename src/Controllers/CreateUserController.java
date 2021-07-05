package Controllers;

import Classes.Question;
import Classes.Test;
import Classes.User;
import Repository.QuestionsRepository;
import Repository.TestsRepository;
import Repository.UsersRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateUserController implements Initializable {
    private ObservableList<User> UserRows = FXCollections.observableArrayList();
    @FXML
    TableView UserTable;

    @FXML
    private TextField FirstName;

    @FXML
    private TextField Surname;

    @FXML
    private RadioButton isStaff;


    /*
    Creates a table view populate from the users table in the db on page load
     */
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        UsersRepository data = new UsersRepository();
        UserRows = data.getUsers();
        TableColumn idCol = new TableColumn("User ID");
        idCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("UserID"));

        TableColumn fNameCol = new TableColumn("First Name");
        fNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("FirstName"));

        TableColumn lNameCol = new TableColumn("Surname");
        lNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("Surname"));

        TableColumn isStaffCol = new TableColumn("Is Staff");
        isStaffCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("IsStaff"));

        UserTable.getColumns().addAll(idCol,fNameCol,lNameCol,isStaffCol);

        UserTable.setItems(UserRows);

    }

    /*
    Adds the user to the db with the correct permissions and refreshes the table view
     */
    public void AddUser(ActionEvent event)
    {
        Integer staffMember;
        UsersRepository data = new UsersRepository();
        if (isStaff.isSelected() == true)
        {
            staffMember = 1;
        }
        else
        {
            staffMember = 0;
        }

        User userToAdd = new User(FirstName.getText(), Surname.getText(), staffMember );
        data.createUser(userToAdd);
        ObservableList<User> row = FXCollections.observableArrayList();
        row = data.getUsers();

        UserTable.setItems(row);
        FirstName.clear();
        Surname.clear();

    }
}
