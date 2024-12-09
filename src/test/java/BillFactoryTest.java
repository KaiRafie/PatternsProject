import org.junit.jupiter.api.Test;
import patterns.dental.clinic.model.user.*;
import patterns.dental.clinic.model.visit.*;
import patterns.dental.clinic.model.bill.*;
import static org.junit.jupiter.api.Assertions.*;

class BillFactoryTest {

    @Test
    void testCreatePatientBill() {
        // Arrange: Create necessary objects
        long billId = 1L;
        Visit visit = new Visit("2024-12-01", "Check-up"); // Assuming a constructor for Visit
        String date = "2024-12-01";
        String time = "10:00 AM";
        double subTotal = 100.0;
        double total = 120.0;
        double insuranceDeduction = 20.0;
        Patient patient = new Patient("John", "Doe", "john.doe@example.com"); // Assuming a constructor for Patient

        // Act: Create a PatientBill using the factory
        Bill bill = BillFactory.createPatientBill(billId, visit, date, time, subTotal, total, insuranceDeduction, patient);

        // Assert: Verify that the bill is correctly created
        assertNotNull(bill, "The created bill should not be null");
        assertTrue(bill instanceof PatientBill, "The created bill should be a PatientBill");
        assertEquals(billId, bill.getBillId(), "Bill ID should match");
        assertEquals(visit, bill.getVisit(), "Visit should match");
        assertEquals(date, bill.getDate(), "Date should match");
        assertEquals(time, bill.getTime(), "Time should match");
        assertEquals(subTotal, bill.getSubTotal(), "Subtotal should match");
        assertEquals(total, bill.getTotal(), "Total should match");
    }

    @Test
    void testCreateDentistBill() {
        // Arrange: Create necessary objects
        long billId = 2L;
        Visit visit = new Visit("2024-12-02", "Filling"); // Assuming a constructor for Visit
        String date = "2024-12-02";
        String time = "2:00 PM";
        double subTotal = 200.0;
        double total = 250.0;
        double insuranceDeduction = 50.0;
        String procedureInfo = "Tooth Filling";
        Patient patient = new Patient("Jane", "Smith", "jane.smith@example.com"); // Assuming a constructor for Patient
        Dentist dentist = new Dentist("Dr. Emily", "Brown", "emily.brown@example.com"); // Assuming a constructor for Dentist

        // Act: Create a DentistBill using the factory
        Bill bill = BillFactory.createDentistBill(billId, visit, date, time, subTotal, total, insuranceDeduction,
                procedureInfo, patient, dentist);

        // Assert: Verify that the bill is correctly created
        assertNotNull(bill, "The created bill should not be null");
        assertTrue(bill instanceof DentistBill, "The created bill should be a DentistBill");
        assertEquals(billId, bill.getBillId(), "Bill ID should match");
        assertEquals(visit, bill.getVisit(), "Visit should match");
        assertEquals(date, bill.getDate(), "Date should match");
        assertEquals(time, bill.getTime(), "Time should match");
        assertEquals(subTotal, bill.getSubTotal(), "Subtotal should match");
        assertEquals(total, bill.getTotal(), "Total should match");
        assertEquals(procedureInfo, ((DentistBill) bill).getProcedureInfo(), "Procedure info should match");
    }
}

