package com.tw.trainning.fightergame.weapon;

import java.io.PrintStream;

import com.tw.trainning.fightergame.entity.Player;


public class WeaponWithPoison extends Weapon {

	private int poisonAttackValue = 2;
	private int times = 1;
	public WeaponWithPoison(String name, int attackValue, int times) {
		super(name, attackValue);
		this.times = times;
	}
	
	@Override
	public int affect(String name, int blood, PrintStream out) {
		out.println(name+"受到"+poisonAttackValue+"点毒性伤害,"+
				name+"剩余生命:"+(blood-poisonAttackValue));
		return blood-poisonAttackValue;
	}
	
	@Override
	public String affect(String name, String status) {
		return name+Player.POISONED+",";
	}

}
