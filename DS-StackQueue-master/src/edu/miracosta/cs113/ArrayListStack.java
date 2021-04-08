package edu.miracosta.cs113;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class ArrayListStack<E> implements StackInterface <E> {
    ArrayList<E> stack;
    int head;

    public ArrayListStack() {
        stack = new ArrayList<E>();
        head = -1;
    }

    public boolean equals(ArrayListStack<E> other) {
        return stack.equals(other.stack);
    }

    public boolean empty() {
        return stack.isEmpty();
    }

    public E peek() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.get(head);
    }

    public E pop() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        E newElement = stack.remove(head);
        head--;

        return newElement;
    }

    public E push(E obj) {
        stack.add(obj);
        head++;
        return stack.get(head);
    }

    public int getSize() {
        return head + 1;
    }
}