package patterns.dental.clinic;

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
