package com.tw.trainning.fightergame.weapon.attribute;

import java.io.PrintStream;
import java.lang.reflect.Constructor;

public class Attribute {
	private final String name;   
	protected boolean possible;
	private int specialAttackValue;
	protected int times;
	protected int recordTimes;
	private static final String SEPERATOR = ",";
	protected double rate;
	
	protected Attribute(String name, int times) {
		this.name = name;
		this.times = times;
		this.recordTimes = times;
		this.specialAttackValue = 2;
		this.possible=false;
		this.rate = 0.5;
	}
	
	protected Attribute(String name, int times, int specialAttackValue) {
		this.name = name;
		this.times = times;
		this.recordTimes = times;
		this.specialAttackValue = specialAttackValue;
		this.possible=false;
		this.rate = 0.5;
	}
	
	protected Attribute(String name, int times, int specialAttackValue, double rate) {
		this.name = name;
		this.times = times;
		this.recordTimes = times;
		this.specialAttackValue = specialAttackValue;
		this.possible=false;
		this.rate = rate;
	}
	
	@SuppressWarnings("unchecked")
	public Attribute accumulate(Attribute another){
		int times = another.accumulateTimes(this.times);
		int value = (this.times == 0 ? another.accumulateAttackValue(0) : another.accumulateAttackValue(this.specialAttackValue));
		//the same attribute replace
		if(this.times == 0){
			reset();
		}
		try {
			Constructor<Attribute> constructor = (Constructor<Attribute>) this.getClass().getDeclaredConstructor(String.class, int.class, int.class);
			return constructor.newInstance(this.name, times, value);
		} catch (Exception e) {
			return this;
		} 
	}
	
	public int affectBlood(String name, int blood, String status, PrintStream out) {
		//specialAttackValue = reset(times);
		if(times == 0 || blood <=0){
			return blood;
		}
		out.println(name+"受到"+specialAttackValue+"点"+this.nameToString()+"伤害,"+
				name+"剩余生命:"+(blood-specialAttackValue));
		times--;
		return blood-specialAttackValue;
	}
	
//	private int reset(int times){
//		return (times == 0 ? 0 : specialAttackValue);
//	}
	
	protected int accumulateTimes(int record){
		return this.recordTimes+record;
	}
	
	protected int accumulateAttackValue(int record){
		return this.specialAttackValue+record;
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
	
	public String getAttributeName(){
		return (possible ? this.getWeaponAttribute() : "");
	}
	
	protected String getWeaponAttribute(){
		return "";
	}
	
	protected String getWeaponAttributeAgain(){
		return "";
	}
	
	public void printStopAttackOnce(String attackName, String beAttackedName, PrintStream out) {
		
	}
	
	public String nameToString(){
		return name;
	}
	
	@Override
	public String toString(){
		return "伤害:"+this.name+SEPERATOR+"伤害值:"
		+this.specialAttackValue+SEPERATOR+"发动轮次:"
		+this.times+SEPERATOR+"发动概率:"+this.rate;
	}

	public void setPossible(boolean possible) {
		this.possible = possible;	
	}

	public void reset() {
		this.times = this.recordTimes;		
	}
}
