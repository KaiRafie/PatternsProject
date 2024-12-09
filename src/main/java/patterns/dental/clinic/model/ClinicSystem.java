package patterns.dental.clinic.model;

import lombok.Getter;
import lombok.Setter;
import patterns.dental.clinic.controller.ClinicSystemController;
import patterns.dental.clinic.controller.DatabaseController;
import patterns.dental.clinic.model.bill.Bill;
import patterns.dental.clinic.model.user.Dentist;
import patterns.dental.clinic.model.user.Patient;
import patterns.dental.clinic.model.user.User;
import patterns.dental.clinic.model.visit.Visit;

import java.util.ArrayList;
import java.util.List;


public class ClinicSystem {
    private static ClinicSystem clinicSystem;

    private List<User> usersList; // not much needed but for being a temp history when loading the program for the first time
    // and then check it manually later on when you want to check back some old modified data.
    private List<Patient> patientsList;
    private List<Dentist> dentistsList;
    private List<Bill> billsList;
    private List<Visit> visitsList;

    private ClinicSystem() {
        usersList = ClinicSystemController.queryAllUsers();
        patientsList = DatabaseController.queryAllPatientRecords();
        dentistsList = DatabaseController.queryAllDentistRecords();
        billsList = DatabaseController.queryAllBillRecords();
        visitsList = DatabaseController.queryAllVisitRecords();
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public List<Patient> getPatientsList() {
        return patientsList;
    }

    public List<Dentist> getDentistsList() {
        return dentistsList;
    }

    public List<Bill> getBillsList() {
        return billsList;
    }

    public List<Visit> getVisitsList() {
        return visitsList;
    }

    /**
     * insures that the class is a singleton
     * @return a single instance of the class
     */
    public static ClinicSystem getInstance() {
        if (clinicSystem == null) {
            synchronized (ClinicSystem.class) {
                if (clinicSystem == null) {
                    clinicSystem = new ClinicSystem();
                }
            }
        }
        return clinicSystem;
    }

}
