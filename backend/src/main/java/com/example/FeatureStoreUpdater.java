package com.example;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.split.client.SplitClient;

@Component
public class FeatureStoreUpdater {

@Autowired
private FeatureStore featureStore;
private SplitClient splitClient;


   public FeatureStoreUpdater(SplitClient splitclient) {
    this.splitClient = splitclient;
   }

   @PostConstruct
   public void updateFeatureStore() {
    var treatment = splitClient.getTreatment("key","multiple-tiers");
    boolean flag = !"on".equals(treatment);
    featureStore.setFeatureToggle(flag);
   }
   

    
}
