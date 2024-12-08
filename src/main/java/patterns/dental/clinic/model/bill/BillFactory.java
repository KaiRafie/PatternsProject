package patterns.dental.clinic.model.bill;

import lombok.Getter;
import patterns.dental.clinic.model.user.Dentist;
import patterns.dental.clinic.model.visit.Visit;
import patterns.dental.clinic.model.user.Patient;

import java.sql.Time;
import java.util.Date;
import java.util.InputMismatchException;

@Getter
public class BillFactory {

    public static Bill createPatientBill(long billId, Visit visit, String date, String time, double subTotal, double total,
                                         double insuranceDeduction, Patient patient) {

        return new PatientBill(billId, visit, date, time, subTotal, total, insuranceDeduction, patient.getFirstName(),
                patient.getLastName());
    }

    public static Bill createDentistBill(long billId, Visit visit, String date, String time, double subTotal, double total,
                                         double insuranceDeduction, String procedureInfo, Patient patient, Dentist dentist) {
        String patientFullName = patient.getFirstName() + " " + patient.getLastName();
        String dentistFullName = dentist.getFirstName() + " " + dentist.getLastName();
        String patientInfo = patient.toString();
        return new DentistBill(billId, visit, date, time, subTotal, total, insuranceDeduction, procedureInfo, patientFullName,
                dentistFullName, patientInfo);
    }
}

