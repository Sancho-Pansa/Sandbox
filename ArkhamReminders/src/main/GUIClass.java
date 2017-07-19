package main;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Insets;
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
	private Button playerBtn;
	
	private Framework fw;
	
	// These variable are for main window (after input of players)
	private SplitPane sPane;
	private TitledPane investPane;
	private TitledPane ancientPane;
	private Accordion accord;
	private GridPane innerPane1;
	private GridPane innerPane2;
	
	@Override
	public void start(Stage primaryStage)
	{
		bPane = new BorderPane();
		gPane = new GridPane();
		gPane.setMaxSize(500, 50);
		
		playerNumField = new TextField();
		playerNumField.setMaxHeight(50);
		
		playerBtn = new Button("OK");
		playerBtn.setMinWidth(100);
		playerBtn.setOnAction(this::PlayerNumEntered);
		
		label = new Label("Добро пожаловать в Arkham Reminders. Введите число игроков.");
		label.setTextAlignment(TextAlignment.CENTER);
		label.setFont(new Font(32));
		label.setPadding(new Insets(50, 0, 0, 200));
		
		gPane.add(new Label("Number of players:"), 0, 0);
		gPane.add(playerNumField, 1, 0);
		gPane.add(playerBtn, 1, 1);
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
	
	public static void main(String[] args)
	{
		GUIClass.launch(args);
	}
	
	private void inputInvestigatorsWindow()
	{
		bPane = new BorderPane();
		gPane = new GridPane();
	}
	
	private void createMainWindow()
	{
		bigBox = new HBox();
		
		this.arrangeRightBorderPane();
		this.arrangeAccordion();
		
		sPane = new SplitPane(accord, bPane);
		
		bigBox.getChildren().add(sPane);
		bigBox.setBorder(new Border((new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1, 0, 0, 0)))));
		scene = new Scene(bigBox, WINDOW_WIDTH, WINDOW_HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void arrangeRightBorderPane()
	{
		ArrayList<Label> ARLabel = new ArrayList<>();
		bPane = new BorderPane();
		
	}
	
	private void arrangeAccordion()
	{
		innerPane1 = new GridPane();
		innerPane2 = new GridPane();
		investPane = new TitledPane("Сыщики", innerPane1);
		ancientPane = new TitledPane("Древний", innerPane2);
		accord = new Accordion(investPane, ancientPane);
		
		accord.setMinWidth(300);
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
}
