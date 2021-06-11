package club.musician.exception;

/**
 * 表示当用户的姓名有问题，就抛出异常
 */
public class NameException extends MyUserException {
    public NameException() {
        super();
    }

    public NameException(String message) {
        super(message);
    }
}
