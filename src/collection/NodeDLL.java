package collection;

import model.MedicalRecord;

public class NodeDLL {
    public MedicalRecord data; // Du lieu benh an
    public NodeDLL next; // Con tro toi node tiep theo
    public NodeDLL prev; // Con tro toi node truoc do

    public NodeDLL(MedicalRecord data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
