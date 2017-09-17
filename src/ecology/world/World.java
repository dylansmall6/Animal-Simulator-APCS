package ecology.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import ecology.Main;
import ecology.animals.Animal;
import ecology.animals.Squirrel;
import ecology.animals.Tiger;
import ecology.resource.Nuts;
import ecology.resource.Resource;

public class World {
	Random random = new Random();
	//MAKING WORLDITEM ARRAY,MAXIMUM X,MAXIMUM Y, ANIMALS, RESOURCES, TIGER NAMES, SQUIRREL NAMES
	private ArrayList<Animal> animals = new ArrayList<Animal>();
	private ArrayList<Resource> resources = new ArrayList<Resource>();
	private ArrayList<String> tigerNames = new ArrayList<String>(Arrays.asList("Jumper","Pouncer","Hazer","Matt","Drefler","Tazer","Steadman","Feaster","Bouncer","Killer"));
	private ArrayList<String> squirrelNames = new ArrayList<String>(Arrays.asList("Bouncie","Lousy","Zippy","Yappy","Minger","Bogey","Zoomy","Zizzles","Zoey","Parky","Dusty","Ruffles","Nick","Loosy","Ponty","Anker","Sniffles","Wrufflet","Quin","Tina","Yapper"));
	public static WorldItem[][] world;
	public static int xMax;
	public static int yMax;
	public World(int x,int y){
		xMax = x;
		yMax = y;
		world = new WorldItem[xMax][yMax];
	}
	public World() {
	}
	public void generate(int numOfTiger,int numOfSquirrel,int numOfNuts) throws InterruptedException{
		/*
		 * Some balloney to make it look cool while setting up
		 */
		System.out.println("Generating World...");
		Thread.sleep(2000);
		System.out.println("Generated World of size: " + xMax+"x"+yMax);
		Thread.sleep(2000);
		/*---------------------*\
		 *  GENERATES TIGERS   *
		\*---------------------*/
		generateTigers(numOfTiger);
		/*---------------------*\
		 * GENERATES SQUIRRELS *
		\*---------------------*/
		generateSquirrels(numOfSquirrel);
		/*---------------------*\
		 *   GENERATES NUTS    *
		\*---------------------*/
		generateNuts(numOfNuts);
		//DONE WITH GENERATION
		System.out.println("World Generated... Starting day one");
		//I am about to sleep! lol
		Thread.sleep(2000);
	}
	/*
	 * GETTERS AND SETTERS
	 * getxMax,setxMax
	 * getyMax,setyMax
	 * getAnimals,setAnimals
	 */
	public int getxMax() {
		return xMax;
	}
	public void setxMax(int xMax) {
		World.xMax = xMax;
	}
	public int getyMax() {
		return yMax;
	}
	public void setyMax(int yMax) {
		World.yMax = yMax;
	}
	public ArrayList<Animal> getAnimals(){
		return animals;
	}
	public void setAnimals(ArrayList<Animal> animals){
		this.animals = animals;
	}
	public ArrayList<Resource> getResources(){
		return resources;
	}
	public void setResources(ArrayList<Resource> resources){
		this.resources = resources;
	}
	public void generateTigers(int numOfTiger) throws InterruptedException{
		System.out.println("Generating Tigers...");
		Thread.sleep(2000);
		/*
		 * TIGER GEN
		 */
		for(int i=0;i<numOfTiger;i++){ 
			//looping through all animals
			//rand x and y for the animal
			int randX = random.nextInt(xMax);
			int randY = random.nextInt(yMax);
			String randName = tigerNames.remove(random.nextInt(tigerNames.size()));	
			int randEnergy = random.nextInt(11)+10;
			int randHealth = random.nextInt(11)+10;
			while(world[randX][randY] instanceof WorldItem){
				//just in case there is already one there!
				randX = random.nextInt(xMax);
				randY = random.nextInt(yMax);
			}
			//puts one on the array just to make sure that no others can spawn on it
			world[randX][randY] = new WorldItem();
			char gender = random.nextInt(2) == 0 ? 'M' : 'F';
			int eyesight = random.nextInt(3) + 3, speed = random.nextInt(2)+1;
			animals.add(new Tiger(randName,randX,randY,randEnergy,randHealth,true,gender,eyesight,speed));
			//Prints out the created animal
			System.out.println("Created new Tiger named "
											 +randName + " with "
											 +randEnergy+ " hunger and "
											 +randHealth+" health at ("
											 +randX+","+randY+")");
			//Sleeps for more balloney!
			Thread.sleep(400);
		}
		/*
		 * END OF TIGER GEN
		 */
	}
	public void generateSquirrels(int numOfSquirrel) throws InterruptedException{
		//Sleeps for squirrels
				System.out.println("Generating Squirrels...");
				Thread.sleep(2000);
				/*
				 * SQUIRREL GEN
				 */
				for(int i=0;i<numOfSquirrel;i++){ 
					//looping through all animals
					//rand x and y for the animal
					int randX = random.nextInt(xMax);
					int randY = random.nextInt(yMax);
					String randName = squirrelNames.remove(random.nextInt(squirrelNames.size()));
					int randEnergy = random.nextInt(11)+3;
					int randHealth = random.nextInt(11)+3;
					int reproductionStart = random.nextInt(3)+2;
					int reproductionMin = random.nextInt(2)+5;
					while(world[randX][randY] instanceof WorldItem){
						//just in case there is already one there!
						randX = random.nextInt(xMax);
						randY = random.nextInt(yMax);
					}
					//puts one on the array just to make sure that no others can spawn on it
					world[randX][randY] = new WorldItem();
					char gender = random.nextInt(2) == 0 ? 'M' : 'F';
					int eyesight = random.nextInt(3) + 3, speed = random.nextInt(2)+1;
					animals.add(new Squirrel(randName,randX,randY,randEnergy,randHealth,true,true,reproductionStart,reproductionMin,gender,eyesight,speed));
					//Prints out the created animal
					System.out.println("Created new Squirrel named "
													 +randName + " with "
													 +randEnergy+ " hunger and "
													 +randHealth+" health at ("
													 +randX+","+randY+"). It has " 
													 +(reproductionMin - reproductionStart) + " days until it is able to first reproduce.");
					//Sleeps for more balloney!
					Thread.sleep(400);
				}
				/*
				 * END OF SQUIRREL GEN
				 */
	}
	public void generateNuts(int numOfNuts) throws InterruptedException{
		/*
		 * NUT GEN
		 */
		System.out.println("Generating Nuts...");
		//Dang I must really like sleeping
		Thread.sleep(2000);
		for(int i=0;i<numOfNuts;i++){
			//looping through resources
			//another rand x and y
			int randX = random.nextInt(xMax);
			int randY = random.nextInt(yMax);
			while(world[randX][randY] instanceof WorldItem){
				//just in case again :) 
				randX = random.nextInt(xMax);
				randY = random.nextInt(yMax);
			}
			//adds it to make sure nothing spawns on it
			world[randX][randY] = new WorldItem();
			resources.add(new Nuts(randX,randY));
			//Prints it all out :)
			System.out.println("Created new Nut named that gives 5 energy at ("
											 +randX+","+randY+")");
			//of course...
			Thread.sleep(400);
		}
		//I am getting tired
		Thread.sleep(2000);
		/*
		 * END OF NUT GEN
		 */
	}
	/**
	 * Randomly spawns a certain number of nuts throughout the world
	 * @param min : minimum amount of nuts given
	 * @param max : maximum amount of nuts given
	 */
	public static void repopulateNuts(int min, int max){
		World world = new World();
		int num = world.random.nextInt((max - min) + 1) + min;
		System.out.println(num + " nuts fell from the sky!!!");
		for(int i=0;i<Main.resources.size();i++){
			if(!Main.resources.get(i).isAlive()){
				World.world[Main.resources.get(i).getXpos()][Main.resources.get(i).getYpos()] = null;
			}
		}
		for(int i=0;i<num;i++){
			int randX = world.random.nextInt(xMax);
			int randY = world.random.nextInt(yMax);
			while(World.world[randX][randY] instanceof WorldItem){
				//just in case again :) 
				randX = world.random.nextInt(xMax);
				randY = world.random.nextInt(yMax);
			}
			//adds it to make sure nothing spawns on it
			World.world[randX][randY] = new WorldItem();
			world.resources.add(new Nuts(randX,randY));
		}
	}
}
//End of class