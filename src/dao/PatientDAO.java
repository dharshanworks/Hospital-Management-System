package dao;

import model.Patient;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    public boolean addPatient(Patient patient) {
        String query = "INSERT INTO patients(name, age, gender, phone, address, disease, medical_history) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setInt(2, patient.getAge());
            preparedStatement.setString(3, patient.getGender());
            preparedStatement.setString(4, patient.getPhone());
            preparedStatement.setString(5, patient.getAddress());
            preparedStatement.setString(6, patient.getDisease());
            preparedStatement.setString(7, patient.getMedicalHistory());

            int rowsInserted = preparedStatement.executeUpdate();

            return rowsInserted > 0;

        } catch (Exception e) {
            System.out.println("Error while adding patient: " + e.getMessage());
        }

        return false;
    }

    public List<Patient> viewAllPatients() {
        List<Patient> patientList = new ArrayList<>();

        String query = "SELECT * FROM patients";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                Patient patient = new Patient();

                patient.setPatientId(resultSet.getInt("patient_id"));
                patient.setName(resultSet.getString("name"));
                patient.setAge(resultSet.getInt("age"));
                patient.setGender(resultSet.getString("gender"));
                patient.setPhone(resultSet.getString("phone"));
                patient.setAddress(resultSet.getString("address"));
                patient.setDisease(resultSet.getString("disease"));
                patient.updateMedicalHistory(resultSet.getString("medical_history"));

                patientList.add(patient);
            }

        } catch (Exception e) {
            System.out.println("Error while viewing patients: " + e.getMessage());
        }

        return patientList;
    }

    public Patient searchPatientById(int patientId) {
        String query = "SELECT * FROM patients WHERE patient_id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, patientId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    Patient patient = new Patient();

                    patient.setPatientId(resultSet.getInt("patient_id"));
                    patient.setName(resultSet.getString("name"));
                    patient.setAge(resultSet.getInt("age"));
                    patient.setGender(resultSet.getString("gender"));
                    patient.setPhone(resultSet.getString("phone"));
                    patient.setAddress(resultSet.getString("address"));
                    patient.setDisease(resultSet.getString("disease"));
                    patient.updateMedicalHistory(resultSet.getString("medical_history"));

                    return patient;
                }
            }

        } catch (Exception e) {
            System.out.println("Error while searching patient: " + e.getMessage());
        }

        return null;
    }

    public boolean updatePatient(Patient patient) {
        String query = "UPDATE patients SET name = ?, age = ?, gender = ?, phone = ?, address = ?, disease = ?, medical_history = ? WHERE patient_id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setInt(2, patient.getAge());
            preparedStatement.setString(3, patient.getGender());
            preparedStatement.setString(4, patient.getPhone());
            preparedStatement.setString(5, patient.getAddress());
            preparedStatement.setString(6, patient.getDisease());
            preparedStatement.setString(7, patient.getMedicalHistory());
            preparedStatement.setInt(8, patient.getPatientId());

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;

        } catch (Exception e) {
            System.out.println("Error while updating patient: " + e.getMessage());
        }

        return false;
    }

    public boolean deletePatient(int patientId) {
        String query = "DELETE FROM patients WHERE patient_id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, patientId);

            int rowsDeleted = preparedStatement.executeUpdate();

            return rowsDeleted > 0;

        } catch (Exception e) {
            System.out.println("Error while deleting patient: " + e.getMessage());
        }

        return false;
    }
}