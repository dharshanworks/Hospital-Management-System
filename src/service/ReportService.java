package service;

import dao.ReportDAO;

public class ReportService {

    private ReportDAO reportDAO = new ReportDAO();

    public int getTotalPatients() {
        return reportDAO.getTotalPatients();
    }

    public int getTotalDoctors() {
        return reportDAO.getTotalDoctors();
    }

    public int getTotalAppointments() {
        return reportDAO.getTotalAppointments();
    }

    public int getTotalBills() {
        return reportDAO.getTotalBills();
    }

    public double getTotalRevenueCollected() {
        return reportDAO.getTotalRevenueCollected();
    }

    public int getPaidBillsCount() {
        return reportDAO.getPaidBillsCount();
    }

    public int getPendingBillsCount() {
        return reportDAO.getPendingBillsCount();
    }

    public int getBookedAppointmentsCount() {
        return reportDAO.getBookedAppointmentsCount();
    }

    public int getCancelledAppointmentsCount() {
        return reportDAO.getCancelledAppointmentsCount();
    }
}