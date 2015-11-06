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

/**
 * This interface details 
 * 
 * @author Kevin Raoofi
 * @param <E> type of element to store in the ring
 */
interface RingBaseRequirements<E> {

    /**
     * Add an element into the ring right after (clockwise) the current number.
     * The newly appended element will become the new current element.
     *
     * @param e element to insert
     */
    void append(E e);

    /**
     * Delete the current element. Its right hand side (clockwise) number will
     * become the new element.
     *
     * @return the deleted element
     */
    E delete();

    /**
     * Add an element to the ring before (clockwise) the current number. The
     * newly inserted element will become the new current element.
     *
     * @param e element to insert
     */
    void insert(E e);

    /**
     * Change the current pointer and a new element will become the current
     * element. If the jump is too large, then it will wrap around the ring.
     * POsitive jump numbers mean going clockwise. Negative means
     * counter-clockwise.
     *
     * @param d distance to jump
     */
    void jump(int d);

    /**
     * Move the current number to a new location. If the move number is too
     * large, then it will wrap around the ring. Positive move numbers mean
     * going clockwise. Negative means counter-clockwise.
     *
     * @param d distance to move
     */
    void move(int d);

    /**
     * Print out all the elements in the ring in one line starting at the
     * current number and going clockwise.
     *
     * @return String showing the ring's contents
     */
    String print();

    /**
     * Sort the elements in the ring in increasing order clockwise. The current
     * number is still after sorting.
     */
    void sort();

}
