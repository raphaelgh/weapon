package com.tw.trainning.fightergame.entity;

import java.io.PrintStream;
import java.util.Random;

import com.tw.trainning.fightergame.weapon.NullWeapon;
import com.tw.trainning.fightergame.weapon.Protect;
import com.tw.trainning.fightergame.weapon.Weapon;

public class Player {
	
	public static final String DEFEATED = "±»´ò°ÜÁË";

	private String name;
	private int attackValue;
	private int blood;
	private String status;
	
	public Player(String name, int attackValue, int blood) {
		this.name = name;
		this.attackValue = attackValue;
		this.blood = blood;
	}

	public boolean canBeAttack() {
		return blood > 0;
	}

	public void attack(Player playerB) {
		playerB.beAttacked(attackValue);
	}
	
	private void beAttacked(int attackValue){
		blood = blood - attackValue;
		status = blood <= 0 ? DEFEATED : status;
	}

	public void outputStatus(PrintStream out) {
		out.println(name + status);
	}

	
}
