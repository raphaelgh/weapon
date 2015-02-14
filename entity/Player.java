package com.tw.trainning.fightergame.entity;

import java.io.PrintStream;
import java.util.Random;

import com.tw.trainning.fightergame.weapon.NullWeapon;
import com.tw.trainning.fightergame.weapon.Protect;
import com.tw.trainning.fightergame.weapon.Weapon;

public class Player {
	
	public static final String DEFEATED = "被打败了";

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

	public void attack(Player playerB, PrintStream out) {
		playerB.beAttacked(attackValue, name+"攻击了"+playerB.name+",", out);
	}
	
	private void beAttacked(int attackValue, String info, PrintStream out){
		blood = blood - attackValue;
		out.println(info+name+"受到"+attackValue+"点伤害,"+
				name+"剩余生命:"+blood);
		status = blood <= 0 ? DEFEATED : status;
	}

	public void outputStatus(PrintStream out) {
		out.println(name + status);
	}

	
}
