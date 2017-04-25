package gui;

import core.Interpreter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame
{
	private static final long serialVersionUID = 7352018073986710489L;
	
	private final int WIDTH = 640;
	private final int HEIGHT = 480;
	
	private JPanel mainPanel;
	private JPanel textPanel; 
	
	private JTextArea inputText;
	private JTextArea outputText;
	private JButton doButton;
	private ButtonListener buttonAct;
	
	private Dimension windSize;
	private Dimension windText;
	
	private Interpreter dummy;
	
	public MainGUI()
	{
		windSize = new Dimension(WIDTH, HEIGHT);
		windText = new Dimension(400, 75);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		this.setTitle("Hog Latin Trascription");
		this.setSize(windSize);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().add(mainPanel);
				
		this.setupFrame();
		
		this.setVisible(true);
	}
	
	private void setupFrame()
	{
		textPanel = new JPanel();
		textPanel.setLayout(null);
		
		buttonAct = new ButtonListener();
		
		inputText = new JTextArea();
		inputText.setToolTipText("Enter original sentence here");
		inputText.setSize(windText);
		inputText.setLocation(75, 100);
		
		outputText = new JTextArea();
		outputText.setEditable(false);
		outputText.setSize(windText);
		outputText.setLocation(75, 300);
		
		doButton = new JButton("Translate");
		doButton.setActionCommand("July! Do the thing");
		doButton.addActionListener(buttonAct);
		
		textPanel.add(inputText);
		textPanel.add(outputText);
		
		mainPanel.add(textPanel, BorderLayout.CENTER);
		mainPanel.add(doButton, BorderLayout.LINE_END);
		
		setContentPane(mainPanel);
	}
	
	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getActionCommand().equals("July! Do the thing"))
			{
				dummy = new Interpreter(inputText.getText());
				outputText.setText(dummy.getHog());
				mainPanel.repaint();
			}
		}
		
	}
	
	public static void main(String[] args) 
	{
		new MainGUI();
	}

}
