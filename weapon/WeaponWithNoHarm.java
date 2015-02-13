package com.tw.trainning.fightergame.weapon;

import java.io.PrintStream;

import com.tw.trainning.fightergame.entity.Player;

public class WeaponWithNoHarm extends Weapon{
	
	public WeaponWithNoHarm(String name, int value, int harm, PrintStream printer) {
		super(name, value, harm, printer);
	}

	@Override
	public void harm(Player player) {
	}

	@Override
	public String harmStatus(String name) {
		return "";
	}

	@Override
	public void print(Player player) {		
	}

}
