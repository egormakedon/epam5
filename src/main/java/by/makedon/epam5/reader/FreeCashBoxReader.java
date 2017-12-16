package by.makedon.epam5.reader;

import by.makedon.epam5.constant.Constant;
import by.makedon.epam5.entity.Cashbox;
import by.makedon.epam5.entity.Visitor;
import by.makedon.epam5.exception.IncorrectFileException;
import by.makedon.epam5.parser.DataParser;
import by.makedon.epam5.validator.DataValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class FreeCashBoxReader {
    private final static Logger LOGGER = LogManager.getLogger(FreeCashBoxReader.class);

    public void read(String filename, List<Cashbox> cashboxList, List<Visitor> visitorList) throws IncorrectFileException {
        File file = new File("in/" + filename);
        if (!file.exists()) {
            throw new IncorrectFileException(filename + " hasn't found");
        }
        LOGGER.log(Level.INFO, filename + " has found");
        Scanner scanner = null;
        int cashboxAmount = 0;
        try {
            scanner = new Scanner(new File("in/" + filename));
            if (!scanner.hasNextLine()) {
                throw new IncorrectFileException(filename + " - has incorrect data");
            }
            String dataLine = scanner.nextLine();
            if (DataValidator.validateCashboxAmount(dataLine)) {
                cashboxAmount = DataParser.parse(dataLine);
                Constant.CASHBOX_AMOUNT.setCashboxAmount(cashboxAmount);
                for (int index = 0; index < cashboxAmount; index++) {
                    Cashbox cashbox = new Cashbox(index);
                    cashboxList.add(cashbox);
                }
            } else {
                throw new IncorrectFileException(dataLine + " - has incorrect data");
            }
            while (scanner.hasNextLine()) {
                dataLine = scanner.nextLine();
                if (DataValidator.validateVisitor(dataLine, cashboxAmount)) {
                    int cashboxNumber = DataParser.parse(dataLine);
                    Visitor visitor = new Visitor(cashboxNumber);
                    visitorList.add(visitor);
                } else {
                    LOGGER.log(Level.ERROR, dataLine + " - has incorrect data");
                }
            }
        } catch (FileNotFoundException e) {
            throw new IncorrectFileException("unknown error", e);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        LOGGER.log(Level.INFO, filename + " has read successful");
    }
}
