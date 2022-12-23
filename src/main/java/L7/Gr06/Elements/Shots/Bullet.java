package L7.Gr06.Elements.Shots;

import L7.Gr06.Common.Globals;
import L7.Gr06.Elements.Instance;
import L7.Gr06.Elements.Position;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Bullet extends Instance {
    int direction;
    public Bullet(Position pos, int direction) {
        super(pos);
        this.direction = direction;
    }
    @Override
    public void draw(TextGraphics s){
        s.setBackgroundColor(TextColor.Factory.fromString(Globals.bgColor));
        s.setForegroundColor(TextColor.Factory.fromString(Globals.textColor));
        s.enableModifiers(SGR.BOLD);
        s.putString(new TerminalPosition(getX(), getY()), "i");
        setY(getY()+direction);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Bullet other = (Bullet) obj;

        return this.getX() == other.getX() && this.getY() == other.getY() && this.direction == other.direction;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * (hash + this.getX() + this.getY() + this.direction) / 11;
        return hash;
    }
}
