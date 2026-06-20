package service;

import dao.BillDAO;
import dao.PatientDAO;
import model.Bill;

import java.util.List;

public class BillService {

    private BillDAO billDAO = new BillDAO();
    private PatientDAO patientDAO = new PatientDAO();

    public boolean generateBill(Bill bill) {

        if (bill.getPatientId() <= 0) {
            System.out.println("Invalid patient ID.");
            return false;
        }

        if (bill.getAmount() <= 0) {
            System.out.println("Amount must be greater than 0.");
            return false;
        }

        if (patientDAO.searchPatientById(bill.getPatientId()) == null) {
            System.out.println("Patient not found. Please enter a valid patient ID.");
            return false;
        }

        return billDAO.generateBill(bill);
    }

    public List<String> viewAllBills() {
        return billDAO.viewAllBills();
    }

    public Bill searchBillById(int billId) {

        if (billId <= 0) {
            System.out.println("Invalid bill ID.");
            return null;
        }

        return billDAO.searchBillById(billId);
    }

    public boolean updatePaymentStatus(int billId, String paymentStatus) {

        if (billId <= 0) {
            System.out.println("Invalid bill ID.");
            return false;
        }

        if (paymentStatus == null || paymentStatus.trim().isEmpty()) {
            System.out.println("Payment status cannot be empty.");
            return false;
        }

        Bill bill = billDAO.searchBillById(billId);

        if (bill == null) {
            System.out.println("Bill not found.");
            return false;
        }

        return billDAO.updatePaymentStatus(billId, paymentStatus);
    }
}