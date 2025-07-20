package game.engine.lanes;

import java.util.*;

import game.engine.base.Wall;
import game.engine.titans.Titan;
import game.engine.weapons.Weapon;

public class Lane implements Comparable<Lane> {
private final  Wall laneWall;
private int dangerLevel;
private final PriorityQueue<Titan> titans ;
private final ArrayList<Weapon> weapons;

public int getDangerLevel() {
	return dangerLevel;
}
public void setDangerLevel(int dangerLevel) {
	if (dangerLevel <=0){
		this.dangerLevel = 0;
	}
	else {
		this.dangerLevel = dangerLevel;
	}
}
public Wall getLaneWall() {
	return laneWall;
}
public PriorityQueue<Titan> getTitans() {
	return titans;
}
public ArrayList<Weapon> getWeapons() {
	return weapons;
}
public Lane(Wall laneWall){
	this.laneWall = laneWall;
	 this.dangerLevel =0;
	 this.titans = new PriorityQueue<>();
	 this.weapons =new ArrayList<>();
	
}
public Lane(Wall laneWall, int dangerLevel, PriorityQueue<Titan> titans,
		ArrayList<Weapon> weapons) {
	super();
	this.laneWall = laneWall;
	this.dangerLevel = dangerLevel;
	this.titans = titans;
	this.weapons = weapons;
}


public void addTitan(Titan titan){
	titans.add(titan);
}
public void addWeapon(Weapon weapon){
	weapons.add(weapon);
}
public void moveLaneTitans(){
	PriorityQueue <Titan> temporary = new PriorityQueue<>();
	while(!titans.isEmpty()){
		Titan t= titans.poll();
		t.move();
		temporary.add(t);
	}
	titans.addAll(temporary);
}

public  int performLaneTitansAttacks(){
	int resourcesGathered = 0;
	PriorityQueue <Titan> temporary = new PriorityQueue<>();
	while(!titans.isEmpty()){
		Titan t = titans.poll();
		if (t.hasReachedTarget()==true)
			resourcesGathered+=t.attack(laneWall);
			temporary.add(t);

}
	titans.addAll(temporary);
	return resourcesGathered;
}

public int performLaneWeaponsAttacks(){
	int resourcesGathered =0;
	for (Weapon weapon : weapons){
			resourcesGathered+=weapon.turnAttack(titans);
			}
	return resourcesGathered;
}
public  boolean isLaneLost(){
	if (laneWall.isDefeated()){
		return true;
	}
	else 
		return false;
}
public void updateLaneDangerLevel(){
	int DangerLevel =0;
	PriorityQueue<Titan> temporary = new PriorityQueue<>();
	while(!titans.isEmpty()){
		Titan titan = titans.poll();
		DangerLevel += titan.getDangerLevel();
		temporary.add(titan);
	}
	setDangerLevel(DangerLevel);
	titans.addAll(temporary);
}
@Override
public int compareTo(Lane o) {
	
	return this.dangerLevel - o.dangerLevel;
}
}
