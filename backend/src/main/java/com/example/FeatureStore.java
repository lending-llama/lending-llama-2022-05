package com.example;

import org.springframework.stereotype.Component;


@Component
public class FeatureStore {

    private boolean featureToggle;

    public FeatureStore() {
        this.featureToggle = false;
    }


    public boolean getMultipleTiersFeatureToggle() {
        return featureToggle;
    }

    public void setMultipleTiersFeatureToggle(boolean featureToggle) {
        this.featureToggle = featureToggle;
    }

}
