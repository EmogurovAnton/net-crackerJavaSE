package myList;

public class MyList<E> {
    private Node head;
    private int size;

    public MyList() {
        this.head = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public int size() {
        return this.size;
    }

    private void checkIndex(int index) {
        String message = "Индекс: " + index + ", " + "Размер: " + this.size;

        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException(message);
        }
    }

    private void addFirst(E data) {
        Node newNode = new Node(data);

        if (isEmpty()) {
            this.head = newNode;
        } else {
            newNode.next = this.head;
        }
        this.head = newNode;
        this.size++;
    }

    public void add(E data) {
        Node newNode = new Node(data);
        Node current = head;

        if (isEmpty()) {
            this.head = newNode;
        } else {
            while (current != null) {
                if (current.next == null) {
                    current.next = newNode;
                    break;
                }
                current = current.next;
            }
        }
        this.size++;
    }

    private Node getNodeByNumber(int index) {
        checkIndex(index);
        Node resultNode = null;
        Node current = this.head;
        int counter = 0;

        while (current != null) {
            if (counter == index) {
                resultNode = current;
                break;
            }
            current = current.next;
            counter++;
        }

        return resultNode;
    }

    public E get(int index) {
        return getNodeByNumber(index).data;
    }

    public void add(int index, E data) {
        checkIndex(index);

        Node newNode = new Node(data);
        Node current = null;
        Node prev = null;

        if (index == 0) {
            addFirst(data);
        } else if (index == this.size) {
            add(data);
        } else {
            current = getNodeByNumber(index);
            prev = getNodeByNumber(index - 1);
            newNode.next = current;
            prev.next = newNode;
            this.size++;
        }
    }

    private void removeFirst() {
        this.head = this.head.next;
    }

    private void removeLast() {
        Node currentNode = this.head;

        while (currentNode.next.next != null) {
            currentNode = currentNode.next;
        }

        currentNode.next = null;
    }

    public void remove(int index) {
        checkIndex(index);

        if (index == 0) {
            removeFirst();
        } else if (index == this.size - 1) {
            removeLast();
        } else {
            Node current = getNodeByNumber(index);
            Node prev = getNodeByNumber(index - 1);
            prev.next = current.next;
        }
        this.size--;
    }


    public void print() {
        Node currentNode = head;

        while (currentNode != null) {
            System.out.println(currentNode.data);
            currentNode = currentNode.next;
        }
    }


    private class Node {
        private Node next;
        private E data;

        public Node(E data) {
            this.data = data;
        }
    }
}

