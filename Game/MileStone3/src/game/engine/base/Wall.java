package game.engine.base;

import game.engine.interfaces.Attackee;

public class Wall implements Attackee {
private final int baseHealth;
private int currentHealth;
public int getCurrentHealth() {
	return currentHealth;
}
public void setCurrentHealth(int currentHealth) {
	if (currentHealth < 0){
		 this.currentHealth = 0;
	}
	else {	
		this.currentHealth = currentHealth;
	}
}
public int getBaseHealth() {
	return baseHealth;
}
public Wall(int baseHealth) {
	super();
	this.baseHealth = baseHealth;
	this.currentHealth = baseHealth;
}
@Override
public int getResourcesValue() {
	// TODO Auto-generated method stub
	return -1;
}

}
