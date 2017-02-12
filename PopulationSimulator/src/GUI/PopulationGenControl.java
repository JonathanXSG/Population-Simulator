package GUI;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import Application.Creature;
import Application.Globals;
import Application.Generation;
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
		Generation.createGenerations(Globals.generationsList, Globals.creaturesHashMap, Globals.currentGen);
		CurrentGeneration.setText(String.valueOf(c));
		Globals.generationsList.get(c).printGeneration(c,false);
		c++;
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
		Globals.generationsList = new ArrayList <Generation>();
		Globals.creaturesHashMap = new HashMap<Integer,Creature>();
	
	} 
	
	@FXML ListView GenerationsLV;
	@FXML ListView CreaturesLV;
	
	
	
}
