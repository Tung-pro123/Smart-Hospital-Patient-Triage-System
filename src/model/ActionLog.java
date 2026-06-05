package model;

import java.time.LocalDateTime;

public class ActionLog {
    public String logId;
    public String actionType; // Ví dụ: "ADD_PATIENT", "UPDATE_VITALS"
    public LocalDateTime timestamp;

    // Dùng kiểu Object để có thể lưu lại bản sao (snapshot) của bất kỳ Data nào
    // trước khi bị sửa đổi
    public Object previousState;

    public ActionLog(String logId, String actionType, Object previousState) {
        this.logId = logId;
        this.actionType = actionType;
        this.timestamp = LocalDateTime.now();
        this.previousState = previousState;
    }
}
