package com.tw.trainning.fightergame.weapon.attribute;

public class Fire extends Attribute {

	private final String ATTRIBUTE = "������";
	private final String HARMNESS = "����";
	
	public Fire(String name, int times, boolean possible) {
		super(name, times, possible);
	}
	
	public Fire(String name, int times, int specialAttackValue, boolean possible) {
		super(name, times, specialAttackValue, possible);
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
