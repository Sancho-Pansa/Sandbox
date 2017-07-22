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
	private byte phaseCounter = 3;
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
		this.encounterArrange();
	}
	
	public void mythosPhase()
	{
		this.encounterArrange();
		PaintMythosEventsList event = new PaintMythosEventsList();
		event.handle(new ActionEvent());
	}
	

	private GridPane upkeepArrange()
	{
		GridPane gPane = new GridPane();
		gPane.setAlignment(Pos.CENTER);
		gPane.setHgap(15);
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
				Button btn = new Button("�����");
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
				Button btn = new Button("�����");
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
				Button btn = new Button("������");
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
				Button btn = new Button("��������");
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
		
		changeBars = new Button("�������� / �����");
		PaintInvestigatorsList action = new PaintInvestigatorsList();
		changeBars.setOnAction(action);
		changeBars.setMinWidth(165);
		
		mythosBars = new Button("������� ������");
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
		
		Button escapeMonster = this.createTipButton("Retreat", "���� �� ��������");
		Button horrorMonster = this.createTipButton("Horror", "�������� �����");
		Button fightMonster = this.createTipButton("Fight", "��� � ��������");
		Button monsterFeats = this.createTipButton("MonsterFeatures", "�������� ��������");
		
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
		
		Button escapeMonster = this.createTipButton("Retreat", "���� �� ��������");
		Button horrorMonster = this.createTipButton("Horror", "�������� �����");
		Button fightMonster = this.createTipButton("Fight", "��� � ��������");
		Button monsterFeats = this.createTipButton("MonsterFeatures", "�������� ��������");
		Button gateClose = this.createTipButton("GateClose", "�������� ����");
		Button gateSeal = this.createTipButton("GateSeal", "������������� ����");
		
		botPane.add(escapeMonster, 0, 0);
		botPane.add(horrorMonster, 1, 0);
		botPane.add(fightMonster, 2, 0);
		botPane.add(monsterFeats, 3, 0);
		
		botPane.add(this.changeBars, 0, 1);
		botPane.add(this.mythosBars, 1, 1);
		botPane.add(gateClose, 2, 1);
		botPane.add(gateSeal, 3, 1);
		
		
		this.upkeepPane.setCenter(gPane);
		this.upkeepPane.setBottom(botPane);
	}
	
	private void investigatorPaneArrange()
	{
		
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
		butt.setMinWidth(200);
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
		botPane.setMinHeight(75);
		
		return botPane;
	}
	
	private void changeBotPane(Label dummy)
	{
		dummy.setWrapText(true);
		GridPane botPane = createbotPane();
		botPane.setAlignment(Pos.CENTER);
		botPane.add(dummy, 0, 0);
		GridPane.setMargin(dummy, new Insets(0, 40, 0, 40));
		upkeepPane.setBottom(botPane);
		
	}
	
	@FXML
	public void nextPhase()
	{
		phaseCounter++;
		if(phaseCounter <= -1)
			this.phaseCounter = 4;
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
		
		private Button backBtn = new Button("�����");
		
		public TipBringer(String tipCode)
		{
			this.tipCode = tipCode;
			tipLabel.setWrapText(true);
			backBtn.setMinWidth(100);
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
		private Button backBtn = new Button("�����");
		
		public PaintInvestigatorsList()
		{
			backBtn.setMinWidth(535);
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
		
		private Button createButton(String label, String bar, Investigator invest)
		{
			Button butt = new Button(label);
			butt.setMinWidth(130);
			butt.setOnAction(new PaintPlusMinus(bar, invest));
			return butt;
		}
		
		@Override
		public void handle(ActionEvent event)
		{
			GridPane gPane = new GridPane();
			gPane.setAlignment(Pos.CENTER);
			gPane.setHgap(5);
			gPane.setVgap(5);
			
			for(int i = 0; i < cll.size(); i++)
			{
				Investigator dummy = cll.getAt(i).getContents();
				Label name = new Label(dummy.getName() + ": ");
				Button health = this.createButton("��������", "health", dummy);
				Button sanity = this.createButton("�����", "sanity", dummy);
				Button maxHealth = this.createButton("����. ��������", "maxhealth", dummy);
				Button maxSanity = this.createButton("����. �����", "maxsanity", dummy);
				Button bless = this.createButton("������", "bless", dummy);
				Button retain = this.createButton("�������", "retain", dummy);
				Button loan = this.createButton("����", "loan", dummy);
				Button sheriffBtn = this.createButton("����� �������", "sheriff", dummy);
				
				gPane.add(name, 0, 2 * i);
				gPane.add(health, 1, 2 * i);
				gPane.add(sanity, 2, 2 * i);
				gPane.add(maxHealth, 3, 2 * i);
				gPane.add(maxSanity, 4, 2 * i);
				gPane.add(bless, 1, 2 * i + 1);
				gPane.add(retain, 2, 2 * i + 1);
				gPane.add(loan, 3, 2 * i + 1);
				gPane.add(sheriffBtn, 4, 2 * i + 1);
			}
			gPane.add(backBtn, 1, cll.size() * 2);
			GridPane.setColumnSpan(backBtn, 4);
			
			gPane.setBorder(createStandartBorder());
			upkeepPane.setCenter(gPane);
		}
		
	}
	
	private class PaintPlusMinus implements EventHandler<ActionEvent>
	{
		private String barName;
		private Investigator invest;
		private Button plus;
		private Button minus;
		private Button backBtn;
		
		public PaintPlusMinus(String barName, Investigator invest)
		{
			this.barName = barName;
			this.invest = invest;
			
			plus = new Button("+");
			plus.setMinWidth(100);
			plus.setOnAction(new PlusMinus(this.barName, true, this.invest));
			
			minus = new Button("-");
			minus.setMinWidth(100);
			minus.setOnAction(new PlusMinus(this.barName, false, this.invest));
			
			backBtn = new Button("�����");
			backBtn.setMinWidth(225);
			backBtn.setOnAction(new PaintInvestigatorsList());
		}
		
		@Override
		public void handle(ActionEvent event) 
		{
			GridPane gPane = new GridPane();
			gPane.setAlignment(Pos.CENTER);
			gPane.setHgap(25);
			gPane.setVgap(100);
			gPane.add(minus, 0, 0);
			gPane.add(plus, 1, 0);
			gPane.add(backBtn, 0, 1);
			GridPane.setColumnSpan(backBtn, 2);
			
			gPane.setBorder(createStandartBorder());
			
			upkeepPane.setCenter(gPane);
		}
	}
	
	private class PlusMinus implements EventHandler<ActionEvent>
	{
		private String stat;
		private boolean plus;
		private Investigator invest;
		
		public PlusMinus(String stat, boolean plus, Investigator invest)
		{
			this.stat = stat;
			this.plus = plus;
			this.invest = invest;
		}
		
		private void checkVitals()
		{
			if((this.invest.getSanity() <= 0 && this.invest.getHealth() <= 0) || invest.getMaxHealth() <= 0 || invest.getMaxSanity() <= 0)
			{
				this.invest.killInvest();
				Label dummy = new Label("����� " + invest.getName() + " c�����.");
				dummy.setFont(new Font(24));
				dummy.setTextFill(Color.RED);
				changeBotPane(dummy);
				return;
			}
				
			if(this.invest.getHealth() <= 0)
			{
				this.invest.discardRetain();
				this.invest.heal();
				Label dummy = new Label("����� " + invest.getName() + " ������� ��������. "
						+ " ���� ����� ������ ������ �������� ����� �� ����� (���������� ����), "
						+ "� ����� ����� ��������, � ����� ������������� � �������� ��. �����.\n"
						+ "���� �� � ���� ����, �� ������� �� ������� � ������������.");
				changeBotPane(dummy);
			}
			
			if(this.invest.getSanity() <= 0)
			{
				this.invest.discardRetain();
				this.invest.addSanity();
				Label dummy = new Label("����� " + invest.getName() + " ���� � �������. "
						+ " ���� ����� ������ ������ �������� ����� �� ����� (���������� ����), "
						+ "� ����� ����� ��������, � ����� ������������� � ���������� ���������.\n"
						+ "���� �� � ���� ����, �� ������� �� ������� � ������������.");
				changeBotPane(dummy);
			}
			
		}
		
		
		
		public void handle(ActionEvent event)
		{
			switch(this.stat)
			{
			case "health":
				if(plus)
					this.invest.heal();
				else
				{
					this.invest.damage();
				}
				break;
			case "sanity":
				if(plus)
					this.invest.addSanity();
				else
					this.invest.minusSanity();
				break;
			case "maxhealth":
				if(!plus)
					this.invest.minusMaxHealth();
				break;
			case "maxsanity":
				if(!plus)
					this.invest.minusMaxSanity();	
				break;
			case "bless":
				if(plus)
					this.invest.bless();
				else
					this.invest.curse();
				break;
			case "retain":
				if(plus)
					this.invest.setRetain();
				else
					this.invest.discardRetain();
				break;
			case "loan":
				if(plus)
					this.invest.setLoan();
				if(plus)
					this.invest.discardLoan();
				break;
			case "sheriff":
				if(plus)
				{
					this.invest.setSheriff();
					sheriff = this.invest;
				}
				else
					this.invest.setTwilight();
				break;
			}
			this.checkVitals();
		}
		
	}
	
	private class PaintMythosEventsList implements EventHandler<ActionEvent>
	{
		private Button backBtn = new Button("�����");
		
		public PaintMythosEventsList()
		{
			backBtn.setMinWidth(430);
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
			gPane.setHgap(30);
			gPane.setVgap(10);

			gPane.add(createMythosEventsButton("doom", "minus", "������� �������������"), 0, 0);
			gPane.add(createMythosEventsButton("doom", "plus", "��������� �������������"), 1, 0);
			gPane.add(createMythosEventsButton("terror", "minus", "������� ����"), 0, 1);
			gPane.add(createMythosEventsButton("terror", "plus", "��������� ����"), 1, 1);
			gPane.add(createMythosEventsButton("gate", "open", "������� �����"), 0, 2);
			gPane.add(createMythosEventsButton("gate", "surge", "��������� ��������"), 1, 2);
			gPane.add(createMythosEventsButton("gate", "close", "������� �����"), 0, 3);
			gPane.add(createMythosEventsButton("gate", "seal", "���������� �����"), 1, 3);
			Button elderSign = createMythosEventsButton("gate", "eldersign", "���� �������");
			elderSign.setMinWidth(430);
			gPane.add(elderSign, 0, 4);
			GridPane.setColumnSpan(elderSign, 2);
			
			gPane.add(createMythosEventsButton("monster", "spawn", "�������� �������"), 0, 6);
			gPane.add(createMythosEventsButton("monster", "kill", "����� �������"), 1, 6);

			gPane.add(backBtn, 0, 12);
			GridPane.setColumnSpan(backBtn, 2);
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
		
		private void aftermathCheck()
		{
			String report = new String("");
			
			if(fw.isAwaken())
			{
				Label dummy = new Label(fw.getAncientOne().getName() + " ����������!");
				dummy.setFont(new Font(24));
				dummy.setTextFill(Color.RED);
				changeBotPane(dummy);
				return;
			}
			
			if(fw.getElderSignsOnMap() >= 6)
			{
				Label dummy = new Label("�� ��������!\n�� ����� ���� 6 ������ �������, � ������ ������ "
						+ "��������� �� ������������� ������ ��������.");
				dummy.setFont(new Font(20));
				dummy.setTextFill(Color.GREEN);
				changeBotPane(dummy);
				return;
			}
			
			if(action.equals("surge"))
			{
				int monsters = 0;
				if(fw.getPlayers() > fw.getGateNum())
					monsters = fw.getPlayers();
				else
					monsters = fw.getGateNum();
				report = "��������� ��������! ������������ �� �������� ������ " + monsters + " ��������.\n";
			}
			
			report += (fw.getAncientOne().getAwakening() - fw.getDoomTrack()) + " �������� �� ����������� ��������.\n";
			
			if(fw.isMapLimit())
				report += "��������� ����� �������� �� �����, ��������� ������� �� �������.\n";
			
			if(fw.isOutskirtsLimit())
				report += "��������� ������ ������. ������� ���� �������� � ������ � ��� (� ��� ����� � ����).\n";
			
			if(fw.isTerrorRaised())
				report += "������� ����� ���������. ������� �� ������ ��������� 1 �����.\n";
			
			if(fw.getTerrorLevel() >= 3)
			{
				report += "������ �������";
				if(fw.getTerrorLevel() >= 6)
				{
					report += ", ����� ����������";
					if(fw.getTerrorLevel() >= 9)
						report += " � \"��������� ����� ������\"";
				}
				report += ".\n";
				System.out.println(report);
			}
			
			if(fw.getTerrorLevel() >= 10)
			{
				fw.cancelOutskirtsLimit();
				report += "������� ����� ��������� ���������. ������� ���� �������� � ������ � ���."
						+ " ����� �������� �� �������� ������ �� ���������.\n";
			}
			
			Label dummy = new Label(report);
			changeBotPane(dummy);
			
		}
		
		@Override
		public void handle(ActionEvent event)
		{
			if(mythos.equals("gate"))
			{
				switch(action)
				{
				case "close":
					fw.closeGate();
					break;
				case "seal":
					fw.sealGate(false);
					break;
				case "eldersign":	
					fw.sealGate(true);
					break;
				case "open":
					fw.createGate(false);
					break;
				case "surge":	
					fw.createGate(true);
					break;
				}
			}
			
			if(mythos.equals("monster"))
			{
				if(action.equals("spawn"))
					fw.spawnMonster();
				if(action.equals("kill"))
					fw.killMonster();
			}
			
			if(mythos.equals("doom"))
			{
				if(action.equals("plus"))
					fw.addDoom();
				if(action.equals("minus"))
					fw.minusDoom();
			}
			
			if(mythos.equals("terror"))
			{
				if(action.equals("plus"))
				{
					fw.addTerrorLevel();
					System.out.println(fw.getTerrorLevel());
				}
				if(action.equals("minus"))
					fw.minusTerrorLevel();
				
			}
			
			aftermathCheck();
		}
	}
	
}
