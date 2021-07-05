package Tests;

import Classes.Question;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {
    private Question question = null;
    private Integer QuestionID = 1;
    private String Description = "test desc";
    private String QuestionType = "test type";
    private String CorrectAnswer = "test ans";
    private String Tags = "test tag";
    private Integer PointValue = 2;
    private Integer MarkedManually = 1;

    @BeforeEach
    void setUp() {
        question = new Question(QuestionID,Description,QuestionType,CorrectAnswer,Tags,PointValue,MarkedManually);
    }

    @AfterEach
    void tearDown() {
        question = null;
    }

    @Test
    void getQuestionID() {
        assertEquals(question.getQuestionID(), 1);
    }

    @Test
    void getDescription() {
        assertEquals(question.getDescription(), "test desc");
    }

    @Test
    void getQuestionType() {
        assertEquals(question.getQuestionType(), "test type");
    }

    @Test
    void getCorrectAnswer() {
        assertEquals(question.getCorrectAnswer(), "test ans");
    }

    @Test
    void getTags() {
        assertEquals(question.getTags(), "test tag");
    }

    @Test
    void getPointValue() {
        assertEquals(question.getPointValue(), 2);
    }

    @Test
    void getMarkedManually() {
        assertEquals(question.getMarkedManually(), 1);
    }
}