package patterns.dental.clinic.model.visit;

import patterns.dental.clinic.model.user.Dentist;
import patterns.dental.clinic.model.user.Patient;

import java.sql.Time;
import java.sql.Date;

public class Visit {
    private long visitId;
    private String visitType;
    private String date;
    private String time;
    private Patient patient;
    private Dentist dentist;
    private String procedureInformation;

    public Visit() {
    }

    public Visit(long visitId, String visitType, String date, String time, Patient patient, Dentist dentist,
                    String procedureInformation) {
        this.visitId = visitId;
        this.visitType = visitType;
        this.date = date;
        this.time = time;
        this.patient = patient;
        this.dentist = dentist;
        this.procedureInformation = procedureInformation;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "visitId=" + visitId +
                ", visitType='" + visitType + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", patient=" + patient.getFirstName() +
                ", dentist=" + dentist.getFirstName() +
                '}';
    }

    public long getVisitId() {
        return visitId;
    }

    public void setVisitId(long visitId) {
        this.visitId = visitId;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    public String getProcedureInformation() {
        return procedureInformation;
    }

    public void setProcedureInformation(String procedureInformation) {
        this.procedureInformation = procedureInformation;
    }
}
