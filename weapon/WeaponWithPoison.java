package com.tw.trainning.fightergame.weapon;

import java.io.PrintStream;
import java.util.Random;

import com.tw.trainning.fightergame.entity.Player;


public class WeaponWithPoison extends Weapon {

	private int poisonAttackValue;
	private final int recordAttackValue = 2;
	private int times;
	private final int recordTimes;
	public WeaponWithPoison(String name, int attackValue, int times) {
		super(name, attackValue);
		recordTimes = times;
	}
	
	public WeaponWithPoison(String name, int attackValue, int times, Random random) {
		super(name, attackValue, random);
		recordTimes = times;
	}
	
	@Override
	public int affectBlood(String name, int blood, String status, PrintStream out) {
		if(!possible && times == 0){
			return blood;
		}
		times = accumulate(possible, status, times, recordTimes); //should solve the first time call
		poisonAttackValue = accumulate(possible, status, poisonAttackValue, recordAttackValue);;
		out.println(name+"受到"+poisonAttackValue+"点毒性伤害,"+
				name+"剩余生命:"+(blood-poisonAttackValue));
		times--;
		return blood-poisonAttackValue;
	}
	
	private int accumulate(boolean possible, String status, int current, int record){
		if(possible && (Player.POISONED.equals(status) || Player.POISONEDAGAIN.equals(status))){
			return current+record;
		}
		return current;
	}
	
	@Override
	public String affectPlayerStatus(String status) {
		if(!possible){
			return status;
		}
		return accumulateStatus(possible, status);
	}
	
	private String accumulateStatus(boolean possible, String status){
		if(Player.POISONED.equals(status) || Player.POISONEDAGAIN.equals(status)){
			return Player.POISONEDAGAIN;
		}
		return Player.POISONED;
	}
	
	@Override
	public String affectAttackStatus(String name, String status){
		if(!possible){
			return Player.NOTHING;
		}
		return name+accumulateStatus(possible, status)+",";
	}
}
