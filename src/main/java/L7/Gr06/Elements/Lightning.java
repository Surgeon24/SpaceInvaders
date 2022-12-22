package L7.Gr06.Elements;

import L7.Gr06.Common.Globals;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Objects;

public class Lightning extends Bullet {
    Integer startOfTheLighting;
    public Lightning(Position pos, int direction) {
        super(new Position(pos.getX(), Globals.height-1), direction);
        startOfTheLighting = pos.getY();
    }
    public void setStartOfTheLighting(Integer x) {startOfTheLighting = x;}
    public Integer getStartOfTheLighting() {return startOfTheLighting;}
    @Override
    public void draw(TextGraphics s){
        s.setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        //s.setForegroundColor(TextColor.Factory.fromString(Globals.textColor));
        s.enableModifiers(SGR.BOLD);
        for (int i= startOfTheLighting; i < Globals.height; i++){
            if (i % 2 == 0)
                s.putString(new TerminalPosition(getX(), i), "_");
            else
                s.putString(new TerminalPosition(getX(), i), "`");
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Lightning other = (Lightning) obj;

        return this.getX() == other.getX() && this.getY() == other.getY() && this.direction == other.direction
                && Objects.equals(this.startOfTheLighting, other.startOfTheLighting);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * (hash + this.getX() + this.getY() + this.direction + this.startOfTheLighting) / 11;
        return hash;
    }
}
