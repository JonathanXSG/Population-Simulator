package Application;
import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

public class Utilities {

	public static int RandInt(int min, int max) {
		if(min==max){
			return min;
		}
		else{
			return ThreadLocalRandom.current().nextInt(min, max+1);
		}
	}
	public static double RandDouble(double min, double max){
		if(min==max){
			return min;
		}
		else{
			return ThreadLocalRandom.current().nextDouble(min,max);
		}
	}
	
	public static double Round(double number){
		DecimalFormat df = new DecimalFormat("#");     
		return Double.valueOf(df.format(number));
	}
}
