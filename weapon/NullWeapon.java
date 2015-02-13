package com.tw.trainning.fightergame.weapon;

import java.util.Random;

import com.tw.trainning.fightergame.entity.Player;

public class NullWeapon extends Weapon{

	public NullWeapon() {
		super("", 0, 0, null);
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
