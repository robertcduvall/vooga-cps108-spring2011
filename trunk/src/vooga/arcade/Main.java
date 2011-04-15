package vooga.arcade;

public class Main {
    public static void main(String[] args){
        XMLParser parser = new XMLParser();
        ArcadeGameObject ob = parser.getGameData("./src/vooga/arcade/game.xml");
        ob.start();
    }
}
