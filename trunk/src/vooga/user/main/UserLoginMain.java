package vooga.user.main;

import java.util.ArrayList;
import java.util.List;

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
		List<String> gameUserReferences = new ArrayList<String>();
		LoginController start = new LoginController(
				"VOOGA GAME LOGIN","", 640, 480, gameUserReferences );
		start.toString();
	}
}