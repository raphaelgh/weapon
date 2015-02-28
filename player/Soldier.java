package com.tw.trainning.fightergame.player;

import java.io.PrintStream;
import com.tw.trainning.fightergame.weapon.Weapon;

public class Soldier extends Player{

	private Weapon weapon;
	//private int protect;
	private final String role = "战士";
	
	public Soldier(String name, int attackValue, int blood, Weapon weapon, int protect) {
		super(name, attackValue, blood);
		this.weapon = weapon;
		this.protector = protect;
	}
	
	@Override
	protected String getRole(){
		return role;
	}
	
//	@Override
//	public void attack(Player playerB, PrintStream out) {
//		//checkAffect(out);
//		if(!canBeAttack()){
//			this.affectWithWeapon.printStopAttackOnce(name, playerB.name, out);
//			this.playerStatus = this.playerStatus.replace(",无法攻击", "");
//			return;
//		}
//		playerB.beAttacked(attackValue+weapon.attack(playerB), getRole()+name+
//				weapon.name()+"攻击了"+playerB.getRole()+playerB.name+","+weapon.bust(name), out);
//	}
	
	@Override
	protected void beAttacked(Player playerB, PrintStream out){
		playerB.beAttacked(attackValue+weapon.attack(playerB), getRole()+name+
				weapon.name()+"攻击了"+playerB.getRole()+playerB.name+","+weapon.bust(name), out);
	}
	
//	@Override
//	protected void beAttacked(int attackValue, String info, PrintStream out){
//		int value = (attackValue - protect) < 0 ? 0 : (attackValue - protect);
//		blood = blood - value;
//		String attackStatus = this.affectWithWeapon.getWeaponAttributeName();
//		attackStatus = ("".equals(attackStatus) ? attackStatus : name+attackStatus+",");
//		out.println(info+name+"受到"+value+"点伤害,"+attackStatus+
//				name+"剩余生命:"+blood);
//		playerStatus = blood <= 0 ? DEFEATED : playerStatus;
//	}

	@Override
	public String toString(){
		return super.toString()+seperator+"防御力:"+this.getProtector()+seperator+
		"\r\n装备的武器信息:"+this.weapon.toString();
	}
}
