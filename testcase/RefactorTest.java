package com.tw.trainning.fightergame.testcase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintStream;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.tw.trainning.fightergame.Game;
import com.tw.trainning.fightergame.entity.Player;
import com.tw.trainning.fightergame.entity.Soldier;
import com.tw.trainning.fightergame.weapon.RefactorWeapon;
import com.tw.trainning.fightergame.weapon.Weapon;
import com.tw.trainning.fightergame.weapon.attribute.Attribute;
import com.tw.trainning.fightergame.weapon.attribute.Freeze;


public class RefactorTest {
	
	PrintStream out = mock(PrintStream.class);
	Random random;
	
	@Before
	public void setup(){
		random = mock(Random.class);
	}
	
	@Test
	public void should_soldier_use_weapon_with_freeze_and_affect_one_time_and_with_random_fight_with_person(){
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(false);
		Attribute freeze = new Freeze("������", 2, true);
		Weapon freezeWeapon = new RefactorWeapon("������", 20, freeze, random);
		Soldier playerA = new Soldier("����",10,100,freezeWeapon,10);
		Player playerB = new Player("����", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�30���˺�,����������,����ʣ������:70");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:90");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�30���˺�,����ʣ������:40");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:80");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�30���˺�,����ʣ������:10");		
		verify(out).println("��������ֱ����,û�л�������");
		verify(out, never()).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:70");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�30���˺�,����ʣ������:-20");
		verify(out).println("�����������");
	}
	
	@Test
	public void should_soldier_use_weapon_with_freeze_and_effect_accumulation_fight_with_person(){
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false);
		Attribute freeze = new Freeze("������", 2, true);
		Weapon freezeWeapon = new RefactorWeapon("������", 5, freeze, random);
		Soldier playerA = new Soldier("����",5,100,freezeWeapon,10);
		Player playerB = new Player("����", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����������,����ʣ������:90");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:90");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,�����ֶ�����,����ʣ������:80");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:80");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:70");		
		verify(out, times(2)).println("��������ֱ����,û�л�������");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:60");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:70");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:50");
		verify(out, times(2)).println("��������ֱ����,û�л�������");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:40");		
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:60");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:30");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:50");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:20");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:40");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:10");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:30");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:0");
		verify(out).println("�����������");
	}
	
	@Test
	public void should_soldier_use_weapon_with_freeze_and_effect_accumulation_twice_fight_with_person(){
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(true)
		.thenReturn(false);
		Attribute freeze = new Freeze("������", 2, true);
		Weapon freezeWeapon = new RefactorWeapon("������", 5, freeze, random);
		Soldier playerA = new Soldier("����",5,100,freezeWeapon,10);
		Player playerB = new Player("����", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����������,����ʣ������:90");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:90");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,�����ֶ�����,����ʣ������:80");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:80");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:70");		
		verify(out, times(3)).println("��������ֱ����,û�л�������");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:60");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:70");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:50");
		verify(out, times(3)).println("��������ֱ����,û�л�������");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����������,����ʣ������:40");		
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:60");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:30");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:50");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:20");
		verify(out).println("��ͨ������������սʿ����,�����ܵ�10���˺�,����ʣ������:40");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:10");
		verify(out, times(3)).println("��������ֱ����,û�л�������");
		
		verify(out).println("սʿ�����ú�������������ͨ������,�����ܵ�10���˺�,����ʣ������:0");
		verify(out).println("�����������");
	}
}
