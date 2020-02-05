package nl.carpago.expressiondrawer;

public interface Operator
{
	// chars representing the operators
	public static final char charPOWER = '^';
	public static final char charMULTIPLY1 = '*';
	public static final char charMULTIPLY2 = 'x';
	public static final char charDIVISION1 = '/';
	public static final char charDIVISION2 = ':';
	public static final char charADD = '+';
	public static final char charSUBTRACT = '-';
	public static final char charOPEN_PARENTHESIS = '(';
	public static final char charCLOSE_PARENTHESIS = ')';
	
	
	// the precedence (0 = highest)
	public static final int OPEN_PARENTHESIS = 0;
	public static final int CLOSE_PARENTHESIS = 1;
	public static final int POWER = 100;
	public static final int MULTIPLY = 99;
	public static final int DIVISION = 99;
	public static final int ADD = 98;
	public static final int SUBTRACT = 98;
	
	public static final String[] operators = {"(", ")", "^", "*", "x", "/", ":", "+", "-"};
	public static final String operatorsString = "()^*x/:+-";
}