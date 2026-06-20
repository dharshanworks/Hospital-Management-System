package dao;

import model.Appointment;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    public boolean bookAppointment(Appointment appointment) {
        String query = "INSERT INTO appointments(patient_id, doctor_id, appointment_date, status) VALUES (?, ?, ?, ?)";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, appointment.getPatientId());
            preparedStatement.setInt(2, appointment.getDoctorId());
            preparedStatement.setString(3, appointment.getAppointmentDate());
            preparedStatement.setString(4, appointment.getStatus());

            int rowsInserted = preparedStatement.executeUpdate();

            return rowsInserted > 0;

        } catch (Exception e) {
            System.out.println("Error while booking appointment: " + e.getMessage());
        }

        return false;
    }

    public List<String> viewAllAppointments() {
        List<String> appointmentList = new ArrayList<>();

        String query = "SELECT a.appointment_id, p.name AS patient_name, d.name AS doctor_name, " +
                "d.specialization, a.appointment_date, a.status " +
                "FROM appointments a " +
                "JOIN patients p ON a.patient_id = p.patient_id " +
                "JOIN doctors d ON a.doctor_id = d.doctor_id";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {

                String appointmentDetails =
                        "Appointment ID   : " + resultSet.getInt("appointment_id") +
                        "\nPatient Name     : " + resultSet.getString("patient_name") +
                        "\nDoctor Name      : " + resultSet.getString("doctor_name") +
                        "\nSpecialization   : " + resultSet.getString("specialization") +
                        "\nAppointment Date : " + resultSet.getString("appointment_date") +
                        "\nStatus           : " + resultSet.getString("status");

                appointmentList.add(appointmentDetails);
            }

        } catch (Exception e) {
            System.out.println("Error while viewing appointments: " + e.getMessage());
        }

        return appointmentList;
    }

    public Appointment searchAppointmentById(int appointmentId) {
        String query = "SELECT * FROM appointments WHERE appointment_id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, appointmentId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    Appointment appointment = new Appointment();

                    appointment.setAppointmentId(resultSet.getInt("appointment_id"));
                    appointment.setPatientId(resultSet.getInt("patient_id"));
                    appointment.setDoctorId(resultSet.getInt("doctor_id"));
                    appointment.setAppointmentDate(resultSet.getString("appointment_date"));
                    appointment.setStatus(resultSet.getString("status"));

                    return appointment;
                }
            }

        } catch (Exception e) {
            System.out.println("Error while searching appointment: " + e.getMessage());
        }

        return null;
    }

    public boolean cancelAppointment(int appointmentId) {
        String query = "UPDATE appointments SET status = ? WHERE appointment_id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, "Cancelled");
            preparedStatement.setInt(2, appointmentId);

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;

        } catch (Exception e) {
            System.out.println("Error while cancelling appointment: " + e.getMessage());
        }

        return false;
    }
}