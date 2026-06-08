package collection;

import model.Patient;

public class BST {
    private NodeTree root;

    public BST() {
        this.root = null;
    }

    // Thêm bệnh nhân vào cây (O(log n))
    public void insert(Patient patient) {
        root = insertRec(root, patient);
    }

    private NodeTree insertRec(NodeTree root, Patient patient) {
        if (root == null) {
            root = new NodeTree(patient);
            return root;
        }
        // So sánh chuỗi patientId để quyết định rẽ trái hay phải
        if (patient.patientId.compareTo(root.patient.patientId) < 0) {
            root.left = insertRec(root.left, patient);
        } else if (patient.patientId.compareTo(root.patient.patientId) > 0) {
            root.right = insertRec(root.right, patient);
        }
        return root;
    }

    // Tìm kiếm bệnh nhân theo ID (O(log n))
    public Patient searchById(String id) {
        NodeTree resultNode = searchRec(root, id);
        if (resultNode != null) {
            return resultNode.patient;
        }
        return null; // Không tìm thấy
    }

    private NodeTree searchRec(NodeTree root, String id) {
        if (root == null || root.patient.patientId.equals(id)) {
            return root;
        }
        if (id.compareTo(root.patient.patientId) < 0) {
            return searchRec(root.left, id);
        }
        return searchRec(root.right, id);
    }
}
