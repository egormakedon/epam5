package by.makedon.epam5.main;

import by.makedon.epam5.exception.IncorrectFileException;
import by.makedon.epam5.reader.FreeCashBoxReader;
import by.makedon.epam5.singleton.CashboxList;
import by.makedon.epam5.singleton.VisitorList;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        CashboxList cashboxList = CashboxList.getInstance();
        VisitorList visitorList = VisitorList.getInstance();

        FreeCashBoxReader reader = new FreeCashBoxReader();
        try {
            reader.read("in.txt", cashboxList, visitorList);
        } catch (IncorrectFileException e) {
            LOGGER.log(Level.ERROR, e);
        }

        for (int index = 0; index < cashboxList.size(); index++) {
            cashboxList.get(index).start();
        }

        new Thread() {
            @Override
            public void run() {
                for (int index = 0; index < visitorList.size() / 2; index++) {
                    cashboxList.get(0).put(visitorList.get(index));
                }
            }

        }.start();

        new Thread() {
            @Override
            public void run() {
                for (int index = visitorList.size() / 2; index < visitorList.size(); index++) {
                    cashboxList.get(1).put(visitorList.get(index));
                }
            }

        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    LOGGER.log(Level.WARN, e);
                }
                cashboxList.get(0).preOrder(visitorList.get(5));
            }

        }.start();
    }
}
