package gui;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import mechanics.*;

public class GUIClass extends Application
{
	private final int WINDOW_HEIGHT = 960;
	private final int WINDOW_WIDTH = 1360;
	
	private Stage primaryStage;
	private Scene scene;
	private Label label;
	
	// These variables are for first window
	private BorderPane bPane;
	private GridPane gPane;
	private HBox bigBox;
	
	private TextField playerNumField;
	private Button okButton;
	
	private Framework fw;
	
	// These variable are used in player name window
	
	ArrayList<Label> ARLabel;
	ArrayList<TextArea> ARtext = new ArrayList<>();
	TextArea ancientTArea;
	
	// These variable are for main window (after input of players)
	private SplitPane sPane;
	private TitledPane investPane;
	private TitledPane ancientPane;
	private Accordion accord;
	private GridPane innerPane1;
	private GridPane innerPane2;
	
	private BorderPane upkeepPane;
	private GridPane upkeepTop;
	private BorderPane movementPane;
	private BorderPane encounterPane;
	private BorderPane otherWorldPane;
	private BorderPane mythosPane;
	
	@Override
	public void start(Stage primaryStage)
	{
		bPane = new BorderPane();
		gPane = new GridPane();
		gPane.setMaxSize(500, 50);
		//TODO Delete this number after tests!
		playerNumField = new TextField("1");
		playerNumField.setMaxHeight(50);
		
		okButton = new Button("OK");
		okButton.setMinWidth(100);
		okButton.setOnAction(this::PlayerNumEntered);
		
		label = new Label("Добро пожаловать в Arkham Reminders. Введите число игроков.");
		label.setTextAlignment(TextAlignment.CENTER);
		label.setFont(new Font(32));
		label.setPadding(new Insets(50, 0, 0, 200));
		
		gPane.add(new Label("Число игроков:"), 0, 0);
		gPane.add(playerNumField, 1, 0);
		gPane.add(okButton, 1, 1);
		gPane.setVgap(20);
		gPane.setHgap(10);
		
		bPane.setCenter(gPane);
		bPane.setBorder(new Border((new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1, 0, 0, 0)))));
		bPane.setTop(label);
		
		scene = new Scene(bPane, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		primaryStage.setTitle("Arkham Reminders");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
		this.primaryStage = primaryStage;
	}
	/*
	public static void main(String[] args)
	{
		GUIClass.launch(args);
	}*/
	
	private void inputInvestigatorsWindow()
	{
		bPane = new BorderPane();
		gPane = new GridPane();
		
		label = new Label("Введите имена сыщиков и Древнего");
		label.setFont(new Font(24));
		label.setPadding(new Insets(20, 20, 40, 500));
		
		ARLabel = new ArrayList<>();
		ancientTArea = new TextArea("Йог-Сотот");
		 
		for(int i = 0; i < fw.getPlayers(); i++)
		{
			ARLabel.add(new Label("Игрок " + (i + 1) + ": "));
			gPane.add(ARLabel.get(i), 0, i);
			//TODO Delete string in constructor after tests!
			TextArea dummy = new TextArea("Аманда");
			dummy.setMaxHeight(35);
			dummy.setMinHeight(35);
			ARtext.add(dummy);
			gPane.add(ARtext.get(i), 1, i);
		}		

		gPane.add(new Label("Древний: "), 0, fw.getPlayers() + 10);
		ancientTArea.setMaxHeight(35);
		ancientTArea.setMinHeight(35);
		gPane.add(ancientTArea, 1, fw.getPlayers() + 10);
		
		okButton = new Button("OK");
		Button backBtn = new Button("Назад");
		okButton.setOnAction(this::playerNamesEntered);
		backBtn.setOnAction(this::backPressed);
		gPane.add(backBtn, 0, fw.getPlayers() + 11);
		gPane.add(okButton, 1, fw.getPlayers() + 11);
		
		gPane.setVgap(10);
		gPane.setHgap(20);
		gPane.setAlignment(Pos.TOP_CENTER);
		
		bPane.setTop(label);
		bPane.setCenter(gPane);
		bPane.setBorder(new Border((new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1, 0, 0, 0)))));
		scene.setRoot(bPane);
	}
	
	private void createMainWindow()
	{
		upkeepPane = new BorderPane();
		upkeepTop = new GridPane();
		label  = new Label("Some text  ");
		upkeepPane.setCenter(label);
		
		bigBox = new HBox();
		bPane = upkeepPane;
		this.arrangeAccordion();
		
		sPane = new SplitPane(accord, bPane);
		sPane.setDividerPositions(0.4);

        accord.maxWidthProperty().bind(sPane.widthProperty().multiply(0.4));
        accord.minWidthProperty().bind(sPane.widthProperty().multiply(0.4));
		
		this.arrangeRightBorderPane();
		
		bigBox.getChildren().add(sPane);
		bigBox.setBorder(new Border((new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1, 0, 0, 0)))));
		
		scene = new Scene(bigBox, WINDOW_WIDTH, WINDOW_HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void arrangePhases()
	{
		this.upkeepPane = new BorderPane();
		
	}
	
	private void arrangeRightBorderPane()
	{
		
		
		
	}
	
	private void arrangeAccordion()
	{
		innerPane1 = new GridPane();
		innerPane2 = new GridPane();
		investPane = new TitledPane("Сыщики", innerPane1);
		ancientPane = new TitledPane("Древний", innerPane2);
		accord = new Accordion(investPane, ancientPane);
	}
	
	private void PlayerNumEntered(ActionEvent e)
	{
		if(playerNumField.getText().isEmpty())
				return;
			else 
			{
				fw = new Framework(new Integer(playerNumField.getText()));
				inputInvestigatorsWindow();
			}
	}
	
	private void playerNamesEntered(ActionEvent event)
	{
		ArrayList<String> dummy = new ArrayList<>();
		for(TextArea x : this.ARtext)
		{
			if(x.getText().isEmpty() || this.ancientTArea.getText().isEmpty())
				return;
			dummy.add(x.getText());
		}
		
		fw.setInvestigators(dummy);
		fw.setAncientOne(this.ancientTArea.getText());
		this.createMainWindow();
	}
	
	private void backPressed(ActionEvent event)
	{
		this.start(primaryStage);
	}
}
