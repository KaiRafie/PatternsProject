package patterns.dental.clinic.model.bill;

import lombok.*;
import patterns.dental.clinic.model.Visit;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
public class PatientBill extends Bill {
    private String patientFirstName;
    private String patientLastName;

    public PatientBill(long billId, Visit visit, Date date, Time time, double subTotal, double total,
                       double insuranceDeduction, String patientFirstName, String patientLastName) {
        super(billId, visit, date, time, subTotal, total, insuranceDeduction);
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;

    }


    @Override
    long generateId() {
        //TODO: implement the generate id after having the full basic structure of the whole project
        return 0;
    }
}
