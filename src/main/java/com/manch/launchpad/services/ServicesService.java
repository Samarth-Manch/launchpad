package com.manch.launchpad.services;

import com.manch.launchpad.models.request.ServiceModel;

public interface ServicesService {
    ServiceModel createService(ServiceModel service);
    ServiceModel updateService(ServiceModel service);
    ServiceModel getService(String id);
}
