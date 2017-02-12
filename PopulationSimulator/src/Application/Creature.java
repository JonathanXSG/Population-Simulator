package Application;
import java.util.HashMap;

public class Creature implements Comparable<Creature>{
	private int uid;
	private int age;
	private int genes = 7;
	private double fitness;
	private int[] parents = new int[2];
	
	//Genes (characteristics of the Creatures
	private int limbs, arms, legs;
	private int deathChance;
	private int mutationChance;
	private int weightMutltiplier;
	private int lifespamMultiplier;
	
	//Fitness for the genes
	private double limbsFit ;
	private double armsFit ;
	private double legsFit ;
	private double deathFit ;
	private double mutationFit ;
	private double weightFit ;
	private double lifespamFit ;
	
	private boolean isSorted = false;
	
	//Method calls all the other methods to set the initial values of the genes of a Creature
	public void genCreatures(){
		genUid(Globals.uidCounter);
		genLimbs(Globals.paramLimbs);
		genLegs(Globals.minLegs,Globals.minArms);
		genArms(Globals.minArms);
		genDeathChance(Globals.maxDeathChance);
		genMutationChance(Globals.maxMutationChance);
		genLifespamMultiplier(Globals.lifespamMultiplier);
		genWeightMutltiplier(Globals.weightMultiplier);
		calcFitness();
		Globals.uidCounter++;
	}
	//Sets the Unique Identifier
	private void genUid(int uid){
		this.uid =uid;
	}
	//Generates the min and max numbers of limbs by picking a random number with the bounds specified
	private void genLimbs(int[] limbs){
		this.limbs = Utilities.RandInt(limbs[0],limbs[1]);
	}
	//Generates the number of legs by picking a random number with the bounds specified
	//Upper bound is found from subtracting the minimum amount of arms to the max amount of limbs
	private void genLegs(int minLegs, int minArms){
		this.legs = Utilities.RandInt(minLegs,(this.limbs - minArms) );
	}
	//Generates the number of arms by picking a random number taking into consideration how many legs it has
	private void genArms(int minArms){
		this.arms = Utilities.RandInt(minArms,(this.limbs - this.legs));
	}
	//Generates the chance that the creature has to die when it gets to a certain age
	private void genDeathChance(int death){
		this.deathChance = Utilities.RandInt(0, death);
	}
	//Generates the chance the Creature has of mutating
	private void genMutationChance(int mutation){
		this.mutationChance = Utilities.RandInt(0, mutation);
	}
	//Generates the weight of the Creature
	private void genWeightMutltiplier(int weight){
		this.weightMutltiplier = Utilities.RandInt(-weight, weight);
	}
	//Generates the value to determine how much the Creature will live
	private void genLifespamMultiplier(int lifespam){
		this.lifespamMultiplier = Utilities.RandInt(-lifespam,lifespam);
	}
	public void setIsSorted(){
		this.isSorted= true;
	}
	
	public void setParents(int parent1,int parent2){
		this.parents[0]=parent1;
		this.parents[1]=parent2;
	}
	//Calculates the fitness of all the genes using functions or constants
	private void calcFitness(){
		limbsFit = limbs * 2.5;
		armsFit = arms * 1.5;
		legsFit = legs * 1.75;
		deathFit = deathChance/100 * -32.0;
		mutationFit = mutationChance/100 * -15.0;
		weightFit = weightMutltiplier/100 * 2.3;
		lifespamFit = lifespamMultiplier/100 * 3.0;
		fitness= limbsFit + armsFit + legsFit + deathFit+ mutationFit + weightFit + lifespamFit;
	}
	
	public void printData(){
		System.out.println("Creature uid:   " + this.uid);
		System.out.printf("%-15s %d  %d %n","Parents: ",this.parents[0],this.parents[1]);
		System.out.printf("%-15s %b %n","Sorted: ",this.isSorted);
		System.out.printf("%-15s %-10d %-15s %-10.2f %n","Limbs:",this.limbs ,"Limbs Fit:", this.limbsFit);
		System.out.printf("%-15s %-10d %-15s %-10.2f %n","Legs:" ,this.legs  ,"Limbs Fit:", this.legsFit);		
		System.out.printf("%-15s %-10d %-15s %-10.2f %n","Arms:" ,this.arms  ,"Arms Fit:",  this.armsFit);
		System.out.printf("%-25s %-10d %-25s %-10.2f %n","Death Chance:" , this.deathChance, "Death Chance Fit:", this.deathFit);
		System.out.printf("%-25s %-10d %-25s %-10.2f %n","Mutation Chance:" ,this.mutationChance, "Mutation Chance Fit:", this.mutationFit);
		System.out.printf("%-25s %-10d %-25s %-10.2f %n","Weight Mutiplier:" , this.weightMutltiplier, "Limbs Fit:", this.weightFit);
		System.out.printf("%-25s %-10d %-25s %-10.2f %n","Lifespam Multiplier:" , this.lifespamMultiplier, "Limbs Fit:", this.lifespamFit);
		System.out.printf("%-15s %-10.2f %n","Fitness: " , this.fitness);
	}
	public void printShortData(){
		System.out.println("Creature uid:   " + this.uid);
		System.out.printf("%-15s %d  %d %n","Parents: ",this.parents[0],this.parents[1]);
		System.out.printf("%-15s %b %n","Sorted: ",this.isSorted);
		System.out.printf("%-15s %-10.2f %n","Fitness: " , this.fitness);
	}

	public int getUid(){
		return this.uid;
	}
	public double getFitness(){
		return this.fitness;
	}
	public boolean getIsSorted(){
		return this.isSorted;
	}
	public int[] getGenes(){
		int[] genes = new int[this.genes];
		genes[0] = this.limbs;
		genes[1] = this.legs;
		genes[2] = this.arms;
		genes[3] = this.deathChance;
		genes[4] = this.mutationChance;
		genes[5] = this.weightMutltiplier;
		genes[6] = this.lifespamMultiplier;
		return genes;
	}
	//Allows for copying genes from any Creature to another
	private void copyGenes(Creature parentCreature, int min,int max){
		for(int i=min;i<max;i++){
			switch(i){
			case 0: this.limbs=parentCreature.getGenes()[0];	break;
			case 1: this.legs=parentCreature.getGenes()[1];	break;
			case 2: this.arms=parentCreature.getGenes()[2];	break;
			case 3: this.deathChance=parentCreature.getGenes()[3];	break;
			case 4: this.mutationChance=parentCreature.getGenes()[4];	break;
			case 5: this.weightMutltiplier=parentCreature.getGenes()[5];	break;
			case 6: this.lifespamMultiplier=parentCreature.getGenes()[6];	break;
			}
		}
	}
	//Method for mutating genes. It's called when entering a new Generation 
	private void mutate(){
		double randMutation = Utilities.RandDouble(0.0, 1.0);
		int numOfMutations = Utilities.RandInt(1, 2);
		for(int i=0; i<numOfMutations;i++){
			if(randMutation<this.mutationChance){
				//A random int is created to choose which gene will Mutate
				int gene = Utilities.RandInt(1, 7);
				switch(gene){
					case 1:genLimbs(Globals.paramLimbs);	break;
					case 2:genLegs(Globals.minLegs,Globals.minArms);	break;
					case 3:genArms(Globals.minArms);	break;
					case 4:genDeathChance(Globals.maxDeathChance);	break;
					case 5:genMutationChance(Globals.maxMutationChance);	break;
					case 6:genLifespamMultiplier(Globals.lifespamMultiplier);	break;
					case 7:genWeightMutltiplier(Globals.weightMultiplier);	break;
				}
			}
		}
	}
	//The Crossover is for assigning genes from the parents to the child
	public void crossover(HashMap<Integer,Creature> creaturesHash, int gen){
		//Random numbers are created to select in how many part the gene pool will be divided over the parents
		int divisions= Utilities.RandInt(1, 2);
		int selection= Utilities.RandInt(1, 2);
		int divisionLine;
		//Creates a reference to the parents using the Creatures HashMap
		Creature parentCreature1 = creaturesHash.get(this.parents[0]);
		Creature parentCreature2 = creaturesHash.get(this.parents[1]);
		
		//The order of crossover is chosen from random number 
		divisionLine:
		if(Utilities.RandInt(1, 2)==1){
			divisionLine =  Utilities.RandInt(this.genes/4, this.genes - this.genes/4);
			this.copyGenes(parentCreature1, 0, divisionLine);
			this.copyGenes(parentCreature2, divisionLine, this.genes);
//			if(divisions==2){
//				divisions--;
//				break divisionLine;
//			}
		}
		else{
			divisionLine =  Utilities.RandInt(this.genes/4, this.genes - this.genes/4);
			this.copyGenes(parentCreature2, 0, divisionLine);
			this.copyGenes(parentCreature1, divisionLine, this.genes);
//			if(divisions==2){
//				divisions--;
//				break divisionLine;
//			}
		}
		genUid(Globals.uidCounter);
		calcFitness();
		Globals.uidCounter++;
	}
	
	
	@Override
 	public int compareTo(Creature o) {
        if(this.getFitness()>o.getFitness()){
        	return -1;
        }
        else if(this.getFitness()>o.getFitness()){
        	return 1;
        }
        else{
        	return 0;
        }
	}
	
	
	
}
