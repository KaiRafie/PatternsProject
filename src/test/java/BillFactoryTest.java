import org.junit.Assert;
import org.junit.Test;
import patterns.dental.clinic.model.bill.*;
import patterns.dental.clinic.model.user.Dentist;
import patterns.dental.clinic.model.user.Patient;
import patterns.dental.clinic.model.user.RegularDentist;
import patterns.dental.clinic.model.visit.Visit;
import patterns.dental.clinic.model.visit.VisitFactory;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class BillFactoryTest {

    @Test
    public void testGetInstance1() {
        BillFactory obj1 = BillFactory.getInstance();
        BillFactory obj2 = BillFactory.getInstance();
        boolean expResult = true;
        boolean result = obj1 == obj2;

        Assert.assertEquals(expResult, result);
    }

    @Test
    public void testGetInstance2() {
        VisitFactory obj1 = VisitFactory.getInstance();
        VisitFactory obj2 = VisitFactory.getInstance();
        boolean expResult = true;
        boolean result = obj1.toString().equals(obj2.toString());

        Assert.assertEquals(expResult, result);
    }

    //these two tests will be different from a machine to another and the only difference will be the date either 1899 or 1900
    @Test
    public void testCreateDefaultPatientBill1() {
        BillFactory billFactory = BillFactory.getInstance();
        VisitFactory visitFactoryTest = VisitFactory.getInstance();
        Date date = new Date(0,0,0);
        Time time = new Time(0,0,0);
        Patient patient = new Patient();
        Dentist dentist = new RegularDentist();
        String visitType = "checkup";
        Visit visit = visitFactoryTest.generateVisit(patient, dentist, date, time, visitType);

        Bill bill = (DentistBill)billFactory.createDefaultBill(BillType.DENTIST_BILL);

        bill.setVisit(visit);
        String expResult = "DentistBill{" +
                "billId=0" +
                ", visit=Visit{visitId=1" +
                ", visitType='checkup'" +
                ", date=1899-12-31, time=00:00:00" +
                ", patient=null, dentist=null}" +
                ", date=Sun Dec 31 00:00:00 EST 1899" +
                ", time=00:00:00, subTotal=0.0, total=0.0" +
                ", insuranceDeduction=0.0, procedureInformation='N/A'" +
                ", patientFullName='N/A'" +
                ", dentistFullName='N/A'" +
                ", patientInformation='N/A'" +
                "}";
        String result = bill.toString();

        Assert.assertEquals(expResult, result);
    }
    @Test
    public void testCreateDefaultPatientBill2() {
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
        String expResult = "PatientBill{" +
                "billId=0" +
                ", FirstName=N/A" +
                ", LastNameN/A" +
                ", visit=Visit{visitId=1" +
                ", visitType='checkup'" +
                ", date=1899-12-31" +
                ", time=00:00:00" +
                ", patient=null" +
                ", dentist=null}" +
                ", date=Sun Dec 31 00:00:00 EST 1899" +
                ", time=00:00:00" +
                ", subTotal=0.0" +
                ", total=0.0" +
                ", insuranceDeduction=0.0" +
                "}";
        String result = bill.toString();

        Assert.assertEquals(expResult, result);
    }
}
