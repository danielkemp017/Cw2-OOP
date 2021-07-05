package Classes;

public class Question {
    public Integer QuestionID;
    public String Description;
    public String QuestionType;
    public String CorrectAnswer;
    public String Tags;
    public Integer PointValue;
    public Integer MarkedManually;

    public Integer getQuestionID()
    {
        return QuestionID;
    }
    public String getDescription()
    {
        return Description;
    }
    public String getQuestionType()
    {
        return QuestionType;
    }
    public String getCorrectAnswer()
    {
        return CorrectAnswer;
    }
    public String getTags()
    {
        return Tags;
    }
    public Integer getPointValue()
    {
        return PointValue;
    }
    public Integer getMarkedManually() {return MarkedManually;}

    public Question (String description, String questionType, String correctAnswer, String tags, Integer pointValue, Integer markedManually)
    {
        Description = description;
        QuestionType = questionType;
        CorrectAnswer = correctAnswer;
        Tags = tags;
        PointValue = pointValue;
        MarkedManually = markedManually;
    }
    public Question (Integer questionID, String description, String questionType, String correctAnswer, String tags, Integer pointValue, Integer markedManually)
    {
        QuestionID = questionID;
        Description = description;
        QuestionType = questionType;
        CorrectAnswer = correctAnswer;
        Tags = tags;
        PointValue = pointValue;
        MarkedManually = markedManually;
    }
}
