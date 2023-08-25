package com.vista.it.dronemedic.model;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DroneTest {

    Drone drone=  new Drone();
    @Transactional
    @Test
    public  void getId() throws Exception{
        Long IdValue = 2L;
        drone.setId(IdValue);
        assertEquals(IdValue, drone.getId());
    }
}
