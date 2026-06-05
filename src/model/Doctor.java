package model;

public class Doctor {
    public String doctorId;
    public String doctorName;
    public String specialty;
    public boolean isAvailable; // true: Đang rảnh chờ nhận bệnh, false: Đang bận

    public Doctor(String doctorId, String doctorName, String specialty) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.specialty = specialty;
        this.isAvailable = true; // Mặc định bác sĩ rảnh khi mới tạo
    }
}
