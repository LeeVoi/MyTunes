package UTIL.exceptions;

public class PlayListExc extends Throwable {
    private String message;
    public PlayListExc(String message, Exception e) {
        this.message = message;
        e.printStackTrace();
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
}
