package com.github.reapy_;

import java.io.*;

public class LogReader {
    BufferedReader reader;
    public LogReader(InputStream stream) throws IOException {
        reader = new BufferedReader(new InputStreamReader(stream));
        System.out.println(reader.readLine());
    }
}
