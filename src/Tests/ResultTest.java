package Tests;

import Classes.Result;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultTest {
    private Result result = null;
    private Integer TestID = 1;
    private Integer UserID = 2;
    private Integer ResultID = 3;
    private Integer TotalScore = 4;

    @BeforeEach
    void setUp() {
        result = new Result(TestID,UserID,ResultID,TotalScore);
    }

    @AfterEach
    void tearDown() {
        result = null;
    }

    @Test
    void getTestID() {
        assertEquals(result.getTestID(), 1);
    }

    @Test
    void getUserID() {
        assertEquals(result.getUserID(), 2);
    }

    @Test
    void getResultID() {
        assertEquals(result.getResultID(), 3);
    }

    @Test
    void getTotalScore() {
        assertEquals(result.getTotalScore(), 4);
    }
}