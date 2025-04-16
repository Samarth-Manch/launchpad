package com.manch.launchpad.services;

import com.manch.launchpad.models.request.DependencyServiceModel;
import com.manch.launchpad.models.request.ServiceModel;

public interface ServicesService {
    ServiceModel createService(ServiceModel service);
    ServiceModel updateService(ServiceModel service);
    ServiceModel getService(String id);
    DependencyServiceModel createDependency(DependencyServiceModel dependency);
    void removeService(String id);
    void removeServiceByServiceId(String serviceId);
}
