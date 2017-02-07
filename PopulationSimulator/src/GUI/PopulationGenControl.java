package GUI;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import Application.Creature;
import Application.Globals;
import Application.Population;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class PopulationGenControl implements Initializable, ControlledScene{
	
	int c = 0;
	public void generate(ActionEvent event)
	{
		Population.createGenerations(Globals.generationsList, Globals.creaturesHash);
		CurrentGeneration.setText(String.valueOf(c));
		this.c++;
	}
	
	public void generateMultiple(ActionEvent event){
		for(int i=0;i<Integer.parseInt(MultipleGenerationsTF.getText());i++){
			generate(event);
		}
	}
	
	@FXML Label CurrentGeneration;
	@FXML Button SingleGenerationB;
	@FXML TextField MultipleGenerationsTF;
	@FXML Button MultipleGenerationsB;
	
	
	@Override
	public void setSceneParent(ScenesController sceneParent) {
		// TODO Auto-generated method stub
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Globals.generationsList = new ArrayList <Population>();
		Globals.creaturesHash = new HashMap<Integer,Creature>();
	
	} 
	
	@FXML ListView GenerationsLV;
	@FXML ListView CreaturesLV;
	
	
	
}
