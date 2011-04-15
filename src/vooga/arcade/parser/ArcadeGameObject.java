package vooga.arcade.parser;


import java.awt.Dimension;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;

public class ArcadeGameObject {
    
    
    private String title, author, genre, description, dateCreated, version, price, language;
    private Game game;
    private Dimension dimension;
    private String[] highscores;
    
    public ArcadeGameObject(Game game, String title,String author, String genre,
            String description, String dateCreated, String[] highscores,
            String version, String price, String language, Dimension dimension) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.dateCreated = dateCreated;
        this.version = version;
        this.price = price;
        this.language = language;
        this.game = game;
        this.highscores = highscores;
        this.dimension = dimension;
        
    }
    
    /**
     * start the game contained in the game object
     */
    public void start(){
        GameLoader loader = new GameLoader();
        loader.setup(game, dimension, false);
        game.start();
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the dateCreated
     */
    public String getDateCreated() {
        return dateCreated;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @return the price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * @return the dimension
     */
    public Dimension getDimension() {
        return dimension;
    }

    /**
     * @return the highscores
     */
    public String[] getHighscores() {
        return highscores;
    }
}
