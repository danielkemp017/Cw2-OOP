package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static javax.swing.JOptionPane.showMessageDialog;


public class ExtendedChoiceController implements Initializable {
    @FXML
    Label questionDescription;
    @FXML
    TextArea submittedAnswer;
    @FXML
    Label scoreText;
    @FXML
    TextField scoreField;

    /*
    setter for description text provided
     */
    public void setDescription(String Description)
    {
        questionDescription.setText(Description);
    }

    /*
    getter for the answer text
     */
    public String getAnswer()
    {
        String answerText = submittedAnswer.getText();
        return answerText;
    }

    /*
    getter for the manual mark given
     */
    public Integer getMark()
    {
        if (Validation(scoreField.getText()))
        {
            Integer mark = Integer.parseInt(scoreField.getText());
            return mark;
        }
        return 999;
    }

    /*
    setter for the answer text for a teacher to mark manually
     */
    public void setSubmittedAnswer(String AnsText)
    {
        submittedAnswer.setText(AnsText);
        submittedAnswer.setDisable(true);
    }

    /*
    enables the manual mark box depending on if the test is in marking mode or not
     */
    public void enableMark(boolean mode)
    {
        if (mode)
        {
            scoreField.setDisable(false);
        }
        else
        {
            scoreField.setDisable(true);
        }
    }

    /*
    max mark setter with a provided mark
     */
    public void setMaxMark(Integer maxScore)
    {
        scoreText.setText("/" + maxScore.toString());
    }

    /*
    monitors the manual mark box to ensure only numbers are entered
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scoreField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                scoreField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    /*
    validates the manual marking to ensure all questions are marked
     */
    public boolean Validation(String markText)
    {
        if (markText == "")
        {
            showMessageDialog(null, "Please enter a mark for each question");
            return false;
        }
        return true;
    }









}
