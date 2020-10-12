package com.air.careerassistant.model;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;

public class AdzunaResult<T> {

    HashMap<T, T> jobListings = new HashMap<>();

    ArrayList<HashMap> results = new ArrayList<>();

    public AdzunaResult() {};

    public AdzunaResult(ArrayList results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "AdzunaResult{" +
                "results=" + results +
                '}';
    }
}
