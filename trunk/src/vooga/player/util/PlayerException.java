package vooga.player.util;

/**
 * @deprecated
 * @author Andrea
 *
 */

@SuppressWarnings("serial")
public class PlayerException extends RuntimeException
{
     public PlayerException (String message)
     {
         super(message);
     }


     public PlayerException (String message, Throwable throwable)
     {
         super(message, throwable);
     }
}
