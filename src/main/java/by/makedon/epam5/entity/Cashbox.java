package by.makedon.epam5.entity;

public class Cashbox extends Thread {
    private int cashboxId;

    public Cashbox(int cashboxId) {
        this.cashboxId = cashboxId;
    }

    public int getCashboxId() {
        return cashboxId;
    }
}
