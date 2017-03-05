package Application;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/*
 This class encapsulates, manages and modifies the creatures for a generation. 
 The Generations can be created, printed in various ways, sorted, the fitness of the generation can be calculated.
 Finally the parents for the generations (except for the first) can be set.
 */
public class Generation {
	
	private static int currentCreature;
	private static int currentParent;
	
	// Array for storing all the creatures in each generation
	private ArrayList<Creature> creaturesList;
	private Boolean isSorted = false;
	private int generationNumber;
	private int bestCreature;
	private int averageCreature;
	private int worstCreature;
	private double highestFitness;
	private double averageFitness;
	private double lowestFitness;
	
	public Generation(){
		//initializing the Generation object with a creaturesList
		if(creaturesList ==null){
			creaturesList = new ArrayList<Creature>();
		}
	}
	
	//Method to create a generation of creatures
	public void createGenerations(ArrayList<Generation> generationsList, HashMap<Integer,Creature> creaturesHashMap){
		generationNumber=Globals.currentGen;
		System.out.printf("Creating generation " + generationNumber + "\n");
		
		//If it's crating the first generation 
		if(generationNumber==0){
			for(int j=0; j<Globals.maxCreatures;j++){
				//Creates a new Creature object in the list, generates it's parameters and then adds it too the HashMap 
				this.creaturesList.add(new Creature());
				this.creaturesList.get(j).genCreatures();					
				creaturesHashMap.put(this.creaturesList.get(j).getUid(),this.creaturesList.get(j));
			}
		}
		//If its not the first generation 
		else{
			currentCreature=0;
			currentParent=0;
			//Tries to mutate the Creatures of the past generation by chance
			mutateGeneration(generationsList.get(generationNumber-1));
			//SetParents method is used on the current Generation using the Creatures of the past Generation
			setParents(generationsList.get(generationNumber-1));
			for(int j=0; j<Globals.maxParents;j++){
				//Iterates through the creatures created from mating, uses the crossover method of the Creature class and adds them on the HashMap
				this.creaturesList.get(j).crossover(creaturesHashMap);
				creaturesHashMap.put(generationsList.get(generationNumber).creaturesList.get(j).getUid(),generationsList.get(generationNumber).creaturesList.get(j));
				currentCreature++;
			}
			int counter =0;
			for(int j=currentCreature; j<Globals.maxCreatures;j++){
				//The remaining space to reach the max capacity of Creatures in a a Generation are filled with the best Creatures from the previous Generation
				generationsList.get(generationNumber).creaturesList.add(generationsList.get(generationNumber-1).creaturesList.get(counter));
				counter++;
			}
		}
		sortGeneration();
	}
	
	//Prints informations about the Generation such as: per Creature data, highest and lowest fitness, and the average
	//It can display the full data of the Creatures or the shorter data
	public void printGeneration(Boolean shortData){
		System.out.printf("\n"+"==============="+"Generation "+this.generationNumber+"==============="+"\n");
		if(shortData){
			for(int j=0;j<Globals.maxCreatures;j++){
				this.creaturesList.get(j).printShortData();
				System.out.printf("\n");
			}
		}
		else{
			for(int j=0;j<Globals.maxCreatures;j++){
				this.creaturesList.get(j).printData();
				System.out.printf("\n");
			}
		}
		System.out.printf("%-20s %b %n","Is Sorted:",this.isSorted);
		System.out.printf("%-20s %.3f%n","Highest Fitness:",calcMaxGenFitness());
		System.out.printf("%-20s %.3f%n","Average Fitness:",calcAvgGenFitness());
		System.out.printf("%-20s %.3f%n","Lowest Fitness:",calcMinGenFitness());
		System.out.printf("==============="+"Generation "+this.generationNumber+"==============="+"\n");
	}
	
	//Find the highest fitness Creature from the Generation
	private double calcMaxGenFitness(){
		//Gets the Creature with the Highest fitness (after the Generation is sorted, this will be the first Creature on the ArrayList)
		this.bestCreature = this.creaturesList.get(0).getUid();
		this.highestFitness =this.creaturesList.get(0).getFitness();
		return this.creaturesList.get(0).getFitness();
	}
	
	//Find the lowest fitness Creature from the Generation
	private double calcMinGenFitness(){
		//Gets the Creature with the Lowest fitness (after the Generation is sorted, this will be the last Creature on the ArrayList)
		this.worstCreature= this.creaturesList.get(Globals.maxCreatures-1).getUid();
		this.lowestFitness =this.creaturesList.get(Globals.maxCreatures-1).getFitness();
		return this.creaturesList.get(Globals.maxCreatures-1).getFitness();
	}
	
	//Calculates the average fitness of the Generation
	private double calcAvgGenFitness(){
		double genFit=0;
		for(int j=0;j<Globals.maxCreatures;j++){
			genFit += this.creaturesList.get(j).getFitness();
		}
		genFit /= (this.creaturesList.size());
		this.averageFitness =genFit;
		return genFit;
	}
	
	//Sorts the Generation by order of highest fitness to lowest
	public void sortGeneration(){
		Collections.sort(this.creaturesList);
		this.isSorted=true;
	}
	
	//This method sets the parents for each new Creature after the first Generation
	public void setParents(Generation parentGeneration){
		if(!this.isSorted){
			parentGeneration.sortGeneration();
		}
		for(int i = 0;i<(Globals.maxParents);i++){
			this.creaturesList.add(new Creature());
			this.creaturesList.get(i).setParents(parentGeneration.creaturesList.get(currentParent).getUid(),parentGeneration.creaturesList.get(currentParent+1).getUid());
			currentParent+=2;
		}
	}
	//Method for using the mutate method inside the Creature class on each Creature 
	public void mutateGeneration(Generation generation){
		for(Creature creature : generation.creaturesList){
			creature.mutate();
		}
	}
	
	public Boolean getIsSorted() {
		return isSorted;
	}
	
	public ArrayList<Creature> getCreaturesList() {
		return creaturesList;
	}
	
	public int getGenerationNumber() {
		return generationNumber;
	}
	
	public int[] getImportantCretures(){
		int[] c= {this.bestCreature,this.averageCreature,this.worstCreature}; 
		return c;
	}
	
	public double[] getFitnesss(){
		double[] fitness = {this.highestFitness,this.averageFitness,this.lowestFitness};
		return fitness;
	}

	@Override
	public String toString(){
		String string = "Generation "+this.generationNumber;
		return string;
	}
	
}
