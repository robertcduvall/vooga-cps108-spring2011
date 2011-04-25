package vooga.collisions;

@SuppressWarnings("serial")
public class CollisionException extends RuntimeException
{
	public static final String NO_SUCH_CONSTRUCTOR = "No such constructor exists in this library";
	public static final String NO_SUCH_METHOD = "No such method exists in this libary";
	public static final String BAD_SYNTAX = "poor syntax";
    
    public CollisionException (String message)
    {
        super(message);
    }


    public CollisionException (String message, Throwable throwable)
    {
        super(message, throwable);
    }
    
}
