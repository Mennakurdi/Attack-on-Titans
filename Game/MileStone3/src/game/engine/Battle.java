package game.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import game.engine.lanes.Lane;
import game.engine.titans.Titan;
import game.engine.titans.TitanRegistry;
import game.engine.weapons.factory.FactoryResponse;
import game.engine.weapons.factory.WeaponFactory;
import game.engine.base.Wall;
import game.engine.dataloader.*;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;

public class Battle {
private final static int[][] PHASES_APPROACHING_TITANS={{1,1, 1, 2, 1, 3, 4},{ 2, 2, 2, 1, 3, 3, 4 },
{ 4, 4, 4, 4, 4, 4, 4}};
private final static int WALL_BASE_HEALTH=10000;
private int numberOfTurns;
private  int resourcesGathered;
private BattlePhase battlePhase = BattlePhase.EARLY;
private  int numberOfTitansPerTurn=1;
private int score;
private int titanSpawnDistance;
private final WeaponFactory weaponFactory;
private final HashMap<Integer, TitanRegistry> titansArchives;
private final  ArrayList<Titan> approachingTitans;
private final  PriorityQueue<Lane> lanes;
private final ArrayList<Lane> originalLanes;

public Battle(int numberOfTurns, int score, int titanSpawnDistance, int initialNumOfLanes,
		int initialResourcesPerLane) throws IOException{
	this.numberOfTurns = numberOfTurns;
	this.score = score;
	this.titanSpawnDistance = titanSpawnDistance;
	this.resourcesGathered = initialResourcesPerLane*initialNumOfLanes;
	this.originalLanes = new ArrayList<>();
	this.lanes= new PriorityQueue<>();
	this.approachingTitans = new ArrayList<>();
	this.titansArchives = DataLoader.readTitanRegistry();
	this.weaponFactory= new WeaponFactory();
	
	// call last thing in the constructor
	initializeLanes(initialNumOfLanes);

}

public int getNumberOfTurns() {
	return numberOfTurns;
}
public void setNumberOfTurns(int numberOfTurns) {
	if (this.numberOfTurns < 0){
		this.numberOfTurns =0;
	}
	else {
		this.numberOfTurns = numberOfTurns;
	}
}
public int getResourcesGathered() {
	return resourcesGathered;
}
public void setResourcesGathered(int resourcesGathered) {
	if (this.resourcesGathered < 0){
		this.resourcesGathered = 0;
	}
	else {
		this.resourcesGathered = resourcesGathered;
	}
}
public BattlePhase getBattlePhase() {
	return battlePhase;
}
public void setBattlePhase(BattlePhase battlePhase) {
	this.battlePhase = battlePhase;
}
public int getNumberOfTitansPerTurn() {
	return numberOfTitansPerTurn;
}
public void setNumberOfTitansPerTurn(int numberOfTitansPerTurn) {
if (numberOfTitansPerTurn <0){
	this.numberOfTitansPerTurn =0;
}
else {
	this.numberOfTitansPerTurn = numberOfTitansPerTurn;
}
}
public int getScore() {
	return score;
}
public void setScore(int score) {
	if (this.score < 0){
		this.score =0;
	}
	else {
		this.score = score;
	}
}
public int[][] getPHASES_APPROACHING_TITANS() {
	return PHASES_APPROACHING_TITANS;
}
public int getWALL_BASE_HEALTH() {
	return WALL_BASE_HEALTH;
}
public int getTitanSpawnDistance() {
	return titanSpawnDistance;
}
public void setTitanSpawnDistance(int titanSpawnDistance) {
	if (titanSpawnDistance <0){
		this.titanSpawnDistance=0;
	}
	this.titanSpawnDistance = titanSpawnDistance;
}
public WeaponFactory getWeaponFactory() {
	return weaponFactory;
}
public HashMap<Integer, TitanRegistry> getTitansArchives() {
	return titansArchives;
}
public ArrayList<Titan> getApproachingTitans() {
	return approachingTitans;
}
public PriorityQueue<Lane> getLanes() {
	return lanes;
}
public ArrayList<Lane> getOriginalLanes() {
	return originalLanes;
}

private void initializeLanes(int numOfLanes){
	for (int i=0; i< numOfLanes;i++){
		Wall w = new Wall ((WALL_BASE_HEALTH));
		Lane L = new Lane (w);
		originalLanes.add(L);
		lanes.add(L);
		
	}
}
public void refillApproachingTitans(){
	if(!approachingTitans.isEmpty()){
		return;
	}
	if (battlePhase == BattlePhase.EARLY){
		for(int i=0;i<7;i++)
			approachingTitans.add(titansArchives.get(PHASES_APPROACHING_TITANS[0][i]).spawnTitan(titanSpawnDistance));
	}
	else if (battlePhase == BattlePhase.INTENSE ){
		for(int i=0;i<7;i++)
			approachingTitans.add(titansArchives.get(PHASES_APPROACHING_TITANS[1][i]).spawnTitan(titanSpawnDistance));
	}
	else if (battlePhase == BattlePhase.GRUMBLING ){
		for(int i=0;i<7;i++)
			approachingTitans.add(titansArchives.get(PHASES_APPROACHING_TITANS[2][i]).spawnTitan(titanSpawnDistance));
	}
}
public void purchaseWeapon(int weaponCode, Lane lane) throws InsufficientResourcesException,InvalidLaneException{
	if (!lanes.contains(lane)){
		 throw new  InvalidLaneException();
	}
	 else {
		 if(!lane.isLaneLost()){
			 FactoryResponse fr = weaponFactory.buyWeapon(resourcesGathered, weaponCode);
			 lane.addWeapon(fr.getWeapon());
			 setResourcesGathered(fr.getRemainingResources());
		 }
	 }
	 performTurn();
}
public  void passTurn(){
	performTurn();
		
}
private void addTurnTitansToLane(){
	if(this.approachingTitans.isEmpty())
		this.refillApproachingTitans();
	
	for(Lane l:lanes) {
		if(!l.isLaneLost()) {
		for (int i =0 ; i< numberOfTitansPerTurn;i++){
		if (approachingTitans.isEmpty())
			refillApproachingTitans();
		l.addTitan(approachingTitans.remove(0));
		}
		return;
	}
		
	}
}
private void moveTitans(){
	PriorityQueue<Lane> temporary = new PriorityQueue<>();
	while (!lanes.isEmpty()){
		Lane lane = lanes.poll();
		lane.moveLaneTitans();
		temporary.add(lane);
	}
	lanes.addAll(temporary);
	}
private int performWeaponsAttacks(){ 
	int accumulatedRescources =0;
	PriorityQueue<Lane> temporary = new PriorityQueue<>();
	while (!lanes.isEmpty()){
		Lane lane= lanes.poll();
		if(!lane.isLaneLost()){
			accumulatedRescources += lane.performLaneWeaponsAttacks();	
		}
		temporary.add(lane);
	}
	lanes.addAll(temporary);
	resourcesGathered += accumulatedRescources;
	score+=accumulatedRescources;

	 return accumulatedRescources;
}
private int performTitansAttacks(){
	PriorityQueue<Lane> temporary = new PriorityQueue<>();
	int total =0;
	while (!lanes.isEmpty()){
		Lane lane = lanes.poll();
		if (!lane.isLaneLost()){
			total +=lane.performLaneTitansAttacks();
			if(!lane.isLaneLost()){
			temporary.add(lane);
			}
		}
	}
	lanes.addAll(temporary);
	return total;

}
private void updateLanesDangerLevels(){
	PriorityQueue<Lane> temporary = new PriorityQueue<>();
	while (!lanes.isEmpty()){
		 Lane lane = lanes.poll();
		 lane.updateLaneDangerLevel();
		 temporary.add(lane);
	}
	lanes.addAll(temporary);
}
private void finalizeTurns(){
	if(this.isGameOver()==false){
	numberOfTurns++;
	if (numberOfTurns < 15){
		battlePhase = BattlePhase.EARLY;
	}
	else if (numberOfTurns >= 15 &&numberOfTurns < 30){
		battlePhase = BattlePhase.INTENSE;
	}
	else{
		battlePhase = BattlePhase.GRUMBLING;
	
	if (numberOfTurns >30 && numberOfTurns%5==0){
		battlePhase = BattlePhase.GRUMBLING;
		numberOfTitansPerTurn = numberOfTitansPerTurn*2;
	}
	}
	}
}
private void performTurn(){
	moveTitans();
	performWeaponsAttacks();
	performTitansAttacks();
	updateLanesDangerLevels();
	addTurnTitansToLane();
	updateLanesDangerLevels();
	finalizeTurns();
}
public boolean isGameOver(){
	if(lanes.isEmpty()){
		return true;
	}
return false;
}










}
