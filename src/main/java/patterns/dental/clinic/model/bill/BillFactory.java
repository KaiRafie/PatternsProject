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

    private long billId = 0;


    private BillFactory() {
    }

    public BillFactory getInstance() {
        if (billFactory == null) {
            synchronized (BillFactory.class) {
                if (billFactory == null) {
                    billFactory = new BillFactory();
                }
            }
        }
        return billFactory;
    }

    public Bill createDefaultBill(BillType billType) {
        int defaultBillId = 0;

        return switch (billType) {
            case DENTIST_BILL -> new DentistBill(billId, new Visit(), new Date(), new Time(0,0,0),
                    0, 0, 0, "N/A", "N/A", "N/A", "N/A" );
            case PATIENT_BILL -> new PatientBill(billId, new Visit(), new Date(), new Time(0,0,0),
                    0, 0, 0, "N/A", "N/A");
            default -> throw new InputMismatchException("Bill type not found: " + billType);
        };
    }

    public Bill createPatientBill(Visit visit, Date date, Time time, int subTotal, int total, int insuranceDeduction,
                                  String patientFirstName, String patientLastName) {
        billId++;
        return new PatientBill(billId, visit, date, time, subTotal, total, insuranceDeduction, patientFirstName, patientLastName);
    }

    public Bill createDentistBill(Visit visit, Date date, Time time, int subTotal, int total, int insuranceDeduction,
                                  String procedureInfo, Patient patient, String dentistFirstName, String dentistLastName) {
        billId++;
        String patientFullName = patient.getFirstName() + " " + patient.getLastName();
        String dentistFullName = dentistFirstName + " " + dentistLastName;
        String patientInfo = patient.toString();
        return new DentistBill(billId, visit, date, time, subTotal, total, insuranceDeduction, procedureInfo, patientFullName,
                dentistFullName, patientInfo);
    }
}

enum BillType {
    DENTIST_BILL, PATIENT_BILL
}
