package patterns.dental.clinic.model.user;


import patterns.dental.clinic.MyList;

import java.util.Date;

public abstract class Dentist extends User {
    private String specialty;
    private MyList<String> allowedOperations;

    public Dentist() {
    }


    public Dentist(String firstName, String lastName, long userID, String loginPass, String dateOfBirth,
                        MyList<String> allowedOperations, String specialty) {
        super(firstName, lastName, userID, loginPass, dateOfBirth);
        this.specialty = specialty;
        this.allowedOperations = allowedOperations;
    }

    public Dentist(String firstName, String lastName, String loginPass, String dateOfBirth) {
        super(firstName, lastName, loginPass, dateOfBirth);
    }

    public Dentist(String firstName, String lastName, long userID, String loginPass, String dateOfBirth, String specialty) {
        super( firstName,  lastName,  userID,  loginPass,  dateOfBirth);
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setAllowedOperations(MyList<String> allowedOperations) {
        this.allowedOperations = allowedOperations;
    }

    public MyList<String> getAllowedOperations() {
        return allowedOperations;
    }
}
