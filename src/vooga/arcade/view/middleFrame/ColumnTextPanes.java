package vooga.arcade.view.middleFrame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vooga.arcade.controller.ArcadeController;
import vooga.arcade.view.helper.ResourceManager;

/**
 * A generic class that creates a swing component that creates column of
 * JEditorPane, stacked on top of each other using GridLayout.
 */
public class ColumnTextPanes extends JPanel
{
	private static final long serialVersionUID = 1L;
	private List<JEditorPane> editorPanes = new ArrayList<JEditorPane>();
	private ResourceManager columnTextAreaResource = new ResourceManager(
			"vooga.arcade.resources.ColumnTextAreaResource");
	private ResourceManager middleFrameResource = new ResourceManager(
			"vooga.arcade.resources.MiddleFrameResource");

	public ColumnTextPanes(final int numPanels, String[] names,
			final ArcadeController pc)
	{
		for (int i = 0; i < numPanels; i++)
		{
			JEditorPane pane = new JEditorPane();
			pane.setEditable(false); // Read-only
			editorPanes.add(pane);
			this.add(new JScrollPane(pane));
		}

		GridLayout g = new GridLayout(numPanels, 1);
		g.setVgap(columnTextAreaResource.getInteger("VerticalGap"));
		this.setLayout(g);
		this.setPreferredSize(new Dimension(columnTextAreaResource
				.getInteger("PreferredWidth"), columnTextAreaResource
				.getInteger("PreferredHeight")));

	}

	public void changePaneToURL(int i, String url)
	{
		try
		{
			// Try to display the page
			editorPanes.get(i).setPage("file://"+url);
		}
		catch (IOException e)
		{
			System.err.println("FAIL URL");
		}

	}

}
