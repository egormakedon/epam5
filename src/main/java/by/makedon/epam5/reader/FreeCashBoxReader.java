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
        try {
            scanner = new Scanner(new File("in/" + filename));
            StringBuilder sb = new StringBuilder();

            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }

            if (!DataValidator.validate(sb.toString())) {
                throw new IncorrectFileException(sb.toString() + " - has incorrect data");
            }

            int cashboxAmount = DataParser.parse(sb.toString(), Constant.CASHBOX_AMOUNT);
            int cashboxCapacity = DataParser.parse(sb.toString(), Constant.CASHBOX_CAPACITY);
            int visitorAmount = DataParser.parse(sb.toString(), Constant.VISITOR_AMOUNT);

            Constant.CASHBOX_AMOUNT.set(cashboxAmount);
            Constant.CASHBOX_CAPACITY.set(cashboxCapacity);
            Constant.VISITOR_AMOUNT.set(visitorAmount);


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
