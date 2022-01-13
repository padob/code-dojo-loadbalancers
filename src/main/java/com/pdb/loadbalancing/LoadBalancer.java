package com.pdb.loadbalancing;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LoadBalancer {
    private static final int MAX_INSTANCES_NUMBER = 10;
    private List<String> adresses;

    public LoadBalancer() {
        this.adresses = new ArrayList<>();
    }

//    public void register(String adress) {
//        this.addresses.add(adress); //TODO duplicated? order?
//    }

    public void register(List<String> adressesToBeRegistered) throws IllegalArgumentException {
        List<String> distinctAdresses = adressesToBeRegistered.stream().distinct().collect(Collectors.toList());
        if (adresses.size() + distinctAdresses.size() > MAX_INSTANCES_NUMBER) {
            //fail
            System.out.println("MAX_INSTANCES_NUMBER was taken");
            throw new IllegalArgumentException("MAX_INSTANCES_NUMBER was taken");
            //TODO exception? error handlig?
        } else {
            this.adresses.addAll(distinctAdresses); //<10
        }
    }

    public List<String> getAdresses() {
        return adresses;
    }

}
