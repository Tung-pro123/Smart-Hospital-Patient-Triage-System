package collection;

import model.MedicalRecord;

public class DoublyLinkedList {
    private NodeDLL head;
    private NodeDLL tail;
    private NodeDLL currentView; // Con tro luu vi tri Node dang xem

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.currentView = null;
    }

    // Them ho so kham moi (O(1))
    public void appendRecord(MedicalRecord newRecord) {
        // Boc du lieu vao trong 1 NodeDLL moi
        NodeDLL newNode = new NodeDLL(newRecord);

        if (head == null) {
            head = newNode;
            tail = newNode;
            currentView = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            currentView = newNode; // Tu dong tro den ho so moi nhat
        }
    }

    // Lat ve ho so cu hon (O(1))
    public MedicalRecord goBackward() {
        if (currentView != null && currentView.prev != null) {
            currentView = currentView.prev;
            return currentView.data; // Tra ve du lieu MedicalRecord ben trong
        }
        return null;
    }

    // Lat toi ho so moi hon (O(1))
    public MedicalRecord goForward() {
        if (currentView != null && currentView.next != null) {
            currentView = currentView.next;
            return currentView.data;
        }
        return null;
    }

    // Lay ho so dang xem hien tai
    public MedicalRecord getCurrentView() {
        if (currentView != null) {
            return currentView.data;
        }
        return null;
    }
}
