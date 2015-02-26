package com.tw.trainning.fightergame.weapon;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.tw.trainning.fightergame.entity.Player;
import com.tw.trainning.fightergame.weapon.attribute.Attribute;
import com.tw.trainning.fightergame.weapon.attribute.NULLAttribute;

public class RefactorWeapon extends Weapon{
	protected final int attackValue; //not support upgrade
	private final String name;     //not support rename
	protected boolean possible=false;
	protected Random random;
	private Attribute extraHarm = NULLAttribute.getInstance();
	private List<Attribute> attrList = new ArrayList<Attribute>();
	
	public RefactorWeapon(String name, int attackValue) {
		super(name, attackValue);
		this.attackValue = attackValue;
		this.name = name;
	}
	
	public RefactorWeapon(String name, int attackValue, Attribute attr, Random random) {
		super(name, attackValue, 0, random);
		this.attackValue = attackValue;
		this.name = name;
		this.random = random;
		this.extraHarm = attr;
		attrList.add(attr);
	}
	
	public int attack(Player player){		
		player.beAffectedByWeapon(this);
		return attackValue;
	}
	
	private Attribute selectAttribute(Random random){
		int index = random.nextInt(9);
		try{
			return attrList.get(index);
		}
		catch(Exception e){
			return NULLAttribute.getInstance();
		}
	}
	
	public String name(){
		return "ÓÃ"+name;
	}
	
	@Override
	public String toString(){
		return "Ãû³Æ:"+name+",¹¥»÷Á¦:"+attackValue;
	}
	
	
	public int affectBlood(String name, int blood, String status, PrintStream out) {
		return extraHarm.affectBlood(name, blood, status, out);
	}
	
	public String affectPlayerStatus(String status) {
		return extraHarm.affectPlayerStatus(status);
	}

	public void printStopAttackOnce(String attackName, String beAttackedName, PrintStream out) {
		extraHarm.printStopAttackOnce(attackName, beAttackedName, out);
	}

	public String bust(String name) {
		return extraHarm.bust(name);
	}
	

	public void addAttribute(Attribute freeze) {
		attrList.add(freeze);
	}
	
	@Override
	public void accumulate(Weapon affectWithWeapon) {
		Attribute selected = selectAttribute(random);
		this.extraHarm = affectWithWeapon.accumulate(selected);
		this.possible = !NULLAttribute.class.equals(selected.getClass());	
		extraHarm.setPossible(possible);
	}

	@Override
	protected Attribute accumulate(Attribute another) {
		boolean isNextHarmNULL = NULLAttribute.class.equals(another.getClass());
		boolean possibility = !isNextHarmNULL && ((NULLAttribute.class.equals(this.extraHarm.getClass()) || this.extraHarm.getClass().equals(another.getClass())));
		return (possibility ? this.extraHarm.accumulate(another) : (isNextHarmNULL ? this.extraHarm : another));
	}
	
	@Override
	public String getWeaponAttributeName() {
		return this.extraHarm.getAttributeName();
	}
}
