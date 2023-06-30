# Elevator

A coding project for discussion.

## Author
Curtis Crabb

## Requirements

* Provide code that simulates an elevator. You are free to use any language.
* Upload the completed project to GitHub for discussion during interview.
* Document all assumptions and any features that weren’t implemented.
* The result should be an executable, or script, that can be run with the following inputs and generate the following outputs.
  * Inputs: [list of floors to visit] (e.g. elevator start=12 floor=2,9,1,32)
  * Outputs: [total travel time, floors visited in order] (e.g. 560 12,2,9,1,32)
  * Program Constants: Single floor travel time: 10

## Assumptions

There are a lot of assumptions being made based on reading the requirement.  Typically, these questions and assumptions would be reviewed with the product owner and/or customer to hash out more specific requirements.  In this case, I believe it made most sense to make these assumptions and document them below:

* Bottom floor is floor #1 (no underground floors)
* Top floor is floor #50
* Floor numbers correlate to the number above floor #1.  For example, floor 13 was not skipped because someone didn’t want to have an “unlucky” floor.
* Input requires at least two floors to be specified (starting and ending floor), and all floors specified must be valid integers between 1 and 50 inclusive.
* Any invalid inputs result in an error message
* Travel time is in seconds
* The elevator starts at the initial floor specified in our input.  There is no need to wait for it to come from some other floor before travel begins.
* No additional complications are introduced by door opening/closing times or acceleration and deceleration of speeds at beginning/ending of trip (i.e. traveling 3 floors takes exactly 3x the amount of time to travel 1 floor).
* The "floors to visit" input is in the order in which floors should be visited.  We are NOT trying to make the most efficient travel order to get to each floor.
* The exact format and method of inputs/outputs is not critical.  Here, I use command line arguments for the inputs and I write outputs to the console. 
* If inputs include the same floor referenced twice in a row, no travel time is added for that part of the trip.
* Assuming nothing specific is being required by the phrase "simulates an elevator".  Does that imply I should have a stateful object (i.e. an Elevator) that knows the current floor and has methods such as moveToFloor(int floorNumber)?  For now, I thought that was overkill and was not necessary for the mentioned inputs and outputs.  

## Unit Tests

To run unit tests, run the following in the project root directory:

> ./gradlew test

## Running

To run the program with the default parameters, you can do so by running the following in the project root directory:

> ./run.sh

To run via gradle with parameters (which starts on floor 12 and goes to 2, 9, 1, 32):

> ./gradlew run --args "12 2 9 1 32"