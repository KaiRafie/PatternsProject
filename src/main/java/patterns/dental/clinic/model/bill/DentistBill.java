package patterns.dental.clinic.model.bill;

import lombok.*;
import patterns.dental.clinic.model.visit.Visit;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
public class DentistBill extends Bill {
    private String procedureInformation;
    private String patientFullName;
    private String dentistFullName;
    private String patientInformation;

    public DentistBill(long billId, Visit visit, Date date, Time time, double subTotal, double total,
                       double insuranceDeduction, String procedureInfo, String patientFullName, String dentistFullName,
                       String patientInfo) {
        super(billId, visit, date, time, subTotal, total, insuranceDeduction);
        this.procedureInformation = procedureInfo;
        this.patientFullName = patientFullName;
        this.dentistFullName = dentistFullName;
        this.patientInformation = patientInfo;
    }


    @Override
    long generateId() {
        //TODO: implement the generate id after having the full basic structure of the whole project
        return 0;
    }
}
