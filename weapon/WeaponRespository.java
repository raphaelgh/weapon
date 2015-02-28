package com.tw.trainning.fightergame.weapon;

import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.tw.trainning.fightergame.weapon.attribute.Attribute;
import com.tw.trainning.fightergame.weapon.attribute.Bust;
import com.tw.trainning.fightergame.weapon.attribute.Fire;
import com.tw.trainning.fightergame.weapon.attribute.Freeze;
import com.tw.trainning.fightergame.weapon.attribute.Halo;
import com.tw.trainning.fightergame.weapon.attribute.NULLAttribute;
import com.tw.trainning.fightergame.weapon.attribute.Poison;

public class WeaponRespository {

	private List<Weapon> list = new ArrayList<Weapon>();
	private Random random;
	private static final int WEAPON_COUNTS = 20;
	private static final int WEAPON_ATTACK_VALUE = 20;
	private static final int ATTRIBUTE_COUNTS = 3;
	private static final int ATTRIBUTE_TIMES = 5;
	private static final int ATTRIBUTE_ATTACK_VALUE = 3;
	
	private String[] weaponName = {"¹÷", "°ô", "µ¶", "²æ", "êª", "½£", "Ç¹", "½£", "Ã¬"};
	private Class<?>[] attrClass = {Fire.class, Freeze.class, Halo.class, Poison.class, Bust.class};
	
	public WeaponRespository(Random random){
		this.random = random;
		for(int i = 0; i < random.nextInt(WEAPON_COUNTS); i++){
			list.add(generatorWeapon(this.random));
		}
	}
	
	private Weapon generatorWeapon(Random random){
		int index = random.nextInt(weaponName.length);
		String name = weaponName[index];			
		int weaponAttackValue = random.nextInt(WEAPON_ATTACK_VALUE) + 1;	
		Weapon weapon = generatorNoAttributeWeapon(name, weaponAttackValue);
		return addWeaponAttribute(weapon);
	}
	
	private Weapon addWeaponAttribute(Weapon weapon){
		if(NULLWeapon.class.equals(weapon.getClass())){
			return weapon;
		}
		int index;
		String seperator = "@";
		for(int i = 0; i < random.nextInt(ATTRIBUTE_COUNTS); i++){
			index = random.nextInt(attrClass.length);
			Attribute attr = generatorAttribute(attrClass[index], random);
			if(NULLAttribute.class.equals(attr.getClass())){	
				break;
			}
			weapon.addAttribute(attr);
			seperator = (i == 0 ? seperator : "#");
			weapon.addPrefixToName(attr.nameToString()+seperator);
		}
		return weapon;
	}
	
	private Weapon generatorNoAttributeWeapon(String name, int weaponAttackValue){
		try {
			Constructor<Weapon> constructor = (Constructor<Weapon>) Weapon.class.getDeclaredConstructor(String.class, int.class);
			return constructor.newInstance(name, weaponAttackValue);
		} catch (Exception e) {
			return NULLWeapon.getInstance();
		}
	}
	
	@SuppressWarnings("unchecked")
	private Attribute generatorAttribute(Class<?> attr, Random random){
		int times = random.nextInt(ATTRIBUTE_TIMES) + 1;
		int specialAttackValue = random.nextInt(ATTRIBUTE_ATTACK_VALUE) + 1;
		try {
			Constructor<Attribute> constructor = (Constructor<Attribute>) attr.getDeclaredConstructor(int.class, int.class);
			return constructor.newInstance(times, specialAttackValue);
		} catch (Exception e) {
			return NULLAttribute.getInstance();
		} 
	}
	
	public void listWeapon(PrintStream out){
		StringBuilder strings = new StringBuilder();
		Iterator<Weapon> iterator = list.iterator();
		while(iterator.hasNext()){
			strings.append(iterator.next().toString());
			strings.append("\r\n");
		}
		out.print(strings.toString());
	}
	
	public Weapon fetchWeapon(int number){
		return list.get(number);
	}
	
	public int size(){
		return this.list.size();
	}
}
