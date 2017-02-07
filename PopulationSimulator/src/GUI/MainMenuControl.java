package GUI;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainMenuControl implements Initializable, ControlledScene {
	
	ScenesController myController;
	
	@Override
	public void setSceneParent(ScenesController scenParent) {
		myController = scenParent;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	@FXML
	private Button StartPopulation;
	@FXML
	private AnchorPane MainMenuBackground;
	
	public void Start(ActionEvent event){
		myController.setScene(MainApplication.PopulationConfigID);
	}
}
