package by.makedon.epam5.constant;

public enum Constant {
    CASHBOX_AMOUNT, CASHBOX_CAPACITY, VISITOR_AMOUNT;

    private int number;

    public void set(int number) {
        this.number = number;
    }

    public int get() {
        return number;
    }
}
