package Application;
import java.util.ArrayList;
import java.util.HashMap;

public class Globals {
	
	public static int uidCounter = 0;
	public static int currentGen = 0;

	public static int maxCreatures = 100;
	public static int maxParents = 50;
	public static int[] paramLimbs = new int[2];
	public static int minLegs;
	public static int minArms;
	public static int maxDeathChance;
	public static int maxMutationChance;
	public static int weightMultiplier ;
	public static int lifespamMultiplier = 1;

	public static ArrayList<Population> generationsList;

	public static HashMap<Integer,Creature> creaturesHash;
	
	public void setupDefaults() {
		currentGen = 0;
		uidCounter = 0;
		maxCreatures = 100;
		maxParents = 50;
		paramLimbs[0] = 2;
		paramLimbs[1] = 6;
		minLegs=1;
		minArms=1;
		maxDeathChance= 20;
		maxMutationChance= 5;
		weightMultiplier= 2;
		lifespamMultiplier= 1;
		
		
		
	}

	
}
