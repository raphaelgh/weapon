package com.tw.trainning.fightergame.entity;

public class Weapon extends Protect{

	public Weapon(String name, int value) {
		super(value);
		this.name = name;
	}

	private String name;
	
	
	public String toString(){
		return name;
	}
}
