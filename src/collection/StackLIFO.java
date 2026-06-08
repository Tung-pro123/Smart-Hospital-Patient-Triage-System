package collection;

public class StackLIFO {
    private NodeStack top;

    public StackLIFO() {
        this.top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    // Thuat toan: Them vao dinh Stack (O(1))
    public void push(String patientId) {
        NodeStack newNode = new NodeStack(patientId);
        newNode.next = top;
        top = newNode;
    }

    // Thuat toan: Rut khoi dinh Stack (O(1))
    public String pop() {
        if (isEmpty()) {
            return null;
        }
        String id = top.patientId;
        top = top.next;
        return id;
    }

    // Thuat toan: Xem phan tu tren dinh nhung khong rut ra (O(1))
    public String peek() {
        if (isEmpty()) {
            return null;
        }
        return top.patientId;
    }
}
