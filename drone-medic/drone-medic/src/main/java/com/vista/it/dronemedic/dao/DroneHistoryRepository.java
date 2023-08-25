package com.vista.it.dronemedic.dao;

import com.vista.it.dronemedic.model.Drone;
import com.vista.it.dronemedic.model.DroneHistory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface DroneHistoryRepository extends CrudRepository<DroneHistory,Long> {


    List<DroneHistory> findByDroneIdOrderByDateDesc(Long droneId);


}
