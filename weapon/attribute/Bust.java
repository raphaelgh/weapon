package com.tw.trainning.fightergame.weapon.attribute;

public class Bust extends Attribute{
	
	public Bust() {
		super("", 0);
	}
	
	private final String HARMNESS = "������ȫ��һ��,";

	@Override
	public String bust(String name) {
		return name+HARMNESS+",";
	}
}
