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
	private void genUid(int uid){
		this.uid =uid;
	}
	private void genLimbs(int[] limbs){
		this.limbs = Utilities.RandInt(limbs[0],limbs[1]);
	}
	private void genLegs(int minLegs, int minArms){
		this.legs = Utilities.RandInt(minLegs,(this.limbs - minArms) );
	}
	private void genArms(int minArms){
		this.arms = Utilities.RandInt(minArms,(this.limbs - this.legs));
	}
	private void genDeathChance(int death){
		this.deathChance = Utilities.RandInt(0, death);
	}
	private void genMutationChance(int mutation){
		this.mutationChance = Utilities.RandInt(0, mutation);
	}
	private void genWeightMutltiplier(int weight){
		this.weightMutltiplier = Utilities.RandInt(-weight, weight);
	}
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
		System.out.println("Creature uid: " + this.uid);
		System.out.printf("Parents: " + this.parents[0] + "  " + this.parents[1] + "\n");
		System.out.printf("Sorted: " +this.isSorted+ " \n");
		System.out.printf("Limbs: " + this.limbs +"     "+"Limbs Fit: "+ Utilities.Round(this.limbsFit) + " \n");
		System.out.printf("Legs: " + this.legs +"     "+"Limbs Fit: "+ Utilities.Round(this.legsFit) + " \n");		
		System.out.printf("Arms: " + this.arms +"     "+"Arms Fit: "+ Utilities.Round(this.armsFit) + " \n");
		System.out.printf("Death Chance: " + Utilities.Round(this.deathChance) +"     "+"Death Chance Fit: "+ Utilities.Round(this.deathFit) + " \n");
		System.out.printf("Mutation Chance: " + Utilities.Round(this.mutationChance) +"     "+"Mutation Chance Fit: "+ Utilities.Round(this.mutationFit )+ " \n");
		System.out.printf("Weight Mutiplier: " + Utilities.Round(this.weightMutltiplier) +"     "+"Limbs Fit: "+ Utilities.Round(this.weightFit) + " \n");
		System.out.printf("Lifespam Multiplier: " + Utilities.Round(this.lifespamMultiplier) +"     "+"Limbs Fit: "+ Utilities.Round(this.lifespamFit) + " \n");
		System.out.printf("Fitness: " + Utilities.Round(this.fitness) + "\n");
	}
	public void printShortData(){
		System.out.print("Creature uid: " + this.uid + "   ");
		System.out.printf("Sorted " +this.isSorted+ "   ");
		System.out.printf("Parent1 " +this.parents[0]+ "   ");
		System.out.printf("Parent2 " +this.parents[1]+ "   ");
		System.out.printf("Fitness: " + Utilities.Round(this.fitness) + "\n");
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
	
	private void copyGenes(Creature parentCreature, int min,int max){
		for(int i=min;i<max;i++){
			switch(i){
			case 0: this.limbs=parentCreature.getGenes()[0];
			case 1: this.legs=parentCreature.getGenes()[1];
			case 2: this.arms=parentCreature.getGenes()[2];
			case 3: this.deathChance=parentCreature.getGenes()[3];
			case 4: this.mutationChance=parentCreature.getGenes()[4];
			case 5: this.weightMutltiplier=parentCreature.getGenes()[5];
			case 6: this.lifespamMultiplier=parentCreature.getGenes()[6];
			}
		}
	}
	
	private void mutate(){
		double randMutation = Utilities.RandDouble(0.0, 1.0);
		if(randMutation<this.mutationChance){
			int gene = Utilities.RandInt(1, 7);
			switch(gene){
				case 1:genLimbs(Globals.paramLimbs);
				case 2:genLegs(Globals.minLegs,Globals.minArms);
				case 3:genArms(Globals.minArms);
				case 4:genDeathChance(Globals.maxDeathChance);
				case 5:genMutationChance(Globals.maxMutationChance);
				case 6:genLifespamMultiplier(Globals.lifespamMultiplier);
				case 7:genWeightMutltiplier(Globals.weightMultiplier);
			}
		}
	}
	
	public void crossover(HashMap<Integer,Creature> creaturesHash, int gen){
		int divisions= Utilities.RandInt(1, 2);
		int selection= Utilities.RandInt(1, 2);
		int divisionLine =  Utilities.RandInt(this.genes/4, this.genes - this.genes/4);
		Creature parentCreature1 = creaturesHash.get(this.parents[0]);
		Creature parentCreature2 = creaturesHash.get(this.parents[1]);
		if(divisions ==1){
			if(Utilities.RandInt(1, 2)==1){
				this.copyGenes(parentCreature1, 0, divisionLine);
				this.copyGenes(parentCreature2, divisionLine, this.genes);
			}
			else{
				this.copyGenes(parentCreature2, 0, divisionLine);
				this.copyGenes(parentCreature1, divisionLine, this.genes);
			}
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
