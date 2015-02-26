package com.tw.trainning.fightergame.weapon.attribute;

import java.io.PrintStream;

import com.tw.trainning.fightergame.entity.Player;

public class Freeze extends Attribute{
	
	public static final String ATTRIBUTE = "冻僵了";
	public static final String HARMNESS = "冰冻";

	public Freeze(String name, int times, boolean possible) {
		super(name, times, possible);
	}
	
	public Freeze(String name, int times, int specialAttackValue, boolean possible){
		super(name, times, specialAttackValue, possible);
	}
	
	@Override
	protected String getWeaponAttribute(){
		return ATTRIBUTE;
	}
	
	@Override
	protected String getWeaponAttributeAgain(){
		return "又"+ATTRIBUTE;
	}
	
	@Override
	protected String getWeaponHarmness(){
		return HARMNESS;
	}
	
	@Override
	public int affectBlood(String name, int blood, String status, PrintStream out) {
		times = (times == 0 ? 0 : --times);
		return blood;
	}
	
	@Override
	public String affectPlayerStatus(String status) {
		if(times == 0 && status.indexOf("冻僵") != -1){
			return Player.NOTHING+",无法攻击";
		}
		if(times % 2 == 0 && status.indexOf("冻僵") != -1){ //freeze twice
			return status+",无法攻击";
		}
		return super.affectPlayerStatus(status);
	}
	
	@Override
	public void printStopAttackOnce(String attackName, String beAttackedName, PrintStream out) {
		out.println(attackName+"冻得直哆嗦,没有击中"+beAttackedName);
	}
}
