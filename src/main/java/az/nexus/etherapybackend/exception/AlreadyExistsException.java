package az.nexus.etherapybackend.exception;


public class AlreadyExistsException extends RuntimeException
{
    public AlreadyExistsException(String message)
    {
        super(message);
    }
}
