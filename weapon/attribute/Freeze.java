package com.tw.trainning.fightergame.weapon.attribute;

import java.io.PrintStream;

public class Freeze extends Attribute{
	
	private static final String ATTRIBUTE = "������";

	public Freeze(String name, int times) {
		super(name, times);
	}
	
	public Freeze(String name, int times, int specialAttackValue){
		super(name, times, specialAttackValue);
	}
	
	public Freeze(int times, int specialAttackValue){
		super("����", times, specialAttackValue);
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
	public int affectBlood(String name, int blood, String status, PrintStream out) {
		times = (times <= 0 ? 0 : --times);
		return blood;
	}
	
	@Override
	public String affectPlayerStatus(String status) {
		if(times == 0 && status.indexOf("����") != -1){
			return ",�޷�����";
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
