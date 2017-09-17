package ecology;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import ecology.animals.Animal;
import ecology.animals.AnimalEvent;
import ecology.resource.Resource;
import ecology.resource.ResourceEvent;
import ecology.weather.WeatherEvent;
import ecology.world.World;

public class Main
{
AnimalEvent events = new AnimalEvent();
public static ArrayList<Animal> animals = new ArrayList<Animal>(6);
public static ArrayList<Resource> resources = new ArrayList<Resource>();
public int numOfDays = 1;
public int numTigers;
public int numSquirrels;
public int numNuts;
public boolean isWeather;
public int daysSinceStormStarted = 0;
public static void main(String args[]) throws InterruptedException
{
	Main main = new Main();
	main.setUp();
}

/**
* setUp initializes all animal instances and prints init states
 * @throws InterruptedException 
*/
public void setUp() throws InterruptedException
{
	Scanner in = new Scanner(System.in);
	welcome();
	ask(in);
	World world = new World(10,10);
	world.generate(numTigers,numSquirrels,numNuts);
	animals = world.getAnimals();
	resources = world.getResources();
	tick(animals,resources);
}
private void welcome() {
	try{
		FileReader fr = new FileReader("chars.txt");
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(fr);
		String s="";
		char [] line;
		int i=0,j=0;
		char [][]chars = new char[5][42];
		while((s = br.readLine()) != null) 
		{
			line=s.toCharArray();

			for(j=0;j<line.length;j++)
			{
				chars[i][j]=line[j];
				System.out.print(""+chars[i][j]);
				if(i == 1 || i == 3){
					Thread.sleep(10);
				}
				else{
					Thread.sleep(20);
				}
			}
			i++;
			System.out.print("\n");
		}
    }
	catch(Exception e)
	{
		System.out.println("Welcome to Ecology Simulator V0.11!");
	}
}					

private void ask(Scanner in) {
	System.out.println("How many Tigers in this simulation? (<=5)");
	numTigers = in.nextInt();
	while(numTigers > 5){
		System.out.println("Please enter a number less than or equal to five");
		numTigers = in.nextInt();
	}
	System.out.println("How many Squirrels in this simulation?(<=15)");
	numSquirrels = in.nextInt();
	while(numSquirrels > 15){
		System.out.println("Please enter a number less than or equal to fifteen");
		numSquirrels = in.nextInt();
	}
	System.out.println("How many Nuts in this simulation?(<=20)");
	numNuts = in.nextInt();
	while(numNuts > 20){
		System.out.println("Please enter a number less than or equal to twenty");
		numNuts = in.nextInt();
	}
}

/**
* Runs the entire game, updates the animals, basically the main method of the game
* @throws InterruptedException 
* 
*/
public void tick(ArrayList<Animal> a,ArrayList<Resource> r) throws InterruptedException
{	
	while(!AnimalEvent.isEveryAnimalDead(a)){
		int randNum = (int) (Math.random() * 11);
		if(randNum == 0 && !isWeather){
			isWeather = true;
		}
		randNum = (int) (Math.random() * 11);
		if(randNum == 0){
			World.repopulateNuts(2, 4);
		}
		//Print out the format!!!
		System.out.println("Day "+numOfDays+":");
		System.out.printf("%s\t%-10s%-10s%-10s%-10s%-10s\n","GENDER","TYPE","NAME","(X,Y)","ENERGY","HEALTH");
		System.out.println("...........................................................");
		//Now we do the moves
		doAll(a,r);
		numOfDays++;
		Thread.sleep(3000);
	}
	printEndOfGame();
}

/**
* Print the type, name, xpos, ypos, energy, and health of Animal a
* @param a
*/
public void printAnimalUpdate(Animal a)
{
	System.out.printf("%s\t%-10s%-10s%-10s%-10s%-10s\n",a.getGender(), a.getType(),a.getName(),"("+a.getXpos()+","+a.getYpos()+")"
												,a.getEnergy(),a.getHealth());
}
/**
 * Prints the end of the game: stats and more 
 */
public void printEndOfGame(){
	FileWriter wr = null;
	BufferedWriter bw = null;
	System.out.println("GAME ENDED\nSTATS:\n\tDays: "+ (numOfDays-1) + 
										 "\n\tNumber of births: "+ AnimalEvent.numOfBirths+
										 "\n\tNumber of Plants eaten: "+ResourceEvent.numOfPlantsEaten+
										 "\n\tNumber of Animals that rose from the dead: "+AnimalEvent.numOfRebirths + " (" + AnimalEvent.numOfZombies + " of which were zombies)");
	try {
		wr = new FileWriter("stats.txt");
		bw = new BufferedWriter(wr);
		bw.write("GAME ENDED\nSTATS:\n\tDays: "+ (numOfDays-1) + 
				 "\n\tNumber of births: "+ AnimalEvent.numOfBirths+
				 "\n\tNumber of Plants eaten: "+ResourceEvent.numOfPlantsEaten+
				 "\n\tNumber of Animals that rose from the dead: "+AnimalEvent.numOfRebirths + " (" + AnimalEvent.numOfZombies + " of which were zombies)");
	} catch (IOException e) {
		e.printStackTrace();
	}finally {
		try {
			if(bw !=null){
				bw.close();
			}
			if(wr != null){
				wr.close();
			}
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}
/**
 * Does all of the movements. ALL OF THEM!!!
 * @param a
 * @param r
 * @throws InterruptedException
 */
public void doAll(ArrayList<Animal> a, ArrayList<Resource> r) throws InterruptedException{
	if(isWeather){
		
	}
	for(int i=0;i<a.size();i++){
		/*
		 * PREDATOR MOVES
		 */
		if(a.get(i).getPredType().equals("Predator")){
			for(int j=0;j<a.size();j++){
				if(a.get(j).getPredType().equals("Prey")){
					if(!a.get(i).isZombie()&&a.get(j).isAlive()){
						a.get(i).chase(a.get(j));
						break;
					}
					else if(a.get(i).isZombie()&&a.get(j).getType().equals(a.get(i).getType())&&a.get(j).isAlive()){
						//If it is a zombie, chase others of its kind
						a.get(i).chase(a.get(j));
						break;
					}
					
				}
				else{
					continue;
				}
			}
		}
		/*
		 * PREY MOVES
		 */
		else if(a.get(i).getPredType().equals("Prey")){
			for(int j=0;j<a.size();j++){
				if(a.get(j).getPredType().equals("Prey")){
					if(!a.get(i).isZombie()&&!a.get(j).isZombie()&&a.get(j).isAlive()){
						a.get(i).follow(a.get(j));
						break;
					}
					else if(a.get(i).isZombie()&&a.get(j).getType().equals(a.get(i).getType())&&a.get(j).isAlive()){
						//If it is a zombie, chase others of its kind
						a.get(i).chase(a.get(j));
						break;
					}
				}			
				else{
					continue;
				}
			}
		}
		else{
			a.get(i).move(0,0,World.xMax,World.yMax,a.get(i).getSpeed());
		}
		/*
		 * Check for resources
		 */
		for(int j=0;j<r.size();j++){
			ResourceEvent.checkToSeeIfAnimalIsNearPlant(a.get(i), r.get(j));
		}
		/*
		 * Reincarnation try
		 */
		events.doRandomReincarnation(a.get(i));
		/*
		 * Reduces Energy
		 */
		if(a.get(i).getEnergy()>0){
			a.get(i).setEnergy(a.get(i).getEnergy()-1);
		}
		else{
			//Reduces Health if Energy < 0 :( rip in piece
			a.get(i).setHealth(a.get(i).getHealth()-2);
			if(a.get(i).getHealth()<=0){
				a.get(i).setHealth(0);
				a.get(i).setAlive(false);
			}
		}
		/*
		 * Reproduction Updates
		 */
		a.get(i).setEnergyToReproduce(a.get(i).getEnergyToReproduce()+1);
		if(a.get(i).getEnergyToReproduce() >= a.get(i).getMinEnergyToReproduce()){
			a.get(i).setCanReproduce(true);
		}
		/*
		 * Prints out animals
		 */
		if(a.get(i).isAlive()){
			printAnimalUpdate(a.get(i));
		}
		else{
			//Does/Prints nothing if animal is dead!
			//System.out.print("\t"+a.get(i).getName()+" is dead!\n");
		}
	}
}
}