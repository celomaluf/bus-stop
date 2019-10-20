package com.daimler.exercise.http.util;

import lombok.Getter;

public enum EventBusEnum {

    OPERATORS_BY_TS ("find.all.operators.by.timestamp"),
    VEHICLES_BY_TS_OPERATOR ("find.all.vehicles.by.timestamp.and.operator"),
    VEHICLES_BY_TS_OPERATOR_AT_STOP ("find.all.vehicles.by.timestamp.and.operator.at.a.bus.stop"),
    TRACES_BY_TS_VEHICLE ("find.all.traces.by.vehicle");

    @Getter
    private String name;

    EventBusEnum(String name) {
        this.name = name;
    }
}


