package com.tw.trainning.fightergame.weapon;

import java.io.PrintStream;
import java.util.Random;

import com.tw.trainning.fightergame.entity.Player;

public class WeaponWithHalo extends Weapon{
	public static final String ATTRIBUTE = "‘Œµπ¡À";
	public static final String HARMNESS = "—£‘Œ";

	public WeaponWithHalo(String name, int attackValue, int times,
			Random random) {
		super(name, attackValue, 2, random);
	}
	
	@Override
	protected String getWeaponAttribute(){
		return ATTRIBUTE;
	}
	
	@Override
	protected String getWeaponAttributeAgain(){
		return "”÷"+ATTRIBUTE;
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
		//times = accumulate(possible, status, times, recordTimes);
		return blood;
	}
	
	@Override
	public String affectPlayerStatus(String status) {
		if(!possible && times == 0 && status.indexOf("‘Œµπ") != -1){
			return Player.NOTHING;
		}
		if(!possible && status.indexOf("‘Œµπ") == -1){
			return status;
		}
		return super.affectPlayerStatus(status)+",Œﬁ∑®π•ª˜";
	}
	
	@Override
    public void printStopAttackOnce(String attackName, String beAttackedName, PrintStream out) {
		out.println(attackName + "‘Œµπ¡À,Œﬁ∑®π•ª˜,—£‘Œªπ £:" + times + "¬÷");
	}
}
