##  Space Invaders 

Space Invaders is a text based arcade game,
that make a reference to the game series of
the same title, taking best moment from those games
and adapting it to modern realities.

This project was developed by *Mikhail Ermolaev* and *David Burchakov* for LDTS 2022⁄2023.

### IMPLEMENTED FEATURES

- **Moving** - The player can move the game character (hereinafter referred to as GC) 
left and right by pressing *A* and *D* buttons.
- **Shooting** - The GC will shoot when the *space* is pressed.
- **Killing enemies** - When the GC's bullet hits enemy, both of them
(bullet and enemy) disappearing.
- **Destroying walls** - When the bullet hits wall, bullet disappears
and wall loses its strength. After losing all the strength wall 
also disappears.

### PLANNED FEATURES

- **Point Counter** - By killing invaders, player should gain points
that will show progress in the game.
- **Bear arms** - Not only GC should have the right to have gun.
Enemy should also be able to shoot.
- **Sometimes you lose** - If enemy reaches GC or shoots him
the Game Over screen should appear.


### DESIGN

#### The GC should be able to move and shoot at the same time
**Problem in Context**

As the project became more complex, more and more classes 
began to appear that could be combined in one word - elements.
To make it easier to work with these classes, and also to 
ensure unification, we could use a generative design pattern.

**The Pattern**

We have applied the Prototype pattern.This prototype makes it 
easier to create new elements.

**Implementation**

**Consequences**

----

#### KNOWN CODE SMELLS AND REFACTORING SUGGESTIONS

1st code smell:

before:

public boolean collide(Position object){
    int obX = object.getX();
    int obY = object.getY();
    int enX = this.getX();
    int enY = this.getY();
    if ((enX <= obX && enX+2 >= obX) && (enY <= obY && enY+2 >= obY))
        return true;
    return false;
}

after:

public boolean collide(Position object){
    return  (getX() <= object.getX() && getX() + 2 >= object.getX()) &&
        (getY() <= object.getY() && getY() + 2 >= object.getY());
}


### TESTING

- Screenshot of coverage report.
- Link to mutation testing report.

### SELF-EVALUATION

- Mikhail Ermolaev: %
- David Burchakov: %

