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
}
