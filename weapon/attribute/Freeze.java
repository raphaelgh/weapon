package com.tw.trainning.fightergame.weapon.attribute;

import java.io.PrintStream;

import com.tw.trainning.fightergame.entity.Player;

public class Freeze extends Attribute{
	
	public static final String ATTRIBUTE = "������";
	public static final String HARMNESS = "����";

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
		return "��"+ATTRIBUTE;
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
		if(times == 0 && status.indexOf("����") != -1){
			return Player.NOTHING+",�޷�����";
		}
		if(times % 2 == 0 && status.indexOf("����") != -1){ //freeze twice
			return status+",�޷�����";
		}
		return super.affectPlayerStatus(status);
	}
	
	@Override
	public void printStopAttackOnce(String attackName, String beAttackedName, PrintStream out) {
		out.println(attackName+"����ֱ����,û�л���"+beAttackedName);
	}
}
