package ecology.animals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import ecology.Main;
import ecology.world.World;
import ecology.world.WorldItem;

public class AnimalEvent {
	//Counters for stats at end of game
	public static int numOfBirths = 0;
	public static int numOfRebirths = 0;
	public static int numOfZombies = 0;
	//Random names just in case :)
	private static ArrayList<String> names = new ArrayList<String>(Arrays.asList("Bailey","Max","Joey","Tanky","Zoey","Bogey","Lizzy","Dizzy","Bo","Sizzy","Mozey","Dunky","Speedy","Zoney","Boney","Lonely","Pokey"));
	/**
	 * This method returns true if every animal is dead, false if one or more is alive. 
	 * Pretty smart thinkin if you ask me
	 * @param animals
	 * @return
	 */
	public static boolean isEveryAnimalDead(ArrayList<Animal> animals){
		for(int i=0;i<animals.size();i++){
			if(animals.get(i).isAlive()==true){
				return false;
			}		
		}
		return true;
		
	}
	/**
	 * Checks to see if two Prey are on the same tile or block or whatever its called
	 * @param a
	 * @param b
	 */
	public static void areTwoPreywOverLapping(Animal a,Animal b){
				if(a.getXpos()==b.getXpos()&&a.getYpos()==b.getYpos()){
					if(a.getPredType().equals("Prey")&&b.getPredType().equals("Prey")){
						if(!a.getName().equals(b.getName())){
							if(a.isCanReproduce()&&b.isCanReproduce()){
								if(a.isAlive()&&b.isAlive()){
									if(a.getType().equals(b.getType())){
										if(!(a.getGender() == b.getGender())){
											reproduce(a,b);
											a.setMinEnergyToReproduce(5);
											b.setMinEnergyToReproduce(5);
										}
									}
								}
							}
						}
					}
				}
		
	}
	/**
	 * Reproduces two animals into another animal with an integrated name!
	 * Of course this has to use World class and stuff so yeah... Thats why its static...
	 * @param a
	 * @param b
	 */
	private static void reproduce(Animal a,Animal b){
		Random random = new Random();
		String name = a.getName().substring(0, a.getName().length()/2+1) + b.getName().substring(b.getName().length()/2);
		for(int i=0;i<Main.animals.size();i++){
			if(name.equals(Main.animals.get(i).getName())){
				if(names.size()>0){
				name = names.get(random.nextInt(names.size()));
				names.remove(names.indexOf(name));
				}			
			}
			else{
				name = a.getName().substring(0, a.getName().length()/2+1) + b.getName().substring(b.getName().length()/2);
			}
		}
		int randX = random.nextInt(World.xMax);
		int randY = random.nextInt(World.yMax);
		while(World.world[randX][randY] instanceof WorldItem){
			randX = random.nextInt(World.xMax);
			randY = random.nextInt(World.yMax);
		}
		char gender = random.nextInt(2) == 0 ? 'M' : 'F';
		int rnd = random.nextInt(10);
		int eyesight = (int) (rnd == 0 ? a.getEyesight() + 1 : Math.ceil(((double)a.getEyesight()+b.getEyesight()) / 2)), speed = (int) (rnd == 0 ? a.getSpeed() + 1 : Math.ceil(((double)a.getSpeed() + b.getSpeed()) / 2));
		System.out.println(a.getName()+" and "+b.getName()+" reproduced, creating a new Squirrel named "+ name + " with " + eyesight + " eyesight and " + speed + " speed." );
		Main.animals.add(new Squirrel(name,randX,randY,((a.getEnergy()+b.getEnergy())/2)+6,((a.getHealth()+a.getHealth())/2)+6,true,false,0,10,gender,eyesight,speed));
		a.setEnergyToReproduce(0);
		b.setEnergyToReproduce(0);
		numOfBirths++;
	}
	/**
	 * 1/730 Chance dead animal reincarnates with random health/energy
	 * 1/3650 That a dead animal reincarnates as a zombie (kills its own type)
	 * @param a
	 */
	public void doRandomReincarnation(Animal a){
		Random random = new Random();
		int chance = random.nextInt(731);
		int zchance = random.nextInt(6);
		int health = random.nextInt(9)+5;
		int energy = random.nextInt(9)+5;
		if(chance == 0){
			if(!a.isAlive()){
				if(zchance == 0){
					a.setZombie(true);
					a.setHealth(health);
					a.setEnergy(energy);
					a.setAlive(true);
					System.out.println(a.getName() + " has miraculously rose from the dead, but as a zombie!!");
					numOfRebirths++;
					numOfZombies++;
				}
				else{
					a.setHealth(health);
					a.setEnergy(energy);
					a.setAlive(true);
					a.setEnergyToReproduce(4);
					System.out.println(a.getName() + " has miraculously rose from the dead!");
					numOfRebirths++;
				}
			}
		}
	}
}
//End of the class!