package replay;
import java.io.Serializable;

import com.golden.gamedev.object.*;


@SuppressWarnings("serial")
public class SerialPlayField extends PlayField implements Serializable{
	public SerialPlayField(){
		super();
	}
	public SerialPlayField(Background background){
		super(background);
	}
	public void setPlayField(PlayField field){
		this.clearPlayField();
		for(SpriteGroup sprites : field.getGroups()){
			this.addGroup(sprites);
		}
		for(Sprite s: field.getExtraGroup().getSprites()){
			if(s!= null)
				this.add(s);
		}
		this.setBackground(field.getBackground());
	}
}
