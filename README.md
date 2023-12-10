Deadline 1:

UML: Classes.jpg
Skeleton screens: Run application using `mvn clean javafx:run`
Classes without body but containing relationships, etc: Present in the top-level `classes` folder and the `src` folder.

Deadline 2:

Command to run the application: `mvn clean javafx:run`
Threading has been implemented for garbage collection, by removing objects that have to be rendered if they have moved off-screen.
It uses the ScheduledService to act as a daemon process that runs once every 2 seconds in parallel to the actual application.

For JUnit testing, the following command can be used: `mvn clean test`
Apart from the com.example.stickhero.spritesheet.CustomAnimationTimerTest, all the other tests incorporate the use of TestFX to create test the functionalities within the GUI.
The CollisionTimerTest checks for the collision between two objects and the type of collision as well (complete overlap, partial overlap, no overlap, on edge overlap).
The CanMove, CanRotate and CanScale tests check for the movement, rotation and scaling of the objects respectively.