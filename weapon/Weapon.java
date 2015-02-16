package com.tw.trainning.fightergame.weapon;

import java.io.PrintStream;
import java.util.Random;

import com.tw.trainning.fightergame.entity.Player;

public class Weapon{
	
	private final int attackValue; //not support upgrade
	private final String name;     //not support rename
	protected boolean possible=true;
	protected Random random;
	
	public Weapon(String name, int attackValue) {
		this.attackValue = attackValue;
		this.name = name;
	}
	
	public Weapon(String name, int attackValue, Random random) {
		this.attackValue = attackValue;
		this.name = name;
		this.random = random;
	}
	
	public String attack(Player player){
		this.possible = (random != null ? random.nextBoolean() : possible);
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

	public int affectBlood(String name, int blood, String status, PrintStream out) {
		return blood;
	}
	
	public String affectPlayerStatus(String status) {
		return status;
	}
	
	public String affectAttackStatus(String name, String status){
		return Player.NOTHING;
	}
	
}
