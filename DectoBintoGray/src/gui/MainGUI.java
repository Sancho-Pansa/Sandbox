package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import main.*;

/**
 * @author Mortorium
 *
 */
public class MainGUI extends JFrame
{
	
	private static final long serialVersionUID = 5765267086570271159L;
	private final int WINDOW_WIDTH = 640;
	private final int WINDOW_HEIGHT = 480;
	
	private final JPanel mainPanel;
	private JPanel textPanel;
	
	private Dimension winSize;
	private Dimension textAreaSize;
	
	private JButton doButton;
	private JTextField decField;
	private JTextField binField;
	private JTextField grayField;
	
	private ActionListener actListen;
	
	public MainGUI()
	{
		winSize = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
		textAreaSize = new Dimension(300, 30);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		this.setTitle("Convert Dec to Bin to Gray");
		this.setSize(winSize);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().add(mainPanel);
		Arrangement();
		this.setVisible(true);
	}
	
	private void Arrangement()
	{
		textPanel = new JPanel();
		textPanel.setLayout(null);
		doButton = new JButton("Do the thing");
		
		
		actListen = new BrowseAction();
		
		decField = new JTextField();
		decField.setToolTipText("Enter decimal number here");
		decField.setSize(textAreaSize);
		decField.setLocation(75, 100);
		//decField.setHorizontalAlignment(JTextField.RIGHT);
		
		binField = new JTextField("Binary digit");
		binField.setSize(textAreaSize);
		binField.setLocation(75, 200);
		binField.setEditable(false);
		binField.setPreferredSize(textAreaSize);
		//binField.setHorizontalAlignment(JTextField.RIGHT);
		
		grayField = new JTextField("Gray digit");
		grayField.setSize(textAreaSize);
		grayField.setLocation(75, 300);
		grayField.setEditable(false);
		grayField.setPreferredSize(textAreaSize);
		//grayField.setHorizontalAlignment(JTextField.RIGHT);
		
		textPanel.add(decField);
		textPanel.add(binField);
		textPanel.add(grayField);
		mainPanel.add(textPanel, BorderLayout.CENTER);
		mainPanel.add(doButton, BorderLayout.LINE_END);
		
		doButton.setActionCommand("Get number");
		doButton.addActionListener(actListen);
		
		setContentPane(mainPanel);
	}
	
	private class BrowseAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getActionCommand().equals("Get number"))
			{
				DectoBin dummy = new DectoBin();
				
				
				dummy.setDec(Integer.valueOf(decField.getText()));
				dummy.translateToBin();				
				
				binField.setText(dummy.getBin());
				
				BinToGray dummyTwo = new BinToGray(dummy);
				dummyTwo.Translate();
				
				grayField.setText(dummyTwo.getGray());
				mainPanel.repaint();
			}
		}
	}
}
