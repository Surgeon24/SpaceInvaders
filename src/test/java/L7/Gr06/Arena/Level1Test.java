package L7.Gr06.Arena;

import L7.Gr06.Common.Globals;
import L7.Gr06.Elements.Bullet;
import L7.Gr06.Elements.Enemies.Enemy;
import L7.Gr06.Elements.Enemies.EnemyBeta;
import L7.Gr06.Elements.Enemies.EnemyGamma;
import L7.Gr06.Elements.Position;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Level1Test {
    //Level1 tests
    @Test
    public void createEnemies(){
        Arena arena = new Level1();
        assertEquals(new Position(1,6),  arena.enemies.get(0).getPosition());
        assertEquals(new Position(7,9),  arena.enemies.get(3).getPosition());
        assertEquals(new Position(19,6), arena.enemies.get(6).getPosition());
    }

    @Test
    public void createWalls(){
        Arena arena = new Level1();
        assertEquals(new Position(5, Globals.height-8), arena.walls.get(0).getPosition());
        assertEquals(new Position(45,Globals.height-8), arena.walls.get(2).getPosition());
    }

    @Test
    public void changePositions() {
        Arena arena = new Level1();
        assertEquals(arena.enemies.get(0).getPosition(), new Position(1,6));
        assertEquals(arena.enemies.get(1).getPosition(), new Position(1,9));
        arena.changePositions();
        assertEquals(arena.enemies.get(0).getPosition(), new Position(2,6));
        assertEquals(arena.enemies.get(1).getPosition(), new Position(1,12));
        //shouldn't change anything, because frame didn't change
        arena.changePositions();
        assertEquals(new Position(2,6),  arena.enemies.get(0).getPosition());
        assertEquals(new Position(1,12), arena.enemies.get(1).getPosition());
    }
}
