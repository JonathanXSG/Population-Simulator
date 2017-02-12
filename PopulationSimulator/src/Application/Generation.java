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
	
	// Array for storing all the creatures in each generation
	private ArrayList<Creature> creaturesList;
	private static int currentCreature= 0;
	private static int currentParent= 0;
	private static int currentGen;
	
	public Generation(){
		//initializing the Generation object with a creaturesList
		if(creaturesList ==null){
			creaturesList = new ArrayList<Creature>();
		}
	}
	
	//Method to create a generation of creatures
	public static void createGenerations(ArrayList<Generation> generationsList, HashMap<Integer,Creature> creaturesHashMap, int currentGen){
		System.out.printf("Creating generation " + Globals.currentGen + "\n");

		//If it's crating the first generation 
		if(currentGen==0){
			//Creates the Generation object in the List
			generationsList.add(new Generation());
			for(int j=0; j<Globals.maxCreatures;j++){
				//Creates a new Creature object in the list, generates it's parameters and then adds it too the HashMap 
				generationsList.get(currentGen).creaturesList.add(new Creature());
				generationsList.get(currentGen).creaturesList.get(j).genCreatures();					
				creaturesHashMap.put(generationsList.get(currentGen).creaturesList.get(j).getUid(),generationsList.get(Globals.currentGen).creaturesList.get(j));
			}
		}
		//If its not the first generation 
		else{
			currentCreature=0;
			currentParent=0;
			//Creates the Generation object in the list and uses the SetParents method on it
			generationsList.add(new Generation());
			setParents(generationsList,currentGen);
			for(int j=0; j<Globals.maxParents;j++){
				//Iterates through the creatures created from mating, uses the crossover method of the Creature class and add them on the HashMap
				generationsList.get(currentGen).creaturesList.get(j).crossover(creaturesHashMap, Globals.currentGen);
				creaturesHashMap.put(generationsList.get(currentGen).creaturesList.get(j).getUid(),generationsList.get(currentGen).creaturesList.get(j));
				currentCreature++;
			}
			int counter =0;
			for(int j=currentCreature; j<Globals.maxCreatures;j++){
				//The remaining space to reach the max capacity of Creatures in a a Generation are filled with the best Creatures from the previous Generation
				generationsList.get(currentGen).creaturesList.add(generationsList.get(currentGen-1).creaturesList.get(counter));
				counter++;
			}
		}
		currentGen++;
		Globals.currentGen=currentGen;
	}
	
	//Prints informations about the Generation such as: per Creature data, highest and lowest fitness, and the average
	//It can display the full data of the Creatures or the shorter data
	public void printGeneration(int gen, Boolean shortData){
		System.out.printf("\n"+"==============="+"Generation "+gen+"==============="+"\n");
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
		System.out.printf("%-20s %.3f%n","Highest Fitness:",calcMaxGenFitness());
		System.out.printf("%-20s %.3f%n","Average Fitness:",calcAvgGenFitness());
		System.out.printf("%-20s %.3f%n","Lowest Fitness:",calcMinGenFitness());
		System.out.printf("==============="+"Generation "+gen+"==============="+"\n");
	}
	
	//Find the highest fitness Creature from the Generation
	public double calcMaxGenFitness(){
		//If the Generation is sorted then just get the first Creature, otherwise find the highest 
		if(this.creaturesList.get(0).getIsSorted()){
			return this.creaturesList.get(0).getFitness();
		}
		else{
			double genFit = this.creaturesList.get(0).getFitness();
			for(int j=0;j<Globals.maxCreatures;j++){
				if(this.creaturesList.get(j).getFitness() > genFit){
					genFit = this.creaturesList.get(j).getFitness();
				}
			}
			return genFit;
		}
	}
	
	//Find the lowest fitness Creature from the Generation
	public double calcMinGenFitness(){
		//If the Generation is sorted then just get the last Creature, otherwise find the lowest 
		if(this.creaturesList.get(0).getIsSorted()){
			return this.creaturesList.get(Globals.maxCreatures-1).getFitness();
		}
		else{
			double genFit = this.creaturesList.get(0).getFitness();
			for(int j=0;j<Globals.maxCreatures;j++){
				if(this.creaturesList.get(j).getFitness() < genFit){
					genFit = this.creaturesList.get(j).getFitness();
				}
			}
			return genFit;
		}
	}
	
	//Calculates the average fitness of the Generation
	public double calcAvgGenFitness(){
		double genFit=0;
		for(int j=0;j<Globals.maxCreatures;j++){
			genFit += this.creaturesList.get(j).getFitness();
		}
		genFit /= (this.creaturesList.size());
		return genFit;
	}
	
	//Sorts the Generation by order of highest fitness to lowest
	public void sortGeneration(){
		Collections.sort(creaturesList);
		for(Creature i : creaturesList){
			i.setIsSorted();
		}
	}
	
	//This method sets the parents for each new Creature after the first Generation
	public static void setParents(ArrayList<Generation> generationsList,int gen){
		for(int i = 0;i<(Globals.maxParents);i++){
			generationsList.get(gen).creaturesList.add(new Creature());
			generationsList.get(gen).creaturesList.get(i).setParents(generationsList.get(gen-1).creaturesList.get(currentParent).getUid(),generationsList.get(gen-1).creaturesList.get(currentParent+1).getUid());
			currentParent=+2;
		}
	}
	
	
	
}
