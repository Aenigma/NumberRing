/*
 * Copyright 2015 Kevin Raoofi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.frostburg.computerclub.edu.frostburg.cosc489.numberring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A link based ring buffer implementation
 *
 * @author Kevin Raoofi
 * @param <E> type of element to store in the ring
 *
 */
public class CircularLinkedList<E> implements Ring<E> {

    private final Comparator<E> cmp;
    private RingNode head;
    private int size = 0;

    {
        head = new RingNode();
        head.prev = head;
        head.next = head;
    }

    /**
     * Uses natural ordering to sort.
     *
     * It is invalid if {@code E} does not implement {@link Comparable}. If
     * {@code E} does not implement {@link Comparable}, it must use
     * {@link CircularLinkedList#CircularLinkedList(java.util.Comparator)},
     * instead.
     */
    public CircularLinkedList() {
        this((Comparator<E>) Comparator.naturalOrder());
    }

    /**
     * Uses a comparator to sort.
     *
     * @param cmp uses the supplied comparator to order the elements
     */
    public CircularLinkedList(Comparator<E> cmp) {
        this.cmp = cmp;
    }

    private void checkAdd(E e) {
        if (e == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }

        if (this.head.data == null && this.size > 0) {
            throw new IllegalStateException(
                    "Head data is null but size is non-zero");
        }
    }

    private void checkDelete() {
        if (this.size <= 0) {
            throw new IllegalStateException(
                    "Cannot delete elmements on ring of size " + this.size);
        }
    }

    @Override
    public void insert(E e) {
        checkAdd(e);
        size++;

        // If the ring is empty, put it there and we're done
        if (this.head.data == null) {
            this.head.data = e;
            return;
        }

        RingNode insert = new RingNode(e, this.head.prev, this.head);

        // tail's next must be the new element
        this.head.prev.next = insert;
        this.head.prev = insert;

        this.head = insert;
    }

    @Override
    public void append(E e) {
        checkAdd(e);
        size++;

        // If the ring is empty, put it there and we're done
        if (this.head.data == null) {
            this.head.data = e;
            return;
        }

        RingNode insert = new RingNode(e, this.head, this.head.next);

        // tail's next must be the new element
        this.head.next.prev = insert;
        this.head.next = insert;

        this.head = insert;
    }

    @Override
    public E delete() {
        final E data = this.head.data;
        final RingNode newNext = this.head.next;
        size--;

        this.head.next.prev = this.head.prev;
        this.head.prev.next = this.head.next;

        this.head.next = this.head.prev = null;
        this.head.data = null;

        this.head = newNext;

        return data;
    }

    @Override
    public void jump(int d) {
        if (size <= 0) {
            return;
        }

        d = d % this.size;

        while (d < 0) {
            this.head = this.head.prev;
            ++d;
        }

        while (d > 0) {
            this.head = this.head.next;
            --d;
        }
    }

    @Override
    public void move(int d) {
        if (d == 0 || size <= 0) {
            return;
        }

        E old = this.delete();
        this.jump(--d);
        this.append(old);
    }

    @Override
    public void sort() {
        List<E> l = this.toList();
        Collections.sort(l, cmp);
        int cIndex = l.indexOf(this.head.data);
        this.head = new RingNode();
        this.head.next = this.head;
        this.head.prev = this.head;

        this.size = 0; // adding operators will error otherwise

        for (int i = 0; i < l.size(); i++) {
            E e = l.get((cIndex + i) % l.size());
            append(e);
        }

        jump(1);
    }

    @Override
    public String print() {
        return this.toList()
                .stream()
                .map((a) -> a.toString())
                .collect(Collectors.joining(" "));
    }

    @Override
    public List<E> toList() {
        List<E> l = new ArrayList<>(this.size);

        if (this.size == 0) {
            return l;
        }

        l.add(this.head.data);
        RingNode current = this.head.next;

        while (current != this.head) {
            l.add(current.data);
            current = current.next;
        }

        return l;
    }

    private final class RingNode implements Comparable<RingNode> {

        E data;
        RingNode prev;
        RingNode next;

        public RingNode() {
        }

        public RingNode(E data, RingNode prev, RingNode next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public int compareTo(RingNode o) {
            return cmp.compare(this.data, o.data);
        }

    }

}
