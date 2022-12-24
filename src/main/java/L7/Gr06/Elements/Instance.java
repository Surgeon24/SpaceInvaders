package L7.Gr06.Elements;

import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Instance {
    private Position position;

    protected Instance(Position pos) {
        position = pos;
    }
    public Position getPosition(){
        return position;
    }
    public void setPosition(Position pos){ this.position = pos;}
    public int getX() {
        return position.getX();
    }
    public int getY() {
        return position.getY();
    }
    public void setX(int newX) {
        position.setX(newX);
    }
    public void setY(int newY) {
        position.setY(newY);
    }
    public boolean collide(Position object){
        return  (getX() == object.getX() && getY() == object.getY());
    }
    public void draw(TextGraphics graphics){}
}
