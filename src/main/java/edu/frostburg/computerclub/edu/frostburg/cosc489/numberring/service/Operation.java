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

/**
 *
 * @author Kevin Raoofi
 */
class Operation {

    private final OperationType operation;
    private final int operand;

    private Operation(OperationType operation, int operand) {
        this.operation = operation;
        this.operand = operand;
    }

    public static Operation readOperation(String line) {
        final String[] tokens = line.split("\\s");
        final OperationType operation;
        int operand = 0;

        operation = OperationType.valueOf(tokens[0].toUpperCase());

        if (operation.isConsumer()) {
            operand = Integer.parseInt(tokens[1]);
        }

        return new Operation(operation, operand);
    }

    public OperationType getOperation() {
        return operation;
    }

    public int getOperand() {
        return operand;
    }

    @Override
    public String toString() {
        return "Operation{" + "operation=" + operation + ", operand=" + operand
                + '}';
    }

    enum OperationType {

        APPEND(true),
        DELETE(false),
        INSERT(true),
        JUMP(true),
        MOVE(true),
        SORT(false),
        PRINT(false);

        private final boolean consumer;

        private OperationType(boolean consumer) {
            this.consumer = consumer;
        }

        boolean isConsumer() {
            return consumer;
        }

    }
}
