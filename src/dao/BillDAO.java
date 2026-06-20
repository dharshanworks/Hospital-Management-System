package dao;

import model.Bill;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {

    public boolean generateBill(Bill bill) {
        String query = "INSERT INTO bills(patient_id, amount, payment_status) VALUES (?, ?, ?)";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, bill.getPatientId());
            preparedStatement.setDouble(2, bill.getAmount());
            preparedStatement.setString(3, bill.getPaymentStatus());

            int rowsInserted = preparedStatement.executeUpdate();

            return rowsInserted > 0;

        } catch (Exception e) {
            System.out.println("Error while generating bill: " + e.getMessage());
        }

        return false;
    }

    public List<String> viewAllBills() {
        List<String> billList = new ArrayList<>();

        String query = "SELECT b.bill_id, p.name AS patient_name, p.disease, b.amount, b.payment_status " +
                "FROM bills b " +
                "JOIN patients p ON b.patient_id = p.patient_id";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {

                String billDetails = "Bill ID          : " + resultSet.getInt("bill_id") +
                        "\nPatient Name     : " + resultSet.getString("patient_name") +
                        "\nDisease          : " + resultSet.getString("disease") +
                        "\nAmount           : Rs." + resultSet.getDouble("amount") +
                        "\nPayment Status   : " + resultSet.getString("payment_status");

                billList.add(billDetails);
            }

        } catch (Exception e) {
            System.out.println("Error while viewing bills: " + e.getMessage());
        }

        return billList;
    }

    public Bill searchBillById(int billId) {
        String query = "SELECT * FROM bills WHERE bill_id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, billId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    Bill bill = new Bill();

                    bill.setBillId(resultSet.getInt("bill_id"));
                    bill.setPatientId(resultSet.getInt("patient_id"));
                    bill.setAmount(resultSet.getDouble("amount"));
                    bill.setPaymentStatus(resultSet.getString("payment_status"));

                    return bill;
                }
            }

        } catch (Exception e) {
            System.out.println("Error while searching bill: " + e.getMessage());
        }

        return null;
    }

    public boolean updatePaymentStatus(int billId, String paymentStatus) {
        String query = "UPDATE bills SET payment_status = ? WHERE bill_id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, paymentStatus);
            preparedStatement.setInt(2, billId);

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;

        } catch (Exception e) {
            System.out.println("Error while updating payment status: " + e.getMessage());
        }

        return false;
    }
}