package patterns.dental.clinic.model.user;

import java.util.Date;

public abstract class User {
   
    private String firstName;
    private String lastName;
    private String loginID;
    private String loginPass;
    private Date dateOfBirth;

    public User(String firstName, String lastName, String loginPass, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginPass = loginPass;
        this.dateOfBirth = dateOfBirth;
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

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getLoginPass() {
        return loginPass;
    }

    public void setLoginPass(String loginPass) {
        this.loginPass = loginPass;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
