import org.junit.Assert;
import org.junit.Test;
import patterns.dental.clinic.model.bill.Bill;
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
    public void testCreateDefaultPatientBill() {
        BillFactory billFactory = BillFactory.getInstance();
//        LocalDate currentDate = LocalDate.now();
//        Date date = new Date(currentDate.getYear() - 1900,currentDate.getMonthValue(),currentDate.getDayOfMonth());
//        Time time = new Time(11,59,59);
//        Patient patient = new Patient();
//        Dentist dentist = new RegularDentist();
//        String visitType = "checkup";
        DentistBill bill = (DentistBill)billFactory.createDefaultBill(BillType.DENTIST_BILL);
        String expResult = "DentistBill{" +
                ", billId=" + bill.getBillId() +
                ", visit=" + bill.getVisit() +
                ", date=" + bill.getDate() +
                ", time=" + bill.getTime() +
                ", subTotal=" + bill.getSubTotal() +
                ", total=" + bill.getTotal() +
                ", insuranceDeduction=" + bill.getInsuranceDeduction() +
                ", procedureInformation='" + bill.getProcedureInformation() + '\'' +
                ", patientFullName='" + bill.getPatientFullName() + '\'' +
                ", dentistFullName='" + bill.getPatientFullName() + '\'' +
                ", patientInformation='" + bill.getPatientInformation() + '\'' +
                '}';
        String result = bill.toString();

        Assert.assertEquals(expResult, result);
    }
}
