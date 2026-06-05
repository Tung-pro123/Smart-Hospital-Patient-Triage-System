package model;

public class Patient {
    public String patientId;
    public String fullName;
    public int age;
    public String gender;
    public String contactNumber;
    public String healthInsuranceCode;

    // Móc nối với sinh hiệu của bệnh nhân
    public Vitals currentVitals;

    // Constructor cơ bản
    public Patient(String patientId, String fullName, int age, String gender,
            String contactNumber, String healthInsuranceCode, Vitals currentVitals) {
        this.patientId = patientId;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.healthInsuranceCode = healthInsuranceCode;
        this.currentVitals = currentVitals;
    }
}
