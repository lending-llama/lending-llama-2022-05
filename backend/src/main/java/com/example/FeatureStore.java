package com.example;

import org.springframework.stereotype.Component;


@Component
public class FeatureStore {

    private boolean featureToggle;

    public FeatureStore() {
        this.featureToggle = false;
    }


    public boolean getFeatureToggle() {
        return featureToggle;
    }

    public void setFeatureToggle(boolean featureToggle) {
        this.featureToggle = featureToggle;
    }
    
}
