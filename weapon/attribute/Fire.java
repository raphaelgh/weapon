package com.tw.trainning.fightergame.weapon.attribute;

public class Fire extends Base {

	private final String ATTRIBUTE = "������";
	private final String HARMNESS = "����";
	
	public Fire(String name, int times, boolean possible) {
		super(name, times, possible);
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
