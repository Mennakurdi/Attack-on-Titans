package game.engine.weapons;

import game.engine.titans.Titan;


import java.util.PriorityQueue;

public class VolleySpreadCannon extends Weapon {

	private final int minRange;
	private final int maxRange;
	public static final int WEAPON_CODE = 3;

	public VolleySpreadCannon(int baseDamage, int minRange, int maxRange) {
		super(baseDamage);
		this.minRange = minRange;
		this.maxRange = maxRange;
	}

	public int getMinRange() {
		return minRange;
	}

	public int getMaxRange() {
		return maxRange;
	}

	@Override
	public int getDamage() {
		// TODO Auto-generated method stub
		return super.getDamage();
	}

	@Override
	public int turnAttack(PriorityQueue<Titan> laneTitans) {
		int gainedResources = 0;
		
		PriorityQueue<Titan> temporary = new PriorityQueue<>();
		while (!laneTitans.isEmpty()) {
			Titan t = laneTitans.poll();
			if (t.getDistance() >= getMinRange()&& t.getDistance() <= getMaxRange()) {
				gainedResources += super.attack(t);
				if (!t.isDefeated())
					temporary.add(t);

			}
			else
				temporary.add(t);

		}
		laneTitans.addAll(temporary);
		return gainedResources;
	}

}
