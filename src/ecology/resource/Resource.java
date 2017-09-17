package ecology.resource;

import java.util.ArrayList;

import ecology.world.WorldItem;

public class Resource extends WorldItem{
	//AMOUNT OF HEALTH IT GIVES TO ANIMAL
	private int givenHealth;
	//TYPES OF ANIMALS IT FEEDS
	private ArrayList<String> types;
	//IS IT ALIVE OR NOT
	private boolean isAlive;
	//NAME
	private String name;
	/**
	 * Constructor for the Resource Class. Takes in the following params:
	 * @param name
	 * @param initX
	 * @param initY
	 * @param givenHealth
	 * @param types
	 * @param isAlive
	 */
	public Resource(String name,int initX, int initY, int givenHealth,ArrayList<String> types, boolean isAlive) {
		super(initX, initY);
		this.givenHealth = givenHealth;
		this.setTypes(types);
		this.isAlive = isAlive;
		this.name=name;
	}
	public Resource(){
	}
	/*
	 * GETTERS AND SETTERS
	 * getGivenHealth,setGivenHealth
	 * getTypes,setTypes
	 * isAlive,setAlive
	 * getName,setName
	 */
	public int getGivenHealth() {
		return givenHealth;
	}
	public void setGivenHealth(int givenHealth) {
		this.givenHealth = givenHealth;
	}
	public ArrayList<String> getTypes() {
		return types;
	}
	public void setTypes(ArrayList<String> types) {
		this.types = types;
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
//End of class