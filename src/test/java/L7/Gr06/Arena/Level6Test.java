package L7.Gr06.Arena;

import L7.Gr06.Common.Globals;
import L7.Gr06.Elements.Bullet;
import L7.Gr06.Elements.Enemies.EnemyGamma;
import L7.Gr06.Elements.Position;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Level6Test {
    @Test
    public void createEnemies(){
        Arena arena = new Level6();
        assertEquals(new Position(3,6),  arena.enemies.get(1).getPosition());
        assertEquals(new Position(3,9),  arena.enemies.get(2).getPosition());
        assertEquals(new Position(3,18), arena.enemies.get(4).getPosition());
        assertEquals(new Position(9,15), arena.enemies.get(8).getPosition());
    }

    @Test
    public void createWalls(){
        Arena arena = new Level6();
        assertEquals(new Position(5, Globals.height-8), arena.walls.get(0).getPosition());
        assertEquals(new Position(25,Globals.height-8), arena.walls.get(1).getPosition());
        assertEquals(new Position(45,Globals.height-8), arena.walls.get(2).getPosition());
    }

    @Test
    public void changePositions() {
        Arena arena = new Level6();
        assertEquals(arena.enemies.get(1).getPosition(), new Position(3,6));
        assertEquals(arena.enemies.get(6).getPosition(), new Position(9,9));
        arena.changePositions();
        assertEquals(arena.enemies.get(1).getPosition(), new Position(4,6));
        assertEquals(arena.enemies.get(6).getPosition(), new Position(8,9));
        //shouldn't change anything, because frame didn't change
        arena.changePositions();
        assertEquals(arena.enemies.get(1).getPosition(), new Position(4,6));
        assertEquals(arena.enemies.get(6).getPosition(), new Position(8,9));
    }


    //Arena tests
    @Test
    public void checkCollisions(){
        Arena arena = new Level6();
        int numberOfEnemiesBefore = arena.enemies.size();
        List<Bullet> newShots = arena.hero.getShots();
        newShots.add(newShots.size(), new Bullet(new Position(4,12),-1));  //enemy killed id0
        newShots.add(newShots.size(), new Bullet(new Position(4,7),-1));//enemy shot id2
        newShots.add(newShots.size(), new Bullet(new Position(3,18),-1)); //enemy shot id3
        newShots.add(newShots.size(), new Bullet(new Position(5,3),-1));  //shot missed
        newShots.add(newShots.size(), new Bullet(new Position(26, Globals.height - 8),-1));//wall shot
        arena.hero.setShots(newShots);
        arena.checkCollisions();

        assertEquals(numberOfEnemiesBefore-1, arena.enemies.size());
        assertEquals(1, arena.enemies.get(2).getHealth());
        assertEquals(1, arena.enemies.get(2).getHealth());
        assertEquals(4, arena.enemies.get(3).getHealth());
        assertEquals(4, arena.walls.get(1).getStrength());
    }

}
