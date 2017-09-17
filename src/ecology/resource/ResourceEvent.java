package ecology.resource;



import ecology.animals.Animal;
import ecology.world.WorldItem;

public class ResourceEvent {
	//VARIABLE TO KEEP TRACK OF PLANTS: ANOTHER STATISTIC
	public static int numOfPlantsEaten = 0;
	/**
	 * Checks to see if animal is near a plant, if it is then the animal eats it. Boom
	 * Throws Interrupted cause of Thread.sleep()
	 * @param a
	 * @param r
	 * @throws InterruptedException
	 */
	public static void checkToSeeIfAnimalIsNearPlant(Animal a,Resource r) throws InterruptedException{
		if(r.isAlive()&&a.isAlive()){
			if(WorldItem.isDistanceBetweenTwoWorldItemsLessThanOrEqualTo(a, r, 0)){
				for(int i=0;i<r.getTypes().size();i++){
					if(a.getType().equalsIgnoreCase(r.getTypes().get(i))){
						a.setEnergy(a.getEnergy()+r.getGivenHealth());
						System.out.println(a.getName()+ " found and ate some " + r.getName()+" gaining "+r.getGivenHealth()+ " energy!");
						r.setAlive(false);
						numOfPlantsEaten++;
						Thread.sleep(500);
						break;
					}
				}
			}
		}
	}
}
