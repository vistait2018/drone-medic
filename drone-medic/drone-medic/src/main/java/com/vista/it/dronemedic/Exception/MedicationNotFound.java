package com.vista.it.dronemedic.Exception;

import java.text.MessageFormat;

public class MedicationNotFound  extends RuntimeException{
    public MedicationNotFound(Long id) {
        super(MessageFormat.format("Medication With {} no found",id));
    }
}
