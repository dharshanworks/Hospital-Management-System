package dao;

import model.Doctor;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    public boolean addDoctor(Doctor doctor) {
        String query = "INSERT INTO doctors(name, specialization, phone, experience) VALUES (?, ?, ?, ?)";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, doctor.getName());
            preparedStatement.setString(2, doctor.getSpecialization());
            preparedStatement.setString(3, doctor.getPhone());
            preparedStatement.setInt(4, doctor.getExperience());

            int rowsInserted = preparedStatement.executeUpdate();

            return rowsInserted > 0;

        } catch (Exception e) {
            System.out.println("Error while adding doctor: " + e.getMessage());
        }

        return false;
    }

    public List<Doctor> viewAllDoctors() {
        List<Doctor> doctorList = new ArrayList<>();

        String query = "SELECT * FROM doctors";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Doctor doctor = new Doctor();

                doctor.setDoctorId(resultSet.getInt("doctor_id"));
                doctor.setName(resultSet.getString("name"));
                doctor.setSpecialization(resultSet.getString("specialization"));
                doctor.setPhone(resultSet.getString("phone"));
                doctor.setExperience(resultSet.getInt("experience"));

                doctorList.add(doctor);
            }

        } catch (Exception e) {
            System.out.println("Error while viewing doctors: " + e.getMessage());
        }

        return doctorList;
    }

    public List<Doctor> searchDoctorBySpecialization(String specialization) {
        List<Doctor> doctorList = new ArrayList<>();

        String query = "SELECT * FROM doctors WHERE specialization LIKE ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + specialization + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Doctor doctor = new Doctor();

                    doctor.setDoctorId(resultSet.getInt("doctor_id"));
                    doctor.setName(resultSet.getString("name"));
                    doctor.setSpecialization(resultSet.getString("specialization"));
                    doctor.setPhone(resultSet.getString("phone"));
                    doctor.setExperience(resultSet.getInt("experience"));

                    doctorList.add(doctor);
                }
            }

        } catch (Exception e) {
            System.out.println("Error while searching doctor: " + e.getMessage());
        }

        return doctorList;
    }

    public Doctor searchDoctorById(int doctorId) {
        String query = "SELECT * FROM doctors WHERE doctor_id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, doctorId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Doctor doctor = new Doctor();

                    doctor.setDoctorId(resultSet.getInt("doctor_id"));
                    doctor.setName(resultSet.getString("name"));
                    doctor.setSpecialization(resultSet.getString("specialization"));
                    doctor.setPhone(resultSet.getString("phone"));
                    doctor.setExperience(resultSet.getInt("experience"));

                    return doctor;
                }
            }

        } catch (Exception e) {
            System.out.println("Error while searching doctor by ID: " + e.getMessage());
        }

        return null;
    }

    public boolean updateDoctor(Doctor doctor) {
        String query = "UPDATE doctors SET name = ?, specialization = ?, phone = ?, experience = ? WHERE doctor_id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, doctor.getName());
            preparedStatement.setString(2, doctor.getSpecialization());
            preparedStatement.setString(3, doctor.getPhone());
            preparedStatement.setInt(4, doctor.getExperience());
            preparedStatement.setInt(5, doctor.getDoctorId());

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;

        } catch (Exception e) {
            System.out.println("Error while updating doctor: " + e.getMessage());
        }

        return false;
    }

    public boolean deleteDoctor(int doctorId) {

        String checkActiveAppointmentsQuery = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND UPPER(status) != 'CANCELLED'";

        String deleteCancelledAppointmentsQuery = "DELETE FROM appointments WHERE doctor_id = ? AND UPPER(status) = 'CANCELLED'";

        String deleteUserQuery = "DELETE FROM users WHERE doctor_id = ?";

        String deleteDoctorQuery = "DELETE FROM doctors WHERE doctor_id = ?";

        try (Connection connection = DBConnection.getConnection()) {

            connection.setAutoCommit(false);

            try (PreparedStatement checkStatement = connection.prepareStatement(checkActiveAppointmentsQuery)) {

                checkStatement.setInt(1, doctorId);

                try (ResultSet resultSet = checkStatement.executeQuery()) {

                    if (resultSet.next()) {
                        int activeAppointmentCount = resultSet.getInt(1);

                        if (activeAppointmentCount > 0) {
                            System.out.println("Cannot delete doctor. This doctor has active appointments.");
                            System.out.println("Please cancel the active appointments first.");
                            connection.rollback();
                            return false;
                        }
                    }
                }
            }

            try (PreparedStatement deleteCancelledAppointmentsStatement = connection
                    .prepareStatement(deleteCancelledAppointmentsQuery)) {

                deleteCancelledAppointmentsStatement.setInt(1, doctorId);
                deleteCancelledAppointmentsStatement.executeUpdate();
            }

            try (PreparedStatement deleteUserStatement = connection.prepareStatement(deleteUserQuery)) {

                deleteUserStatement.setInt(1, doctorId);
                deleteUserStatement.executeUpdate();
            }

            try (PreparedStatement deleteDoctorStatement = connection.prepareStatement(deleteDoctorQuery)) {

                deleteDoctorStatement.setInt(1, doctorId);

                int rowsDeleted = deleteDoctorStatement.executeUpdate();

                if (rowsDeleted > 0) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                    return false;
                }
            }

        } catch (Exception e) {
            System.out.println("Error while deleting doctor: " + e.getMessage());
        }

        return false;
    }
}