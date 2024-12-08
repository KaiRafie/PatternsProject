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

    public DentistBill() {
    }

    public DentistBill(long billId, Visit visit, String date, String time, double subTotal, double total,
                       double insuranceDeduction, String procedureInfo, String patientFullName, String dentistFullName,
                       String patientInfo) {
        super(billId, visit, date, time, subTotal, total, insuranceDeduction);
        this.procedureInformation = procedureInfo;
        this.patientFullName = patientFullName;
        this.dentistFullName = dentistFullName;
        this.patientInformation = patientInfo;
    }

    public String getProcedureInformation() {
        return procedureInformation;
    }

    public void setProcedureInformation(String procedureInformation) {
        this.procedureInformation = procedureInformation;
    }

    public String getPatientFullName() {
        return patientFullName;
    }

    public void setPatientFullName(String patientFullName) {
        this.patientFullName = patientFullName;
    }

    public String getDentistFullName() {
        return dentistFullName;
    }

    public void setDentistFullName(String dentistFullName) {
        this.dentistFullName = dentistFullName;
    }

    public String getPatientInformation() {
        return patientInformation;
    }

    public void setPatientInformation(String patientInformation) {
        this.patientInformation = patientInformation;
    }

    @Override
    public String toString() {
        return "DentistBill{" +
                "billId=" + getBillId() +
                ", visit=" + getVisit() +
                ", date=" + getDate() +
                ", time=" + getTime() +
                ", subTotal=" + getSubTotal() +
                ", total=" + getTotal() +
                ", insuranceDeduction=" + getInsuranceDeduction() +
                ", procedureInformation='" + procedureInformation + '\'' +
                ", patientFullName='" + patientFullName + '\'' +
                ", dentistFullName='" + dentistFullName + '\'' +
                ", patientInformation='" + patientInformation + '\'' +
                '}';
    }
}
