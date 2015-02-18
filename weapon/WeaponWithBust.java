package com.tw.trainning.fightergame.weapon;

import java.io.PrintStream;
import java.util.Random;

import com.tw.trainning.fightergame.entity.Player;

public class WeaponWithBust extends Weapon{

	private final String HARMNESS = "发动了全力一击,";

	public WeaponWithBust(String name, int attackValue, int times,
			Random random) {
		super(name, attackValue, 1, random);
	}
	
	@Override
	public int attack(Player player){
		this.possible = (random != null ? random.nextBoolean() : possible);
		player.beAffectedByWeapon(this);
		if(possible || times != 0){
			return attackValue*3;
		}
		return attackValue;
	}
	
	@Override
	protected String getWeaponHarmness(){
		return HARMNESS;
	}
	
	@Override
	public String bust(String name) {
		if(possible || times != 0){
			return name+HARMNESS;
		}
		return "";
	}
	
	@Override
	public int affectBlood(String name, int blood, String status, PrintStream out) {
		if(!possible && times == 0){
			return blood;
		}
		times = accumulate(possible, status, times, recordTimes);
		times--;
		return blood;
	}
	
	@Override
	protected int accumulate(boolean possible, String status, int current, int record){
		if(possible){
			return current+record;
		}
		return current;
	}
	
	@Override
	public String affectAttackStatus(String name, String status){
		return Player.NOTHING;
	}
}
