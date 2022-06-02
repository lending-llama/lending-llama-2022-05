package com.example;

public class FeatureStore {

    private boolean multipleTiersEnabled = false;

    public FeatureStore() {
    }

    public boolean isMultipleTiersEnabled() {
        return multipleTiersEnabled;
    }

    public void setMultipleTiersEnabled(final boolean multipleTiersEnabled) {
        this.multipleTiersEnabled = multipleTiersEnabled;
    }

}
