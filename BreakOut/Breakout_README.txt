Autumn Barker (Carter)
cs251 - Fall 2014 Section 5
Breakout Game Lab

To Launch game run:
java -jar Breakout.jar

Game Play:
-Controls: The left and right arrow keys will control the paddle movement.
-Scoring: For every brick hit required you get 10 points.  If a brick only requires one hit, you will get a max of 10 points when the brick disappears.  If the brick requires 3 hits, 30 points will be added to the score when the brick disappears.

Description of program internals:
Classes:
-Data classes include the Game Object under which the Ball, Paddle, and Bricks reside.  There is also a Player class that holds the player’s score, lives, and level.
-There is a Breakout class that houses main and instantiates most things and then passes them to the game view class that has most of the game logic and display

Algorithm details:
-Ball movement was taken from the class demo files (Timer Demo)
-Paddle movement uses key bindings because I could not get the key listener to work.
-Collision detection reused the detection created in Part 1 of this assignment 
-End of game detection happens either when the players loses all of his/her 5 lives or they have successfully made it through the 5 levels.  A pop-up will appear when either of those conditions are met letting you know whether you lost or won.
-When the number of bricks in each level equals 0 it will replace the ball, paddle, and create the next level.
-There are 5 built in levels, however, if you load a level through a command line argument, it will take the place of the first built in level, so that there will still only be 5 levels to win.
-The built in level csv’s will be listed at the end for reference (that way you know a brick wasn’t disappearing because the hit requirement wasn’t met vs. faulty brick destruction logic.

Known bugs:
-If the ball hits the paddle just right (I think at the corner) it will appear to roll along the paddle till it gets to the other side instead of bouncing off of it.  I didn’t have time to investigate this.  I’m assuming it has something to do with the corner logic

-If I had more time I would have liked to implement a way to tell how many hits were required on each brick.  Even something as simple as a number that decremented with each hit but I never got that far.


Levels:
Level 1-
7,10
,,,,,,,,,
1,,,,,,,,,1
1,1,,,,,,,1,1
,1,1,,,,,1,1,
,,1,1,,,1,1,,
,,,2,1,1,2,,,
,,,,2,2,,,,

Level 2-
7,10
,,,,,,,,,,
,3,3,,,,,3,3
,3,3,,,,,3,3
,,,,3,3,,,,
2,2,,,,,,,2,2,,
,2,2,,,,,2,2,,,
,,,2,2,2,2,,,

Level 3-
8,10
,1,,,,,,,1,,
,,1,,,,,1,,,
,,,1,,,1,,,,
,,,,1,1,,,,,
,,,,1,1,,,,,
,,,1,,,1,,,,
,,1,,,,,1,,,
,1,,,,,,,1,,

Level 4-
8,10
,,,,,,,,,,
,1,,1,,1,1,1,,1,
,1,,1,,,1,,,1,
,1,1,1,,,1,,,1,
,1,,1,,,1,,,1,
,1,,1,,,1,,,,
,1,,1,,1,1,1,,1,
,,,,,,,,,,

Level 5-
5,10
5,5,5,5,5,5,5,5,5,5
5,5,5,5,5,5,5,5,5,5
5,5,5,5,5,5,5,5,5,5
5,5,5,5,5,5,5,5,5,5
5,5,5,5,5,5,5,5,5,5


