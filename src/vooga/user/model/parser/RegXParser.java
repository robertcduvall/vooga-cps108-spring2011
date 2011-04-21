package vooga.user.model.parser;
import java.util.regex.Pattern;

import vooga.user.main.ResourceManager;

/**
 * Class that parses expression String into a Pattern and Matcher to use both
 * Regular expressions and constant instances in a search.
 */
public class RegXParser {
	  public String[] specialRegularExpressions;
	  private ResourceManager regExResource = new ResourceManager("vooga.user.resources.RegularExpressionResource");
	  //password string must have 1 letter 1 number 6-15
	  //username = 1-15 characters  
	       
	        public RegXParser() {
	        	specialRegularExpressions = regExResource.getStringArray("RegExTerms");
	        }

	        public boolean verifyRegex(String prompt, String input){
	        	for(String regexTerm : specialRegularExpressions){
	        		if(regexTerm.equals(prompt)){
	        			
	        			return Pattern.matches(regExResource.getString(regexTerm),input);
	        		}
	        	}
	        	return Pattern.matches(regExResource.getString("GeneralRegex"), input);
	        }
	        
	}

