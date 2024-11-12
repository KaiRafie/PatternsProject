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
}
