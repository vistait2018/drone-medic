package com.vista.it.dronemedic.service;

import com.vista.it.dronemedic.dao.DroneHistoryRepository;
import com.vista.it.dronemedic.model.Drone;
import com.vista.it.dronemedic.model.DroneHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DroneHistoryService {
    @Autowired
    private DroneHistoryRepository droneHistoryRepository;
    @Autowired
    private DroneService droneService;

    public List<DroneHistory>  droneHistories(){
        return (List<DroneHistory>) droneHistoryRepository.findAll();
    }
    public DroneHistory getDroneHistory(Long id){
        return  droneHistoryRepository.findById(id).get();
    }

    public void saveDroneHistory(DroneHistory droneHistory){
        droneHistoryRepository.save(droneHistory);

    }
   public List<DroneHistory> droneHistoryForAParticularDrone(Long droneId){
     List<DroneHistory> droneHistories =droneHistoryRepository.findByDroneIdOrderByDateDesc(droneId);
      return droneHistories;

    }
    public void saveAll(List<DroneHistory> droneHistories){
        droneHistoryRepository.saveAll(droneHistories);
    }
}
