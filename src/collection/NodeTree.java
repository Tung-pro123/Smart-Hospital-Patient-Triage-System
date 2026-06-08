package collection;

import model.Patient;

public class NodeTree {
    public Patient patient;
    public NodeTree left;
    public NodeTree right;

    public NodeTree(Patient patient) {
        this.patient = patient;
        this.left = null;
        this.right = null;
    }
}
