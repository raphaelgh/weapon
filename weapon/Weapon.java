package com.tw.trainning.fightergame.weapon;

import java.io.PrintStream;

import com.tw.trainning.fightergame.entity.Player;

public class Weapon{
	
	private int attackValue;
	private String name;
	
	public Weapon(String name, int attackValue) {
		this.attackValue = attackValue;
		this.name = name;
	}
	
	public String attack(Player player){
		player.beAffectedByWeapon(this);
		return "ÓÃ"+name;
	}
	
	public int value(){
		return attackValue;
	}
	
	@Override
	public String toString(){
		return "Ãû³Æ:"+name+",¹¥»÷Á¦:"+attackValue;
	}

	public int affect(String name, int blood, PrintStream out) {
		return blood;
	}
	
	public String affect(String name, String status) {
		return status;
	}
	
}
