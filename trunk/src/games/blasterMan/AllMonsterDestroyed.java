package games.blasterMan;



import vooga.levels.IGoal;
import vooga.levels.LevelException;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;

public class AllMonsterDestroyed implements IGoal{
	 private VoogaPlayField myPlayField;
	 private LevelManager levels;
	 private long start;
	 private long end;
	 
	 public AllMonsterDestroyed(){
		 start = System.currentTimeMillis();
	 }
	@Override
	public boolean checkCompletion(LevelManager levelManager) {
		end = System.currentTimeMillis();
		return (end - start)/1000L == 60L;
	}

	@Override
	public void progress() {
		levels.updateNumOfLevelsCompleted();
		try
        {
            levels.loadNextLevel();
            System.out.println("Next Level!!!");
        }
        catch (LevelException e)
        {
            System.out.println("GOOD JOB!!!!!");
            System.exit(0);  
        }
		
	}

	@Override
	public void setupGoal(LevelManager manager, VoogaPlayField playfield) {
		this.myPlayField = playfield;
        this.levels = manager;
		
	}

}
