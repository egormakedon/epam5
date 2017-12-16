package by.makedon.epam5.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataParser {
    public static int parse(String dataLine) {
        final String AMOUNT_REGEXP = "\\d+";
        Pattern p = Pattern.compile(AMOUNT_REGEXP);
        Matcher m = p.matcher(dataLine);
        m.find();
        String stringAmount = m.group();
        return Integer.parseInt(stringAmount);
    }
}