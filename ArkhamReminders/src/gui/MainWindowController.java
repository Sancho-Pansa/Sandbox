package gui;

import list.CircularLinkedList;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
	private Investigator sheriff = null;
	
	private Button changeBars;
	private Button mythosBars;
	
	private CircularLinkedList<Investigator> cll;
	
	@FXML
	private Label phaseLabel;
	
	@FXML
	private BorderPane upkeepPane;
	
	//private GridPane botPane;

	public void setFramework(Framework fw)
	{ 
		this.fw = fw;
		this.cll = fw.getCList();
		this.tipLabel = new Label();
	}
	
	public void upkeepPhase()
	{
		GridPane gPane = this.upkeepArrange();
		upkeepPane.setCenter(gPane);
	}
	
	public void movementPhase()
	{
		this.movementArrange();
	}
	
	public void encounterPhase()
	{
		this.encounterArrange();
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
		gPane.setHgap(15);
		gPane.setVgap(10);
		
		if(sheriff == null)
			sheriff = this.findSheriff();
		
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
			if(current.hasLoan())
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
		
		row += 2;
		
		if(!(sheriff == null))
		{
			gPane.add(new Label(sheriff.getName() + ": "), 0, row);
			Label dummy = new Label(Messager.getMessage("Sheriff"));
			dummy.setWrapText(true);
			dummy.setMinWidth(400);
			dummy.setMaxWidth(400);
			gPane.add(dummy, 1, row);
		}
		
		gPane.setBorder(createStandartBorder());
		HBox hb = new HBox();
		
		changeBars = new Button("Здоровье / разум");
		PaintInvestigatorsList action = new PaintInvestigatorsList();
		changeBars.setOnAction(action);
		changeBars.setMinWidth(165);
		
		mythosBars = new Button("Врата / монстры");
		PaintMythosEventsList mythosaction = new PaintMythosEventsList();
		mythosBars.setOnAction(mythosaction);
		mythosBars.setMinWidth(165);
		
		hb.getChildren().add(changeBars);
		hb.setAlignment(Pos.CENTER);
		hb.setMinHeight(67);
		upkeepPane.setBottom(hb);
		
		return gPane;
	}
	
	private void movementArrange()
	{
		GridPane gPane = new GridPane();
		gPane.setAlignment(Pos.CENTER);
		gPane.setHgap(15);
		gPane.setVgap(10);
		
		gPane.setPadding(new Insets(0, 20, 0, 20));
		
		this.tipLabel = new Label();
		
		if(!(this.sheriff == null))
		{
			tipLabel.setText(Messager.getMessage("SheriffCar"));
			gPane.add(new Label(this.sheriff.getName() + ": "), 0, 0);
			gPane.add(tipLabel, 0, 1);
		}
			
		gPane.setBorder(createStandartBorder());
		
		GridPane botPane = this.createbotPane();
		
		Button escapeMonster = this.createTipButton("Retreat", "Уход от монстров");
		Button horrorMonster = this.createTipButton("Horror", "Проверка ужаса");
		Button fightMonster = this.createTipButton("Fight", "Бой с монстром");
		Button monsterFeats = this.createTipButton("MonsterFeatures", "Свойства монстров");
		
		botPane.add(escapeMonster, 0, 0);
		botPane.add(horrorMonster, 1, 0);
		botPane.add(fightMonster, 2, 0);
		botPane.add(monsterFeats, 3, 0);
		
		botPane.add(changeBars, 0, 1);
		botPane.add(mythosBars, 1, 1);
		
		this.upkeepPane.setBottom(botPane);
		this.upkeepPane.setCenter(gPane);
	}
	
	private void encounterArrange()
	{
		GridPane gPane = new GridPane();
		gPane.setAlignment(Pos.CENTER);
		gPane.setHgap(15);
		gPane.setVgap(10);
		gPane.setBorder(this.createStandartBorder());
		int row = 0;
		
		this.tipLabel = new Label();
		for(int i = 0; i < cll.size(); i++)
		{
			Investigator current = cll.getAt(i).getContents();
			if(current.isTwilight())
			{
				gPane.add(new Label(current.getName() + ": "), 0, row);
				Label dummy = new Label(Messager.getMessage("Twilight"));
				dummy.setWrapText(true);
				dummy.setMinWidth(400);
				dummy.setMaxWidth(400);
				gPane.add(dummy, 1, row);
				row++;
			}
		}
		
		GridPane botPane = this.createbotPane();
		
		Button escapeMonster = this.createTipButton("Retreat", "Уход от монстров");
		Button horrorMonster = this.createTipButton("Horror", "Проверка ужаса");
		Button fightMonster = this.createTipButton("Fight", "Бой с монстром");
		Button monsterFeats = this.createTipButton("MonsterFeatures", "Свойства монстров");
		Button gateClose = this.createTipButton("GateClose", "Закрытие Врат");
		Button gateSeal = this.createTipButton("GateSeal", "Запечатывание Врат");
		
		botPane.add(escapeMonster, 0, 0);
		botPane.add(horrorMonster, 1, 0);
		botPane.add(fightMonster, 2, 0);
		botPane.add(monsterFeats, 3, 0);
		
		botPane.add(this.changeBars, 0, 1);
		botPane.add(gateClose, 1, 1);
		botPane.add(gateSeal, 2, 1);
		
		botPane.add(this.mythosBars, 3, 1);
		
		this.upkeepPane.setCenter(gPane);
		this.upkeepPane.setBottom(botPane);
	}
	
	private Investigator findSheriff()
	{
		for(int i = 0; i < cll.size(); i++)
		{
			if(cll.getAt(i).getContents().isSheriff())
			{
				return cll.getAt(i).getContents();
			}
		}
		
		return null;
	}
	
	private Button createTipButton(String code, String label)
	{
		TipBringer tBringer = new TipBringer(code);
		Button butt = new Button(label);
		butt.setOnAction(tBringer);
		butt.setMinWidth(165);
		return butt;
	}
	
	private Button createMythosEventsButton(String object, String code, String label)
	{
		MythosEvents mythosEvent = new MythosEvents(code, object);
		Button butt = new Button(label);
		butt.setOnAction(mythosEvent);
		butt.setMinWidth(165);
		return butt;
	}
	
	private Border createStandartBorder()
	{
		return new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3)));

	}
	
	private GridPane createbotPane()
	{
		GridPane botPane = new GridPane();
		botPane.setHgap(5);
		botPane.setVgap(5);
		botPane.setAlignment(Pos.BOTTOM_CENTER);
		botPane.setMinHeight(50);
		
		return botPane;
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
	
	private class TipBringer implements EventHandler<ActionEvent>
	{
		private String tipCode;
		
		private Button backBtn = new Button("Назад");
		
		public TipBringer(String tipCode)
		{
			this.tipCode = tipCode;
			tipLabel.setWrapText(true);
			backBtn.setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent event) 
				{
					movementArrange();
				}
				
			});
		}

		@Override
		public void handle(ActionEvent event) 
		{
			tipLabel.setText(Messager.getTip(tipCode));
			tipLabel.setFont(new Font(18));
			VBox vBox = new VBox();
			vBox.setAlignment(Pos.CENTER);
			vBox.getChildren().add(tipLabel);
			vBox.setPadding(new Insets(0, 20, 0, 20));
			vBox.getChildren().add(this.backBtn);
			vBox.setBorder(createStandartBorder());
			upkeepPane.setCenter(vBox);
		}
		
	}
	
	private class PaintInvestigatorsList implements EventHandler<ActionEvent>
	{
		private Button backBtn = new Button("Назад");
		
		public PaintInvestigatorsList()
		{
			backBtn.setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent event) 
				{
					phaseCounter--;
					nextPhase();
				}
				
			});
		}
		
		@Override
		public void handle(ActionEvent event)
		{
			GridPane gPane = new GridPane();
			gPane.setAlignment(Pos.CENTER);
			gPane.setHgap(5);
			gPane.setVgap(10);
			
			for(int i = 0; i < cll.size(); i++)
			{
				Label name = new Label(cll.getAt(i).getContents().getName() + ": ");
				Button incHealth = new Button("Здоровье+");
				incHealth.setMinWidth(50);
				incHealth.setOnAction(new ChangeHealthSanity("health", "increase", cll.getAt(i).getContents()));
				Button decHealth = new Button("Здоровье-");
				decHealth.setMinWidth(50);
				decHealth.setOnAction(new ChangeHealthSanity("health", "decrease", cll.getAt(i).getContents()));
				Button incSanity = new Button("Разум+");
				incSanity.setMinWidth(50);
				incSanity.setOnAction(new ChangeHealthSanity("sanity", "increase", cll.getAt(i).getContents()));
				Button decSanity = new Button("Разум-");
				decSanity.setMinWidth(50);
				decSanity.setOnAction(new ChangeHealthSanity("sanity", "decrease", cll.getAt(i).getContents()));
				
				gPane.add(name, 0, i);
				gPane.add(incHealth, 1, i);
				gPane.add(incSanity, 2, i);
				gPane.add(decHealth, 3, i);
				gPane.add(decSanity, 4, i);
			}
			
			gPane.add(backBtn, 2, cll.size() + 1);
			
			gPane.setBorder(createStandartBorder());
			upkeepPane.setCenter(gPane);
		}
		
	}
	
	private class ChangeHealthSanity implements EventHandler<ActionEvent>
	{
		private String barName;
		private String action;
		private Investigator invest;
		
		public ChangeHealthSanity(String barName, String action, Investigator invest)
		{
			this.barName = barName;
			this.action = action;
			this.invest = invest;
		}
		
		@Override
		public void handle(ActionEvent event) 
		{
			if(action.equals("increase"))
			{
				if(barName.equals("health"))
					invest.heal();
				if(barName.equals("sanity"))
					invest.restoreSanity();
			}
			
			if(action.equals("decrease"))
			{
				if(barName.equals("health"))
					invest.damage();
				if(barName.equals("sanity"))
				{
					invest.decreaseSanity();
				}
			}
		}
	}
	
	private class PaintMythosEventsList implements EventHandler<ActionEvent>
	{
		private Button backBtn = new Button("Назад");
		
		public PaintMythosEventsList()
		{
			backBtn.setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent event) 
				{
					phaseCounter--;
					nextPhase();
				}
				
			});
		}
		
		@Override
		public void handle(ActionEvent event)
		{
			GridPane gPane = new GridPane();
			gPane.setAlignment(Pos.CENTER);
			gPane.setHgap(20);
			gPane.setVgap(10);
			
			gPane.add(createMythosEventsButton("gate", "open", "Открыть Врата"), 0, 0);
			gPane.add(createMythosEventsButton("gate", "surge", "Нашествие монстров"), 1, 0);
			gPane.add(createMythosEventsButton("gate", "close", "Закрыть Врата"), 0, 1);
			gPane.add(createMythosEventsButton("gate", "seal", "Запечатать Врата"), 1, 1);
			gPane.add(createMythosEventsButton("gate", "eldersign", "Знак Древних"), 0, 2);
			
			gPane.add(createMythosEventsButton("monster", "spawn", "Добавить монстра"), 0, 3);
			gPane.add(createMythosEventsButton("monster", "kill", "Убить монстра"), 1, 3);

			gPane.add(backBtn, 1, 5);
			
			gPane.setBorder(createStandartBorder());
			upkeepPane.setCenter(gPane);
		}
		
	}
	
	private class MythosEvents implements EventHandler<ActionEvent>
	{
		private String action;
		private String mythos;

		public MythosEvents(String action, String mythos)
		{
			this.action = action;
			this.mythos = mythos;
		}
		
		@Override
		public void handle(ActionEvent event)
		{
			if(mythos.equals("gate"))
			{
				if(action.equals("close"))
					fw.closeGate();
				if(action.equals("seal"))
					fw.sealGate(false);
				if(action.equals("eldersign"))
					fw.sealGate(true);
				if(action.equals("open"))
					fw.createGate(false);
				if(action.equals("surge"))
					fw.createGate(true);
			}
			
			if(mythos.equals("monster"))
			{
				if(action.equals("spawn"))
					fw.spawnMonster();
				if(action.equals("kill"))
					fw.killMonster();
			}
		}
		
	}
	
}
