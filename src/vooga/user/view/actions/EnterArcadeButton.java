package vooga.user.view.actions;

import java.awt.event.ActionEvent;

import vooga.user.controller.ILoginController;
import vooga.user.view.gui.middleFrame.FieldPanel;


public class EnterArcadeButton extends AbstractSlogoAction{

	FieldPanel panel;
	ILoginController pc;
	public EnterArcadeButton(ILoginController p, FieldPanel fieldPanel) {
		super(p);
		pc = p;
		panel = fieldPanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Launch Arcade		
	}

}
