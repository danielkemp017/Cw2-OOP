package Tests;

import Classes.Question;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestTest {
    private Classes.Test test = null;
    private Integer TestID = 1;
    private String QuestionList = "test list";
    private String TestName = "test name";

    @BeforeEach
    void setUp() {
        test = new Classes.Test(TestID,QuestionList, TestName);
    }

    @AfterEach
    void tearDown() {
        test = null;
    }

    @Test
    void getTestID() {
        assertEquals(test.getTestID(), 1);
    }

    @Test
    void getQuestionList() {
        assertEquals(test.getQuestionList(), "test list");
    }

    @Test
    void getTestName() {
        assertEquals(test.getTestName(), "test name");
    }
}