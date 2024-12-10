package patterns.dental.clinic.controller;

import patterns.dental.clinic.model.bill.Bill;
import patterns.dental.clinic.model.bill.PatientBill;
import patterns.dental.clinic.model.user.*;
import patterns.dental.clinic.model.visit.Visit;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseController {
    private static final String URL = "jdbc:sqlite:src/main/resources/database/ClinicSystem.db";

    private static Connection connect() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Creating tables if they do not exist
    public static void createPatientTable() {
        // SQL to create the table
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS patient (
                patient_id INTEGER PRIMARY KEY AUTOINCREMENT,
                patient_first_name TEXT NOT NULL,
                patient_last_name TEXT NOT NULL,
                patient_birth_date TEXT NOT NULL,
                patient_login_password TEXT NOT NULL
            );
            """;

        // SQL to insert a dummy record to make patients ids start at 10000
        String insertDummySQL = """
            INSERT INTO patient(patient_id, patient_first_name, patient_last_name, patient_birth_date, patient_login_password)
            VALUES (9999, 'dummy', 'dummy', '2000-01-01', 'dummy');
            """;

        // SQL to delete the dummy record
        String deleteDummySQL = """
            DELETE FROM patient WHERE patient_id = 9999;
            """;

        try (Connection conn = connect();
             Statement stat = conn.createStatement()) {
            // Execute the table creation SQL
            stat.execute(createTableSQL);

            // Insert the dummy record
            stat.execute(insertDummySQL);

            // Delete the dummy record
            stat.execute(deleteDummySQL);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void createDentistTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS dentist(
                dentist_id INTEGER PRIMARY KEY AUTOINCREMENT,
                dentist_first_name TEXT NOT NULL,
                dentist_last_name TEXT NOT NULL,
                dentist_birth_date TEXT NOT NULL,
                allowed_operations TEXT NOT NULL,
                specialty TEXT NOT NULL,
                dentist_login_password TEXT NOT NULL
                )
                """;
        try (Connection conn = connect();
                Statement stat = conn.createStatement()) {
            stat.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createVisitTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS visit(
                visit_id INTEGER PRIMARY KEY AUTOINCREMENT,
                dentist_id INTEGER NOT NULL,
                patient_id INTEGER NOT NULL,
                visit_type TEXT NOT NULL,
                visit_date TEXT NOT NULL,
                visit_time TEXT NOT NULL,
                visit_procedure_information TEXT NOT NULL,
                FOREIGN KEY (dentist_id) REFERENCES dentist(dentist_id),
                FOREIGN KEY (patient_id) REFERENCES patient(patient_id)
                )
                """;
        try (Connection conn = connect();
                Statement stat = conn.createStatement()) {
            stat.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    private String procedureInformation;
//    private String patientFullName;
//    private String dentistFullName;
//    private String patientInformation;

    public static void createBillTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS bill(
                bill_id INTEGER PRIMARY KEY AUTOINCREMENT,
                visit_id INTEGER NOT NULL,
                bill_date TEXT NOT NULL,
                bill_time TEXT NOT NULL,
                sub_total REAL,
                total REAL,
                insurance_deduction REAL,
                FOREIGN KEY (visit_id) REFERENCES visit(visit_id)
                )
                """;
        try (Connection conn = connect();
                Statement stat = conn.createStatement()) {
            stat.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void initializeDatabase() {
        createPatientTable();
        createDentistTable();
        createVisitTable();
        createBillTable();
    }

    // Insert records into tables
    public static void insertPatientRecord(String firstName, String lastName, String birthDate, String password) {
        String sql = """
                INSERT INTO patient(patient_first_name, patient_last_name, patient_birth_date, patient_login_password)
                VALUES(?,?,?,?)
                """;

        try (Connection conn = connect();
                PreparedStatement stat = conn.prepareStatement(sql)) {
            stat.setString(1, firstName);
            stat.setString(2, lastName);
            stat.setString(3, birthDate);
            stat.setString(4, password);
            stat.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertDentistRecord(String firstName, String lastName, String birthDate, List<String> allowedOperations,
                                           String specialty, String password) {
        String sql = """
                INSERT INTO dentist(dentist_first_name, dentist_last_name, dentist_birth_date, allowed_operations, specialty,
                dentist_login_password)
                VALUES(?,?,?,?,?,?)
                """;

        try (Connection conn = connect();
                PreparedStatement stat = conn.prepareStatement(sql)) {
            stat.setString(1, firstName);
            stat.setString(2, lastName);
            stat.setString(3, birthDate);
            if (allowedOperations.isEmpty()) {
                stat.setString(4, "All, ");
            } else {
                StringBuilder sb = new StringBuilder();
                for (var operation: allowedOperations) {
                    sb.append(operation).append(", ");
                }
                stat.setString(4, sb.toString());
            }
            stat.setString(5, specialty);
            stat.setString(6, password);
            stat.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertVisitRecord(long dentistId, long patientId, String visitType, String date, String time,
                                            String procedure) {
        String sql = """
                INSERT INTO visit(dentist_id, patient_id, visit_type, visit_date, visit_time, visit_procedure_information)
                VALUES(?,?,?,?,?,?)
                """;

        try (Connection conn = connect();
                PreparedStatement stat = conn.prepareStatement(sql)) {
            stat.setLong(1, dentistId);
            stat.setLong(2, patientId);
            stat.setString(3, visitType);
            stat.setString(4, date);
            stat.setString(5, time);
            stat.setString(6, procedure);
            stat.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertBillRecord(long visitId, String date, String time, double subTotal, double total,
                                        double insuranceDeduction) {
        String sql = """
                INSERT INTO bill(visit_id, bill_date, bill_time, sub_total, total, insurance_deduction)
                VALUES(?,?,?,?,?,?)
                """;

        try (Connection conn = connect();
                PreparedStatement stat = conn.prepareStatement(sql)) {
            stat.setLong(1, visitId);
            stat.setString(2, date);
            stat.setString(3, time);
            stat.setDouble(4, subTotal);
            stat.setDouble(5, total);
            stat.setDouble(6, insuranceDeduction);
            stat.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update records in case of changing any status of any object
    public static void updatePatientRecord(long patientId, String firstName, String lastName, String birthDate,
                                           String password) {
        String sql = """
                UPDATE patient SET
                patient_first_name = ?,
                patient_last_name = ?,
                patient_birth_date = ?,
                patient_login_password = ?
                WHERE patient_id = ?
                """;

        try (Connection conn = connect();
                PreparedStatement stat = conn.prepareStatement(sql)) {

            // Set the values in the prepared statement
            stat.setString(1, firstName);
            stat.setString(2, lastName);
            stat.setString(3, birthDate);
            stat.setString(4, password);
            stat.setLong(5, patientId);

            // Execute the update statement
            int rowsAffected = stat.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Patient record updated successfully.");
            } else {
                System.out.println("No patient found with the given ID.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error updating patient record: " + e.getMessage());
        }
    }

    public static void updateDentistRecord(long dentistId, String firstName, String lastName, String birthDate,
                                           List<String> allowedOperations, String specialty, String password) {
        String sql = """
                UPDATE dentist SET
                dentist_first_name = ?,
                dentist_last_name = ?,
                dentist_birth_date = ?,
                allowed_operations = ?,
                specialty = ?,
                dentist_login_password = ?
                WHERE dentist_id = ?
                """;

        try (Connection conn = connect();
                PreparedStatement stat = conn.prepareStatement(sql)) {

            stat.setString(1, firstName);
            stat.setString(2, lastName);
            stat.setString(3, birthDate);
            stat.setString(4, allowedOperations.toString());
            stat.setString(5, specialty);
            stat.setString(6, password);
            stat.setLong(7, dentistId);

            int rowsAffected = stat.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Dentist record updated successfully.");
            } else {
                System.out.println("No dentist found with the given ID.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error updating dentist record: " + e.getMessage());
        }
    }

    public static void updateVisitRecord(long visitId, long patientId, long dentistId, String visitType, String date,
                                         String time, String procedure) {
        String sql = """
                UPDATE visit SET
                dentist_id = ?,
                patient_id = ?,
                visit_type = ?,
                visit_date = ?,
                visit_time = ?,
                visit_procedure_information = ?
                WHERE visit_id = ?
                """;

        try (Connection conn = connect();
                PreparedStatement stat = conn.prepareStatement(sql)) {

            stat.setLong(1, dentistId);
            stat.setLong(2, patientId);
            stat.setString(3, visitType);
            stat.setString(4, date);
            stat.setString(5, time);
            stat.setLong(7, visitId);

            int rowsAffected = stat.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Visit record updated successfully.");
            } else {
                System.out.println("No visit found with the given ID.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error updating visit record: " + e.getMessage());
        }
    }

    public static void updateBillRecord(long billId, long visitId, String date, String time, double subTotal, double total,
                                        double insuranceDeduction) {
        String sql = """
                UPDATE bill SET
                visit_id = ?,
                bill_date = ?,
                bill_time = ?,
                sub_total = ?,
                total = ?,
                insurance_deduction = ?
                WHERE bill_id = ?
                """;

        try (Connection conn = connect();
                PreparedStatement stat = conn.prepareStatement(sql)) {

            stat.setLong(1, visitId);
            stat.setString(2, date);
            stat.setString(3, time);
            stat.setDouble(4, subTotal);
            stat.setDouble(5, total);
            stat.setDouble(6, insuranceDeduction);
            stat.setLong(7, billId);

            int rowsAffected = stat.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Bill record updated successfully.");
            } else {
                System.out.println("No bill found with the given ID.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error updating bill record: " + e.getMessage());
        }
    }

    public static boolean updateBillTotalsWithPayment(long billId, double payment) {
        String fetchTotalSql = "SELECT total FROM bill WHERE bill_id = ?";
        String updateSql = """
            UPDATE bill SET
            sub_total = ?,
            total = ?
            WHERE bill_id = ?
            """;

        try (Connection conn = connect();
                PreparedStatement fetchStat = conn.prepareStatement(fetchTotalSql);
                PreparedStatement updateStat = conn.prepareStatement(updateSql)) {

            fetchStat.setLong(1, billId);
            double currentTotal;

            try (ResultSet resultSet = fetchStat.executeQuery()) {
                if (resultSet.next()) {
                    currentTotal = resultSet.getDouble("total");
                } else {
                    System.out.println("No bill found with the given ID.");
                    return false;
                }
            }
            double newTotal = currentTotal - payment;
            double newSubTotal = (currentTotal / 1.14975) - (payment / 1.14975);

            updateStat.setDouble(1, newSubTotal);
            updateStat.setDouble(2, newTotal);
            updateStat.setLong(3, billId);

            int rowsAffected = updateStat.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Bill totals updated successfully.");
                return true;
            } else {
                System.out.println("Failed to update bill totals.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error updating bill totals: " + e.getMessage());
        }
        return false;
    }

    public static void deletePatientRecord(long patientId) {
        String sql = """
                DELETE FROM patient WHERE patient_id = ?
                """;

        try (Connection conn = connect();
                PreparedStatement stat = conn.prepareStatement(sql)) {
            stat.setLong(1, patientId);
            stat.execute();

            int rowsAffected = stat.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Patient record deleted successfully.");
            } else {
                System.out.println("No patient found with the given ID.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting patient record: " + e.getMessage());
        }
    }

    public static void deleteDentistRecord(long dentistId) {
        String sql = """
                DELETE FROM dentist WHERE dentist_id = ?
                """;

        try (Connection conn = connect();
                PreparedStatement stat = conn.prepareStatement(sql)) {
            stat.setLong(1, dentistId);
            stat.execute();

            int rowsAffected = stat.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Dentist record deleted successfully.");
            } else {
                System.out.println("No dentist found with the given ID.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting dentist record: " + e.getMessage());
        }
    }

    public static void deleteVisitRecord(long visitId) {
        String sql = """
                DELETE FROM visit WHERE visit_id = ?
                """;

        try (Connection conn = connect();
                PreparedStatement stat = conn.prepareStatement(sql)) {
            stat.setLong(1, visitId);
            stat.execute();

            int rowsAffected = stat.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Visit record deleted successfully.");
            } else {
                System.out.println("No visit found with the given ID.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting visit record: " + e.getMessage());
        }
    }

    public static void deleteBillRecord(long billId) {
        String sql = """
                DELETE FROM bill WHERE bill_id = ?
                """;

        try (Connection conn = connect();
                PreparedStatement stat = conn.prepareStatement(sql)) {
            stat.setLong(1, billId);
            stat.execute();

            int rowsAffected = stat.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Bill record deleted successfully.");
            } else {
                System.out.println("No bill found with the given ID.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting bill record: " + e.getMessage());
        }
    }

    // Query all data
    public static List<Patient> queryAllPatientRecords() {
        String sql = "SELECT * FROM patient";
        List<Patient> patients = new ArrayList<>();

        try (Connection conn = connect();
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery(sql)) {
            while (rs.next()) {
                long patientId = rs.getLong("patient_id");
                String firstName = rs.getString("patient_first_name");
                String lastName = rs.getString("patient_last_name");
                String birthDate = rs.getString("patient_birth_date");
                String password = rs.getString("patient_login_password");

                //String firstName, String lastName, long userID, String loginPass, Date dateOfBirth
                Patient patient = new Patient(firstName, lastName, patientId, password, birthDate);

                patients.add(patient);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return patients;
    }

    public static List<Dentist> queryAllDentistRecords() {
        String sql = "SELECT * FROM dentist";
        List<Dentist> dentists = new ArrayList<>();

        try (Connection conn = connect();
             Statement stat = conn.createStatement();
             ResultSet rs = stat.executeQuery(sql)) {
            while (rs.next()) {
                long dentistId = rs.getLong("dentist_id");
                String firstName = rs.getString("dentist_first_name");
                String lastName = rs.getString("dentist_last_name");
                String birthDate = rs.getString("dentist_birth_date");
                List<String> allowedOperations = Arrays.stream(rs.getString("allowed_operations")
                                                        .split(", ")).toList();
                String specialty = rs.getString("specialty");
                String password = rs.getString("dentist_login_password");

                Dentist dentist;
                if (specialty.equalsIgnoreCase("apprentice")) {
                    dentist = new ApprenticeDentist(firstName, lastName, dentistId, password, birthDate, allowedOperations, specialty);
                } else {
                    dentist = new RegularDentist(firstName, lastName, dentistId, password, birthDate, allowedOperations, specialty);
                }

                dentists.add(dentist);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dentists;
    }

    public static List<Visit> queryAllVisitRecords() {
        //long visitId, String visitType, String date, String time, Patient patient, Dentist dentist

        String sql = """
                     SELECT visit.*, patient.*, dentist.* FROM visit
                     JOIN patient ON visit.patient_id = patient.patient_id
                     JOIN dentist ON visit.dentist_id = dentist.dentist_id
                     """;
        List<Visit> visits = new ArrayList<>();

        try (Connection conn = connect();
             Statement stat = conn.createStatement();
             ResultSet rs = stat.executeQuery(sql)) {
            while (rs.next()) {

                // the visit stuff
                long visitId = rs.getLong("visit_id");
                String visitType = rs.getString("visit_type");
                String visitDate = rs.getString("visit_date");
                String visitTime = rs.getString("visit_time");
                String procedureInformation = rs.getString("visit_procedure_information");
                // the dentist stuff
                long dentistId = rs.getLong("dentist_id");
                String dentistFirstName = rs.getString("dentist_first_name");
                String dentistLastName = rs.getString("dentist_last_name");
                String dentistBirthDate = rs.getString("dentist_birth_date");
                List<String> allowedOperations = Arrays.stream(rs.getString("allowed_operations")
                                                        .split(", ")).toList();
                String specialty = rs.getString("specialty");
                String dentistPassword = rs.getString("dentist_login_password");

                Dentist dentist;
                if (specialty.equalsIgnoreCase("apprentice")) {
                    dentist = new ApprenticeDentist(dentistFirstName, dentistLastName, dentistId, dentistPassword,
                            dentistBirthDate, allowedOperations, specialty);
                } else {
                    dentist = new RegularDentist(dentistFirstName, dentistLastName, dentistId, dentistPassword,
                            dentistBirthDate, allowedOperations, specialty);
                }

                // the patient stuff
                long patientId = rs.getLong("patient_id");
                String patientFirstName = rs.getString("patient_first_name");
                String patientLastName = rs.getString("patient_last_name");
                String patientBirthDate = rs.getString("patient_birth_date");
                String patientPassword = rs.getString("patient_login_password");


                //String firstName, String lastName, long userID, String loginPass, Date dateOfBirth
                Patient patient = new Patient(patientFirstName, patientLastName, patientId, patientPassword, patientBirthDate);

                //long visitId, String visitType, String date, String time, Patient patient, Dentist dentist
                Visit visit = new Visit(visitId, visitType, visitDate, visitTime, patient, dentist, procedureInformation);

                visits.add(visit);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return visits;
    }

    public static List<Bill> queryAllBillRecords() {
        String sql = """
                     SELECT bill.*, visit.*, patient.*, dentist.* FROM bill
                     JOIN visit ON bill.visit_id = visit.visit_id
                     JOIN patient ON visit.patient_id = patient.patient_id
                     JOIN dentist ON visit.dentist_id = dentist.dentist_id
                     """;
        List<Bill> bills = new ArrayList<>();

        try (Connection conn = connect();
             Statement stat = conn.createStatement();
             ResultSet rs = stat.executeQuery(sql)) {
            while (rs.next()) {

                // the visit stuff
                long visitId = rs.getLong("visit_id");
                String visitType = rs.getString("visit_type");
                String visitDate = rs.getString("visit_date");
                String visitTime = rs.getString("visit_time");
                String procedureInformation = rs.getString("visit_procedure_information");

                // the dentist stuff
                long dentistId = rs.getLong("dentist_id");
                String dentistFirstName = rs.getString("dentist_first_name");
                String dentistLastName = rs.getString("dentist_last_name");
                String dentistBirthDate = rs.getString("dentist_birth_date");
                List<String> allowedOperations = Arrays.stream(rs.getString("allowed_operations")
                                                        .split(", ")).toList();
                String specialty = rs.getString("specialty");
                String dentistPassword = rs.getString("dentist_login_password");

                Dentist dentist;
                if (specialty.equalsIgnoreCase("apprentice")) {
                    dentist = new ApprenticeDentist(dentistFirstName, dentistLastName, dentistId, dentistPassword,
                            dentistBirthDate, allowedOperations, specialty);
                } else {
                    dentist = new RegularDentist(dentistFirstName, dentistLastName, dentistId, dentistPassword,
                            dentistBirthDate, allowedOperations, specialty);
                }

                // the patient stuff
                long patientId = rs.getLong("patient_id");
                String patientFirstName = rs.getString("patient_first_name");
                String patientLastName = rs.getString("patient_last_name");
                String patientBirthDate = rs.getString("patient_birth_date");
                String patientPassword = rs.getString("patient_login_password");


                //String firstName, String lastName, long userID, String loginPass, Date dateOfBirth
                Patient patient = new Patient(patientFirstName, patientLastName, patientId, patientPassword, patientBirthDate);

                //long visitId, String visitType, String date, String time, Patient patient, Dentist dentist
                Visit visit = new Visit(visitId, visitType, visitDate, visitTime, patient, dentist, procedureInformation);

                // the bill stuff

                //long billId, Visit visit, String date, String time, double subTotal, double total, double insuranceDeduction
                long billId = rs.getLong("bill_id");
                String billDate = rs.getString("bill_date");
                String billTime = rs.getString("bill_time");
                double subTotal = rs.getDouble("sub_total");
                double total = rs.getDouble("total");
                double insuranceDeduction = rs.getDouble("insurance_deduction");

                Bill bill = new PatientBill(billId, visit, billDate, billTime, subTotal, total, insuranceDeduction,
                                                patientFirstName, patientLastName);

                bills.add(bill);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bills;
    }

    // Query for a specific patient
    public static List<Bill> queryBillsByPatientId(long patientId) {
        String sql = """
                     SELECT bill.*, visit.*, patient.*, dentist.* FROM bill
                     JOIN visit ON bill.visit_id = visit.visit_id
                     JOIN patient ON visit.patient_id = patient.patient_id
                     JOIN dentist ON visit.dentist_id = dentist.dentist_id
                     WHERE patient.patient_id = """ + patientId;

        List<Bill> bills = new ArrayList<>();

        try (Connection conn = connect();
             Statement stat = conn.createStatement();
             ResultSet rs = stat.executeQuery(sql)) {
            while (rs.next()) {

                // the visit stuff
                long visitId = rs.getLong("visit_id");
                String visitType = rs.getString("visit_type");
                String visitDate = rs.getString("visit_date");
                String visitTime = rs.getString("visit_time");
                String procedureInformation = rs.getString("visit_procedure_information");

                // the dentist stuff
                long dentistId = rs.getLong("dentist_id");
                String dentistFirstName = rs.getString("dentist_first_name");
                String dentistLastName = rs.getString("dentist_last_name");
                String dentistBirthDate = rs.getString("dentist_birth_date");
                List<String> allowedOperations = Arrays.stream(rs.getString("allowed_operations")
                                                                .split(", ")).toList();
                String specialty = rs.getString("specialty");
                String dentistPassword = rs.getString("dentist_login_password");

                Dentist dentist;
                if (specialty.equalsIgnoreCase("apprentice")) {
                    dentist = new ApprenticeDentist(dentistFirstName, dentistLastName, dentistId, dentistPassword,
                            dentistBirthDate, allowedOperations, specialty);
                } else {
                    dentist = new RegularDentist(dentistFirstName, dentistLastName, dentistId, dentistPassword,
                            dentistBirthDate, allowedOperations, specialty);
                }

                // the patient stuff
                long patient_id = rs.getLong("patient_id");
                String patientFirstName = rs.getString("patient_first_name");
                String patientLastName = rs.getString("patient_last_name");
                String patientBirthDate = rs.getString("patient_birth_date");
                String patientPassword = rs.getString("patient_login_password");


                //String firstName, String lastName, long userID, String loginPass, Date dateOfBirth
                Patient patient = new Patient(patientFirstName, patientLastName, patient_id, patientPassword, patientBirthDate);

                //long visitId, String visitType, String date, String time, Patient patient, Dentist dentist
                Visit visit = new Visit(visitId, visitType, visitDate, visitTime, patient, dentist, procedureInformation);

                // the bill stuff

                //long billId, Visit visit, String date, String time, double subTotal, double total, double insuranceDeduction
                long billId = rs.getLong("bill_id");
                String billDate = rs.getString("bill_date");
                String billTime = rs.getString("bill_time");
                double subTotal = rs.getDouble("sub_total");
                double total = rs.getDouble("total");
                double insuranceDeduction = rs.getDouble("insurance_deduction");

                Bill bill = new PatientBill(billId, visit, billDate, billTime, subTotal, total, insuranceDeduction,
                        patientFirstName, patientLastName);

                bills.add(bill);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bills;
    }

    public static List<Visit> queryVisitsByPatientId(long patientId) {
        //long visitId, String visitType, String date, String time, Patient patient, Dentist dentist

        String sql = """
                     SELECT visit.*, patient.*, dentist.* FROM visit
                     JOIN patient ON visit.patient_id = patient.patient_id
                     JOIN dentist ON visit.dentist_id = dentist.dentist_id
                     WHERE visit.patient_id =""" + patientId;

        List<Visit> visits = new ArrayList<>();

        try (Connection conn = connect();
             Statement stat = conn.createStatement();
             ResultSet rs = stat.executeQuery(sql)) {
            while (rs.next()) {

                // the visit stuff
                long visitId = rs.getLong("visit_id");
                String visitType = rs.getString("visit_type");
                String visitDate = rs.getString("visit_date");
                String visitTime = rs.getString("visit_time");
                String procedureInformation = rs.getString("visit_procedure_information");
                // the dentist stuff
                long dentistId = rs.getLong("dentist_id");
                String dentistFirstName = rs.getString("dentist_first_name");
                String dentistLastName = rs.getString("dentist_last_name");
                String dentistBirthDate = rs.getString("dentist_birth_date");
                List<String> allowedOperations = Arrays.stream(rs.getString("allowed_operations")
                                                        .split(", ")).toList();
                String specialty = rs.getString("specialty");
                String dentistPassword = rs.getString("dentist_login_password");

                Dentist dentist;
                if (specialty.equalsIgnoreCase("apprentice")) {
                    dentist = new ApprenticeDentist(dentistFirstName, dentistLastName, dentistId, dentistPassword,
                            dentistBirthDate, allowedOperations, specialty);
                } else {
                    dentist = new RegularDentist(dentistFirstName, dentistLastName, dentistId, dentistPassword,
                            dentistBirthDate, allowedOperations, specialty);
                }

                // the patient stuff
                long patient_id = rs.getLong("patient_id");
                String patientFirstName = rs.getString("patient_first_name");
                String patientLastName = rs.getString("patient_last_name");
                String patientBirthDate = rs.getString("patient_birth_date");
                String patientPassword = rs.getString("patient_login_password");


                //String firstName, String lastName, long userID, String loginPass, Date dateOfBirth
                Patient patient = new Patient(patientFirstName, patientLastName, patient_id, patientPassword, patientBirthDate);

                //long visitId, String visitType, String date, String time, Patient patient, Dentist dentist
                Visit visit = new Visit(visitId, visitType, visitDate, visitTime, patient, dentist, procedureInformation);

                visits.add(visit);
                System.out.println(visit.toString());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return visits;
    }

    // Query the last record in the tables to be able to insert it in the list with their id
    public static User queryLastPatientRecord() {
        User patient = null;
        String sql = """
                SELECT * FROM patient
                WHERE patient_id = (SELECT MAX(patient_id) FROM patient)
                """;

        try (Connection conn = connect();
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery(sql)) {
            while (rs.next()) {
                long patientId = rs.getLong("patient_id");
                String firstName = rs.getString("patient_first_name");
                String lastName = rs.getString("patient_last_name");
                String birthDate = rs.getString("patient_birth_date");
                String password = rs.getString("patient_login_password");

                //String firstName, String lastName, long userID, String loginPass, Date dateOfBirth
                patient = new Patient(firstName, lastName, patientId, password, birthDate);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patient;

    }

    public static Dentist queryLastDentistRecord() {
        Dentist dentist = null;
        String sql = """
                SELECT * FROM dentist
                WHERE dentist_id = (SELECT MAX(dentist_id) FROM dentist)
                """;

        try (Connection conn = connect();
             Statement stat = conn.createStatement();
             ResultSet rs = stat.executeQuery(sql)) {
            while (rs.next()) {
                long dentistId = rs.getLong("dentist_id");
                String firstName = rs.getString("dentist_first_name");
                String lastName = rs.getString("dentist_last_name");
                String birthDate = rs.getString("dentist_birth_date");
                List<String> allowedOperations = Arrays.stream(rs.getString("allowed_operations")
                                                        .split(", ")).toList();
                String specialty = rs.getString("specialty");
                String password = rs.getString("dentist_login_password");


                if (specialty.equalsIgnoreCase("apprentice")) {
                    dentist = new ApprenticeDentist(firstName, lastName, dentistId, password, birthDate, allowedOperations,
                            specialty);
                } else {
                    dentist = new RegularDentist(firstName, lastName, dentistId, password, birthDate, allowedOperations,
                            specialty);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dentist;
    }

    public static Bill queryLastBill() {
        String sql = """
                     SELECT bill.*, visit.*, patient.*, dentist.* FROM bill
                     JOIN visit ON bill.visit_id = visit.visit_id
                     JOIN patient ON visit.patient_id = patient.patient_id
                     JOIN dentist ON visit.dentist_id = dentist.dentist_id
                     WHERE bill.bill_id = (SELECT MAX(bill_id) FROM bill)""";
        Bill bill = null;

        try (Connection conn = connect();
             Statement stat = conn.createStatement();
             ResultSet rs = stat.executeQuery(sql)) {
            while (rs.next()) {

                // the visit stuff
                long visitId = rs.getLong("visit_id");
                String visitType = rs.getString("visit_type");
                String visitDate = rs.getString("visit_date");
                String visitTime = rs.getString("visit_time");
                String procedureInformation = rs.getString("visit_procedure_information");

                // the dentist stuff
                long dentistId = rs.getLong("dentist_id");
                String dentistFirstName = rs.getString("dentist_first_name");
                String dentistLastName = rs.getString("dentist_last_name");
                String dentistBirthDate = rs.getString("dentist_birth_date");
                List<String> allowedOperations = Arrays.stream(rs.getString("allowed_operations")
                        .split(", ")).toList();
                String specialty = rs.getString("specialty");
                String dentistPassword = rs.getString("dentist_login_password");

                Dentist dentist;
                if (specialty.equalsIgnoreCase("apprentice")) {
                    dentist = new ApprenticeDentist(dentistFirstName, dentistLastName, dentistId, dentistPassword,
                            dentistBirthDate, allowedOperations, specialty);
                } else {
                    dentist = new RegularDentist(dentistFirstName, dentistLastName, dentistId, dentistPassword,
                            dentistBirthDate, allowedOperations, specialty);
                }

                // the patient stuff
                long patient_id = rs.getLong("patient_id");
                String patientFirstName = rs.getString("patient_first_name");
                String patientLastName = rs.getString("patient_last_name");
                String patientBirthDate = rs.getString("patient_birth_date");
                String patientPassword = rs.getString("patient_login_password");


                //String firstName, String lastName, long userID, String loginPass, Date dateOfBirth
                Patient patient = new Patient(patientFirstName, patientLastName, patient_id, patientPassword, patientBirthDate);

                //long visitId, String visitType, String date, String time, Patient patient, Dentist dentist
                Visit visit = new Visit(visitId, visitType, visitDate, visitTime, patient, dentist, procedureInformation);

                // the bill stuff

                //long billId, Visit visit, String date, String time, double subTotal, double total, double insuranceDeduction
                long billId = rs.getLong("bill_id");
                String billDate = rs.getString("bill_date");
                String billTime = rs.getString("bill_time");
                double subTotal = rs.getDouble("sub_total");
                double total = rs.getDouble("total");
                double insuranceDeduction = rs.getDouble("insurance_deduction");

                bill = new PatientBill(billId, visit, billDate, billTime, subTotal, total, insuranceDeduction,
                        patientFirstName, patientLastName);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bill;
    }

    public static Visit queryLastVisit() {
        String sql = """
                     SELECT visit.*, patient.*, dentist.* FROM visit
                     JOIN patient ON visit.patient_id = patient.patient_id
                     JOIN dentist ON visit.dentist_id = dentist.dentist_id
                     WHERE visit.visit_id = (SELECT MAX(visit_id) FROM visit WHERE visit_id IS NOT NULL)
                     """;
        Visit visit = null;

        try (Connection conn = connect();
             Statement stat = conn.createStatement();
             ResultSet rs = stat.executeQuery(sql)) {
            while (rs.next()) {

                // the visit stuff
                long visitId = rs.getLong("visit_id");
                String visitType = rs.getString("visit_type");
                String visitDate = rs.getString("visit_date");
                String visitTime = rs.getString("visit_time");
                String procedureInformation = rs.getString("visit_procedure_information");
                // the dentist stuff
                long dentistId = rs.getLong("dentist_id");
                String dentistFirstName = rs.getString("dentist_first_name");
                String dentistLastName = rs.getString("dentist_last_name");
                String dentistBirthDate = rs.getString("dentist_birth_date");
                List<String> allowedOperations = Arrays.stream(rs.getString("allowed_operations")
                        .split(", ")).toList();
                String specialty = rs.getString("specialty");
                String dentistPassword = rs.getString("dentist_login_password");

                Dentist dentist;
                if (specialty.toLowerCase().contains("apprentice")) {
                    dentist = new ApprenticeDentist(dentistFirstName, dentistLastName, dentistId, dentistPassword,
                            dentistBirthDate, allowedOperations, specialty);
                } else {
                    dentist = new RegularDentist(dentistFirstName, dentistLastName, dentistId, dentistPassword,
                            dentistBirthDate, allowedOperations, specialty);
                }

                // the patient stuff
                long patient_id = rs.getLong("patient_id");
                String patientFirstName = rs.getString("patient_first_name");
                String patientLastName = rs.getString("patient_last_name");
                String patientBirthDate = rs.getString("patient_birth_date");
                String patientPassword = rs.getString("patient_login_password");


                //String firstName, String lastName, long userID, String loginPass, Date dateOfBirth
                Patient patient = new Patient(patientFirstName, patientLastName, patient_id, patientPassword, patientBirthDate);

                //long visitId, String visitType, String date, String time, Patient patient, Dentist dentist
                visit = new Visit(visitId, visitType, visitDate, visitTime, patient, dentist, procedureInformation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return visit;
    }

    public static Visit queryVisitByBillId(long billId) {
        String sql = """
                     SELECT bill.bill_id visit.*, patient.*, dentist.* FROM bill
                     JOIN visit ON bill.visit_id = visit.visit_id
                     JOIN patient ON visit.patient_id = patient.patient_id
                     JOIN dentist ON visit.dentist_id = dentist.dentist_id
                     WHERE bill.bill_id = """ + billId;
        Visit visit = null;

        try (Connection conn = connect();
             Statement stat = conn.createStatement();
             ResultSet rs = stat.executeQuery(sql)) {
            while (rs.next()) {

                // the visit stuff
                long visitId = rs.getLong("visit_id");
                String visitType = rs.getString("visit_type");
                String visitDate = rs.getString("visit_date");
                String visitTime = rs.getString("visit_time");
                String procedureInformation = rs.getString("visit_procedure_information");
                // the dentist stuff
                long dentistId = rs.getLong("dentist_id");
                String dentistFirstName = rs.getString("dentist_first_name");
                String dentistLastName = rs.getString("dentist_last_name");
                String dentistBirthDate = rs.getString("dentist_birth_date");
                List<String> allowedOperations = Arrays.stream(rs.getString("allowed_operations")
                        .split(", ")).toList();
                String specialty = rs.getString("specialty");
                String dentistPassword = rs.getString("dentist_login_password");

                Dentist dentist;
                if (specialty.equalsIgnoreCase("apprentice")) {
                    dentist = new ApprenticeDentist(dentistFirstName, dentistLastName, dentistId, dentistPassword,
                            dentistBirthDate, allowedOperations, specialty);
                } else {
                    dentist = new RegularDentist(dentistFirstName, dentistLastName, dentistId, dentistPassword,
                            dentistBirthDate, allowedOperations, specialty);
                }

                // the patient stuff
                long patient_id = rs.getLong("patient_id");
                String patientFirstName = rs.getString("patient_first_name");
                String patientLastName = rs.getString("patient_last_name");
                String patientBirthDate = rs.getString("patient_birth_date");
                String patientPassword = rs.getString("patient_login_password");


                //String firstName, String lastName, long userID, String loginPass, Date dateOfBirth
                Patient patient = new Patient(patientFirstName, patientLastName, patient_id, patientPassword, patientBirthDate);

                //long visitId, String visitType, String date, String time, Patient patient, Dentist dentist
                visit = new Visit(visitId, visitType, visitDate, visitTime, patient, dentist, procedureInformation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return visit;
    }
}
