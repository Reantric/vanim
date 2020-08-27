package vanim.root.exception;

public class ImpossibleBoundsException extends Exception {
    public ImpossibleBoundsException(String err, Throwable throwable) {
        super(err, throwable);
    }

    public ImpossibleBoundsException(String err) {
        super(err);
    }
}