package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class MultipleChoiceController {
    @FXML
    Label questionDescription;
    @FXML
    VBox ansArea;
    @FXML
    ToggleGroup ansGroup;
    @FXML
    Label scoreText;
    @FXML
    TextField scoreField;

    /*
    description text setter with provided description
     */
    public void setDescription(String description)
    {
        questionDescription.setText(description);
    }

    /*
    answer getter
     */
    public Toggle getAnswer()
    {
        return ansGroup.getSelectedToggle();
    }

    /*
    sets the possible answers into the toggle group
     */
    public void setPossibleAns(ArrayList<String> possibleAnsList)
    {
        for (String a:possibleAnsList)
        {
            RadioButton ansButton = new RadioButton(a);
            ansButton.setToggleGroup(ansGroup);
            ansArea.getChildren().add(ansButton);
        }
    }

    /*
    enables the manual marking score box depending on the marking mode
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

}
