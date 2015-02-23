package com.tw.trainning.fightergame.weapon.attribute;

import java.io.PrintStream;

import com.tw.trainning.fightergame.entity.Player;

public class Base implements Attribute{
	private final String name;     //not support rename
	protected boolean possible=false;
	private int specialAttackValue;
	private final int recordAttackValue = 2;
	protected int times=0;
	protected int recordTimes;
	
	protected Base(String name, int times, boolean possible) {
		this.name = name;
		this.possible = possible;
		this.recordTimes = times;
	}
	
	public void accumulate(String status){
		times = accumulate(possible, status, times, recordTimes);
		specialAttackValue = accumulate(possible, status, specialAttackValue, recordAttackValue);
	}
	
	public int affectBlood(String name, int blood, String status, PrintStream out) {
		specialAttackValue = reset(times);
		if(times == 0){
			return blood;
		}
		out.println(name+"受到"+specialAttackValue+"点"+this.getWeaponHarmness()+"伤害,"+
				name+"剩余生命:"+(blood-specialAttackValue));
		times--;
		return blood-specialAttackValue;
	}
	
	private int reset(int times){
		return (times == 0 ? 0 : specialAttackValue);
	}
	
	protected int accumulate(boolean possible, String status, int current, int record){
		if(possible && 
				accumulateFlag(status)){
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
		if(accumulateFlag(status)){
			return this.getWeaponAttributeAgain();
		}
		return this.getWeaponAttribute();
	}
	
	private boolean accumulateFlag(String status){
		return (status.indexOf(this.getWeaponAttribute()) != -1 || status.indexOf(this.getWeaponAttributeAgain()) != -1);
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
	
    public void printStopAttackOnce(String attackName, String beAttackedName, PrintStream out) {
		
	}

	public String bust(String name) {
		return "";
	}
	
	public String toString(){
		return name;
	}

	@Override
	public void setPossible(boolean possible) {
		this.possible = possible;	
	}
}
