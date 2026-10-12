package com.github.reapy_;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import static java.lang.Thread.sleep;

public class JacksonParser {
    private final LinkedBlockingQueue<String> interpretationQueue;
    private final LinkedBlockingQueue<Event> logicQueue;
    private final LinkedBlockingQueue<Event> correlationQueue;
    ObjectMapper mapper = new ObjectMapper();
    Event event;
    String line;
    int amountOfThreads;

    public JacksonParser(int amountOfThreads,LinkedBlockingQueue<String> interpretationQueue, LinkedBlockingQueue<Event> logicQueue, LinkedBlockingQueue<Event> correlationQueue){
        this.interpretationQueue = interpretationQueue;
        this.logicQueue = logicQueue;
        this.correlationQueue = correlationQueue;
        this.amountOfThreads = amountOfThreads;
    }
    public void Parser() {
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        for (int i = 0 ; i < amountOfThreads; i++) {
            executor.submit(() -> {
                while (true) {
                    try {
                        line = interpretationQueue.take();
                        System.out.println("JSONParser: took out line");
                        event = mapper.readValue(line, Event.class);
                        correlationQueue.put(event);
                        logicQueue.put(event);
                    } catch (InterruptedException | JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }

            });
        }
    }
}
