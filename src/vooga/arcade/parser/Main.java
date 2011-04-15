package vooga.arcade.parser;

public class Main {
    public static void main(String[] args){
        XMLParser parser = new XMLParser();
        ArcadeGameObject ob = parser.getGameData("./src/vooga/arcade/parser/game.xml");
        ob.start();
    }
}
