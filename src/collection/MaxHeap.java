package collection;

import model.Patient;

public class MaxHeap {
    private Patient[] heapArray;
    private int currentSize;
    private int capacity;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.currentSize = 0;
        this.heapArray = new Patient[this.capacity];
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize == capacity;
    }

    public void enqueue(Patient patient) {
        if (isFull()) {
            System.out.println("Triage Queue is full!");
            return;
        }

        heapArray[currentSize] = patient;
        siftUp(currentSize);
        currentSize++;
    }

    public Patient extractMax() {
        if (isEmpty()) {
            return null;
        }

        Patient mostCritical = heapArray[0];
        heapArray[0] = heapArray[currentSize - 1];
        heapArray[currentSize - 1] = null;
        currentSize--;

        siftDown(0);
        return mostCritical;
    }

    // Hàm phụ trợ gộp 2 tiêu chí so sánh (Độ khẩn cấp và Ai đến trước)
    private boolean isHigherPriority(Patient p1, Patient p2) {
        // Ưu tiên 1: Cấp cứu nặng hơn
        if (p1.getTriageLevel() > p2.getTriageLevel()) {
            return true;
        }
        // Ưu tiên 2: Cùng mức độ, nhưng bốc số trước (số nhỏ hơn) thì lên trước
        else if (p1.getTriageLevel() == p2.getTriageLevel()) {
            return p1.ticketNumber < p2.ticketNumber;
        }
        return false;
    }

    private void siftUp(int index) {
        int parentIndex = (index - 1) / 2;
        // Dùng isHigherPriority thay cho so sánh cũ
        while (index > 0 && isHigherPriority(heapArray[index], heapArray[parentIndex])) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    private void siftDown(int index) {
        while (true) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int largest = index;

            // Dùng isHigherPriority thay cho so sánh cũ
            if (leftChild < currentSize && isHigherPriority(heapArray[leftChild], heapArray[largest])) {
                largest = leftChild;
            }
            if (rightChild < currentSize && isHigherPriority(heapArray[rightChild], heapArray[largest])) {
                largest = rightChild;
            }

            if (largest != index) {
                swap(index, largest);
                index = largest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        Patient temp = heapArray[i];
        heapArray[i] = heapArray[j];
        heapArray[j] = temp;
    }
}