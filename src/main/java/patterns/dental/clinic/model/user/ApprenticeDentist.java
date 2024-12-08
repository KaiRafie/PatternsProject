package patterns.dental.clinic.model.user;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Setter

public class ApprenticeDentist extends Dentist {
    private ArrayList<String> allowedOperations;

    public ApprenticeDentist(String firstName, String lastName, String loginID, String loginPass, Date dateOfBirth) {
        super(firstName, lastName, loginID, loginPass, dateOfBirth);
    }

    public void addOperation(Operations value){
        allowedOperations.add(value.toString());
    }


}
