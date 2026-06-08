package model;

import java.time.LocalDateTime;

public class MedicalRecord {
    public String recordId;
    public String patientId;
    public String diagnosis;
    public String prescription;
    public LocalDateTime visitDate;

    public MedicalRecord(String recordId, String patientId, String diagnosis, String prescription) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.visitDate = LocalDateTime.now();
    }
}