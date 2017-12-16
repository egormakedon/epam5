package by.makedon.epam5.exception;

public class IncorrectFileException extends Exception{
    public IncorrectFileException() {}
    public IncorrectFileException(String m) {
        super(m);
    }
    public IncorrectFileException(String m, Throwable th) {
        super(m, th);
    }
    public IncorrectFileException(Throwable th) {
        super(th);
    }
}
