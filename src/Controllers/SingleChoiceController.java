package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static javax.swing.JOptionPane.showMessageDialog;


public class SingleChoiceController implements Initializable {
    @FXML
    Label questionDescription;
    @FXML
    TextField submittedAnswer;
    @FXML
    TextField scoreField;
    @FXML
    Label scoreText;

    /*
    description text setter with provided description
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
     manual mark getter with validation
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
     sets the submitted anstext and makes it readonly so a teach can not alter it in marking mode
      */
     public void setSubmittedAnswer(String ansText)
     {
         submittedAnswer.setText(ansText);
         submittedAnswer.setDisable(true);
     }

     /*
     enables the manual marking box so that a score can be entered if the test is in marking mode
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
     max mark setter
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
