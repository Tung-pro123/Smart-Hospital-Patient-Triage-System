package service;

import collection.QueueFIFO;
import model.Doctor;
import model.Patient;

public class DoctorService {
    // Hang doi chua cac bac si dang ranh
    private QueueFIFO availableDoctors;

    public DoctorService() {
        this.availableDoctors = new QueueFIFO();
    }

    /**
     * Nghiep vu 1: Them bac si vao ca truc (san sang nhan benh)
     */
    public void addDoctorToShift(String id, String name, String specialty) {
        Doctor newDoctor = new Doctor(id, name, specialty);
        availableDoctors.enqueue(newDoctor);
        System.out.println("[+] Bac si san sang nhan ca: " + name + " (Chuyen khoa: " + specialty + ")");
    }

    /**
     * Nghiep vu 2: Phan cong bac si cho benh nhan (Dequeue)
     */
    public Doctor assignDoctor(Patient patient) {
        Doctor assignedDoctor = availableDoctors.dequeue();

        if (assignedDoctor != null) {
            assignedDoctor.isAvailable = false;
            System.out.println(">> PHAN CONG: Bac si " + assignedDoctor.doctorName + " dang tiep nhan benh nhan ["
                    + patient.fullName + "]");
            return assignedDoctor;
        } else {
            System.out.println("!! He thong qua tai: Hien khong co bac si nao ranh. Benh nhan [" + patient.fullName
                    + "] vui long cho.");
            return null;
        }
    }

    /**
     * Nghiep vu 3: Bac si kham xong, quay lai hang doi de tiep nhan ca moi
     * (Enqueue)
     */
    public void releaseDoctor(Doctor doctor) {
        if (doctor != null) {
            doctor.isAvailable = true;
            availableDoctors.enqueue(doctor);
            System.out.println("[v] Bac si " + doctor.doctorName + " da kham xong va quay lai cuoi hang cho.");
        }
    }
}