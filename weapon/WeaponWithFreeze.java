package com.tw.trainning.fightergame.weapon;

import java.io.PrintStream;

import com.tw.trainning.fightergame.entity.Player;

public class WeaponWithFreeze extends Weapon{

	private int times = 0;
	
	public WeaponWithFreeze(String name, int value, int harmvalue,
			PrintStream out) {
		super(name, value, harmvalue, out);
	}

	@Override
	public String harmStatus(String name) {
		if(possibility == 1){
			return name + " is freezed. ";
		}
		return "";
	}
	
	@Override
	public void harm(Player player){
		times++;
		if(times == 3){
			player.stopAttackOnce();
			print(player);
			times = 0;
		}
	}

	@Override
	public void print(Player player) {
		String info = player.getName() + " is freezed and cannot attack.";
		printer.println(info);
	}

}
