package game.engine.titans;

public class ColossalTitan extends Titan {
	public static final int TITAN_CODE =4;
	public ColossalTitan(int baseHealth, int baseDamage, int heightInMeters,
			int distanceFromBase, int speed, int resourcesValue, int dangerLevel) {
		super(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed,
				resourcesValue, dangerLevel);
	}
	@Override
	public boolean move() {
		
		int distance = getDistance() - getSpeed();
		setDistance(distance);
		setSpeed (getSpeed() + 1);
		if (hasReachedTarget() == true){
			return true;
		}
		else 
			return false;
	}
		
		
		
	


	

	

}
