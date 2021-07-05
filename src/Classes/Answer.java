package Classes;

public class Answer {
    public Integer AnsID;
    public Integer TestID;
    public Integer QuestionID;
    public Integer UserID;
    public String AnsText;

    public Integer getAnsID() {return AnsID;}
    public Integer getTestID() {return TestID;}
    public Integer getQuestionID() {return QuestionID;}
    public Integer getUserID() {return UserID;}
    public String getAnsText() {return AnsText;}

    public Answer (Integer testID, Integer questionID, Integer userID, String ansText)
    {
        TestID = testID;
        QuestionID = questionID;
        UserID = userID;
        AnsText = ansText;

    }

    public Answer (Integer ansID,Integer testID, Integer questionID, Integer userID, String ansText)
    {
        TestID = testID;
        QuestionID = questionID;
        UserID = userID;
        AnsText = ansText;
        AnsID = ansID;

    }


}
