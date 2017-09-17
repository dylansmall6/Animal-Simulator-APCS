package ecology.resource;

import java.util.ArrayList;

public class Nuts extends Resource{
	//types it can feed
	public static ArrayList<String> types = new ArrayList<String>();
	/**
	 * Constructor for Nuts class, basically a world item that feeds things
	 * @param initX
	 * @param initY
	 */
	public Nuts(int initX, int initY) {
		super("Nuts", initX, initY, 5, types, true);
		types.add("Squirrel");
	}
	
}
