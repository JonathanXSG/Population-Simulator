package Application;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Population {
	
	private ArrayList<Creature> creaturesList;
	static int currentCreature=0;
	static int currentParent =0;
	
	public Population(){
		if(creaturesList ==null){
			creaturesList = new ArrayList<Creature>();
		}
	}
	
	public static void createGenerations(ArrayList<Population> generationsList, HashMap<Integer,Creature> creaturesHash){
		if(Globals.currentGen==0){
			System.out.printf("Creating Generation 0 \n");
			generationsList.add(new Population());
			for(int j=0; j<Globals.maxCreatures;j++){
				generationsList.get(Globals.currentGen).creaturesList.add(new Creature());
				generationsList.get(Globals.currentGen).creaturesList.get(j).genCreatures();					
				creaturesHash.put(generationsList.get(Globals.currentGen).creaturesList.get(j).getUid(),generationsList.get(Globals.currentGen).creaturesList.get(j));
			}
		}
		else{
			System.out.printf("Creating generation " + Globals.currentGen + "\n");
			currentCreature=0;
			currentParent=0;
			generationsList.add(new Population());
			setParents(generationsList,Globals.currentGen);
			for(int j=0; j<Globals.maxParents;j++){
				generationsList.get(Globals.currentGen).creaturesList.get(j).crossover(creaturesHash, Globals.currentGen);
				creaturesHash.put(generationsList.get(Globals.currentGen).creaturesList.get(j).getUid(),generationsList.get(Globals.currentGen).creaturesList.get(j));
				currentCreature++;
			}
			int counter =0;
			for(int j=currentCreature; j<Globals.maxCreatures;j++){
				System.out.println(counter);
				generationsList.get(Globals.currentGen).creaturesList.add(generationsList.get(Globals.currentGen-1).creaturesList.get(counter));
				counter++;
			}
		}
		Globals.currentGen++;
	}
	
	public void printGeneration(int gen){
		System.out.printf("--------------"+"Generation "+gen+"--------------"+"\n");
		for(int j=0;j<Globals.maxCreatures;j++){
			System.out.printf("Generation "+gen+"\n");
			this.creaturesList.get(j).printData();
			System.out.printf("\n");
		}
		System.out.println(Utilities.Round(calcMaxGenFitness()));
		System.out.println(Utilities.Round(calcMinGenFitness()));
		System.out.println(Utilities.Round(calcAvgGenFitness(gen)));
		System.out.printf("The value in key 0 is "+Globals.creaturesHash.get(0).getFitness()+"\n");
		System.out.printf("\n");
	}
	
	public double calcMaxGenFitness(){
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
	
	public double calcMinGenFitness(){
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
	
	public double calcAvgGenFitness(int gen){
		double genFit=0;
		for(int j=0;j<Globals.maxCreatures;j++){
			genFit += this.creaturesList.get(j).getFitness();
		}
		genFit /= (this.creaturesList.size());
		return genFit;
	}
	
	public void sortGeneration(){
		Collections.sort(creaturesList);
		for(Creature i : creaturesList){
			i.setIsSorted();
		}
	}
	
	public static void setParents(ArrayList<Population> generationsList,int gen){
		for(int i = 0;i<(Globals.maxParents);i++){
			generationsList.get(gen).creaturesList.add(new Creature());
			generationsList.get(gen).creaturesList.get(i).setParents(generationsList.get(gen-1).creaturesList.get(currentParent).getUid(),generationsList.get(gen-1).creaturesList.get(currentParent+1).getUid());
			currentParent+=2;
		}
	}
	
	
	
}
