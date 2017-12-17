package by.makedon.epam5.singleton;

import java.util.concurrent.locks.ReentrantLock;

public class VisitorList {
    private static VisitorList instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private VisitorList() {}
    public static VisitorList getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new VisitorList();
            }
        } finally {
            lock.unlock();
        }
        return instance;
    }


}
