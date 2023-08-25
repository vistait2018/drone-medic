package com.vista.it.dronemedic.droneTask;

import com.vista.it.dronemedic.service.DroneAutomationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DroneAutomationTask {



    @Autowired
    private DroneAutomationService droneAutomationService;

    @Scheduled(fixedRate =120000)
    public void chargeAndChangeDroneState(){
        droneAutomationService.chargeDroneChangeState();
    }
}
