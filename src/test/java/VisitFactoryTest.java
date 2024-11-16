import org.junit.Assert;
import org.junit.Test;
import patterns.dental.clinic.model.user.Dentist;
import patterns.dental.clinic.model.user.Patient;
import patterns.dental.clinic.model.user.RegularDentist;
import patterns.dental.clinic.model.visit.Visit;
import patterns.dental.clinic.model.visit.VisitFactory;

import java.sql.Date;
import java.sql.Time;

public class VisitFactoryTest {

    @Test
    public void testGetInstance1() {
        VisitFactory obj1 = VisitFactory.getInstance();
        VisitFactory obj2 = VisitFactory.getInstance();
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

    @Test
    public void testGenerateVisit() {
        Patient patient = new Patient();
        patient.setFirstName("Mark");
        Dentist dentist = new RegularDentist();
        dentist.setFirstName("Sara");
        Date date = new Date(0,0,0);
        Time time = new Time(0,0,0);
        String visitType = "checkup";

        VisitFactory visitFactory = VisitFactory.getInstance();
        Visit visit = visitFactory.generateVisit(patient, dentist, date, time, visitType);

        String expResult = "Visit{" +
                "visitId=1" +
                ", visitType='checkup" + '\'' +
                ", date=1899-12-31" +
                ", time=00:00:00" +
                ", patient=Mark" +
                ", dentist=Sara" +
                '}';
        String result = visit.toString();

        Assert.assertEquals(expResult, result);
    }
}
