package com.tw.trainning.fightergame.entity;

import java.io.PrintStream;

import com.tw.trainning.fightergame.weapon.NullWeapon;
import com.tw.trainning.fightergame.weapon.Protect;
import com.tw.trainning.fightergame.weapon.Weapon;

public class Player {

	private String name;	
	private int blood;
	private int attack;
	protected PrintStream printer;
	private String role;
	private Weapon myWeapon = new NullWeapon("null weapon", 0, 0, printer);
	private Weapon enemyWeapon = new NullWeapon("null weapon", 0, 0, printer);
	private Protect protect = new Protect(0);
	
	public Player(String name, String role, int blood, int attack, PrintStream printer) {
		this.name = name;
		this.blood = blood;
		this.attack = attack;
		this.printer = printer;
		this.role = role + " ";
	}
	
	public Player(String name, String role, int blood, int attack, Weapon weapon, Protect protect, PrintStream printer) {
		this.name = name;
		this.blood = blood;
		this.attack = attack + weapon.value();
		this.printer = printer;
		this.role = role + " ";
		this.myWeapon = weapon;
		this.protect = protect;
	}
	
	public int getBlood() {
		return blood;
	}

	public String getName() {
		return name;
	}

	protected String getRole() {
		return role;
	}

	public void attack(Player p){
		this.enemyWeapon.harm(this);
		p.beAttack(this.attack - p.protect.value());
		p.setWeapon(myWeapon);
		print(p);
	}
	
//	public void attackWithWeapon(Player p, Weapon weapon){
//		this.enemyWeapon.harm(this);
//		p.beAttack(this.attack);
//		p.setWeapon(weapon);
//		print(p);
//	}
	
	private void setWeapon(Weapon weapon) {
		this.enemyWeapon = weapon;
	}

	protected int getAttackValue(){
		return attack;
	}
	
	public void beAttack(int harm){
		this.blood = this.blood - harm;
	}
	
	protected void print(Player p){
		printer.println(this.role + this.name + this.myWeapon.use() + " attack " + p.role + p.name
				+ ". " + p.name + " lose " + (this.attack - p.protect.value())
				+ " blood. " + p.enemyWeapon.harmStatus(p.name) + p.name + " left "
				+ p.blood + " blood.");
	}
	
//	protected void print(Soldier s){
//		printer.println(this.role + this.name + " attack " + s.getRole() + s.getName()
//				+ ". " + s.getName() + " lose " + (this.attack - s.getProtectValue())
//				+ " blood. " + s.getName() + " left "
//				+ s.getBlood() + " blood.");
//	}
	
	public void printBeDefeated(){
		printer.println(this.name + " be defeated!");
	}
	
	public boolean canBeAttack(){
		return (this.blood > 0);
	}
}
