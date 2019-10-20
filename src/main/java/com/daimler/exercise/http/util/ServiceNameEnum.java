package com.daimler.exercise.http.util;

import lombok.Getter;

public enum ServiceNameEnum {

	READ_BUS_STOP("http-verticle-read-bus-stop-service");

	@Getter
    private String name;

	ServiceNameEnum(String name) {
		this.name = name;
	}

}

