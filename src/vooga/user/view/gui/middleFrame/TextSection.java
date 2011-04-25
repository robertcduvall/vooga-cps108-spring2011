package vooga.user.view.gui.middleFrame;

import javax.swing.JLabel;

public class TextSection implements ISectionAddable {

	@Override
	public void addSection(String sectionTitle, String[] sectionInformation,
			FieldPanel panel) {
		for(int j =0 ; j < sectionInformation.length; j++){
			panel.add(new JLabel(sectionInformation[j]), "gap para, span, growx, wrap");
		}
	}

}
