package vooga.network.exceptions;



public class NetworkException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NetworkException (String message)
    {
        super(message);
    }
	
	public static NetworkException SERVER_NOT_EXIST =
        new NetworkException("Cannot find server, check server connectivity");
	
	public static NetworkException FAIL_TO_CREATE_HOST =
        new NetworkException("Cannot create host");
	
	public static NetworkException INTERNAL_ERROR =
        new NetworkException("Cannot tell the reason");
}
