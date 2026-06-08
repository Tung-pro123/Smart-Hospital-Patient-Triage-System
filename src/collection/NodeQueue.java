package collection;

import model.Doctor;

public class NodeQueue {
    public Doctor data;
    public NodeQueue next;

    public NodeQueue(Doctor data) {
        this.data = data;
        this.next = null;
    }
}
