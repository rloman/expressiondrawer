package nl.carpago.expressiondrawer;

public class InvalidExpressionException extends Exception
{
	private String message;
	
	public InvalidExpressionException()
	{
		super();
	}
}