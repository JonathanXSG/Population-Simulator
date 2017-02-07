package Application;
import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

public class Utilities {

	public static int RandInt(int min, int max) {
		if(min==max){
			return min;
		}
		else{
			int randNum = ThreadLocalRandom.current().nextInt(min, max+1);
			return randNum;
		}
	}
	public static double RandDouble(double min, double max){
		if(min==max){
			return min;
		}
		else{
			double randNum = ThreadLocalRandom.current().nextDouble(min,max);
			return randNum;
		}
	}

	public static double Round(double number){
		DecimalFormat df = new DecimalFormat("#.##");     
		number = Double.valueOf(df.format(number));
		return number;
	}
}
