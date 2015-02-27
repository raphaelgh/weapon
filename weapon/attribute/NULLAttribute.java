package com.tw.trainning.fightergame.weapon.attribute;

import java.io.PrintStream;

public class NULLAttribute extends Attribute{
	
	private NULLAttribute(){
		super("", 0);
	}
	
	private static NULLAttribute instance = new NULLAttribute();
	
	public static NULLAttribute getInstance(){
		instance = (instance == null ? new NULLAttribute() : instance);
		return instance;
	}

	@Override
	public String getAttributeName() {
		return "";
	}

	@Override
	public int affectBlood(String name, int blood, String status, PrintStream out){
		return blood;
	}

	@Override
	public String affectPlayerStatus(String status) {
		return status;
	}

	@Override
	public void printStopAttackOnce(String attackName, String beAttackedName,
			PrintStream out) {
	}

	@Override
	public void setPossible(boolean possible) {
		
	}

	@Override
	public Attribute accumulate(Attribute another) {
		return another;		
	}

	@Override
	protected int accumulateAttackValue(int recordAttackValue) {
		return recordAttackValue;
	}

	@Override
	protected int accumulateTimes(int recordTimes) {
		return recordTimes;
	}

}
