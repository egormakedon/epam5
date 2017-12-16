package by.makedon.epam5.validator;

import by.makedon.epam5.parser.DataParser;

import java.util.regex.Pattern;

public class DataValidator {
    public static boolean validateCashboxAmount(String dataLine) {
        final String CASHBOX_AMOUNT_REGEXP = "^CASHBOX_AMOUNT: \\d+$";
        Pattern p = Pattern.compile(CASHBOX_AMOUNT_REGEXP);
        if (p.matcher(dataLine).matches()) {
            int cashboxAmount = DataParser.parse(dataLine);
            return cashboxAmount > 0;
        } else {
            return false;
        }
    }

    public static boolean validateVisitor(String dataLine, int cashboxAmount) {
        final String VISITOR_REGEXP = "^VISITOR, CASHBOX_NUMBER: \\d+$";
        Pattern p = Pattern.compile(VISITOR_REGEXP);
        if (p.matcher(dataLine).matches()) {
            int cashboxNumber = DataParser.parse(dataLine);
            return cashboxNumber >= 0 && cashboxNumber < cashboxAmount;
        } else {
            return false;
        }
    }
}