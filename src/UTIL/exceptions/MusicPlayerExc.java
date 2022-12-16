package UTIL.exceptions;

public class MusicPlayerExc extends Throwable {
        private String message;
        public MusicPlayerExc(String message, Exception e) {
            this.message = message;
            e.printStackTrace();
        }

        @Override
        public String getMessage()
        {
            return message;
        }
    }

