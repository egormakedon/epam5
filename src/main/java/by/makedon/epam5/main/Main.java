package by.makedon.epam5.main;

import by.makedon.epam5.entity.Cashbox;
import by.makedon.epam5.entity.Visitor;
import by.makedon.epam5.exception.IncorrectFileException;
import by.makedon.epam5.reader.FreeCashBoxReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        List<Cashbox> cashboxList = new ArrayList<Cashbox>();
        List<Visitor> visitorList = new ArrayList<Visitor>();

        FreeCashBoxReader reader = new FreeCashBoxReader();
        try {
            reader.read("in.txt", cashboxList, visitorList);
        } catch (IncorrectFileException e) {
            LOGGER.log(Level.ERROR, e);
        }
    }
}
