package com.tw.trainning.fightergame.weapon.attribute;

public class Fire extends Attribute {

	private final String ATTRIBUTE = "������";
	
	public Fire(String name, int times) {
		super(name, times);
	}
	
	public Fire(String name, int times, int specialAttackValue) {
		super(name, times, specialAttackValue);
	}
	
	public Fire(int times, int specialAttackValue) {
		super("����", times, specialAttackValue);
	}

	@Override
	protected String getWeaponAttribute(){
		return ATTRIBUTE;
	}
	
	@Override
	protected String getWeaponAttributeAgain(){
		return "��"+ATTRIBUTE;
	}

}
