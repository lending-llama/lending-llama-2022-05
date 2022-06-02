package com.example;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class FeatureStore {

    private String treatment;

    @Autowired
    public FeatureStore() {}
}


