package com.threalpool.executor;

import java.util.concurrent.BlockingQueue;

public class PoolThreadRunnable implements Runnable {
    private BlockingQueue taskQ = null;
    private Thread thread = null;
    private boolean isStopped = false;

    public PoolThreadRunnable(BlockingQueue queue) {
        this.taskQ = queue;
    }

    @Override
    public void run() {
        this.thread = Thread.currentThread();
        while(!isStopped()) {
            Runnable runnable = null;
            try {
                runnable = (Runnable) taskQ.take();
                runnable.run();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void doStop() {
        isStopped = true;
        this.thread.interrupt();
    }

    private boolean isStopped() {
        return this.isStopped;
    }
}
