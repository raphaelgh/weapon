package com.tw.trainning.fightergame.weapon.attribute;

public class Poison extends Attribute{
	private final String ATTRIBUTE = "÷–∂æ¡À";
	
	public Poison(String name, int times) {
		super(name, times);
	}
	
	public Poison(String name, int times, int specialAttackValue) {
		super(name, times, specialAttackValue);
	}

	@Override
	protected String getWeaponAttribute(){
		return ATTRIBUTE;
	}
	
	@Override
	protected String getWeaponAttributeAgain(){
		return "”÷"+ATTRIBUTE;
	}
}
