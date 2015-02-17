package com.tw.trainning.fightergame.weapon;

import java.io.PrintStream;
import java.util.Random;

import com.tw.trainning.fightergame.entity.Player;

public class Weapon{
	
	private final int attackValue; //not support upgrade
	private final String name;     //not support rename
	protected boolean possible=false;
	private Random random;
	private int specialAttackValue;
	private final int recordAttackValue = 2;
	protected int times=0;
	protected int recordTimes;
	
	public Weapon(String name, int attackValue) {
		this.attackValue = attackValue;
		this.name = name;
	}
	
	protected Weapon(String name, int attackValue, int times, Random random) {
		this.attackValue = attackValue;
		this.name = name;
		this.random = random;
		this.recordTimes = times;
	}
	
	public String attack(Player player){
		this.possible = (random != null ? random.nextBoolean() : possible);
		player.beAffectedByWeapon(this);
		return "��"+name;
	}
	
	public int value(){
		return attackValue;
	}
	
	@Override
	public String toString(){
		return "����:"+name+",������:"+attackValue;
	}
	
	
	public int affectBlood(String name, int blood, String status, PrintStream out) {
		specialAttackValue = reset(times);
		if(!possible && times == 0){
			return blood;
		}
		times = accumulate(possible, status, times, recordTimes);
		specialAttackValue = accumulate(possible, status, specialAttackValue, recordAttackValue);
		out.println(name+"�ܵ�"+specialAttackValue+"��"+this.getWeaponHarmness()+"�˺�,"+
				name+"ʣ������:"+(blood-specialAttackValue));
		times--;
		return blood-specialAttackValue;
	}
	
	private int reset(int times){
		return (times == 0 ? 0 : specialAttackValue);
	}
	
	protected int accumulate(boolean possible, String status, int current, int record){
		if(possible && 
				(this.getWeaponAttribute().equals(status) || 
						this.getWeaponAttributeAgain().equals(status))){
			return current+record;
		}
		return current;
	}
	
	public String affectPlayerStatus(String status) {
		if(!possible){
			return status;
		}
		return accumulateStatus(possible, status);
	}
	
	protected String accumulateStatus(boolean possible, String status){
		if(this.getWeaponAttribute().equals(status) || this.getWeaponAttributeAgain().equals(status)){
			return this.getWeaponAttributeAgain();
		}
		return this.getWeaponAttribute();
	}
	
	public String affectAttackStatus(String name, String status){
		if(!possible){
			return Player.NOTHING;
		}
		return name+accumulateStatus(possible, status)+",";
	}
	
	protected String getWeaponAttribute(){
		return Player.NOTHING;
	}
	
	protected String getWeaponAttributeAgain(){
		return Player.NOTHING;
	}
	
	protected String getWeaponHarmness(){
		return "";
	}
	
}
