package patterns.dental.clinic;

import patterns.dental.clinic.model.bill.*;
import patterns.dental.clinic.model.user.Dentist;
import patterns.dental.clinic.model.user.Patient;
import patterns.dental.clinic.model.user.RegularDentist;
import patterns.dental.clinic.model.visit.Visit;
import patterns.dental.clinic.model.visit.VisitFactory;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        BillFactory billFactory = BillFactory.getInstance();
        VisitFactory visitFactoryTest = VisitFactory.getInstance();
        Date date = new Date(0,0,0);
        Time time = new Time(0,0,0);
        Patient patient = new Patient();
        Dentist dentist = new RegularDentist();
        String visitType = "checkup";
        Visit visit = visitFactoryTest.generateVisit(patient, dentist, date, time, visitType);

        Bill bill = (PatientBill)billFactory.createDefaultBill(BillType.PATIENT_BILL);

        bill.setVisit(visit);

        System.out.println(bill);
    }
}
