package Controllers;

import Classes.*;

import Repository.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import static javax.swing.JOptionPane.showMessageDialog;

public class TakeTestController {

    private Integer testID;
    private User user;
    private Integer studentID;
    private Integer resultID;

    @FXML
    TabPane tabPane;

    @FXML
    Button submitResultBtn;

    @FXML
    Button submitMarkBtn;

    private ObservableList<Question> questionList = FXCollections.observableArrayList();

    private ArrayList<Object> controllerList = new ArrayList<>();

    private Test returnedTest;



    /*
    takes in a test id and retrieves the test from the DB. it then prepares that test to be output on the window
     */
    public void receiveTestID(Integer receivedId, boolean markingMode) throws IOException {

        if (markingMode == true)
        {
            submitMarkBtn.setVisible(true);
            submitResultBtn.setVisible(false);
        }
        else
        {
            submitMarkBtn.setVisible(false);
            submitResultBtn.setVisible(true);
        }

        testID = receivedId;
        QuestionsRepository questionRepo = new QuestionsRepository();
        TestsRepository testRepo = new TestsRepository();
        returnedTest = testRepo.getTest(testID);
        String str = returnedTest.QuestionList;
        str = str.replace("[", "");
        str = str.replace("]", "");
        str = str.replaceAll(" ", "");
        ArrayList<String> myList = new ArrayList<String>(Arrays.asList(str.split(",")));

        for (String qID:myList)
        {
            Question questionReceived = questionRepo.getQuestion(Integer.parseInt(qID));
            if (markingMode == true)
            {
                if (questionReceived.MarkedManually == 1)
                {
                    questionList.add(questionReceived);
                }
            }
            else
            {
                questionList.add(questionReceived);
            }
        }
        createQuestions(markingMode);
    }

    public void receiveUser(User receivedUser)
    {
        user = receivedUser;
    }

    public void receiveStudentID (Integer receivedStudentID)
    {
        studentID = receivedStudentID;
    }

    public void receiveResultID(Integer receivedResultID)
    {
        resultID = receivedResultID;
    }

    /*
    creates the questions over multiple tabs on the test screen.
    It loads in and uses specific fxml and controllers for each question type
    It then enables or disables certain content depending on the marking mode
     */
    private void createQuestions (boolean markingMode) throws IOException
    {
        Integer counter = 1;
        for (Question q:questionList) {
            Tab tab = new Tab("Question " + counter);
            tabPane.getTabs().add(tab);
            if (q.QuestionType.equalsIgnoreCase("Single Answer"))
            {
                FXMLLoader loaderSingle = new FXMLLoader();
                loaderSingle.setLocation(TakeTestController.class.getResource("/SingleChoiceTab.fxml"));
                tab.setContent(loaderSingle.load());
                SingleChoiceController controllerSingle = loaderSingle.getController();
                controllerSingle.setDescription(q.Description);
                controllerSingle.enableMark(markingMode);
                controllerSingle.setMaxMark(q.PointValue);
                if (markingMode == true)
                {
                    AnswerRepository ansRepo = new AnswerRepository();
                    Answer studentAnswer = ansRepo.getStudentAnswer(testID,q.QuestionID,studentID);
                    controllerSingle.setSubmittedAnswer(studentAnswer.AnsText);
                }
                controllerList.add(controllerSingle);
            }
            else if (q.QuestionType.equalsIgnoreCase("Multiple Choice"))
            {
                FXMLLoader loaderMulti = new FXMLLoader();
                loaderMulti.setLocation(TakeTestController.class.getResource("/MultipleChoiceTab.fxml"));
                tab.setContent(loaderMulti.load());
                MultipleChoiceController controllerMulti = loaderMulti.getController();
                Integer placeToStart = q.Description.indexOf("[") + 1;
                Integer placeToEnd = q.Description.indexOf("]");
                String possibleAnsStr = q.Description.substring(placeToStart, placeToEnd);
                ArrayList<String> possibleAnsList = new ArrayList<String>(Arrays.asList(possibleAnsStr.split(",")));
                controllerMulti.setDescription(q.Description.substring(0,q.Description.indexOf("[")));
                controllerMulti.setPossibleAns(possibleAnsList);
                controllerMulti.setMaxMark(q.PointValue);
                controllerMulti.enableMark(markingMode);
                controllerList.add(controllerMulti);
            }
            else
            {
                FXMLLoader loaderExtended = new FXMLLoader();
                loaderExtended.setLocation(TakeTestController.class.getResource("/ExtendedChoiceTab.fxml"));
                tab.setContent(loaderExtended.load());
                ExtendedChoiceController controllerExtended = loaderExtended.getController();
                controllerExtended.setDescription(q.Description);
                controllerExtended.setMaxMark(q.PointValue);
                controllerExtended.enableMark(markingMode);
                if (markingMode == true)
                {
                    AnswerRepository ansRepo = new AnswerRepository();
                    Answer studentAnswer = ansRepo.getStudentAnswer(testID,q.QuestionID,studentID);
                    controllerExtended.setSubmittedAnswer(studentAnswer.AnsText);
                }

                controllerList.add(controllerExtended);
            }
            counter++;
        }
    }

    /*
    retrieves the results form the tabs and auto marks them. The end score is then saved to the DB
     */
    public void submitResult(ActionEvent event) throws IOException {
        ObservableList<Tab> tabArrayList = tabPane.getTabs();
        Integer currentQuestionIndex = 0;
        Integer totalScore = 0;
        SubmittedAnswerRepository submittedAnswerRepo = new SubmittedAnswerRepository();
        for (Tab t:tabArrayList)
        {
            Question currentQuestion = questionList.get(currentQuestionIndex);
            if (currentQuestion.QuestionType.equalsIgnoreCase("Single Answer"))
            {
                String answerText = ((SingleChoiceController)(controllerList.get(currentQuestionIndex))).getAnswer();
                if (currentQuestion.MarkedManually == 1)
                {
                    Answer ansToSend = new Answer(returnedTest.TestID, currentQuestion.QuestionID, user.UserID, answerText);
                    submittedAnswerRepo.saveAnswer(ansToSend);
                }
                if (answerText.equalsIgnoreCase(currentQuestion.CorrectAnswer))
                {
                    totalScore += currentQuestion.PointValue;
                }
            }
            else if (currentQuestion.QuestionType.equalsIgnoreCase("Multiple Choice"))
            {
                Toggle answerSelected = ((MultipleChoiceController)(controllerList.get(currentQuestionIndex))).getAnswer();
                if (answerSelected != null) {
                    String btnSelectedTxt = ((RadioButton) (answerSelected)).getText();
                    if (btnSelectedTxt.equalsIgnoreCase(currentQuestion.CorrectAnswer))
                    {
                        totalScore += currentQuestion.PointValue;
                    }
                }
            }
            else
            {
                String answerText = ((ExtendedChoiceController)(controllerList.get(currentQuestionIndex))).getAnswer();
                Answer ansToSend = new Answer(returnedTest.TestID, currentQuestion.QuestionID, user.UserID, answerText);
                submittedAnswerRepo.saveAnswer(ansToSend);
            }

            currentQuestionIndex++;

        }
        showMessageDialog(null, "Test completed your total score is: " + totalScore);
        ResultsRepository resultsRepo = new ResultsRepository();
        Result resultToSend = new Result(testID, user.UserID, totalScore);
        resultsRepo.createResult(resultToSend);
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    /*
    retrieves the teachers manual marks form the tabs and updates the provisional result in the db
     */
    public void submitMarks(ActionEvent event)
    {
        ObservableList<Tab> tabArrayList = tabPane.getTabs();
        Integer currentQuestionIndex = 0;
        Integer totalScore = 0;

        for (Tab t:tabArrayList)
        {
            Question currentQuestion = questionList.get(currentQuestionIndex);
            if (currentQuestion.QuestionType.equalsIgnoreCase("Single Answer"))
            {
                Integer mark = ((SingleChoiceController)(controllerList.get(currentQuestionIndex))).getMark();
                totalScore += mark;
            }
            else
            {
                Integer mark = ((ExtendedChoiceController)(controllerList.get(currentQuestionIndex))).getMark();
                totalScore += mark;
            }

            currentQuestionIndex++;

        }

        if (totalScore < 999) {
            showMessageDialog(null, "Marks submitted");
            ResultsRepository resultsRepo = new ResultsRepository();
            resultsRepo.submitManualMarks(totalScore, resultID);
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }


    }


}
