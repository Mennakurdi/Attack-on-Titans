package game.engine.titans;

public class PureTitan extends Titan {
	public static final int TITAN_CODE =1;
	public PureTitan(int baseHealth, int baseDamage, int heightInMeters,
			int distanceFromBase, int speed, int resourcesValue, int dangerLevel) {
		super(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed,
				resourcesValue, dangerLevel);
		
	}
	@Override
	public void setCurrentHealth(int health) {
		// TODO Auto-generated method stub
		super.setCurrentHealth(health);
	}
	@Override
	public int getCurrentHealth() {
		// TODO Auto-generated method stub
		return super.getCurrentHealth();
	}
	
	

}
