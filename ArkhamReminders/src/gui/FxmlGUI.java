package gui;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class FxmlGUI extends Application
{

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		Parent root = FXMLLoader.load(getClass().getResource("Arkham_Welcome.fxml"));
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Arkham Reminders");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void main(String[] args)
	{
		Application.launch(args);
	}
}
