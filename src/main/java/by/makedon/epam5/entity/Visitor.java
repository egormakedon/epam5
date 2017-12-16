package by.makedon.epam5.entity;

public class Visitor {
    private int visitorId;
    private static int counter = 0;
    private int cashboxNumber;

    public Visitor(int cashboxNumber) {
        visitorId = counter;
        incrementCounter();
        this.cashboxNumber = cashboxNumber;
    }

    public int getVisitorId() {
        return visitorId;
    }

    public int getCashboxNumber() {
        return cashboxNumber;
    }

    private static void incrementCounter() {
        counter++;
    }
}
