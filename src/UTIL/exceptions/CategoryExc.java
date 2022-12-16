package UTIL.exceptions;

public class CategoryExc extends Throwable{
    private String message;
    public CategoryExc(String message, Exception e) {
        this.message = message;
        e.printStackTrace();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
