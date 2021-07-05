package Controllers;

import Classes.Question;
import Classes.QuestionTableCreator;
import Classes.Test;
import Repository.QuestionsRepository;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javax.swing.JOptionPane.showMessageDialog;


public class CreateQuestionController implements Initializable {
    @FXML
    private TextField DescriptionText;

    @FXML
    private ComboBox<String> TypeText;

    @FXML
    private TextField AnswerText;

    @FXML
    private TextField QuestionTagsText;

    @FXML
    private TextField QuestionValueText;

    @FXML
    private TableView<Question> QuestionTable;

    @FXML
    private Label AnswerLabel;

    @FXML
    private RadioButton MarkedManually;



    @Override
    /*
    Creates question table, filters question value text area so only numbers can be entered
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<TableColumn> columns = QuestionTableCreator.CreateQuestionColumns();
        QuestionsRepository data = new QuestionsRepository();
        ObservableList<Question> rows = FXCollections.observableArrayList();
        rows = data.getAllQuestions();
        for (TableColumn col:columns)
        {
            QuestionTable.getColumns().add(col);
        }
        QuestionTable.setItems(rows);

        QuestionValueText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                QuestionValueText.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        TypeText.getItems().addAll("Please Select","Multiple Choice", "Single Answer","Extended Answer");
        TypeText.getSelectionModel().selectFirst();

        TypeText.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (TypeText.getValue().toString() == "Extended Answer")
            {
                AnswerText.setVisible(false);
                AnswerLabel.setVisible(false);
            }
            else
            {
                AnswerText.setVisible(true);
                AnswerLabel.setVisible(true);
            }
        });



    }

    /*
    Adding a question to the DB once validated
     */
    public void AddQuestion(ActionEvent event)
    {
        Integer markedManually;
        if (MarkedManually.isSelected() == true)
        {
            markedManually = 1;
        }
        else
        {
            markedManually = 0;
        }
        if (Validation(DescriptionText.getText(), TypeText.getValue().toString(),AnswerText.getText(),QuestionValueText.getText(),markedManually)) {
            QuestionsRepository data = new QuestionsRepository();


            Question questionToAdd = new Question(DescriptionText.getText(), TypeText.getValue().toString(),
                    AnswerText.getText(), QuestionTagsText.getText(), Integer.parseInt(QuestionValueText.getText()), markedManually);
            data.createQuestion(questionToAdd);

            ObservableList<Question> row = FXCollections.observableArrayList();
            row = data.getAllQuestions();

            QuestionTable.setItems(row);
            DescriptionText.clear();
            TypeText.getSelectionModel().selectFirst();
            AnswerText.clear();
            QuestionTagsText.clear();
            QuestionValueText.clear();
            MarkedManually.setSelected(false);
        }

    }

    /*
    Clones currently selected question from the question table into the create fields
     */
    public void cloneQuestion(ActionEvent event)
    {
       Question questionClone = ((Question) QuestionTable.getSelectionModel().getSelectedItem());
       DescriptionText.setText(questionClone.Description);
       TypeText.setValue(questionClone.QuestionType);
       AnswerText.setText(questionClone.CorrectAnswer);
       QuestionTagsText.setText(questionClone.Tags);
       QuestionValueText.setText(questionClone.PointValue.toString());
    }

    /*
    Tags Popup to add extra tags
     */
    public void addTagsPopup()
    {
        TextInputDialog tagPopup = new TextInputDialog("Enter Tags Here");
        tagPopup.setHeaderText("Add Tags");
        tagPopup.setContentText("Enter Tag");
        tagPopup.showAndWait();

        if (QuestionTagsText.getText().length() == 0)
        {
            QuestionTagsText.setText(tagPopup.getEditor().getText());

        }
        else
        {
            QuestionTagsText.setText(QuestionTagsText.getText() + "," + tagPopup.getEditor().getText());
        }
    }

    public void clearTags()
    {
        QuestionTagsText.clear();
    }


    /*
    Checks the question to make sure its valid
     */
    public Boolean Validation(String Description, String Type, String Ans, String Value, Integer MarkedManually)
    {
        if (Type == "Please Select")
        {
            showMessageDialog(null, "Please enter a question type ");
            return false;
        }
        if (Description == "")
        {
            showMessageDialog(null, "Please enter a description ");
            return false;
        }
        if (Type == "Multiple Choice" && !Description.contains("["))
        {
            showMessageDialog(null, "Please format your question description according to the example");
            return false;
        }
        if (Type == "Multiple Choice" && !Description.contains("]"))
        {
            showMessageDialog(null, "Please format your question description according to the example");
            return false;
        }
        if (Type == "Multiple Choice" && MarkedManually == 1)
        {
            showMessageDialog(null, "Please deselect marked manually as multiple choice questions are not eligible for manual marking");
            return false;
        }
        if (Type == "Extended Answer" && MarkedManually == 0)
        {
            showMessageDialog(null, "Please select marked manually as extended choice questions are not eligible for auto marking");
            return false;
        }
        if (Ans == "" && Type != "Extended Answer")
        {
            showMessageDialog(null, "Please enter an answer ");
            return false;
        }
        if (Value == "")
        {
            showMessageDialog(null, "Please enter an point value ");
            return false;
        }


        return true;
    }
}
