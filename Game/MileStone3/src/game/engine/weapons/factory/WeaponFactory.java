package game.engine.weapons.factory;


import game.engine.dataloader.DataLoader;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.weapons.Weapon;
import game.engine.weapons.WeaponRegistry;

import java.io.IOException;
import java.util.HashMap;

public class WeaponFactory {

private final HashMap<Integer, WeaponRegistry> weaponShop;

public HashMap<Integer, WeaponRegistry> getWeaponShop() {
	return weaponShop;
}

public WeaponFactory() throws IOException{
	this.weaponShop = DataLoader.readWeaponRegistry();
}
public FactoryResponse buyWeapon(int resources, int weaponCode) throws InsufficientResourcesException{
	WeaponRegistry weaponRegistry= weaponShop.get(weaponCode);
	if (weaponRegistry!= null && resources< weaponRegistry.getPrice()){
		throw new InsufficientResourcesException(resources);
	}
	else{
		Weapon w = weaponRegistry.buildWeapon();
		int remainingResources = resources-weaponRegistry.getPrice();
	
		 FactoryResponse factoryResponse = new FactoryResponse(w,remainingResources);
		
		return factoryResponse;
	}
}
public void addWeaponToShop(int code, int price){
	WeaponRegistry weaponRegistry = new WeaponRegistry(code, price);
	weaponShop.put(code, weaponRegistry);
}
 public  void addWeaponToShop(int code, int price, int damage, String name){
	WeaponRegistry weaponRegistry = new WeaponRegistry(code, price,damage,name);
	weaponShop.put(code, weaponRegistry);
 }
 
 public  void addWeaponToShop(int code, int price, int damage, String name, int minRange, int maxRange){
	 WeaponRegistry weaponRegistry = new WeaponRegistry(code, price,damage,name,minRange,maxRange);
	 weaponShop.put(code, weaponRegistry);
 }
}

