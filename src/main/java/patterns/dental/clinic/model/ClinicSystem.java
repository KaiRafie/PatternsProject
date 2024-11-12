package patterns.dental.clinic.model;

import lombok.Getter;
import lombok.Setter;
import patterns.dental.clinic.model.bill.Bill;
import patterns.dental.clinic.model.bill.BillModel;
import patterns.dental.clinic.model.user.Dentist;
import patterns.dental.clinic.model.user.Patient;
import patterns.dental.clinic.model.user.User;

import java.util.List;


public class ClinicSystem {
    private ClinicSystem clinicSystem;

    @Getter
    @Setter
    private List<User> usersList;
    @Getter
    @Setter
    private List<Patient> patientsList;
    @Getter
    @Setter
    private List<Dentist> dentistsList;
    @Getter
    @Setter
    private List<Bill> billsList;
    @Getter
    @Setter
    private List<Visit> visitsList;

    private ClinicSystem() {
    }

    /**
     * insures that the class is a singleton
     * @return a single instance of the class
     */
    public ClinicSystem getInstance() {
        if (clinicSystem == null) {
            synchronized (BillModel.class) {
                if (clinicSystem == null) {
                    clinicSystem = new ClinicSystem();
                }
            }
        }
        return clinicSystem;
    }

}