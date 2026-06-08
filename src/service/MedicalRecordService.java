package service;

import model.MedicalRecord;
import model.Patient;

public class MedicalRecordService {

    // Không cần khai báo DoublyLinkedList ở đây nữa, vì nó đã nằm trong Patient

    /**
     * Nghiep vu 1: Bac si them benh an vao ĐÚNG cuốn sổ của bệnh nhân đó
     */
    public void addNewRecord(Patient patient, String recordId, String diagnosis, String prescription) {
        // Tạo bệnh án mới
        MedicalRecord newRecord = new MedicalRecord(recordId, patient.patientId, diagnosis, prescription);

        // Mở cuốn sổ (DoublyLinkedList) CỦA RIÊNG BỆNH NHÂN NÀY và móc nối vào đuôi
        patient.medicalHistory.appendRecord(newRecord);

        System.out.println("[+] Da them ho so benh an moi cho [" + patient.fullName + "]: " + diagnosis);
    }

    /**
     * Nghiep vu 2: In chi tiet ho so dang duoc chon de xem cua 1 benh nhan
     */
    public void printCurrentRecord(Patient patient) {
        MedicalRecord current = patient.medicalHistory.getCurrentView();
        if (current != null) {
            System.out.println("\n--- THONG TIN BENH AN (" + patient.fullName + ") ---");
            System.out.println("Ma BA: " + current.recordId);
            System.out.println("Chan doan: " + current.diagnosis);
            System.out.println("Don thuoc: " + current.prescription);
            System.out.println("----------------------------------");
        } else {
            System.out.println("Chua co ho so benh an nao de xem.");
        }
    }

    /**
     * Nghiep vu 3: Lat ve ho so cu hon (Previous) tren so cua rieng benh nhan
     */
    public void viewPrevious(Patient patient) {
        MedicalRecord prev = patient.medicalHistory.goBackward();
        if (prev != null) {
            System.out.println("<< Lat ve ho so cu hon cua [" + patient.fullName + "]");
            printCurrentRecord(patient);
        } else {
            System.out.println("!! Day la ho so cu nhat cua [" + patient.fullName + "], khong the lui lai.");
        }
    }

    /**
     * Nghiep vu 4: Lat toi ho so moi hon (Next)
     */
    public void viewNext(Patient patient) {
        MedicalRecord next = patient.medicalHistory.goForward();
        if (next != null) {
            System.out.println(">> Lat toi ho so moi hon cua [" + patient.fullName + "]");
            printCurrentRecord(patient);
        } else {
            System.out.println("!! Day la ho so moi nhat cua [" + patient.fullName + "].");
        }
    }
}