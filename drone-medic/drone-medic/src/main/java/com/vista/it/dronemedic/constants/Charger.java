package com.vista.it.dronemedic.constants;

public enum Charger {

    CHARGING(1),FULL(2),INADEQUATECHARGING(3) ,DISCHARGING(4);
 private  int value;

    Charger(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
