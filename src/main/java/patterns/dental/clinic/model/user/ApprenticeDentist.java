package patterns.dental.clinic.model.user;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter

public class ApprenticeDentist extends Dentist {
    private List<String> allowedOperations;

    public ApprenticeDentist(String firstName, String lastName, String loginID, String loginPass, Date dateOfBirth,
                             long dentistId, List<String> allowedOperation) {
        super(firstName, lastName, loginID, loginPass, dateOfBirth, dentistId);
        this.allowedOperations = allowedOperation;
    }
}
