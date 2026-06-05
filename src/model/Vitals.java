package model;

public class Vitals {
    public double heartRate; // Nhịp tim
    public double bloodPressure; // Huyết áp
    public double spO2; // Nồng độ oxy
    public double temperature; // Nhiệt độ

    // Mức độ ưu tiên (ví dụ: 1 là nguy kịch nhất, 5 là bình thường)
    public int triageLevel;

    public Vitals(double heartRate, double bloodPressure, double spO2, double temperature) {
        this.heartRate = heartRate;
        this.bloodPressure = bloodPressure;
        this.spO2 = spO2;
        this.temperature = temperature;
        // Triage level sẽ được tính toán tự động qua một hàm khác, khởi tạo tạm bằng 0
        this.triageLevel = 0;
    }
}
