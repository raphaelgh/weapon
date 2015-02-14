package com.tw.trainning.fightergame.entity;

import java.io.PrintStream;
import java.util.Random;

import com.tw.trainning.fightergame.weapon.Weapon;

public class Player {
	
	public static final String DEFEATED = "被打败了";

	protected String name;
	protected int attackValue;
	protected int blood;
	protected String status;
	private final String role = "普通人";
	
	public Player(String name, int attackValue, int blood) {
		this.name = name;
		this.attackValue = attackValue;
		this.blood = blood;
	}

	public boolean canBeAttack() {
		return blood > 0;
	}
	
	protected String getRole(){
		return role;
	}

	public void attack(Player playerB, PrintStream out) {
		playerB.beAttacked(attackValue, getRole()+name+"攻击了"+playerB.getRole()+playerB.name+",", out);
	}
	
	protected void beAttacked(int attackValue, String info, PrintStream out){
		blood = blood - attackValue;
		out.println(info+name+"受到"+attackValue+"点伤害,"+
				name+"剩余生命:"+blood);
		status = blood <= 0 ? DEFEATED : status;
	}

	public void outputStatus(PrintStream out) {
		out.println(name + status);
	}

	
}
