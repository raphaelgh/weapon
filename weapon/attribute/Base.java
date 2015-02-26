package com.tw.trainning.fightergame.weapon.attribute;

import java.io.PrintStream;
import java.lang.reflect.Constructor;

import com.tw.trainning.fightergame.entity.Player;

public class Base implements Attribute{
	private final String name;     //not support rename
	protected boolean possible=false;
	private int specialAttackValue = 2;
	protected int times=0;
	protected int recordTimes;
	
	protected Base(String name, int times, boolean possible) {
		this.name = name;
		this.possible = possible;
		this.times = times;
		this.recordTimes = times;
	}
	
	protected Base(String name, int times, int specialAttackValue, boolean possible) {
		this.name = name;
		this.possible = possible;
		this.times = times;
		this.recordTimes = times;
		this.specialAttackValue = specialAttackValue;
	}
	
	@SuppressWarnings("unchecked")
	public Attribute accumulate(Attribute another){
		boolean possibility = this.getClass().equals(another.getClass());
		int times = another.accumulateTimes(possibility, this.times);
		int specialAttackValue = another.accumulateAttackValue(possibility, this.specialAttackValue);
		try {
			Constructor<Attribute> constructor = (Constructor<Attribute>) this.getClass().getDeclaredConstructor(String.class, int.class, int.class, boolean.class);
			return constructor.newInstance(this.name, times, specialAttackValue, true);
		} catch (Exception e) {
			return this;
		} 
	}
	
	public int affectBlood(String name, int blood, String status, PrintStream out) {
		specialAttackValue = reset(times);
		if(times == 0){
			return blood;
		}
		out.println(name+"�ܵ�"+specialAttackValue+"��"+this.getWeaponHarmness()+"�˺�,"+
				name+"ʣ������:"+(blood-specialAttackValue));
		times--;
		return blood-specialAttackValue;
	}
	
	private int reset(int times){
		return (times == 0 ? 0 : specialAttackValue);
	}
	
	public int accumulateTimes(boolean possible, int record){
		return (possible ? this.recordTimes+record : record);
	}
	
	public int accumulateAttackValue(boolean possible, int record){
		return (possible ? this.specialAttackValue+record : record);
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
