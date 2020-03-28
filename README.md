The changes to main are including a state function to hava a menu state and a playing state. lines 44-50, if statements @87,138,227,253.
I moved some things to InputHandler to create a little bit of inheritance. 

The MainMenu class is just a panel with buttons that should start the game, show play instructions, and quit the game.  It doesn't ask for player input and such as I was going to make a sub-menu to choose colours etc. but got stuck. 
This video is how I got the idea for states based menus   https://www.youtube.com/watch?v=FZWX5WoGW00




























# Glow-Air-Hockey-Project

Our game is a computerized take on the table game air hockey.

To run the code for Demo 2, download the zip folder of the files from Github.
The main game function is GlowAirHockeyApp.java, run this file as a java application. You will then be prompted to input player and colour info into the console. From here you can view the current physics of the game. 

There is a working JUnit test for the Puck class (puckTest.java). Run using JUnit 4.

As of now, the puck is given an initial velocity and will randomly bounce around until a player wins by reaching 7 goals by chance.
Friction, and puck collisions with the edges of the game board are working however collisions with the paddles are still a work in progress. 
We currently have a working GUI using JavaFX which uses a timer to update positions of the puck and paddles, but paddle controls are yet to be implemented.
