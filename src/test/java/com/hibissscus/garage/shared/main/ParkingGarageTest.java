package com.hibissscus.garage.shared.main;

import com.hibissscus.garage.shared.model.vehicle.Car;
import com.hibissscus.garage.shared.model.vehicle.Motorbike;
import com.hibissscus.garage.shared.model.vehicle.Vehicle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class ParkingGarageTest {

    /**
     * Under test.
     */
    ParkingGarage garage;

    @Before
    public void setUp() {
        garage = new ParkingGarage.Builder(1, 1, 1).build();
    }

    @Test
    public void testBuilder() {
        assertEquals(1, garage.freeSpaceAvailable());
    }

    @Test
    public void testParkVehicle() {
        Vehicle motorbike = new Motorbike();
        assertTrue(garage.parkVehicle(motorbike));
        assertEquals(0, garage.freeSpaceAvailable());
    }

    @Test
    public void testUnParkVehicle() {
        Vehicle motorbike = new Motorbike();
        assertTrue(garage.parkVehicle(motorbike));
        assertEquals(0, garage.freeSpaceAvailable());
        assertTrue(garage.unParkVehicle(motorbike));
        assertEquals(1, garage.freeSpaceAvailable());
    }

    @Test
    public void testFreeSpaceAvailable() {
        assertEquals(1, garage.freeSpaceAvailable());
    }

    @Test
    public void testGetParkingLevels() {
        assertEquals(1, garage.getParkingLevels().length);
    }

    @Test
    public void testGetLevels() {
        assertEquals(1, garage.getParkingLevels().length);
    }

    @Test
    public void testIsCannotPark() {
        Vehicle car = new Car();
        assertFalse(garage.parkVehicle(car));
        assertTrue(garage.isCannotPark());
    }
}