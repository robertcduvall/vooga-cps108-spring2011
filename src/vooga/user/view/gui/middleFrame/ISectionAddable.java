package vooga.user.view.gui.middleFrame;

public interface ISectionAddable {

	/**
	 *This method adds a fill-in section to the gui when passed in a section title and; fill-in prompts
	 */
	public void addSection(String sectionTitle, String[] sectionInformation, FieldPanel panel);

}
