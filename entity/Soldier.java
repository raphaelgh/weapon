package com.tw.trainning.fightergame.entity;

import java.io.PrintStream;

import com.tw.trainning.fightergame.weapon.Protect;
import com.tw.trainning.fightergame.weapon.Weapon;

public class Soldier extends Player{

	private Weapon weapon;
	private int protect;
	private final String role = "战士";
	
	public Soldier(String name, int attackValue, int blood, Weapon weapon, int protect) {
		super(name, attackValue, blood);
		this.weapon = weapon;
		this.protect = protect;
	}
	
	@Override
	protected String getRole(){
		return role;
	}
	
	@Override
	public void attack(Player playerB, PrintStream out) {
		playerB.beAttacked(attackValue+weapon.value(), getRole()+name+
				weapon.attack(playerB)+"攻击了"+playerB.getRole()+playerB.name+",", out);
	}
	
	@Override
	protected void beAttacked(int attackValue, String info, PrintStream out){
		blood = blood - (attackValue - protect);
		out.println(info+name+"受到"+(attackValue - protect)+"点伤害,"+
				name+"剩余生命:"+blood);
		status = blood <= 0 ? DEFEATED : status;
	}


}
