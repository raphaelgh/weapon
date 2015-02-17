package com.tw.trainning.fightergame.weapon;

import java.io.PrintStream;
import java.util.Random;

import com.tw.trainning.fightergame.entity.Player;

public class WeaponWithFreeze extends Weapon{
	
	public static final String ATTRIBUTE = "������";
	public static final String HARMNESS = "����";

	public WeaponWithFreeze(String name, int attackValue, int times,
			Random random) {
		super(name, attackValue, 2, random);
	}
	
	@Override
	protected String getWeaponAttribute(){
		return ATTRIBUTE;
	}
	
	@Override
	protected String getWeaponAttributeAgain(){
		return "��"+ATTRIBUTE;
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
		if(times == 0 && status.indexOf("����") != -1){
			return Player.NOTHING+",�޷�����";
		}
		if(times % 2 == 0 && status.indexOf("����") != -1){ //freeze twice
			return status+",�޷�����";
		}
		return super.affectPlayerStatus(status);
	}
	
	

}
