package L7.Gr06.Arena;

import L7.Gr06.Common.Globals;
import L7.Gr06.Elements.Enemies.Enemy;
import L7.Gr06.Elements.Position;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class Level1Test {
    //Level1 tests
    @Test
    public void createEnemies(){
        Arena arena = new Level1();
        assertEquals(arena.enemies.get(0).getPosition(), new Position(1,6));
        assertEquals(arena.enemies.get(3).getPosition(), new Position(7,9));
        assertEquals(arena.enemies.get(6).getPosition(), new Position(19,6));
    }

    @Test
    public void createWalls(){
        Arena arena = new Level1();
        assertEquals(arena.walls.get(0).getPosition(), new Position(5, Globals.height-8));
        assertEquals(arena.walls.get(2).getPosition(), new Position(45,Globals.height-8));
    }

    //Arena tests

/*    @Test
    public void changePositions(){

        Level1 arena = new Level1();
        Enemy enemy0Before = arena.enemies.get(0);
        arena.changePositions();
        arena.changePositions();
        Enemy enemy0 = arena.enemies.get(0);


        assertEquals(enemy0Before.getX(), 2);
        assertEquals(enemy0Before.getY(), 6);
        assertEquals(enemy0Before.getPosition(), new Position(2,6));

        assertEquals(enemy0.getX(), 4);
        assertEquals(enemy0.getY(), 6);
        assertEquals(enemy0.getPosition(), new Position(2,6));

    }
  */
}
