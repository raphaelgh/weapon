package com.tw.trainning.fightergame.weapon;

import java.io.PrintStream;
import java.util.Random;

public class WeaponWithFreeze extends Weapon{
	
	public static final String ATTRIBUTE = "¶³½©ÁË";
	public static final String HARMNESS = "±ù¶³";

	public WeaponWithFreeze(String name, int attackValue, int times,
			Random random) {
		super(name, attackValue, times, random);
	}
	
	@Override
	protected String getWeaponAttribute(){
		return ATTRIBUTE;
	}
	
	@Override
	protected String getWeaponAttributeAgain(){
		return "ÓÖ"+ATTRIBUTE;
	}
	
	@Override
	protected String getWeaponHarmness(){
		return HARMNESS;
	}
	
	@Override
	public int affectBlood(String name, int blood, String status, PrintStream out) {
		if(!possible && times == 0){
			return blood;
		}
		times--;
		times = accumulate(possible, status, times, recordTimes);
		return blood;
	}
	
	@Override
	public String affectPlayerStatus(String status) {
		if(times % 2 == 0 && status.indexOf("¶³½©") != -1){ //freeze twice
			return status+",ÎÞ·¨¹¥»÷";
		}
		return super.affectPlayerStatus(status);
	}
	
	

}
