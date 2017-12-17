package by.makedon.epam5.singleton;

import by.makedon.epam5.entity.Visitor;

import java.util.ArrayList;
import java.util.List;
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

    private List<Visitor> visitorList = new ArrayList<Visitor>();

    public void add(Visitor visitor) {
        lock.lock();
        try {
            visitorList.add(visitor);
        } finally {
            lock.unlock();
        }
    }

    public Visitor get(int index) {
        lock.lock();
        try {
            return visitorList.get(index);
        } finally {
            lock.unlock();
        }
    }
}
