package com.oa.elevator;

import java.util.Arrays;

/**
 * Elevator:  An elevator class that supports calculating travel times based on a list of floors to visit.
 * For requirements and assumptions made when creating this application, see the project README.md
 */
public class Elevator {
    private static final int SECONDS_PER_FLOOR = 10;
    private static final int BOTTOM_FLOOR = 1;
    private static final int TOP_FLOOR = 50;

    public static void main(String[] args) {
        Elevator elevator = new Elevator();
        elevator.ride(args);
    }

    /**
     * Simulates an elevator trip based upon the floors to visit.  Outputs the total time in seconds
     * the ride will take along with a list of the floors visited in the order they were visited.
     *
     * @param args an array of Strings where each string represents a floor number (an integer between 1 and 50).
     *             At least one floor must be specified.
     */
    private void ride(String[] args) {
        int[] floorsToVisit;
        try {
            floorsToVisit = parseAndValidateInputs(args);
            int totalTime = calculateMultiTripTime(floorsToVisit);
            System.out.println("Elevator total travel time: " + totalTime + " seconds");
            System.out.println("Floors visited: " + Arrays.toString(floorsToVisit));
        }
        catch (IllegalArgumentException x) {
            System.out.println(x.getMessage());
        }
    }

    /**
     * Takes an array of strings and converts to an array of floor numbers to visit, validating that each
     * string represents a valid floor and that at least one floor has been specified.
     *
     * @param args an array of strings representing floor numbers
     * @return an array of floor numbers
     * @throws IllegalArgumentException if there are any parsing or validation issues
     */
    protected int[] parseAndValidateInputs(String[] args) throws IllegalArgumentException {
        String validationMsg = "Elevator must be provided at least one floor number and each floor number must be a valid integer between " + BOTTOM_FLOOR + " and " + TOP_FLOOR + ", inclusive.";
        int[] floors;
        try {
            floors = Arrays.stream(args).mapToInt(Integer::parseInt).toArray();
        }
        catch (NumberFormatException x) {
            throw new IllegalArgumentException(validationMsg);
        }

        if (floors.length==0) {
            throw new IllegalArgumentException(validationMsg);
        }

        for (int floor : floors) {
            if (floor<BOTTOM_FLOOR || floor>TOP_FLOOR) {
                throw new IllegalArgumentException(validationMsg);
            }
        }
        return floors;
    }

    protected int calculateMultiTripTime(int[] floorsToVisit) {
        if (floorsToVisit.length<=1) return 0;

        int totalTripSeconds = 0;
        int currentStartFloor = floorsToVisit[0];
        for (int i = 1; i<floorsToVisit.length; i++) {
            totalTripSeconds += calculateSingleTripTime(currentStartFloor, floorsToVisit[i]);
            currentStartFloor = floorsToVisit[i];
        }

        return totalTripSeconds;
    }

    protected int calculateSingleTripTime(int startFloor, int endFloor) {
        return Math.abs(startFloor-endFloor) * SECONDS_PER_FLOOR;
    }
}