package ecology.world;

public class WorldItem {
	//Everything must have a position on the plane
	private int xpos;
	private int ypos;
	/**
	 * Constructor, just takes in an X and Y
	 * @param initX
	 * @param initY
	 */
	public WorldItem(int initX,int initY){
		setXpos(initX);
		setYpos(initY);
	}
	/**
	 * Just to find distance and such
	 */
	public WorldItem(){
		
	}
	/*
	 * GETTERS AND SETTERS
	 * getYpos,setYpos
	 * getXpos,setXpos
	 */
	public int getYpos() {
		return ypos;
	}
	public void setYpos(int ypos) {
		this.ypos = ypos;
	}
	public int getXpos() {
		return xpos;
	}
	public void setXpos(int xpos) {
		this.xpos = xpos;
	}
	/**
	 * returns the distance between two items
	 * @param a
	 * @param b
	 * @return
	 */
	public static double getDistanceBetweenTwoWorldItems(WorldItem a,WorldItem b){
		return Math.sqrt(Math.pow(a.getXpos()-b.getXpos(), 2)+Math.pow(a.getYpos()-b.getYpos(), 2));
	}
	/**
	 * returns true if the distance is <= int c (a number inputted by user)
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	public static boolean isDistanceBetweenTwoWorldItemsLessThanOrEqualTo(WorldItem a,WorldItem b,int c){
		return getDistanceBetweenTwoWorldItems(a, b) <= c;
	}
}
