package Controllers;

import Classes.Question;
import Classes.QuestionTableCreator;
import Classes.Test;
import Repository.QuestionsRepository;
import Repository.TestsRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javax.swing.JOptionPane.showMessageDialog;

public class CreateTestController implements Initializable {
    @FXML
    private TableView<Question> QuestionTable;
    @FXML
    private TableView<Question> QuestionTestTable;
    @FXML
    private TextField testName;
    @FXML
    private TextField tagFilter;
    @FXML
    private RadioButton isMarkedManually;

    private ObservableList<Question> QuestionRows = FXCollections.observableArrayList();

    private ObservableList<Question> QuestionTestRows = FXCollections.observableArrayList();

    @Override
    /*
    Creates both tables as well as implementing some filtering
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<TableColumn> questionColumns = QuestionTableCreator.CreateQuestionColumns();
        ArrayList<TableColumn> testColumns = QuestionTableCreator.CreateQuestionColumns();
        QuestionsRepository qData = new QuestionsRepository();


        QuestionRows = qData.getAllQuestions();
        for (TableColumn col:testColumns)
        {
            QuestionTestTable.getColumns().add(col);
        }
        for (TableColumn col:questionColumns)
        {

            QuestionTable.getColumns().add(col);
        }
        QuestionTable.setItems(QuestionRows);
        QuestionTestTable.setItems(QuestionTestRows);

        FilteredList<Question> filteredData = new FilteredList<>(QuestionRows, p -> true);

        tagFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(question -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (question.getTags().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
        });
        SortedList<Question> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(QuestionTable.comparatorProperty());
        QuestionTable.setItems(sortedData);
    }

    /*
    Add question to the currently in creation test table
     */
    public void addQuestionTest(ActionEvent event)
    {
        Question questionToAdd = QuestionTable.getSelectionModel().getSelectedItem();
        QuestionTestRows.add(questionToAdd);
    }

    /*
    Remove question from the currently in creation test table
     */
    public void removeQuestionTest(ActionEvent event)
    {
        Question questionToRemove = QuestionTestTable.getSelectionModel().getSelectedItem();
        QuestionTestRows.remove(questionToRemove);

    }

    /*
    Saves the test with the properties from the relevant fields
     */
    public void saveTest(ActionEvent event)
    {
        Integer markedManually;
        ObservableList<Question> questionList = FXCollections.observableArrayList();
        ArrayList<Integer> questionIdList = new ArrayList<>();
        questionList = QuestionTestTable.getItems();
        for (Question Q:questionList)
        {
            questionIdList.add(Q.QuestionID);
        }

        Test testToSend = new Test(questionIdList.toString(), testName.getText());

        TestsRepository TestsRepo = new TestsRepository();
        if (Validation(testToSend))
        {
            TestsRepo.createTest(testToSend);
            showMessageDialog(null, "Test Saved");
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }

    }

    /*
    Validates the test before being created in the DB
     */
    public boolean Validation(Test test)
    {
        if (test.TestName == "")
        {
            showMessageDialog(null, "Please enter a test name");
            return false;
        }
        if (test.QuestionList == "[]")
        {
            showMessageDialog(null, "Please select at least one question for a test");
            return false;
        }
        return true;
    }
}
