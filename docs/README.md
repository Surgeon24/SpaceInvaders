##  Space Invaders 

Space Invaders is a text based arcade game,
that make a reference to the game series of
the same title, taking best moment from those games
and adapting it to modern realities.

This project was developed by *Mikhail Ermolaev* and *David Burchakov* for LDTS 2022⁄2023.

### IMPLEMENTED FEATURES

All implemented classes and their relations are shown on the diagram:\
![UML diagram](/docs/images/SpaceInvadersDiagram.jpeg "UML diagram")

- **Moving** - The player can move the game character (hereinafter referred to as GC) 
left and right by pressing *A* and *D* buttons.
- **Shooting** - The GC will shoot when the *space* is pressed.
- **Killing enemies** - When the GC's bullet hits enemy, both of them
(bullet and enemy) disappearing.
- **Point Counter** - By killing invaders, player gains points
that will show progress in the game.
- **Bear arms** - Enemy are able to shoot.
- **Sometimes you lose** - If enemy reaches GC or shoots him
the Game Over screen appears.
- **Levels difficulty** - Next level is more difficult than the previous, and the difficulty is balanced. The game is not too easy and not too hard to play.
- **Complex enemies** - different types of enemies are introduced.
- **Destroying walls** - When the bullet hits wall, bullet disappears
and wall loses its strength. After losing all the strength wall 
also disappears.
- **Upgrade spaceship** - During the game, player can go to the upgrade menu (using TAB) and spend points
to upgrade the GC.

![screenshot1](/docs/images/screenshot1.png "main menu")
![screenshot2](/docs/images/screenshot2.png "gameplay")
![screenshot3](/docs/images/screenshot3.png "upgrade menu")

------

### DESIGN

#### Creating and managing plenty of instances
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
- [Instance](https://github.com/FEUP-LDTS-2022/project-l07gr06/blob/master/src/main/java/L7/Gr06/Elements/Instance.java)
- [Hero](https://github.com/FEUP-LDTS-2022/project-l07gr06/blob/master/src/main/java/L7/Gr06/Elements/Hero.java)
- [Enemy](https://github.com/FEUP-LDTS-2022/project-l07gr06/blob/master/src/main/java/L7/Gr06/Elements/Enemies/Enemy.java)

Another using of this pattern is between  Arena (Prototype) and Levels (objects)\
These classes can be found in the following files:


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
![codeSmell1a](/docs/images/codeSmell1a.png "1st code smell (before)")

code after:
![codeSmell1b](/docs/images/codeSmell1b.png "1st code smell (after)")


### TESTING

Testing with coverage shows us solid results, but not perfect. Mostly because
there are many primitive getters and setters that weren't tested,
as well as constructors, music libraries that are difficult to test.

![Test coverage](/docs/images/testResults.png "Test with coverage")



### SELF-EVALUATION

- Mikhail Ermolaev: still in progress%
- David Burchakov:  still in progress%

