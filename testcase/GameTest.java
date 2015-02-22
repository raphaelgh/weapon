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
import com.tw.trainning.fightergame.weapon.WeaponWithBust;
import com.tw.trainning.fightergame.weapon.WeaponWithFire;
import com.tw.trainning.fightergame.weapon.WeaponWithFreeze;
import com.tw.trainning.fightergame.weapon.WeaponWithHalo;
import com.tw.trainning.fightergame.weapon.WeaponWithPoison;


public class GameTest {
	
	PrintStream out = mock(PrintStream.class);
	Random random;
	
	@Before
	public void setup(){
		random = mock(Random.class);
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
		when(random.nextBoolean())
		.thenReturn(true);
		Weapon poison = new WeaponWithPoison("优质毒箭", 10, 1, random);
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
		when(random.nextBoolean())
		.thenReturn(true)
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
		//verify(out).println("张三受到2点毒性伤害,张三剩余生命:4");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:70");
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到30点伤害,张三剩余生命:-24");
		verify(out).println("张三被打败了");
	}
	
	@Test
	public void should_soldier_use_weapon_with_poison_and_effect_accumulation_fight_with_person(){
		Random random = mock(Random.class);
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false);
		Weapon poison = new WeaponWithPoison("优质毒箭", 10, 2, random);
		//Weapon sword = new Weapon("利剑", 40);
		Soldier playerA = new Soldier("李四",5,100,poison,20);
		Player playerB = new Player("张三", 30, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到15点伤害,张三中毒了,张三剩余生命:85");
		verify(out).println("张三受到2点毒性伤害,张三剩余生命:83");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:90");
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到15点伤害,张三又中毒了,张三剩余生命:68");
		verify(out).println("张三受到4点毒性伤害,张三剩余生命:64");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:80");
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到15点伤害,张三剩余生命:49");
		verify(out).println("张三受到4点毒性伤害,张三剩余生命:45");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:70");
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到15点伤害,张三剩余生命:30");
		verify(out).println("张三受到4点毒性伤害,张三剩余生命:26");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:60");
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到15点伤害,张三剩余生命:11");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:50");
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到15点伤害,张三剩余生命:-4");
		verify(out).println("张三被打败了");
	}
	
	@Test
	public void should_soldier_use_weapon_with_poison_and_effect_accumulation_twice_fight_with_person(){
		Random random = mock(Random.class);
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(true)
		.thenReturn(false);
		Weapon poison = new WeaponWithPoison("优质毒箭", 5, 2, random);
		//Weapon sword = new Weapon("利剑", 40);
		Soldier playerA = new Soldier("李四",5,100,poison,20);
		Player playerB = new Player("张三", 30, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到10点伤害,张三中毒了,张三剩余生命:90");
		verify(out).println("张三受到2点毒性伤害,张三剩余生命:88");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:90");
		
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到10点伤害,张三又中毒了,张三剩余生命:78");
		verify(out).println("张三受到4点毒性伤害,张三剩余生命:74");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:80");
		
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到10点伤害,张三剩余生命:64");
		verify(out).println("张三受到4点毒性伤害,张三剩余生命:60");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:70");
		
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到10点伤害,张三剩余生命:50");
		verify(out).println("张三受到4点毒性伤害,张三剩余生命:46");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:60");
		
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到10点伤害,张三剩余生命:36");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:50");
		
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到10点伤害,张三中毒了,张三剩余生命:26");
		verify(out).println("张三受到2点毒性伤害,张三剩余生命:24");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:40");
		
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到10点伤害,张三剩余生命:14");
		verify(out).println("张三受到2点毒性伤害,张三剩余生命:12");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:30");
		
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到10点伤害,张三剩余生命:2");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:20");
		
		verify(out).println("战士李四用优质毒箭攻击了普通人张三,张三受到10点伤害,张三剩余生命:-8");
		verify(out).println("张三被打败了");
	}
	
	@Test
	public void should_soldier_use_weapon_with_fire_and_effect_accumulation_twice_fight_with_person(){
		Random random = mock(Random.class);
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(true)
		.thenReturn(false);
		Weapon fire = new WeaponWithFire("火焰剑", 5, 2, random);
		Soldier playerA = new Soldier("李四",5,100,fire,20);
		Player playerB = new Player("张三", 30, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用火焰剑攻击了普通人张三,张三受到10点伤害,张三着火了,张三剩余生命:90");
		verify(out).println("张三受到2点烧伤伤害,张三剩余生命:88");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:90");
		
		verify(out).println("战士李四用火焰剑攻击了普通人张三,张三受到10点伤害,张三又着火了,张三剩余生命:78");
		verify(out).println("张三受到4点烧伤伤害,张三剩余生命:74");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:80");
		
		verify(out).println("战士李四用火焰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:64");
		verify(out).println("张三受到4点烧伤伤害,张三剩余生命:60");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:70");
		
		verify(out).println("战士李四用火焰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:50");
		verify(out).println("张三受到4点烧伤伤害,张三剩余生命:46");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:60");
		
		verify(out).println("战士李四用火焰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:36");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:50");
		
		verify(out).println("战士李四用火焰剑攻击了普通人张三,张三受到10点伤害,张三着火了,张三剩余生命:26");
		verify(out).println("张三受到2点烧伤伤害,张三剩余生命:24");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:40");
		
		verify(out).println("战士李四用火焰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:14");
		verify(out).println("张三受到2点烧伤伤害,张三剩余生命:12");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:30");
		
		verify(out).println("战士李四用火焰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:2");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:20");
		
		verify(out).println("战士李四用火焰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:-8");
		verify(out).println("张三被打败了");
	}
	
	@Test
	public void should_soldier_use_weapon_with_freeze_and_affect_one_time_and_with_random_fight_with_person(){
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(false);
		Weapon freeze = new WeaponWithFreeze("寒冰剑", 20, 2, random);
		Soldier playerA = new Soldier("李四",10,100,freeze,10);
		Player playerB = new Player("张三", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到30点伤害,张三冻僵了,张三剩余生命:70");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:90");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到30点伤害,张三剩余生命:40");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:80");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到30点伤害,张三剩余生命:10");		
		verify(out).println("张三冻得直哆嗦,没有击中李四");
		verify(out, never()).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:70");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到30点伤害,张三剩余生命:-20");
		verify(out).println("张三被打败了");
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
		Weapon freeze = new WeaponWithFreeze("寒冰剑", 5, 2, random);
		Soldier playerA = new Soldier("李四",5,100,freeze,10);
		Player playerB = new Player("张三", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三冻僵了,张三剩余生命:90");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:90");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三又冻僵了,张三剩余生命:80");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:80");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:70");		
		verify(out, times(2)).println("张三冻得直哆嗦,没有击中李四");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:60");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:70");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:50");
		verify(out, times(2)).println("张三冻得直哆嗦,没有击中李四");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:40");		
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:60");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:30");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:50");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:20");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:40");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:10");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:30");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:0");
		verify(out).println("张三被打败了");
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
		Weapon freeze = new WeaponWithFreeze("寒冰剑", 5, 2, random);
		Soldier playerA = new Soldier("李四",5,100,freeze,10);
		Player playerB = new Player("张三", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三冻僵了,张三剩余生命:90");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:90");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三又冻僵了,张三剩余生命:80");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:80");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:70");		
		verify(out, times(3)).println("张三冻得直哆嗦,没有击中李四");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:60");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:70");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:50");
		verify(out, times(3)).println("张三冻得直哆嗦,没有击中李四");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三冻僵了,张三剩余生命:40");		
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:60");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:30");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:50");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:20");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:40");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:10");
		verify(out, times(3)).println("张三冻得直哆嗦,没有击中李四");
		
		verify(out).println("战士李四用寒冰剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:0");
		verify(out).println("张三被打败了");
	}
	
	@Test
	public void should_soldier_use_weapon_with_halo_and_affect_one_time_and_with_random_fight_with_person(){
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(false);
		Weapon halo = new WeaponWithHalo("晕锤", 20, 2, random);
		Soldier playerA = new Soldier("李四",10,100,halo,10);
		Player playerB = new Player("张三", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到30点伤害,张三晕倒了,张三剩余生命:70");
		verify(out).println("张三晕倒了,无法攻击,眩晕还剩:1轮");
		
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到30点伤害,张三剩余生命:40");
		verify(out).println("张三晕倒了,无法攻击,眩晕还剩:0轮");
		
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到30点伤害,张三剩余生命:10");		
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:90");
		
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到30点伤害,张三剩余生命:-20");
		verify(out).println("张三被打败了");
	}
	
	@Test
	public void should_soldier_use_weapon_with_halo_and_effect_accumulation_fight_with_person(){
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(true)
		.thenReturn(false);
		Weapon halo = new WeaponWithHalo("晕锤", 10, 2, random);
		Soldier playerA = new Soldier("李四",10,100,halo,10);
		Player playerB = new Player("张三", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到20点伤害,张三晕倒了,张三剩余生命:80");
		verify(out, times(2)).println("张三晕倒了,无法攻击,眩晕还剩:1轮");
		
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到20点伤害,张三又晕倒了,张三剩余生命:60");
		verify(out).println("张三晕倒了,无法攻击,眩晕还剩:2轮");
		
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到20点伤害,张三剩余生命:40");		
		verify(out, times(2)).println("张三晕倒了,无法攻击,眩晕还剩:1轮");
		
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到20点伤害,张三剩余生命:20");
		verify(out).println("张三晕倒了,无法攻击,眩晕还剩:0轮");
		
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到20点伤害,张三剩余生命:0");
		verify(out).println("张三被打败了");
	}
	
	@Test
	public void should_soldier_use_weapon_with_halo_and_effect_accumulation_twice_fight_with_person(){
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false);
		Weapon halo = new WeaponWithHalo("晕锤", 5, 2, random);
		Soldier playerA = new Soldier("李四",5,100,halo,10);
		Player playerB = new Player("张三", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到10点伤害,张三晕倒了,张三剩余生命:90");
		verify(out, times(3)).println("张三晕倒了,无法攻击,眩晕还剩:1轮");
		
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到10点伤害,张三又晕倒了,张三剩余生命:80");
		verify(out).println("张三晕倒了,无法攻击,眩晕还剩:2轮");
		
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到10点伤害,张三剩余生命:70");		
		verify(out, times(3)).println("张三晕倒了,无法攻击,眩晕还剩:1轮");
		
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到10点伤害,张三剩余生命:60");
		verify(out, times(2)).println("张三晕倒了,无法攻击,眩晕还剩:0轮");
		
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到10点伤害,张三剩余生命:50");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:90");
		
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到10点伤害,张三晕倒了,张三剩余生命:40");
		verify(out, times(3)).println("张三晕倒了,无法攻击,眩晕还剩:1轮");
		
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到10点伤害,张三剩余生命:30");
		verify(out, times(2)).println("张三晕倒了,无法攻击,眩晕还剩:0轮");
		
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到10点伤害,张三剩余生命:20");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:80");
		
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到10点伤害,张三剩余生命:10");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:70");
		
		verify(out).println("战士李四用晕锤攻击了普通人张三,张三受到10点伤害,张三剩余生命:0");		
		verify(out).println("张三被打败了");
	}
	
	@Test
	public void should_soldier_use_weapon_with_bust_and_affect_one_time_and_with_random_fight_with_person(){
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(false);
		Weapon bust = new WeaponWithBust("利剑", 20, 2, random);
		Soldier playerA = new Soldier("李四",10,100,bust,10);
		Player playerB = new Player("张三", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用利剑攻击了普通人张三,李四发动了全力一击,张三受到70点伤害,张三剩余生命:30");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:90");
		
		verify(out).println("战士李四用利剑攻击了普通人张三,张三受到30点伤害,张三剩余生命:0");
		verify(out).println("张三被打败了");
	}
	
	@Test
	public void should_soldier_use_weapon_with_bust_and_effect_accumulation_fight_with_person(){
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(true)
		.thenReturn(false);
		Weapon bust = new WeaponWithBust("利剑", 20, 2, random);
		Soldier playerA = new Soldier("李四",10,100,bust,10);
		Player playerB = new Player("张三", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用利剑攻击了普通人张三,李四发动了全力一击,张三受到70点伤害,张三剩余生命:30");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:90");
		
		verify(out).println("战士李四用利剑攻击了普通人张三,李四发动了全力一击,张三受到70点伤害,张三剩余生命:-40");
		verify(out).println("张三被打败了");
	}
	
	@Test
	public void should_soldier_use_weapon_with_bust_and_effect_accumulation_twice_fight_with_person(){
		when(random.nextBoolean())
		.thenReturn(true)
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(true)
		.thenReturn(false);
		Weapon bust = new WeaponWithBust("利剑", 5, 2, random);
		Soldier playerA = new Soldier("李四",5,100,bust,10);
		Player playerB = new Player("张三", 20, 100);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用利剑攻击了普通人张三,李四发动了全力一击,张三受到20点伤害,张三剩余生命:80");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:90");
		
		verify(out).println("战士李四用利剑攻击了普通人张三,李四发动了全力一击,张三受到20点伤害,张三剩余生命:60");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:80");
		
		verify(out).println("战士李四用利剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:50");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:70");
		
		verify(out).println("战士李四用利剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:40");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:60");
		
		verify(out).println("战士李四用利剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:30");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:50");
		
		verify(out).println("战士李四用利剑攻击了普通人张三,李四发动了全力一击,张三受到20点伤害,张三剩余生命:10");
		verify(out).println("普通人张三攻击了战士李四,李四受到10点伤害,李四剩余生命:60");
		
		verify(out).println("战士李四用利剑攻击了普通人张三,张三受到10点伤害,张三剩余生命:0");
		
		verify(out).println("张三被打败了");
	}
	
	@Test
	public void should_soldier_use_weapon_with_poison_fight_with_soldier_use_weapon_with_halo(){
		Random poisonRandom = mock(Random.class);
		when(poisonRandom.nextBoolean())
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false);
		
		Random haloRandom = mock(Random.class);
		when(haloRandom.nextBoolean())
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false);
		
		Weapon poison = new WeaponWithPoison("毒剑", 15, 2, poisonRandom);
		Weapon halo = new WeaponWithHalo("晕锤", 20, 2, haloRandom);
		Soldier playerA = new Soldier("李四",10,100,poison,10);
		Soldier playerB = new Soldier("张三", 20, 100, halo, 10);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用毒剑攻击了战士张三,张三受到15点伤害,张三中毒了,张三剩余生命:85");
		verify(out).println("张三受到2点毒性伤害,张三剩余生命:83");
		verify(out).println("战士张三用晕锤攻击了战士李四,李四受到30点伤害,李四晕倒了,李四剩余生命:70");
		
		verify(out).println("李四晕倒了,无法攻击,眩晕还剩:1轮");
		verify(out).println("张三受到2点毒性伤害,张三剩余生命:81");
		verify(out).println("战士张三用晕锤攻击了战士李四,李四受到30点伤害,李四剩余生命:40");
		
		verify(out).println("李四晕倒了,无法攻击,眩晕还剩:0轮");
		verify(out).println("战士张三用晕锤攻击了战士李四,李四受到30点伤害,李四剩余生命:10");
		
		verify(out).println("战士李四用毒剑攻击了战士张三,张三受到15点伤害,张三剩余生命:66");
		verify(out).println("战士张三用晕锤攻击了战士李四,李四受到30点伤害,李四剩余生命:-20");
		
		verify(out).println("李四被打败了");
	}
	
	@Test
	public void should_soldier_use_weapon_with_fire_fight_with_soldier_use_weapon_with_freeze(){
		Random fireRandom = mock(Random.class);
		when(fireRandom.nextBoolean())
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false);
		
		Random freezeRandom = mock(Random.class);
		when(freezeRandom.nextBoolean())
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false);
		
		Weapon fire = new WeaponWithFire("火焰剑", 15, 3, fireRandom);
		Weapon freeze = new WeaponWithFreeze("冰刀", 20, 2, freezeRandom);
		Soldier playerA = new Soldier("李四",10,100,fire,10);
		Soldier playerB = new Soldier("张三", 20, 100, freeze, 10);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用火焰剑攻击了战士张三,张三受到15点伤害,张三着火了,张三剩余生命:85");
		verify(out).println("张三受到2点烧伤伤害,张三剩余生命:83");
		verify(out).println("战士张三用冰刀攻击了战士李四,李四受到30点伤害,李四冻僵了,李四剩余生命:70");
		
		verify(out).println("战士李四用火焰剑攻击了战士张三,张三受到15点伤害,张三剩余生命:68");
		verify(out).println("张三受到2点烧伤伤害,张三剩余生命:66");
		verify(out).println("战士张三用冰刀攻击了战士李四,李四受到30点伤害,李四剩余生命:40");
		
		verify(out).println("战士李四用火焰剑攻击了战士张三,张三受到15点伤害,张三剩余生命:51");
		verify(out).println("张三受到2点烧伤伤害,张三剩余生命:49");
		verify(out).println("战士张三用冰刀攻击了战士李四,李四受到30点伤害,李四剩余生命:10");
		
		verify(out).println("李四冻得直哆嗦,没有击中张三");
		verify(out).println("战士张三用冰刀攻击了战士李四,李四受到30点伤害,李四剩余生命:-20");
		
		verify(out).println("李四被打败了");
	}
	
	@Test
	public void should_soldier_use_weapon_with_halo_fight_with_soldier_use_weapon_with_freeze(){
		Random haloRandom = mock(Random.class);
		when(haloRandom.nextBoolean())
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false);
		
		Random freezeRandom = mock(Random.class);
		when(freezeRandom.nextBoolean())
		.thenReturn(true)
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false);
		
		Weapon halo = new WeaponWithHalo("晕锤", 15, 3, haloRandom);
		Weapon freeze = new WeaponWithFreeze("冰刀", 20, 2, freezeRandom);
		Soldier playerA = new Soldier("李四",10,100,halo,10);
		Soldier playerB = new Soldier("张三", 20, 100, freeze, 10);
		Game game = new Game(playerA, playerB, out);
		game.start();
		verify(out).println("战士李四用晕锤攻击了战士张三,张三受到15点伤害,张三晕倒了,张三剩余生命:85");
		verify(out).println("张三晕倒了,无法攻击,眩晕还剩:1轮");
		
		verify(out).println("战士李四用晕锤攻击了战士张三,张三受到15点伤害,张三剩余生命:70");
		verify(out).println("张三晕倒了,无法攻击,眩晕还剩:0轮");

		verify(out).println("战士李四用晕锤攻击了战士张三,张三受到15点伤害,张三剩余生命:55");
		verify(out).println("战士张三用冰刀攻击了战士李四,李四受到30点伤害,李四冻僵了,李四剩余生命:70");
		
		verify(out).println("战士李四用晕锤攻击了战士张三,张三受到15点伤害,张三剩余生命:40");
		verify(out).println("战士张三用冰刀攻击了战士李四,李四受到30点伤害,李四剩余生命:40");
		
		verify(out).println("战士李四用晕锤攻击了战士张三,张三受到15点伤害,张三剩余生命:25");
		verify(out).println("战士张三用冰刀攻击了战士李四,李四受到30点伤害,李四剩余生命:10");
		
		verify(out).println("李四冻得直哆嗦,没有击中张三");
		verify(out).println("战士张三用冰刀攻击了战士李四,李四受到30点伤害,李四剩余生命:-20");
		
		verify(out).println("李四被打败了");
	}
}
