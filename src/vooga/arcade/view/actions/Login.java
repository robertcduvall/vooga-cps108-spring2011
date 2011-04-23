package vooga.arcade.view.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import vooga.arcade.controller.ArcadeController;
import vooga.arcade.parser.ArcadeGameObject;
import vooga.user.controller.LoginController;

/**
 * 
 * @author Andrea
 *
 */
public class Login extends AbstractViewAction
{
    public Login (ArcadeController c)
    {
        super(c);
    }
    
    @Override
    public void actionPerformed(ActionEvent sv) {
        List<String> gameTitles = new ArrayList<String>();
        List<ArcadeGameObject> allGames = controller.queryModel("title", null);
        for (ArcadeGameObject game : allGames)
        {
            String currentTitle = game.getData("title");
            gameTitles.add(currentTitle);
        }
        
        LoginController start = new LoginController("VOOGA GAME LOGIN", "", 640, 480, gameTitles);  
        start.toString();
    }
}
