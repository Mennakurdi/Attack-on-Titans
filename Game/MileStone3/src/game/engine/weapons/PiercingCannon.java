package game.engine.weapons;

import game.engine.titans.Titan;

import java.util.PriorityQueue;

public class PiercingCannon extends  Weapon {
	public static final int WEAPON_CODE =1;
	public PiercingCannon(int baseDamage) {
		super(baseDamage);
		
	}
	
	public int getDamage() {
		return super.getDamage();
	}

	@Override
public int turnAttack(PriorityQueue<Titan> laneTitans) {
		int resourcesGathered =0;
		PriorityQueue<Titan> notDead = new PriorityQueue<>();
		for (int i=0;i<5 && !laneTitans.isEmpty();i++){
			Titan t = laneTitans.poll();
			resourcesGathered+= super.attack(t);
			if (!t.isDefeated()){
				notDead.add(t);
			}
		}
		laneTitans.addAll(notDead);
		return resourcesGathered;
	}

	


}
