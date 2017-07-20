package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import mechanics.*;

public class WelcomeWindowController
{	
	private Framework fw;
	
	@FXML
	private TextField numField;
	
	@FXML
	private Button playerNumBtn;
	
	@FXML
	private void playerNumEntered() throws Exception
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
