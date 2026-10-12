package com.github.reapy_;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;

public class Main {
    public HashMap<String, BlockingQueue<Event>>
    public static void main(String[] args) throws IOException, InterruptedException {

        int amountOfThreadsParser = 2;
        ArrayList<String> arguments = new ArrayList<>(Arrays.asList("--output=json", "-n 10"));
        Interpreter interpreter = new Interpreter(arguments);
        LinkedBlockingQueue<String> interpretationQueue= new LinkedBlockingQueue<>(3000);
        LinkedBlockingQueue<Event> correlationQueue = new LinkedBlockingQueue<>(3000);
        LinkedBlockingQueue<Event> logicQueue = new LinkedBlockingQueue<>(3000);
        InputStream stream = interpreter.ProcessStarter();
        LogReader logReader = new LogReader(stream, interpretationQueue);
        ExaminerThread examinerThread = new ExaminerThread(logicQueue, 4);

        JacksonParser parser = new JacksonParser(amountOfThreadsParser , interpretationQueue, logicQueue, correlationQueue);
        parser.Parser();
        logReader.ReaderActivator();
        examinerThread.Examiner();
        examinerThread.ValuesStatics();
        while (true){
            sleep(20);
        }

        /*ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        executor.submit(()->{

        }); */

        }
}
