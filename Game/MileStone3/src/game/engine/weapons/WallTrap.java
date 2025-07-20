package game.engine.weapons;

import game.engine.titans.Titan;

import java.util.PriorityQueue;

public class WallTrap extends Weapon {
	public static final int WEAPON_CODE =4;
public WallTrap(int baseDamage){
	super(baseDamage);
}
@Override
public int getDamage() {
	// TODO Auto-generated method stub
	return super.getDamage();
}
@Override
public int turnAttack(PriorityQueue<Titan> laneTitans) {
	int resourcesGahtered =0;
	Titan t = laneTitans.peek();
	if (!laneTitans.isEmpty()){
	if (t.hasReachedTarget()== true){
		resourcesGahtered+=super.attack(t);
	if (resourcesGahtered!=0){
		laneTitans.poll();
		return resourcesGahtered;
	}
	}
	}
	return 0;
	
	
}

}
