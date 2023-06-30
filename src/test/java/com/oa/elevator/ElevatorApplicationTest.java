package com.oa.elevator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ElevatorApplicationTest {
    ElevatorApplication elevatorApplication;
    Elevator elevatorMock;

    @BeforeEach
    void setUp() {
        elevatorMock = mock(Elevator.class);
        elevatorApplication = new ElevatorApplication(elevatorMock);
    }

    @Test
    public void test_rideTheElevator_success() {
        // mock our dependency calls to Elevator
        when(elevatorMock.calculateMultiTripTime(any())).thenReturn(500);

        // Call our method
        elevatorApplication.rideTheElevator(new String[] { "12", "2", "9" });

        // Verify Elevator was called with the expected floors
        verify(elevatorMock).calculateMultiTripTime(new int[] { 12, 2, 9 });
    }

    @Test
    public void test_rideTheElevator_failure() {
        // Call our method with "bad" inputs
        elevatorApplication.rideTheElevator(new String[] { "1e2", "2", "9" });

        // Verify Elevator was never called
        verify(elevatorMock, never()).calculateMultiTripTime(any());
    }
}
