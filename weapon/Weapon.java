package com.tw.trainning.fightergame.weapon;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.tw.trainning.fightergame.entity.Player;
import com.tw.trainning.fightergame.weapon.attribute.Attribute;
import com.tw.trainning.fightergame.weapon.attribute.Bust;
import com.tw.trainning.fightergame.weapon.attribute.NULLAttribute;

public class Weapon{
	protected int attackValue;
	private String name;   
	protected boolean possible=false;
	protected Random random;
	private Attribute extraHarm;
	private List<Attribute> attrList = new ArrayList<Attribute>();
	private String bust;
	
	public Weapon(String name, int attackValue) {
		this.attackValue = attackValue;
		this.name = name;
		this.random = new Random();
		extraHarm = NULLAttribute.getInstance();
		//attrList.add(extraHarm);
	}
	
	public Weapon(String name, int attackValue, Attribute attr, Random random) {
		this.attackValue = attackValue;
		this.name = name;
		this.random = random;
		this.extraHarm = attr;
		attrList.add(extraHarm);
	}
	
	public int attack(Player player){		
		return player.beAffectedByWeapon(this);
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
		return "用"+name;
	}
	
	@Override
	public String toString(){
		StringBuilder info = new StringBuilder();
		info.append("名称:"+name+",攻击力:"+attackValue);
		info.append("\r\n");
		Iterator<Attribute> iterator = this.attrList.iterator();
		Attribute attr;
		int index = 0;
		while(iterator.hasNext()){
			index++;
			attr = iterator.next();
			info.append("属性"+index+":"+attr.toString());
			info.append("\r\n");
		}
		return info.toString();
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
		return ("".equals(bust) ? bust : name+bust);
	}
	
	public void addAttribute(Attribute freeze) {
		attrList.add(freeze);
	}
	
	public int accumulate(Weapon affectWithWeapon) {
		Attribute selected = selectAttribute(random);		
		this.extraHarm = affectWithWeapon.accumulate(selected);
		this.possible = !(NULLAttribute.class.equals(selected.getClass()) || Bust.class.equals(selected.getClass()));	
		extraHarm.setPossible(possible);
		return intensifyAttack(selected);
	}

	protected Attribute accumulate(Attribute another) {
		boolean isNextHarmNULL = NULLAttribute.class.equals(another.getClass()) || Bust.class.equals(another.getClass());
		boolean possibility = !isNextHarmNULL && ((NULLAttribute.class.equals(this.extraHarm.getClass()) || this.extraHarm.getClass().equals(another.getClass())));
		return (possibility ? this.extraHarm.accumulate(another) : (isNextHarmNULL ? this.extraHarm : another));
	}
	
	public String getWeaponAttributeName() {
		return this.extraHarm.getAttributeName();
	}
	
	private int intensifyAttack(Attribute attr){		
		this.bust = (Bust.class.equals(attr.getClass()) ? "发动了全力一击," : "");
		return (Bust.class.equals(attr.getClass()) ? this.attackValue * 3 : this.attackValue);
	}
	
	public void addPrefixToName(String prefix){
		this.name = prefix+this.name;
	}
}
