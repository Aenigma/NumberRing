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

import edu.frostburg.computerclub.edu.frostburg.cosc489.numberring.service.CliNumberRingService;
import edu.frostburg.computerclub.edu.frostburg.cosc489.numberring.service.Instructions;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Main method; deals with getting the instructions from file
 *
 * @author Kevin Raoofi
 */
public class Main {

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String... args) throws IOException {
        Path input
                = Paths.get("../")
                .toAbsolutePath()
                .normalize()
                .resolve("COSC489_PT_2158_Input.txt");

        if (!Files.exists(input)) {
            System.out.println("File does not exist; Goodbye :(");
            // TODO: JFileChooser to get file, instead
            return;
        }

        Instructions readFile = Instructions.readFile(input);
        CliNumberRingService cliNumberRingService
                = new CliNumberRingService(new CircularLinkedList<>(), readFile);

        System.out.println(readFile);
        cliNumberRingService.run();
    }
}
