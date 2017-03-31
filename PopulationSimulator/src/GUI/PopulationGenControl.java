package GUI;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import Application.Creature;
import Application.Globals;
import Application.Utilities;
import Application.Generation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


public class PopulationGenControl implements Initializable, ControlledScene{
	ScenesController myController;
	ObservableList<Creature> creaturesOL;
	ObservableList<Creature> bestCreatureOL;
	
	XYChart.Series highestSeries = new XYChart.Series();
	XYChart.Series lowestSeries = new XYChart.Series();
	XYChart.Series averageSeries = new XYChart.Series();
	
	//Generator Tab
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void generate(ActionEvent event)
	{
		Globals.generationsList.add(new Generation());
		Globals.generationsList.get(Globals.currentGen).createGenerations(Globals.generationsList, Globals.creaturesHashMap);
		//Globals.generationsList.get(Globals.currentGen).printGeneration(true);
		currentGenerationL.setText(Globals.generationsList.get(Globals.currentGen).toString());
		generationsLV.getItems().add(Globals.generationsList.get(Globals.currentGen));
		
		highestSeries.getData().add(new XYChart.Data(Globals.currentGen,Globals.generationsList.get(Globals.currentGen).getFitnesss()[0]));
		lowestSeries.getData().add(new XYChart.Data(Globals.currentGen,Globals.generationsList.get(Globals.currentGen).getFitnesss()[1]));
		averageSeries.getData().add(new XYChart.Data(Globals.currentGen,Globals.generationsList.get(Globals.currentGen).getFitnesss()[2]));
		bestCreatureLV.getItems().clear();
		bestCreatureLV.getItems().setAll(Globals.creaturesHashMap.get(Globals.generationsList.get(Globals.currentGen).getImportantCretures()[0]));
		
		Globals.currentGen++;
	}
	public void generateMultiple(ActionEvent event){
		for(int i=0;i<Integer.parseInt(multipleGenerationsTF.getText());i++){
			generate(event);
		}
	}
	
	@FXML Label currentGenerationL;
	@FXML Button singleGenerationB;
	@FXML TextField multipleGenerationsTF;
	@FXML Button multipleGenerationsB;
	@FXML ListView<Creature> bestCreatureLV;
	
	NumberAxis xAxis = new NumberAxis();
	NumberAxis yAxis = new NumberAxis();
	@FXML LineChart<Number, Number> generationsLC = new LineChart<Number, Number>(xAxis, yAxis);
	@FXML AreaChart<Number, Double> generationsAC; 
	

	//Generations Database tab
	public void setGenerationInfo(Generation generation){
		totalCreaturesL.setText(String.valueOf(Globals.maxCreatures));
		highestFitL.setText(String.valueOf(Utilities.Round(generation.getFitnesss()[0])));
		averageFitL.setText(String.valueOf(Utilities.Round(generation.getFitnesss()[1])));
		lowestFitL.setText(String.valueOf(Utilities.Round(generation.getFitnesss()[2])));
		isSortedL.setText(String.valueOf(generation.getIsSorted()));
		bestCreatureL.setText(String.valueOf(generation.getImportantCretures()[0]));
		averageCreatureL.setText(String.valueOf(generation.getImportantCretures()[1]));
		worstCreatureL.setText(String.valueOf(generation.getImportantCretures()[2]));
	}
	
	public void setCreatureInfo(Creature creature){
		uidL.setText(String.valueOf(creature.getUid()));
		ageL.setText(String.valueOf(creature.getAge()));
		fitnessL.setText(String.valueOf(Utilities.Round(creature.getFitness())));
		parent1L.setText(String.valueOf(creature.getParents()[0]));
		parent2L.setText(String.valueOf(creature.getParents()[1]));
		limbsL.setText(String.valueOf(creature.getGenes()[0]));
		legsL.setText(String.valueOf(creature.getGenes()[1]));
		armsL.setText(String.valueOf(creature.getGenes()[2]));
		mutationChanceL.setText(String.valueOf(creature.getGenes()[3]));
		HeightMultiplierL.setText(String.valueOf(creature.getGenes()[4]));
		weightMultiplierL.setText(String.valueOf(creature.getGenes()[5]));
		deathChanceL.setText(String.valueOf(creature.getGenes()[6]));
		lifespanMultiplierL.setText(String.valueOf(creature.getGenes()[7]));
	}
	ChangeListener<Generation> selectedGeneration = new ChangeListener<Generation>() {
		@Override
		public void changed(ObservableValue<? extends Generation> observable, Generation oldValue,Generation newValue) {
			creaturesOL = FXCollections.observableArrayList(newValue.getCreaturesList());
			creaturesLV.getItems().setAll(creaturesOL);
			setGenerationInfo(newValue);
		}
	};
	ChangeListener<Creature> selectedCreature = new ChangeListener<Creature>() {
		@Override
		public void changed(ObservableValue<? extends Creature> observable, Creature oldValue,Creature newValue) {
			try {
				setCreatureInfo(newValue);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	};
	
	@FXML Label uidL;
	@FXML Label ageL;
	@FXML Label fitnessL;
	@FXML Label parent1L;
	@FXML Label parent2L;
	@FXML Label limbsL;
	@FXML Label legsL;
	@FXML Label armsL;
	@FXML Label mutationChanceL;
	@FXML Label HeightMultiplierL;
	@FXML Label weightMultiplierL;
	@FXML Label deathChanceL;
	@FXML Label lifespanMultiplierL;
	@FXML Label totalCreaturesL;
	@FXML Label highestFitL;
	@FXML Label averageFitL;
	@FXML Label lowestFitL;
	@FXML Label isSortedL;
	@FXML Label bestCreatureL;
	@FXML Label averageCreatureL;
	@FXML Label worstCreatureL;
	
	//General things
	public void exit(ActionEvent event){
		myController.setScene(MainStage.mainMenuID);
	}
	
	@FXML ListView<Generation> generationsLV;
	@FXML ListView<Creature> creaturesLV;
	
	@Override
	public void setSceneParent(ScenesController sceneParent) {
		myController = sceneParent;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Globals.generationsList = new ArrayList <Generation>();
		Globals.creaturesHashMap = new HashMap<Integer,Creature>();
		generationsLV.getSelectionModel().selectedItemProperty().addListener(selectedGeneration);
		creaturesLV.getSelectionModel().selectedItemProperty().addListener(selectedCreature);
		highestSeries.setName("Highest Fitness");
		lowestSeries.setName("Lowest Fitness");
		averageSeries.setName("Average Fitness");
		generationsLC.getData().add(highestSeries);
		generationsLC.getData().add(lowestSeries);
		generationsLC.getData().add(averageSeries);
	} 

	
}
