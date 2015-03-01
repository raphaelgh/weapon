package com.tw.trainning.fightergame.weapon.attribute;

import java.io.PrintStream;

public class Freeze extends Attribute{
	
	private static final String ATTRIBUTE = "冻僵了";

	public Freeze(String name, int times) {
		super(name, times);
	}
	
	public Freeze(String name, int times, int specialAttackValue){
		super(name, times, specialAttackValue);
	}
	
	public Freeze(int times, int specialAttackValue){
		super("寒冰", times, specialAttackValue);
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
	public int affectBlood(String name, int blood, String status, PrintStream out) {
		times = (times <= 0 ? 0 : --times);
		return blood;
	}
	
	@Override
	public String affectPlayerStatus(String status) {
		if(times == 0 && status.indexOf("冻僵") != -1){
			return ",无法攻击";
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
