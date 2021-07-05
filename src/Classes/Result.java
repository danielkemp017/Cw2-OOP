package Classes;

public class Result {
    public Integer TestID;
    public Integer UserID;
    public Integer ResultID;
    public Integer TotalScore;

    public Integer getTestID() {return TestID;}
    public Integer getUserID() {return UserID;}
    public Integer getResultID() {return ResultID;}
    public Integer getTotalScore() {return TotalScore;}

    public Result(Integer testID, Integer userID, Integer totalScore)
    {
        TestID = testID;
        UserID = userID;
        TotalScore = totalScore;
    }
    public Result(Integer testID, Integer userID, Integer resultID , Integer totalScore)
    {
        TestID = testID;
        UserID = userID;
        ResultID = resultID;
        TotalScore = totalScore;
    }
}

