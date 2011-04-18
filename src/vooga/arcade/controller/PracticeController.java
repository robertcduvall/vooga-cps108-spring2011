package vooga.arcade.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import vooga.arcade.view.gui.VoogaViewer;


public class PracticeController implements IController
{
    private VoogaViewer view;


    //private SlogoModel model;

    public PracticeController (String string, String string2, int i, int j)
    {
        //model = new SlogoModel(this);
        view = new VoogaViewer(string, string2, new Dimension(i, j), this);
    }


    public PracticeController ()
    {}


    @Override
    public void displayError (String s)
    {
        view.showError(s);
    }


    @Override
    public void addNewAvatar (String imageUML)
    {
    // TODO Auto-generated method stub

    }


    @Override
    public void promptModel (String str)
    {
    // TODO Auto-generated method stub

    }

//    @Override
//    public void updateView (DrawableData p)
//    {
//        // TODO Auto-generated method stub
//        
}
