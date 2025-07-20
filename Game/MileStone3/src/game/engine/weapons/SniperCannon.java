package game.engine.weapons;

import game.engine.titans.Titan;

import java.util.PriorityQueue;

public class SniperCannon extends Weapon {
	public static final int WEAPON_CODE =2;
	public SniperCannon(int baseDamage) {
		super(baseDamage);
		
	}
	
	public int getDamage() {
		// TODO Auto-generated method stub
		return super.getDamage();
	}

	@Override
	public int turnAttack(PriorityQueue<Titan> laneTitans) {
		int resourcesGathered =0;
		Titan t = laneTitans.peek();
		if (!laneTitans.isEmpty()){
			resourcesGathered+=super.attack(t);
		if (resourcesGathered!=0){
			laneTitans.poll();
			return resourcesGathered;
		}
	}
		return 0;
		
				
	}
	

}
