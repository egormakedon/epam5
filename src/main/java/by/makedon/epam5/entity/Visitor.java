package by.makedon.epam5.entity;

import java.util.concurrent.atomic.AtomicInteger;

public class Visitor {
    private static AtomicInteger counter = new AtomicInteger(0);
    private int visitorId;
    private State cashboxState;

    public Visitor() {
        visitorId = counter.get();
        incrementCounter();
    }

    @Override
    public String toString() {
        return "visitor: " + visitorId;
    }

    public void setCashboxState(State cashboxState) {
        this.cashboxState = cashboxState;
    }

    public void preOrder() {
        cashboxState.preOrder(this);
    }

    private static void incrementCounter() {
        counter.incrementAndGet();
    }
}