package patterns.dental.clinic.model.visit;

import patterns.dental.clinic.model.user.Dentist;
import patterns.dental.clinic.model.user.Patient;

import java.sql.Time;
import java.sql.Date;

public class Visit {
    private long visitId;
    private String visitType;
    private Date date;
    private Time time;
    private Patient patient;
    private Dentist dentist;

    public Visit() {
    }

    public Visit(long visitId, String visitType, Date date, Time time, Patient patient, Dentist dentist) {
        this.visitId = visitId;
        this.visitType = visitType;
        this.date = date;
        this.time = time;
        this.patient = patient;
        this.dentist = dentist;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "visitId=" + visitId +
                ", visitType='" + visitType + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", patient=" + getPatient().getFirstName() +
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
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
}
