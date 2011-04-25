package vooga.leveleditor.gui;

import javax.swing.JTree;

import org.w3c.dom.Element;

/**
 * A simple XML editor.
 */
public class XMLEditor extends JTree
{

    private Element properties;

    public XMLEditor(Element e)
    {
        this.properties = e;
    }

}
