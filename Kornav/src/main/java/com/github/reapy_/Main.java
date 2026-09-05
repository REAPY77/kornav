package com.github.reapy_;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<String> arguments = new ArrayList<>(Arrays.asList("--output=json", "-n 10"));
        Interpreter process = new Interpreter(arguments);
        LogReader reader = new LogReader(process.ProcessStarter());
        }
}
