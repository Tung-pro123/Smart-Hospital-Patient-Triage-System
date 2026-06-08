package main;

import model.Vitals;
import service.MedicalRecordService;
import service.PatientService;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== KHOI DONG HE THONG SMART HOSPITAL TRIAGE ===\n");

        // Goi ham test chuc nang Tiep nhan, Tra cuu (BST) va Phan luong cap cuu
        // (Max-Heap)
        testPatientTriageAndLookup();

        System.out.println("\n==========================================================\n");

        // Goi ham test chuc nang Duyet ho so benh an (Doubly Linked List)
        testMedicalHistoryNavigation();
    }

    /**
     * HÀM TEST 1: Kiểm tra Hàng đợi ưu tiên (Max-Heap) và Cây tìm kiếm (BST)
     */
    public static void testPatientTriageAndLookup() {
        System.out.println("--- TEST MODULE 1 & 2: QUAN LY BENH NHAN & PHAN LUONG ---");
        PatientService service = new PatientService(100);

        System.out.println("\n[1] TIEP NHAN BENH NHAN...");
        Vitals v1 = new Vitals(85, 120, 98, 37);
        v1.triageLevel = 2;
        service.registerNewPatient("BN01", "Le Van Nhe", 30, "Nam", "0901", "BH01", v1);

        Vitals v2 = new Vitals(140, 180, 88, 39);
        v2.triageLevel = 5;
        service.registerNewPatient("BN02", "Tran Thi Cap Cuu", 65, "Nu", "0902", "BH02", v2);

        Vitals v3 = new Vitals(80, 110, 99, 36.5);
        v3.triageLevel = 1;
        service.registerNewPatient("BN03", "Pham Kham Suc Khoe", 22, "Nam", "0903", "BH03", v3);

        Vitals v4 = new Vitals(110, 150, 94, 38.5);
        v4.triageLevel = 4;
        service.registerNewPatient("BN04", "Vo Trong Thuong", 45, "Nam", "0904", "BH04", v4);

        Vitals v5 = new Vitals(95, 135, 96, 37.5);
        v5.triageLevel = 3;
        service.registerNewPatient("BN05", "Nguyen Dau Bung", 28, "Nu", "0905", "BH05", v5);

        Vitals v6 = new Vitals(135, 175, 85, 40);
        v6.triageLevel = 5;
        service.registerNewPatient("BN06", "Dang Dot Quy", 70, "Nam", "0906", "BH06", v6);

        Vitals v7 = new Vitals(75, 115, 98, 36.8);
        v7.triageLevel = 1;
        service.registerNewPatient("BN07", "Ly Lay Thuoc", 55, "Nu", "0907", "BH07", v7);

        Vitals v8 = new Vitals(105, 145, 92, 38);
        v8.triageLevel = 4;
        service.registerNewPatient("BN08", "Bui Gay Xuong", 35, "Nam", "0908", "BH08", v8);

        Vitals v9 = new Vitals(88, 125, 97, 37.2);
        v9.triageLevel = 2;
        service.registerNewPatient("BN09", "Ho Cam Cum", 15, "Nu", "0909", "BH09", v9);

        Vitals v10 = new Vitals(100, 140, 95, 38);
        v10.triageLevel = 3;
        service.registerNewPatient("BN10", "Ngo Kho Tho", 50, "Nam", "0910", "BH10", v10);

        System.out.println("\n[2] TEST TRA CUU HO SO (O(log n) bang BST)...");
        System.out.println("Tim benh nhan BN06:");
        model.Patient p = service.getPatientInfo("BN06");
        if (p != null) {
            System.out.println("=> Da tim thay: " + p.fullName + " | Tuoi: " + p.age + " | SDT: " + p.contactNumber);
        }

        System.out.println("\nTim benh nhan BN99 (Khong ton tai):");
        service.getPatientInfo("BN99");

        System.out.println("\n[3] TEST GOI KHAM BENH (O(log n) bang Max-Heap)...");
        System.out.println("* Luu y: Level 5 goi truoc. Cung Level thi ai lay so truoc goi truoc.");

        int order = 1;
        while (service.hasWaitingPatients()) {
            System.out.print("Luot " + order + ": ");
            service.callNextPatientForTreatment();
            order++;
        }
    }

    /**
     * HÀM TEST 2: Kiểm tra Danh sách liên kết đôi (Doubly Linked List) cho Hồ sơ
     * bệnh án
     */
    public static void testMedicalHistoryNavigation() {
        System.out.println("--- TEST MODULE 3: LICH SU KHÁM BỆNH (DOUBLY LINKED LIST) ---");
        MedicalRecordService recordService = new MedicalRecordService();

        System.out.println("\n[1] BAC SI NHAP HO SO KHAM BENH...");
        // Bac si nhap 3 lan kham benh (Tu cu den moi) cho benh nhan BN06
        recordService.addNewRecord("HS01", "BN06", "Kham lan 1: Dau dau nhe", "Paracetamol");
        recordService.addNewRecord("HS02", "BN06", "Kham lan 2: Sot cao", "Ibuprofen, Oresol");
        recordService.addNewRecord("HS03", "BN06", "Kham lan 3: Viem hong nang", "Khang sinh Amoxicillin");

        System.out.println("\n[2] TEST CHUC NANG LAT HO SO VE QUÁ KHỨ (PREVIOUS)...");
        System.out.println("* Hien tai dang xem ho so moi nhat (Lan 3).");
        recordService.viewPrevious(); // Lùi về Lan 2
        recordService.viewPrevious(); // Lùi về Lan 1
        recordService.viewPrevious(); // Lùi quá đà -> Sẽ báo lỗi chạm đáy (hồ sơ cũ nhất)

        System.out.println("\n[3] TEST CHUC NANG LAT HO SO TOI HIỆN TẠI (NEXT)...");
        recordService.viewNext(); // Tiến tới Lan 2
        recordService.viewNext(); // Tiến tới Lan 3
        recordService.viewNext(); // Tiến quá đà -> Sẽ báo lỗi chạm đỉnh (hồ sơ mới nhất)
    }

    /**
     * HAM TEST 4: Kiem tra chuc nang Nut Back cua he thong bang Stack LIFO
     */
    public static void testRecentViewsHistory() {
        System.out.println("\n--- TEST MODULE 5: LICH SU XEM HO SO (STACK LIFO) ---");
        PatientService service = new PatientService(10);

        // Khoi tao nhanh 3 benh nhan
        model.Vitals v = new model.Vitals(80, 120, 98, 37);
        service.registerNewPatient("BN01", "Nguyen Van A", 30, "Nam", "0901", "BH01", v);
        service.registerNewPatient("BN02", "Tran Thi B", 25, "Nu", "0902", "BH02", v);
        service.registerNewPatient("BN03", "Le Van C", 40, "Nam", "0903", "BH03", v);

        System.out.println("\n[1] BAC SI XEM LAN LUOT 3 HO SO (Tu A -> B -> C):");
        service.viewPatientProfile("BN01");
        service.viewPatientProfile("BN02");
        service.viewPatientProfile("BN03");

        System.out.println("\n[2] BAC SI BAM NUT QUAY LAI (BACK) LIEN TUC:");
        service.goBack(); // Dang o C -> Se quay ve B
        service.goBack(); // Dang o B -> Se quay ve A
        service.goBack(); // Dang o A -> Se bao loi vi day la trang dau tien
    }
}