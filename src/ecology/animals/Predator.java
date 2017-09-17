package ecology.animals;

import java.util.ArrayList;

public interface Predator 
{
  public ArrayList<String> getPrey();

  public void chase(Animal a);

  public boolean isChasing();

  public void kill(Animal a);

  public void setChasing(boolean isChasing);
}

