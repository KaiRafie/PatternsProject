package patterns.dental.clinic.model.user;


import java.util.Date;

public abstract class Dentist extends User {
    private static long lastId = 0;
    private long dentistID;

    public Dentist(String firstName, String lastName, String loginID, String loginPass, Date dateOfBirth) {
        super(firstName, lastName, loginPass, dateOfBirth);
        dentistID = generateID();
    }

    public long generateID(){
        //To be possibly changed (implemented in DB or in Java)
        return lastId++;
    }
}
