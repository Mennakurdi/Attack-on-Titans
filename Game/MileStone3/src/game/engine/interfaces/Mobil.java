package game.engine.interfaces;
public interface Mobil{
	public int getDistance();
	public void setDistance(int distance);
	public int getSpeed();
	public void setSpeed(int speed);
	default boolean hasReachedTarget(){
		if (getDistance() <= 0){
			return true;
		}
		else 
			return false;	
}
	default boolean move(){
		int distance = getDistance() - getSpeed();
		setDistance(distance);
		if (hasReachedTarget() == true){
			return true;
		}
		else 
			return false;
	}

	
}
