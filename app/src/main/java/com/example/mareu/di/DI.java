package com.example.mareu.di;

import com.example.mareu.service.DummyMareuApiService;
import com.example.mareu.service.MareuApiService;


public class DI {

    private static final MareuApiService service = new DummyMareuApiService();

    public static MareuApiService getMareuApiService() {
        return service;
    }
}
