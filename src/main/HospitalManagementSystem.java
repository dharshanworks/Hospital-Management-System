package main;

import model.Appointment;
import model.Bill;
import model.Doctor;
import model.Patient;
import model.User;

import service.AppointmentService;
import service.AuthService;
import service.BillService;
import service.DoctorService;
import service.PatientService;
import service.ReportService;

import java.util.List;
import java.util.Scanner;

public class HospitalManagementSystem {

    private static Scanner scanner = new Scanner(System.in);

    private static PatientService patientService = new PatientService();
    private static DoctorService doctorService = new DoctorService();
    private static AppointmentService appointmentService = new AppointmentService();
    private static BillService billService = new BillService();
    private static AuthService authService = new AuthService();
    private static ReportService reportService = new ReportService();

    public static void main(String[] args) {

        User loggedInUser = login();

        if (loggedInUser == null) {
            System.out.println("Login failed. Exiting application.");
            return;
        }

        if (loggedInUser.getRole().equalsIgnoreCase("ADMIN")) {
            adminMenu();
        } else if (loggedInUser.getRole().equalsIgnoreCase("DOCTOR")) {
            doctorDashboard(loggedInUser);
        } else {
            System.out.println("Invalid role. Access denied.");
        }
    }

    private static User login() {

        System.out.println("\n========== HOSPITAL MANAGEMENT SYSTEM LOGIN ==========");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = authService.login(username, password);

        if (user != null) {
            System.out.println("\nLogin successful.");
            System.out.println("Welcome, " + user.getUsername() + "!");
            System.out.println("Role: " + user.getRole());
        } else {
            System.out.println("Invalid username or password.");
        }

        return user;
    }

    private static void adminMenu() {

        int choice;

        do {
            System.out.println("\n========== ADMIN DASHBOARD ==========");
            System.out.println("1. Patient Management");
            System.out.println("2. Doctor Management");
            System.out.println("3. Appointment Management");
            System.out.println("4. Billing System");
            System.out.println("5. Reports");
            System.out.println("6. Logout / Exit");
            System.out.print("Enter your choice: ");

            choice = readInt();

            switch (choice) {
                case 1:
                    patientManagementMenu();
                    break;

                case 2:
                    doctorManagementMenu();
                    break;

                case 3:
                    appointmentManagementMenu();
                    break;

                case 4:
                    billingManagementMenu();
                    break;

                case 5:
                    reportsManagementMenu();
                    break;

                case 6:
                    System.out.println("Logged out successfully.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 6);
    }

    private static void doctorDashboard(User loggedInUser) {

        int choice;

        do {
            System.out.println("\n========== DOCTOR DASHBOARD ==========");
            System.out.println("1. View All Appointments");
            System.out.println("2. Search Patient by ID");
            System.out.println("3. View My Doctor Details");
            System.out.println("4. Logout / Exit");
            System.out.print("Enter your choice: ");

            choice = readInt();

            switch (choice) {
                case 1:
                    viewAllAppointments();
                    break;

                case 2:
                    searchPatientById();
                    break;

                case 3:
                    viewLoggedInDoctorDetails(loggedInUser);
                    break;

                case 4:
                    System.out.println("Logged out successfully.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 4);
    }

    private static void viewLoggedInDoctorDetails(User loggedInUser) {

        if (loggedInUser.getDoctorId() <= 0) {
            System.out.println("No doctor profile linked with this account.");
            return;
        }

        Doctor doctor = doctorService.searchDoctorById(loggedInUser.getDoctorId());

        if (doctor == null) {
            System.out.println("Doctor details not found.");
        } else {
            System.out.println("\n----- My Doctor Details -----");
            System.out.println(doctor);
        }
    }

    private static void patientManagementMenu() {

        int choice;

        do {
            System.out.println("\n========== PATIENT MANAGEMENT ==========");
            System.out.println("1. Add Patient");
            System.out.println("2. View All Patients");
            System.out.println("3. Search Patient by ID");
            System.out.println("4. Update Patient");
            System.out.println("5. Delete Patient");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");

            choice = readInt();

            switch (choice) {
                case 1:
                    addPatient();
                    break;

                case 2:
                    viewAllPatients();
                    break;

                case 3:
                    searchPatientById();
                    break;

                case 4:
                    updatePatient();
                    break;

                case 5:
                    deletePatient();
                    break;

                case 6:
                    System.out.println("Returning to main menu...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 6);
    }

    private static void doctorManagementMenu() {

        int choice;

        do {
            System.out.println("\n========== DOCTOR MANAGEMENT ==========");
            System.out.println("1. Add Doctor");
            System.out.println("2. View All Doctors");
            System.out.println("3. Search Doctor by Specialization");
            System.out.println("4. Update Doctor");
            System.out.println("5. Delete Doctor");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");

            choice = readInt();

            switch (choice) {
                case 1:
                    addDoctor();
                    break;

                case 2:
                    viewAllDoctors();
                    break;

                case 3:
                    searchDoctorBySpecialization();
                    break;

                case 4:
                    updateDoctor();
                    break;

                case 5:
                    deleteDoctor();
                    break;

                case 6:
                    System.out.println("Returning to main menu...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 6);
    }

    private static void appointmentManagementMenu() {

        int choice;

        do {
            System.out.println("\n========== APPOINTMENT MANAGEMENT ==========");
            System.out.println("1. Book Appointment");
            System.out.println("2. View All Appointments");
            System.out.println("3. Cancel Appointment");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");

            choice = readInt();

            switch (choice) {
                case 1:
                    bookAppointment();
                    break;

                case 2:
                    viewAllAppointments();
                    break;

                case 3:
                    cancelAppointment();
                    break;

                case 4:
                    System.out.println("Returning to main menu...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 4);
    }

    private static void billingManagementMenu() {

        int choice;

        do {
            System.out.println("\n========== BILLING SYSTEM ==========");
            System.out.println("1. Generate Bill");
            System.out.println("2. View All Bills");
            System.out.println("3. Update Payment Status");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");

            choice = readInt();

            switch (choice) {
                case 1:
                    generateBill();
                    break;

                case 2:
                    viewAllBills();
                    break;

                case 3:
                    updatePaymentStatus();
                    break;

                case 4:
                    System.out.println("Returning to main menu...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 4);
    }

    private static void addPatient() {

        System.out.println("\n----- Add Patient -----");

        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();

        System.out.print("Enter patient age: ");
        int age = readInt();

        System.out.print("Enter gender: ");
        String gender = scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        System.out.print("Enter disease: ");
        String disease = scanner.nextLine();

        System.out.print("Enter medical history: ");
        String medicalHistory = scanner.nextLine();

        Patient patient = new Patient(name, age, gender, phone, address, disease, medicalHistory);

        boolean result = patientService.addPatient(patient);

        if (result) {
            System.out.println("Patient added successfully.");
        } else {
            System.out.println("Failed to add patient.");
        }
    }

    private static void viewAllPatients() {

        System.out.println("\n----- All Patients -----");

        List<Patient> patients = patientService.viewAllPatients();

        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }

        for (Patient patient : patients) {
            System.out.println("--------------------------------------");
            System.out.println(patient);
        }
    }

    private static void searchPatientById() {

        System.out.println("\n----- Search Patient By ID -----");

        System.out.print("Enter patient ID: ");
        int patientId = readInt();

        Patient patient = patientService.searchPatientById(patientId);

        if (patient != null) {
            System.out.println("--------------------------------------");
            System.out.println(patient);
        } else {
            System.out.println("Patient not found.");
        }
    }

    private static void updatePatient() {

        System.out.println("\n----- Update Patient -----");

        System.out.print("Enter patient ID to update: ");
        int patientId = readInt();

        Patient existingPatient = patientService.searchPatientById(patientId);

        if (existingPatient == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.println("\nCurrent Patient Details:");
        System.out.println(existingPatient);

        System.out.println("\nEnter New Details:");

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new age: ");
        int age = readInt();

        System.out.print("Enter new gender: ");
        String gender = scanner.nextLine();

        System.out.print("Enter new phone number: ");
        String phone = scanner.nextLine();

        System.out.print("Enter new address: ");
        String address = scanner.nextLine();

        System.out.print("Enter new disease: ");
        String disease = scanner.nextLine();

        System.out.print("Enter new medical history: ");
        String medicalHistory = scanner.nextLine();

        Patient updatedPatient = new Patient(patientId, name, age, gender, phone, address, disease, medicalHistory);

        boolean result = patientService.updatePatient(updatedPatient);

        if (result) {
            System.out.println("Patient updated successfully.");
        } else {
            System.out.println("Failed to update patient.");
        }
    }

    private static void deletePatient() {

        System.out.println("\n----- Delete Patient -----");

        System.out.print("Enter patient ID to delete: ");
        int patientId = readInt();

        Patient patient = patientService.searchPatientById(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.println("\nPatient Details:");
        System.out.println(patient);

        System.out.print("\nAre you sure you want to delete this patient? yes/no: ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            boolean result = patientService.deletePatient(patientId);

            if (result) {
                System.out.println("Patient deleted successfully.");
            } else {
                System.out.println("Failed to delete patient.");
            }
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    private static void addDoctor() {

        System.out.println("\n----- Add Doctor -----");

        System.out.print("Enter doctor name: ");
        String name = scanner.nextLine();

        System.out.print("Enter specialization: ");
        String specialization = scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        System.out.print("Enter experience in years: ");
        int experience = readInt();

        Doctor doctor = new Doctor(name, specialization, phone, experience);

        boolean result = doctorService.addDoctor(doctor);

        if (result) {
            System.out.println("Doctor added successfully.");
        } else {
            System.out.println("Failed to add doctor.");
        }
    }

    private static void viewAllDoctors() {

        System.out.println("\n----- All Doctors -----");

        List<Doctor> doctors = doctorService.viewAllDoctors();

        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
            return;
        }

        for (Doctor doctor : doctors) {
            System.out.println("--------------------------------------");
            System.out.println(doctor);
        }
    }

    private static void searchDoctorBySpecialization() {

        System.out.println("\n----- Search Doctor By Specialization -----");

        System.out.print("Enter specialization: ");
        String specialization = scanner.nextLine();

        List<Doctor> doctors = doctorService.searchDoctorBySpecialization(specialization);

        if (doctors == null || doctors.isEmpty()) {
            System.out.println("No doctors found for this specialization.");
            return;
        }

        for (Doctor doctor : doctors) {
            System.out.println("--------------------------------------");
            System.out.println(doctor);
        }
    }

    private static void updateDoctor() {

        System.out.println("\n----- Update Doctor -----");

        System.out.print("Enter doctor ID to update: ");
        int doctorId = readInt();

        Doctor existingDoctor = doctorService.searchDoctorById(doctorId);

        if (existingDoctor == null) {
            System.out.println("Doctor not found.");
            return;
        }

        System.out.println("\nCurrent Doctor Details:");
        System.out.println(existingDoctor);

        System.out.println("\nEnter New Details:");

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new specialization: ");
        String specialization = scanner.nextLine();

        System.out.print("Enter new phone number: ");
        String phone = scanner.nextLine();

        System.out.print("Enter new experience in years: ");
        int experience = readInt();

        Doctor updatedDoctor = new Doctor(doctorId, name, specialization, phone, experience);

        boolean result = doctorService.updateDoctor(updatedDoctor);

        if (result) {
            System.out.println("Doctor updated successfully.");
        } else {
            System.out.println("Failed to update doctor.");
        }
    }

    private static void deleteDoctor() {

        System.out.println("\n----- Delete Doctor -----");

        System.out.print("Enter doctor ID to delete: ");
        int doctorId = readInt();

        Doctor doctor = doctorService.searchDoctorById(doctorId);

        if (doctor == null) {
            System.out.println("Doctor not found.");
            return;
        }

        System.out.println("\nDoctor Details:");
        System.out.println(doctor);

        System.out.print("\nAre you sure you want to delete this doctor? yes/no: ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            boolean result = doctorService.deleteDoctor(doctorId);

            if (result) {
                System.out.println("Doctor deleted successfully.");
            } else {
                System.out.println("Failed to delete doctor.");
            }
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    private static void bookAppointment() {

        System.out.println("\n----- Book Appointment -----");

        System.out.println("Available Patients:");
        viewAllPatients();

        System.out.println("\nAvailable Doctors:");
        viewAllDoctors();

        System.out.print("\nEnter patient ID: ");
        int patientId = readInt();

        System.out.print("Enter doctor ID: ");
        int doctorId = readInt();

        System.out.print("Enter appointment date: ");
        String appointmentDate = scanner.nextLine();

        Appointment appointment = new Appointment(patientId, doctorId, appointmentDate, "Booked");

        boolean result = appointmentService.bookAppointment(appointment);

        if (result) {
            System.out.println("Appointment booked successfully.");
        } else {
            System.out.println("Failed to book appointment.");
        }
    }

    private static void viewAllAppointments() {

        System.out.println("\n----- All Appointments -----");

        List<String> appointments = appointmentService.viewAllAppointments();

        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }

        for (String appointment : appointments) {
            System.out.println("--------------------------------------");
            System.out.println(appointment);
        }
    }

    private static void cancelAppointment() {

        System.out.println("\n----- Cancel Appointment -----");

        viewAllAppointments();

        System.out.print("\nEnter appointment ID to cancel: ");
        int appointmentId = readInt();

        Appointment appointment = appointmentService.searchAppointmentById(appointmentId);

        if (appointment == null) {
            System.out.println("Appointment not found.");
            return;
        }

        System.out.println("\nAppointment Details:");
        System.out.println(appointment);

        System.out.print("\nAre you sure you want to cancel this appointment? yes/no: ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            boolean result = appointmentService.cancelAppointment(appointmentId);

            if (result) {
                System.out.println("Appointment cancelled successfully.");
            } else {
                System.out.println("Failed to cancel appointment.");
            }
        } else {
            System.out.println("Cancellation stopped.");
        }
    }

    private static void generateBill() {

        System.out.println("\n----- Generate Bill -----");

        System.out.println("Available Patients:");
        viewAllPatients();

        System.out.print("\nEnter patient ID: ");
        int patientId = readInt();

        System.out.print("Enter bill amount: ");
        double amount = readDouble();

        Bill bill = new Bill(patientId, amount, "Pending");

        boolean result = billService.generateBill(bill);

        if (result) {
            System.out.println("Bill generated successfully.");
        } else {
            System.out.println("Failed to generate bill.");
        }
    }

    private static void viewAllBills() {

        System.out.println("\n----- Billing History -----");

        List<String> bills = billService.viewAllBills();

        if (bills.isEmpty()) {
            System.out.println("No bills found.");
            return;
        }

        for (String bill : bills) {
            System.out.println("--------------------------------------");
            System.out.println(bill);
        }
    }

    private static void updatePaymentStatus() {

        System.out.println("\n----- Update Payment Status -----");

        viewAllBills();

        System.out.print("\nEnter bill ID: ");
        int billId = readInt();

        Bill bill = billService.searchBillById(billId);

        if (bill == null) {
            System.out.println("Bill not found.");
            return;
        }

        System.out.println("\nCurrent Bill Details:");
        System.out.println(bill);

        System.out.println("\nChoose Payment Status:");
        System.out.println("1. Paid");
        System.out.println("2. Pending");
        System.out.print("Enter your choice: ");

        int choice = readInt();

        String paymentStatus;

        if (choice == 1) {
            paymentStatus = "Paid";
        } else if (choice == 2) {
            paymentStatus = "Pending";
        } else {
            System.out.println("Invalid payment status choice.");
            return;
        }

        boolean result = billService.updatePaymentStatus(billId, paymentStatus);

        if (result) {
            System.out.println("Payment status updated successfully.");
        } else {
            System.out.println("Failed to update payment status.");
        }
    }

    private static double readDouble() {

        while (true) {
            try {
                double number = Double.parseDouble(scanner.nextLine());
                return number;
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid amount: ");
            }
        }
    }
    private static void reportsManagementMenu() {

    int choice;

    do {
        System.out.println("\n========== REPORTS DASHBOARD ==========");
        System.out.println("1. View Hospital Summary");
        System.out.println("2. View Billing Summary");
        System.out.println("3. View Appointment Summary");
        System.out.println("4. Back to Admin Dashboard");
        System.out.print("Enter your choice: ");

        choice = readInt();

        switch (choice) {
            case 1:
                viewHospitalSummary();
                break;

            case 2:
                viewBillingSummary();
                break;

            case 3:
                viewAppointmentSummary();
                break;

            case 4:
                System.out.println("Returning to admin dashboard...");
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
        }

    } while (choice != 4);
}

private static void viewHospitalSummary() {

    System.out.println("\n========== HOSPITAL SUMMARY ==========");

    System.out.println("Total Patients      : " + reportService.getTotalPatients());
    System.out.println("Total Doctors       : " + reportService.getTotalDoctors());
    System.out.println("Total Appointments  : " + reportService.getTotalAppointments());
    System.out.println("Total Bills         : " + reportService.getTotalBills());
}

private static void viewBillingSummary() {

    System.out.println("\n========== BILLING SUMMARY ==========");

    System.out.println("Total Revenue Collected : Rs." + reportService.getTotalRevenueCollected());
    System.out.println("Paid Bills              : " + reportService.getPaidBillsCount());
    System.out.println("Pending Bills           : " + reportService.getPendingBillsCount());
}

private static void viewAppointmentSummary() {

    System.out.println("\n========== APPOINTMENT SUMMARY ==========");

    System.out.println("Booked Appointments    : " + reportService.getBookedAppointmentsCount());
    System.out.println("Cancelled Appointments : " + reportService.getCancelledAppointmentsCount());
}

    private static int readInt() {

        while (true) {
            try {
                int number = Integer.parseInt(scanner.nextLine());
                return number;
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }
}