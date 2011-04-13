package vooga.core.event;

/**
 * @author Michael Ansel
 * @author Ethan Goh
 */
public interface ITimer extends Comparable<ITimer>
{
	String getName();

	void fireEvent();

	long getNextFireTime();

	boolean isFinished();

	boolean isReadyToFire();
}
