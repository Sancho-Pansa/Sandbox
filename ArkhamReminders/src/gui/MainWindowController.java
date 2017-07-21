package gui;

import list.CircularLinkedList;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import mechanics.*;
import messages.Messager;

public final class MainWindowController
{
	private Framework fw;
	private byte phaseCounter = 0;
	private Label tipLabel;
	
	private CircularLinkedList<Investigator> cll;
	
	@FXML
	private Label phaseLabel;
	
	@FXML
	private BorderPane upkeepPane;

	public void setFramework(Framework fw)
	{ 
		this.fw = fw;
		this.cll = fw.getCList();
		this.tipLabel = new Label();
		tipLabel.setWrapText(true);
	}
	
	public void upkeepPhase()
	{
		GridPane gPane = this.upkeepArrange();
		upkeepPane.setCenter(gPane);
		HBox hb = new HBox();
		upkeepPane.setBottom(hb);
	}
	
	public void movementPhase()
	{
		
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
	

	private GridPane upkeepArrange()
	{
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
				Label dummy = new Label(Messager.getMessage("Blessed"));
				dummy.setWrapText(true);
				dummy.setMinWidth(400);
				dummy.setMaxWidth(400);
				gPane.add(dummy, 1, row);
				Button btn = new Button("Снять");
				btn.setOnAction(new ChangeState(i, "Curse"));
				gPane.add(btn, 2, row);
				row++;
			}
		}
		
		row += 2;
		for(int i = 0; i < cll.size(); i++)
		{
			Investigator current = cll.getAt(i).getContents();
			if(current.isCursed())
			{
				gPane.add(new Label(current.getName() + ": "), 0, row);
				Label dummy = new Label(Messager.getMessage("Cursed"));
				dummy.setWrapText(true);
				dummy.setMinWidth(400);
				dummy.setMaxWidth(400);
				gPane.add(dummy, 1, row);
				Button btn = new Button("Снять");
				btn.setOnAction(new ChangeState(i, "Bless"));
				gPane.add(btn, 2, row);
				row++;
			}
		}
		
		row += 2;
		for(int i = 0; i < cll.size(); i++)
		{
			Investigator current = cll.getAt(i).getContents();
			if(current.hasRetain())
			{
				gPane.add(new Label(current.getName() + ": "), 0, row);
				Label dummy = new Label(Messager.getMessage("Retain"));
				dummy.setWrapText(true);
				dummy.setMinWidth(400);
				dummy.setMaxWidth(400);
				gPane.add(dummy, 1, row);
				Button btn = new Button("Убрать");
				btn.setOnAction(new ChangeState(i, "Retain"));
				gPane.add(btn, 2, row);
				row++;
			}
		}
		row += 2;
		for(int i = 0; i < cll.size(); i++)
		{
			Investigator current = cll.getAt(i).getContents();
			if(current.hasRetain())
			{
				gPane.add(new Label(current.getName() + ": "), 0, row);
				Label dummy = new Label(Messager.getMessage("Loan"));
				dummy.setWrapText(true);
				dummy.setMinWidth(400);
				dummy.setMaxWidth(400);
				gPane.add(dummy, 1, row);
				Button btn = new Button("Нищеброд");
				btn.setOnAction(new ChangeState(i, "Loan"));
				gPane.add(btn, 2, row);
				row++;
			}
		}
		gPane.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
		return gPane;		
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
	
	private class ChangeState implements EventHandler<ActionEvent>
	{
		private int num;
		private String stateName;
		
		public ChangeState(int num, String stateName)
		{
			this.num = num;
			this.stateName = stateName;
		}
		
		@Override
		public void handle(ActionEvent event)
		{
			switch(this.stateName)
			{
			case "Curse":
				cll.getAt(num).getContents().curse();
				break;
			case "Bless":
				cll.getAt(num).getContents().bless();
				break;
			case "Retain":
				cll.getAt(num).getContents().discardRetain();
				break;
			case "Loan":
				cll.getAt(num).getContents().discardLoan();
				break;
			}			
		}
	}
	
	private class bringTips implements EventHandler<ActionEvent>
	{
		private String tipCode;
		
		public bringTips(String tipCode)
		{
			this.tipCode = tipCode;
		}

		@Override
		public void handle(ActionEvent event) 
		{
			switch(tipCode)
			{
			case "Retreat":
				break;
			}
		}
		
	}
	
}
