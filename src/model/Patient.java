package model;

public class Patient {
    public String patientId;
    public String fullName;
    public int age;
    public String gender;
    public String contactNumber;
    public String healthInsuranceCode;
    public int ticketNumber;
    // Móc nối với sinh hiệu của bệnh nhân
    public Vitals currentVitals;

    // Constructor cơ bản
    public Patient(String patientId, String fullName, int age, String gender,
            String contactNumber, String healthInsuranceCode, int ticketNumber, Vitals currentVitals) {
        this.patientId = patientId;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.healthInsuranceCode = healthInsuranceCode;
        this.ticketNumber = ticketNumber;
        this.currentVitals = currentVitals;
    }

    // --- CÁC HÀM BỔ SUNG ĐỂ KẾT NỐI VỚI CẤU TRÚC DỮ LIỆU --- //

    /**
     * Lấy mức độ ưu tiên từ đối tượng Vitals.
     * Hàm này được MaxHeap sử dụng để sắp xếp bệnh nhân.
     */
    public int getTriageLevel() {
        if (this.currentVitals != null) {
            return this.currentVitals.triageLevel;
        }
        return 0; // Mức mặc định thấp nhất nếu bệnh nhân chưa được đo sinh hiệu
    }

    /**
     * Cập nhật lại mức độ ưu tiên sau khi cây quyết định (BST) tính toán xong.
     */
    public void setTriageLevel(int newLevel) {
        if (this.currentVitals != null) {
            this.currentVitals.triageLevel = newLevel;
        }
    }
}
