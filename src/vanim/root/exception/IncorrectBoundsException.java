package vanim.root.exception;

public class IncorrectBoundsException extends Exception {
    public IncorrectBoundsException(String err, Throwable throwable) {
        super(err, throwable);
    }

    public IncorrectBoundsException(String err) {
        super(err);
    }
}

