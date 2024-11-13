package patterns.dental.clinic.model.user;


public abstract class Dentist extends User {
    private long dentistID = 0;

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
