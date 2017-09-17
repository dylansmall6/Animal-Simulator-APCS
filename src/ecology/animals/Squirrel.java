package ecology.animals;

public class Squirrel extends Animal {
	/**
	 * CONSTRUCTOR FOR SQUIRREL
	 * @param name
	 * @param initX
	 * @param initY
	 * @param hunger
	 * @param health
	 * @param isAlive
	 * @param canReproduce
	 * @param start
	 * @param min
	 */
	public Squirrel(String name, int initX, int initY, int hunger, int health, boolean isAlive,boolean canReproduce, int start, int min,char gender,int eyesight,int speed) {
		super(name, initX, initY, hunger, health, isAlive,canReproduce,start,min,gender,eyesight,speed);
	}
	@Override
	public String getType(){
		return "Squirrel";
	}
	@Override
	public String getPredType(){
		return "Prey";
	}
}
