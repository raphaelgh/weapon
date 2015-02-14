package com.tw.trainning.fightergame.weapon;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WeaponRespository {

	private static List<Weapon> list = new ArrayList<Weapon>();
	
	static{
		list.add(new Weapon("����ľ��", 20));
		list.add(new Weapon("����", 50));
		list.add(new Weapon("��ì", 40));
		list.add(new Weapon("����", 30));
	}
	
	public static void listWeapon(PrintStream out){
		StringBuilder strings = new StringBuilder();
		Iterator<Weapon> iterator = list.iterator();
		while(iterator.hasNext()){
			strings.append(iterator.next().toString());
			strings.append("\r\n");
		}
		out.print(strings.toString());
	}
	
	public static Weapon fetchWeapon(int number){
		return list.get(number);
	}
	
//	public static void main(String[] args){
//		WeaponRespository.listWeapon(System.out);
//	}
}
