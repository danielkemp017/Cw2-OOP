package Classes;

import Repository.QuestionsRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.util.ArrayList;

public class QuestionTableCreator {
    public static ArrayList<TableColumn> CreateQuestionColumns()
    {
        ArrayList<TableColumn> Columns = new ArrayList<>();


        TableColumn idCol = new TableColumn("Question ID");
        idCol.setCellValueFactory(new PropertyValueFactory<Question, Integer>("QuestionID"));
        Columns.add(idCol);

        TableColumn descCol = new TableColumn("Description");
        descCol.setCellValueFactory(new PropertyValueFactory<Question, String>("Description"));
        Columns.add(descCol);

        TableColumn TypeCol = new TableColumn("Type");
        TypeCol.setCellValueFactory(new PropertyValueFactory<Question, String>("QuestionType"));
        Columns.add(TypeCol);

        TableColumn AnswerCol = new TableColumn("Answer");
        AnswerCol.setCellValueFactory(new PropertyValueFactory<Question, String>("CorrectAnswer"));
        Columns.add(AnswerCol);

        TableColumn TagsCol = new TableColumn("Tags");
        TagsCol.setCellValueFactory(new PropertyValueFactory<Question, String>("Tags"));
        Columns.add(TagsCol);

        TableColumn ValueCol = new TableColumn("Value");
        ValueCol.setCellValueFactory(new PropertyValueFactory<Question, Integer>("PointValue"));
        Columns.add(ValueCol);

        TableColumn MarkedManuallyCol = new TableColumn("Marked Manually");
        MarkedManuallyCol.setCellValueFactory(new PropertyValueFactory<Question, Integer>("MarkedManually"));
        Columns.add(MarkedManuallyCol);


        return Columns;

    }
}
