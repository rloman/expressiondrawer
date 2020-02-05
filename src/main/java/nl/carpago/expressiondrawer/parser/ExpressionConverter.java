package nl.carpago.expressiondrawer.parser;

import nl.carpago.expressiondrawer.model.Operator;
import nl.carpago.expressiondrawer.model.Tree;
import nl.carpago.expressiondrawer.parser.exceptions.InvalidExpressionException;

import java.util.EmptyStackException;
import java.util.Stack;

public class ExpressionConverter
{
	private String inorder;
	private String[] tokens;
	
	private Stack result;
	private Stack operators;
			
	
	public ExpressionConverter(String inorder)
	{
		ExpressionParser parser = new ExpressionParser();
		tokens = parser.parse(inorder);
		this.inorder = inorder;
	}
	
	public String getInorder()
	{
		return this.inorder;
	}
	
	public String[] getTokens()
	{
		return this.tokens;
	}

	private void processToken(String token)
	{
		String topOperator;
		
		// value 
		if(!this.isOperator(token))
		{
			result.push(token);
			return;
		}
				
				
		// open parenthesis
		if(token.equals("(")) 
		{	
			operators.push(token);
			return;
		}	
				
				
		// close parenthesis
		if(token.equals(")"))
		{
			// process the )	
			topOperator = (String) operators.pop();
			while(!topOperator.equals("(") && !operators.empty())
			{	
				result.push(topOperator);
				topOperator = (String) operators.pop();
			}
			if(!topOperator.equals("("))
			{
				System.out.println("No matching opening parenthesis");
			}
			return;
		}
			
		//default operator
		if(!operators.empty())
		{
			topOperator = (String) operators.peek();
				{
					while (!(operators.empty()) && (precedence(token) <=  precedence(topOperator)))
					{
						result.push(topOperator);
						operators.pop();
						if(!operators.empty()){
							topOperator = (String) operators.peek();
						}
					}
					
				}
		}
		operators.push(token);
	}
	
	public Stack getPostorderStack()
	{
			result=	new Stack();
			operators	=	new Stack();
			
			for(int i=0;i < this.tokens.length;i++)
			{
				String current = tokens[i].trim();
				this.processToken(current);
				
			}
			
			//pop all the leftover operators from operators stack and push on the resultStack
			while(!operators.empty())
			{
				result.push((String) operators.pop());
			}
			
			return result;
	}
	
	public Stack getPreorderStack()
	{
		Stack postorderStack = this.getPostorderStack();
		Stack preorderStack = new Stack();
		
		while(!(postorderStack.empty()))
		{
			preorderStack.push(postorderStack.pop());
		}
		
		return preorderStack;
	}
	
	public Tree getExpressionTree() throws InvalidExpressionException
	{
		Stack preorderStack = this.getPreorderStack();
		Stack treeStack = new Stack(); // stack of Tree's
		Tree tree;
		String current;
		
		while(!preorderStack.empty())
		{
			current = (String) preorderStack.pop();
			
			if(!this.isOperator(current))
			{
				tree=new Tree(current); // value = current, left and right are empty trees.--> leafnode.
				treeStack.push(tree);
			}
			else
			{
				try
				{
					tree=new Tree(current);
					tree.setRight((Tree) treeStack.pop());
					tree.setLeft((Tree) treeStack.pop());
					treeStack.push(tree);
				}
				catch (EmptyStackException e)// boerenmanier om foutieve expressie af te vangen en te melden naar gebruiker.
				{
					throw new InvalidExpressionException();
				}
			}
		}
		return (Tree) treeStack.pop();
	}
	
	
	public String getPostorderString()
	{
			String result = "";
			Stack postorderStack = this.getPostorderStack();
			
			while(!postorderStack.empty())
			{
				result = (String) postorderStack.pop()+result;
			}
			
			return result;
	}
	
		
	private int precedence(String operatorString)
	{
		char operator=operatorString.charAt(0);
		switch (operator)
		{
			case Operator.charOPEN_PARENTHESIS:
				return Operator.OPEN_PARENTHESIS;
				
			case Operator.charPOWER:
				return Operator.POWER;
				
			case Operator.charMULTIPLY1:
				return Operator.MULTIPLY;
				
			case Operator.charMULTIPLY2:
				return Operator.MULTIPLY;
				
			case Operator.charDIVISION1:
				return Operator.DIVISION;
				
			case Operator.charDIVISION2:
				return Operator.DIVISION;	
				
			case Operator.charADD:
				return Operator.ADD;
				
			case Operator.charSUBTRACT:
				return Operator.SUBTRACT;
				
			default:
				return -1;
							
		}
		
	}
	
	
	private boolean isNumeric(String value)
	{
		float iValue;
		try
		{
			iValue=Float.parseFloat(value);
			return true;
		}
		catch (NumberFormatException e)
		{
			return false;
		}
	}
	
	private boolean isOperator(String value)
	{
		return (Operator.operatorsString.indexOf(value) >= 0);
	}
	
	private boolean isIdentifier(String value)
	{
		return !(this.isNumeric(value) || this.isOperator(value));
	}
	
	
		
}