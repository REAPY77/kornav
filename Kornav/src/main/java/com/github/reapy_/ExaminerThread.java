package com.github.reapy_;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.security.Key;
import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.Thread.sleep;

public class ExaminerThread {

    Cache<Long, Integer> logCache = Caffeine.newBuilder().expireAfterWrite(Duration.ofSeconds(10)).build();

    public int amountOfThreads;
    public LinkedBlockingQueue<Event> logicQueue;
    AtomicLong key = new AtomicLong(0);
    AtomicLong totallogs = new AtomicLong();
    int errorlogs = 0;

    public ExaminerThread(LinkedBlockingQueue<Event> logicQueue, int amountOfThreads) {
        this.logicQueue = logicQueue;
        this.amountOfThreads = amountOfThreads;
    }

    public void Examiner() throws InterruptedException {
        System.out.println("started Examiner");
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        for (int i = 0; i < amountOfThreads; i++) {
            executor.submit(() -> {
                while (true) {
                    Event event = logicQueue.take();
                    System.out.println("took out line");
                    if (event.priority < 3) {

                        logCache.put(key.incrementAndGet(), 1);

                    } else {
                        logCache.put(key.incrementAndGet(), 0);
                    }

                }
            });
        }
    }
    public void ValuesStatics(){
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        executor.submit(()-> {
            /*while (true) {
                errorlogs = 0;
                totallogs.set(logCache.asMap().size());
                logCache.asMap().forEach((key, value) -> {
                    if (value == 1) {
                        errorlogs++;
                    }
                    long totalLogCount = totallogs.get();
                    System.out.println(totalLogCount + " " + errorlogs);
                });
                sleep(500);
            } */
        });
    }
}