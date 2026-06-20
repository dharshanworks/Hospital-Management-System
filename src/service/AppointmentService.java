package service;

import dao.AppointmentDAO;
import dao.DoctorDAO;
import dao.PatientDAO;
import model.Appointment;

import java.util.List;

public class AppointmentService {

    private AppointmentDAO appointmentDAO = new AppointmentDAO();
    private PatientDAO patientDAO = new PatientDAO();
    private DoctorDAO doctorDAO = new DoctorDAO();

    public boolean bookAppointment(Appointment appointment) {

        if (appointment.getPatientId() <= 0) {
            System.out.println("Invalid patient ID.");
            return false;
        }

        if (appointment.getDoctorId() <= 0) {
            System.out.println("Invalid doctor ID.");
            return false;
        }

        if (appointment.getAppointmentDate() == null || appointment.getAppointmentDate().trim().isEmpty()) {
            System.out.println("Appointment date cannot be empty.");
            return false;
        }

        if (patientDAO.searchPatientById(appointment.getPatientId()) == null) {
            System.out.println("Patient not found. Please enter a valid patient ID.");
            return false;
        }

        if (doctorDAO.searchDoctorById(appointment.getDoctorId()) == null) {
            System.out.println("Doctor not found. Please enter a valid doctor ID.");
            return false;
        }

        return appointmentDAO.bookAppointment(appointment);
    }

    public List<String> viewAllAppointments() {
        return appointmentDAO.viewAllAppointments();
    }

    public Appointment searchAppointmentById(int appointmentId) {

        if (appointmentId <= 0) {
            System.out.println("Invalid appointment ID.");
            return null;
        }

        return appointmentDAO.searchAppointmentById(appointmentId);
    }

    public boolean cancelAppointment(int appointmentId) {

        if (appointmentId <= 0) {
            System.out.println("Invalid appointment ID.");
            return false;
        }

        Appointment appointment = appointmentDAO.searchAppointmentById(appointmentId);

        if (appointment == null) {
            System.out.println("Appointment not found.");
            return false;
        }

        if (appointment.getStatus().equalsIgnoreCase("Cancelled")) {
            System.out.println("Appointment is already cancelled.");
            return true;
        }

        return appointmentDAO.cancelAppointment(appointmentId);
    }
}