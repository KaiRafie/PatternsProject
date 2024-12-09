package patterns.dental.clinic.memento;

import patterns.dental.clinic.model.bill.Bill;
import patterns.dental.clinic.model.user.Dentist;
import patterns.dental.clinic.model.user.Patient;
import patterns.dental.clinic.model.user.User;
import patterns.dental.clinic.model.visit.Visit;

import java.util.List;

public class ClinicSystemMemento {
    private final List<User> usersList;
    private final List<Patient> patientsList;
    private final List<Dentist> dentistsList;
    private final List<Bill> billsList;
    private final List<Visit> visitsList;

    public ClinicSystemMemento(List<User> usersList, List<Patient> patientsList, List<Dentist> dentistsList,
                               List<Bill> billsList, List<Visit> visitsList) {
        this.usersList = List.copyOf(usersList);
        this.patientsList = List.copyOf(patientsList);
        this.dentistsList = List.copyOf(dentistsList);
        this.billsList = List.copyOf(billsList);
        this.visitsList = List.copyOf(visitsList);
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
}
