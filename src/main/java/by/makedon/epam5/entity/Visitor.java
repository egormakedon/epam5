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
        if (cashboxState != null) {
            cashboxState.preOrder(this);
        }
    }

    public void changeCashbox(State c) {
        if (cashboxState != null) {
            cashboxState.removeFromCashbox(this);
            setCashboxState(c);
            this.cashboxState.changeCashbox(this);
        }
    }

    private static void incrementCounter() {
        counter.incrementAndGet();
    }
}