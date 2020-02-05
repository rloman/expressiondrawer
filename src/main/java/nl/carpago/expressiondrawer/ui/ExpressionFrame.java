package nl.carpago.expressiondrawer.ui;

import nl.carpago.expressiondrawer.parser.exceptions.InvalidExpressionException;
import nl.carpago.expressiondrawer.model.Tree;
import nl.carpago.expressiondrawer.parser.ExpressionConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class ExpressionFrame extends JFrame implements ActionListener
{
	private JTextField expressie;
	private JLabel label1;
	private JButton knopOK;
	private JButton knopSluiten;
	private JTextArea textarea;
	private TreePanel treePanel;
	private JLabel filler;
	private JLabel melding;
		
	public ExpressionFrame()
	{
		super("Expression Frame");
		
		// set look and feel
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			
		}
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		
		JPanel menuPane = new JPanel();
		menuPane.setLayout(new GridLayout(0, 4, 5, 0)); // 0,4 omdat vier aantal cols is en dan moet rows 0 voor selfadjusting ;-(
				
		label1 = new JLabel("Voer een expressie in:", SwingConstants.RIGHT);
				
		
		expressie = new JTextField(40);
		
		//JPanel dialogPane = new JPanel();
		//dialogPane.setLayout(new BorderLayout());
		//dialogPane.add(expressie, BorderLayout.NORTH);
		//dialogPane.add(melding, BorderLayout.SOUTH);
		expressie.addActionListener(this);
		
		knopOK = new JButton("ok");
		knopOK.addActionListener(this);
		
		knopSluiten = new JButton("sluiten");
		knopSluiten.addActionListener(this);
		
		filler = new JLabel("", SwingConstants.RIGHT);
		melding = new JLabel("");
		
		
		treePanel = new TreePanel();
		treePanel.setVisible(true);
		
				
		menuPane.add(label1);
		//menuPane.add(expresie);
		//menuPane.add(melding);
		menuPane.add(expressie);
		menuPane.add(knopOK);
		menuPane.add(knopSluiten);
		menuPane.add(filler);
		menuPane.add(melding);
		
		contentPane.add(menuPane, BorderLayout.NORTH);
		contentPane.add(treePanel, BorderLayout.CENTER);
		
		this.setContentPane(contentPane);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}
		});
		
		
	}
	
	public void actionPerformed(ActionEvent event)
	{
		//clicked OK or entered on the textfield
		if(event.getSource() == this.knopOK || event.getSource()  == this.expressie)
		{
			treePanel.setTree(new Tree());
			treePanel.repaint();
			
			
			String expressie = this.expressie.getText();
			ExpressionConverter converter=new ExpressionConverter(expressie);
			String out = converter.getPostorderString();
					
			Tree tree=null;
			try 
			{
				tree = converter.getExpressionTree();
				treePanel.setTree(tree);
				treePanel.repaint();
			}
			catch (InvalidExpressionException e)
			{
				this.melding.setText("Ongeldige expressie");
				return;
			}
			
			try
			{
				this.filler.setText("Resultaat:");
				this.melding.setText("");
				
				this.melding.setText(String.valueOf(tree.eval()));
			}
			catch (NumberFormatException e)
			{
				this.melding.setText("onbekend");
			}
				
			
			
			
			//double eval = tree.eval();
			//this.textarea.append(String.valueOf(eval));
			/*System.out.println("<inorder>"+converter.getInorder()+"</inorder>");
			String[] tokens = converter.getTokens();
			for(int i = 0; i < tokens.length;i++)
			{
				System.out.println("<token#"+i+">"+tokens[i]+"</token>");
			}*/
		}
		else 
		{
			if(event.getSource() == this.knopSluiten)
			{
				this.dispose();
				System.exit(0);
			}		
		}
		
	}

}