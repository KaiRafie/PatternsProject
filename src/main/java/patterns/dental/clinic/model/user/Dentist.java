package patterns.dental.clinic.model.user;


import java.util.Date;

public abstract class Dentist extends User {
    private long dentistID = 0;

    public Dentist() {
    }

    public Dentist(String firstName, String lastName, String loginID, String loginPass, Date dateOfBirth, long dentistId) {
        super(firstName, lastName, loginID, loginPass, dateOfBirth);
        this.dentistID = dentistId;
    }

    private long generateID(){
        //TODO: Implement id generation method
        return 0;
    };

    @Override
    public String toString() {
        return "Dentist{" +
                "patientID=" + dentistID +
                ", dateOfBirth=" + getDateOfBirth() +
                ", patientFullName" + getFirstName() + " " + getLastName() +
                '}';
    }
}
