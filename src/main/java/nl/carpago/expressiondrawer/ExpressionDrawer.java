/*
 * @(#)ExpressionDrawer.java 1.0 04/12/19
 *
 * You can modify the template of this file in the
 * directory ..\JCreator\Templates\Template_1\Project_Name.java
 *
 * You can also create your own project template by making a new
 * folder in the directory ..\JCreator\Template\. Use the other
 * templates as examples.
 *
 */
package nl.carpago.expressiondrawer;

import java.awt.*;
import java.awt.event.*;

class ExpressionDrawer extends Frame {
	
	public ExpressionDrawer() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}
		});
	}

	public static void main(String args[]) {
		System.out.println("Starting ExpressionDrawer...");
		ExpressionDrawer mainFrame = new ExpressionDrawer();
		mainFrame.setSize(400, 400);
		mainFrame.setTitle("ExpressionDrawer");
		mainFrame.setVisible(true);
	}
}
