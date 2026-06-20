package model;

public class Patient {

    private int patientId;
    private String name;
    private int age;
    private String gender;
    private String phone;
    private String address;
    private String disease;
    private String medicalHistory;

    public Patient() {
    }

    public Patient(String name, int age, String gender, String phone, String address, String disease, String medicalHistory) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.disease = disease;
        this.medicalHistory = medicalHistory;
    }

    public Patient(int patientId, String name, int age, String gender, String phone, String address, String disease, String medicalHistory) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.disease = disease;
        this.medicalHistory = medicalHistory;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void updateMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    @Override
    public String toString() {
        return "Patient ID       : " + patientId +
                "\nName             : " + name +
                "\nAge              : " + age +
                "\nGender           : " + gender +
                "\nPhone            : " + phone +
                "\nAddress          : " + address +
                "\nDisease          : " + disease +
                "\nMedical History  : " + medicalHistory;
    }
}