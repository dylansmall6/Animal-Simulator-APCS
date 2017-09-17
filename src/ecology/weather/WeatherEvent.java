package ecology.weather;

import java.util.Random;

public class WeatherEvent {
	public static void doWeather(){
		Random random = new Random();
		int chance = random.nextInt(3);
		switch(chance){
		case 0:
			System.out.println("Tornado");
			break;
		case 1:
			System.out.println("Storm");
			break;
		case 2:
			System.out.println("Flood");
			break;
		}
	}
}
