# WALL-E Robot
## Context
Pixar puts you in charge of creating a well-designed, well-documented software to write programs for a robot. That robot is WALL-E (from the movie of the same name) and is responsible for compacting trash into cubes to help clean the Earth of its garbage.

## Robot
WALL-E can move forward a defined distance on the ground. WALL-E can also turn clockwise by a certain number of degrees. Specifying a negative number of degrees results in WALL-E turning anti-clockwise by that number of degrees. WALL-E has one arm that it can extend and retract. At the end of the arm is a gripper that can open and close in order to hold onto an object. Finally, WALL-E has a compactor used to reduce trash to cubes. The compactor can compact up to ten objects before it needs to be emptied. WALL-E is powered by a single battery with a maximum charge of 100 units.

## Basic Needs
1. Start by creating abstractions for an action and a program. A program is a sequence of steps that the robot should perform. After being created, a program is transferred to the robot and executed autonomously (i.e., without additional client code). A program is composed of a sequence of actions, which represent the steps to perform. A single action can involve manipulating several motors of the robot. Client code creates programs by assembling the actions in a sequence.

### Actions
2. Create the following set of basic actions. These basic actions are the fundamental building blocks for creating programs, and clients are not expected to have to create new basic actions:

(a)Move forward some distance: ensure the arm is retracted, then move forward

(b)Turn 90 degrees to the right or left: ensure the arm is retracted, then turn clockwise or anti-clockwise

(c)Grab an object: extend the arm, close the gripper, retract the arm

(d)Release an object: ensure the arm is retracted, then open the gripper

(e)Compact an object: ensure the gripper holds an object, then compact the object

(f)Empty the compactor: empty the content of the compactor


3. Enforce that the execution of every basic action follows the same protocol:

(a)First check the state of the battery

(b)If the charge of the battery is <= 5 units, then recharge the battery

(c)Perform the action and update the battery level

4. Provide a way for clients to design their own complex actions that perform a sequence of basic actions. For example, one such complex action could be to "move backward some distance" (WALL-E can not move in reverse, hence to move backward, he would first need to turn backward), and another could be to "grab and compact ten objects, then empty the compactor".

5. Provide a way for clients to add a new action that will force the battery to be recharged before execution the action, regardless of the current charge.

### Programs
6. Allow clients to create and edit programs. It should also be possible to execute the entire program (i.e., execute each action within it).

7. Violating the preconditions of the Robot interface can damage the robot. Design your software to prevent the execution of actions that would violate the preconditions of the Robot interface.

8. Implement a way for clients to perform computations related to the actions of a program. For examples of computations that can be performed: given a program, clients should be able to calculate the total distance WALL-E will move, and the total number of objects that will be compacted, without actually executing the program. Make sure to consider complex actions composed of other actions, and use polymorphism effectively.

9. Create a logging system that allows the Pixar development team (including you) to keep track of all the actions performed by the client. Every time an action is performed, log the statement "X action performed, battery level is Y" (replacing X with the type of action performed and Y with the updated battery level). Design this feature to avoid unnecessary coupling between the program and actions and the logging system, and to ensure that the way a statement is logged can be easily changed in the future (e.g., printing to the console or writing to a file).
