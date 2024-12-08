package patterns.dental.clinic.model.bill;

import lombok.*;
import patterns.dental.clinic.model.visit.Visit;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
public class PatientBill extends Bill {
    private String patientFirstName;
    private String patientLastName;

    public PatientBill(long billId, Visit visit, String date, String time, double subTotal, double total,
                       double insuranceDeduction, String patientFirstName, String patientLastName) {
        super(billId, visit, date, time, subTotal, total, insuranceDeduction);
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;

    }

    @Override
    public String toString() {
        return "PatientBill{" +
                "billId=" + getBillId() +
                ", FirstName=" + patientFirstName +
                ", LastName" + patientLastName +
                ", visit=" + getVisit() +
                ", date=" + getDate() +
                ", time=" + getTime() +
                ", subTotal=" + getSubTotal() +
                ", total=" + getTotal() +
                ", insuranceDeduction=" + getInsuranceDeduction() +
                '}';
    }
}
