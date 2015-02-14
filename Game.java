package com.tw.trainning.fightergame;

import java.io.PrintStream;

import com.tw.trainning.fightergame.entity.Player;

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
		while(playerA.canBeAttack() && playerB.canBeAttack()){
			playerA.attack(playerB);
			if(!playerB.canBeAttack()){
				break;
			}
			playerB.attack(playerA);
		}
		Player player = playerA.canBeAttack() ? playerB : playerA;
		player.outputStatus(out);
	}
}
