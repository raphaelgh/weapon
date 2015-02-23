package com.tw.trainning.fightergame.weapon;

import java.io.PrintStream;
import java.util.Random;

import com.tw.trainning.fightergame.entity.Player;
import com.tw.trainning.fightergame.weapon.attribute.Attribute;

public class RefactorWeapon extends Weapon{
	protected final int attackValue; //not support upgrade
	private final String name;     //not support rename
	protected boolean possible=false;
	protected Random random;
	private Attribute extraHarm;
	
	public RefactorWeapon(String name, int attackValue) {
		super(name, attackValue);
		this.attackValue = attackValue;
		this.name = name;
	}
	
	public RefactorWeapon(String name, int attackValue, Attribute attr, Random random) {
		super(name, attackValue, 0, random);
		this.attackValue = attackValue;
		this.name = name;
		this.random = random;
		this.extraHarm = attr;
	}
	
	public int attack(Player player){
		this.possible = (random != null ? random.nextBoolean() : possible);
		extraHarm.setPossible(possible);
		String status = player.beAffectedByWeapon(this);
		extraHarm.accumulate(status);
		return attackValue;
	}
	
	public String name(){
		return "ÓÃ"+name;
	}
	
	@Override
	public String toString(){
		return "Ãû³Æ:"+name+",¹¥»÷Á¦:"+attackValue;
	}
	
	
	public int affectBlood(String name, int blood, String status, PrintStream out) {
		return extraHarm.affectBlood(name, blood, status, out);
	}
	
	public String affectPlayerStatus(String status) {
		return extraHarm.affectPlayerStatus(status);
	}
	
	
	public String affectAttackStatus(String name, String status){
		return extraHarm.affectAttackStatus(name, status);
	}

	public void printStopAttackOnce(String attackName, String beAttackedName, PrintStream out) {
		extraHarm.printStopAttackOnce(attackName, beAttackedName, out);
	}

	public String bust(String name) {
		return extraHarm.bust(name);
	}
}
