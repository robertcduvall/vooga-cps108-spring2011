package vooga.arcade.view.ratingsPane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import vooga.arcade.controller.PracticeController;
import vooga.arcade.view.helper.ResourceManager;
import vooga.arcade.view.middleFrame.VoogaMiddleFrame;

public class RatingsPane extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_TEXT_FIELD_WIDTH = 60;
	private static final int DEFAULT_TEXT_FIELD_HEIGHT = 6;

	private ResourceManager ratingsPaneResource = new ResourceManager(
			"vooga.arcade.resources.RatingsPaneResource");

	private PracticeController pc;
	private JEditorPane ratingsPane;
	private JScrollPane scrollPane;

	public RatingsPane(PracticeController p) {
		this(DEFAULT_TEXT_FIELD_WIDTH, DEFAULT_TEXT_FIELD_HEIGHT);
		pc = p;
	}

	public RatingsPane(int textWidth, int textHeight) {
		ratingsPane = new JEditorPane();
		ratingsPane.setEditable(false);
		ratingsPane.setDragEnabled(true);
		scrollPane = new JScrollPane(ratingsPane);

		this.setBorder(BorderFactory.createTitledBorder(ratingsPaneResource
				.getString("FieldTitle")));
		this.setLayout(new GridLayout(1, 1));
		this.add(scrollPane, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(ratingsPaneResource
				.getInteger("PREFERRED_HEIGHT"), ratingsPaneResource
				.getInteger("PREFERRED_HEIGHT")));
	}

	public JEditorPane getRatingsPane() {
		return ratingsPane;
	}
}
