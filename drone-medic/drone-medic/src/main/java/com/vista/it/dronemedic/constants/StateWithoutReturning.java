package com.vista.it.dronemedic.constants;

public enum StateWithoutReturning {
    IDLE(1),LOADING(6),LOADED(2),DELIVERING(3),DELIVERED(4);
    private  int value;

    StateWithoutReturning(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
