package games.patterson_game.refactoredVooga.sprites.util;

@SuppressWarnings("serial")
public class SpriteException extends RuntimeException
{

    
    public SpriteException (String message)
    {
        super(message);
    }


    public SpriteException (String message, Throwable throwable)
    {
        super(message, throwable);
    }
    
}
