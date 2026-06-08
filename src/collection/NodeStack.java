package collection;

public class NodeStack {
    public String patientId; // Chi can luu ID la du de tim lai tren BST
    public NodeStack next;

    public NodeStack(String patientId) {
        this.patientId = patientId;
        this.next = null;
    }
}
