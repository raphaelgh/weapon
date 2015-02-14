package com.tw.trainning.fightergame.testcase;

import java.io.PrintStream;
import java.util.Random;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.tw.trainning.fightergame.Game;
import com.tw.trainning.fightergame.entity.Player;
import com.tw.trainning.fightergame.entity.Soldier;
import com.tw.trainning.fightergame.weapon.Protect;
import com.tw.trainning.fightergame.weapon.Weapon;
import com.tw.trainning.fightergame.weapon.WeaponWithFreeze;
import com.tw.trainning.fightergame.weapon.WeaponWithPoison;


public class GameTest {
	
	@Test
	public void should_two_player_fight_together_and_output_result(){
		PrintStream out = mock(PrintStream.class);
		Player playerA = new Player("李四", 20, 100);
		Player playerB = new Player("张三", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("张三被打败了");
	}
	
	@Test
	public void should_two_player_fight_together_and_output_process_and_result(){
		PrintStream out = mock(PrintStream.class);
		Player playerA = new Player("李四", 40, 100);
		Player playerB = new Player("张三", 30, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("李四攻击了张三,张三受到40点伤害,张三剩余生命:60");
		verify(out).println("张三攻击了李四,李四受到30点伤害,李四剩余生命:70");
		verify(out).println("李四攻击了张三,张三受到40点伤害,张三剩余生命:20");
		verify(out).println("张三攻击了李四,李四受到30点伤害,李四剩余生命:40");
		verify(out).println("李四攻击了张三,张三受到40点伤害,张三剩余生命:-20");
		verify(out).println("张三被打败了");
	}
	
}
