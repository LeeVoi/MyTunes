package UTIL.exceptions;

public class AuthorExc extends Throwable{
    private String message;
    public AuthorExc(String message, Exception e) {
        this.message = message;
        e.printStackTrace();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
