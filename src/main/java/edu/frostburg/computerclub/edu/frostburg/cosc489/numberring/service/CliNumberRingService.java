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
package edu.frostburg.computerclub.edu.frostburg.cosc489.numberring.service;

import edu.frostburg.computerclub.edu.frostburg.cosc489.numberring.Ring;
import java.util.EnumMap;
import java.util.function.BiConsumer;

/**
 *
 * @author Kevin Raoofi
 */
public class CliNumberRingService implements Runnable {

    private final Ring<Integer> ring;
    private final Instructions is;

    private static final EnumMap<Operation.OperationType, BiConsumer<Ring<Integer>, Operation>> operationMap;

    static {
        operationMap = new EnumMap(Operation.OperationType.class);

        operationMap.put(Operation.OperationType.APPEND, (r, op) -> {
            r.append(op.getOperand());
        });
        operationMap.put(Operation.OperationType.DELETE, (r, op) -> {
            r.delete();
        });
        operationMap.put(Operation.OperationType.INSERT, (r, op) -> {
            r.insert(op.getOperand());
        });
        operationMap.put(Operation.OperationType.JUMP, (r, op) -> {
            r.jump(op.getOperand());
        });
        operationMap.put(Operation.OperationType.MOVE, (r, op) -> {
            r.move(op.getOperand());
        });
        operationMap.put(Operation.OperationType.SORT, (r, op) -> {
            r.sort();
        });
        operationMap.put(Operation.OperationType.PRINT, (r, op) -> {
            System.out.println(r.print());
        });
    }

    public CliNumberRingService(Ring<Integer> ring, Instructions is) {
        this.ring = ring;
        this.is = is;
    }

    @Override
    public void run() {
        ring.addAll(is.getInitializeData());
        is.getOperations()
                .forEach(op -> {
            operationMap.get(op.getOperation())
                    .accept(ring, op);
        });
    }

}
