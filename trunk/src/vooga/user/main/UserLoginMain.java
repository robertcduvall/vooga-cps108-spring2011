package vooga.user.main;

import vooga.user.controller.LoginController;

/**
 * Compsci 108 Spring 2011 - Vooga Login Sequence
 * 
 * @author Conrad
 * 
 */
public class UserLoginMain
{
	public static void main(String[] args)
	{
		LoginController start = new LoginController(
				"VOOGA GAME ARCADE","", 640, 480);
		start.toString();
	}
}