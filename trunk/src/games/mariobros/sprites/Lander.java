package games.mariobros.sprites;

import vooga.core.VoogaGame;

import vooga.core.event.IEventHandler;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.physics.PhysicsVelocityC;

@SuppressWarnings("serial")
public class Lander extends Sprite
{
	/**
	 * The paddle speed, in pixels per ms.
	 */
	public static final Double THRUST_POWER = 0.002D;
	public static final Double GRAVITY = 0.001D;

	private double hspeed = 0;
	private double vspeed = 0;

	private VoogaGame game;
	private boolean safe;
	private int lives = 3;
	private double initX, initY;

	/**
	 * Places the Lander at the given (x,y) coordinate.
	 * 
	 * @param game
	 *            The game that created this paddle.
	 * @param x
	 *            The x-coordinate.
	 * @param y
	 *            The y-coordinate.
	 */
	public Lander(VoogaGame game, double x, double y)
	{
		super(game.getImageLoader().getImage("lander"));

		setX(x - getWidth() / 2);
		setY(y - getHeight());
		setAngle(Direction.NORTH.getAngle());

		this.game = game;
		addComponents(new PhysicsVelocityC());
		initX = x;
		initY = y;

		// game.addEveryTurnEvent("Game.Forces.ShipGravity", new IEventHandler()
		// {
		// @Override
		// public void handleEvent (Object o)
		// {
		// accelerateShip();
		// }
		//
		// });

		game.registerEventHandler("Input.User.Left", new IEventHandler()
		{
			@Override
			public void handleEvent(Object o)
			{
				leftThrust(THRUST_POWER);
			}
		});

		game.registerEventHandler("Input.User.Right", new IEventHandler()
		{
			@Override
			public void handleEvent(Object o)
			{
				rightThrust(THRUST_POWER);
			}
		});

		game.registerEventHandler("Input.User.Up", new IEventHandler()
		{
			@Override
			public void handleEvent(Object o)
			{
				upThrust(THRUST_POWER);
			}
		});

		game.registerEventHandler("Input.User.Down", new IEventHandler()
		{
			@Override
			public void handleEvent(Object o)
			{
				downThrust(THRUST_POWER);
			}
		});
	}

	private void accelerateShip()
	{
		accelerate(GRAVITY, 90);
	}

	protected void leftThrust(double power)
	{
		hspeed -= power;
		setHorizontalSpeed(hspeed);
	}

	protected void rightThrust(double power)
	{
		hspeed += power;
		setHorizontalSpeed(hspeed);
	}

	protected void downThrust(double power)
	{
		accelerate(power, 90);
	}

	private void upThrust(double power)
	{
		// setVerticalSpeed(power);
		accelerate(power, -90);
	}

	public void explode()
	{
		loseLife();
		resetLander();
	}

	public boolean isSafe()
	{
		return safe;
	}

	public void setSafe(boolean s)
	{
		safe = s;
	}

	private void loseLife()
	{
		lives--;
		if (lives < 0)
		{
			game.finish();
		}
	}

	public void resetLander()
	{
		setX(initX - getWidth() / 2);
		setY(initY - getHeight());
		setVerticalSpeed(0);
		setHorizontalSpeed(0);
		safe = false;
	}
}
