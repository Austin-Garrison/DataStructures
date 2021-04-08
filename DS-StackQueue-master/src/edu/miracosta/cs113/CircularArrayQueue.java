package edu.miracosta.cs113;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class CircularArrayQueue<E> extends AbstractQueue <E> implements Queue<E> {
    int size;
    int head;
    int tail;
    int capacity;
    E[] circularQueue;

    public CircularArrayQueue(int initialCapacity) {
        capacity = initialCapacity;
        circularQueue = (E[]) new Object[initialCapacity];
        size = 0;
        head = 0;
        tail = capacity - 1;
    }

    public boolean add(E obj) {
        if (size == capacity)
            reallocate();
        else {
            size++;
            tail = (tail + 1) % capacity;
            circularQueue[tail] = obj;
        }
        return true;
    }

    @Override
    public boolean offer(E e) {
        if (size == capacity) {
			reallocate();
        }
        size++;
        tail = (tail + 1) % capacity;
        circularQueue[tail] = e;
        return true;
    }

    @Override
    public E peek() {
        if(size == 0)
            return null;
        else
            return circularQueue[head];
    }

    @Override
    public E poll() {
        if(size == 0)
            return null;
        else{
            E result = circularQueue[head];
            head = (head + 1) % capacity;
            size--;
            return result;
        }   
    }

    public E remove() {
        if(size == 0)
            throw new NoSuchElementException();

        else {    
            E result = circularQueue[head];
            head = (head + 1) % capacity;
            size--;
            return result;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iter();
    }

    @Override
    public int size() {
        return size;
    }

    public boolean addFirst(E item) {
        if (size == capacity) {
            reallocate();
        }
        size++;
        head = (head - 1 + capacity) % capacity;
        circularQueue[head] = item;
        return true;
    }

    private void reallocate() {
        int newCapacity = 2 * capacity;
        E[] newData = (E[]) new Object[newCapacity];
        int j = head;
        for (int i = 0; i < size; i++) {
            newData[i] = circularQueue[j];
            j = (j + 1) % capacity;
        }
        head = 0;
        tail = size - 1;
        capacity = newCapacity;
        circularQueue = newData;
    }

    private class Iter implements Iterator<E> {

        private int index;
        private int count = 0;

        public Iter() {
            index = head;
        }

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E returnValue = circularQueue[index];
            index = (index + 1) % capacity;
            count++;
            return returnValue;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
