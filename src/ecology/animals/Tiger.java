package ecology.animals;



public class Tiger extends Animal{
	/**
	 * Constructor for Tiger, almost identical to that of Animal
	 * @param name
	 * @param initX
	 * @param initY
	 * @param hunger
	 * @param health
	 * @param isAlive
	 */
	public Tiger(String name, int initX, int initY, int hunger, int health, boolean isAlive,char gender,int eyesight,int speed) {
		super(name, initX, initY, hunger, health, isAlive,false,Integer.MIN_VALUE,Integer.MAX_VALUE,gender,eyesight,speed);
	}
	/**
	 * Init for World Gen
	 */
	@Override
	public String getType(){
		return "Tiger";
	}
	@Override
	public String getPredType(){
		return "Predator";
	}
}
