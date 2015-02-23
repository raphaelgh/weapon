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
		Attribute freeze = new Freeze("寒冰剑", 2, true);
		Weapon freezeWeapon = new RefactorWeapon("寒冰剑", 20, freeze, random);
		Soldier playerA = new Soldier("李四",10,100,freezeWeapon,10);
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
		Attribute freeze = new Freeze("寒冰剑", 2, true);
		Weapon freezeWeapon = new RefactorWeapon("寒冰剑", 5, freeze, random);
		Soldier playerA = new Soldier("李四",5,100,freezeWeapon,10);
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
		Attribute freeze = new Freeze("寒冰剑", 2, true);
		Weapon freezeWeapon = new RefactorWeapon("寒冰剑", 5, freeze, random);
		Soldier playerA = new Soldier("李四",5,100,freezeWeapon,10);
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
}
