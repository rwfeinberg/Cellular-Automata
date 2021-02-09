## Lab Discussion
### Team 18
### Muazzam Khan Noorpuri(mk412), Ryan Feinberg(rwf8), Kenneth Marenco(kem78)


### Issues in Current Code

#### CheckDimensions Method
 * 32 lines long

#### Control Panel Class
 * Intra Line Duplication in creating csv file
 * Combine two 'for' loops Line 244 and 235

#### Cell Classes
 * Find better way to implement different kinds of neighborhoods
 * Currently each simulation's cell overrides findNeighbors to choose neighborhood

#### findNeighbors in Cell classes
 * Too complex, reduce number of indented for and if statements

#### Exception handlers should preserve original exceptions
 * All errors on catch statements 
 * Should not printStackTrace

#### Simplify/break down if statements and return statement in Cell

#### Fire Cell, PercolationCell and SimulationView
 * For those variable only used locally, remove as instance variables and convert to local variables.
 


### Refactoring Plan

 * What are the code's biggest issues?
    * findNeighbors needs to be unique per simulation if not the default neighborhood.
    * cases used to makeCell - reflection could be used here
    * Should not pass in List<List<Cell>> etc as a parameter. Pass in entire Grid instead to incorporate encapsulation

 * Which issues are easy to fix and which are hard?
    * Easy:
      * Complex methods
      * Local variables instead instances
      * Duplication in control Panel
    * Medium:
      * Changing List inputs to objects such as List<List<Cell>> to Grid
    * Hard:
      * Redoing the findNeighbors process
      * Reflection for cases in makeCell


 * What are good ways to implement the changes "in place"?
    * Using IntelliJ tools:
      * Extracting/Refactoring methods

    * Doing one change at a time and checking 

### Refactoring Work

 * Issue chosen: CheckDimensions method length
    * 2 helper methods added to divide work (1 to check columns and 1 to check rows)

 * Issue chosen: Duplication in control Panel
    * First for loop reduced to only not be repeated. Checked that both for loop are indeed required.

 * Fixed certain instance variables in FireCell and WaTor Cell
