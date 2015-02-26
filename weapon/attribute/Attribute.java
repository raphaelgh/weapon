package com.tw.trainning.fightergame.weapon.attribute;

import java.io.PrintStream;

public interface Attribute {
	public int affectBlood(String name, int blood, String status, PrintStream out) ;
	public String affectAttackStatus(String name, String status);
	public String affectPlayerStatus(String status);
	public void printStopAttackOnce(String attackName, String beAttackedName, PrintStream out);
	public String bust(String name);
	public void setPossible(boolean possible);
	public int accumulateTimes(int recordTimes);
	public int accumulateAttackValue(int recordAttackValue);
	public Attribute accumulate(Attribute another);
}
