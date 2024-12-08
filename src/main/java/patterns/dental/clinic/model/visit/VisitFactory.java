package patterns.dental.clinic.model.visit;

import lombok.Getter;
import patterns.dental.clinic.model.user.Dentist;
import patterns.dental.clinic.model.user.Patient;

@Getter
public class VisitFactory {
    private static VisitFactory visitFactory;

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

    public Visit generateVisit(long visitId, Patient patient, Dentist dentist, String date, String time, String visitType) {

        return new Visit(visitId, visitType, date, time, patient, dentist);
    }

}
