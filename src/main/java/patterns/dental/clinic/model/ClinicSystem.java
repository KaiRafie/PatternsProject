package patterns.dental.clinic.model;

import patterns.dental.clinic.controller.ClinicSystemController;
import patterns.dental.clinic.controller.DatabaseController;
import patterns.dental.clinic.memento.ClinicSystemMemento;
import patterns.dental.clinic.model.bill.Bill;
import patterns.dental.clinic.model.user.Dentist;
import patterns.dental.clinic.model.user.Patient;
import patterns.dental.clinic.model.user.User;
import patterns.dental.clinic.model.visit.Visit;

import java.util.ArrayList;
import java.util.List;


public class ClinicSystem {
    private static ClinicSystem clinicSystem;

    private List<User> usersList = new ArrayList<>(); // not much needed but for being a temp history when loading the program for the first time
    // and then check it manually later on when you want to check back some old modified data.
    private List<Patient> patientsList = new ArrayList<>();
    private List<Dentist> dentistsList = new ArrayList<>();
    private List<Bill> billsList = new ArrayList<>();
    private List<Visit> visitsList = new ArrayList<>();

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

    public ClinicSystemMemento saveSystemHistory() {
        return new ClinicSystemMemento(usersList, patientsList, dentistsList, billsList, visitsList);
    }

    public void restoreSystemState(ClinicSystemMemento memento) {
        this.usersList = new ArrayList<>(memento.getUsersList());
        this.patientsList = new ArrayList<>(memento.getPatientsList());
        this.dentistsList = new ArrayList<>(memento.getDentistsList());
        this.billsList = new ArrayList<>(memento.getBillsList());
        this.visitsList = new ArrayList<>(memento.getVisitsList());
    }

}
