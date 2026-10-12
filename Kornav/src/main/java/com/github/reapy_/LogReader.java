package com.github.reapy_;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class LogReader {
    public boolean activator = true;
    BufferedReader reader;


    LinkedBlockingQueue<String> InterpretationQueue;
    public LogReader(InputStream stream, LinkedBlockingQueue<String> InterpretationQueue) throws IOException {
        this.InterpretationQueue = InterpretationQueue;
        reader = new BufferedReader(new InputStreamReader(stream));
        System.out.println("reader created");
        ObjectMapper mapper = new ObjectMapper();


    }
    public void ReaderActivator() throws IOException, InterruptedException {
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        executor.submit(()->{
            String line;
            while(activator) {

                try {
                    line = reader.readLine();





                    InterpretationQueue.put(line);
                    System.out.println("logreader: added new line to queue");
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);


                }
            }
        });







    }


}
