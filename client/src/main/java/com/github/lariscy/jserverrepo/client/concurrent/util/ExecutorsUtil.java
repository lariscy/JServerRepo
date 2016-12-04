package com.github.lariscy.jserverrepo.client.concurrent.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Steven
 */
public class ExecutorsUtil {
    
    public static ExecutorService getSingleThreadExecutor(String threadName){
        return Executors.newSingleThreadExecutor((Runnable r) -> {
            Thread t = Executors.defaultThreadFactory().newThread(r);
            t.setName(threadName);
            t.setDaemon(true);
            return t;
        });
    }
    
    public static ExecutorService getFixedThreadPoolExecutor(String threadName, int numOfThreads){
        return Executors.newFixedThreadPool(numOfThreads, new ThreadFactory() {
            
            private final AtomicInteger counter = new AtomicInteger();
            
            @Override
            public Thread newThread(Runnable r) {
                Thread t = Executors.defaultThreadFactory().newThread(r);
                t.setName(String.format("%s-%d", threadName, counter.incrementAndGet()));
                t.setDaemon(true);
                return t;
            }
        });
    }

}
