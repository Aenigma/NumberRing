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

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author Kevin Raoofi
 */
@Ignore
public class RingTest {

    final Supplier<Ring> ringImpl;

    private static final Integer[] testInput
            = new Integer[]{2, 3, 5, 6, 1, 4, 8, 5};

    private static final List<Integer> testInputList = Arrays.asList(testInput);

    /**
     *
     * @param ringImpl factory for ring implementation
     */
    public RingTest(Supplier<Ring> ringImpl) {
        this.ringImpl = ringImpl;
    }

    /**
     * Test of insert method, of class Ring.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        final Ring<Integer> instance = ringImpl.get();
        final Integer[] expected = new Integer[]{7, 2, 3, 5, 6, 1, 4, 8, 5};
        final Integer[] actual;

        instance.addAll(testInputList);
        instance.insert(7);

        final List<Integer> l = instance.toList();
        actual = new Integer[l.size()];

        l.toArray(actual);

        System.out.println("Actual: " + Arrays.toString(actual));
        System.out.println("Expected: " + Arrays.toString(expected));

        assertArrayEquals(expected, actual);
    }

    /**
     * Test of append method, of class Ring.
     */
    @Test
    public void testAppend() {
        System.out.println("append");
        final Ring<Integer> instance = ringImpl.get();
        final Integer[] expected = new Integer[]{7, 3, 5, 6, 1, 4, 8, 5, 2};
        final Integer[] actual;

        instance.addAll(testInputList);
        instance.append(7);

        final List<Integer> l = instance.toList();
        actual = new Integer[l.size()];

        l.toArray(actual);

        System.out.println("Actual: " + Arrays.toString(actual));
        System.out.println("Expected: " + Arrays.toString(expected));

        assertArrayEquals(expected, actual);
    }

    /**
     * Test of delete method, of class Ring.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        final Ring<Integer> instance = ringImpl.get();
        final Integer[] expected = new Integer[]{3, 5, 6, 1, 4, 8, 5};
        final Integer[] actual;

        instance.addAll(testInputList);
        instance.delete();

        final List<Integer> l = instance.toList();
        actual = new Integer[l.size()];

        l.toArray(actual);

        System.out.println("Actual: " + Arrays.toString(actual));
        System.out.println("Expected: " + Arrays.toString(expected));

        assertArrayEquals(expected, actual);
    }

    /**
     * Test of jump method, of class Ring.
     */
    @Test
    public void testJump() {
        System.out.println("jump");
        final Ring<Integer> instance = ringImpl.get();
        final Integer[] expected = new Integer[]{5, 6, 1, 4, 8, 5, 2, 3};
        final Integer[] actual;

        instance.addAll(testInputList);
        instance.jump(10);

        final List<Integer> l = instance.toList();
        actual = new Integer[l.size()];

        l.toArray(actual);

        System.out.println("Actual: " + Arrays.toString(actual));
        System.out.println("Expected: " + Arrays.toString(expected));

        assertArrayEquals(expected, actual);
    }

    /**
     * Test of move method, of class Ring.
     */
    @Test
    public void testMove() {
        System.out.println("move");
        final Ring<Integer> instance = ringImpl.get();
        final Integer[] expected = new Integer[]{2, 6, 1, 4, 8, 5, 3, 5};
        final Integer[] actual;

        instance.addAll(testInputList);
        instance.move(-5);

        final List<Integer> l = instance.toList();
        actual = new Integer[l.size()];

        l.toArray(actual);

        System.out.println("Actual: " + Arrays.toString(actual));
        System.out.println("Expected: " + Arrays.toString(expected));

        assertArrayEquals(expected, actual);
    }

    /**
     * Test of sort method, of class Ring.
     */
    @Test
    public void testSort() {
        System.out.println("sort");
        final Ring<Integer> instance = ringImpl.get();
        final Integer[] expected = new Integer[]{2, 3, 4, 5, 5, 6, 8, 1};
        final Integer[] actual;

        instance.addAll(testInputList);
        instance.sort();

        final List<Integer> l = instance.toList();
        actual = new Integer[l.size()];

        l.toArray(actual);

        System.out.println("Actual: " + Arrays.toString(actual));
        System.out.println("Expected: " + Arrays.toString(expected));

        assertArrayEquals(expected, actual);
    }

    /**
     * Test of print method, of class Ring.
     */
    @Test
    public void testPrint() {
        System.out.println("print");
        final Ring<Integer> instance = ringImpl.get();
        final String expected = "2 3 5 6 1 4 8 5";
        final String actual;

        instance.addAll(testInputList);

        actual = instance.print();

        System.out.println("Actual: " + actual);
        System.out.println("Expected: " + expected);

        assertEquals(expected, actual);
    }

}
