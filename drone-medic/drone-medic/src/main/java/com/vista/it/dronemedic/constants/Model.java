package com.vista.it.dronemedic.constants;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;


public enum Model {
    LIGHTWEIGHT(150), MIDDLEWEIGHT(300),
    CRUISERWEIGHT(350),HEAVYWEIGHT(450);
    private  int value;

    Model(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
