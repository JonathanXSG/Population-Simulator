package GUI;

import java.util.HashMap;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;


public class ScenesController extends StackPane {
	//This holds all the Scenes to be displayed
	private HashMap<String,Node> scenes = new HashMap<>();
	
	public ScenesController(){
		super();
	}
	//Add a scene to the HashMap
	public void addScene(String name, Node scene){
		scenes.put(name,scene);
	}
	//Retrieve scene by name
	public Node getScene(String name){
		return scenes.get(name);
	}
	//Loads the fxml file, adds the scene to the HashMap and then injects the scene to the controller
	public Boolean loadScene(String name, String resourse){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(resourse)) ;
			Parent loadScene = (Parent) loader.load();
			ControlledScene sceneController = (ControlledScene) loader.getController();
			sceneController.setSceneParent(this);
			addScene(name, loadScene);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	 //This method tries to displayed the screen with a predefined name.
    //First it makes sure the screen has been already loaded.  Then if there is more than
    //one screen the new screen is been added second, and then the current screen is removed.
    // If there isn't any screen being displayed, the new screen is just added to the root.
	public Boolean setScene( String name){
		if(scenes.get(name) != null){	//screen loaded
			final DoubleProperty opacity = opacityProperty();

            if (!getChildren().isEmpty()) {    //if there is more than one screen
                Timeline fade = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                        new KeyFrame(new Duration(300), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        getChildren().remove(0);                    //remove the displayed screen
                        getChildren().add(0, scenes.get(name));     //add the screen
                        Timeline fadeIn = new Timeline(
                                new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                new KeyFrame(new Duration(800), new KeyValue(opacity, 1.0)));
                        fadeIn.play();
                    }
                }, new KeyValue(opacity, 0.0)));
                fade.play();

            } else {
                setOpacity(0.0);
                getChildren().add(scenes.get(name));       //no one else been displayed, then just show
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(300), new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
            return true;
        } else {
            System.out.println("Scene hasn't been loaded!!! \n");
            return false;
        }
		
	}
	
	//This method will remove the screen with the given name from the collection of screens
    public boolean unloadScreen(String name) {
        if (scenes.remove(name) == null) {
            System.out.println("Screen didn't exist");
            return false;
        } else {
            return true;
        }
    }
    
}
