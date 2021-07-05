package Tests;

import Classes.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user = null;
    private String firstName = "Joe";
    private String surname = "Blogs";
    private Integer ID = 17;
    private Integer isStaff = 1;

    @BeforeEach
    void setUp() {
        user = new User(ID,firstName,surname,isStaff);
    }

    @AfterEach
    void tearDown() {
        user = null;
    }

    @Test
    void getUserID() {
        assertEquals(user.getUserID(), 17);

    }

    @Test
    void getFirstName() {
        assertEquals(user.getFirstName(),"Joe");
    }

    @Test
    void getSurname() {
        assertEquals(user.getSurname(),"Blogs");
    }

    @Test
    void getIsStaff() {
        assertEquals(user.getIsStaff(), 1);
    }
}