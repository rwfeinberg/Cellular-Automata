simulation
====

This project implements a cellular automata simulator.

Names: Ryan Feinberg, Muazzam Khan-Noorpuri, Kenneth Marenco

### Timeline

Start Date: 10/05/2020

Finish Date: 10/20/2020

Hours Spent: 120

### Primary Roles

Ryan Feinberg: JavaFX Boilerplate, JavaFX UI (View implementation), Language SplashScreen, RPSCell Implementation

Muazzam Khan-Noorpuri: Model Setup(abstract Cell class, Grid etc.), GameOfLifeCell, Percolation Cell, SchellingCell, Graph Implementation (Simulation side)

Kenneth Marenco: Model Setup (Neighbor features of abstract Cell), FireCell and WaTorCell implementation, View Features (Grid view), 

### Resources Used

StackOverFlow


### Running the Program

Main class: Run Main class in cellsociety to launch JavaFX Application.

Data files needed: 

* Every CSV file created inside /data/... must have a respective .properties file in src/resources/ with the **same filename**. (ex. mygrid.csv and mygrid.properties) (If directory names are different, make sure to update respective constant in Main.java)
* The CSV file should be of the format: (the grid should contain cell states 0 or 1 for GameOfLife simulations and 0, 1, or 2 for other simulations)
```
    numofrows,numofcolumns,,,,
    state,state,state...
    state,state,state...
    state,state,state...
    ...
```
* The .properties file should be of the format: (Required keys: GameType, Title, Author, Description, FileName, NeighborType, EdgePolicy)

    **FileName's value should be pathname to associated CSV. (ex. for mygrid.properties: FileName=data/GAMETYPEHERE/mygrid.csv)**
```
keyName=Value
```
* All .css stylesheet files should be placed inside src/resources/styles. 
* All language configuration files should be placed inside src/resources/configs.


Features implemented:

* Language Splash Screen: Choose language from dropdown to set Button/Dropdown menu labels to a language.
* Simulation Title and Description appear at top of window.
* Grid: View the cell states in real time, as well as click on a cell to change its state.
* Graph: Population graph shows real-time population of each cell type in the currently displayed grid over time.
* Game Dropdown Menu: Choose a simulation type in of which to choose a CSV File for in the adjacent dropdown menu.
* Grid Dropdown Menu: Choose a CSV File to display on the grid of the chosen simulation type.
* Style Dropdown Menu: Choose a .css stylesheet for the window to be displayed in.
* Randomize Button: Click to randomly set every state of cell in current grid.
* Start Button: Click to start the simulation.
* Stop Button: Click to stop the simulation from running.
* Step Button: Click to advance simulation exactly 1 frame/step forward.
* Save Button: Click to save the current grid configuration displayed to a new CSV/Properties file pair. Enter a filename, title, and author.
* Speed Slider: Drag this slider back and forth to change the simulation speed to up to 25 fps.


### Notes/Assumptions

Assumptions or Simplifications:

Interesting data files:
 * GameOfLife:
    * symmetry11_11.csv: continues endlessly with symmetric pattern
 * Percolation:
    * testPercolation.csv: demonstrates that water only flows through Cardinal neighbours (and not diagonally)
 * Schelling:
    * alternatingColumn.csv: blue moves to form a neighborhood in upper left while red nearly is able to
 * RPS:
    * RPSSwirl.csv: endlessly 'swirls' - increase FPS to see best effect
 * ForestFire:
    * Test11_11.csv: when run multiple time, different outcomes occur to demonstrate the unpredictability of the fire spreading
 * WaTor:
    * sampleTestWaTor.csv: when ran multiple times, different outcomes occur to demonstrate an ecosystem where sharks become extinct

Known Bugs:
 * Graph always has 3 lines for states even if only 2 states present (e.g. GameOfLifeCell)
 * Grid and Graph are not separate entities (ie. cannot choose which to have open)
 * Randomize Grid button does not reset graph
 * assertThrows cannot work in tests because we use try-catch code in all instances of Exceptions (responsible for lower line coverage in cellsociety package and a few model classes)

Extra credit:
 * Styling of pages - 3 types of styles


### Impressions

Ryan: I think this was a solid assignment and helped me better understand user interaction. Although I think that the amount of features required for the project was excessive, it got the point where adding more features only made the code messier and more complicated, and didn't help me learn anything new (This was the most features I've seen on a GameOfLife simulator anywhere on the internet).

Kenneth: I thought this was a very challenging yet insightful project. Towards the mid-late portion of the project window it became more and more clear where certain design principles became extremely important. I felt this project heavily involved putting new concepts to practice which was a hard but exciting experience. 

Muazzam: I really enjoyed this project as it allowed me to really gain a better appreciation for the capabilities of JavaFX. Also, it allowed me to see the benefits of separating the model and the simulation while also implementing other key design principles like inheritance, open-closed etc.

