package vooga.user.model.parser;
import java.util.regex.Pattern;
import vooga.user.main.ResourceManager;

/**
 * This class uses regular expression to check user input. All input must pass through
 * this class to get verified before it is stored.
 * @author Conrad Haynes
 */
public class RegXParser {
	  public String[] specialRegularExpressions;
	  private ResourceManager regExResource = new ResourceManager("vooga.user.resources.RegularExpressionResource"); 
	       
	  		/**
	  		 * This is the constructor for the RegXParser - it also creates a default array for all regex prompts
	  		 */
	        public RegXParser() {
	        	specialRegularExpressions = regExResource.getStringArray("RegExTerms");
	        }

	        /**
	         * This method verify's that the input string is in the appropriate format for the given prompt
	         */
	        public boolean verifyRegex(String prompt, String input){
	        	for(String regexTerm : specialRegularExpressions){
	        		if(regexTerm.equals(prompt)){	
	        			return Pattern.matches(regExResource.getString(regexTerm),input);
	        		}
	        	}
	        	return Pattern.matches(regExResource.getString("GeneralRegex"), input);
	        }
	        
	}

