package patterns.dental.clinic.model.visit;

import patterns.dental.clinic.model.user.Dentist;
import patterns.dental.clinic.model.user.Patient;

import java.sql.Time;
import java.util.Date;

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
}
