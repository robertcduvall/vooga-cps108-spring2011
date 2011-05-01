package vooga.user.view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vooga.user.controller.ILoginController;
import vooga.user.view.gui.middleFrame.FieldPanel;


public class ViewInputButton extends AbstractLoginAction{

	FieldPanel panel;
	ILoginController pc;
	public ViewInputButton(ILoginController p, FieldPanel fieldPanel) {
		super(p);
		pc = p;
		panel = fieldPanel;
	}

	//@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent arg0) {
		pc.updateWithInformationPanel();
		//controller.removeFrame();
		//System.out.println("Should Display");
		}
	}

