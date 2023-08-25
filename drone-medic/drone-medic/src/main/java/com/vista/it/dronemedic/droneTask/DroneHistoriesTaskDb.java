package com.vista.it.dronemedic.droneTask;

import com.vista.it.dronemedic.service.LogServiceDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DroneHistoriesTaskDb {

    @Autowired
    LogServiceDb logServiceDb;

    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        logServiceDb.writeLog();

    }
}
