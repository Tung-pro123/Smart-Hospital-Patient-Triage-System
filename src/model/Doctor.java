package model;

public class Doctor {
    public String doctorId;
    public String doctorName;
    public String specialty; // Chuyen khoa
    public boolean isAvailable; // Trang thai ranh/ban

    public Doctor(String doctorId, String doctorName, String specialty) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.specialty = specialty;
        this.isAvailable = true; // Mac dinh khi tao la dang ranh
    }
}