package application;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class Main extends Application 
{
	final static int MAX_WIDTH = 1360;
	final static int MAX_HEIGHT = 768;
	
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		/*
		 * Initialization of panes
		 */
		HBox hbox = new HBox();
		HBox textBox = new HBox();
		AnchorPane ap1 = new AnchorPane();
		Accordion accord = new Accordion();
		ArrayList<TitledPane> tPaneList = new ArrayList<>();
		SplitPane sPane = new SplitPane();
		
		hbox.setTranslateX(20);
		hbox.setTranslateY(10);
		
		sPane.setPrefSize(MAX_WIDTH * 0.95, MAX_HEIGHT * 0.95);
		sPane.setDividerPositions(0.3);
		
		ap1.maxWidthProperty().bind(sPane.widthProperty().multiply(0.3));
		ap1.minWidthProperty().bind(sPane.widthProperty().multiply(0.3));
		
		ap1.getChildren().add(accord);
		
		sPane.getItems().addAll(ap1, textBox);
		
		hbox.getChildren().add(sPane);

		Scene scene = new Scene(new Group(hbox), MAX_WIDTH, MAX_HEIGHT);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Archham");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
