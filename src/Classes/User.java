package Classes;

public class User {
    public Integer UserID;
    public String FirstName;
    public String Surname;
    public Integer IsStaff;

    public Integer getUserID() {return UserID;}
    public String getFirstName() {return FirstName;}
    public String getSurname() {return Surname;}
    public Integer getIsStaff() {return IsStaff;}

    public User(int userID, String firstName, String surname, int isStaff)
    {
        UserID = userID;
        FirstName = firstName;
        Surname = surname;
        IsStaff = isStaff;
    }

    public User(String firstName, String surname, int isStaff )
    {
        FirstName = firstName;
        Surname = surname;
        IsStaff = isStaff;
    }
}
