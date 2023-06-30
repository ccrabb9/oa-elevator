package com.oa.elevator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElevatorTest {
    Elevator elevator;

    @BeforeEach
    void setUp() {
        elevator = new Elevator();
    }

    @ParameterizedTest
    @MethodSource("calculateSingleTripTime_success_arguments")
    public void test_calculateSingleTripTime_success(int startFloor, int endFloor, int expectedSeconds) {
        int seconds = elevator.calculateSingleTripTime(startFloor, endFloor);
        assertEquals(expectedSeconds, seconds);
    }

    private static Stream<Arguments> calculateSingleTripTime_success_arguments() {
        return Stream.of(
            Arguments.of(1, 1, 0),
            Arguments.of(1, 2, 10),
            Arguments.of(1, 3, 20),
            Arguments.of(1, 4, 30),
            Arguments.of(1, 50, 490),
            Arguments.of(50, 1, 490),
            Arguments.of(20, 18, 20)
        );
    }

    @ParameterizedTest
    @MethodSource("calculateSingleTripTime_failure_arguments")
    public void test_calculateSingleTripTime_failure(int startFloor, int endFloor) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> elevator.calculateSingleTripTime(startFloor, endFloor));
    }

    private static Stream<Arguments> calculateSingleTripTime_failure_arguments() {
        return Stream.of(
            Arguments.of(1, 51),
            Arguments.of(0, 50),
            Arguments.of(0, 51),
            Arguments.of(1, -3),
            Arguments.of(1, 0),
            Arguments.of(51, 30)
        );
    }

    @ParameterizedTest
    @MethodSource("calculateMultiTripTime_success_arguments")
    public void test_calculateMultiTripTime_success(int[] floorsToVisit, int expectedSeconds) {
        int seconds = elevator.calculateMultiTripTime(floorsToVisit);
        assertEquals(expectedSeconds, seconds);
    }

    private static Stream<Arguments> calculateMultiTripTime_success_arguments() {
        return Stream.of(
            Arguments.of(new int[] { 1, 1 }, 0),
            Arguments.of(new int[] { 1, 1, 1 }, 0),
            Arguments.of(new int[] { 1, 2 }, 10),
            Arguments.of(new int[] { 2, 1 }, 10),
            Arguments.of(new int[] { 12, 2, 9, 1, 32 }, 560),
            Arguments.of(new int[] { 12, 2, 9, 1, 1, 32 }, 560),
            Arguments.of(new int[] { 1, 2, 9, 1, 32 }, 470)
        );
    }

    @ParameterizedTest
    @MethodSource("calculateMultiTripTime_failure_arguments")
    public void test_calculateMultiTripTime_failure(int[] floorsToVisit) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> elevator.calculateMultiTripTime(floorsToVisit));
    }

    private static Stream<Arguments> calculateMultiTripTime_failure_arguments() {
        return Stream.of(
            Arguments.of((Object) new int[] {}),
            Arguments.of((Object) new int[] { 1 }),
            Arguments.of((Object) new int[] { 0 }),
            Arguments.of((Object) new int[] { 1, -1 }),
            Arguments.of((Object) new int[] { 1, 1, 51 }),
            Arguments.of((Object) new int[] { -31, 2 }),
            Arguments.of((Object) new int[] { 12, 0, 9, 1, 32 }),
            Arguments.of((Object) new int[] { 12, 2, 9, 1, 64, 32 }),
            Arguments.of((Object) new int[] { 1, -2, 9, 1, 32 })
        );
    }
}
