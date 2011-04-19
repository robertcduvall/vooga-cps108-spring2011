package vooga.arcade.view.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import vooga.arcade.controller.PracticeController;
import vooga.arcade.view.buttonToolBar.ButtonBar;
import vooga.arcade.view.helper.ResourceManager;
import vooga.arcade.view.iView.IView;
import vooga.arcade.view.middleFrame.VoogaMiddleFrame;
import vooga.arcade.view.ratingsPane.RatingsPane;


/**
 * This is the GUI for the VOOGA project - layout modeled after the iTunes store.
 * @author Andrea Scripa, borrowed from Ethan Goh (SLogo)
 */

public class VoogaViewer extends JFrame implements IView
{
    private static final long serialVersionUID = 1L;
    private VoogaMiddleFrame middleFrame;
    private RatingsPane ratingsPane;
    private ResourceManager middleFrameResource =
        new ResourceManager("vooga.arcade.resources.MiddleFrameResource");

    private PracticeController pc;

    public VoogaViewer (String title,
                        String prompt,
                        Dimension dim,
                        PracticeController p)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        pc = p;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setTitle(title);
        this.add(ButtonBar.createButtonToolBar(p), BorderLayout.PAGE_START);
        this.setResizable(false);
  
        middleFrame = new VoogaMiddleFrame(pc);
        ratingsPane = new RatingsPane(pc);
        
        this.add(middleFrame, BorderLayout.CENTER);
        this.add(ratingsPane, BorderLayout.PAGE_END);
        this.setMinimumSize(dim);
        this.setVisible(true);
    }


    public VoogaViewer (String title,
                        String prompt,
                        int dimx,
                        int dimy,
                        PracticeController p)
    {
        this(title, prompt, new Dimension(dimx, dimy), p);
    }


    public void showError (String str)
    {
        JOptionPane.showMessageDialog(middleFrame, str);
    }


//    public void addVariableListEntry (String str)
//    {
//        middleFrame.addVariable(str);
//    }
//
//
//    public void clearVariables ()
//    {
//        middleFrame.clearVariables();
//    }
//
//
   @Override
    public void updateList (String name)
    {
    }
}
