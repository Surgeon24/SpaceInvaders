package elements;

/*
    Generic instance
 */
public class Instance {
    private Position position;
    private String color;

    //constructor
    Instance(Position pos) {
        position = pos;
    }
    //getters and setters
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
}
