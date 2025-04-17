package com.manch.launchpad.services;

import com.manch.launchpad.models.request.ServiceModel;

import java.util.List;
import java.util.TreeMap;

public interface UpService {
    void createServices(Long microserviceId);
    void runServices(TreeMap<ServiceModel, List<ServiceModel>> serviceDependencyGraph);
}
