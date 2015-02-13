package com.tw.trainning.fightergame.weapon;

import java.io.PrintStream;

import com.tw.trainning.fightergame.entity.Player;

public abstract class Weapon{

	protected String name;
	protected int value;
	protected PrintStream printer;
	protected int harmValue = 2;
	protected int possibility;
	
	public Weapon(String name, int value, int harmvalue, PrintStream out){
		this.name = name;
		this.value = value;
		this.printer = out;
		this.harmValue = harmvalue;
	}
	
	public void setPossibility(int p){
		this.possibility = p;
	}
	
	public int value(){
		return this.value;
	}
	
	public String toString(){
		return name;
	}
	
	public void harm(Player player){
		//System.out.println(this.possibility);
		if(possibility == 1){
			player.beAttack(harmValue);
			print(player);
		}
	}

	public abstract String harmStatus(String name);
	public abstract void print(Player player);

	public String use() {
		if(possibility == 1){
			return " use " + name;
		}
		return " use " + (name.split(" "))[1];
	}
	
}
