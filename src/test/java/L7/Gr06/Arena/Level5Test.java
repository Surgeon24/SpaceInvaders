package L7.Gr06.Arena;

import L7.Gr06.Common.Globals;
import L7.Gr06.Elements.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Level5Test {
    @Test
    public void createEnemies(){
        Arena arena = new Level5();
        assertEquals(new Position(3,6),  arena.enemies.get(0).getPosition());
        assertEquals(new Position(3,9),  arena.enemies.get(1).getPosition());
        assertEquals(new Position(9,6),  arena.enemies.get(4).getPosition());
        assertEquals(new Position(9,15), arena.enemies.get(6).getPosition());
    }

    @Test
    public void createWalls(){
        Arena arena = new Level5();
        assertEquals(new Position(5, Globals.height-8), arena.walls.get(0).getPosition());
        assertEquals(new Position(25,Globals.height-8), arena.walls.get(1).getPosition());
        assertEquals(new Position(45,Globals.height-8), arena.walls.get(2).getPosition());
    }

    @Test
    public void changePositions() {
        Arena arena = new Level5();
        assertEquals(new Position(3,9),  arena.enemies.get(1).getPosition());
        assertEquals(new Position(9,6),  arena.enemies.get(4).getPosition());
        assertEquals(new Position(9,15), arena.enemies.get(6).getPosition());
        arena.changePositions();
        assertEquals(new Position(2,9),   arena.enemies.get(1).getPosition());
        assertEquals(new Position(10,6),  arena.enemies.get(4).getPosition());
        assertEquals(new Position(8,15),  arena.enemies.get(6).getPosition());
        //shouldn't change anything, because frame didn't change
        arena.changePositions();
        assertEquals(new Position(2,9),   arena.enemies.get(1).getPosition());
        assertEquals(new Position(10,6),  arena.enemies.get(4).getPosition());
        assertEquals(new Position(8,15),  arena.enemies.get(6).getPosition());
    }
}
