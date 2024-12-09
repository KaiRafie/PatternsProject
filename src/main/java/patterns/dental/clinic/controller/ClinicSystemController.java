package patterns.dental.clinic.controller;

import lombok.Getter;
import lombok.Setter;
import patterns.dental.clinic.memento.ClinicSystemMemento;
import patterns.dental.clinic.model.ClinicSystem;
import patterns.dental.clinic.model.bill.Bill;
import patterns.dental.clinic.model.bill.BillFactory;
import patterns.dental.clinic.model.user.Dentist;
import patterns.dental.clinic.model.user.Patient;
import patterns.dental.clinic.model.user.User;
import patterns.dental.clinic.model.user.UserFactory;
import patterns.dental.clinic.model.visit.Visit;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.stream.Stream;

public class ClinicSystemController {
    private final Stack<ClinicSystemMemento> systemHistory = new Stack<>();
    private ClinicSystem clinicSystem = ClinicSystem.getInstance();

    public static List<User> queryAllUsers() {
        List<Patient> patients = DatabaseController.queryAllPatientRecords();
        List<Dentist> dentists = DatabaseController.queryAllDentistRecords();
        List<User> users = Stream.concat(patients.stream(), dentists.stream())
                .sorted(Comparator.comparingLong(User::getUserID))
                .toList();

        return users;
    }

    public void saveSystemHistory() {
        ClinicSystemMemento memento = clinicSystem.saveSystemHistory();
        systemHistory.push(memento);
    }

    public void restoreSystemHistory() {
        if (!systemHistory.isEmpty()) {
            ClinicSystemMemento memento = systemHistory.pop();
            clinicSystem.restoreSystemState(memento);
        } else {
            throw new NoSuchElementException("No system state to restore.");
        }
    }

    public boolean createBill(long visitId, String date, String time, double subTotal, double total, double insuranceDeduction) {
        DatabaseController.insertBillRecord(visitId, date, time, subTotal, total, insuranceDeduction);
        Bill bill = DatabaseController.queryLastBill();
        String billType = bill.getClass().getSimpleName();
        Bill newBill = null;
        if (billType.equalsIgnoreCase("patientbill")) {
            newBill = BillFactory.createPatientBill(bill.getBillId(), bill.getVisit(), date, time, subTotal, total,
                    insuranceDeduction, bill.getVisit().getPatient());
        } else if (billType.equalsIgnoreCase("dentistbill")){
            newBill = BillFactory.createDentistBill(bill.getBillId(), bill.getVisit(), date, time, subTotal, total,
                    insuranceDeduction, bill.getVisit().getProcedureInformation(), bill.getVisit().getPatient(),
                    bill.getVisit().getDentist());
        }
        clinicSystem.getBillsList().add(newBill);

        if (clinicSystem.getBillsList().contains(newBill) && bill.getBillId() == newBill.getBillId()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateBill(long billId, long visitId, String date, String time, double subTotal, double total,
                                double insuranceDeduction) {
        DatabaseController.updateBillRecord(billId, visitId, date, time, subTotal, total, insuranceDeduction);
        Visit visit = DatabaseController.queryVisitByBillId(billId);

        for (int i = 0; i < clinicSystem.getBillsList().size(); i++) {

            Bill bill = clinicSystem.getBillsList().get(i);
            String billType = bill.getClass().getSimpleName();
            if (bill.getBillId() == billId) {
                Bill updatedBill = null;
                if (billType.equalsIgnoreCase("patientbill")) {
                    updatedBill = BillFactory.createPatientBill(billId, visit, date, time, subTotal, total,
                            insuranceDeduction, visit.getPatient());


                } else if (billType.equalsIgnoreCase("dentistbill")){
                    updatedBill = BillFactory.createDentistBill(billId, visit, date, time, subTotal, total,
                            insuranceDeduction, visit.getProcedureInformation(), visit.getPatient(), visit.getDentist());

                }
                clinicSystem.getBillsList().set(i, updatedBill);
                return true;
            }
        }

        return false;
    }

    public Bill removeBill(long billId) {
        DatabaseController.deleteBillRecord(billId);

        Bill removedBill = null;

        for (int i = 0; i < clinicSystem.getBillsList().size(); i++) {
            Bill bill = clinicSystem.getBillsList().get(i);

            if (bill.getBillId() == billId) {
                removedBill = clinicSystem.getBillsList().remove(i);
                break;
            }
        }
        return removedBill;
    }

    public boolean createDentist(String fistName, String lastName, String password, String birthDate, List<String> Operations,
                                    String specialty) {
        DatabaseController.insertDentistRecord(fistName, lastName, birthDate, Operations, specialty, password);

        Dentist dentist = DatabaseController.queryLastDentistRecord();

        clinicSystem.getDentistsList().add(dentist);

        if (clinicSystem.getDentistsList().contains(dentist)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateDentist(long dentistId, String firstName, String lastName, String birthDate,
                              List<String> allowedOperations, String specialty, String password) {
        DatabaseController.updateDentistRecord(dentistId, firstName, lastName, birthDate, allowedOperations, specialty, password);

        for (int i = 0; i < clinicSystem.getDentistsList().size(); i++) {

            Dentist dentist = clinicSystem.getDentistsList().get(i);

            if (dentist.getUserID() == dentistId) {
                Dentist updatedDentist = UserFactory.createDentist(dentistId, firstName, lastName, password, birthDate,
                        specialty, allowedOperations);
                clinicSystem.getDentistsList().set(i, updatedDentist);
                return true;
            }
        }

        return false;
    }

    public Dentist removeDentist(long dentistId) {
        DatabaseController.deleteDentistRecord(dentistId);

        Dentist removedDentist = null;

        for (int i = 0; i < clinicSystem.getDentistsList().size(); i++) {
            Dentist dentist = clinicSystem.getDentistsList().get(i);

            if (dentist.getUserID() == dentistId) {
                removedDentist = clinicSystem.getDentistsList().remove(i);
                break;
            }
        }
        return removedDentist;
    }

    public boolean createPatient(String firstName, String lastName, String birthDate, String password) {
        DatabaseController.insertPatientRecord(firstName, lastName, birthDate, password);

        User patient = DatabaseController.queryLastPatientRecord();

        clinicSystem.getPatientsList().add((Patient) patient);

        if (clinicSystem.getPatientsList().contains(patient)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updatePatient(long patientId, String firstName, String lastName, String birthDate, String password) {
        DatabaseController.updatePatientRecord(patientId, firstName, lastName, birthDate, password);

        for (int i = 0; i < clinicSystem.getPatientsList().size(); i++) {
            User patient = clinicSystem.getPatientsList().get(i);

            if (patient.getUserID() == patientId) {
                User updatedPatient = UserFactory.createPatient(firstName, lastName, password, birthDate);

                clinicSystem.getPatientsList().set(i, (Patient) updatedPatient);
                return true;
            }
        }
        return false;
    }

    public User removePatient(long patientId) {
        DatabaseController.deletePatientRecord(patientId);

        Patient removedPatient = null;

        for (int i = 0; i < clinicSystem.getPatientsList().size(); i++) {
            User patient = clinicSystem.getPatientsList().get(i);

            if (patient.getUserID() == patientId) {
                removedPatient = clinicSystem.getPatientsList().remove(i);
                break;
            }
        }
        return removedPatient;
    }

    public Visit removeVisit(long visitId) {
        DatabaseController.deleteVisitRecord(visitId);

        Visit removedVisit = null;

        for (int i = 0; i < clinicSystem.getBillsList().size(); i++) {
            Visit visit = clinicSystem.getVisitsList().get(i);

            if (visit.getVisitId() == visitId) {
                removedVisit = clinicSystem.getVisitsList().remove(i);
                break;
            }
        }
        return removedVisit;
    }

    public boolean createVisit(long dentistId, long patientId, String visitType, String date, String time, String procedure) {
        DatabaseController.insertVisitRecord(dentistId, patientId, visitType, date, time, procedure);

        Visit visit = DatabaseController.queryLastVisit();

        clinicSystem.getVisitsList().add(visit);

        if (clinicSystem.getVisitsList().contains(visit)) {
            return true;
        } else {
            return false;
        }
    }
}
