package GUI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainStage extends Application {

	static final String mainMenuID = "MainMenu";
    static final String mainMenuFile = "MainMenu.fxml";
    static final String PopulationConfigID = "PopulationConfig";
    static final String PopulationConfigFile = "PopulationConfig.fxml";
    static final String PopulationGenID = "PopulationGen";
    static final String PopulationGenFile = "PopulationGen.fxml";

    @Override
    public void start(Stage primaryStage) {
    	primaryStage.setTitle("Population Simulator");
    	ScenesController mainContainer = new ScenesController();
    	mainContainer.loadScene(mainMenuID, mainMenuFile);
    	mainContainer.loadScene(PopulationConfigID, PopulationConfigFile);
    	mainContainer.loadScene(PopulationGenID, PopulationGenFile);
    	mainContainer.setScene(mainMenuID);

    	Group root = new Group();
    	root.getChildren().addAll(mainContainer);
    	Scene scene = new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();

    	OutputStream output = null;
		try {
			output = new FileOutputStream("E:\\Users\\Jonathan\\Desktop\\text.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	PrintStream printOut = new PrintStream(output);

    	//System.setErr(printOut);
    	
    }
    
    
}
