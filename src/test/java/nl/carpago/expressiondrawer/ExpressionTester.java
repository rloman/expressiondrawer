package nl.carpago.expressiondrawer;

import nl.carpago.expressiondrawer.parser.exceptions.InvalidExpressionException;
import nl.carpago.expressiondrawer.model.Tree;
import nl.carpago.expressiondrawer.parser.ExpressionConverter;
import org.junit.Test;

public class ExpressionTester {


    @Test
    public void testConverter() {

        //String expression = "3x4^3-8/2+5x3^2+2";
        //String expression = "3x4^3-8+5x3^2";
        //String expression = "axb-56*78"; //8
        //String expression = "22/7";
        //String expression = "3x((2+(5-3))";
        //String expression = "2-3x4/2";
        String expression = "3+-4";
        ExpressionConverter converter = new ExpressionConverter(expression);
        String out = converter.getPostorderString();
        System.out.println("<postorder>" + out + "</postorder>");

        try {
            Tree tree = converter.getExpressionTree();
            tree.drukAf();
        } catch (InvalidExpressionException e) {

        }
        //double eval = tree.eval();
        //System.out.println(eval);
        System.out.println("<inorder>" + converter.getInorder() + "</inorder>");
        String[] tokens = converter.getTokens();
        for (int i = 0; i < tokens.length; i++) {
            System.out.println("<token#" + i + ">" + tokens[i] + "</token>");
        }
    }

}