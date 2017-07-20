package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mechanics.*;

public class WelcomeWindowController implements Initializable
{	
	private Framework fw;
	
	@FXML
	private TextField numField;
	
	@FXML
	private Button playerNumBtn;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		
	}
	
	@FXML
	private void PlayerNumEntered() throws Exception
	{
		if(this.numField.getText().isEmpty())
			return;
		else
		{
			this.fw = new Framework(new Integer(this.numField.getText()));
			
			SetPlayersWindowController window = new SetPlayersWindowController(this.fw, this.numField.getScene());
			window.arrangeWindow();
		}
	}
	
}
