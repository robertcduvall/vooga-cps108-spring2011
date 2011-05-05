package games.lolcats.src.Helper;

import java.util.Comparator;

import com.golden.gamedev.object.Sprite;

public class SpriteLayerComparator implements Comparator<Sprite> {
	public int compare(Sprite emp1, Sprite emp2) {
		int layer1 = (emp1).getLayer();
		int layer2 = (emp2).getLayer();

		if (layer1 > layer2)
			return 1;
		else if (layer1 < layer2)
			return -1;
		else
			return 0;
	}
}