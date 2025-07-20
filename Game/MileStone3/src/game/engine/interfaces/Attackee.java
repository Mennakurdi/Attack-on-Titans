package game.engine.interfaces;

public interface Attackee {
  public  int getCurrentHealth();
  public void setCurrentHealth(int health);
  public int getResourcesValue();
  default boolean isDefeated(){
	  if (getCurrentHealth() <=0){
		  return true;
	  }
	  else 
		  return false;
  }
  default int takeDamage(int damage){
	  
	  setCurrentHealth(getCurrentHealth()-damage);
	  if (isDefeated() == true){
		  return  getResourcesValue();
	  }
	  else 
		  return 0;
  }
}
