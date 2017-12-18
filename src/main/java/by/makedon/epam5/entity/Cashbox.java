package by.makedon.epam5.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Cashbox extends Thread implements State {
    private final static Logger LOGGER = LogManager.getLogger(Cashbox.class);

    private static AtomicInteger counter = new AtomicInteger(0);
    private int cashboxId;
    private BlockingQueue<Visitor> visitorQueue;

    public Cashbox(int capacity) {
        cashboxId = counter.get();
        incrementCounter();
        visitorQueue = new ArrayBlockingQueue<Visitor>(capacity);
    }

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
                Visitor visitor = visitorQueue.take();
                System.out.println(visitor.toString() + " out from " + this.toString());
            } catch (InterruptedException e) {
                LOGGER.log(Level.WARN, e);
            }
        }
    }

    @Override
    public void preOrder(Visitor visitor) {
        visitorQueue.remove(visitor);
        System.out.println(visitor.toString() + " out from " + this.toString() + " without queue");
    }

    @Override
    public String toString() {
        return "cashbox: " + cashboxId;
    }

    public void put(Visitor visitor) {
        try {
            visitorQueue.put(visitor);
        } catch (InterruptedException e) {
            LOGGER.log(Level.WARN, e);
        }
    }

    private static void incrementCounter() {
        counter.incrementAndGet();
    }
}
