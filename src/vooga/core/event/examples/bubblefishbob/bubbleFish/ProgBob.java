package vooga.core.event.examples.bubblefishbob.bubbleFish;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import vooga.core.event.EventManager;
import vooga.core.event.IEventHandler;
import vooga.core.event.Timer;
import vooga.core.event.examples.bubblefishbob.bonuses.Bonus;
import vooga.core.event.examples.bubblefishbob.bonuses.BonusFactory;
import vooga.core.event.examples.bubblefishbob.bonuses.BonusPause;
import vooga.core.event.examples.bubblefishbob.bonuses.BonusRewind;
import vooga.core.event.examples.bubblefishbob.bonuses.BonusSmRocks;
import vooga.core.event.examples.bubblefishbob.bonuses.BonusTorpedo;


import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;


public class ProgBob
{
    final static int RAD_BUBBLE = 13;
    final static int W_SMFISH = 8;
    final static int H_SMFISH = 6;
    final static int GS_MAINMENU = 0;
    final static int GS_PLAY = 1;
    final static int GS_LEVEL_COMPLETED = 2;
    final static int GS_GAME_OVER = 3;
    final static int ITEM_FREE = 0;
    final static int ITEM_BUBBLE = 1;
    final static int ITEM_TORPEDO = 2;
    final static int ITEM_BONUS = 3;
    final static int ITEM_SMROCKS = 4;
    final static int NUM_BOB_STATES = 11;
    final static int MAX_BUBBLES = 128;
    final static double MAX_TIME_PAUSED = 6;
    final static double MAX_TIME_REWIND = 4;
    final static double PI_2 = 2 * Math.PI;
    private static final int LEVEL_COUNT = 5;
    private static final int EPISODE_COUNT = 5;

    Font fnt;
    Font fnt2;
    Font fnt3;
    Font fnts[];
    Sprite bm_bob[];
    Sprite bm_bubbles[];
    Sprite bm_evil_fish;
    Sprite bm_smfish;
    Sprite bm_torpedo;
    Sprite bm_smrock;
    Sprite bm_fisho;
    Sprite bm_click_to_continue;
    Sprite bm_congrats;
    Sprite bm_bg_menu;
    Sprite bm_bg_game;
    Sprite bm_lev_comp;
    Sprite bm_loading;
    Sprite bm_game_over;
    Sprite bm_part_bub[];
    String snd_level_start;
    String snd_explosion;
    String snd_shoot_bubble;
    String snd_plip_plop;
    String snd_pop;
    String snd_bob_loses;
    String snd_pick;
    String snd_swap;
    String snd_lev_comp;
    String snd_combo[];
    boolean torpedo;
    int smrocks;
    double name_show;
    int total_fish_saved;
    int longest_combo;
    double handicap;
    int game_state;
    boolean game_starting;
    boolean completed_the_game;
    double time_rewind;
    double time_paused;
    public boolean isTorpedo()
	{
		return torpedo;
	}

	public void setTorpedo(boolean torpedo)
	{
		this.torpedo = torpedo;
	}

	public int getSmrocks()
	{
		return smrocks;
	}

	public void setSmrocks(int smrocks)
	{
		this.smrocks = smrocks;
	}

	public double getTime_rewind()
	{
		return time_rewind;
	}

	public void setTime_rewind(double time_rewind)
	{
		this.time_rewind = time_rewind;
	}

	public double getTime_paused()
	{
		return time_paused;
	}

	public void setTime_paused(double time_paused)
	{
		this.time_paused = time_paused;
	}

	int num_bonuses;
    Bonus oldbonuses;
    Part parts;
    Item items;
    double path_t0;
    int fish_to_save;
    double path_last_t;
    Bubble bubbles[];
    Bubble first_bub;
    Bubble shot_bubble;
    Sprite next_bubble;
    Sprite next_bubble2;
    int bob_y;
    int bob_x;
    double bob_akey;
    double akey0;
    double akey1;
    double akey2;
    double akey3;
    double shoot_time;
    boolean game_over;
    double go_speedup;
    int level;
    boolean level_completed;
    double st_timer;
    int level_score;
    int total_score;
    int score_show;
    Game game;
    private Level myCurrentLevel;
    private EventManager myEventManager;
    private boolean pauseScreenVisible;
    private boolean levelNameVisible;
    private Timer levelNameDisplayTimer;


    boolean isSomeBonusActing ()
    {
        return time_rewind > 0 || time_paused > 0 || items != null || smrocks > 0 || torpedo;
    }


    public void init (Game game, EventManager eventManager)
    {
        this.game = game;
        myEventManager = eventManager;

        myEventManager.registerEventHandler("Input.Mouse.LeftClick",
                                            new IEventHandler()
                                            {
                                                @Override
                                                public void handleEvent (Object o)
                                                {
                                                    mouseClick();
                                                }
                                            });

        myEventManager.forwardEvent("Input.User.Pause", "User.Pause");
        myEventManager.registerEventHandler("User.Pause", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                pause();
            }
        });

        myEventManager.forwardEvent("Input.User.Swap", "User.SwapBubbles");
        myEventManager.registerEventHandler("User.SwapBubbles",
                                            new IEventHandler()
                                            {
                                                @Override
                                                public void handleEvent (Object o)
                                                {
                                                    swapBubbles();
                                                }
                                            });

        myEventManager.forwardEvent("Input.User.Cheat", "User.Cheat");
        myEventManager.registerEventHandler("User.Cheat", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                cheat();
            }
        });

        myEventManager.registerEventHandler("User.StartLevel",
                                            new IEventHandler()
                                            {
                                                @Override
                                                public void handleEvent (Object o)
                                                {
                                                    int levelToStart =
                                                        (Integer) o;
                                                    startLevel(levelToStart);
                                                }
                                            });

        myEventManager.registerEventHandler("User.StartGame",
                                            new IEventHandler()
                                            {
                                                @Override
                                                public void handleEvent (Object o)
                                                {
                                                    startGame();
                                                }
                                            });

        myEventManager.registerEventHandler("User.HideLevelName",
                                            new IEventHandler()
                                            {
                                                @Override
                                                public void handleEvent (Object o)
                                                {
                                                    levelNameVisible = false;
                                                }
                                            });

        fnt = Font.decode("Arial-BOLD-16");
        fnt2 = Font.decode("Arial-BOLD-20");
        fnt3 = Font.decode("Arial-PLAIN-14");
    
        fnts = new Font[11];
        for (int k = 0; k < 11; k++)
        {
            fnts[k] = Font.decode("Arial-PLAIN-" + (k + 2));
        }
        Part part = new Part(this, 10, 10, null, 0);
        for (int k = 0; k < 100; k++)
        {
            part.next = new Part(this, 10, 10, null, 0);
            part = part.next;
        }
        for (int k = 0; k < 100; k++)
        {
            part.next = new PartBub(this, 10, 10, null, 0);
            part = part.next;
        }
    
        bm_loading = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/loading.jpg"));
        bm_click_to_continue = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/click_to_continue.png"));
        bm_congrats = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/congrats.png"));
        bm_bob = new Sprite[NUM_BOB_STATES];
        bm_bob[0] = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/bob_0000.png"));
        bm_bob[1] = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/bob_0002.png"));
        bm_bob[2] = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/bob_0004.png"));
        bm_bob[3] = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/bob_0006.png"));
        bm_bob[4] = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/bob_0008.png"));
        bm_bob[5] = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/bob_0008.png"));
        bm_bob[6] = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/bob_0010.png"));
        bm_bob[7] = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/bob_0012.png"));
        bm_bob[8] = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/bob_0014.png"));
        bm_bob[9] = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/bob_0016.png"));
        bm_bob[10] = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/bob_0018.png"));
        bm_evil_fish = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/evil_fish.png"));
        bm_smfish = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/smfish.png"));
    
        snd_shoot_bubble = "vooga/core/event/examples/bubblefishbob/resources/release_bubble.au";
        snd_combo = new String[8];
        snd_combo[0] = "vooga/core/event/examples/bubblefishbob/resources/combo_01.au";
        snd_combo[1] = "vooga/core/event/examples/bubblefishbob/resources/combo_02.au";
        snd_combo[2] = "vooga/core/event/examples/bubblefishbob/resources/combo_03.au";
        snd_combo[3] = "vooga/core/event/examples/bubblefishbob/resources/combo_04.au";
        snd_combo[4] = "vooga/core/event/examples/bubblefishbob/resources/combo_05.au";
        snd_combo[5] = "vooga/core/event/examples/bubblefishbob/resources/combo_06.au";
        snd_combo[6] = "vooga/core/event/examples/bubblefishbob/resources/combo_07.au";
        snd_combo[7] = "vooga/core/event/examples/bubblefishbob/resources/combo_08.au";
        snd_plip_plop ="vooga/core/event/examples/bubblefishbob/resources/pop_01.au";
        snd_pop = "vooga/core/event/examples/bubblefishbob/resources/pop_01.au";
        snd_bob_loses = "vooga/core/event/examples/bubblefishbob/resources/bob_loses.au";
        snd_pick = "vooga/core/event/examples/bubblefishbob/resources/pickup.au";
        snd_swap = "vooga/core/event/examples/bubblefishbob/resources/gulp.au";
        snd_lev_comp = "vooga/core/event/examples/bubblefishbob/resources/lev_comp_song.au";
        snd_level_start = "vooga/core/event/examples/bubblefishbob/resources/level_start.au";
        snd_explosion = "vooga/core/event/examples/bubblefishbob/resources/explosion.au";
    
        bm_bubbles = new Sprite[7];
        bm_bubbles[0] = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/blue.png"));
        bm_bubbles[1] = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/red.png"));
        bm_bubbles[2] = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/green.png"));
        bm_bubbles[3] = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/orange.png"));
        bm_bubbles[4] = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/purple.png"));
        bm_bubbles[5] = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/cyan.png"));
        bm_bubbles[6] = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/white.png"));
        bm_bg_game = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/seagrass.jpg"));
        bm_bg_menu = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/bg_menu.jpg"));
        bm_lev_comp = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/lev_comp.png"));
        bm_game_over = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/game_over.png"));
        bm_part_bub = new Sprite[4];
        bm_part_bub[0] = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/part_bub_01.png"));
        bm_part_bub[1] = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/part_bub_02.png"));
        bm_part_bub[2] = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/part_bub_03.png"));
        bm_part_bub[3] = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/part_bub_04.png"));
        bm_torpedo = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/torpedo.png"));
        bm_smrock = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/smrock.png"));
        bm_fisho = new Sprite(game.getImage("vooga/core/event/examples/bubblefishbob/resources/fisho_full.png"));
    
        num_bonuses = 4;

        initGame();
        initLevel(1);
        gameOver();
        game_state = GS_MAINMENU;
    }

    void initGame ()
    {
        total_fish_saved = 0;
        total_score = 0;
        level_score = 0;
        shoot_time = 0;
        completed_the_game = false;
        game_over = false;
        pauseScreenVisible = false;
    }

    void initLevel (int level_num)
    {
        level_completed = false;
        handicap = 1;
        longest_combo = 0;
        torpedo = false;
        smrocks = 0;
        levelNameVisible = true;
        myEventManager.removeTimer("LevelNameDisplayTimer");
        myEventManager.addTimer("LevelNameDisplayTimer", 5, "User.HideLevelName");
        time_rewind = 0;
        time_paused = 0;
        items = null;
        game_starting = true;
        level_score = 0;
        go_speedup = 0;
        st_timer = 0;
        shot_bubble = new Bubble();
        shot_bubble.bm = null;
        bob_y = 290;
        bob_akey = 0;
        level = level_num;
        
        myCurrentLevel = LevelLoader.getInstance().load("level"+getCurrentSubLevel());
        fish_to_save = myCurrentLevel.getFishToSave();

        myCurrentLevel.setPathSpeed(myCurrentLevel.getPathSpeed() + (getCurrentEpisode()-1) * 0.08);
        path_t0 = 0;
        path_last_t = 0;
        for (int k = 0; k < myCurrentLevel.getPathLength()-1; k++)
        {
            path_last_t += myCurrentLevel.getPath().get(k).getDistTo(myCurrentLevel.getPath().get(k+1));
        }
        path_last_t /= 2 * RAD_BUBBLE;
        bubbles = new Bubble[MAX_BUBBLES];
        for (int k = 0; k < bubbles.length; k++)
        {
            bubbles[k] = new Bubble();
        }
        first_bub = new Bubble();
        first_bub.bm = getRandomBubble(true);
        first_bub.fish_inside = true;
        Point2D.Double pnt = getPathPoint(0);
        first_bub.x = pnt.x;
        first_bub.y = pnt.y;
        next_bubble = getRandomBubble(true);
        next_bubble2 = getRandomBubble(true);
    }

    private int getCurrentSubLevel ()
    {
        return (level - 1) % LEVEL_COUNT + 1;
    }

    private int getCurrentEpisode ()
    {
        return (level - 1) / LEVEL_COUNT + 1;
    }

    public void draw (Graphics2D context)
    {
        if (game_state == GS_MAINMENU)
        {
            bm_bg_menu.render(context);
        }
        else if (game_state == GS_LEVEL_COMPLETED)
        {
            bm_bg_game.render(context, 0, 0);
            bm_lev_comp.render(context, 90, 30);
            context.setColor(Color.WHITE);
            context.setFont(fnt);
            drawOutlined(context, 340, 151, "Fish Saved:");
            drawOutlined(context, 340, 168, "" + total_fish_saved);
            drawOutlined(context, 140, 151, "Max Combo:");
            drawOutlined(context, 140, 168, "" + longest_combo);
            drawOutlined(context, 240, 205, "Level Score:");
            drawOutlined(context, 240, 222, "" + level_score);
            context.setFont(fnt);
            context.setColor(Color.WHITE);
            bm_click_to_continue.render(context, 156, 290);
            drawParts(context);
            drawBar(context);
        }
        else if (game_state == GS_GAME_OVER)
        {
            bm_bg_game.render(context, 0, 0);
            if (completed_the_game)
            {
                context.setColor(Color.WHITE);
                context.setFont(fnt2);
                bm_congrats.render(context, 50, 30);
                context.setColor(Color.WHITE);
                context.setFont(fnt);
                drawOutlined(context, 340, 175, "Fish Saved:");
                drawOutlined(context, 340, 192, "" + total_fish_saved);
                drawOutlined(context, 140, 175, "Final Score:");
                drawOutlined(context, 140, 192, "" + total_score);
            }
            else
            {
                bm_game_over.render(context, 132, 30);
                context.setColor(Color.WHITE);
                context.setFont(fnt);
                drawOutlined(context, 240, 111, "Fish Saved:");
                drawOutlined(context, 240, 128, "" + total_fish_saved);
                drawOutlined(context, 240, 155, "Final Score:");
                drawOutlined(context, 240, 172, "" + total_score);
            }
            drawParts(context);
            drawBar(context);
            bm_click_to_continue.render(context, 156, 290);
        }
        else if (game_state == GS_PLAY)
        {
            bm_bg_game.render(context, 0, 0);
            drawPath(context);
            drawParts(context);
            drawItems(context);
            bob_x = game.getMouseX();
            int y_offset = (int)(5 * Math.sin(shoot_time * Math.PI));
            if (torpedo)
            {
                bm_torpedo.setX(bob_x - bm_torpedo.getWidth() / 2);
                bm_torpedo.setY(bob_y - bm_torpedo.getHeight() / 2 + y_offset);
                bm_torpedo.render(context);
            }
            else if (smrocks > 0)
            {
                bm_smrock.setX(bob_x - bm_torpedo.getWidth() / 2);
                bm_smrock.setY(bob_y - bm_torpedo.getHeight() / 2 + y_offset);
                bm_smrock.render(context);
            }
            else
            {
                if (shoot_time >= 1)
                {
                    next_bubble.setX(bob_x - next_bubble.getWidth() / 2);
                    next_bubble.setY(bob_y - next_bubble.getHeight() + y_offset);
                    next_bubble.render(context);
                }
                else
                {
                    int x_offset = (int)(shoot_time * 2 * RAD_BUBBLE);
                    next_bubble.setX(bob_x - x_offset / 2);
                    next_bubble.setY((bob_y + (1 - shoot_time) * 4 * RAD_BUBBLE) - next_bubble.getHeight() + y_offset);
                    next_bubble.render(context);
                }
                // BUGBUG: change size to 16x16
                next_bubble2.render(context,  
                                    (int)((bob_x - 16 / 2) + 1), 
                                    (int)(((bob_y - 4) + y_offset) + (1 - shoot_time) * 16));
            }
            bm_bob[(int)bob_akey].render(context, bob_x - 22, bob_y + y_offset);
            drawBar(context);
            if (levelNameVisible && !pauseScreenVisible)
            {
                context.setFont(fnt2);
                context.setColor(Color.BLACK);
                drawOutlined(context, 240, 170, "Level " + getCurrentEpisode() + "-" + getCurrentSubLevel());
            }
        }
        if (pauseScreenVisible)
        {
            context.setFont(fnt2);
            context.setColor(Color.BLACK);
            drawOutlined(context, 240, 170, "Paused");
            context.setFont(fnt);
            context.setColor(Color.BLACK);
            context.drawString("Press space to continue", 240, 190);
        }
    }

    void drawParts (Graphics2D context)
    {
        for (Part part = parts; part != null; part = part.next)
        {
            if (part.life < 0.999)
            {
                part.draw(context);
            }
        }
    }

    void drawPath (Graphics2D context)
    {
        int x = 0;
        int y = 0;
        double t = 0;
        for (Bubble bubble = first_bub; bubble != null; bubble = bubble.next)
        {
            if (path_t0 + bubble.t < path_last_t - 1)
            {
                if (bubble.fish_inside)
                {
                    bm_smfish.setX(bubble.x - 8);
                    bm_smfish.setY(bubble.y - 6 + 2 * Math.sin(bubble.phase * PI_2));
                    bm_smfish.render(context);
                }
                double x_scale = 1 - bubble.trans;
                x = (int)(bubble.trans * bubble.attach_x + x_scale * bubble.x);
                if (path_t0 + bubble.t >= path_last_t - 4)
                {
                    double y_scale = ((path_t0 + bubble.t) - (path_last_t - 4)) / 4;
                    y = (int)((bubble.trans * bubble.attach_y + x_scale * bubble.y) + 3 * Math.sin(bubble.phase * PI_2) * (1 - y_scale) + 4 * Math.sin(PI_2 * akey2) * y_scale);
                }
                else
                {
                    y = (int)(bubble.trans * bubble.attach_y + x_scale * bubble.y + 3 * Math.sin(bubble.phase * PI_2));
                }
                bubble.bm.render(context, x - RAD_BUBBLE, y - RAD_BUBBLE);
            }
            t = bubble.t;
        }
    
        t += path_t0 + 1 + st_timer * (path_t0 + path_last_t - t);
        for (double t1 = Math.floor(t); t1 < path_last_t; t1 += 0.5)
        {
            Point2D.Double pnt = getPathPoint(t1);
            y = (int)(pnt.y + 3 * Math.sin(PI_2 * akey1 + t1));
            bm_part_bub[3].render(context, (int)pnt.x - 2, y - 2);
        }
    
        x = (int)(myCurrentLevel.getPath().get(myCurrentLevel.getPathLength() - 1).x - 55 + (1 - Math.sin(0.5 * Math.PI + 0.5 * Math.PI * st_timer)) * (480 - x));
        y = (int)myCurrentLevel.getPath().get(myCurrentLevel.getPathLength() - 1).y;
        bm_evil_fish.render(context, x, (y + (int)(4 * Math.sin(PI_2 * akey2))) - 40);
    }

    void drawItems (Graphics2D context)
    {
        for (Item item = items; item != null; item = item.next)
        {
            int x = (int)(item.getX() + 3 * Math.sin(PI_2 * akey1));
            int y = (int)item.getY();
            if (4 * item.time_existed < 1 && item.vel_y > 0)
            {
                double t = 4 * item.time_existed;
                int w = (int)(item.bm.getWidth() * t);
                int h = (int)(item.bm.getHeight() * t);
                // BUGBUG: change size
                item.bm.render(context, x - w / 2, y - h / 2);
            }
            else
            {
                item.bm.render(context, x, y);
            }
        }
        if (shot_bubble.bm != null)
        {
            shot_bubble.bm.render(context, (int)shot_bubble.x, (int)shot_bubble.y);
        }
    }

    void drawBar (Graphics2D context)
    {
        int w = bm_fisho.getWidth();
        int h = (int)(w * ((myCurrentLevel.getFishToSave() - fish_to_save) / myCurrentLevel.getFishToSave()));
        // BUGBUG: change size
        bm_fisho.render(context, 276, 342);
        context.setColor(Color.WHITE);
        context.setFont(fnt);
        context.drawString(scoreString(), 63, 354);
        context.drawString(getCurrentEpisode() + " - " + getCurrentSubLevel(), 190, 354);
    }

    void drawOutlined (Graphics2D context, int x, int y, String text)
    {
        // BUGBUG: center
        context.setColor(Color.BLACK);
        context.drawString(text, x - 2, y - 2);
        context.drawString(text, x - 2, y + 2);
        context.drawString(text, x + 2, y + 2);
        context.drawString(text, x + 2, y - 2);
        context.drawString(text, x - 2, y);
        context.drawString(text, x, y + 2);
        context.drawString(text, x + 2, y);
        context.drawString(text, x, y - 2);
        context.setColor(new Color(0xECD300));
        context.drawString(text, x, y);
    }

    public void update (double elapsedTime)
    {
        if (game_state != GS_PLAY)
        {
            return;
        }
        if (!level_completed && !game_over && !existsInPath(next_bubble))
        {
            next_bubble = getRandomBubble(false);            
        }
        if (!level_completed && !game_over && !existsInPath(next_bubble2))
        {
            next_bubble2 = getRandomBubble(false);            
        }
        akey0 = (akey0 + elapsedTime) % 1.0;
        akey1 = (akey1 + 0.7 * elapsedTime) % 1.0;
        akey2 = (akey2 + 0.5 * elapsedTime) % 1.0;
        akey3 = (akey3 + 0.3 * elapsedTime) % 1.0;
        bob_akey = (bob_akey + 2 * elapsedTime) % NUM_BOB_STATES;
        time_paused = Math.max(0, time_paused - elapsedTime);
        time_rewind = Math.max(0, time_rewind - elapsedTime);
        shoot_time = Math.min(1, shoot_time + 3 * elapsedTime);
        score_show += Math.min(4, (int)(5 * elapsedTime * (total_score - score_show)));
        score_show = Math.min(score_show, total_score);
        updateParts(elapsedTime);
        updateItems(elapsedTime);
        updateBubbles(elapsedTime);
    }

    void updateParts (double time)
    {
        Part dead = null;
        for (Part part = parts; part != null; part = part.next)
        {
            part.life -= part.speed * time;
            if (part.life <= 0.001)
            {
                if (dead == null)
                {
                    parts = part.next;
                }
                else
                {
                    dead.next = part.next;
                }
            }
            else if (part.life < 0.999)
            {
                part.x += time * part.vx;
                part.y += time * part.vy;
                dead = part;
            }
        }
    }

    void updateItems (double time)
    {
        Item dead = null;
        for (Item item = items; item != null; item = item.next)
        {
            //item.y += time * item.vel_y;
            item.setY(time * item.vel_y + item.getY());
            if (item.getY() < -21 || item.getY() > 380 || item.type == ITEM_FREE)
            {
                if (dead == null)
                {
                    items = items.next;
                }
                else
                {
                    dead.next = item.next;
                }
            }
            else
            {
                item.time_existed += time;
                if (item.type == ITEM_TORPEDO || item.type == ITEM_SMROCKS)
                {
                    if (item.type == ITEM_TORPEDO)
                    {
                        item.vel_y -= 400 * time;
                    }
                    for ( ; Math.abs(item.getY() - item.py) > 4; item.py -= 4)
                    {
                        PartBub partbub = new PartBub(this, item.getX(), item.py, bm_part_bub[0], randDouble(1, 2.2));
                        partbub.vx = randDouble(-10, 10);
                        partbub.vy = randDouble(-50, -10);
                        partbub.x += randDouble(-5, 5);
                        addPart(partbub);
                    }
                    for (Bubble bubble = first_bub; bubble != null; bubble = bubble.next)
                    {
                        double dx = item.getX() - bubble.x;
                        double dy = item.getY() - bubble.y;
                        if (dx * dx + dy * dy < RAD_BUBBLE * RAD_BUBBLE)
                        {
                            if (item.type == ITEM_TORPEDO)
                            {
                                for (Bubble bubble1 = first_bub; bubble1 != null; bubble1 = bubble1.next)
                                {
                                    spawnBiggerBurst(item.getX(), item.getY());
                                    double ddx = item.getX() - bubble1.x;
                                    double ddy = item.getY() - bubble1.y;
                                    if (ddx * ddx + ddy * ddy < 4096 && path_t0 + bubble1.t > 0)
                                    {
                                        spawnBurst(bubble1.x, bubble1.y);
                                        if (bubble1.fish_inside)
                                        {
                                            fish_to_save--;
                                            total_fish_saved++;
                                            Part part = new Part(this, bubble1.x, bubble1.y, bm_smfish, 0.4);
                                            part.vx = randDouble(-180, -140);
                                            part.vy = randDouble(-20, 20);
                                            addPart(part);
                                        }
                                        if (bubble1.next != null)
                                        {
                                            bubble1.next.shot = true;
                                            bubble1.next.prev = bubble1.prev;
                                        }
                                        if (bubble1.prev != null)
                                        {
                                            bubble1.prev.next = bubble1.next;
                                        }
                                        else
                                        {
                                            first_bub = bubble1.next;
                                        }
                                    }
                                }
                                game.playSound(snd_explosion);
                                shoot_time = 0.5;
                            }
                            else if (item.type == ITEM_SMROCKS)
                            {
                                spawnBurst(bubble.x, bubble.y);
                                if (bubble.fish_inside)
                                {
                                    fish_to_save--;
                                    total_fish_saved++;
                                    Part part = new Part(this, bubble.x, bubble.y, bm_smfish, 0.4);
                                    part.vx = randDouble(-180, -140);
                                    part.vy = randDouble(-20, 20);
                                    addPart(part);
                                }
                                if (bubble.next != null)
                                {
                                    bubble.next.shot = true;
                                    bubble.next.prev = bubble.prev;
                                }
                                if (bubble.prev != null)
                                {
                                    bubble.prev.next = bubble.next;
                                }
                                else
                                {
                                    first_bub = bubble.next;
                                }
                                game.playSound(snd_pop);
                            }
                            item.type = ITEM_FREE;
                            if (first_bub == null)
                            {
                                if (fish_to_save <= 0)
                                {
                                    level_completed = true;
                                }
                                else
                                {
                                    first_bub = new Bubble();
                                    first_bub.t = -path_t0 - 1;
                                    first_bub.bm = getRandomBubble(true);
                                    first_bub.fish_inside = true;
                                }
                            }
                        }
                    }
                }
                else
                {
                    for ( ; Math.abs(item.getY() - item.py) > 4; item.py += 4)
                    {
                        PartBub partbub = new PartBub(this, item.getX(), item.py, bm_part_bub[0], randDouble(1, 2.2));
                        partbub.vx = randDouble(-5, 5);
                        partbub.vy = randDouble(-50, -10);
                        partbub.x += randDouble(-7, 7);
                        addPart(partbub);
                    }
                    if (item.getBonus() != null && Math.abs(item.getX() - bob_x) < 25 && Math.abs(item.getY() - (bob_y + 20)) < 38)
                    {
                        spawnBurst(item.getX(), item.getY());
                        game.playSound(snd_pick);
                        PartString partstring = new PartString(this, item.getX(), item.getY(), item.getBonus().getName(), 0.6);
                        partstring.vx = 0;
                        partstring.vy = -30;
                        addPart(partstring);
                        shoot_time = 0.5;
                        item.getBonus().applyBonus();
                        if (dead == null)
                        {
                            items = items.next;
                        }
                        else
                        {
                            dead.next = item.next;
                        }
                    }
                }
                dead = item;
            }
        }
    }

    void updateBubbles (double elapsedTime)
    {
        double acc = myCurrentLevel.getPathSpeed() * 1.5;
        Bubble bubble = getLastBubble();
        if (bubble != null)
        {
            if (game_starting)
            {
                double t = path_t0 + bubble.t;
                if (t < myCurrentLevel.getStartT() - 8)
                {
                    acc *= 25;
                }
                else if (t < myCurrentLevel.getStartT())
                {
                    acc *= 1 + (24 * (myCurrentLevel.getStartT() - t)) / 8;
                }
                else
                {
                    game_starting = false;
                }
            }
            double t = (path_t0 + bubble.t) / path_last_t;
            if (time_paused <= 0)
            {
                if (t < 0.7)
                {
                    if (t > 0.1)
                    {
                        handicap += 0.1 * (0.7 - t) * elapsedTime;
                    }
                    else
                    {
                        handicap += 0.06 * elapsedTime;
                    }
                }
                else if (t > 0.7)
                {
                    handicap -= 0.15 * (t - 0.7) * elapsedTime;
                }
            }
            handicap = Math.max(0.95, Math.min(handicap, 4));
            acc *= handicap;
            if (t < 0.4)
            {
                t = 1 + 15 * (0.4 - t);
            }
            else if (t > 0.8)
            {
                if (t > 0.95)
                {
                    t = 0.95;
                }
                t = 2.5 * (1 - t);
                if (t < 0.15)
                {
                    t = 0.15;
                }
            }
            else
            {
                t = 0.5 + 0.5 * (1 - (t - 0.4) / 0.4);
            }
            acc *= t;
        }
        acc = Math.max(0.2, acc);
        if (time_paused > 0)
        {
            double factor = 1;
            if (time_paused > 5)
            {
                factor = 1 - (MAX_TIME_PAUSED - time_paused);
            }
            else if (time_paused > 1)
            {
                factor = 0;
            }
            else if (time_paused > 0)
            {
                factor = 1 - time_paused;
            }
            acc *= factor;
        }
        if (time_rewind > 0)
        {
            double factor = 0;
            if (time_rewind > 3)
            {
                factor = 4 - time_rewind;
            }
            else if (time_rewind > 1)
            {
                factor = 1;
            }
            else if (time_rewind > 0)
            {
                factor = 1 * time_rewind;
            }
            acc = acc * (1 - factor) - 3 * factor;
        }
        if (game_over)
        {
            acc += go_speedup;
            go_speedup += 32 * elapsedTime;
        }
        else
        {
            acc = Math.max(-12, Math.min(acc, 12));
        }
        acc *= elapsedTime;
        if (bubble != null && (path_t0 + bubble.t > 0 || acc > 0))
        {
            path_t0 += acc;
        }
        if (game_over)
        {
            if (first_bub == null)
            {
                st_timer += elapsedTime;
            }
            if (st_timer > 1)
                gameOver();
        }
        if (level_completed)
        {
            st_timer += elapsedTime;
            if (st_timer > 1) completeLevel();
            return;
        }
        double shot_y = shot_bubble.y;
        shot_bubble.y -= 620 * elapsedTime;
        if (shot_bubble.bm != null)
        {
            for (double part_y = shot_bubble.y; part_y < shot_y; part_y += 5)
            {
                PartBub partbub = new PartBub(this, shot_bubble.x, part_y, bm_part_bub[0], randDouble(3, 4.2));
                partbub.vx = randDouble(-10, 10);
                partbub.vy = randDouble(-40, 0);
                partbub.x += randDouble(-7, 7);
                addPart(partbub);
            }
        }
        if (shot_bubble.y < -2 * RAD_BUBBLE)
        {
            shot_bubble.bm = null;
        }
        Bubble bubble1 = first_bub;
        while (!game_over && fish_to_save > 0 && first_bub.x > -2 * RAD_BUBBLE) 
        {
            bubble1 = new Bubble();
            bubble1.phase = first_bub.phase - 0.1;
            bubble1.fish_inside = true;
            bubble1.shot = false;
            first_bub.prev = bubble1;
            bubble1.next = first_bub;
            bubble1.t = first_bub.t - 1;
            bubble1.bm = getRandomBubble(true);
            first_bub = bubble1;
            updateBubble(first_bub);
        }
        for ( ; bubble1 != null; bubble1 = bubble1.next)
        {
            if (path_t0 + bubble1.t >= path_last_t)
            {
                if (!game_over)
                {
                    game.playSound(snd_bob_loses);
                }
                game_over = true;
                if (bubble1.prev != null)
                {
                    bubble1.prev.next = null;
                }
                if (bubble1 == first_bub)
                {
                    first_bub = null;
                    return;
                }
            }
            bubble1.phase += (elapsedTime % 1.0);
            bubble1.trans = Math.max(0, bubble1.trans - 4 * elapsedTime);
            updateBubble(bubble1);
            if (shot_bubble.bm != null && 
                shot_bubble.y - 2 * RAD_BUBBLE <= bubble1.y + 8 && 
                shot_y + 2 * RAD_BUBBLE + 10 > bubble1.y + 8 && 
                Math.abs(shot_bubble.x - bubble1.x) < 15)
            {
                Bubble bubble2 = new Bubble();
                bubble2.bm = shot_bubble.bm;
                bubble2.shot = true;
                bubble2.trans = 1;
                bubble2.attach_x = shot_bubble.x;
                bubble2.attach_y = shot_bubble.y;
                double f10 = (bubble1.prev != null) ? Math.min(bubble1.prev.x, bubble1.x) : bubble1.x - RAD_BUBBLE;
                double f11 = (bubble1.prev != null) ? Math.max(bubble1.prev.x, bubble1.x) : bubble1.x;
                double f12 = (bubble1.prev != null) ? bubble1.prev.x - bubble1.x : (bubble1.next != null) ? bubble1.x - bubble1.next.x : 1;
                double f15 = (bubble1.prev != null) ? bubble1.prev.y - bubble1.y : (bubble1.next != null) ? bubble1.y - bubble1.next.y : 0;
                double f16 = Math.sqrt(f12 * f12 + f15 * f15);
                f15 /= f16;
                boolean flag2 = true;
                if (Math.abs(f15) > 0.4)
                {
                    flag2 = (f15 < 0);
                }
                else
                {
                    flag2 = ! ((bubble1.prev != null && shot_bubble.x > f10 || bubble1.prev == null) && shot_bubble.x < f11);
                }
                if (!flag2)
                {
                    bubble2.next = bubble1;
                    bubble2.prev = bubble1.prev;
                    bubble2.t = bubble1.t - 0.5;
                    if (bubble1.prev != null)
                    {
                        bubble1.prev.next = bubble2;
                    }
                    else
                    {
                        first_bub = bubble2;
                    }
                    bubble1.prev = bubble2;
                }
                else
                {
                    bubble2.prev = bubble1;
                    bubble2.next = bubble1.next;
                    bubble2.t = bubble1.t + 0.5;
                    if (bubble1.next != null)
                    {
                        bubble1.next.prev = bubble2;
                    }
                    bubble1.next = bubble2;
                }
                shot_bubble.bm = null;
            }
            if (bubble1.next != null)
            {
                int i = 1;
                boolean flag = bubble1.shot;
                if (bubble1.prev == null || bubble1.prev.bm != bubble1.bm)
                {
                    for (Bubble bubble3 = bubble1.next; bubble3 != null && bubble3.bm == bubble1.bm; bubble3 = bubble3.next)
                    {
                        if (bubble3.t - (i + bubble1.t) > 0.01)
                        {
                            i = 0;
                            break;
                        }
                        if (bubble3.shot)
                        {
                            flag = true;
                        }
                        i++;
                    }
                }
                if (flag && i >= 3)
                {
                    game.playSound(snd_plip_plop);
                    level_score += i * 50;
                    total_score += i * 50;
                    if (!isSomeBonusActing() && (randInt(16) == 4 || i >= 4 && randInt(10) <= i))
                    {
                        double f13 = (path_t0 + bubble.t) / path_last_t;
                        if(f13 > 0.4)
                        {
                            Item item = new Item(3, bubble1.x,bubble1.y,0,70,0,BonusFactory.generateBonus(this));
                            if(items == null)
                            {
                                items = item;
                            } else
                            {
                                item.next = items.next;
                                items.next = item;
                            }
                        }
                    }
                    boolean flag1 = false;
                    int j = 1;
                    double curr_x = 0;
                    double curr_y = 0;
                    for (int k = i; k > 0; k--)
                    {
                        curr_x += bubble1.x;
                        curr_y += bubble1.y;
                        j += bubble1.combo;
                        if (bubble1.fish_inside)
                        {
                            total_fish_saved++;
                            fish_to_save--;
                            Part part = new Part(this, bubble1.x, bubble1.y, bm_smfish, 0.4);
                            part.vx = randDouble(-180, -140);
                            part.vy = randDouble(-20, 20);
                            addPart(part);
                        }
                        spawnBurst(bubble1.x, bubble1.y);
                        if (bubble1.next != null)
                        {
                            bubble1.next.prev = bubble1.prev;
                        }
                        if (bubble1.prev != null)
                        {
                            bubble1.prev.next = bubble1.next;
                        }
                        if (bubble1 == first_bub)
                        {
                            first_bub = bubble1.next;
                        }
                        if (bubble1.next != null)
                        {
                            bubble1 = bubble1.next;
                        }
                        else
                        {
                            flag1 = true;
                            bubble1 = bubble1.prev;
                        }
                    }
    
                    curr_x /= i;
                    curr_y /= i;
                    if (bubble1 != null && adjIsGoingToBurst(bubble1))
                    {
                        bubble1.combo = j;
                    }
                    if (j > 1)
                    {
                        PartString partstring = new PartString(this, curr_x, curr_y, "Combo " + j + "x", 0.6);
                        partstring.vx = 0;
                        partstring.vy = -30;
                        addPart(partstring);
                    }
                    if (longest_combo < j)
                    {
                        longest_combo = j;
                    }
                    if (j > 0)
                    {
                        j--;
                    }
                    if (j > 7)
                    {
                        j = 7;
                    }
                    game.playSound(snd_combo[j]);
                    Bubble bubble6 = bubble1;
                    if (bubble6 != null && bubble6.prev != null && bubble6.bm == bubble6.prev.bm && !flag1)
                    {
                        bubble6.shot = true;
                    }
                }
                if (bubble1 == null)
                {
                    if (fish_to_save <= 0)
                    {
                        level_completed = true;
                    }
                    if (level_completed)
                    {
                        first_bub = null;
                        return;
                    }
                    else
                    {
                        first_bub = bubble1 = new Bubble();
                        bubble1.bm = getRandomBubble(false);
                        bubble1.t = -path_t0;
                        updateBubble(bubble1);
                        return;
                    }
                }
                if (bubble1.next == null)
                {
                    return;
                }
                double f14 = Math.abs(bubble1.next.t - bubble1.t);
                if (f14 < 0.99)
                {
                    for (Bubble bubble4 = bubble1.next; bubble4 != null; bubble4 = bubble4.next)
                    {
                        double f18 = 6 * (1 - f14) * elapsedTime;
                        if (f18 > f14)
                            f18 = f14;
                        bubble4.t += f18;
                    }
                }
                if (f14 > 1.01)
                {
                    for(Bubble bubble5 = bubble1.next; bubble5 != null; bubble5 = bubble5.next)
                    {
                        double f19 = 2 * f14 * elapsedTime;
                        if (f19 > 0.15)
                        {
                            f19 = 0.15;
                        }
                        if (f19 > f14)
                        {
                            f19 = f14;
                        }
                        bubble5.t -= f19;
                    }
                }
            }
            if (bubble1.combo > 0 && !adjIsGoingToBurst(bubble1))
            {
                bubble1.combo = 0;
            }
        }
    }

    private void gameOver ()
    {
        game_state = GS_GAME_OVER;
        
        myEventManager.pushFilter();
        myEventManager.removeEventHandlers("Input.*");
        IEventHandler progbobHandler = myEventManager.removeEventHandler("EveryTurn.User.ProgBobUpdate");
        myEventManager.removeEventHandlers("User.*");
        myEventManager.removeEventHandlers("*.User.*");
        myEventManager.registerEventHandler("EveryTurn.User.ProgBobUpdate", progbobHandler);
        myEventManager.registerEventHandler("Input.Mouse.LeftClick", new IEventHandler(){
            @Override
            public void handleEvent (Object o)
            {
                myEventManager.popFilter();
                myEventManager.fireEvent(this, "User.StartGame");
            }
        });
    }


    private void completeLevel ()
    {
        if (game_state != GS_LEVEL_COMPLETED)
        {
            game.playSound(snd_lev_comp);
        }
        game_state = GS_LEVEL_COMPLETED;
        
        myEventManager.pushFilter();
        myEventManager.removeEventHandlers("Input.*");
        myEventManager.removeEventHandlers("User.*");
        myEventManager.removeEventHandlers("*.User.*");
        myEventManager.registerEventHandler("Input.Mouse.LeftClick", new IEventHandler(){
            @Override
            public void handleEvent (Object o)
            {
                myEventManager.popFilter();
                myEventManager.fireEvent(this, "User.StartLevel", level + 1);
            }
        });
    }

    void updateBubble (Bubble bubble)
    {
        Point2D.Double tp = getPathPoint(path_t0 + bubble.t);
        bubble.x = tp.x;
        bubble.y = tp.y;
    }
    
    public void pause()
    {
        game.playSound(snd_pick);
        pauseScreenVisible = true;
        
        myEventManager.pushFilter();
        IEventHandler pauseInputHandler = myEventManager.removeEventHandler("Input.User.Pause");
        myEventManager.removeEventHandlers("Input.*");
        myEventManager.removeEventHandlers("User.*");
        myEventManager.removeEventHandlers("*.User.*");
        
        myEventManager.registerEventHandler("Input.User.Pause", pauseInputHandler);
        myEventManager.registerEventHandler("User.Pause", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                unpause();
            }
        });
    }
    
    public void unpause()
    {
        pauseScreenVisible = false;
        myEventManager.popFilter();
    }
    
    public void swapBubbles()
    {
        game.playSound(snd_swap);
        Sprite next = next_bubble;
        next_bubble = next_bubble2;
        next_bubble2 = next;
        shoot_time = 0.5;
    }
    
    public void cheat()
    {
        fish_to_save = 0;
        torpedo = true;
    }
    
    public void startLevel(int levelToStart)
    {
        game.playSound(snd_level_start);
        game_state = GS_PLAY;
        game_over = false;
        if (levelToStart > LEVEL_COUNT * EPISODE_COUNT)
        {
            completed_the_game = true;
            gameOver();
        }
        initLevel(levelToStart);
    }
    
    public void startGame()
    {
        initGame();
        myEventManager.fireEvent(this, "User.StartLevel", 1);
    }
    
    public void mouseClick()
    {
        if (game_state == GS_PLAY)
        {
            if (smrocks > 0)
            {
                game.playSound(snd_shoot_bubble);
                smrocks--;
                Item item = new Item(ITEM_SMROCKS,game.getMouseX(),bob_y,bob_y,-360,0,bm_smrock);
                if (items == null)
                {
                    items = item;
                }
                else
                {
                    item.next = items.next;
                    items.next = item;
                }
                shoot_time = 0.5;
                PartString partstring = new PartString(this, item.getX(), item.getY(), "" + smrocks, 0.6);
                partstring.vx = 0;
                partstring.vy = -30;
                addPart(partstring);
            }
            else if (torpedo)
            {
                torpedo = false;
                game.playSound(snd_shoot_bubble);
                Item item = new Item(ITEM_TORPEDO,game.getMouseX(),bob_y,bob_y,-120,0,bm_torpedo);

                if (items == null)
                {
                    items = item;
                }
                else
                {
                    item.next = items.next;
                    items.next = item;
                }
                shoot_time = 0.5;
            }
            else if (shot_bubble.bm != null)
            {
                return;
            }
            else if (shoot_time < 0.8)
            {
                return;
            }
            else
            {
                game.playSound(snd_shoot_bubble);
                shot_bubble.bm = next_bubble;
                next_bubble = next_bubble2;
                next_bubble2 = getRandomBubble(false);
                shot_bubble.x = game.getMouseX();
                shot_bubble.y = bob_y - 10;
                shoot_time = 0;
            }
        }
    }

    public void handleInput ()
    {
        if (game.keyPressed(KeyEvent.VK_SPACE))
            myEventManager.fireEvent(this, "Input.User.Pause");
        
        if (game.click())
            myEventManager.fireEvent(this, "Input.Mouse.LeftClick");

        if (game.rightClick())
            myEventManager.fireEvent(this, "Input.User.Swap");
        
        if(game.bsInput.isMousePressed(MouseEvent.BUTTON2))
            myEventManager.fireEvent(this, "Input.User.Cheat");
        
        if(game.bsInput.isKeyDown(KeyEvent.VK_T))
            myEventManager.fireEvent(this, "Input.User.Cheat");
    }

    void addPart (Part part)
    {
        if (parts == null)
        {
            parts = part;
        } 
        else
        {
            part.next = parts;
            parts = part;
        }
    }


    Point2D.Double getPathPoint (double idx)
    {
        if (idx < 0)
        {
            return new Point2D.Double(-30, -400);
        }
        double idx_dist = idx * 2 * RAD_BUBBLE;
        double curr_dist = 0;
        for (int i = 0; i < myCurrentLevel.getPathLength(); i++)
        {
            double next_dist =
                curr_dist +
                        (i + 1 < myCurrentLevel.getPathLength() ? myCurrentLevel.getPath()
                                                                                .get(i)
                                                                                .getDistTo(myCurrentLevel.getPath()
                                                                                                         .get(i + 1))
                                                               : 0);
            if (idx_dist < next_dist)
            {
                double curr_idx =
                    (idx_dist - curr_dist) /
                            myCurrentLevel.getPath()
                                          .get(i)
                                          .getDistTo(myCurrentLevel.getPath()
                                                                   .get(i + 1));
                return new Point2D.Double(myCurrentLevel.getPath().get(i).x +
                                                  (myCurrentLevel.getPath()
                                                                 .get(i + 1).x - myCurrentLevel.getPath()
                                                                                               .get(i).x) *
                                                  curr_idx,
                                          myCurrentLevel.getPath().get(i).y +
                                                  (myCurrentLevel.getPath()
                                                                 .get(i + 1).y - myCurrentLevel.getPath()
                                                                                               .get(i).y) *
                                                  curr_idx);
            }
            curr_dist = next_dist;
        }
        return new Point2D.Double(myCurrentLevel.getPath()
                                                .get(myCurrentLevel.getPathLength() - 1).x,
                                  myCurrentLevel.getPath()
                                                .get(myCurrentLevel.getPathLength() - 1).y);
    }


    boolean existsInPath (Sprite bub)
    {
        for (Bubble bubble = first_bub; bubble != null; bubble = bubble.next)
        {
            if (bubble.bm == bub)
            {
                return true;
            }
        }
        return false;
    }

    Bubble getLastBubble ()
    {
        if (first_bub == null)
            return null;
        Bubble bubble;
        for (bubble = first_bub; bubble.next != null; bubble = bubble.next)
            ;
        return bubble;
    }

    boolean adjIsGoingToBurst (Bubble bubble)
    {
        Sprite s = bubble.bm;
        int i = 1;
        for (Bubble b = bubble.prev; b != null && b.bm == s; b = b.prev)
            i++;
        for (Bubble b = bubble.next; b != null && b.bm == s; b = b.next)
            i++;
        return i >= 3;
    }

    void spawnBiggerBurst (double x, double y)
    {
        for (int i = 0; i < 30; i++)
        {
            double angle = randDouble(0, PI_2);
            double magnitude = randDouble(140, 310);
            PartBub partbub = new PartBub(this, x, y, bm_part_bub[0], randDouble(3, 3.8));
            partbub.life += randDouble(0, 0.4);
            partbub.vx = magnitude * Math.cos(angle);
            partbub.vy = magnitude * Math.sin(angle);
            partbub.x += partbub.vx / 80;
            partbub.y += partbub.vy / 80;
            addPart(partbub);
        }
    }

    void spawnBurst (double x, double y)
    {
        y += 5;
        for (int i = 0; i < 26; i++)
        {
            double angle = randDouble(0, PI_2);
            double magnitude = randDouble(50, 100);
            PartBub partbub = new PartBub(this, x, y, bm_part_bub[0], randDouble(3, 4.2));
            partbub.life += randDouble(0, 0.2);
            partbub.vx = magnitude * Math.cos(angle);
            partbub.vy = magnitude * Math.sin(angle) - magnitude;
            partbub.x += partbub.vx / 80;
            partbub.y += partbub.vy / 80;
            addPart(partbub);
        }
    }

    Sprite getRandomBubble (boolean totally_random)
    {
        int max = (3 + getCurrentEpisode() / 2) % bm_bubbles.length;
        if (totally_random)
        {
            return bm_bubbles[randInt(max)];
        }
        for (int j = 0; j < 300; j++)
        {
            Sprite bub = bm_bubbles[randInt(max)];
            if (existsInPath(bub))
            {
                return bub;                
            }
        }
        System.out.println("stalled.");
        return bm_bubbles[randInt(max)];
    }

    int randInt (int max)
    {
        return (int)(Math.random() * max);
    }

    double randDouble (double min, double max)
    {
        return min + Math.random() * (max - min);
    }

    String scoreString ()
    {
        StringBuffer stringbuffer = new StringBuffer(score_show);
        for (int i = stringbuffer.length() - 1 - 2; i > 0; i -= 3)
        {
            stringbuffer.insert(i, "0");            
        }
        return stringbuffer.toString();
    }
}
