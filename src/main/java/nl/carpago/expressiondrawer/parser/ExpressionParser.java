package nl.carpago.expressiondrawer.parser;

import nl.carpago.expressiondrawer.model.Operator;

import java.util.StringTokenizer;

public class ExpressionParser
{
	
	public ExpressionParser()
	{
		
	}
	
	
	public String[] parse(String inorder)
	{
		String hulp;
		
		inorder = inorder.trim();
		inorder = inorder.replace(',','.');
		hulp = inorder;
		
		for(int i = 0; i < Operator.operators.length; i++)
		{
			hulp = tokenize(hulp, Operator.operators[i]);
		}
		//now we should have a string with tokens delmited by " "
				
		int i = 0;
		String token;
		
		StringTokenizer tokenizer = new StringTokenizer(hulp, " ");
		
		String[] result = new String[tokenizer.countTokens()];
		while(tokenizer.hasMoreTokens())
		{
			result[i] = tokenizer.nextToken();
			i++;
		}
		
		
		return result;
	}
	
	
	private String tokenize(String in, String delimiter)
	{
		StringBuffer result = new StringBuffer(in);
		int i = 0;
		while(i < result.length())
		{
			if(result.charAt(i) == delimiter.charAt(0))
			{
				result.replace(i,i+1," "+delimiter+" ");
				i++;
			}
			i++;
		}
		
		return result.toString();
		
	}
} 