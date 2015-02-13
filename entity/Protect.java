package com.tw.trainning.fightergame.entity;

public class Protect implements Decorate{
	private int value;
	public Protect(int value){
		this.value = value;
	}
	
	public int value(){
		return this.value;
	}
}
