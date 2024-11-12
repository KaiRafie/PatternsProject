package patterns.dental.clinic.model.user;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter

public class ApprenticeDentist extends Dentist {
    private ArrayList<String> allowedOperations;

    public ApprenticeDentist(String firstName, String lastName, String loginID, String loginPass, Date dateOfBirth) {
        super(firstName, lastName, loginID, loginPass, dateOfBirth);
    }

    enum Operations{
        TEETH_CLEANING, FLUORIDE_APPLICATION, CAVITY_CLEANING, DENTAL_EXAMINATIONS;
        @Override
        public String toString(){
            return name().replace('_', ' ');
        }
    }

    public void addOperation(Operations value){
        allowedOperations.add(value.toString());
    }

}
