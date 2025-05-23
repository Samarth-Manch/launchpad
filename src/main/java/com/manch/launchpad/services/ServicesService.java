package com.manch.launchpad.services;

import com.manch.launchpad.models.request.ServiceDependencyModel;
import com.manch.launchpad.models.request.ServiceModel;

public interface ServicesService {
    ServiceModel createService(ServiceModel service);
    ServiceModel updateService(ServiceModel service);
    ServiceModel getService(String id);
    ServiceDependencyModel createDependency(ServiceDependencyModel dependency);
    void removeService(String id);
    void removeServiceByServiceId(String serviceId);
    ServiceModel getServiceByServiceId(String serviceId);
}
