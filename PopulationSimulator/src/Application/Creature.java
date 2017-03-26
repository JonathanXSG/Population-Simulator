package Application;
import java.util.HashMap;

public class Creature implements Comparable<Creature>{
	private int uid;
	private int age=0;
	private int genes = 8;
	private double fitness;
	private int[] parents = new int[2];
	
	//Genes (characteristics of the Creatures
	private int limbs, arms, legs;
	private int mutationChance;
	private int heightMultiplier;
	private int weightMutltiplier;
	private int deathChance;
	private int lifespanMultiplier;
	
	private int height, weight,lifespan;
	
	//Fitness for the genes
	private double limbsFit ;
	private double armsFit ;
	private double legsFit ;
	private double heightFit;
	private double weightFit ;
	private double deathFit ;
	private double lifespanFit ;
	private double mutationFit ;
	private double heightMutltiplier;
	
	//Method calls all the other methods to set the initial values of the genes of a Creature
	public void genCreatures(){
		genUid(Globals.uidCounter);
		genLimbs(Globals.paramLimbs);
		genLegs(Globals.minLegs,Globals.minArms);
		genArms(Globals.minArms);
		genMutationChance(Globals.maxMutationChance);
		genHeightMutltiplier(Globals.heightMultiplier);
		genWeightMutltiplier(Globals.weightMultiplier);
		genDeathChance(Globals.maxDeathChance);
		genLifespanMultiplier(Globals.lifespanMultiplier);
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
		checkLimbs();
		this.legs = Utilities.RandInt(minLegs,(this.limbs - minArms) );
	}
	//Generates the number of arms by picking a random number taking into consideration how many legs it has
	private void genArms(int minArms){
		checkLimbs();
		this.arms = Utilities.RandInt(minArms,(this.limbs - this.legs));
		
	}
	//Generates the chance the Creature has of mutating
	private void genMutationChance(int mutation){
		this.mutationChance = Utilities.RandInt(0, mutation);
	}
	//Generates the height of the Creature
	private void genHeightMutltiplier(int height){
		this.heightMutltiplier = Utilities.RandInt(-height, height);
	}
	//Generates the weight of the Creature
	private void genWeightMutltiplier(int weight){
		this.weightMutltiplier = Utilities.RandInt(-weight, weight);
	}
	//Generates the chance that the creature has to die when it gets to a certain age
	private void genDeathChance(int death){
		this.deathChance = Utilities.RandInt(0, death);
	}
	//Generates the value to determine how much the Creature will live
	private void genLifespanMultiplier(int lifespan){
		this.lifespanMultiplier = Utilities.RandInt(-lifespan,lifespan);
	}
	
	public void addAge(){
		this. age += Utilities.RandInt(8, 12);
	}
	public void setParents(int parent1,int parent2){
		this.parents[0]=parent1;
		this.parents[1]=parent2;
	}
	//Calculates the fitness of all the genes using functions or constants (temporary)
	private void calcFitness(){
		limbsFit = limbs * 2.5;
		armsFit = arms * 1.5;
		legsFit = legs * 1.75;
		mutationFit = mutationChance * -0.9;
		weightFit = weightMutltiplier * 1.4;
		heightFit = heightMultiplier *1.1;
		deathFit = (deathChance/10) * -2.2;
		lifespanFit = lifespanMultiplier * 2.0;
		fitness= limbsFit + armsFit + legsFit + deathFit+ mutationFit + weightFit + lifespanFit;
	}
	//Method for printing the cull data of the Creature
	public void printData(){
		System.out.println("Creature uid:   " + this.uid);
		System.out.printf("%-15s %d %n","Age: ",this.age);
		System.out.printf("%-15s %d  %d %n","Parents: ",this.parents[0],this.parents[1]);
		System.out.printf("%-15s %-10d %-15s %-10.3f %n","Limbs:",this.limbs ,"Limbs Fit:", this.limbsFit);
		System.out.printf("%-15s %-10d %-15s %-10.3f %n","Legs:" ,this.legs  ,"Limbs Fit:", this.legsFit);		
		System.out.printf("%-15s %-10d %-15s %-10.3f %n","Arms:" ,this.arms  ,"Arms Fit:",  this.armsFit);
		System.out.printf("%-25s %-10d %-25s %-10.3f %n","Mutation Chance:" ,this.mutationChance, "Mutation Chance Fit:", this.mutationFit);
		System.out.printf("%-25s %-10d %-25s %-10.3f %n","Height Mutiplier:" , this.heightMutltiplier, "Limbs Fit:", this.heightFit);
		System.out.printf("%-25s %-10d %-25s %-10.3f %n","Weight Mutiplier:" , this.weightMutltiplier, "Limbs Fit:", this.weightFit);
		System.out.printf("%-25s %-10d %-25s %-10.3f %n","Death Chance:" , this.deathChance, "Death Chance Fit:", this.deathFit);
		System.out.printf("%-25s %-10d %-25s %-10.3f %n","Lifespan Multiplier:" , this.lifespanMultiplier, "Limbs Fit:", this.lifespanFit);
		System.out.printf("%-15s %-10.2f %n","Fitness: " , this.fitness);
	}
	//Method for printing a small amount of data of the Creature
	public void printShortData(){
		System.out.println("Creature uid:   " + this.uid);
		System.out.printf("%-15s %d %n","Age: ",this.age);
		System.out.printf("%-15s %d  %d %n","Parents: ",this.parents[0],this.parents[1]);
		System.out.printf("%-15s %-10.3f %n","Fitness: " , this.fitness);
	}
	public int getUid(){
		return this.uid;
	}
	public double getFitness(){
		return this.fitness;
	}
	public int getAge() {
		return this.age;
	}
	public int[] getParents() {
		return this.parents;
	}
	public void setParents(int[] parents) {
		this.parents = parents;
	}
	public int[] getGenes(){
		int[] genes = new int[this.genes];
		genes[0] = this.limbs;
		genes[1] = this.legs;
		genes[2] = this.arms;
		genes[3] = this.mutationChance;
		genes[4] = this.heightMultiplier;
		genes[5] = this.weightMutltiplier;
		genes[6] = this.deathChance;
		genes[7] = this.lifespanMultiplier;
		return genes;
	}
	//Allows for copying genes from any Creature to another
	private void copyGenes(Creature parentCreature, int min,int max){
		for(int i=min;i<max;i++){
			switch(i){
			case 0: this.limbs=parentCreature.getGenes()[0]; break;
			case 1: this.legs=parentCreature.getGenes()[1];	break;
			case 2: this.arms=parentCreature.getGenes()[2];	break;
			case 3: this.mutationChance=parentCreature.getGenes()[3]; break;
			case 4: this.heightMultiplier=parentCreature.getGenes()[4];	break;
			case 5: this.weightMutltiplier=parentCreature.getGenes()[5]; break;
			case 6: this.deathChance=parentCreature.getGenes()[6]; break;
			case 7: this.lifespanMultiplier=parentCreature.getGenes()[7]; break;
			}
		}
	}
	//Method for mutating genes. It's called when entering a new Generation 
	public void mutate(){
		double randMutation = Utilities.RandDouble(0.0, 1.0);
		int numOfMutations = Utilities.RandInt(1, 2);
		for(int i=0; i<numOfMutations;i++){
			if(randMutation<=this.mutationChance){
				//A random int is created to choose which gene will Mutate
				int gene = Utilities.RandInt(0, 7);
				switch(gene){
					case 0:genLimbs(Globals.paramLimbs);	break;
					case 1:genLegs(Globals.minLegs,Globals.minArms);	break;
					case 2:genArms(Globals.minArms);	break;
					case 3:genMutationChance(Globals.maxMutationChance);	break;
					case 4:genWeightMutltiplier(Globals.weightMultiplier);	break;
					case 5:genWeightMutltiplier(Globals.heightMultiplier);	break;
					case 6:genDeathChance(Globals.maxDeathChance);	break;
					case 7:genLifespanMultiplier(Globals.lifespanMultiplier);	break;
				}
			}
		}
		
	}
	//The Crossover is for assigning genes from the parents to the child
	public void crossover(HashMap<Integer,Creature> creaturesHash){
		//Random numbers are created to select in how many parts the gene pool will be divided over the parents
		int divisions= Utilities.RandInt(1, 2);
		int selection= Utilities.RandInt(1, 2);
		int divisionLine;
		//Creates a reference to the parents using the Creatures HashMap
		Creature[] parentCreatures = {creaturesHash.get(this.parents[0]),creaturesHash.get(this.parents[1])};
		
		//The order of crossover is chosen from random number 
		if(divisions==1){
			divisionLine =  Utilities.RandInt(this.genes/4, this.genes - this.genes/4);
			if(selection==1){
				this.copyGenes(parentCreatures[0], 0, divisionLine);
				this.copyGenes(parentCreatures[1], divisionLine, this.genes);
			}
			else{
				this.copyGenes(parentCreatures[1], 0, divisionLine);
				this.copyGenes(parentCreatures[0], divisionLine, this.genes);
			}
			checkLimbs();
		}
		else{
			divisionLine=  Utilities.RandInt(3,4);
			if(selection==1){
				this.copyGenes(parentCreatures[0], 0, divisionLine-this.genes/4);
				this.copyGenes(parentCreatures[1], (divisionLine-this.genes/4)+1, (divisionLine+this.genes/4)-1);
				this.copyGenes(parentCreatures[0],(divisionLine+this.genes/4)+1, this.genes);
			}
			else{
				this.copyGenes(parentCreatures[1], 0, divisionLine-this.genes/4);
				this.copyGenes(parentCreatures[0], (divisionLine-this.genes/4)+1, (divisionLine+this.genes/4)-1);
				this.copyGenes(parentCreatures[1],(divisionLine+this.genes/4)+1, this.genes);
			}
			checkLimbs();
		}
		checkLimbs();
		genUid(Globals.uidCounter);
		calcFitness();
		Globals.uidCounter++;
	}
	//Checks that the value of the limbs is bigger or equal than the legs and arms combined
	private void checkLimbs(){
//		while(this.limbs<this.legs+this.arms){
//			this.limbs++;
//		}
		while(this.limbs<this.legs + Globals.minArms){
			this.limbs++;
		}
	}
	
	@Override
	public String toString() {
		return "Creature " + this.uid ;
	}
	@Override
 	public int compareTo(Creature o) {
        if(this.getFitness()<o.getFitness()){
        	return 1;
        }
        else if(this.getFitness()>o.getFitness()){
        	return -1;
        }
        else{
        	return 0;
        }
	}
	
}
