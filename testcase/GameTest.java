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
		Player playerA = new Player("����", 20, 100);
		Player playerB = new Player("����", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("�����������");
	}
	
	@Test
	public void should_two_player_fight_together_and_output_process_and_result(){
		PrintStream out = mock(PrintStream.class);
		Player playerA = new Player("����", 40, 100);
		Player playerB = new Player("����", 30, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("���Ĺ���������,�����ܵ�40���˺�,����ʣ������:60");
		verify(out).println("��������������,�����ܵ�30���˺�,����ʣ������:70");
		verify(out).println("���Ĺ���������,�����ܵ�40���˺�,����ʣ������:20");
		verify(out).println("��������������,�����ܵ�30���˺�,����ʣ������:40");
		verify(out).println("���Ĺ���������,�����ܵ�40���˺�,����ʣ������:-20");
		verify(out).println("�����������");
	}
	
}
