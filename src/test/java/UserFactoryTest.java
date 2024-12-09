import org.junit.jupiter.api.Test;
import patterns.dental.clinic.model.visit.*;
import patterns.dental.clinic.model.user.*;
import patterns.dental.clinic.model.bill.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Arrays;

public class UserFactoryTest {

        @Test
        void testCreatePatient() {
            String firstName = "John";
            String lastName = "Doe";
            String loginPass = "password123";
            String dateOfBirth = "1990-01-01";

            User patient = UserFactory.createPatient(firstName, lastName, loginPass, dateOfBirth);

            assertNotNull(patient, "The created patient should not be null");
            assertTrue(patient instanceof Patient, "The created user should be a Patient");
            assertEquals(firstName, patient.getFirstName(), "First name should match");
            assertEquals(lastName, patient.getLastName(), "Last name should match");
            assertEquals(loginPass, patient.getLoginPass(), "Login password should match");
            assertEquals(dateOfBirth, ((Patient) patient).getDateOfBirth(), "Date of birth should match");
        }

        @Test
        void testCreateRegularDentist() {
            long dentistId = 101;
            String firstName = "Emily";
            String lastName = "Brown";
            String loginPass = "12345";
            String dateOfBirth = "1985-07-20";
            String specialty = "Oral Surgery";
            List<String> allowedOperations = List.of("Extraction", "Cleaning");

            Dentist dentist = UserFactory.createDentist(dentistId, firstName, lastName, loginPass, dateOfBirth, specialty, allowedOperations);

            assertNotNull(dentist, "The created dentist should not be null");
            assertTrue(dentist instanceof RegularDentist, "The created dentist should be a RegularDentist");
            assertEquals(dentistId, dentist.getUserID(), "Dentist ID should match");
            assertEquals(firstName, dentist.getFirstName(), "First name should match");
            assertEquals(lastName, dentist.getLastName(), "Last name should match");
            assertEquals(loginPass, dentist.getLoginPass(), "Login password should match");
            assertEquals(dateOfBirth, dentist.getDateOfBirth(), "Date of birth should match");
            assertEquals(specialty, ((RegularDentist) dentist).getSpecialty(), "Specialty should match");
            assertEquals(allowedOperations, ((RegularDentist) dentist).getAllowedOperations(), "Allowed operations should match");
        }

        @Test
        void testCreateApprenticeDentist() {
            long dentistId = 102;
            String firstName = "Alice";
            String lastName = "Smith";
            String loginPass = "12345";
            String dateOfBirth = "1995-03-15";
            String specialty = "Apprentice - General Dentistry";
            List<String> allowedOperations = List.of("Cleaning", "X-Ray");

            Dentist dentist = UserFactory.createDentist(dentistId, firstName, lastName, loginPass, dateOfBirth, specialty, allowedOperations);

            assertNotNull(dentist, "The created dentist should not be null");
            assertTrue(dentist instanceof ApprenticeDentist, "The created dentist should be an ApprenticeDentist");
            assertEquals(dentistId, dentist.getUserID(), "Dentist ID should match");
            assertEquals(firstName, dentist.getFirstName(), "First name should match");
            assertEquals(lastName, dentist.getLastName(), "Last name should match");
            assertEquals(loginPass, dentist.getLoginPass(), "Login password should match");
            assertEquals(dateOfBirth, dentist.getDateOfBirth(), "Date of birth should match");
            assertEquals(specialty, ((ApprenticeDentist) dentist).getSpecialty(), "Specialty should match");
            assertEquals(allowedOperations, ((ApprenticeDentist) dentist).getAllowedOperations(), "Allowed operations should match");
        }
}
