package dao;

import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReportDAO {

    private int getCount(String query) {

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("Error while fetching count: " + e.getMessage());
        }

        return 0;
    }

    private double getDoubleValue(String query) {

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }

        } catch (Exception e) {
            System.out.println("Error while fetching amount: " + e.getMessage());
        }

        return 0.0;
    }

    public int getTotalPatients() {
        String query = "SELECT COUNT(*) FROM patients";
        return getCount(query);
    }

    public int getTotalDoctors() {
        String query = "SELECT COUNT(*) FROM doctors";
        return getCount(query);
    }

    public int getTotalAppointments() {
        String query = "SELECT COUNT(*) FROM appointments";
        return getCount(query);
    }

    public int getTotalBills() {
        String query = "SELECT COUNT(*) FROM bills";
        return getCount(query);
    }

    public double getTotalRevenueCollected() {
        String query = "SELECT IFNULL(SUM(amount), 0) FROM bills WHERE UPPER(payment_status) = 'PAID'";
        return getDoubleValue(query);
    }

    public int getPaidBillsCount() {
        String query = "SELECT COUNT(*) FROM bills WHERE UPPER(payment_status) = 'PAID'";
        return getCount(query);
    }

    public int getPendingBillsCount() {
        String query = "SELECT COUNT(*) FROM bills WHERE UPPER(payment_status) = 'PENDING'";
        return getCount(query);
    }

    public int getBookedAppointmentsCount() {
        String query = "SELECT COUNT(*) FROM appointments WHERE UPPER(status) = 'BOOKED'";
        return getCount(query);
    }

    public int getCancelledAppointmentsCount() {
        String query = "SELECT COUNT(*) FROM appointments WHERE UPPER(status) = 'CANCELLED'";
        return getCount(query);
    }
}