package UTIL.exceptions;

public class SongDAOExc {
    public final String message;
    public SongDAOExc(String message, Exception e) {
        this.message = message;
        e.printStackTrace();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
}
