package com.tw.trainning.fightergame.weapon;

import java.util.Random;

public class WeaponWithFire extends Weapon{

	private final String ATTRIBUTE = "�Ż���";
	private final String HARMNESS = "����";
	
	public WeaponWithFire(String name, int attackValue, int times, Random random) {
		super(name, attackValue, times, random);
	}
	
	@Override
	protected String getWeaponAttribute(){
		return ATTRIBUTE;
	}
	
	@Override
	protected String getWeaponAttributeAgain(){
		return "��"+ATTRIBUTE;
	}
	
	@Override
	protected String getWeaponHarmness(){
		return HARMNESS;
	}
}
