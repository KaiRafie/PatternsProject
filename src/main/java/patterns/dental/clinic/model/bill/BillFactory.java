package patterns.dental.clinic.model.bill;

import lombok.Getter;
import patterns.dental.clinic.model.visit.Visit;
import patterns.dental.clinic.model.user.Patient;

import java.sql.Time;
import java.util.Date;
import java.util.InputMismatchException;

@Getter
public class BillFactory {
    private static BillFactory billFactory;


    private BillFactory() {
    }

    public static BillFactory getInstance() {
        if (billFactory == null) {
            synchronized (BillFactory.class) {
                if (billFactory == null) {
                    billFactory = new BillFactory();
                }
            }
        }
        return billFactory;
    }

    public Bill createPatientBill(long billId, Visit visit, String date, String time, int subTotal, int total, int insuranceDeduction,
                                  String patientFirstName, String patientLastName) {
        return new PatientBill(billId, visit, date, time, subTotal, total, insuranceDeduction, patientFirstName, patientLastName);
    }

    public Bill createDentistBill(long billId, Visit visit, String date, String time, int subTotal, int total, int insuranceDeduction,
                                  String procedureInfo, Patient patient, String dentistFirstName, String dentistLastName) {
        String patientFullName = patient.getFirstName() + " " + patient.getLastName();
        String dentistFullName = dentistFirstName + " " + dentistLastName;
        String patientInfo = patient.toString();
        return new DentistBill(billId, visit, date, time, subTotal, total, insuranceDeduction, procedureInfo, patientFullName,
                dentistFullName, patientInfo);
    }
}

