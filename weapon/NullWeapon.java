package com.tw.trainning.fightergame.weapon;

import java.io.PrintStream;

import com.tw.trainning.fightergame.entity.Player;

public class NullWeapon extends Weapon{

	public NullWeapon(String name, int value, int harm, PrintStream out) {
		super(name, value, harm, out);
	}

	@Override
	public String harmStatus(String name) {
		return "";
	}
	
	@Override
	public String use() {
		return "";
	}

	@Override
	public void print(Player player) {
	}
	
	@Override
	public void harm(Player player) {
	}

}
