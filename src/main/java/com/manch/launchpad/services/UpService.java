package com.manch.launchpad.services;

import com.manch.launchpad.models.request.ServiceModel;

import java.util.HashMap;
import java.util.List;

public interface UpService {
    void createServices(Long microserviceId);
    void runServices(HashMap<ServiceModel, List<ServiceModel>> serviceDependencyGraph);
}
