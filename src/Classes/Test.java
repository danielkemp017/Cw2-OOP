package Classes;

public class Test {
    public Integer TestID;
    public String QuestionList;
    public String TestName;


    public Integer getTestID()
    {
        return TestID;
    }
    public String getQuestionList() {return QuestionList;}
    public String getTestName()
    {
        return TestName;
    }


    public Test(String questionList, String testName)
    {
        QuestionList = questionList;
        TestName = testName;
    }
    public Test(Integer testID,String questionList, String testName )
    {
        TestID = testID;
        QuestionList = questionList;
        TestName = testName;
    }
}
