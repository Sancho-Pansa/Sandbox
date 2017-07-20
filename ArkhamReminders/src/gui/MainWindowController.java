package gui;

import list.CircularLinkedList;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXML;
import mechanics.*;

public final class MainWindowController
{
	private Framework fw;
	
	private CircularLinkedList<Investigator> cll;
	
	@FXML
	private Label dummyLabel;
	
	public void setFramework(Framework fw)
	{ 
		this.fw = fw;
		this.cll = fw.getCList();
	}
	
	
	@FXML
	private BorderPane upkeepPane;//, movementPane, encounterPane, otherWorldsPane, mythosPane;
	/*
	@FXML
	private Label upkeepTips;*/
	/*
	public MainWindowController(Framework fw, Scene scene)
	{
		this.fw = fw;
		this.scene = scene;
		
	}*/
	
	/*
	public final void arrangeWindow()
	{
		
	}*/
}
