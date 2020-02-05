package nl.carpago.expressiondrawer.model;

import nl.carpago.expressiondrawer.ui.TreePanel;

import java.awt.*;

public class Tree
{
	private String value;
	private Tree left;
	private Tree right;
	
	public Tree()
	{
		this.value=null;
		this.left=null;
		this.right=null;
	}
	
	public Tree(String value)
	{
		this.value = value;
		this.left = null;
		this.right = null;
	}
	
	public void setLeft(Tree left)
	{
		this.left = left;
	}
	
	public void setRight(Tree right)
	{
		this.right = right;
	}
	
	
	public String toString()
	{	
		String result = "";
		result+="[";
		result+=this.value;
		
		if(left!=null)
		{
			result+=" left: ";
			result+=left.toString();
		}
		
		if(right!=null)
		{
			result+=" right: ";
			result+=right.toString();
		}
		
		result+="]";
		
		return result;
	}
	
	public void drukAf()
	{
		this.drukAfHulp();
		System.out.println();
	}
	
	private void drukAfHulp()
	{
		System.out.print("[");
		System.out.print(this.value);
		
		if(left!=null)
		{
			System.out.print(" left: ");
			left.drukAfHulp();
		}
		
		if(right!=null)
		{
			System.out.print(" right: ");
			right.drukAfHulp();
		}
		
		System.out.print("]");
	}
	
	public void draw(Graphics g, TreePanel treePanel, int initialHeight, int myHeight, int leftx, int rightx) // draw tree on Treepanel.
	{
		if(value==null) return;
		int x = leftx + (rightx-leftx)/2;
		int y = 50+(initialHeight - myHeight)*30;
		
		//draw the node.
		g.drawString(value, x-2*value.length(), y);
		g.drawOval(x-10,y-14,22,22); //drawOval(x, y, w, h) = oval at x,y with width w and height h.
				
		
		if(left!=null) 
		{	
			// first draw the line to the left-childnode
			int xNieuw = leftx+ (x-leftx)/2;
			int yNieuw = y+30;
			g.drawLine(x-10, y-4, xNieuw+13, y+30-5);
			left.draw(g, treePanel, initialHeight, myHeight-1, leftx, leftx+(rightx-leftx)/2); //draw left recursive
		}
		
		if(right!=null)
		{
			// first draw the line to the right-childnode
			int xNieuw = x + (rightx - x) / 2;
			int yNieuw = y+30;
			g.drawLine(x+12, y-4, xNieuw-11, y+30-5);
			
			right.draw(g, treePanel, initialHeight, myHeight-1, leftx+(rightx-leftx)/2, rightx); // draw right recursive
		}
	}	
	
	public boolean isLeaf()
	{
		return (left==null && right==null);
	}
	
	public int getHeight()
	{
		if(this.isLeaf())
		{
			return 0;	
		}
		else
		{
			if(left==null)
			{
				return 1+right.getHeight();
			}
			if(right==null)
			{
				return 1+left.getHeight();
			}
			
			return 1+Math.max(left.getHeight(), right.getHeight());
		}
	}
	
	public double eval() throws NumberFormatException
	{
		if(this.isLeaf())
		{
			try
			{
				return Double.parseDouble(value);
			}
			catch (NumberFormatException e)
			{
				throw e;
			}
		}
		else
		{
			char operator = this.value.charAt(0);
			switch (operator)
			{
				case Operator.charPOWER:
					return Math.pow(left.eval(),right.eval());
				
				case Operator.charMULTIPLY1: // fall through
				case Operator.charMULTIPLY2:
					return left.eval() * right.eval();
				
				case Operator.charDIVISION1:
				case Operator.charDIVISION2:
					return left.eval() / right.eval();
				
				case Operator.charADD:
					return left.eval() + right.eval();
					
				case Operator.charSUBTRACT:
					return left.eval() - right.eval();
				default:
					return 0d;
			}
		}
	}
	
	
}