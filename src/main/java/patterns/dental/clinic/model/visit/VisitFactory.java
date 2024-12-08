package patterns.dental.clinic.model.visit;

import lombok.Getter;
import patterns.dental.clinic.model.user.Dentist;
import patterns.dental.clinic.model.user.Patient;

@Getter
public class VisitFactory {

    public static Visit generateVisit(long visitId, Patient patient, Dentist dentist, String date, String time, String visitType,
                                    String procedure) {

        return new Visit(visitId, visitType, date, time, patient, dentist, procedure);
    }

}
