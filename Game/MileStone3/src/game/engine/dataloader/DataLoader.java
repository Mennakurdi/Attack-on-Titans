package game.engine.dataloader;

import game.engine.titans.TitanRegistry;
import game.engine.weapons.WeaponRegistry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class DataLoader {
private static final String TITANS_FILE_NAME = "titans.csv";
private static final String WEAPONS_FILE_NAME = "weapons.csv";

public static String getTitansFileName() {
	return TITANS_FILE_NAME;
}

public static String getWeaponsFileName() {
	return WEAPONS_FILE_NAME;
}

public static HashMap<Integer, TitanRegistry> readTitanRegistry() throws IOException{
	 HashMap<Integer, TitanRegistry> BufferedReader;
	 BufferedReader reader  = new BufferedReader(new FileReader("titans.csv"));
	 HashMap<Integer , TitanRegistry> Hm = new  HashMap<Integer , TitanRegistry>();
	 String s = "";
	 while ((s = reader.readLine()) !=null){
		 String [] array = s.split(",");
		int code  = Integer.parseInt(array[0]);
		int baseHealth = Integer.parseInt(array[1]);
		int baseDamage = Integer.parseInt(array[2]);
		int heightInMeters = Integer.parseInt(array[3]);
		int speed = Integer.parseInt(array[4]);
		int resourcesValue = Integer.parseInt(array[5]);
		int dangerLevel = Integer.parseInt(array[6]);
		TitanRegistry titan = new TitanRegistry (code, baseHealth, baseDamage,heightInMeters,speed,resourcesValue,dangerLevel);
		 Hm.put(code, titan);
	 }
return Hm;	 
}
public static HashMap<Integer, WeaponRegistry> readWeaponRegistry() throws IOException{
	HashMap<Integer, WeaponRegistry> BufferedReader;
	BufferedReader reader  = new BufferedReader(new FileReader("weapons.csv"));
	HashMap<Integer , WeaponRegistry> Hm = new  HashMap<Integer , WeaponRegistry>();
	String L = "";
	while ((L = reader.readLine()) != null){
		String [] arr = L.split(",");
		int code = Integer.parseInt(arr[0]);
		int price = Integer.parseInt(arr[1]);
		int damage = Integer.parseInt(arr[2]);
		
		
		if (arr.length == 6){
			int minRange = Integer.parseInt(arr[4]);
			int maxRange = Integer.parseInt(arr[5]);
			WeaponRegistry weapon = new WeaponRegistry(code , price,damage,arr[3],minRange,maxRange);
			Hm.put(code, weapon);
		}
		else { 
			WeaponRegistry weapon = new WeaponRegistry(code , price,damage,arr[3]);
			Hm.put(code, weapon);

		}

	}
	return Hm;
}

}
