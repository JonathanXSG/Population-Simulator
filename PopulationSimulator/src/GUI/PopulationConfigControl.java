package GUI;

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
import Application.*;

public class PopulationConfigControl implements Initializable, ControlledScene{
	
	ScenesController myController;
	ObservableList<Integer> maxCreatures = FXCollections.observableArrayList(100,200,400,600,800,1000);
	ObservableList<Integer> maxParents = FXCollections.observableArrayList(25,50);

	public void exit(ActionEvent event){
		myController.setScene(MainApplication.mainMenuID);
		clear(null);
	}
	
	public void save(ActionEvent event){
		Globals.maxCreatures = maxCreaturesCB.getValue();
		Globals.maxParents = maxParentsCB.getValue();
		if (minLimbsTF.getText() != "") {Globals.paramLimbs[0] = Integer.parseInt(minLimbsTF.getText());}
		if (maxLimbsTF.getText() != "") {Globals.paramLimbs[1] = Integer.parseInt(maxLimbsTF.getText());}
		if (minLegsTF.getText() != "") {Globals.minLegs = Integer.parseInt(minLegsTF.getText());}
		if (minArmsTF.getText() != "") {Globals.minArms = Integer.parseInt(minArmsTF.getText());}
		if (maxDeathChanceTF.getText() != "") {Globals.maxDeathChance = Integer.parseInt(maxDeathChanceTF.getText());}
		if (maxMutationChanceTF.getText() != "") {Globals.maxMutationChance = Integer.parseInt(maxMutationChanceTF.getText());}
		if (weitghMultiplierTF.getText() != "") {Globals.weightMultiplier = Integer.parseInt(weitghMultiplierTF.getText());}
		if (lifespamMultiplierTF.getText() != "") {Globals.lifespamMultiplier = Integer.parseInt(lifespamMultiplierTF.getText());}
	}
	
	public void nextScene(ActionEvent event){
		save(event);
		myController.setScene(MainApplication.PopulationGenID);
	}
	public void clear(ActionEvent event){
		maxCreaturesCB.getSelectionModel().clearSelection();
		maxParentsCB.getSelectionModel().clearSelection();
		minLimbsTF.clear();
		maxLimbsTF.clear();
		minLegsTF.clear();
		minArmsTF.clear();
		maxMutationChanceTF.clear();
		maxDeathChanceTF.clear();
		lifespamMultiplierTF.clear();
		weitghMultiplierTF.setText("");
	}
	
	@Override
	public void setSceneParent(ScenesController scenParent) {
		myController = scenParent;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources){
		maxCreaturesCB.getItems().addAll(maxCreatures);
		maxCreaturesCB.setValue(100);
		maxParentsCB.getItems().addAll(maxParents );
		maxParentsCB.setValue(50);
		addPropoerties(minLimbsTF,2,20,4);
		addPropoerties(maxLimbsTF,2,20,4);
		addPropoerties(minLegsTF,0,20,2);
		addPropoerties(minArmsTF,0,20,2);
		addPropoerties(maxMutationChanceTF,0,100,10);
		addPropoerties(maxDeathChanceTF,0,100,20);
		addPropoerties(lifespamMultiplierTF,0,5,2);
		addPropoerties(weitghMultiplierTF,0,5,2);
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
	@FXML public TextField maxDeathChanceTF;
	@FXML public TextField lifespamMultiplierTF;
	@FXML public TextField weitghMultiplierTF;
	
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
