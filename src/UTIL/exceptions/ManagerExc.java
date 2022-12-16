package UTIL.exceptions;

public class ManagerExc extends Throwable {
        private String message;
        public ManagerExc(String message, Exception e) {
            this.message = message;
            e.printStackTrace();
        }

        @Override
        public String getMessage() {
            return message;
        }
    }

