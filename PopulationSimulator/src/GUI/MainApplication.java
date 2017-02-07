package GUI;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {

	static String mainMenuID = "MainMenu";
    static String mainMenuFile = "MainMenu.fxml";
    static String PopulationConfigID = "PopulationConfig";
    static String PopulationConfigFile = "PopulationConfig.fxml";
    static String PopulationGenID = "PopulationGen";
    static String PopulationGenFile = "PopulationGen.fxml";
    
	
	  @Override
	    public void start(Stage primaryStage) {
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
	        
	    }
}
