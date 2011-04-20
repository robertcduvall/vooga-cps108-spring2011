package vooga.user.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PasswordParser {
	public Map<String, String> keyMapping;
	
	public PasswordParser(){
	
	}
	
	public Map<String, String> parse(List<String> list){
		keyMapping = new HashMap<String,String>();
		
		for(String s : list){
			String[] keys = s.split(":");
			keyMapping.put(keys[0],keys[1]);
		}
		return keyMapping;
	}
}
