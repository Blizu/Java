package zad1;

public class TranslationException extends Exception {
    private String message = "";

    public TranslationException() {
    }

    public TranslationException(String msg) {
        this.message = msg;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
