package vooga.user.view.gui.middleFrame;

import javax.swing.JLabel;

public class TextSection implements ISectionAddable {

	@Override
	public void addSection(String sectionTitle, String request,
			FieldPanel panel) {
			panel.add(new JLabel(request), "gap para, span, growx, wrap");		
	}
}
