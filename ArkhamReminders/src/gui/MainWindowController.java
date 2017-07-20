package gui;

import list.CircularLinkedList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import mechanics.*;
import messages.Messager;

public final class MainWindowController
{
	private Framework fw;
	private byte phaseCounter = 0;
	
	private CircularLinkedList<Investigator> cll;
	
	@FXML
	private Label phaseLabel;	
	
	@FXML
	private BorderPane upkeepPane;//, movementPane, encounterPane, otherWorldsPane, mythosPane;

	public void upkeepPhase()
	{
		cll.getAt(1).getContents().bless();
		cll.getAt(3).getContents().bless();
		Button btn = new Button("Sign");
		GridPane gPane = new GridPane();
		gPane.setAlignment(Pos.CENTER);
		gPane.setHgap(25);
		gPane.setVgap(10);
		int row = 0;
		for(int i = 0; i < cll.size(); i++)
		{
			Investigator current = cll.getContent(cll.getAt(i));
			if(current.isBlessed())
			{
				gPane.add(new Label(current.getName() + ": "), 0, row);
				gPane.add(new Label(Messager.getMessage("Bless")), 1, row);
				gPane.add(new Button("Foo"), 2, row);
				row++;
			}
		}
		
		upkeepPane.setCenter(gPane);
		//this.upkeepPane.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	public void movementPhase()
	{
		this.upkeepPane.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		upkeepPane.setCenter(new Label("a"));
	}
	
	public void encounterPhase()
	{
		this.upkeepPane.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		upkeepPane.setCenter(new Label("b"));
	}
	
	public void otherWorldPhase()
	{
		this.upkeepPane.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	public void mythosPhase()
	{
		this.upkeepPane.setBackground(new Background(new BackgroundFill(Color.MAGENTA, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	public void setFramework(Framework fw)
	{ 
		this.fw = fw;
		this.cll = fw.getCList();
		this.arrangeButtons();
	}

	@FXML
	public void nextPhase()
	{
		phaseCounter++;
		this.phaseLabel.setText(Messager.PHASE_LIST[phaseCounter]);
		switch(phaseCounter)
		{
		case 0:
			this.upkeepPhase();
			break;
		case 1:
			this.movementPhase();
			break;
		case 2:
			this.encounterPhase();
			break;
		case 3:
			this.otherWorldPhase();
			break;
		case 4:
			this.mythosPhase();
			break;
		}
		if(phaseCounter >= 4)
			phaseCounter = -1;
	}
	
	private void arrangeButtons()
	{
		
	}
	
}
