package Application;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class Globals {
	//Temporary place for variables that are used in various places
	
	public static int uidCounter;
	public static int currentGen;

	public static int maxCreatures;
	public static int maxParents;
	public static int[] paramLimbs = new int[2];
	public static int minLegs;
	public static int minArms;
	public static int maxMutationChance;
	public static int weightMultiplier;
	public static int heightMultiplier;
	public static int maxDeathChance;
	public static int lifespanMultiplier;

	public static ObservableList<Generation> generationsList;

	public static ObservableMap<Integer,Creature> creaturesHashMap;
	
	public void setupDefaults() {
		currentGen = 0;
		uidCounter = 0;
		maxCreatures = 100;
		maxParents = 50;
		paramLimbs[0] = 2;
		paramLimbs[1] = 6;
		minLegs=1;
		minArms=1;
		maxMutationChance= 10;
		heightMultiplier = 2;
		weightMultiplier= 2;
		maxDeathChance= 20;
		lifespanMultiplier= 2;
	}
}
