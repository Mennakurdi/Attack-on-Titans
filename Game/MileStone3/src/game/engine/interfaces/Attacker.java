package game.engine.interfaces;

public interface Attacker {
	public int getDamage();
	
	 default int attack(Attackee target){
		 if (target.takeDamage(getDamage())!=0){
			 if (target.isDefeated()== true){
				 return target.getResourcesValue();
			 }
		 }
      return 0;
	 }
}
