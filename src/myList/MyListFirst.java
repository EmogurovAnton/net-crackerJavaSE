package myList;

import java.util.NoSuchElementException;

public class MyListFirst<E> {
    private Node head; //Указатель на первый элемент
    private int size; //Размер листа


    public MyListFirst() {
        initHead();
        this.size = 0;
    }

    public MyListFirst(int size) {
        initHead();
        this.size = size;
    }


    private void initHead() {
        this.head = new Node(null, null);
        this.head.next = this.head;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Индекс: " + index + " " + "Размер: " + this.size);
        }
    }

    public int size() {
        return this.size;
    }

    private Node getNodeByNumber(int index) {
        checkIndex(index);
        Node resultNode = null;
        int count = 0;

        for (Node current = this.head.next; current != this.head; current = current.next) {
            if (count++ == index) {
                resultNode = current;
                break;
            }
        }
        return resultNode;
    }

    private Node getLastNode() {
        Node last = this.head;
        for (Node pointer = this.head.next; pointer != this.head; pointer = pointer.next) {
            last = pointer;
        }
        return last;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public E get(int index) {
        return getNodeByNumber(index).data;
    }

    public void add(E data) {
        if (data != null) {
            getLastNode().next = new Node(this.head, data);
            this.size++;
        }
    }

    public void add(int index, E data) {
        if (data != null) {
            if (index == 0) {
                this.head.next = new Node(this.head.next, data);
                this.size++;
            } else if (index == this.size) {
                getLastNode().next = new Node(this.head, data);
                this.size++;
            } else {
                Node node = getNodeByNumber(index - 1);
                if (node != null) {
                    node.next = new Node(node.next, data);
                    this.size++;
                }
            }
        }
    }

    private E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Элемент не найден");
        }
        E oldElement = this.head.next.data;
        this.head.next = this.head.next.next;
        this.size--;
        return oldElement;
    }

    private E removeLast() {
        E oldElement;
        if (isEmpty()) {
            throw new NoSuchElementException("Элемент не найден");
        } else if (this.size == 1) {
            oldElement = removeFirst();
        } else {
            Node previous = getNodeByNumber(this.size - 1);
            oldElement = previous.next.data;
            previous.next = previous.next.next;
            this.size--;
        }
        return oldElement;
    }

    public E remove(int index) {
        if (index >= this.size || index < 0) {
            throw new NoSuchElementException("Элемент не найден");
        }

        E element = null;
        if (index >= 0 && index < this.size) {
            if (index == 0) {
                element = removeFirst();
            } else if (index == this.size - 1) {
                element = removeLast();
            } else {
                Node previous = getNodeByNumber(index - 1);
                element = previous.next.data;
                previous.next = previous.next.next;
                this.size--;
            }
        }
        return element;
    }


    private class Node {
        private Node next; //Указатель на следующий элемент
        private E data; //Данные


        public Node(Node next, E data) {
            this.next = next;
            this.data = data;
        }
    }
}
