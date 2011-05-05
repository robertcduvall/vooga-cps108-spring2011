package vooga.user.model;

import java.util.List;

import vooga.user.view.gui.middleFrame.ISectionAddable;

//import java.awt.event.ActionListener;
/**
 * @author Conrad Haynes
 *This LoginTemplate provides a simple frame to go about constructing input fill-in panel sequences to update the migLayout with.
 */
public class LoginTemplate {
	
	public String[] myInputs, myPrompts;
	public String myHeader, myImageURL;
	public int[] myButtons;
	public ISectionAddable mySectionType;
	
	/**
	 * Although this method has a lot of parameters, it provides an easy way to store and wrap information to update the view with.
	 * It also acts as a container object for the migLayout.
	 * @param header - the title of a specific section in a gui view
	 * @param prompts - the questions the writer wants to prompt within a section
	 * @param imageURL - the location of an image the user want to include in their layout
	 * @param buttonAction - create a button based on the action you want it to perform
	 */
	public LoginTemplate(String header, String[]prompts, String imageURL, int[] buttons, ISectionAddable section){	
		myPrompts = prompts;
		myHeader = header;
		myImageURL = imageURL;
		myButtons = buttons;
		mySectionType=section;
	}
	
	/**
	 * Retrieves the header/section title of the template
	 */
	public String getHeader(){
		return myHeader;
	}
	
	/**
	 * Retrieves the prompt questions for a login template
	 */
	public String[] getPrompts(){
		return myPrompts;
	}

	/**
	 * Retrieves the image location for the template outline
	 */
	public String getImageURL() {
		return myImageURL;
	}
	
	/**
	 * Retrieves a button action for a specific button - if implemented
	 */
	public int[] getButtonListener(){
		return myButtons;
	}

	/**
	 * Retrieves the section type (instructions for how to display information) from a given LoginTemplate
	 */
	public ISectionAddable getSectionType() {
		return mySectionType;
	}	
}
