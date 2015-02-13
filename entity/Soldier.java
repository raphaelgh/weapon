package com.tw.trainning.fightergame.entity;

import java.io.PrintStream;

import com.tw.trainning.fightergame.weapon.Protect;
import com.tw.trainning.fightergame.weapon.Weapon;

public class Soldier extends Player{

	private Weapon weapon;
	private Protect protect;
	
	public Soldier(String name, String role, int blood, int attack,
			Weapon weapon, Protect protect, PrintStream printer) {
		super(name, role, blood, (attack + weapon.value()), printer);
		this.weapon = weapon;
		this.protect = protect;
	}
	
	protected int getWeaponValue() {
		return weapon.value();
	}

	protected int getProtectValue() {
		return protect.value();
	}
	
	public void attack(Player player, Weapon weapon){
		weapon.harm(player);
		super.attack(player);
	}
	
	@Override
	public void beAttack(int harm){
		super.beAttack(harm - protect.value());
	}
	
	@Override
	protected void print(Player p){
		int protect = (Soldier.class.isInstance(p)) ? ((Soldier)p).getProtectValue() : 0;
		printer.println(this.getRole() + this.getName() + " use "  
				+ this.weapon.toString() +" attack " + p.getRole() + p.getName()
				+ ". " + p.getName() + " lose " + (this.getAttackValue() - protect)
				+ " blood. " + p.getName() + " left "
				+ p.getBlood() + " blood.");
	}
}
