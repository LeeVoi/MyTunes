package UTIL.exceptions;
public class SongExc extends Throwable {
    public String message;
    public SongExc(String message, Exception e)
    {
        this.message = message;
        e.printStackTrace();
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
