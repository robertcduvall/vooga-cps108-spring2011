
package vooga.debugger.view.grapher;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import vooga.debugger.Debugger;
import vooga.debugger.model.GameField;
import vooga.debugger.util.MethodAction;


/**
 * Extends class GameField by adding GUI functionality for recording and graphing real-time data of this field in the game
 * 
 * @author Troy Ferrell
 */
public class GraphGameField extends GameField
{	
	private static final int MAX_DATA_SIZE = 200;
	private static final int SAMPLING_RATE_MAX = 30;
	private static final int SAMPLING_RATE_MIN = 1;
	private static final int SAMPLING_RATE_TICK = 10;
	
	private DebuggerGrapher myDebugGrapher;
	
	private ArrayList<DataPoint> fieldData = new ArrayList<DataPoint>();
	private DataPoint maxPoint = new DataPoint(Double.MIN_VALUE, Double.MIN_VALUE);
	private DataPoint minPoint = new DataPoint(Double.MAX_VALUE, Double.MAX_VALUE);
	private Color myGraphColor = Color.BLACK;
	private boolean recording = true;
	private int samplingCounter = 0;
	private int samplingRate = SAMPLING_RATE_MIN;
	
	private JPanel myGraphFieldPanel;
	private JLabel myFieldNameLabel;
	private JLabel mySelectedPointLabel;
	private JButton myColorChooserButton;
	private JButton myRecordButton;
	private JButton myStopRecordButton;
	private JCheckBox myDrawCheckBox;
	private JComboBox mySamplingCombo;
	
	public GraphGameField(Field [] path, Debugger debug, DebuggerGrapher debugG)
	{
		super(path);
		
		myDebugGrapher = debugG;
		
		myGraphFieldPanel = new JPanel();
		myGraphFieldPanel.setMaximumSize(new Dimension(400, 150));
		
		JPanel graphPanel = initFieldPanel();
		JPanel removePanel = initButtonPanel(debug);
		
		myGraphFieldPanel.add(graphPanel);
		myGraphFieldPanel.add(removePanel);
		
		// Init data array with origin point
		this.addDataPoint(0.0, 0.0);
	}

	/**
	 * Initialize the panels holding the remove, recording, and stop recording buttons
	 * @param debug - controller class
	 * @return panel described
	 */
	private JPanel initButtonPanel(Debugger debug) 
	{
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
		
		JButton removeButton = new JButton(new ImageIcon("src/vooga/debugger/resources/CloseButton.png"));
		removeButton.addActionListener(new MethodAction(this, "removeButtonPressed", debug));
		
		myRecordButton = new JButton(new ImageIcon("src/vooga/debugger/resources/RecordButton.png"));
		myRecordButton.setEnabled(false);
		myRecordButton.addActionListener(new MethodAction(this, "recordButtonPressed"));
		
		myStopRecordButton = new JButton(new ImageIcon("src/vooga/debugger/resources/StopRecordButton.png"));
		myStopRecordButton.setEnabled(true);
		myStopRecordButton.addActionListener(new MethodAction(this, "stopRecordButtonPressed"));
		
		buttonPanel.add(removeButton);
		buttonPanel.add(myRecordButton);
		buttonPanel.add(myStopRecordButton);
		
		return buttonPanel;
	}
	
	/**
	 * Initialize the panels holding the components for changing the sampling rate for recording of this GameField
	 * @param debug - controller class
	 * @return panel described
	 */
	private JPanel initSamplingPanel()
	{
		JPanel samplingPanel = new JPanel();
		
		String [] sampleOptions = new String[SAMPLING_RATE_MAX/SAMPLING_RATE_TICK + 1];
		sampleOptions[0] = SAMPLING_RATE_MIN + " cycles";
		int sampleCounter = SAMPLING_RATE_TICK;
		for(int i = 0; i < SAMPLING_RATE_MAX/SAMPLING_RATE_TICK; i++)
		{
			sampleOptions[i + 1] = sampleCounter + " cycles";
			sampleCounter += SAMPLING_RATE_TICK;
		}

		mySamplingCombo = new JComboBox(sampleOptions);
		mySamplingCombo.setEnabled(false);
		mySamplingCombo.addActionListener(new MethodAction(this, "sampleRateChanged"));
		
		samplingPanel.add(new JLabel("Sampling Rate: "));
		samplingPanel.add(mySamplingCombo);
		
		return samplingPanel;
	}

	/**
	 * Initialize panel containing field name, selected point on cavnas, and drawing state
	 * @return panel described
	 */
	private JPanel initFieldPanel() 
	{
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout(2, 1));
		
		myFieldNameLabel = new JLabel("Field Name: " + this.myFieldName);
		myFieldNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		labelPanel.add(myFieldNameLabel);
		
		mySelectedPointLabel = new JLabel("( , )");
		mySelectedPointLabel.setHorizontalAlignment(SwingConstants.CENTER);
		labelPanel.add(mySelectedPointLabel);
		
		JPanel settingPanel = new JPanel();
		settingPanel.add(createColorPanel());
		
		myDrawCheckBox = new JCheckBox("Draw");
		myDrawCheckBox.setSelected(true);
		myDrawCheckBox.addActionListener(new MethodAction(this, "drawStateChanged"));
		settingPanel.add(myDrawCheckBox);
		settingPanel.setAlignmentY(SwingConstants.TOP);
		
		JPanel combinePanels = new JPanel();
		combinePanels.setLayout(new GridLayout(3,1));
		combinePanels.add(labelPanel);
		combinePanels.add(initSamplingPanel());
		combinePanels.add(settingPanel);
		
		return combinePanels;
	}
	
	/**
	 * Create panel containing components for choosing a color for drawing this field's plot
	 * @return panel described
	 */
	private JPanel createColorPanel() 
	{
		JPanel colorPanel = new JPanel();
		
		myColorChooserButton = new JButton();
		myColorChooserButton.setBackground(myGraphColor);
		myColorChooserButton.addActionListener(new MethodAction(this, "selectGraphColor"));
		
		colorPanel.add(new JLabel("Plot Color: "));
		colorPanel.add(myColorChooserButton);
		
		return colorPanel;
	}
	
	/**
	 * Update this field with the new object value.
	 * If field is recording value, add new object value as data point to field's plot
	 */
	protected void updateField(long deltaTime, Object fieldInstance)
	{
		if(recording)
		{
			samplingCounter++;
			if(samplingCounter == samplingRate)
			{
				if(fieldData.size() < MAX_DATA_SIZE )
				{
					try
					{
						double time = fieldData.get(fieldData.size() - 1).getX() + deltaTime;
						double objValue;
						
						if(fieldInstance.getClass().equals(Boolean.class))
							objValue = ((Boolean)fieldInstance) ? 1 : 0;
						else
							objValue = Double.parseDouble(fieldInstance.toString());
						
						this.addDataPoint(time, objValue);
						
						this.myDebugGrapher.repaint();
					} catch(NumberFormatException e)
					{
						e.printStackTrace();
						// TODO: print error to console
					}
				}
				else
				{
					recording = false;
					myRecordButton.setEnabled(true);
					myStopRecordButton.setEnabled(false);
					mySamplingCombo.setEnabled(true);
				}
				
				samplingCounter = 0;
			}
		}
	}
	
	/**
	 * Update the field and it's gui component for new selected point
	 * @param dp
	 */
	public void updateSelectPoint(DataPoint dp)
	{
		mySelectedPointLabel.setText(String.format("( %.1f , %.1f )", dp.getX(), dp.getY()));
	}
	
	/**
	 * Add new data coordinate to plot
	 * @param time - x value
	 * @param data - y value
	 */
	public void addDataPoint(double time, double data)
	{
		if(time >= 0)
		{
			DataPoint newPoint = new DataPoint(time, data);
			fieldData.add(newPoint);
			
			// check Min/Max for time value
			if(newPoint.getX() > maxPoint.getX())
				maxPoint.setX(newPoint.getX());
			else if(newPoint.getX() < minPoint.getX())
				minPoint.setX(newPoint.getX());
			
			// Check Min/Max for data value
			if(newPoint.getY() > maxPoint.getY())
				maxPoint.setY(newPoint.getY());
			else if(newPoint.getY() < minPoint.getY())
				minPoint.setY(newPoint.getY());
		}
	}
	
	/**
	 * Action Event Method - called when color button is clicked. 
	 * Shows color picker for user to select new plotting color
	 */
	private void selectGraphColor()
	{
		myGraphColor = JColorChooser.showDialog(myGraphFieldPanel,
                "Choose Background Color",
                myColorChooserButton.getBackground());
		
		myColorChooserButton.setBackground(myGraphColor);
		
		myDebugGrapher.repaint();
	}
	
	/**
	 * Action Event Method - called when remove button clicked.
	 * Allows user to remove this field from this panel list
	 * @param debug - controller of system
	 */
	private void removeButtonPressed(Debugger debug)
	{
		debug.removeField(this);
	}
	
	/**
	 * Action Event Method - called when record button clicked.
	 * Clears values for this GraphField and sets state to record new values
	 */
	private void recordButtonPressed()
	{
		// show dialog
		int result = JOptionPane.showConfirmDialog(myDebugGrapher, 
				"You are about to clear all of this \nfield's graph data and begin recording new values."
				+ "\nAre you sure you want to continue?",
				"Still Record?", JOptionPane.OK_CANCEL_OPTION);
		if(result == JOptionPane.OK_OPTION)
		{
			// Reset Data
			fieldData.clear();
			this.addDataPoint(0.0, 0.0);
			
			// Start Recording again
			recording = true;
			myRecordButton.setEnabled(false);
			myStopRecordButton.setEnabled(true);
			mySamplingCombo.setEnabled(false);
			
			this.myDebugGrapher.repaint();
		}
	}
	
	/**
	 * Action Event Method - called when stop record button clicked.
	 * Stops field from continuing to record values from game
	 */
	private void stopRecordButtonPressed()
	{
		recording = false;
		
		myRecordButton.setEnabled(true);
		myStopRecordButton.setEnabled(false);
		mySamplingCombo.setEnabled(true);
	}
	
	
	/**
	 * Action Event Method - called when draw checkbox changed.
	 * Updates debugger grapher to repaint itself with new draw state of this GraphField
	 */
	private void drawStateChanged()
	{
		myDebugGrapher.repaint();
	}
	
	/**
	 * Action Event Method - called when sample combo box is affected.
	 * Updates sampling rate of systems for recording values
	 */
	private void sampleRateChanged()
	{
		samplingRate = mySamplingCombo.getSelectedIndex()*SAMPLING_RATE_TICK;
		if(samplingRate == 0)
			samplingRate = SAMPLING_RATE_MIN;
	}
	
	/**
	 * Returns DataPoint object containing the max values of this field's x and y data
	 * @return the DataPoint obj
	 */
	public DataPoint getMaxPoint()
	{
		return this.maxPoint;
	}
	
	/**
	 * Returns DataPoint object containing the min values of this field's x and y data
	 * @return the DataPoint obj
	 */
	public DataPoint getMinPoint()
	{
		return this.minPoint;
	}
	
	public Color getPlotColor()
	{
		return myGraphColor;
	}
	
	public Collection<DataPoint> getData()
	{
		return fieldData;
	}
	
	public JPanel getPanel()
	{
		return myGraphFieldPanel;
	}

	public boolean isDrawable()
	{
		return myDrawCheckBox.isSelected();
	}
	
	public void setDrawable(boolean sel)
	{
		myDrawCheckBox.setSelected(sel);
	}
}
