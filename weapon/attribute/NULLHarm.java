package com.tw.trainning.fightergame.weapon.attribute;

import java.io.PrintStream;

public class NULLHarm implements Attribute{
	
	private NULLHarm(){}
	
	private static NULLHarm instance = new NULLHarm();
	
	public static Attribute getNULLHarm(){
		instance = (instance == null ? new NULLHarm() : instance);
		return instance;
	}

	@Override
	public void accumulate(String status) {
	}

	@Override
	public String affectAttackStatus(String name, String status) {
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
	public String bust(String name) {
		return "";
	}

	@Override
	public void printStopAttackOnce(String attackName, String beAttackedName,
			PrintStream out) {
	}

	@Override
	public void setPossible(boolean possible) {
		
	}

	@Override
	public Attribute accumulate(String status, Attribute another) {
		return another;		
	}

	@Override
	public int accumulateAttackValue(boolean possible, String status,
			int recordAttackValue) {
		return recordAttackValue;
	}

	@Override
	public int accumulateTimes(boolean possible, String status, int recordTimes) {
		return recordTimes;
	}

}
