package model;

public class Bill {

    private int billId;
    private int patientId;
    private double amount;
    private String paymentStatus;

    public Bill() {
    }

    public Bill(int patientId, double amount, String paymentStatus) {
        this.patientId = patientId;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
    }

    public Bill(int billId, int patientId, double amount, String paymentStatus) {
        this.billId = billId;
        this.patientId = patientId;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "Bill ID          : " + billId +
                "\nPatient ID       : " + patientId +
                "\nAmount           : Rs." + amount +
                "\nPayment Status   : " + paymentStatus;
    }
}