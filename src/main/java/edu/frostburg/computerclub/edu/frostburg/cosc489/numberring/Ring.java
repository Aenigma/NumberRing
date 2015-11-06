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

import java.util.Collection;
import java.util.List;

/**
 * This interface defines additional methods on top of the base requirements for
 * a Ring. There is no
 *
 * @author Kevin Raoofi
 * @param <E> type of element to store in the ring
 */
public interface Ring<E> extends RingBaseRequirements<E> {

    /**
     * The {@link List} will have the elements starting from the current number
     * going clockwise
     *
     * @return a list representation of the data
     */
    List<E> toList();

    /**
     * Adds all elements and sets the head to be the first element in the list
     *
     * @param items items to insert into the ring
     */
    default void addAll(Collection<E> items) {
        for (E e : items) {
            append(e);
        }

        jump(1);
    }
}
