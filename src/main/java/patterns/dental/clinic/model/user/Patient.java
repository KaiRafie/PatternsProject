package patterns.dental.clinic.model.user;

import lombok.*;

@Getter
@Setter

public class Patient extends User {
    private long patientID;

    public long generateID(){
        //TODO: Implement id generation method
        return 0;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientID=" + patientID +
                ", dateOfBirth=" + getDateOfBirth() +
                ", patientFullName" + getFirstName() + " " + getLastName() +
                '}';
    }
}
