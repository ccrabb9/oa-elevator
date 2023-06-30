package com.oa.elevator;

import java.util.Arrays;

public class ElevatorApplication {

    private final Elevator elevator;

    public static void main(String[] args) {
        ElevatorApplication application = new ElevatorApplication(new Elevator());
        application.rideTheElevator(args);
    }

    public ElevatorApplication(Elevator elevator) {
        this.elevator = elevator;
    }

    /**
     * Simulates an elevator trip based upon the floors to visit.  Outputs the total time in
     * seconds the ride will take (to the console) along with a list of the floors visited in
     * the order they were visited.
     *
     * @param args an array of Strings where each string represents a floor number to visit (an integer).
     */
    public void rideTheElevator(String[] args) {
        try {
            int[] floorsToVisit = parseInputs(args);
            int totalTime = elevator.calculateMultiTripTime(floorsToVisit);
            System.out.println("Elevator total travel time: " + totalTime + " seconds");
            System.out.println("Floors visited: " + Arrays.toString(floorsToVisit));
        }
        catch (IllegalArgumentException x) {
            System.out.println(x.getMessage());
        }
    }

    /**
     * Takes an array of strings and converts to an array of floor numbers.
     *
     * @param args an array of strings representing floor numbers
     * @return an array of floor numbers
     * @throws IllegalArgumentException if there are any parsing or validation issues
     */
    private int[] parseInputs(String[] args) throws IllegalArgumentException {
        try {
            return Arrays.stream(args).mapToInt(Integer::parseInt).toArray();
        }
        catch (NumberFormatException x) {
            throw new IllegalArgumentException("Floor numbers must be valid integers");
        }
    }
}
