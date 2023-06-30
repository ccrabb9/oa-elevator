package com.oa.elevator;

/**
 * Elevator:  A class representing an elevator that supports calculating travel times based upon
 * a list of floors to visit.
 */
public class Elevator {
    private static final int SECONDS_PER_FLOOR = 10;
    private static final int BOTTOM_FLOOR = 1;
    private static final int TOP_FLOOR = 50;

    public int calculateMultiTripTime(int[] floorsToVisit) throws IllegalArgumentException {
        if (floorsToVisit.length<2) {
            throw new IllegalArgumentException("At least two floor numbers must be specified when riding the elevator (a starting and ending floor)");
        }

        int totalTripSeconds = 0;
        int currentStartFloor = floorsToVisit[0];
        for (int i = 1; i<floorsToVisit.length; i++) {
            totalTripSeconds += calculateSingleTripTime(currentStartFloor, floorsToVisit[i]);
            currentStartFloor = floorsToVisit[i];
        }

        return totalTripSeconds;
    }

    public int calculateSingleTripTime(int startFloor, int endFloor) throws IllegalArgumentException {
        validateFloorNumber(startFloor);
        validateFloorNumber(endFloor);
        return Math.abs(startFloor-endFloor) * SECONDS_PER_FLOOR;
    }

    private void validateFloorNumber(int floorNumber) throws IllegalArgumentException {
        if (floorNumber<BOTTOM_FLOOR || floorNumber>TOP_FLOOR) {
            throw new IllegalArgumentException("Elevator floors must be an integer between " +
                    BOTTOM_FLOOR + " and " + TOP_FLOOR + ".");
        }
    }
}