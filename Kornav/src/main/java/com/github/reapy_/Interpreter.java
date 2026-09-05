package com.github.reapy_;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class Interpreter {
    ArrayList<String> arguments = new ArrayList<>(Arrays.asList("journalctl", "-f"));
    ProcessBuilder builder;

    public Interpreter(ArrayList arguments) {
        this.arguments.addAll(arguments);
        builder = new ProcessBuilder(this.arguments);
    }
    public InputStream ProcessStarter() throws IOException {
        Process process1 = builder.start();
        return process1.getInputStream();


    }
}


