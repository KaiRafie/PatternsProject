import org.junit.jupiter.api.Test;
import patterns.dental.clinic.model.visit.*;
import patterns.dental.clinic.model.user.*;
import patterns.dental.clinic.model.bill.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Arrays;

class BillFactoryTest {

    @Test
    void testCreatePatientBill() {
        // Arrange
        long billId = 1;
        Visit visit = new Visit(101, "Routine Checkup", "2024-12-01", "10:00 AM",
                new Patient("John", "Doe", 1001L, "password123", "1990-01-01"), null, null);
        String date = "2024-12-01";
        String time = "10:00 AM";
        double subTotal = 100.0;
        double total = 120.0;
        double insuranceDeduction = 20.0;
        Patient patient = new Patient("John", "Doe", 1001L, "password123", "1990-01-01");

        // Act
        Bill bill = BillFactory.createPatientBill(billId, visit, date, time, subTotal, total, insuranceDeduction, patient);

        // Assert
        assertNotNull(bill, "The created bill should not be null");
        assertTrue(bill instanceof PatientBill, "The created bill should be a PatientBill");
        assertEquals(billId, bill.getBillId(), "Bill ID should match");
        assertEquals(visit, bill.getVisit(), "Visit should match");
        assertEquals(date, bill.getDate(), "Date should match");
        assertEquals(time, bill.getTime(), "Time should match");
        assertEquals(subTotal, bill.getSubTotal(), "Subtotal should match");
        assertEquals(total, bill.getTotal(), "Total should match");
        assertEquals(insuranceDeduction, bill.getInsuranceDeduction(), "Insurance deduction should match");
    }

    @Test
    void testCreateDentistBill() {
        // Arrange
        long billId = 2;
        Patient patient = new Patient("Jane", "Smith", 1002, "securepass", "1995-05-15");
        Dentist dentist = new RegularDentist("Dr. Emily", "Brown", 2001, "dentistpass", "1985-07-20",
                Arrays.asList("Cleaning", "Checkup"), "General Dentistry");
        Visit visit = new Visit(102, "Procedure", "2024-12-02", "2:00 PM", patient, dentist, "Filling");
        String date = "2024-12-02";
        String time = "2:00 PM";
        double subTotal = 200.0;
        double total = 250.0;
        double insuranceDeduction = 50.0;
        String procedureInfo = "Tooth Filling";

        // Act
        Bill bill = BillFactory.createDentistBill(billId, visit, date, time, subTotal, total, insuranceDeduction,
                procedureInfo, patient, dentist);

        // Assert
        assertNotNull(bill, "The created bill should not be null");
        assertTrue(bill instanceof DentistBill, "The created bill should be a DentistBill");
        assertEquals(billId, bill.getBillId(), "Bill ID should match");
        assertEquals(visit, bill.getVisit(), "Visit should match");
        assertEquals(date, bill.getDate(), "Date should match");
        assertEquals(time, bill.getTime(), "Time should match");
        assertEquals(subTotal, bill.getSubTotal(), "Subtotal should match");
        assertEquals(total, bill.getTotal(), "Total should match");
        assertEquals(insuranceDeduction, bill.getInsuranceDeduction(), "Insurance deduction should match");
        assertEquals(procedureInfo, ((DentistBill) bill).getProcedureInformation(), "Procedure info should match");
        assertEquals(patient.getFirstName() + " " + patient.getLastName(), ((DentistBill) bill).getPatientFullName(),
                "Patient full name should match");
        assertEquals(dentist.getFirstName() + " " + dentist.getLastName(), ((DentistBill) bill).getDentistFullName(),
                "Dentist full name should match");
    }
}


