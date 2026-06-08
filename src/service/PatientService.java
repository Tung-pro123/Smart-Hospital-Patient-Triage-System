package service;

import collection.MaxHeap;
import collection.StackLIFO;
import collection.BST;
import model.Patient;
import model.Vitals;

public class PatientService {
    // Các cấu trúc dữ liệu cốt lõi
    private MaxHeap triageQueue;
    private BST patientDatabase;
    private StackLIFO viewHistory; // Them bien Stack nay vao
    // Biến đếm toàn cục để cấp số thứ tự tự động (ticketNumber)
    private int globalTicketCounter;

    // Khởi tạo Service với sức chứa mặc định của bệnh viện
    public PatientService(int hospitalCapacity) {
        this.triageQueue = new MaxHeap(hospitalCapacity);
        this.patientDatabase = new BST();
        this.globalTicketCounter = 1; // Bắt đầu bốc từ số 1
        this.viewHistory = new StackLIFO();
    }

    /**
     * Nghiệp vụ 1: Tiếp nhận bệnh nhân mới
     */
    public void registerNewPatient(String id, String name, int age, String gender,
            String contact, String insurance, Vitals vitals) {

        // 1. Cấp số thứ tự hiện tại và tăng biến đếm lên cho người sau
        int currentTicket = globalTicketCounter++;

        // 2. Tạo đối tượng bệnh nhân
        Patient newPatient = new Patient(id, name, age, gender, contact, insurance, currentTicket, vitals);

        // 3. Đưa vào Cây tìm kiếm (để lưu trữ hồ sơ và tra cứu sau này)
        patientDatabase.insert(newPatient);

        // 4. Đưa vào Hàng đợi ưu tiên (để chờ bác sĩ gọi vào khám)
        triageQueue.enqueue(newPatient);

        System.out.println("Da tiep nhan benh nhan:  " + name + " | So thu tu: " + currentTicket + " | Muc do: "
                + newPatient.getTriageLevel());
    }

    /**
     * Nghiệp vụ 2: Bác sĩ gọi bệnh nhân nguy kịch nhất vào khám
     */
    public Patient callNextPatientForTreatment() {
        Patient nextPatient = triageQueue.extractMax();

        if (nextPatient == null) {
            // sua thanh tieng viet khong dau
            System.out.println("Hien tai khong co benh nhan nao dang cho trong phong cap cuu.");
            return null;
        }

        System.out.println(">> DANG GOI VAO PHONG CAP CUU " + nextPatient.fullName + " (Muc do: "
                + nextPatient.getTriageLevel() + " Ve so: " + nextPatient.ticketNumber + ")");
        return nextPatient;
    }

    /**
     * Nghiệp vụ 3: Tra cứu nhanh thông tin hành chính bằng Mã bệnh nhân (O(log n))
     */
    public Patient getPatientInfo(String patientId) {
        Patient foundPatient = patientDatabase.searchById(patientId);

        if (foundPatient == null) {
            System.out.println("Không tìm thấy bệnh nhân với ID: " + patientId);
        }

        return foundPatient;
    }

    /**
     * Kiểm tra xem phòng cấp cứu còn ai đang chờ không
     */
    public boolean hasWaitingPatients() {
        return !triageQueue.isEmpty();
    }

    /**
     * Nghiep vu 4: Bac si mo xem ho so (Giong viec click vao 1 trang web)
     */
    public void viewPatientProfile(String patientId) {
        // Tim benh nhan trong cay BST (O(log n))
        model.Patient p = patientDatabase.searchById(patientId);

        if (p != null) {
            // Neu stack rong hoac ho so dang mo khac voi ho so tren dinh, thi Push vao
            // Stack
            if (viewHistory.isEmpty() || !viewHistory.peek().equals(patientId)) {
                viewHistory.push(patientId);
            }
            System.out.println(">> Dang mo xem ho so: " + p.fullName + " (ID: " + p.patientId + ")");
        } else {
            System.out.println("!! Khong tim thay benh nhan voi ID: " + patientId);
        }
    }

    /**
     * Nghiep vu 5: Bam nut "Back" de quay lai ho so truoc do (O(1))
     */
    public void goBack() {
        if (viewHistory.isEmpty()) {
            System.out.println("!! Lich su xem dang trong, khong the quay lai.");
            return;
        }

        // Buoc 1: Vut bo ho so dang xem hien tai ra khoi Stack
        String currentView = viewHistory.pop();

        // Buoc 2: Nhin xuong ho so tiep theo trong Stack (truoc do)
        String previousView = viewHistory.peek();

        if (previousView != null) {
            // Dung cay BST load lai thong tin nguoi do ra màn hinh
            model.Patient p = patientDatabase.searchById(previousView);
            System.out.println("<< Quay lai ho so truoc: " + p.fullName + " (ID: " + p.patientId + ")");
        } else {
            System.out.println("!! Day la ho so dau tien ban xem, khong the lui nua.");
            // Nhet lai vao Stack de khong bi mat trang thai hien tai
            viewHistory.push(currentView);
        }
    }
}