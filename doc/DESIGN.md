# Simulation Design Final
### Kenneth Marenco, Muazzam Khan-Noorpuri, Ryan Feinberg

## Team Roles and Responsibilities

 * Team Member #1: Kenneth

My main role of the team was to implement Model portions of simulation. I implemented the FireCell and its test csv file as well as the corresponding property file. This is the same case with the WaTorCell. I also worked on incorporating different features for the project such modifying the model classes to accomodated the change how neighbors where made. The same case is for implementing the EdgePolicy framework. 

 * Team Member #2: Muazzam

 My main roles in this project were a balance of the model and view elements of the project. Firstly, I was responsibly for the setup of the model side, laying out the Grid and creating the abstract Cell class, from which the simuation cells are based off. From here I created the cells for 3 simulations (GameOfLife, Percolation & Schelling.) On the view side, I worked on implementing the Population Graph that runs alongside the visual simulation. I also implemented the visual version of each cell (essentially a link between a JavaFX Rectangle and a simulation cell from the model.) Also helped with some visual components like the creation of the Randomize button.

 * Team Member #3: Ryan 

 My main role on this team was to implement the View portion of the project. Specifically, the  JavaFX Boilerplate, JavaFX UI (ControlPanel, buttons/menus/titles/styles), and the Language SplashScreen. I also implemented the Rock, Paper, Scissors Cell subclass, helped out on the Population Graph and SimulationView in general, and did a bit of testing (we split mostly evenly?).

## Design goals

#### What Features are Easy to Add

* New Simulation (Cell) Types
* New Buttons/Menus

## High-level Design

#### Core Classes
Cell: The fundamental model that all other cell types are based off. It serves to contain all the necessary information that would be needed by all cells to update, display correctly, and intereact with other cells.

Grid: This model serves to hold all cells where they have structure and it is how most features communicate with the individual cells. It takes the desired information from given property files to accomplish the user's desired simulation setup.

Grid Setup: This class serves to give grid all the necessary information it needs to complete its task. It is where grid gets its initial setup with the desired cells.

CellView: This view serves to give the model cell a means to visualize on the display. Using JavaFX shapes, it provided a means to separate the model and view components of the cell by having the view hold an instance of the model via our linked cell.

SimulationView: This view class serves the most important role in visualizing the information being communicated by the cells over several chronos. This class allows both the grid and the graph to be displayed as well as the other features setting up the scene and adding these components to the root.

GridView: This view serves to give the model grid a mean to appear on screen for the user by taking in the model's information about the shape and number of cells it was able to provide structure and spacing between the CellViews.


## Assumptions that Affect the Design

#### Features Affected by Assumptions

* We assume that every csv file that is being used for simulation will have a corresponding property file of the same name in the resources package of the source folder. 

* It is assumed that all the property files will contain a valid neighborType and EdgePolicy value. There is currently no default if either of those keys are left empty.

* We assume that any cell models contain at max 3 different states. The graph created in SimulationView has 3 curves that will match to the 3 cell states if there are 3 different types.

## Significant differences from Original Plan

* The final version implements SimulationView which is a part of the view components rather than being a high level class of the model components as originally planned.

* One of the original plans when simulation was planned to be the high level model class was for the step function that allows time to pass be an included feature. However, the final version has that functionaity inside the Main class. 

* The original plan for the project did not take into account the idea of using property files to communicate necessary information needed by the cell and grid.


## New Features HowTo

#### Easy to Add Features

* New Simulation (Cell) Types
* New Buttons/Menus

#### Other Features not yet Done

* The framework for the edgePolicy feature was implemented for the finite edge case, but the extra 2 policies were not implemented.

    * Because the majority of the framework is already implemented and is functioning for the finite edge policy case, the final steps would be to implement the rules for how the neighbors would be added for the toroidal and klein bottle edge policy cases in the Cell class. Another feature that would also need to be included is a default and exception for when the property files does not contain a key or an invalid key.

* The graph from SimulationView have curves that does state which cell state it is tracking.
    * The states of the cell would be placed in the property files to keep better track of that information and have the constructor of the simulation view pass that information through to be used. To also get the colors of the curves to match the cell state color scheme it would have the property file also include that information.

* The simulation cannot be dynamically change the color scheme for the cell's states. 
    * In order to implement this, the colors for each cell state would somehow have to be associated with the state in the .properties file. An example may be: ```cellstate0name=EMPTY``` and the next line may be: ```cellstate0color=BLACK``` or something similar. This way, we can then simply change the properties file dynamically in some sort of dropdown/dialog, and then refresh the visuals.

* The simulation cannot replace the color scheme of the cell's states with images.
    * Implementing this feature would be done in a very similar way as described above, except this time, instead of ```cellstate0color=BLACK``` we would have something along the lines of ```cellstate0img=logo.png```. Then, inside the dialog, instead of performing a setFill, we would set the background to the image chosen.

