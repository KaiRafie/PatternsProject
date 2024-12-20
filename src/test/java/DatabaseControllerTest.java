import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import patterns.dental.clinic.controller.DatabaseController;
import patterns.dental.clinic.model.visit.*;
import patterns.dental.clinic.model.user.*;
import patterns.dental.clinic.model.bill.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Arrays;

public class DatabaseControllerTest {
    @BeforeAll
    static void setupDatabase() {
        DatabaseController.initializeDatabase();
    }

    @Test
    void testCreatePatientTable() {
        assertDoesNotThrow(DatabaseController::createPatientTable, "Creating the Patient table should not throw exceptions");
    }

    @Test
    void testCreateDentistTable() {
        assertDoesNotThrow(DatabaseController::createDentistTable, "Creating the Dentist table should not throw exceptions");
    }

    @Test
    void testCreateVisitTable() {
        assertDoesNotThrow(DatabaseController::createVisitTable, "Creating the Visit table should not throw exceptions");
    }

    @Test
    void testCreateBillTable() {
        assertDoesNotThrow(DatabaseController::createBillTable, "Creating the Bill table should not throw exceptions");
    }

    @Test
    void testInsertAndQueryPatientRecord() {
        String firstName = "John";
        String lastName = "Doe";
        String birthDate = "1990-01-01";
        String password = "12345";

        DatabaseController.insertPatientRecord(firstName, lastName, birthDate, password);
        List<Patient> patients = DatabaseController.queryAllPatientRecords();

        assertFalse(patients.isEmpty(), "The patient list should not be empty");
        Patient patient = patients.get(patients.size() - 1); // Get the last inserted patient
        assertEquals(firstName, patient.getFirstName(), "Patient first name should match");
        assertEquals(lastName, patient.getLastName(), "Patient last name should match");
        assertEquals(birthDate, patient.getDateOfBirth(), "Patient birth date should match");
    }

    @Test
    void testInsertAndQueryDentistRecord() {
        String firstName = "Emily";
        String lastName = "Brown";
        String birthDate = "1985-07-20";
        String specialty = "Oral Surgery";
        String password = "securepass";
        List<String> allowedOperations = List.of("Extraction", "Cleaning");

        DatabaseController.insertDentistRecord(firstName, lastName, birthDate, allowedOperations, specialty, password);
        List<Dentist> dentists = DatabaseController.queryAllDentistRecords();

        // Assert
        assertFalse(dentists.isEmpty(), "The dentist list should not be empty");
        Dentist dentist = dentists.get(dentists.size() - 1); // Get the last inserted dentist
        assertEquals(firstName, dentist.getFirstName(), "Dentist first name should match");
        assertEquals(lastName, dentist.getLastName(), "Dentist last name should match");
        assertEquals(birthDate, dentist.getDateOfBirth(), "Dentist birth date should match");
        assertEquals(specialty, ((RegularDentist) dentist).getSpecialty(), "Dentist specialty should match");
        assertEquals(allowedOperations, ((RegularDentist) dentist).getAllowedOperations(), "Dentist allowed operations should match");
    }

    @Test
    void testInsertAndQueryVisitRecord() {
        User patient = DatabaseController.queryLastPatientRecord();
        Dentist dentist = DatabaseController.queryLastDentistRecord();
        String visitType = "Routine Checkup";
        String visitDate = "2024-12-01";
        String visitTime = "10:00 AM";
        String procedure = "Teeth Cleaning";

        DatabaseController.insertVisitRecord(dentist.getUserID(), patient.getUserID(), visitType, visitDate, visitTime, procedure);
        List<Visit> visits = DatabaseController.queryAllVisitRecords();

        assertFalse(visits.isEmpty(), "The visit list should not be empty");
        Visit visit = visits.get(visits.size() - 1);
        assertEquals(visitType, visit.getVisitType(), "Visit type should match");
        assertEquals(visitDate, visit.getDate(), "Visit date should match");
        assertEquals(visitTime, visit.getTime(), "Visit time should match");
        assertEquals(procedure, visit.getProcedureInformation(), "Visit procedure should match");
        assertEquals(patient.getUserID(), visit.getPatient().getUserID(), "Visit patient ID should match");
        assertEquals(dentist.getUserID(), visit.getDentist().getUserID(), "Visit dentist ID should match");
    }

    @Test
    /*
    long dentistId,
    long patientId,
    String visitType,
    String date,
    String time,
    String procedure
     */
    void testInsertAndQueryBillRecord() {
        User patient = DatabaseController.queryLastPatientRecord();
        Dentist dentist = DatabaseController.queryLastDentistRecord();
        DatabaseController.insertVisitRecord(dentist.getUserID(), patient.getUserID(), "Cavity Checkup", "15/12/2024", "11:10:15", "Teeth Cleaning");
        Visit visit = DatabaseController.queryLastVisit();
        String billDate = "2024-12-02";
        String billTime = "3:00 PM";
        double subTotal = 100.0;
        double total = 120.0;
        double insuranceDeduction = 20.0;
        DatabaseController.insertBillRecord(visit.getVisitId(), billDate, billTime, subTotal, total, insuranceDeduction);
        List<Bill> bills = DatabaseController.queryAllBillRecords();

        assertFalse(bills.isEmpty(), "The bill list should not be empty");
        Bill bill = bills.getLast();
        assertEquals(visit.getVisitId(), bill.getVisit().getVisitId(), "Bill visit ID should match");
        assertEquals(billDate, bill.getDate(), "Bill date should match");
        assertEquals(billTime, bill.getTime(), "Bill time should match");
        assertEquals(subTotal, bill.getSubTotal(), "Bill subtotal should match");
        assertEquals(total, bill.getTotal(), "Bill total should match");
        assertEquals(insuranceDeduction, bill.getInsuranceDeduction(), "Bill insurance deduction should match");
    }

    @Test
    void testUpdatePatientRecord() {
        User patient = DatabaseController.queryLastPatientRecord();
        String updatedFirstName = "John Updated";
        String updatedLastName = "Doe Updated";
        String updatedBirthDate = "1991-01-01";
        String updatedPassword = "12345";

        DatabaseController.updatePatientRecord(patient.getUserID(), updatedFirstName, updatedLastName, updatedBirthDate, updatedPassword);
        User updatedPatient = DatabaseController.queryLastPatientRecord();

        assertEquals(updatedFirstName, updatedPatient.getFirstName(), "Updated first name should match");
        assertEquals(updatedLastName, updatedPatient.getLastName(), "Updated last name should match");
        assertEquals(updatedBirthDate, updatedPatient.getDateOfBirth(), "Updated birth date should match");
    }

    @Test
    void testQueryLastPatientRecord() {
        User patient = DatabaseController.queryLastPatientRecord();

        assertNotNull(patient, "The patient record should not be null.");
        assertEquals("John", patient.getFirstName(), "First name mismatch.");
        assertEquals("Doe", patient.getLastName(), "Last name mismatch.");
        assertEquals(1, patient.getUserID(), "User ID mismatch.");
        assertEquals("password123", patient.getLoginPass(), "Password mismatch.");
        assertEquals("1990-01-01", patient.getDateOfBirth(), "Birthdate mismatch.");
    }

    @Test
    void testQueryLastDentistRecord() {
        Dentist dentist = DatabaseController.queryLastDentistRecord();

        assertNotNull(dentist, "The dentist record should not be null.");
        assertEquals("Jane", dentist.getFirstName(), "First name mismatch.");
        assertEquals("Smith", dentist.getLastName(), "Last name mismatch.");
        assertEquals(1, dentist.getUserID(), "User ID mismatch.");
        assertEquals("specialty123", dentist.getSpecialty(), "Specialty mismatch.");
        assertTrue(dentist.getAllowedOperations().contains("Cleaning"), "Allowed operations mismatch.");
    }

    @Test
    void testQueryLastBill() {
        Bill bill = DatabaseController.queryLastBill();

        assertNotNull(bill, "The bill record should not be null.");
        assertEquals(1, bill.getBillId(), "Bill ID mismatch.");
        assertEquals(100.0, bill.getSubTotal(), "SubTotal mismatch.");
        assertEquals(110.0, bill.getTotal(), "Total mismatch.");
        assertEquals(10.0, bill.getInsuranceDeduction(), "Insurance deduction mismatch.");
        assertNotNull(bill.getVisit(), "Visit should not be null.");
    }

    @Test
    void testQueryLastVisit() {
        Visit visit = DatabaseController.queryLastVisit();

        assertNotNull(visit, "The visit record should not be null.");
        assertEquals(1, visit.getVisitId(), "Visit ID mismatch.");
        assertEquals("Consultation", visit.getVisitType(), "Visit type mismatch.");
        assertEquals("2024-12-01", visit.getDate(), "Visit date mismatch.");
        assertEquals("14:30", visit.getTime(), "Visit time mismatch.");
        assertNotNull(visit.getPatient(), "Patient should not be null.");
        assertNotNull(visit.getDentist(), "Dentist should not be null.");
    }

    @Test
    void testQueryVisitByBillId() {
        long testBillId = 1;
        Visit visit = DatabaseController.queryVisitByBillId(testBillId);

        assertNotNull(visit, "The visit record should not be null.");
        assertEquals(1, visit.getVisitId(), "Visit ID mismatch.");
        assertEquals("Consultation", visit.getVisitType(), "Visit type mismatch.");
        assertEquals("2024-12-01", visit.getDate(), "Visit date mismatch.");
        assertEquals("14:30", visit.getTime(), "Visit time mismatch.");
        assertEquals(testBillId, visit.getVisitId(), "Bill ID mismatch in the visit.");
    }
}
