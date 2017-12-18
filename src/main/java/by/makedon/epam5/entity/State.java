package by.makedon.epam5.entity;

public interface State {
    void preOrder(Visitor visitor);
    void changeCashbox(Visitor visitor);
    void removeFromCashbox(Visitor visitor);
}
