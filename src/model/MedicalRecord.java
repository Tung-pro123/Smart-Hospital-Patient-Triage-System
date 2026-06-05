package model;

import java.time.LocalDateTime;

public class MedicalRecord {
    public String recordId;
    public String patientId;
    public LocalDateTime visitDate;
    public String doctorId;
    public String diagnosis; // Chẩn đoán
    public String prescription; // Đơn thuốc

    // 2 con trỏ cốt lõi của Doubly Linked List
    public MedicalRecord next;
    public MedicalRecord prev;

    public MedicalRecord(String recordId, String patientId, String doctorId,
            String diagnosis, String prescription) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.visitDate = LocalDateTime.now(); // Tự động lấy giờ hiện tại
        this.doctorId = doctorId;
        this.diagnosis = diagnosis;
        this.prescription = prescription;

        // Khởi tạo Node độc lập, chưa liên kết nên trỏ null
        this.next = null;
        this.prev = null;
    }
}
