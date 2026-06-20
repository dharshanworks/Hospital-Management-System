package service;

import dao.DoctorDAO;
import model.Doctor;

import java.util.List;

public class DoctorService {

    private DoctorDAO doctorDAO = new DoctorDAO();

    public boolean addDoctor(Doctor doctor) {

        if (doctor.getName() == null || doctor.getName().trim().isEmpty()) {
            System.out.println("Doctor name cannot be empty.");
            return false;
        }

        if (doctor.getSpecialization() == null || doctor.getSpecialization().trim().isEmpty()) {
            System.out.println("Specialization cannot be empty.");
            return false;
        }

        if (doctor.getExperience() < 0) {
            System.out.println("Experience cannot be negative.");
            return false;
        }

        return doctorDAO.addDoctor(doctor);
    }

    public List<Doctor> viewAllDoctors() {
        return doctorDAO.viewAllDoctors();
    }

    public List<Doctor> searchDoctorBySpecialization(String specialization) {

        if (specialization == null || specialization.trim().isEmpty()) {
            System.out.println("Specialization cannot be empty.");
            return null;
        }

        return doctorDAO.searchDoctorBySpecialization(specialization);
    }

    public Doctor searchDoctorById(int doctorId) {

        if (doctorId <= 0) {
            System.out.println("Invalid doctor ID.");
            return null;
        }

        return doctorDAO.searchDoctorById(doctorId);
    }

    public boolean updateDoctor(Doctor doctor) {

        if (doctor.getDoctorId() <= 0) {
            System.out.println("Invalid doctor ID.");
            return false;
        }

        if (doctor.getName() == null || doctor.getName().trim().isEmpty()) {
            System.out.println("Doctor name cannot be empty.");
            return false;
        }

        if (doctor.getSpecialization() == null || doctor.getSpecialization().trim().isEmpty()) {
            System.out.println("Specialization cannot be empty.");
            return false;
        }

        return doctorDAO.updateDoctor(doctor);
    }

    public boolean deleteDoctor(int doctorId) {

        if (doctorId <= 0) {
            System.out.println("Invalid doctor ID.");
            return false;
        }

        return doctorDAO.deleteDoctor(doctorId);
    }
}