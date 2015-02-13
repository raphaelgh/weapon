package com.tw.trainning.fightergame;

import java.io.PrintStream;

import com.tw.trainning.fightergame.entity.Player;

public class Game {

	Player playerA;
	Player playerB;
	PrintStream print;
	
	public Game(Player playerA, Player playerB, PrintStream print) {
		this.playerA = playerA;
		this.playerB = playerB;
		this.print = print;
	}

	public void start(){
		while(playerA.canBeAttack() && playerB.canBeAttack()){
			playerA.attack(playerB);
			if(!playerB.canBeAttack()){
				break;
			}
			playerB.attack(playerA);
		}
		Player defeatedPlayer = playerA.canBeAttack() ? playerB : playerA;
		defeatedPlayer.printBeDefeated();
	}
}
