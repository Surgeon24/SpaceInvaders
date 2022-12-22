package L7.Gr06.Elements;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeroTest {

    @Test
    public void shootTest(){
        // arrange
        final Position pos = new Position(0, 0);
        final Position pos2 = new Position(0, -2);
        Hero hero = new Hero(pos);
        Bullet expectedShot = new Bullet(pos2, -1);
        // Act
        hero.shoot();
        List<Bullet> lst = hero.getShots();
        // Assert
        assertEquals(lst.get(0), expectedShot);
    }

    // ???
    public void collide(){ }

}
