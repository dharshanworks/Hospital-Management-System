package service;

import dao.PatientDAO;
import model.Patient;

import java.util.List;

public class PatientService {

    private PatientDAO patientDAO = new PatientDAO();

    public boolean addPatient(Patient patient) {

        if (patient.getName() == null || patient.getName().trim().isEmpty()) {
            System.out.println("Patient name cannot be empty.");
            return false;
        }

        if (patient.getAge() <= 0) {
            System.out.println("Age must be greater than 0.");
            return false;
        }

        if (patient.getPhone() == null || patient.getPhone().trim().isEmpty()) {
            System.out.println("Phone number cannot be empty.");
            return false;
        }

        return patientDAO.addPatient(patient);
    }

    public List<Patient> viewAllPatients() {
        return patientDAO.viewAllPatients();
    }

    public Patient searchPatientById(int patientId) {

        if (patientId <= 0) {
            System.out.println("Invalid patient ID.");
            return null;
        }

        return patientDAO.searchPatientById(patientId);
    }

    public boolean updatePatient(Patient patient) {

        if (patient.getPatientId() <= 0) {
            System.out.println("Invalid patient ID.");
            return false;
        }

        if (patient.getName() == null || patient.getName().trim().isEmpty()) {
            System.out.println("Patient name cannot be empty.");
            return false;
        }

        if (patient.getAge() <= 0) {
            System.out.println("Age must be greater than 0.");
            return false;
        }

        return patientDAO.updatePatient(patient);
    }

    public boolean deletePatient(int patientId) {

        if (patientId <= 0) {
            System.out.println("Invalid patient ID.");
            return false;
        }

        return patientDAO.deletePatient(patientId);
    }
}