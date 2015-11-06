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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Represents all the instructions to perform on a ring
 *
 * @author Kevin Raoofi
 */
public final class Instructions {

    private final List<Integer> initializeData;

    private final List<Operation> operations;

    private Instructions(List<Integer> initializeData,
            List<Operation> operations) {
        this.initializeData = initializeData;
        this.operations = operations;
    }

    private static final Pattern INIT_PATTERN = Pattern.compile(
            "^(?:\\s*\\d+\\s*)+$");

    private static final Pattern OPERATION_PATTERN = Pattern.compile(
            "^\\w+\\s*(?:\\d+)?$");

    /**
     * Instructions method for an Instructions object; constructed by reading in
     * the file
     *
     * @param p path to the file to be read
     * @return instructions in file
     * @throws IOException if file cannot be read
     */
    public static Instructions readFile(Path p) throws IOException {
        final List<String> lines = Files.readAllLines(p);

        final List<Integer> initialData = Arrays.stream(lines.stream()
                .filter(INIT_PATTERN.asPredicate())
                .findAny()
                .map(s -> s.split("\\s"))
                .get())
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        final List<Operation> operations = lines.stream()
                .filter(OPERATION_PATTERN.asPredicate())
                .map(Operation::readOperation)
                .collect(Collectors.toList());

        return new Instructions(initialData, operations);
    }

    @Override
    public String toString() {
        return "Instructions{" + "initializeData=" + initializeData
                + ", operations=" + operations + '}';
    }

    public static void main(String... args) throws IOException {
        Path input
                = Paths.get("../")
                .toAbsolutePath()
                .normalize()
                .resolve("COSC489_PT_2158_Input.txt");

        Instructions readFile = Instructions.readFile(input);
        System.out.println(readFile);
    }
}
