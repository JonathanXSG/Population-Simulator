package GUI;

import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


public class PopulationGenControl implements Initializable, ControlledScene{
	ScenesController myController;
	ObservableList<Generation> generationsOL;
	ObservableList<Creature> creaturesOL;
	ObservableList<Creature> bestCreatureOL;
	private FileChooser fileDialog = new FileChooser();
	private File file;
	XYChart.Series<Number,Number> highestSeries = new XYChart.Series<Number,Number> ();
	XYChart.Series<Number,Number> lowestSeries = new XYChart.Series<Number,Number> ();
	XYChart.Series<Number,Number> averageSeries = new XYChart.Series<Number,Number> ();
	
	//------------------------------Generator Tab------------------------------
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void generate(ActionEvent event)
	{
		Globals.generationsList.add(new Generation());
		Globals.generationsList.get(Globals.currentGen).createGenerations(Globals.generationsList, Globals.creaturesHashMap);
		
		currentGenerationL.setText(Globals.generationsList.get(Globals.currentGen).toString());
		highestSeries.getData().add(new XYChart.Data(Globals.currentGen,Globals.generationsList.get(Globals.currentGen).getFitnesss()[0]));
		lowestSeries.getData().add(new XYChart.Data(Globals.currentGen,Globals.generationsList.get(Globals.currentGen).getFitnesss()[1]));
		averageSeries.getData().add(new XYChart.Data(Globals.currentGen,Globals.generationsList.get(Globals.currentGen).getFitnesss()[2]));
		
		bestCreatureLV.getItems().setAll(Globals.creaturesHashMap.get(Globals.generationsList.get(Globals.currentGen).getImportantCretures()[0]));
		Globals.currentGen++;
	}
	public void generateMultiple(ActionEvent event){
		for(int i=0;i<Integer.parseInt(multipleGenerationsTF.getText());i++){
			generate(event);
		}
	}
	
	public void saveGeneration(){
		try {
			file= fileDialog.showSaveDialog(myController.getScene().getWindow());
			Globals.generationsList.get(Globals.currentGen).printGeneration(false, new FileOutputStream(file));
		} catch ( IOException e) {
			e.printStackTrace();
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
	

	//------------------------------Generations Database tab------------------------------
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
			if(newValue != null){
				setCreatureInfo(newValue);
			}
		}
	};
	
	@FXML Tab generationsDataTab;
	@FXML ListView<Generation> generationsLV;
	@FXML ListView<Creature> creaturesLV;
	
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
	
	//------------------------------Creatures Database tab------------------------------

	
	@FXML Tab creaturesDataTab;
	@FXML ListView<Creature> allCreaturesLV;
	
	@FXML Label uidL2;
	@FXML Label ageL2;
	@FXML Label fitnessL2;
	@FXML Label parent1L2;
	@FXML Label parent2L2;
	@FXML Label limbsL2;
	@FXML Label legsL2;
	@FXML Label armsL2;
	@FXML Label mutationChanceL2;
	@FXML Label HeightMultiplierL2;
	@FXML Label weightMultiplierL2;
	@FXML Label deathChanceL2;
	@FXML Label lifespanMultiplierL2;
	
	//General things
	public void exit(ActionEvent event){
		myController.setScene(MainStage.mainMenuID);
	}
	
	@Override
	public void setSceneParent(ScenesController sceneParent) {
		myController = sceneParent;
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Globals.generationsList = FXCollections.observableArrayList();
		Globals.creaturesHashMap = FXCollections.observableHashMap();
		generationsLV.getSelectionModel().selectedItemProperty().addListener(selectedGeneration);
		creaturesLV.getSelectionModel().selectedItemProperty().addListener(selectedCreature);
		highestSeries.setName("Highest Fitness");
		lowestSeries.setName("Lowest Fitness");
		averageSeries.setName("Average Fitness");
		generationsLC.getData().add(highestSeries);
		generationsLC.getData().add(lowestSeries);
		generationsLC.getData().add(averageSeries);
		
		bestCreatureLV.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				populationGenTabPane.getSelectionModel().select(generationsDataTab);
				generationsLV.getSelectionModel().select(Globals.currentGen-1);
				creaturesLV.getSelectionModel().select(bestCreatureLV.getSelectionModel().getSelectedItem());
				setCreatureInfo(bestCreatureLV.getSelectionModel().getSelectedItem());
			}
			
		});
		Globals.creaturesHashMap.addListener((MapChangeListener.Change<? extends Integer, ? extends Creature> hashMapChange) -> {
			if (hashMapChange.wasAdded()) {
				allCreaturesLV.getItems().add(hashMapChange.getValueAdded());
            }
		});
		Globals.generationsList.addListener((ListChangeListener.Change<? extends Generation> listChange) -> {
			listChange.next();
			if (listChange.wasAdded()) {
				generationsLV.getItems().addAll(listChange.getAddedSubList());
            }
		});
		
	} 

	@FXML TabPane populationGenTabPane;
	
}
