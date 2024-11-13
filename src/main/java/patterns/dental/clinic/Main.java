package patterns.dental.clinic;

import patterns.dental.clinic.model.bill.BillFactory;
import patterns.dental.clinic.model.bill.BillType;
import patterns.dental.clinic.model.bill.DentistBill;
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
        VisitFactory visitFactory = VisitFactory.getInstance();
//        LocalDate currentDate = LocalDate.now();
//        Date date = new Date(currentDate.getYear() - 1900,currentDate.getMonthValue(),currentDate.getDayOfMonth());
//        Time time = new Time(11,59,59);
        Patient patient = new Patient();
        Dentist dentist = new RegularDentist();
//        String visitType = "checkup";
        Visit visit = null;

        DentistBill bill = (DentistBill)billFactory.createDefaultBill(BillType.DENTIST_BILL);

        //TODO: bill.setPatient...

        System.out.println(bill);
    }
}
