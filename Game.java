package com.tw.trainning.fightergame;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Random;

import com.tw.trainning.fightergame.player.Player;
import com.tw.trainning.fightergame.player.Soldier;
import com.tw.trainning.fightergame.weapon.Weapon;
import com.tw.trainning.fightergame.weapon.WeaponRespository;

public class Game {

	Player playerA;
	Player playerB;
	PrintStream out;
	
	public Game(Player playerA, Player playerB, PrintStream print) {
		this.playerA = playerA;
		this.playerB = playerB;
		this.out = print;
	}

	public void start(){
		while(playerA.isLive(out)){
			playerA.attack(playerB, out);
			if(!playerB.isLive(out)){
				break;
			}
			playerB.attack(playerA, out);
		}
		Player player = playerA.isLive(out) ? playerB : playerA;
		player.outputStatus(out);
	}
	
	private void start(InputStream in){
		int i = 1;
		System.out.println("--------第"+i+"回合-------------");
		while(playerA.isLive(out)){						
			playerA.attack(playerB, out);
			wait123(2);
			if(!playerB.isLive(out)){
				break;
			}
			playerB.attack(playerA, out);
			//continue123(in);
			wait123(2);		
			i++;
			System.out.println();
			System.out.println("--------第"+i+"回合-------------");
		}
		Player player = playerA.isLive(out) ? playerB : playerA;
		player.outputStatus(out);
		System.out.println("输入bye结束游戏,按回车键游戏继续");
	}
	
	private void wait123(int seconds){
		try {
			Thread.sleep(seconds*1000);			
		} catch (InterruptedException e) {

		}
	}
	
//	private void continue123(InputStream in){
//		System.err.println("按回车键继续...");
//		try {
//			in.read();
//		} catch (IOException e) {				
//		}
//	}
	
	public static void main(String[] args){		
		Random random = new Random();
		WeaponRespository weaponRes = new WeaponRespository(random);	
		Game game;
		System.out.println("按回车键游戏开始");
		while(!isBye()){
			game = gameGenerator(random, weaponRes);
			game.start(System.in);
		}		
	}
	
	private static boolean isBye(){
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try{
			return "bye".equalsIgnoreCase(in.readLine());
		}
		catch(Exception e){
			return true;
		}
	}
	
	private static Game gameGenerator(Random random, WeaponRespository weaponRes){
		String name;
		int attackValue;
		String[] inputs = getInput();
		name = inputs[0];
		attackValue = Integer.parseInt(inputs[1]);		
		Player myself;
		Player other;
		myself = generateSelf(random, weaponRes, name, attackValue);
		other = generateCompetor(random, 10, weaponRes);
		return new Game(myself, other, System.out);		
	}
	
	private static Player generateSelf(Random random, WeaponRespository weaponRes, String name, int attackValue){		
		Weapon weapon;
		Player myself;
		weapon = weaponRes.fetchWeapon(random.nextInt(weaponRes.size()));
		System.out.println("系统为您选择的武器是:\r\n"+weapon.toString());
		myself = new Soldier(name, attackValue, 100, weapon, 10);
		System.out.println("根据您的输入，您的信息如下:\r\n"+myself.toString());
		return myself;
	}
	
	private static String[] getInput(){
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String name;
		int attackValue;
		while(true){
			try {
				System.out.println("请输入您的名字:");
				name = in.readLine();
				name = (name == null || "".equals(name) ? "王小二" : name);
				System.out.println("请输入您的攻击力:");
				try{
					attackValue = Integer.parseInt(in.readLine());					
				}
				catch(Exception e){
					attackValue = 5;
				}
				break;
			} catch (Exception e) {
				System.err.println("输入有误，请重新输入！");
			}
		}
		String[] strs = {name, String.valueOf(attackValue)};
		return strs;
	}

	private static Player generateCompetor(Random random, int attackValue, WeaponRespository weaponRes) {
		Weapon weapon = weaponRes.fetchWeapon(random.nextInt(weaponRes.size()));
		String name = "武大郎";
		int value = random.nextInt(attackValue) + 1;
		Player player = new Soldier(name, value, 100, weapon, 10);
		System.out.println("您的对手信息如下:\r\n"+player.toString());
		return player;
	}
}
