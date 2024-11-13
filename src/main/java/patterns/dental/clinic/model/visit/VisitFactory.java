package patterns.dental.clinic.model.visit;

import lombok.Getter;
import patterns.dental.clinic.model.user.Dentist;
import patterns.dental.clinic.model.user.Patient;

import java.sql.Date;
import java.sql.Time;

@Getter
public class VisitFactory {
    private static VisitFactory visitFactory;

    private static long visitId = 0;

    private VisitFactory() {
    }

    public static VisitFactory getInstance() {
        if (visitFactory == null) {
            synchronized (VisitFactory.class) {
                if (visitFactory == null) {
                    visitFactory = new VisitFactory();
                }
            }
        }
        return visitFactory;
    }

    public Visit generateVisit(Patient patient, Dentist dentist, Date date, Time time, String visitType) {
        ++visitId;
        return new Visit(visitId, visitType, date, time, patient, dentist);
    }

}
