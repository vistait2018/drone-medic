package com.vista.it.dronemedic.Exception;

import java.text.MessageFormat;

public class DroneHasAlreadyLoadedMicalException extends RuntimeException {
    public DroneHasAlreadyLoadedMicalException(Long droneId, Long medicId) {
        super(MessageFormat.format("Drone with {0} is already loaded with medication with {1}",droneId,medicId));
    }

}
