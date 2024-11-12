package patterns.dental.clinic;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClinicSystem {
    private List<User> usersList;
    private List<Patient> patientsList;
    private List<Dentist> dentistsList;
    private List<Bill> billsList;
    private List<Visit> visitsList;
    
}
