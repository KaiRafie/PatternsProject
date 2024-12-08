package patterns.dental.clinic.model.user;

import java.util.Date;

public abstract class User {
   
    private String firstName;
    private String lastName;
    private long userID;
    private String loginPass;
    private String dateOfBirth;

    public User() {
    }

    public User(String firstName, String lastName, long userID, String loginPass, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
        this.loginPass = loginPass;
        this.dateOfBirth = dateOfBirth;
    }

    public User(String firstName, String lastName, String loginPass, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginPass = loginPass;
        this.dateOfBirth = dateOfBirth;
    }

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long loginID) {
        this.userID = loginID;
    }

    public String getLoginPass() {
        return loginPass;
    }

    public void setLoginPass(String loginPass) {
        this.loginPass = loginPass;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
