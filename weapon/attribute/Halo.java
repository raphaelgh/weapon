package com.tw.trainning.fightergame.weapon.attribute;

import java.io.PrintStream;

public class Halo extends Attribute{
	
	public Halo(String name, int times) {
		super(name, times);
	}
	
	public Halo(String name, int times, int specialAttackValue) {
		super(name, times, specialAttackValue);
	}
	
	public Halo(int times, int specialAttackValue) {
		super("ѣ��", times, specialAttackValue);
	}

	public static final String ATTRIBUTE = "�ε���";
	
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
		times = (times == 0 ? 0 : --times);
		return blood;
	}
	
	@Override
	public String affectPlayerStatus(String status) {
		if(!possible && times == 0 && status.indexOf("�ε�") != -1){
			return "";
		}
		if(!possible && status.indexOf("�ε�") == -1){
			return status;
		}
		return super.affectPlayerStatus(status)+",�޷�����";
	}
	
	@Override
    public void printStopAttackOnce(String attackName, String beAttackedName, PrintStream out) {
		out.println(attackName + "�ε���,�޷�����,ѣ�λ�ʣ:" + times + "��");
	}
}
