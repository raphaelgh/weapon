package com.tw.trainning.fightergame.weapon;

import java.io.PrintStream;

import com.tw.trainning.fightergame.entity.Player;

public class WeaponWithPoison extends Weapon{
	
	public WeaponWithPoison(String name, int value, int harmvalue, PrintStream out) {
		super(name, value, harmvalue, out);
	}
	
	@Override
	public void print(Player player){
		String info = player.getName() + " " + "is poisoned and lost " + harmValue + " blood. " +
				player.getName() + " left " + player.getBlood() + " blood.";
		printer.println(info);
	}
	
	@Override
	public String toString(){
		return "poison";
	}

	@Override
	public String harmStatus(String name) {
		return name + " is poisoned. ";
	}

}
