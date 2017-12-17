package by.makedon.epam5.singleton;

import by.makedon.epam5.entity.Cashbox;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class CashboxList {
    private static CashboxList instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private CashboxList() {}
    public static CashboxList getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new CashboxList();
            }
        } finally {
            lock.unlock();
        }
        return instance;
    }

    private List<Cashbox> cashboxList = new ArrayList<Cashbox>();

    public void add(Cashbox cashbox) {
        lock.lock();
        try {
            cashboxList.add(cashbox);
        } finally {
            lock.unlock();
        }
    }

    public Cashbox get(int index) {
        lock.lock();
        try {
            return cashboxList.get(index);
        } finally {
            lock.unlock();
        }
    }
}
