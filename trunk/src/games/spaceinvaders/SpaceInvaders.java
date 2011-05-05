package games.spaceinvaders;

import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import vooga.core.VoogaGame;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;
import com.golden.gamedev.*;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;

public class SpaceInvaders extends Game implements Commons
{
     { distribute = true; } 
     
//     public final BufferedImage ALIENPIX = getImage(ALIEN);
//     public final BufferedImage BOMBPIX = getImage(BOMB);
//     public final BufferedImage SHOTPIX = getImage(SHOT);
//     public final BufferedImage PLAYERPIX = getImage(PLAYER);
//     public final BufferedImage EXPLOSIONPIX = getImage(EXPLOSION);

     PlayField myPlayfield;
     SpriteGroup myPlayerGroup;
     SpriteGroup myEnemyGroup;
     Background myBackground;
     
     Player myPlayer;
     Shot myShot;

     //BombBallCollision myCollision;
     
     private boolean isGameOver = false;
     
     private int alienX = 150;
     private int alienY = 5;
     private int deaths = 0;
     private int direction = -1;

//     Stat<Integer> myLives;
//     Stat<Integer> myScore;

     @Override
     public void initResources() 
     {       
         myPlayfield = new PlayField();
         myPlayerGroup = new SpriteGroup("Player");
         myEnemyGroup = new SpriteGroup("Enemy");
         myBackground = new ImageBackground(getImage(BACKGROUND), 640, 480);
         
         myPlayfield.setBackground(myBackground);
         myPlayfield.addGroup(myPlayerGroup);
         myPlayfield.addGroup(myEnemyGroup);
         
         for (int i=0; i < 4; i++) {
             for (int j=0; j < 6; j++) {
                 Alien alien = new Alien(getImage(ALIEN), getImage(BOMB), alienX + 18*j, alienY + 18*i);
                 myEnemyGroup.add(alien);
             }
         }
         
         myPlayer = new Player(getImage(PLAYER));
         myShot = new Shot(getImage(SHOT));
         
         myPlayerGroup.add(myPlayer);
         myPlayerGroup.add(myShot);
         
         //TODO: add collision group to playfield
     }

//    public void render (Graphics2D g)
//    {
//        myPlayfield.render(g);
//        //TODO: draw "You Lose" when end game
//    }

    @Override
    public void update (long elapsedTime)
    {
        if (!isGameOver)
        {
            // playfield updates all the groups and check for collision
            myPlayfield.update(elapsedTime);
            
            // player
            myPlayer.act();

            // shot
            if (myShot.isVisible()) 
            {
                Sprite[] spritesArr = myEnemyGroup.getSprites();
                int shotX = (int) myShot.getX();
                int shotY = (int) myShot.getY();

                for (Sprite s : spritesArr) 
                {
                    Alien alien = (Alien) s;
                    int alienX = (int) alien.getX();
                    int alienY = (int) alien.getY();

                    if (alien.isVisible() && myShot.isVisible()) 
                    {
                        if (shotX >= (alienX) && 
                            shotX <= (alienX + ALIEN_WIDTH) &&
                            shotY >= (alienY) &&
                            shotY <= (alienY+ALIEN_HEIGHT) ) 
                        {
                            alien.setImage(getImage(EXPLOSION));
                            alien.setDying(true);
                            deaths++;
                            myShot.die();
                        }
                    }
                }

                int y = (int) myShot.getY();
                y -= 4;
                if (y < 0) myShot.die();
                else myShot.setY(y);
            }

            // aliens
            Sprite[] spritesArr = myEnemyGroup.getSprites();

            for (Sprite s1 : spritesArr) 
            {
                Alien a1 = (Alien) s1;
                int x = (int) a1.getX();

                if (x  >= BOARD_WIDTH - BORDER_RIGHT && direction != -1) 
                {
                    direction = -1;
                    for (Sprite s2 : spritesArr) 
                    {
                        Alien a2 = (Alien) s2;
                        a2.setY(a2.getY() + GO_DOWN);
                    }
                }

                if (x <= BORDER_LEFT && direction != 1) 
                {
                    direction = 1;
                    for (Sprite s3 : spritesArr) 
                    {
                        Alien a3 = (Alien) s3;
                        a3.setY(a3.getY() + GO_DOWN);
                    }
                }
            }

            for (Sprite s : spritesArr) 
            {
                Alien alien = (Alien) s;
                if (alien.isVisible()) 
                {
                    int y = (int) alien.getY();
                    if (y > GROUND - ALIEN_HEIGHT) 
                    {
                        isGameOver = true;
//                        message = "Invasion!"; //TODO: message of invasion
                    }
                    alien.act(direction);
                }
            }

            // bombs
            Random generator = new Random();

            for (Sprite s : spritesArr) 
            {
                int shot = generator.nextInt(15);
                Alien a = (Alien) s;
                Alien.Bomb b = a.getBomb();
                if (shot == CHANCE && a.isVisible() && b.isDestroyed()) 
                {
                    b.setDestroyed(false);
                    b.setX(a.getX());
                    b.setY(a.getY());   
                }

                int bombX = (int) b.getX();
                int bombY = (int) b.getY();
                int playerX = (int) myPlayer.getX();
                int playerY = (int) myPlayer.getY();

                if (myPlayer.isVisible() && !b.isDestroyed()) 
                {
                    if ( bombX >= (playerX) && 
                        bombX <= (playerX+PLAYER_WIDTH) &&
                        bombY >= (playerY) && 
                        bombY <= (playerY+PLAYER_HEIGHT) ) 
                    {
                            myPlayer.setImage(getImage(EXPLOSION));
                            myPlayer.setDying(true);
                            b.setDestroyed(true);;
                    }
                }

                if (!b.isDestroyed()) 
                {
                    b.setY(b.getY() + 1);   
                    if (b.getY() >= GROUND - BOMB_HEIGHT) 
                    {
                        b.setDestroyed(true);
                    }
                }
            }
        }       
    }
    
    public static void main(String[] args) 
    {  
        GameLoader game = new GameLoader();
        game.setup(new SpaceInvaders(), new Dimension(640, 480), true);
        game.start();  
    }

    @Override
    public void render (Graphics2D g)
    {
        myPlayfield.render(g);
    }
}