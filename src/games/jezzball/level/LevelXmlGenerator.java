package games.jezzball.level;

/**
 * code written to generate XML for level xmls
 * 
 * @author KevinWang
 *
 */
public class LevelXmlGenerator {
    public static void createLevel(int xCoord, int yCoord, int numRows, int numColumns){
        String type;
        for(int i =0; i<numRows; i++){
            
            for(int j = 0; j < numColumns; j++){
                if(i == 0 || i == numRows-1){
                    type = "wall";
                }else if(j==0 || j == numColumns-1){
                    type="wall";
                }else{
                    continue;
                }
                String outputString = "<instance type=\""+type+"\">\n"+
                "\t<x>"+(xCoord+20*i)+"</x>\n"+
                "\t<y>"+(yCoord+20*j)+"</y>\n"+
                "\t</instance>\n";
                    
                System.out.println(outputString);
                
                
            }
            System.out.println();
        }
    }
    
    public static void main(String args[]){
        createLevel(100,100,15,15);
    }
    
    /*
    <instance type="ball">
    <x>150</x>
    <y>150</y>
    <direction>34</direction>
    <speed>0.1</speed>
    </instance>
*/
}
