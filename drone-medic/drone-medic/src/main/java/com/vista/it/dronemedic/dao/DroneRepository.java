package com.vista.it.dronemedic.dao;

import com.vista.it.dronemedic.model.Drone;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface DroneRepository extends CrudRepository<Drone, Long> {

    @Modifying
    @Query("update Drone c set c.batteryCapacity = ?1 ,c.droneState = ?2  where c.id = ?3 ")
     void updateDrone(@Param(value = "batteryCapacity")  int batteryCapacity,
                      @Param(value = "droneState") String droneState,
                      @Param(value = "id") long id

              );
}
