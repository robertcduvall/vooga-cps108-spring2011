package games.blasterMan;

import games.blasterMan.sprites.PlayerType;

import java.io.Serializable;


@SuppressWarnings("serial")
public abstract class Skill implements Serializable{
	protected int castSpeed; 
	protected int skillID;
	public Skill(final int ID)
	{
		castSpeed = 1;
		skillID = ID;
	}
	
	public abstract void invoke(PlayerType player);
	
	public abstract void upgrade();
	
	@Override
	public boolean equals(Object o){
		return this.skillID == ((Skill)o).skillID;
	}
}
