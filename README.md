Deadline 1:

UML: Classes.jpg
Skeleton screens: Run application using `mvn clean javafx:run`
Classes without body but containing relationships, etc: Present in the top-level `classes` folder and the `src` folder.

Deadline 2:

Command to run the application: `mvn clean javafx:run`
Threading has been implemented for garbage collection, by removing objects that have to be rendered if they have moved off-screen.
It uses the ScheduledService to act as a daemon process that runs once every 2 seconds in parallel to the actual application.