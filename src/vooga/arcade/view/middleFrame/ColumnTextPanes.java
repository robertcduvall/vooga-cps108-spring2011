package vooga.arcade.view.middleFrame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import vooga.arcade.controller.PracticeController;
import vooga.arcade.view.helper.ResourceManager;

/**
 * A generic class that creates a swing component that creates column of
 * JEditorPane, stacked on top of each other using GridLayout.
 * 
 */
public class ColumnTextPanes extends JPanel {
	private static final long serialVersionUID = 1L;
	private List<DefaultListModel> editorPane = new ArrayList<DefaultListModel>();
	private ResourceManager columnTextAreaResource = new ResourceManager(
	"ColumnTextAreaResource");
	private ResourceManager middleFrameResource = new ResourceManager("SlogoMiddleFrameResource");

	public ColumnTextPanes(final int numPanels, String[] names, final PracticeController pc) {
		for (int i = 0; i < numPanels; i++) 
		{
			DefaultListModel d = new DefaultListModel();
			JEditorPane paneToAdd = new JEditorPane();
			paneToAdd.setEditable(false);
			paneToAdd.setDragEnabled(true);
			JScrollPane scrollPane = new JScrollPane(paneToAdd);
			editorPane.add(d);


			paneToAdd.setBorder(BorderFactory.createTitledBorder(names[i]));

			if(i == middleFrameResource.getInteger("CurrentPlayerIndex"))
			{
				ListSelectionListener listSelectionListener = new ListSelectionListener()
				{
					int selections[];

					public void valueChanged(ListSelectionEvent listSelectionEvent)
					{

						boolean adjust = listSelectionEvent.getValueIsAdjusting();

						if (!adjust) 
						{
							JList list = (JList) listSelectionEvent.getSource();

							selections = list.getSelectedIndices();
						}
					}
				};

			}

			this.add(scrollPane);

		}


		GridLayout g = new GridLayout(numPanels, 1);
		g.setVgap(columnTextAreaResource.getInteger("VerticalGap"));
		this.setLayout(g);
		this.setPreferredSize(new Dimension(columnTextAreaResource
				.getInteger("PreferredWidth"), columnTextAreaResource
				.getInteger("PreferredHeight")));


	}

	public void clearAll() {
		for (int i = 0; i < editorPane.size(); i++)
			editorPane.get(i).clear();
	}

	public void clearComponent(int i) {
		editorPane.get(i).clear();
	}

	public void addStringToComponent(int i, String s) {
		editorPane.get(i).addElement(s);
	}

	public DefaultListModel getTextArea(int i) {
		return editorPane.get(i);
	}

	public void addString(int i, String name){

		editorPane.get(i).addElement(name);
	}

	public void setSelected(int component, int index)
	{

	}

	public void setUnselected(int component, int index)
	{

	}

}
