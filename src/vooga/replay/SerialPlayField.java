package vooga.replay;

import java.io.Serializable;

import com.golden.gamedev.object.*;

/**
 * Serializable PlayField Object. The State table will store the sprites in this PlayField.
 * 
 * @author Josue
 */

@SuppressWarnings("serial")
public class SerialPlayField extends PlayField implements Serializable {

	public SerialPlayField() {
		super();
	}

	public SerialPlayField(Background background) {
		super(background);
	}

	/**
	 * Sets current SerialPlayField to new PlayField Object by adding
	 * SpriteGroups and Background from parameter field.
	 * 
	 * @param field
	 *            - PlayField object that you are setting this SerialPlayField
	 *            to.
	 */
	public void setPlayField(PlayField field) {
		this.clearPlayField();
		for (SpriteGroup sprites : field.getGroups()) {
			this.addGroup(sprites);
		}
		for (Sprite s : field.getExtraGroup().getSprites()) {
			if (s != null)
				this.add(s);
		}
		this.setBackground(field.getBackground());
	}
}
