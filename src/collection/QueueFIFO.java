package collection;

import model.Doctor;

public class QueueFIFO {
    private NodeQueue head;
    private NodeQueue tail;

    public QueueFIFO() {
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    // Thuật toán: Thêm bác sĩ vào cuối hàng đợi (O(1))
    public void enqueue(Doctor doctor) {
        NodeQueue newNode = new NodeQueue(doctor);
        if (tail == null) {
            head = newNode;
            tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    // Thuật toán: Lấy bác sĩ đứng đầu hàng đợi ra (O(1))
    public Doctor dequeue() {
        if (isEmpty()) {
            return null;
        }
        Doctor doctor = head.data;
        head = head.next;

        if (head == null) {
            tail = null; // Neu hang doi rong, reset tail ve null
        }
        return doctor;
    }
}