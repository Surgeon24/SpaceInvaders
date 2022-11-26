##  Space Invaders 

Space Invaders is a text based arcade game,
that make a reference to the game series of
the same title, taking best moment from those games
and adapting it to modern realities.

This project was developed by *Mikhail Ermolaev* and *David Burchakov* for LDTS 2022⁄2023.

### IMPLEMENTED FEATURES

All implemented classes and their relations are shown on the diagram:
![UML diagram](/docs/images/SpaceInvadersClassDiagram.jpg "UML diagram")

- **Moving** - The player can move the game character (hereinafter referred to as GC) 
left and right by pressing *A* and *D* buttons.
- **Shooting** - The GC will shoot when the *space* is pressed.
- **Killing enemies** - When the GC's bullet hits enemy, both of them
(bullet and enemy) disappearing.
- **Destroying walls** - When the bullet hits wall, bullet disappears
and wall loses its strength. After losing all the strength wall 
also disappears.
![implemented features in mid delivery](/docs/images/midDeliveryImplemented.png "implemented features in mid delivery")
### PLANNED FEATURES

- **Point Counter** - By killing invaders, player should gain points
that will show progress in the game.
- **Bear arms** - Not only GC should have the right to have gun.
Enemy should also be able to shoot.
- **Sometimes you lose** - If enemy reaches GC or shoots him
the Game Over screen should appear.
![planed features in mid delivery](/docs/images/midDeliveryPlaned.png "planed features in mid delivery")
------

### DESIGN

#### The GC should be able to move and shoot at the same time
**Problem in Context**

As the project became more complex, more and more classes 
began to appear that could be united by one concept - elements.
To make it easier to work with these classes, and also to 
ensure unification, we could use a generative design pattern.

**The Pattern**

We have applied the Prototype pattern, which is used when we have an instance 
of the class (prototype) and we'd like to create new objects by just copying 
the prototype.

**Implementation**

UML, that was represented above shows dependencies between Prototype (Instance)
and its objects (Enemy, Hero, Wall..)\
These classes can be found in the following files:
- [Instance](src/main/java/L7/Gr06/elements/Instance.java)
- [Hero](src/main/java/L7/Gr06/elements/Hero.java)
- [Enemy](src/main/java/L7/Gr06/elements/Enemy.java)

**Consequences**

The use of the Prototype Pattern in the current design allows the following benefits:
- We don't need to create plenty of methods, that saves our time.
- We use one code in many places, witch is a better way to write code
- We make program more stable, by overriding prototype's methods,  
also allowing testing our program without forcing to implement those methods.

#### KNOWN CODE SMELLS AND REFACTORING SUGGESTIONS

1st code smell:

Checking collision of two objects in a 2D game shouldn't be a hard function.\
Creating a lot of temporary variables helped to avoid confusion in syntax while 
creating a function, but now it looks too messy.\
code before:
![codeSmell1a](/docs/images/codeSmell1a.jpg "1st code smell (before)")

code after:
![codeSmell1b](/docs/images/codeSmell1b.jpg "1st code smell (after)")


### TESTING

Testing with coverage shows us weak results, mostly because
there are many primitive getters and setters that weren't tested,
also as constructors.\
(more tests will be implemented in future updates, 
also TDD methodology will be represented)
![Test coverage](/docs/images/coverage.jpg "Test with coverage")



### SELF-EVALUATION

- Mikhail Ermolaev: still in progress%
- David Burchakov:  still in progress%

