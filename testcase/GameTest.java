package com.tw.trainning.fightergame.testcase;

import java.io.PrintStream;
import java.util.Random;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.tw.trainning.fightergame.Game;
import com.tw.trainning.fightergame.entity.Player;
import com.tw.trainning.fightergame.entity.Soldier;
import com.tw.trainning.fightergame.weapon.Weapon;
import com.tw.trainning.fightergame.weapon.WeaponRespository;
import com.tw.trainning.fightergame.weapon.WeaponWithPoison;


public class GameTest {
	
	PrintStream out = mock(PrintStream.class);
	
	@Before
	public void setup(){
		
	}
	
	@Test
	public void should_two_player_fight_together_and_output_result(){
		//PrintStream out = mock(PrintStream.class);
		Player playerA = new Player("李四", 20, 100);
		Player playerB = new Player("张三", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("张三被打败了");
	}
	
//	@Test
//	public void should_two_player_fight_together_and_output_process_and_result(){
//		//PrintStream out = mock(PrintStream.class);
//		Player playerA = new Player("李四", 40, 100);
//		Player playerB = new Player("张三", 30, 100);
//		Game game = new Game(playerA, playerB, out);
//		game.start();
//		verify(out).println("李四攻击了张三,张三受到40点伤害,张三剩余生命:60");
//		verify(out).println("张三攻击了李四,李四受到30点伤害,李四剩余生命:70");
//		verify(out).println("李四攻击了张三,张三受到40点伤害,张三剩余生命:20");
//		verify(out).println("张三攻击了李四,李四受到30点伤害,李四剩余生命:40");
//		verify(out).println("李四攻击了张三,张三受到40点伤害,张三剩余生命:-20");
//		verify(out).println("张三被打败了");
//	}
	
	@Test
	public void should_soldier_use_weapon_fight_with_person(){
		Weapon weapon = new Weapon("优质木棒", 10);
		Soldier playerA = new Soldier("李四",40,100,weapon,20);
		Player playerB = new Player("张三", 30, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用优质木棒攻击了普通人张三,张三受到50点伤害,张三剩余生命:50");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:90");
		verify(out).println("战士李四用优质木棒攻击了普通人张三,张三受到50点伤害,张三剩余生命:0");
		verify(out).println("张三被打败了");
	}
	
	@Test
	public void should_person_fight_with_person(){
		Player playerA = new Player("李四",40,100);
		Player playerB = new Player("张三", 30, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("普通人李四攻击了普通人张三,张三受到40点伤害,张三剩余生命:60");
		verify(out).println("普通人张三攻击了普通人李四,李四受到30点伤害,李四剩余生命:70");
		verify(out).println("普通人李四攻击了普通人张三,张三受到40点伤害,张三剩余生命:20");
		verify(out).println("普通人张三攻击了普通人李四,李四受到30点伤害,李四剩余生命:40");
		verify(out).println("普通人李四攻击了普通人张三,张三受到40点伤害,张三剩余生命:-20");
		verify(out).println("张三被打败了");
	}
	
	@Test
	public void should_soldier_fight_with_soldier(){
		Weapon stick = new Weapon("优质木棒", 10);
		Weapon sword = new Weapon("利剑", 40);
		Soldier playerA = new Soldier("李四",40,100,stick,20);
		Soldier playerB = new Soldier("张三", 30, 100, sword, 20);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用优质木棒攻击了战士张三,张三受到30点伤害,张三剩余生命:70");
		verify(out).println("战士张三用利剑攻击了战士李四,李四受到50点伤害,李四剩余生命:50");
		verify(out).println("战士李四用优质木棒攻击了战士张三,张三受到30点伤害,张三剩余生命:40");
		verify(out).println("战士张三用利剑攻击了战士李四,李四受到50点伤害,李四剩余生命:0");
		verify(out).println("李四被打败了");
	}
	
	@Test
	public void should_weapon_respository_work_well(){
		WeaponRespository.listWeapon(out);
		verify(out).print("名称:优质木棒,攻击力:20\r\n名称:利剑,攻击力:50\r\n" +
				"名称:长矛,攻击力:40\r\n名称:砍刀,攻击力:30\r\n");
	}
	
	@Test
	public void should_soldier_use_weapon_with_poison_and_affect_one_time_fight_with_person(){
		Weapon poison = new WeaponWithPoison("优质毒箭", 10, 1);
		//Weapon sword = new Weapon("利剑", 40);
		Soldier playerA = new Soldier("李四",40,100,poison,20);
		Player playerB = new Player("张三", 30, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到50点伤害,张三中毒了,张三剩余生命:50");
		verify(out).println("张三受到2点毒性伤害,张三剩余生命:48");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:90");
	}
	
	@Test
	public void should_soldier_use_weapon_with_poison_and_affect_one_time_and_with_random_fight_with_person(){
		Random random = mock(Random.class);
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false);
		Weapon poison = new WeaponWithPoison("优质毒箭", 10, 1, random);
		//Weapon sword = new Weapon("利剑", 40);
		Soldier playerA = new Soldier("李四",40,100,poison,20);
		Player playerB = new Player("张三", 30, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到50点伤害,张三中毒了,张三剩余生命:50");
		verify(out).println("张三受到2点毒性伤害,张三剩余生命:48");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:90");
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到50点伤害,张三剩余生命:-2");
		verify(out).println("张三被打败了");
	}
	
	@Test
	public void should_soldier_use_weapon_with_poison_and_affect_more_than_one_time_and_with_random_fight_with_person(){
		Random random = mock(Random.class);
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false);
		Weapon poison = new WeaponWithPoison("优质毒箭", 10, 2, random);
		//Weapon sword = new Weapon("利剑", 40);
		Soldier playerA = new Soldier("李四",20,100,poison,20);
		Player playerB = new Player("张三", 30, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到30点伤害,张三中毒了,张三剩余生命:70");
		verify(out).println("张三受到2点毒性伤害,张三剩余生命:68");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:90");
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到30点伤害,张三剩余生命:38");
		verify(out).println("张三受到2点毒性伤害,张三剩余生命:36");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:80");
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到30点伤害,张三剩余生命:6");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:70");
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到30点伤害,张三剩余生命:-24");
		verify(out).println("张三被打败了");
	}
}
