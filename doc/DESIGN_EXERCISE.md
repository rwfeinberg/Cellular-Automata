# Simulation Lab Discussion
## Muazzam Khan Noorpuri(mk412), Ryan Feinberg(rwf8), Kenneth Marenco(kem78)


## Rock Paper Scissors

### High Level Design Ideas


### CRC Card Classes

This class's purpose or value is to create weapons
```java
 public abstract class Weapon{
     private Weapon loseTo;
     private Weapon winTo;
 }
```

This class's purpose or value is to manage the Rock weapon:
```java
 public class Rock extends Weapon{
     private Weapon loseTo = Paper;
     private Weapon winTo = Scissor;
 }
```
^This is the same for all three weapons

This class's purpose or value is to hold Players' info:
```java
 public class Player {
     private int score;
     private Weapon chosenWeapon;
     private int id;
 }
```

### Use Cases

### Use Cases

 * A new game is started with five players, their scores are reset to 0.
 ```java
 for([player : Players]){
    player.setScore(0);
 }
 ```

 * A player chooses his RPS "weapon" with which he wants to play for this round.
 ```java
 Weapon weaponChoice = input;
 player.setWeapon(weaponChoice);
 ```

 * Given three players' choices, one player wins the round, and their scores are updated.
 ```java
 Player winner = getWinner(Players);
 winner.updateScore(1);

 ```

 * A new choice is added to an existing game and its relationship to all the other choices is updated.
 ```java
 public class newChoice extends Weapon{
     private Weapon loseTo = Paper;
     private Weapon winTo = Scissor;
 }
 ```

 * A new game is added to the system, with its own relationships for all its "weapons".
 ```java
 List[] Weapon = {Rock, Paper, Scissor, Other ....}
 ```


## Cell Society

### High Level Design Ideas
* Commonalities: 
    * Grid Format
    * Each Cell has finite choices for state (e.g. on/off etc)
    * Interaction with other cells
    * Cells ability to update state

Differences:
    * Number of states
    * Rules to update state based on neighbours
    * Size

### CRC Card Classes

This class's purpose or value is to manage something:
```java
public class Something {
    public int getTotal (Collection<Integer> data)
    public Value getValue ()
}
```

This class's purpose or value is to be useful:
```java
public class Value {
    public void update (int data)
}
```

### Use Cases

* Apply the rules to a cell: set the next state of a cell to dead by counting its number of neighbors using the Game of Life rules for a cell in the middle (i.e., with all of its neighbors)
```java
Something thing = new Something();
Value v = thing.getValue();
v.update(13);
```

* Move to the next generation: update all cells in a simulation from their current state to their next state
```java
Something thing = new Something();
Value v = thing.getValue();
v.update(13);
```

* Switch simulations: load a new simulation from a data file, replacing the current running simulation with the newly loaded one
```java
Something thing = new Something();
Value v = thing.getValue();
v.update(13);
```
