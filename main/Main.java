package main;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import model.Patient;
import model.Vitals;
import service.MedicalRecordService;
import service.PatientService;

public class Main {

    public static void main(String[] args) {
        // System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.out.println("=== KHOI DONG HE THONG SMART HOSPITAL TRIAGE ===\n");

        // Goi ham test chuc nang Tiep nhan, Tra cuu (BST) va Phan luong cap cuu
        // (Max-Heap)
        testPatientTriageAndLookup();

        System.out.println("\n==========================================================\n");

        // Goi ham test chuc nang Duyet ho so benh an (Doubly Linked List)
        testMedicalHistoryNavigation();

        testRecentViewsHistory();
        testDoctorAssignment();
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
     * bệnh án của từng bệnh nhân
     */
    public static void testMedicalHistoryNavigation() {
        System.out.println("--- TEST MODULE 3: LICH SU KHÁM BỆNH CỦA BỆNH NHÂN (DOUBLY LINKED LIST) ---");
        MedicalRecordService recordService = new MedicalRecordService();

        // 1. Tạo đối tượng bệnh nhân trước
        // (Giả sử Constructor có đủ các tham số như file Patient.java của bạn)
        Patient patientBN06 = new Patient("BN06", "Lê Thanh Tùng", 22, "Male",
                "0909xxxxxx", "BHYT-001", 1, new Vitals(36.5, 120, 98, 2));

        System.out.println("\n[1] BAC SI NHAP HO SO KHAM BENH CHO: " + patientBN06.fullName);

        // 2. Truyền đối tượng bệnh nhân vào hàm để nó tự thêm vào "sổ" của người đó
        recordService.addNewRecord(patientBN06, "HS01", "Kham lan 1: Dau dau nhe", "Paracetamol");
        recordService.addNewRecord(patientBN06, "HS02", "Kham lan 2: Sot cao", "Ibuprofen, Oresol");
        recordService.addNewRecord(patientBN06, "HS03", "Kham lan 3: Viem hong nang", "Khang sinh Amoxicillin");

        System.out.println("\n[2] TEST CHUC NANG LAT HO SO VE QUÁ KHỨ (PREVIOUS)...");
        // Bây giờ tất cả các hàm đều cần truyền patientBN06 vào
        recordService.viewPrevious(patientBN06); // Lùi về Lan 2
        recordService.viewPrevious(patientBN06); // Lùi về Lan 1
        recordService.viewPrevious(patientBN06); // Lùi quá đà

        System.out.println("\n[3] TEST CHUC NANG LAT HO SO TOI HIỆN TẠI (NEXT)...");
        recordService.viewNext(patientBN06); // Tiến tới Lan 2
        recordService.viewNext(patientBN06); // Tiến tới Lan 3
        recordService.viewNext(patientBN06); // Tiến quá đà
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

    /**
     * HAM TEST 3: Kiem tra Hang doi (FIFO Queue) phan cong Bac si
     */
    public static void testDoctorAssignment() {
        System.out.println("--- TEST MODULE 4: PHAN CONG BAC SI (FIFO QUEUE) ---");
        // Nho import service.DoctorService o dau file
        service.DoctorService doctorService = new service.DoctorService();

        System.out.println("\n[1] DIEM DANH BAC SI VAO CA TRUC:");
        // Them 3 bac si vao hang doi (Theo thu tu: Tuan -> Mai -> Hung)
        doctorService.addDoctorToShift("BS01", "Dr. Nguyen Tuan", "Cap Cuu Tong Hop");
        doctorService.addDoctorToShift("BS02", "Dr. Le Mai", "Ngoai Khoa");
        doctorService.addDoctorToShift("BS03", "Dr. Tran Hung", "Noi Khoa");

        System.out.println("\n[2] MO PHONG TIEP NHAN BENH NHAN VA PHAN CONG:");
        // Tao nhanh 4 benh nhan de test
        model.Vitals v = new model.Vitals(80, 120, 98, 37);
        model.Patient p1 = new model.Patient("BN01", "Benh nhan A", 30, "Nam", "0901", "BH01", 1, v);
        model.Patient p2 = new model.Patient("BN02", "Benh nhan B", 25, "Nu", "0902", "BH02", 2, v);
        model.Patient p3 = new model.Patient("BN03", "Benh nhan C", 50, "Nam", "0903", "BH03", 3, v);
        model.Patient p4 = new model.Patient("BN04", "Benh nhan D", 40, "Nu", "0904", "BH04", 4, v);

        // Phat benh nhan cho cac bac si (He thong se lay dung thu tu Tuan -> Mai ->
        // Hung)
        model.Doctor docForP1 = doctorService.assignDoctor(p1);
        model.Doctor docForP2 = doctorService.assignDoctor(p2);
        model.Doctor docForP3 = doctorService.assignDoctor(p3);

        System.out.println("\n[3] THU PHAN CONG KHI HET BAC SI RANH:");
        // Luc nay ca 3 bac si deu da duoc phan cong (hang doi rong)
        model.Doctor docForP4 = doctorService.assignDoctor(p4);

        System.out.println("\n[4] BAC SI HOAN THANH CA KHAM VA QUAY LAI HANG CHO:");
        // Bac si Tuan kham xong cho benh nhan A, quay lai vao hang doi
        doctorService.releaseDoctor(docForP1);

        // Bay gio he thong phan cong lai benh nhan D dang cho
        System.out.println("Goi lai benh nhan D vao kham:");
        docForP4 = doctorService.assignDoctor(p4);
    }
}