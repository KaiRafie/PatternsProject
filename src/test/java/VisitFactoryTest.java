import org.junit.jupiter.api.Test;
import patterns.dental.clinic.model.visit.*;
import patterns.dental.clinic.model.user.*;
import patterns.dental.clinic.model.bill.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Arrays;

public class VisitFactoryTest {

    @Test
    void testGenerateVisitWithDentistAndProcedure() {
        // Arrange
        long visitId = 1;
        String visitType = "Tooth Extraction";
        String date = "2024-12-02";
        String time = "2:00 PM";
        Patient patient = new Patient("Jane", "Smith", 1002, "securepass", "1995-05-15");
        Dentist dentist = new RegularDentist("Dr. Emily", "Brown", 2001, "dentistpass", "1985-07-20",
                List.of("Extraction", "Cleaning"), "Oral Surgery");
        String procedureInformation = "Wisdom Tooth Extraction";

        // Act
        Visit visit = VisitFactory.generateVisit(visitId, patient, dentist, date, time, visitType, procedureInformation);

        // Assert
        assertNotNull(visit, "The created visit should not be null");
        assertEquals(visitId, visit.getVisitId(), "Visit ID should match");
        assertEquals(visitType, visit.getVisitType(), "Visit type should match");
        assertEquals(date, visit.getDate(), "Visit date should match");
        assertEquals(time, visit.getTime(), "Visit time should match");
        assertEquals(patient, visit.getPatient(), "Patient should match");
        assertEquals(dentist, visit.getDentist(), "Dentist should match");
        assertEquals(procedureInformation, visit.getProcedureInformation(), "Procedure information should match");
    }

    @Test
    void testGenerateVisitWithoutDentistAndProcedure() {
        long visitId = 2;
        String visitType = "Routine Checkup";
        String date = "2024-12-01";
        String time = "10:00 AM";
        Patient patient = new Patient("John", "Doe", 1001, "password123", "1990-01-01");

        Visit visit = VisitFactory.generateVisit(visitId, patient, null, date, time, visitType, null);

        assertNotNull(visit, "The created visit should not be null");
        assertEquals(visitId, visit.getVisitId(), "Visit ID should match");
        assertEquals(visitType, visit.getVisitType(), "Visit type should match");
        assertEquals(date, visit.getDate(), "Visit date should match");
        assertEquals(time, visit.getTime(), "Visit time should match");
        assertEquals(patient, visit.getPatient(), "Patient should match");
        assertNull(visit.getDentist(), "Dentist should be null for a visit without a dentist");
        assertNull(visit.getProcedureInformation(), "Procedure information should be null for a visit without a procedure");
    }

    @Test
    void testToStringMethod() {
        long visitId = 3;
        String visitType = "Consultation";
        String date = "2024-12-03";
        String time = "11:00 AM";
        Patient patient = new Patient("Alice", "Johnson", 1003, "alicepass", "1988-04-10");
        Dentist dentist = new RegularDentist("Dr. Mark", "White", 2002, "dentistmark", "1980-11-25",
                List.of("Consultation", "X-ray"), "Orthodontics");
        String procedureInformation = "Initial Consultation";

        Visit visit = VisitFactory.generateVisit(visitId, patient, dentist, date, time, visitType, procedureInformation);
        String visitString = visit.toString();

        assertNotNull(visitString, "The toString method should not return null");
        assertTrue(visitString.contains("visitId=3"), "Visit ID should be in the string representation");
        assertTrue(visitString.contains("visitType='Consultation'"), "Visit type should be in the string representation");
        assertTrue(visitString.contains("date=2024-12-03"), "Visit date should be in the string representation");
        assertTrue(visitString.contains("time=11:00 AM"), "Visit time should be in the string representation");
        assertTrue(visitString.contains("patient=Alice"), "Patient name should be in the string representation");
        assertTrue(visitString.contains("dentist=Dr. Mark"), "Dentist name should be in the string representation");
    }
}
