package service;

import collection.DoublyLinkedList;
import model.MedicalRecord;

public class MedicalRecordService {
    // Quan ly lich su kham cua benh nhan bang Linked List doi tu code chay
    private DoublyLinkedList historyList;

    public MedicalRecordService() {
        this.historyList = new DoublyLinkedList();
    }

    /**
     * Nghiep vu 1: Bac si them mot ho so benh an moi sau khi kham xong
     */
    public void addNewRecord(String recordId, String patientId, String diagnosis, String prescription) {
        MedicalRecord newRecord = new MedicalRecord(recordId, patientId, diagnosis, prescription);
        historyList.appendRecord(newRecord);
        System.out.println("[+] Da them ho so benh an moi: " + diagnosis);
    }

    /**
     * Nghiep vu 2: In chi tiet ho so dang duoc chon de xem
     */
    public void printCurrentRecord() {
        MedicalRecord current = historyList.getCurrentView();
        if (current != null) {
            System.out.println("\n--- THONG TIN BENH AN CHI TIET ---");
            System.out.println("Ma BA: " + current.recordId);
            System.out.println("Ma BN: " + current.patientId);
            System.out.println("Chan doan: " + current.diagnosis);
            System.out.println("Don thuoc: " + current.prescription);
            System.out.println("Thoi gian: " + current.visitDate);
            System.out.println("----------------------------------");
        } else {
            System.out.println("Chua co ho so benh an nao de xem.");
        }
    }

    /**
     * Nghiep vu 3: Lat ve ho so cu hon (Previous) - Do phuc tap O(1)
     */
    public void viewPrevious() {
        MedicalRecord prev = historyList.goBackward();
        if (prev != null) {
            System.out.println("<< Lat ve ho so cu hon: " + prev.diagnosis);
            printCurrentRecord();
        } else {
            System.out.println("!! Day la ho so cu nhat roi, khong the lui lai qua khu nua.");
        }
    }

    /**
     * Nghiep vu 4: Lat toi ho so moi hon (Next) - Do phuc tap O(1)
     */
    public void viewNext() {
        MedicalRecord next = historyList.goForward();
        if (next != null) {
            System.out.println(">> Lat toi ho so moi hon: " + next.diagnosis);
            printCurrentRecord();
        } else {
            System.out.println("!! Day la ho so moi nhat roi, khong the tien den tuong lai nua.");
        }
    }
}