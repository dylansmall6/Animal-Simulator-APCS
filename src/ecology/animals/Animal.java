package ecology.animals;

import java.util.ArrayList;
import java.util.Random;

import ecology.world.World;
import ecology.world.WorldItem;

public class Animal extends WorldItem implements Predator,Prey{
	Random random = new Random();
	//PRIVATE VARIABLES
	private int energyToReproduce;
	private int minEnergyToReproduce;
	private String name;
	private int hunger;
	private int health;
	private boolean isAlive;
	private boolean isChasing;
	private boolean canReproduce;
	private boolean isZombie = false;
	private char gender;
	private int eyesight;
	private int speed;
	private ArrayList<String> prey = new ArrayList<String>();
	/**
	 * Animal Constructor to update variables form init
	 * @param name
	 * @param initX
	 * @param initY
	 * @param hunger
	 * @param health
	 * @param isAlive
	 * @param canReproduce
	 * @param energyToReproduce
	 * @param minEnergyToReprodce
	 */
	public Animal(String name,int initX, int initY, int hunger,int health, boolean isAlive,boolean canReproduce,int energyToReproduce,int minEnergyToReprodce, char gender,int eyesight,int speed) {
		super(initX, initY);
		this.name = name;
		this.hunger = hunger;
		this.health = health;
		this.isAlive = isAlive;
		this.canReproduce = canReproduce;
		this.energyToReproduce = energyToReproduce;
		this.minEnergyToReproduce = minEnergyToReprodce;
		this.gender = gender;
		this.eyesight = eyesight;
		this.speed = speed;
		prey.add("Squirrel");
	}
	/*
	 * THESE ARE METHODS THAT THE CHILDREN OF THIS CLASS MUST OVERRIDE!!!!
	 * getType	(Usually the name of the class like Tiger,Squirrel)
	 * getPredType (Predator or Prey)
	 */
	public String getType(){return"";}
	public String getPredType(){return"";}
	/* THE FOLLOWING ARE GETTERS AND SETTERS
	 * GetName,SetName
	 * GetHunger,SetHunger
	 * GetHealth,SetHealth
	 * isAlive,setAlive
	 * getPrey
	 * isChasing,setChasing
	 * isCanReproduce,setCanReproduce
	 * getEnergyToReproduce,setEnergyToReproduce
	 * getMinEnergyToReproduce,setMinEnergyToReproduce
	 * isZombie,setZombie
	 * getGender, setGender
	 * getSpeed, setSpeed
	 * getEyesight,setEyesight
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getEnergy() {
		return hunger;
	}
	public void setEnergy(int hunger) {
		this.hunger = hunger;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	@Override
	public ArrayList<String> getPrey() {
		// TODO Auto-generated method stub
		return prey;
	}
	@Override
	public boolean isChasing() {
		return isChasing;
	}
	@Override
	public void setChasing(boolean isChasing) {
		this.isChasing = isChasing;
	}
	public boolean isCanReproduce() {
		return canReproduce;
	}
	public void setCanReproduce(boolean canReproduce) {
		this.canReproduce = canReproduce;
	}
	public int getEnergyToReproduce() {
		return energyToReproduce;
	}
	public void setEnergyToReproduce(int energyToReproduce) {
		this.energyToReproduce = energyToReproduce;
	}
	public int getMinEnergyToReproduce() {
		return minEnergyToReproduce;
	}
	public void setMinEnergyToReproduce(int minEnergyToReproduce) {
		this.minEnergyToReproduce = minEnergyToReproduce;
	}
	public boolean isZombie() {
		return isZombie;
	}
	public void setZombie(boolean isZombie) {
		this.isZombie = isZombie;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public int getEyesight() {
		return eyesight;
	}
	public void setEyesight(int eyesight) {
		this.eyesight = eyesight;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	/* NO MORE GETTERS AND SETTERS
	 * TIME FOR THE ACTUAL METHODS BELOW
	 * hasEnergyToReproduce
	 * move (all animals)
	 * chase (predator)
	 * kill	(predator)
	 * follow (prey)
	 */
	/**
	 * returns true if the animal has enough energy to reproduce
	 */
	public boolean hasEnergyToReproduce(){
		return getEnergyToReproduce() >= minEnergyToReproduce;
	}
	/**
	 * Moves the Animal within a certain boundary
	 * @param minX
	 * @param minY
	 * @param maxX
	 * @param maxY
	 */
	public void move(int minX,int minY,int maxX,int maxY, int speed){
		if(isAlive()){
		setXpos(getXpos()-1+random.nextInt(2+speed));
		setYpos(getYpos()-1+random.nextInt(2+speed));
		if(getXpos()<minX){
			setXpos(getXpos()+speed);
		}
		else if(getXpos()>maxX){
			setXpos(getXpos()-speed);
		}
		if(getYpos()<minY){
			setYpos(getYpos()+speed);
		}
		else if(getYpos()>maxY){
			setYpos(getYpos()-speed);
		}
		}
	}
	/**
	 * Chases an animal until it is at its same x,y; Then it kills it
	 * @see ecology.animals.Predator#chase(ecology.animals.Animal)
	 * @param a
	 */
	@Override
	public void chase(Animal a) {
		if(WorldItem.isDistanceBetweenTwoWorldItemsLessThanOrEqualTo(this, a, this.getEyesight())&&!a.getName().equals(this.getName())){
			this.setChasing(true);
		}
		else{
			this.setChasing(false);
		}
		if(isChasing()&&isAlive()&&a.isAlive){
			System.out.println(this.getName()+" is hunting " + a.getName());
			if(a.getXpos()>this.getXpos()){
				this.setXpos(this.getXpos()+1);	
			}
			else if(a.getXpos()<this.getXpos()){
				this.setXpos(this.getXpos()-1);
			}
			if(a.getYpos() > this.getYpos()){
				this.setYpos(this.getYpos()+1);
			}
			else if(a.getYpos() < this.getYpos()){
				this.setYpos(this.getYpos()-1);
			}
			if(this.getXpos()==a.getXpos()&&this.getYpos()==a.getYpos()){
				this.kill(a);
			}
		}
		else{
			this.move(0,0,World.xMax,World.yMax,a.getSpeed());
		}
		
	}
	/** 
	 * Kills the animal, setting Energy and Health to 0, as well as setting it dead
	 * @param a
	 */
	@Override
	public void kill(Animal a) {
		this.setEnergy(this.getEnergy()+a.getEnergy());
		System.out.println(this.getName()+" has killed " +a.getName());
		a.setEnergy(0);
		a.setHealth(0);
		a.setAlive(false);
		this.setChasing(false);
		
		
	}
	/**
	 * Follows a certain animal (this.follow(a)) for instance. 
	 * This method is for one animal (prey usually) to find another to mate, fight, etc...
	 * @param a
	 */
	@Override
	public void follow(Animal a) {
		if(WorldItem.isDistanceBetweenTwoWorldItemsLessThanOrEqualTo(this, a, this.getEyesight())){
			if(this.hasEnergyToReproduce()&&a.hasEnergyToReproduce()){
				if(isAlive()&&a.isAlive){
					if(!getName().equals(a.getName()) && !(a.getGender() == this.getGender())){
						System.out.println(this.getName()+" wants to reproduce with " + a.getName());
						if(a.getXpos()>this.getXpos()){
							this.setXpos(this.getXpos()+1);	
						}
						else if(a.getXpos()<this.getXpos()){
							this.setXpos(this.getXpos()-1);
						}
						if(a.getYpos() > this.getYpos()){
							this.setYpos(this.getYpos()+1);
						}
						else if(a.getYpos() < this.getYpos()){
							this.setYpos(this.getYpos()-1);
						}
						if(a.isAlive()&&a.hasEnergyToReproduce()&&this.isAlive&&this.hasEnergyToReproduce()){
							AnimalEvent.areTwoPreywOverLapping(this,a);
						}
					}
					else{
						this.move(0,0,World.xMax,World.yMax,a.getSpeed());
					}
				}
				else{
					this.move(0,0,World.xMax,World.yMax,a.getSpeed());
				}
		
			}
			else{
				this.move(0,0,World.xMax,World.yMax,a.getSpeed());
			}
		}
		else{
			this.move(0,0,World.xMax,World.yMax,a.getSpeed());
		}

	}
}
//this is the end!!!