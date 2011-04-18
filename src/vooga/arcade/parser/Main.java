package vooga.arcade.parser;


public class Main {
    public static void main(String[] args) {
        ArcadeGameObject ob = GameXMLParser.getGameData("./src/vooga/arcade/parser/game.xml");
        System.out.println(ob.getData("rating"));
        ob.putData("rating", "yhg is wtf");
        ob.start();
        System.out.println("lololol");
    }
}
