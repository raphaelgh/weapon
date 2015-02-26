package com.tw.trainning.fightergame.weapon.attribute;

public class Bust extends Attribute{
	
	public Bust() {
		super("", 0);
	}
	
	private final String HARMNESS = "发动了全力一击,";

	@Override
	public String bust(String name) {
		return name+HARMNESS+",";
	}
}
