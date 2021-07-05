package Tests;

import Classes.Answer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnswerTest {
    private Answer answer = null;
    private Integer answerID = 1;
    private Integer questionID = 3;
    private Integer testID = 2;
    private Integer userID= 4;
    private String ansText = "Sample ans";

    @BeforeEach
    void setUp() {
        answer = new Answer(answerID,testID,questionID,userID,ansText);
    }

    @AfterEach
    void tearDown() {
        answer = null;
    }

    @Test
    void getAnsID() {
        assertEquals(answer.getAnsID(), 1);
    }

    @Test
    void getTestID() {
        assertEquals(answer.getTestID(), 2);
    }

    @Test
    void getQuestionID() {
        assertEquals(answer.getQuestionID(), 3);
    }

    @Test
    void getUserID() {
        assertEquals(answer.getUserID(),  4);
    }

    @Test
    void getAnsText() {
        assertEquals(answer.getAnsText(), "Sample ans" );
    }
}