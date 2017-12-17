package by.makedon.epam5.parser;

import by.makedon.epam5.constant.Constant;
import by.makedon.epam5.exception.IncorrectFileException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataParser {
    private static final String NUMBER_REGEXP = "\\d+";
    private static final String CASHBOX_AMOUNT_REGEXP = "CASHBOX_AMOUNT:\\d+";
    private static final String CASHBOX_CAPACITY_REGEXP = "CASHBOX_CAPACITY:\\d+";
    private static final String VISITOR_AMOUNT_REGEXP = "VISITOR_AMOUNT:\\d+";

    public static int parse(String data, Constant constant) throws IncorrectFileException {
        String regexp = takeRegexp(constant);
        Pattern p = Pattern.compile(regexp);
        Matcher m = p.matcher(data);
        m.find();
        String string = m.group();
        p = Pattern.compile(NUMBER_REGEXP);
        m = p.matcher(string);
        m.find();
        int number = Integer.parseInt(m.group());
        if (validate(number)) {
            return number;
        } else {
            throw new IncorrectFileException(string + " - has incorrect data");
        }
    }

    private static String takeRegexp(Constant constant) throws IncorrectFileException {
        String regexp;
        switch (constant) {
            case CASHBOX_AMOUNT:
                regexp = CASHBOX_AMOUNT_REGEXP;
                break;
            case CASHBOX_CAPACITY:
                regexp = CASHBOX_CAPACITY_REGEXP;
                break;
            case VISITOR_AMOUNT:
                regexp = VISITOR_AMOUNT_REGEXP;
                break;
            default:
                throw new IncorrectFileException("undefined constant: " + constant);
        }
        return regexp;
    }

    private static boolean validate(int number) {
        return number > 0;
    }
}