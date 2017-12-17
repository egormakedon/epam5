package by.makedon.epam5.singleton;

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


}
