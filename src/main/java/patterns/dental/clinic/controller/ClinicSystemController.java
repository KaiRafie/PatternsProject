package patterns.dental.clinic.controller;

import patterns.dental.clinic.MyList;
import patterns.dental.clinic.model.ClinicSystem;
import patterns.dental.clinic.model.bill.Bill;
import patterns.dental.clinic.model.bill.BillFactory;
import patterns.dental.clinic.model.user.Dentist;
import patterns.dental.clinic.model.user.Patient;
import patterns.dental.clinic.model.user.User;
import patterns.dental.clinic.model.visit.Visit;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClinicSystemController {
    private ClinicSystem clinicSystem = ClinicSystem.getInstance();

    public static List<User> queryAllUsers() {
        List<Patient> patients = DatabaseController.queryAllPatientRecords();
        List<Dentist> dentists = DatabaseController.queryAllDentistRecords();
        List<User> users = Stream.concat(patients.stream(), dentists.stream())
                .sorted(Comparator.comparingLong(User::getUserID))
                .toList();

        return users;
    }

    public void createBill(long visitId, String date, String time, double subTotal, double total, double insuranceDeduction) {
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
    }

    public void updateBill(long billId, long visitId, String date, String time, double subTotal, double total,
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
                break;
            }
        }
    }

    public Bill removeBill(long billId) {
        DatabaseController.deleteBillRecord(billId);

        Bill removedBill = null;

        for (int i = 0; i < clinicSystem.getBillsList().size(); i++) {
            Bill bill = clinicSystem.getBillsList().get(i);

            if (bill.getBillId() == billId) {
                removedBill = clinicSystem.getBillsList().remove(i);
            }
        }
        return removedBill;
    }

    public void createDentist(String fistName, String lastName, String password, String birthDate, MyList<String> Operations,
                                    String specialty) {
        DatabaseController.insertDentistRecord(fistName, lastName, birthDate, Operations, specialty, password);

        Dentist dentist = DatabaseController.queryLastDentistRecord();

        clinicSystem.getDentistsList().add(dentist);
        clinicSystem.getUsersList().add((dentist));

    }

    public void updateDentist(long dentistId, String firstName, String lastName, String birthDate,
                              MyList<String> allowedOperations, String specialty, String password) {
        DatabaseController.updateDentistRecord(dentistId, firstName, lastName, birthDate, allowedOperations, specialty, password);

        //TODO
    }

    public Dentist removeDentist(long dentistId) {
        DatabaseController.deleteDentistRecord(dentistId);

        Dentist removedDentist = null;

        for (int i = 0; i < clinicSystem.getBillsList().size(); i++) {
            Dentist dentist = clinicSystem.getDentistsList().get(i);

            if (dentist.getUserID() == dentistId) {
                removedDentist = clinicSystem.getDentistsList().remove(i);
            }
        }
        return removedDentist;
    }

    public void createPatient() {
        //TODO
    }

    public void updatePatient() {
        //TODO
    }

    public void removePatient() {
        //TODO
    }

    public void removeVisit() {
        //TODO
    }
}
