package myList;

import java.util.NoSuchElementException;

public class MyLinkedList<E> {
    private Node head;
    private Node tail;
    private int size;


    public MyLinkedList() {
        this.head = null;
        this.tail = null;
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
        Node currentNode = new Node(data);

        if (isEmpty()) {
            this.tail = currentNode;
        } else {
            this.head.prev = currentNode;
        }

        currentNode.next = head;
        this.head = currentNode;
        this.size++;
    }

    public void add(E data) {
        Node currentNode = new Node(data);

        if (isEmpty()) {
            this.head = currentNode;
        } else {
            this.tail.next = currentNode;
        }

        currentNode.prev = tail;
        this.tail = currentNode;
        this.size++;
    }

    public void add(int index, E data) {
        checkIndex(index);
        Node newNode = new Node(data);
        Node current = head;
        int counter = 0;

        if (index == 0) {
            addFirst(data);
        } else if (index == this.size) {
            add(data);
        } else {
            while (current != null && counter != index) {
                current = current.next;
                counter++;
            }

            current.prev.next = newNode;
            newNode.prev = current.prev;
            current.prev = newNode;
            newNode.next = current;
            this.size++;
        }
    }

    private void removeFirst() {
        if (this.head.next == null) {
            this.tail = null;
        } else {
            this.head.next.prev = null;
        }
        this.head = this.head.next;
    }

    private void removeLast() {
        if (this.head.next == null) {
            this.head = null;
        } else {
            this.tail.prev.next = null;
        }
        this.tail = this.tail.prev;
    }

    public void remove(int index) {
        checkIndex(index);
        Node current = head;
        int counter = 0;

        if (index == 0) {
            removeFirst();
        } else if (index == this.size - 1) {
            removeLast();
        } else {
            while (current != null && counter != index) {
                current = current.next;
                counter++;
            }
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }

        this.size--;
    }


    private Node getExpectedElement(int index) {
        checkIndex(index);
        Node current = head;
        int counter = 0;

        while (current != null && counter != index) {
            current = current.next;
            counter++;
        }
        return current;
    }

    public E get(int index) {
        return getExpectedElement(index).data;
    }


    public void print() {
        Node currentNode = head;

        while (currentNode != null) {
            System.out.println(currentNode.data);
            currentNode = currentNode.next;
        }
    }


    private class Node {
        private Node prev;
        private Node next;
        private E data;

        public Node(E data) {
            this.data = data;

        }

    }
}
