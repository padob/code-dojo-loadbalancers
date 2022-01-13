package com.pdb.loadbalancing;

import java.util.List;

public class Router {

    private LoadBalancer loadBalancer;

    public Router() {
        loadBalancer = new LoadBalancer();
    }

    public void startup(List<String> adresses) throws IllegalArgumentException {
        loadBalancer.register(adresses);
    }

    public List<String> getAdresses() {
        return loadBalancer.getAdresses(); //TODO NPE
    }


}
