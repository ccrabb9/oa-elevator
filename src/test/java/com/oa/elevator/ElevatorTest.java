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
    @MethodSource("parseAndValidateInputs_arguments")
    public void test_parseAndValidateInputs(String[] inputs, int[] expectedValues) {
        int[] values = elevator.parseAndValidateInputs(inputs);
        assertArrayEquals(expectedValues, values);
    }

    private static Stream<Arguments> parseAndValidateInputs_arguments() {
        return Stream.of(
            Arguments.of(new String[] {"1", "12"}, new int[] {1, 12}),
            Arguments.of(new String[] {"1", "50"}, new int[] {1, 50}),
            Arguments.of(new String[] {"12", "2", "9", "1", "32"}, new int[] {12, 2, 9, 1, 32})
        );
    }

    @ParameterizedTest
    @MethodSource("parseAndValidateInputs_exception_arguments")
    public void test_parseAndValidateInputs_exception(String[] inputs) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> elevator.parseAndValidateInputs(inputs));
    }

    private static Stream<Arguments> parseAndValidateInputs_exception_arguments() {
        return Stream.of(
            Arguments.of((Object) new String[] {}),
            Arguments.of((Object) new String[] {"1", "e12"}),
            Arguments.of((Object) new String[] {"1", "12", "4e"}),
            Arguments.of((Object) new String[] {"1", "12.2"}),
            Arguments.of((Object) new String[] {"1", "51"}),
            Arguments.of((Object) new String[] {"0", "2"}),
            Arguments.of((Object) new String[] {"-1", "2"}),
            Arguments.of((Object) new String[] {"-1.3", "2"})
        );
    }

    @ParameterizedTest
    @MethodSource("calculateSingleTripTime_arguments")
    public void test_calculateSingleTripTime(int startFloor, int endFloor, int expectedSeconds) {
        int seconds = elevator.calculateSingleTripTime(startFloor, endFloor);
        assertEquals(expectedSeconds, seconds);
    }

    private static Stream<Arguments> calculateSingleTripTime_arguments() {
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
    @MethodSource("calculateMultiTripTime_arguments")
    public void test_calculateMultiTripTime(int[] floorsToVisit, int expectedSeconds) {
        int seconds = elevator.calculateMultiTripTime(floorsToVisit);
        assertEquals(expectedSeconds, seconds);
    }

    private static Stream<Arguments> calculateMultiTripTime_arguments() {
        return Stream.of(
            Arguments.of(new int[] { 1 }, 0),
            Arguments.of(new int[] { 1,1 }, 0),
            Arguments.of(new int[] { 1, 1, 1 }, 0),
            Arguments.of(new int[] { 1, 2 }, 10),
            Arguments.of(new int[] { 12, 2, 9, 1, 32 }, 560),
            Arguments.of(new int[] { 12, 2, 9, 1, 1, 32 }, 560),
            Arguments.of(new int[] { 1, 2, 9, 1, 32 }, 470)
        );
    }
}
