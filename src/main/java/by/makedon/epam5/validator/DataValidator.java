package by.makedon.epam5.validator;

public class DataValidator {
    public static boolean validate(String data) {
        final String INPUT_DATA_REGEXP = "^CASHBOX_AMOUNT:\\d+CASHBOX_CAPACITY:\\d+VISITOR_AMOUNT:\\d+$";
        return data.matches(INPUT_DATA_REGEXP);
    }
}