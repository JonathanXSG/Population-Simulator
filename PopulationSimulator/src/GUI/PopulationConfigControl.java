package GUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import Application.*;

public class PopulationConfigControl implements Initializable, ControlledScene{
	
	private ScenesController myController;
	private ObservableList<Integer> maxCreatures = FXCollections.observableArrayList(25,50,100,200,400,600,800,1000);
	private ObservableList<Integer> maxParents = FXCollections.observableArrayList(25,50);
	private FileChooser fileDialog = new FileChooser();
	private File file;
	private BufferedWriter fileWriter;
	private BufferedReader fileReader;
	
	public void exit(ActionEvent event){
		myController.setScene(MainStage.mainMenuID);
	}
	
	public void save(ActionEvent event){
		Globals.maxCreatures = maxCreaturesCB.getValue();
		Globals.maxParents = (int) (maxCreaturesCB.getValue()*(maxParentsCB.getValue()/100.0));
		if (minLimbsTF.getText() != "") {Globals.paramLimbs[0] = Integer.parseInt(minLimbsTF.getText());}
		if (maxLimbsTF.getText() != "") {Globals.paramLimbs[1] = Integer.parseInt(maxLimbsTF.getText());}
		if (minLegsTF.getText() != "") {Globals.minLegs = Integer.parseInt(minLegsTF.getText());}
		if (minArmsTF.getText() != "") {Globals.minArms = Integer.parseInt(minArmsTF.getText());}
		if (maxMutationChanceTF.getText() != "") {Globals.maxMutationChance = Integer.parseInt(maxMutationChanceTF.getText());}
		if (heitghMultiplierTF.getText() != "") {Globals.heightMultiplier = Integer.parseInt(heitghMultiplierTF.getText());}
		if (weitghMultiplierTF.getText() != "") {Globals.weightMultiplier = Integer.parseInt(weitghMultiplierTF.getText());}
		if (maxDeathChanceTF.getText() != "") {Globals.maxDeathChance = Integer.parseInt(maxDeathChanceTF.getText());}
		if (lifespanMultiplierTF.getText() != "") {Globals.lifespanMultiplier = Integer.parseInt(lifespanMultiplierTF.getText());}
		Globals.generationsList.clear();
		myController.unloadScene(MainStage.PopulationGenID);
		myController.loadScene(MainStage.PopulationGenID, MainStage.PopulationGenFile);
		Globals.currentGen=0;
	}
	
	public void saveFile(ActionEvent event){
		fileDialog.setTitle("Save Configuration");
		fileDialog.getExtensionFilters().add( new ExtensionFilter("Text Files", "*.txt"));
		file= fileDialog.showSaveDialog(myController.getScene().getWindow());
        
		try {
			fileWriter = new BufferedWriter(new FileWriter(file));
			fileWriter.append(""+ maxCreaturesCB.getSelectionModel().getSelectedIndex());
			fileWriter.newLine();
			fileWriter.append(""+ maxParentsCB.getSelectionModel().getSelectedIndex());
			fileWriter.newLine();
			fileWriter.append(minLimbsTF.getText());
			fileWriter.newLine();
			fileWriter.append(maxLimbsTF.getText());
			fileWriter.newLine();
			fileWriter.append(minLegsTF.getText());
			fileWriter.newLine();
			fileWriter.append(minArmsTF.getText());
			fileWriter.newLine();
			fileWriter.append(maxMutationChanceTF.getText());
			fileWriter.newLine();
			fileWriter.append(heitghMultiplierTF.getText());
			fileWriter.newLine();
			fileWriter.append(weitghMultiplierTF.getText());
			fileWriter.newLine();
			fileWriter.append(maxDeathChanceTF.getText());
			fileWriter.newLine();
			fileWriter.append(lifespanMultiplierTF.getText());
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void openFile(ActionEvent event){
		fileDialog.setTitle("Open Configuration");
		fileDialog.getExtensionFilters().add( new ExtensionFilter("Text Files", "*.txt"));
		file = fileDialog.showOpenDialog(myController.getScene().getWindow());
		try {
			fileReader = new BufferedReader(new FileReader(file));
			maxCreaturesCB.getSelectionModel().select(Integer.parseInt(fileReader.readLine()));
			maxParentsCB.getSelectionModel().select(Integer.parseInt(fileReader.readLine()));
			minLimbsTF.setText(""+fileReader.readLine());
			maxLimbsTF.setText(""+fileReader.readLine());
			minLegsTF.setText(""+fileReader.readLine());
			minArmsTF.setText(""+fileReader.readLine());
			maxMutationChanceTF.setText(""+fileReader.readLine());
			heitghMultiplierTF.setText(""+fileReader.readLine());
			weitghMultiplierTF.setText(""+fileReader.readLine());
			maxDeathChanceTF.setText(""+fileReader.readLine());
			lifespanMultiplierTF.setText(""+fileReader.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setDeafults(ActionEvent event){
		maxCreaturesCB.getSelectionModel().select(2);
		maxParentsCB.getSelectionModel().select(1);
		minLimbsTF.setText(""+minLimbsTF.getPromptText());
		maxLimbsTF.setText(""+maxLimbsTF.getPromptText());
		minLegsTF.setText(""+minLegsTF.getPromptText());
		minArmsTF.setText(""+minArmsTF.getPromptText());
		maxMutationChanceTF.setText(""+maxMutationChanceTF.getPromptText());
		heitghMultiplierTF.setText(""+heitghMultiplierTF.getPromptText());
		weitghMultiplierTF.setText(""+weitghMultiplierTF.getPromptText());
		maxDeathChanceTF.setText(""+maxDeathChanceTF.getPromptText());
		lifespanMultiplierTF.setText(""+lifespanMultiplierTF.getPromptText());
	}
	
	public void nextScene(ActionEvent event){
		save(event);
		myController.setScene(MainStage.PopulationGenID);
	}
	public void clear(ActionEvent event){
		maxCreaturesCB.getSelectionModel().clearSelection();
		maxParentsCB.getSelectionModel().clearSelection();
		minLimbsTF.clear();
		maxLimbsTF.clear();
		minLegsTF.clear();
		minArmsTF.clear();
		maxMutationChanceTF.clear();
		heitghMultiplierTF.clear();
		weitghMultiplierTF.clear();
		maxDeathChanceTF.clear();
		lifespanMultiplierTF.clear();
	}
	
	@Override
	public void setSceneParent(ScenesController scenParent) {
		myController = scenParent;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources){
		maxCreaturesCB.getItems().addAll(maxCreatures);
		maxCreaturesCB.getSelectionModel().select(2);
		maxParentsCB.getItems().addAll(maxParents );
		maxParentsCB.getSelectionModel().select(1);
		addPropoerties(minLimbsTF,2,20,4);
		addPropoerties(maxLimbsTF,2,20,4);
		addPropoerties(minLegsTF,0,20,2);
		addPropoerties(minArmsTF,0,20,2);
		addPropoerties(maxMutationChanceTF,0,100,10);
		addPropoerties(heitghMultiplierTF,0,5,2);
		addPropoerties(weitghMultiplierTF,0,5,2);
		addPropoerties(maxDeathChanceTF,0,100,20);
		addPropoerties(lifespanMultiplierTF,0,5,2);
	}
	
	@FXML public Button Confirm;
	@FXML public Button Cancel;
	@FXML public ChoiceBox<Integer> maxCreaturesCB;
	@FXML public ChoiceBox<Integer> maxParentsCB;
	@FXML public TextField minLimbsTF;
	@FXML public TextField maxLimbsTF;
	@FXML public TextField minLegsTF;
	@FXML public TextField minArmsTF;
	@FXML public TextField maxMutationChanceTF;
	@FXML public TextField heitghMultiplierTF;
	@FXML public TextField weitghMultiplierTF;
	@FXML public TextField maxDeathChanceTF;
	@FXML public TextField lifespanMultiplierTF;
	
	
	void addPropoerties(TextField tf,int minVal, int maxVal, int defaultVal){
		tf.setText(""+defaultVal);
		tf.setPromptText(""+defaultVal);
		tf.textProperty().addListener(new ChangeListener<String>() {
			   @Override public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
			     if (newValue == null || "".equals(newValue)) {
			       tf.setText("");
			       return;
			     }
			     else{
			    	 final int intValue = Integer.parseInt(newValue);
			    	 if (minVal > intValue || intValue > maxVal) {
			    		 tf.setText(oldValue);
			    	 }
			     }
			   }
			 });
		tf.setTextFormatter(new TextFormatter<String>(integerFilter));
		
	}
	UnaryOperator<Change> integerFilter = change -> {
	    String newText = change.getControlNewText();
	    if (newText.matches("[0-9]*")){ 
	        return change;
	    }
	    return null;
	};
}
