package games.patterson_game.refactoredVooga.core.event.test;

import games.patterson_game.refactoredVooga.core.event.EventManager;
import games.patterson_game.refactoredVooga.core.event.IEventHandler;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;


public class EventManagerTest extends TestCase
{

    private static final long DEFAULT_TIMEOUT = 5; // seconds
    private EventManager myEventManager;
    private int stepsRemaining;


    @Before
    public void setUp () throws Exception
    {
        myEventManager = new EventManager();
        myEventManager.registerEventHandler("JUnit.TestComplete",
                                            new IEventHandler()
                                            {
                                                @Override
                                                public void handleEvent (Object o)
                                                {
                                                    setTestComplete(true);
                                                }
                                            });
    }


    @Test
    public final void testOneShotTimer ()
    {
        myEventManager.addTimer("TestTimer", 1, "JUnit.TestComplete"); // 1 second timer

        runToCompletion(5, true);
    }


    @Test
    public final void testPeriodicTimer ()
    {
        myEventManager.registerEventHandler("JUnit.TestPeriodicTimer",
                                            new IEventHandler()
                                            {
                                                @Override
                                                public void handleEvent (Object o)
                                                {
                                                    decrementStepsRemaining();
                                                }
                                            });
        myEventManager.addPeriodicTimer("TestPeriodicTimer", 1, "JUnit.TestPeriodicTimer"); // 1 second timer

        setStepsRemaining(2);

        runToCompletion(5, true);
    }


    private void setStepsRemaining (int steps)
    {
        stepsRemaining = steps;
    }


    protected void decrementStepsRemaining ()
    {
        stepsRemaining--;
    }


    private void runToCompletion ()
    {
        runToCompletion(DEFAULT_TIMEOUT, false);
    }


    private void runToCompletion (long timeout, boolean runSlowly)
    {
        setTestComplete(false);

        long timeA, timeB = System.currentTimeMillis();
        long timeOut = System.currentTimeMillis() + timeout * 1000;

        while (!isTestComplete())
        {
            timeA = timeB;
            timeB = System.currentTimeMillis();

            myEventManager.update(timeB - timeA);

            if (runSlowly)
            {
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

            if (System.currentTimeMillis() > timeOut) fail("Test took too long! " +
                                                           stepsRemaining +
                                                           " steps remaining in test case.");
        }
    }


    private boolean isTestComplete ()
    {
        return stepsRemaining == 0;
    }


    protected void setTestComplete (boolean isTestComplete)
    {
        if (isTestComplete) stepsRemaining = 0;
        else stepsRemaining = 1;
    }


    @Test
    public final void testFireEvent ()
    {
        myEventManager.fireEvent(this, "JUnit.TestComplete");

        runToCompletion();
    }


    @Test
    public final void testRegisterEventHandler ()
    {
        myEventManager.registerEventHandler("JUnit.Chain", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                myEventManager.fireEvent(this, "JUnit.TestComplete", o);
            }
        });

        myEventManager.fireEvent(this, "JUnit.Chain");

        runToCompletion();
    }

}
