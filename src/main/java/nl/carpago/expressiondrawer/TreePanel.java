package nl.carpago.expressiondrawer;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class TreePanel extends JPanel
{
	
	private Tree tree;
	
	public TreePanel(Tree aTree)
	{
		this();
		this.tree = aTree;
			
	}
	
	public TreePanel()
	{
		super();
		this.setBackground(Color.white);
	}
	
	public void setTree(Tree aTree)
	{
		this.tree = aTree;	
	}
		
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//rloman hier invoegen tree.draw(); of misschien wel dat de Tree zijn TreePanel kent, hetgeen me beter lijkt.
		if(this.tree!=null)
		{
			int left=0;
			int right = this.getWidth();
			tree.draw(g, this, tree.getHeight(), tree.getHeight(), left, right);//twice height, once for initialHeight of the complete tree, twice for the next node.
		}
		//g.drawString("Testpanel waar Tree in komt",(int) this.getSize().getWidth()/2,(int) this.getSize().getHeight()/2);
	}
}